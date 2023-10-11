package de.broccolidev.springbootecommerce.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //protect endpoint /api/orders
        http.authorizeRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/api/orders/**")
                                .authenticated())
                .oauth2ResourceServer((oauth2ResourceServer) ->
                        oauth2ResourceServer
                                .jwt(Customizer.withDefaults()));

        //add CORS filters
        http.cors(Customizer.withDefaults());

        //add negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());

        //force a non-empty response body for 401s to make the response more friendly
        Okta.configureResourceServer401ResponseBody(http);

        //disable CSRF since we are not using Cookies for session tracking
        //otherwise you couldn't send bearer when accessing /purchase endpoint
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
