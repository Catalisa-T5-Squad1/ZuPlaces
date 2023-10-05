package br.com.catalisa.ZuPlaceApi.controller;

import br.com.catalisa.ZuPlaceApi.dto.SpaceRequestDto;
import br.com.catalisa.ZuPlaceApi.dto.SpaceResponseDto;
import br.com.catalisa.ZuPlaceApi.service.SpaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/spaces", produces = {"application/json"})
@Tag(name = "Feature - Spaces")
public class SpaceController {

    @Autowired
    SpaceService spaceService;

    @GetMapping
    @Operation(summary = " : Lista todos os espaços cadastrados", method = "GET")
    public ResponseEntity<List<SpaceResponseDto>> findAllSpaces(){
        List<SpaceResponseDto> spaceResponseDtoList = spaceService.findAll();
        return new ResponseEntity<>(spaceResponseDtoList, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = " : Cadastra um novo recurso", method = "POST")
    public ResponseEntity<SpaceResponseDto> registerSpaces(@RequestBody SpaceRequestDto spaceRequestDto){
        SpaceResponseDto spaceResponseDto = spaceService.create(spaceRequestDto);
        return new ResponseEntity<>(spaceResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = " : Busca um espaço cadastrado por ID", method = "GET")
    public ResponseEntity<SpaceResponseDto> findSpacesById(@PathVariable Long id){
        SpaceResponseDto spaceResponseDto = spaceService.findById(id);
        return new ResponseEntity<>(spaceResponseDto, HttpStatus.OK);
    }
}
