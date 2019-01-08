package test.micky.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebFlux
@Slf4j
public class Config implements WebFluxConfigurer {

    @Autowired
    private ThymeleafReactiveViewResolver viewResolver;

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder().failOnUnknownProperties(false)
            .serializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().enableLoggingRequestDetails(log.isDebugEnabled());
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        if (viewResolver != null) {
            log.error("viewResolver is emtpy");
        }
        registry.viewResolver(viewResolver);
    }

}
