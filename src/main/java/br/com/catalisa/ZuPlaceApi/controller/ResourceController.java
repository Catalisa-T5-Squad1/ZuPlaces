package br.com.catalisa.ZuPlaceApi.controller;

import br.com.catalisa.ZuPlaceApi.dto.ResourceRequestDto;
import br.com.catalisa.ZuPlaceApi.dto.ResourceResponseDto;
import br.com.catalisa.ZuPlaceApi.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@Tag(name = "Feature - Resources")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @GetMapping
    @Operation(summary = " : Lista todos os recursos cadastrados", method = "GET")
    public ResponseEntity<List<ResourceResponseDto>> findAllResources(){
        List<ResourceResponseDto> resourceResponseDtoList = resourceService.findAll();
        return new ResponseEntity<>(resourceResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = " : Busca um recurso cadastrado por ID", method = "GET")
    public ResponseEntity<ResourceResponseDto> findResourceById(@PathVariable Long id){
        ResourceResponseDto responseDto = resourceService.findById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = " : Cadastra um novo recurso", method = "POST")
    public ResponseEntity<ResourceResponseDto> registerResource(@RequestBody ResourceRequestDto resourceRequestDto){
        ResourceResponseDto resourceResponseDto = resourceService.create(resourceRequestDto);
        return new ResponseEntity<>(resourceResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = " : Altera um recurso cadastrado por ID", method = "PUT")
    public ResponseEntity<ResourceResponseDto> alterResource(@PathVariable Long id,
            @RequestBody ResourceRequestDto resourceRequestDto){
        ResourceResponseDto resourceResponseDto = resourceService.update(id, resourceRequestDto);
        return new ResponseEntity<>(resourceResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = " : Deleta um recurso cadastrado por ID", method = "DELETE")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id){
        resourceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
