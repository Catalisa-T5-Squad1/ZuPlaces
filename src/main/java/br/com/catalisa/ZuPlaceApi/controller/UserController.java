package br.com.catalisa.ZuPlaceApi.controller;

import br.com.catalisa.ZuPlaceApi.dto.UserDto;
import br.com.catalisa.ZuPlaceApi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
