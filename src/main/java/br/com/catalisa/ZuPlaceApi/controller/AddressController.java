package br.com.catalisa.ZuPlaceApi.controller;

import br.com.catalisa.ZuPlaceApi.dto.*;
import br.com.catalisa.ZuPlaceApi.service.AddressService;
import br.com.catalisa.ZuPlaceApi.service.GoogleMapsService;
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
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @Autowired
    private GoogleMapsService googleMapsService;

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
    public ResponseEntity<CoordsResponseDto> createLong(@RequestBody CoordsRequestDto address){
        String addres = address.getLogradouro();
        CoordsResponseDto coordsResponseDto = googleMapsService.geocodeAddress(addres);
        System.out.println(coordsResponseDto);
        return ResponseEntity.status(HttpStatus.OK).body(coordsResponseDto);
    }

    @GetMapping(path = "/teste2")
    public ResponseEntity<DistanceRespondeDto> createDistance(@RequestBody DistanceRequestDto distanceRequestDto){
        DistanceRespondeDto distanceRespondeDto = googleMapsService.calculateDistance(
                distanceRequestDto.getLatitudeOrigem(),
                distanceRequestDto.getLongitudeOrigem(),
                distanceRequestDto.getLatitudeDestino(),
                distanceRequestDto.getLongitudeDestino());
        return ResponseEntity.status(HttpStatus.OK).body(distanceRespondeDto);
    }
}
