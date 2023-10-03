package de.broccolidev.springbootecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * The configuration for MyDataRestConfig only applies to REST APIs provided by Spring Data REST.
 * It does not apply to regular @RestControllers that we create manually.
 * As a result, for your CheckoutController we need to add CORS support, hence the need for MyAppConfig.java.
 * You can verify this by removing the CORS code in MyAppConfig.
 */
@Configuration
public class MyAppConfig implements WebMvcConfigurer {

    @Value("${allowed.origins}")
    private String theAllowedOrigins;

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Override
    public void addCorsMappings(CorsRegistry cors) {
        cors.addMapping(basePath + "/**").allowedOrigins(theAllowedOrigins);
    }
}
