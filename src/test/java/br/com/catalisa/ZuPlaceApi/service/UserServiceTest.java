package br.com.catalisa.ZuPlaceApi.service;
import br.com.catalisa.ZuPlaceApi.enums.PersonType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


public class UserServiceTest {
    private static Long ID = 1L;
    private  static final Integer INDEX = 0;
    private static final String NAME = "athos";
    private static final String EMAIL = "athossilva@gmail.com";
private static final String PASSWORD = "1234";
private static PersonType personType;
private static final String phone = "021996705392";
private static final  String DOCUMENT_TYPE = "01613587401";
private static final String USUARIO_NAO_ENCONTRADO = "usuário não encontrado";
@InjectMocks
    private UserService service;
@Mock
    
}
