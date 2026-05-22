package com.radakovic.routing.rest;


import com.radakovic.routing.api.dto.RouteDto;
import com.radakovic.routing.service.RouteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteRest {

    private final RouteService routeService;

    public RouteRest(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping(value = "routing/{origin}/{destination}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RouteDto> getRoute(@PathVariable String origin, @PathVariable String destination) {
        return ResponseEntity.ok(new RouteDto(routeService.getRoute(origin, destination)));
    }
}
