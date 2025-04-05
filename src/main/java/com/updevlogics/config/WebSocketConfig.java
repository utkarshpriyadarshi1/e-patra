package com.updevlogics.config;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new BackupProgressHandler(), "/progress").setAllowedOrigins("*");
    }

}
