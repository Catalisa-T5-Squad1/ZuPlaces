package br.com.catalisa.ZuPlaceApi.controller;

import br.com.catalisa.ZuPlaceApi.dto.UserResponseDto;
import br.com.catalisa.ZuPlaceApi.enums.PersonType;
import br.com.catalisa.ZuPlaceApi.model.SpaceModel;
import br.com.catalisa.ZuPlaceApi.model.UserModel;
import br.com.catalisa.ZuPlaceApi.service.UserService;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {
    private static Long ID = 1L;
    private  static final Integer INDEX = 0;
    private static final String NAME = "athos";
    private static final String EMAIL = "athossilva@gmail.com";
    private static final String PASSWORD = "1234";
    private static final PersonType personType = PersonType.PHYSICAL_PERSON;
    private static final String PHONE = "021996705392";
    private static final  String DOCUMENT_TYPE = "01613587401";
    private  static  final  List<SpaceModel> SPACES = new ArrayList<>();
    private UserModel userModel = new UserModel();
    private UserResponseDto responseDto = new UserResponseDto();
    @InjectMocks
    private UserController controller;
    @Mock
    private UserService service;
    @Mock
    private  ModelMapper mapper;
    @BeforeEach
    void  setup(){
        MockitoAnnotations.openMocks(this);
startUser();
    }
@Test
void  whenFinByIdThenReturnSuccess(){
        when(service.findById(anyLong())).thenReturn(userModel);
when(mapper.map(any(), any())).thenReturn(responseDto);
ResponseEntity<UserResponseDto> response = controller.findById(ID);
assertNotNull(response);
assertNotNull(response.getBody());
assertEquals(ResponseEntity.class, response.getClass());
assertEquals(UserResponseDto.class, response.getBody().getClass());
    assertEquals(ID, response.getBody().getId());
    assertEquals(NAME, response.getBody().getName());
    assertEquals(EMAIL, response.getBody().getEmail());
    assertEquals(PASSWORD, response.getBody().getPassword());
    assertEquals(personType, response.getBody().getPersonType());
    assertEquals(PHONE, response.getBody().getPhone());


}
    private  void startUser(){
        userModel = new UserModel(ID, NAME, EMAIL, PASSWORD, personType, PHONE, DOCUMENT_TYPE, SPACES);
        responseDto = new UserResponseDto(ID, NAME, EMAIL, PASSWORD, personType, PHONE, DOCUMENT_TYPE);
    }
}
