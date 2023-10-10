package br.com.catalisa.ZuPlaceApi.service;

import br.com.catalisa.ZuPlaceApi.dto.ContactRequestDto;
import br.com.catalisa.ZuPlaceApi.dto.ContactResponseDto;
import br.com.catalisa.ZuPlaceApi.model.ContactModel;
import br.com.catalisa.ZuPlaceApi.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllContacts() {
        ContactModel contact1 = new ContactModel();
        ContactModel contact2 = new ContactModel();

        when(contactRepository.findAll()).thenReturn(Arrays.asList(contact1, contact2));

        ContactResponseDto responseDto1 = new ContactResponseDto();
        ContactResponseDto responseDto2 = new ContactResponseDto();

        when(modelMapper.map(contact1, ContactResponseDto.class)).thenReturn(responseDto1);
        when(modelMapper.map(contact2, ContactResponseDto.class)).thenReturn(responseDto2);

        List<ContactResponseDto> contactResponseDtoList = contactService.findAll();

        assertEquals(2, contactResponseDtoList.size());
    }



    @Test
    public void testDeleteContact() {
        Long contactId = 1L;

        contactService.delete(contactId);

        verify(contactRepository, times(1)).deleteById(contactId);
    }
}

