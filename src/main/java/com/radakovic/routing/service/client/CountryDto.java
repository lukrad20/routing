package com.radakovic.routing.service.client;

import java.util.Set;

public record CountryDto(String cca3, Set<String> borders) {
}
