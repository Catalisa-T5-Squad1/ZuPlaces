package br.com.catalisa.ZuPlaceApi.controller;

import br.com.catalisa.ZuPlaceApi.dto.ResourceRequestDto;
import br.com.catalisa.ZuPlaceApi.dto.ResourceResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.UserRequestDto;
import br.com.catalisa.ZuPlaceApi.dto.UserResponseDto;
import br.com.catalisa.ZuPlaceApi.model.UserModel;
import br.com.catalisa.ZuPlaceApi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    public   static  final String ID = "/{id}";
    @Autowired
    private UserService service;
    @Autowired
    private ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers(){
        return ResponseEntity.ok()
                .body(service.findAll().stream()
                        .map(u -> mapper.map(u, UserResponseDto.class))
                        .collect(Collectors.toList()));
    }

    @GetMapping(value = ID)
    public ResponseEntity<UserModel> findById (@PathVariable Long id){
        UserModel userModel = service.findById(id);
        return ResponseEntity.ok().body(userModel);
    }

    @PostMapping
    @Operation(summary = " : Cadastra um novo recurso", method = "POST")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = service.create(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = ID)
    public ResponseEntity<UserResponseDto> update(UserResponseDto userResponseDto){
            UserModel updateUser = service.update(userResponseDto);
            return ResponseEntity.ok().body(mapper.map(updateUser, UserResponseDto.class));
    }
}
