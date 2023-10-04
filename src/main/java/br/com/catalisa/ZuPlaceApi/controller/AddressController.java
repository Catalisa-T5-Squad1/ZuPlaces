package br.com.catalisa.ZuPlaceApi.controller;

import br.com.catalisa.ZuPlaceApi.dto.AddressResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.ZipCodeRequestDto;
import br.com.catalisa.ZuPlaceApi.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api/address", produces = {"application/json"})
@Tag(name = "Feature - Address")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;



    @GetMapping
    @Operation(summary = " : Lista todos os endereços cadastrados", method = "GET")
    public ResponseEntity<List<AddressResponseDto>> findAll(){
        logger.debug("Método findAll chamado");
        List<AddressResponseDto> addressList = addressService.findAll();
        logger.info("Total de endereços encontrados: {}", addressList.size());
        return ResponseEntity.ok(addressList);
    }

    @GetMapping(path = "/zipcode")
    @Operation(summary = " : Busca endereço pelo CEP", method = "GET")
    public ResponseEntity<AddressResponseDto> findAddress(@RequestBody ZipCodeRequestDto zipCodeRequestDto){
        AddressResponseDto addressFound = addressService.findZipCode(zipCodeRequestDto);
        return ResponseEntity.ok(addressFound);
    }
}
