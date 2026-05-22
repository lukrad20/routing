package com.radakovic.routing.service;

import com.radakovic.routing.exception.BadRequestException;
import com.radakovic.routing.model.RouteNode;
import com.radakovic.routing.service.client.CountryClientService;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

@Service
public class RouteService {

    private final CountryClientService countryClientService;

    public RouteService(CountryClientService countryClientService) {
        this.countryClientService = countryClientService;
    }

    public List<String> getRoute(String originCode, String destinationCode) {
        if (Objects.equals(originCode, destinationCode)) {
            throw new BadRequestException("Origin same as destinationCode");
        }

        Map<String, Set<String>> countryCodesMap = countryClientService.getCountries();

        validateCountryCode(originCode, countryCodesMap);
        validateCountryCode(destinationCode, countryCodesMap);

        Set<String> visitedCountryCodes = new HashSet<>();
        Queue<RouteNode> routeNodeToVisit = new ArrayDeque<>();

        routeNodeToVisit.add(new RouteNode(null, originCode));

        while (!routeNodeToVisit.isEmpty()) {
            RouteNode routeNode = routeNodeToVisit.poll();

            if (visitedCountryCodes.contains(routeNode.code())) {
                continue;
            }

            visitedCountryCodes.add(routeNode.code());

            Set<String> borderingCountries = countryCodesMap.getOrDefault(routeNode.code(), Collections.emptySet());

            if (borderingCountries.contains(destinationCode)) {
                return getRoute(new RouteNode(routeNode, destinationCode));
            }

            for (String countryCode : borderingCountries) {
                if (!visitedCountryCodes.contains(countryCode)) {
                    routeNodeToVisit.add(new RouteNode(routeNode, countryCode));
                }
            }
        }

        throw new BadRequestException("Route not found");
    }

    private void validateCountryCode(String countryCode, Map<String, Set<String>> countryCodesMap) {
        if (!countryCodesMap.containsKey(countryCode)) {
            throw new BadRequestException("Country with code '%s' not found".formatted(countryCode));
        }
    }

    private List<String> getRoute(RouteNode routeNode) {
        List<String> routeList = new ArrayList<>();

        while (routeNode != null) {
            routeList.add(routeNode.code());
            routeNode = routeNode.parent();
        }

        Collections.reverse(routeList);

        return routeList;
    }
}
