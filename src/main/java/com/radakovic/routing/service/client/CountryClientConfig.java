package com.radakovic.routing.service.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@Configuration
public class CountryClientConfig {

    @Bean
    RestClient restClient() {
        return RestClient.builder()
                .baseUrl("https://raw.githubusercontent.com")
                .configureMessageConverters(converters -> converters.addCustomConverter(messageConverter()))
                .build();
    }

    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory(RestClient restClient) {
        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build();
    }


    @Bean
    public CountryClient userServiceClient(HttpServiceProxyFactory factory) {
        return factory.createClient(CountryClient.class);
    }

    private JacksonJsonHttpMessageConverter messageConverter() {
        JacksonJsonHttpMessageConverter converter = new JacksonJsonHttpMessageConverter();

        converter.setSupportedMediaTypes(List.of(
                MediaType.APPLICATION_JSON,
                MediaType.TEXT_PLAIN
        ));

        return converter;
    }
}