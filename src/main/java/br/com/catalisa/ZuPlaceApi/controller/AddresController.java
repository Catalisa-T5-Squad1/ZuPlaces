package br.com.catalisa.ZuPlaceApi.controller;

import br.com.catalisa.ZuPlaceApi.dto.AddresResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.ZipCodeRequestDto;
import br.com.catalisa.ZuPlaceApi.service.AddresService;
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
@RequestMapping(value = "/api/addres", produces = {"application/json"})
@Tag(name = "Feature - Addres")
public class AddresController {

    private static final Logger logger = LoggerFactory.getLogger(AddresController.class);

    @Autowired
    private AddresService addresService;



    @GetMapping
    @Operation(summary = " : Lista todos os endereços cadastrados", method = "GET")
    public ResponseEntity<List<AddresResponseDto>> findAll(){
        logger.debug("Método findAll chamado");
        List<AddresResponseDto> addresList = addresService.findAll();
        logger.info("Total de endereços encontrados: {}", addresList.size());
        return ResponseEntity.ok(addresList);
    }

    @GetMapping(path = "/zipcode")
    @Operation(summary = " : Busca endereço pelo CEP", method = "GET")
    public ResponseEntity<AddresResponseDto> findAddres(@RequestBody ZipCodeRequestDto zipCodeRequestDto){
        AddresResponseDto addresFound = addresService.findZipCode(zipCodeRequestDto);
        return ResponseEntity.ok(addresFound);
    }
}
