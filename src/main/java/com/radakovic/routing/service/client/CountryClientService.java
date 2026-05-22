package com.radakovic.routing.service.client;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CountryClientService {

    private final CountryClient countryClient;

    public CountryClientService(CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    @Cacheable("countries")
    public Map<String, Set<String>> getCountries() {
        return countryClient.getCountries().stream()
                .collect(Collectors.toMap(CountryDto::cca3, CountryDto::borders));
    }
}
