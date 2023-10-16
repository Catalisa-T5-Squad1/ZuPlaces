package br.com.catalisa.ZuPlaceApi.controller;

import br.com.catalisa.ZuPlaceApi.dto.*;
import br.com.catalisa.ZuPlaceApi.exception.ExternalRequestFailureException;
import br.com.catalisa.ZuPlaceApi.service.AddressService;
import br.com.catalisa.ZuPlaceApi.service.GoogleMapsService;
import br.com.catalisa.ZuPlaceApi.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/address", produces = {"application/json"})
@Tag(name = "Feature - Address")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @Autowired
    private GoogleMapsService googleMapsService;

    @Autowired
    private LocationService locationService;

    @GetMapping
    @Operation(summary = " : Lista todos os endereços cadastrados", method = "GET")
    public ResponseEntity<List<AddressResponseDto>> findAll(){
        logger.debug("Método findAll chamado");
        List<AddressResponseDto> addressList = addressService.findAll();
        logger.info("Total de endereços encontrados: {}", addressList.size());
        return ResponseEntity.ok(addressList);
    }

    @PostMapping
    @Operation(summary = " : Cadastra endereço pelo CEP", method = "POST")
    public ResponseEntity<AddressResponseDto> createAddress(@RequestBody ZipCodeRequestDto zipCodeRequestDto){
        logger.debug("Método createAddress chamado");
        AddressResponseDto addressFound = addressService.createAddres(zipCodeRequestDto);
        logger.info("Endereço cadastrado com sucesso: {} ", addressFound.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(addressFound);
    }

    @GetMapping(path = "/teste")
    public ResponseEntity<CoordsResponseDto> createLong(@RequestBody CoordsRequestDto address) throws ExternalRequestFailureException {
        String addres = address.getLogradouro();
        CoordsResponseDto coordsResponseDto = googleMapsService.geocodeAddress(addres);
        System.out.println(coordsResponseDto);
        return ResponseEntity.status(HttpStatus.OK).body(coordsResponseDto);
    }

    //método para testar o método: googleMapsService.getLatitudeAndLongitudeUser() que pega a longitude e latitude do usuário.
    @GetMapping(path = "/getLocationUser")
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


//
//    @GetMapping(path = "/calculoDistancia)
//    public ResponseEntity<List<SpaceResponseProximityLocationDto>> createLong(@RequestBody SpaceRequestProximityLocationDto spaceRequestProximityLocationDto){
//        List<SpaceResponseProximityLocationDto> spaceResponseProximityLocationDto = locationService.findSpacesByAddressProximity(
//                spaceRequestProximityLocationDto.getLatitudeOrigem(),
//                spaceRequestProximityLocationDto.getLongitudeOrigem(),
//                spaceRequestProximityLocationDto.getMaxDistance());
//        System.out.println(spaceResponseProximityLocationDto);
//        return ResponseEntity.status(HttpStatus.OK).body(spaceResponseProximityLocationDto);
//    }

    @PostMapping(path = "/testeDistanciaProxima")
    public ResponseEntity<List<SpaceResponseProximityLocationDto>> createLong(@RequestBody SpaceRequestProximityLocationDto spaceRequestProximityLocationDto) throws ExternalRequestFailureException {
        List<SpaceResponseProximityLocationDto> spaceResponseProximityLocationDto = locationService.findSpacesByAddressProximity(
                spaceRequestProximityLocationDto.getLatitudeOrigem(),
                spaceRequestProximityLocationDto.getLongitudeOrigem(),
                spaceRequestProximityLocationDto.getMaxDistance());
        System.out.println("Requisição da lista de spaces feita: " + spaceResponseProximityLocationDto);
        return ResponseEntity.status(HttpStatus.OK).body(spaceResponseProximityLocationDto);
    }
}
