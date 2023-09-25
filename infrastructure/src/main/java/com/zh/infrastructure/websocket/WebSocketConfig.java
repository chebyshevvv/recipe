/*
package com.zh.infrastructure.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final Map<String,>

    public WebSocketConfig(List<WebSocketHandler> handlers) {
        this.handlers = handlers;
        handlers.forEach(
                handler -> {
                    boolean present = handler.getClass().isAnnotationPresent(WebSocketMapping.class);
                    if (present){
                        WebSocketMapping annotation = handler.getClass().getAnnotation(WebSocketMapping.class);
                        String value = annotation.value();
                    }
                }
        );
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

    }

}
*/
