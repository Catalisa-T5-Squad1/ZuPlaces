package br.com.catalisa.ZuPlaceApi.controller;

import br.com.catalisa.ZuPlaceApi.dto.GeoLocationUserResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.SpaceRequestProximityLocationDto;
import br.com.catalisa.ZuPlaceApi.dto.SpaceResponseProximityLocationDto;
import br.com.catalisa.ZuPlaceApi.exception.ExternalRequestFailureException;
import br.com.catalisa.ZuPlaceApi.service.AddressService;
import br.com.catalisa.ZuPlaceApi.service.GoogleMapsService;
import br.com.catalisa.ZuPlaceApi.service.LocationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/location", produces = {"application/json"})
@Tag(name = "Feature - Location")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private GoogleMapsService googleMapsService;

    @Autowired
    private LocationService locationService;


    @GetMapping
    public ResponseEntity<GeoLocationUserResponseDto> getLocationUser() throws ExternalRequestFailureException {
        GeoLocationUserResponseDto geoLocationUserResponseDto = googleMapsService.getLatitudeAndLongitudeUser();
        System.out.println("Requisição foi feita ->  "
                + "Latitude: "
                + geoLocationUserResponseDto.getLatitude()
                + " "
                + "Longitude: "
                + geoLocationUserResponseDto.getLongitude());
        return ResponseEntity.status(HttpStatus.OK).body(geoLocationUserResponseDto);
    }

    @PostMapping
    public ResponseEntity<List<SpaceResponseProximityLocationDto>> createLong(@RequestBody SpaceRequestProximityLocationDto spaceRequestProximityLocationDto) throws ExternalRequestFailureException {
        List<SpaceResponseProximityLocationDto> spaceResponseProximityLocationDto = locationService.findSpacesByAddressProximity(
                spaceRequestProximityLocationDto.getLatitudeOrigem(),
                spaceRequestProximityLocationDto.getLongitudeOrigem(),
                spaceRequestProximityLocationDto.getMaxDistance());
        System.out.println("Requisição da lista de spaces feita: " + spaceResponseProximityLocationDto);
        return ResponseEntity.status(HttpStatus.OK).body(spaceResponseProximityLocationDto);
    }
}
