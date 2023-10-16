package br.com.catalisa.ZuPlaceApi.model;

import br.com.catalisa.ZuPlaceApi.model.ContactModel;
import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class ContactModelTest {

    @Mock
    private UserModel userModel;

    @Mock
    private SpaceModel spaceModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGettersAndSetters() {
        ContactModel contact = new ContactModel();
        contact.setId(1L);
        contact.setName("Ricardo dos Santos");
        contact.setEmail("usuario@zup.com.br");
        contact.setPhone("11988887777");
        contact.setDescription("Gostaria de indicar esse espaço na empresa XPTO");
        contact.setUserModel(userModel);
        contact.setSpaceModel(spaceModel);

        assertEquals(1L, contact.getId());
        assertEquals("Ricardo dos Santos", contact.getName());
        assertEquals("usuario@zup.com.br", contact.getEmail());
        assertEquals("11988887777", contact.getPhone());
        assertEquals("Gostaria de indicar esse espaço na empresa XPTO", contact.getDescription());
        assertEquals(userModel, contact.getUserModel());
        assertEquals(spaceModel, contact.getSpaceModel());
    }
}

