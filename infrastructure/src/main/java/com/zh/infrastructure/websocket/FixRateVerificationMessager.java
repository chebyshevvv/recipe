package com.zh.infrastructure.websocket;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class FixRateVerificationMessager {
    @Data
    public static class Verification {
        private final String code;
        private final Long generateTime;
        private boolean isSend;

        public Verification(String code, Long generateTime) {
            this.code = code;
            this.generateTime = generateTime;
            this.isSend = false;
        }
    }

    private final Map<WebSocketSession,Verification> map = new HashMap<>();

    private static final long INTERVAL_MILLISECONDS = 1000;

    public FixRateVerificationMessager() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(
                () -> {
                    if (!map.isEmpty()){

                        map.forEach(
                                (session,ver) -> {

                                    if (!session.isOpen()){

                                        this.map.remove(session);
                                    }else if (!ver.isSend() && System.currentTimeMillis() - ver.generateTime >= INTERVAL_MILLISECONDS){

                                        WebSocketMessager.sendVerificationCode(session,ver.code);
                                        ver.setSend(true);

                                        try {

                                            onSend(session);
                                        }catch (Exception e){

                                            log.error("发送验证码之后执行onSend失败");
                                        }
                                    }
                                }
                        );
                    }
                },
                1,
                1,
                TimeUnit.SECONDS
        );
    }

    public void addSession(WebSocketSession session){
        this.map.put(session,refresh());
    }
    public abstract void onSend(WebSocketSession session) throws Exception;

    public boolean verifyAndRefresh(WebSocketSession session,String receiveCode){
        boolean verified = this.map.containsKey(session) && this.map.get(session).code.equalsIgnoreCase(receiveCode);
        if (verified){

            this.map.replace(session,refresh());
        }
        return verified;
    }
    private Verification refresh(){
        return new Verification(RandomUtil.randomNumbers(4),System.currentTimeMillis());
    }
}
