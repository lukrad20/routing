package com.radakovic.routing.service.client;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Set;

@HttpExchange
public interface CountryClient {

    @GetExchange(value = "/mledoze/countries/master/countries.json")
    Set<CountryDto> getCountries();
}
