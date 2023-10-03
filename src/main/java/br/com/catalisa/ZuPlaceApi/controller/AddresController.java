package br.com.catalisa.ZuPlaceApi.controller;

import br.com.catalisa.ZuPlaceApi.dto.AddresResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.ZipCodeRequestDto;
import br.com.catalisa.ZuPlaceApi.service.AddresService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/zipcode", produces = {"application/json"})
@Tag(name = "Feature - Addres")
public class AddresController {

    @Autowired
    private AddresService addresService;

    @GetMapping
    @Operation(summary = " : Busca endereço pelo CEP", method = "GET")
    public ResponseEntity<AddresResponseDto> findAddres(@RequestBody ZipCodeRequestDto zipCodeRequestDto){
        AddresResponseDto addresFound = addresService.findCep(zipCodeRequestDto);
        return ResponseEntity.ok(addresFound);
    }
}
