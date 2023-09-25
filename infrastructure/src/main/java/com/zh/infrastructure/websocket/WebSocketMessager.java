package com.zh.infrastructure.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Slf4j
public class WebSocketMessager {
    public static String getVerificationCode(String message){
        String result = "";
        if (message.startsWith("captcha")){

            result = message.substring("captcha".length());
        }
        return result;
    }
    public static void sendRaw(WebSocketSession session,String message){
        if (session.isOpen()){

            try {

                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {

                log.error("发送信息失败");
            }
        }
    }
    public static void sendFinish(WebSocketSession session,Boolean code){
        sendRaw(session,"finish" + code);
    }
    public static void sendVerificationCode(WebSocketSession session,String code){
        sendRaw(session,"captcha" + code);
    }
    public static void sendVerificationCodeResult(WebSocketSession session,Boolean result){
        sendRaw(session,"captcharesult" + result);
    }
}
