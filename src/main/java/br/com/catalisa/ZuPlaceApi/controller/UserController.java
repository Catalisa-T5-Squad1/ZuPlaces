package br.com.catalisa.ZuPlaceApi.controller;

import br.com.catalisa.ZuPlaceApi.dto.UserDto;
import br.com.catalisa.ZuPlaceApi.model.UserModel;
import br.com.catalisa.ZuPlaceApi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<List<UserDto>> findAllUsers(){
        return ResponseEntity.ok()
                .body(service.findAll().stream()
                        .map(u -> mapper.map(u, UserDto.class))
                        .collect(Collectors.toList()));
    }

    @GetMapping(value = ID)
    public ResponseEntity<UserModel> findById (@PathVariable Long id){
        UserModel userModel = service.findById(id);
        return ResponseEntity.ok().body(userModel);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(UserDto userDto){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(ID)
                .buildAndExpand(service.create(userDto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping(value = ID)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = ID)
    public ResponseEntity<UserDto> update(UserDto userDto){
            UserModel updateUser = service.update(userDto);
            return ResponseEntity.ok().body(mapper.map(updateUser, UserDto.class));
    }
}
