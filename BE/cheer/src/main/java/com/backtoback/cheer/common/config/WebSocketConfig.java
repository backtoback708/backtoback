package com.backtoback.cheer.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
//        config.setApplicationDestinationPrefixes("");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/cheer").setAllowedOrigins("http://localhost:3000").withSockJS();
<<<<<<< HEAD
        registry.addEndpoint("/ws").setAllowedOrigins("http://k8a708.p.ssafy.io").withSockJS();
=======
        registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:3000").withSockJS();
>>>>>>> d3e242c9852359e36e08f18e14d7f969c3bdb292
    }

}
