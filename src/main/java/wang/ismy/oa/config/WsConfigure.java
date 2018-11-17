package wang.ismy.oa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import wang.ismy.oa.controller.CommunicationSocketController;


@Configuration
@EnableWebSocket
public class WsConfigure implements WebSocketConfigurer
{
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
    {
        System.out.println("==========================");
        registry.addHandler(myHandler(), "/comm").setAllowedOrigins("*");
    }

    @Bean
    public CommunicationSocketController myHandler()
    {
        return new CommunicationSocketController();
    }
}

