package br.com.catalisa.ZuPlaceApi.service;

import br.com.catalisa.ZuPlaceApi.dto.UserRequestDto;
import br.com.catalisa.ZuPlaceApi.dto.UserResponseDto;
import br.com.catalisa.ZuPlaceApi.enums.PersonType;
import br.com.catalisa.ZuPlaceApi.exception.ResourseNotFoundException;
import br.com.catalisa.ZuPlaceApi.model.SpaceModel;
import br.com.catalisa.ZuPlaceApi.model.UserModel;
import br.com.catalisa.ZuPlaceApi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {
    private static Long ID = 1L;
    private  static final Integer INDEX = 0;
    private static final String NAME = "athos";
    private static final String EMAIL = "athossilva@gmail.com";
private static final String PASSWORD = "1234";
private static final PersonType personType = PersonType.PHISICAL_PERSON;
private static final String PHONE = "021996705392";
private static final  String DOCUMENT_TYPE = "01613587401";
private  static  final  List<SpaceModel> SPACES = new ArrayList<>();
private static final String USUARIO_NAO_ENCONTRADO = "usuário não encontrado";
private static final String USUARIO_NAO_CADASTRADO = "usuário não cadastrado";

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;
    private UserModel userModel;
    private UserRequestDto requestDto;
    private UserResponseDto responseDto;
    private Optional<UserModel> optionalUserModel;

    @BeforeEach
    void  setUp(){
        MockitoAnnotations.openMocks(this);
    startUser();
    }



    @Test
    void  whenFindByIdThenReturnAUserInstance(){
        when(repository.findById(anyLong())).thenReturn(optionalUserModel);
        UserModel responce = service.findById(ID);

        assertNotNull(responce);

        assertEquals(UserModel.class, responce.getClass());
        assertEquals(ID, responce.getId());
        assertEquals(NAME, responce.getName());
        assertEquals(EMAIL, responce.getEmail());
        assertEquals(PASSWORD, responce.getPassword());
        assertEquals(personType, responce.getPersonType());
        assertEquals(PHONE, responce.getPhone());
        assertEquals(DOCUMENT_TYPE, responce.getDocumentType());
        assertEquals(SPACES, responce.getSpaces());
    }
    @Test
    void   whenFindByIdThenReturnResourseNotFoundException(){
        when(repository.findById(anyLong()))
                .thenThrow(new ResourseNotFoundException(USUARIO_NAO_ENCONTRADO));
        try {
            service.findById(ID);

        } catch (Exception e){
    assertEquals(ResourseNotFoundException.class, e.getClass());
    assertEquals(USUARIO_NAO_ENCONTRADO, e.getMessage());

        }
    }
    @Test
    void whenFindAllThenReturnAListOfUsers(){
        when(repository.findAll()).thenReturn(List.of(userModel));
        List<UserModel> responce = service.findAll();
        assertNotNull(responce);
        assertEquals(1, responce.size());
        assertEquals(UserModel.class, responce.get(INDEX).getClass());
        assertEquals(ID, responce.get(INDEX).getId());
        assertEquals(NAME, responce.get(INDEX).getName());
        assertEquals(EMAIL, responce.get(INDEX).getEmail());
        assertEquals(PASSWORD, responce.get(INDEX).getPassword());
        assertEquals(personType, responce.get(INDEX).getPersonType());
        assertEquals(PHONE, responce.get(INDEX).getPhone());
        assertEquals(DOCUMENT_TYPE, responce.get(INDEX).getDocumentType());
        assertEquals(SPACES, responce.get(INDEX).getSpaces());
    }

    @Test
    void whenCreateThenReturnASuccess(){
        when(repository.save(any(UserModel.class))).thenReturn(userModel);

         UserResponseDto response = service.create(requestDto);
    assertNotNull(response);

        assertEquals(UserResponseDto.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(personType, response.getPersonType());
        assertEquals(PHONE, response.getPhone());
        assertEquals(DOCUMENT_TYPE, response.getDocumentType());

    }

    @Test
    void whenCreateThenReturnResourseNotFoundException() {
        when(repository.save(any())).thenThrow(new ResourseNotFoundException(USUARIO_NAO_CADASTRADO));
        try {
            service.create(requestDto);
        } catch (Exception e) {
            assertEquals(ResourseNotFoundException.class, e.getClass());
            assertEquals(USUARIO_NAO_CADASTRADO, e.getMessage());
        }
    }


    @Test
    void whenUpdateThenReturnSucess(){
        when(repository.save(any())).thenReturn(userModel);
        when(repository.findById(anyLong())).thenReturn(optionalUserModel);
        UserModel response = service.update(ID, responseDto);
            assertNotNull(response);
            assertEquals(UserModel.class, response.getClass());
            assertEquals(ID, response.getId());
            assertEquals(NAME, response.getName());
            assertEquals(EMAIL, response.getEmail());
            assertEquals(PASSWORD, response.getPassword());
            assertEquals(personType, response.getPersonType());
            assertEquals(PHONE, response.getPhone());
            assertEquals(DOCUMENT_TYPE, response.getDocumentType());
            assertEquals(SPACES, response.getSpaces());
    }

    @Test
    void whenUpdateThenReturnResourseNotFoundException(){
        when(repository.findById(anyLong())).thenThrow(new ResourseNotFoundException(USUARIO_NAO_CADASTRADO));
        try {
            service.update(ID, responseDto);
        } catch (Exception e){
            assertEquals(ResourseNotFoundException.class, e.getClass());
            assertEquals(USUARIO_NAO_CADASTRADO, e.getMessage());
        }
    }

    @Test
    void deleteSuccess(){
        when(repository.findById(anyLong())).thenReturn(optionalUserModel);
        doNothing().when(repository).deleteById(anyLong());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void whenDeleteByIdThenReturnResourseNotFoundExcepetion(){
        when(repository.findById(anyLong()))
                .thenThrow(new ResourseNotFoundException(USUARIO_NAO_ENCONTRADO));
        try {
            service.delete(ID);
        } catch (Exception e){
    assertEquals(ResourseNotFoundException.class, e.getClass());
       assertEquals(USUARIO_NAO_ENCONTRADO, e.getMessage());
        }
    }

    private void startUser(){
    userModel = new UserModel(ID, NAME, EMAIL, PASSWORD, personType, PHONE, DOCUMENT_TYPE, SPACES);
    requestDto = new UserRequestDto(NAME, EMAIL, PASSWORD, personType, PHONE, DOCUMENT_TYPE);
                    responseDto = new UserResponseDto(ID, NAME, EMAIL, PASSWORD, personType, PHONE, DOCUMENT_TYPE);
                    optionalUserModel = Optional.of(new UserModel(ID, NAME, EMAIL, PASSWORD, personType, PHONE, DOCUMENT_TYPE, SPACES));
}
}
