package br.com.catalisa.ZuPlaceApi.service;

import br.com.catalisa.ZuPlaceApi.dto.*;
import br.com.catalisa.ZuPlaceApi.enums.PersonType;
import br.com.catalisa.ZuPlaceApi.exception.ResourseNotFoundException;
import br.com.catalisa.ZuPlaceApi.model.AddressModel;
import br.com.catalisa.ZuPlaceApi.model.ResourceModel;
import br.com.catalisa.ZuPlaceApi.model.SpaceModel;
import br.com.catalisa.ZuPlaceApi.model.UserModel;
import br.com.catalisa.ZuPlaceApi.repository.AddressRepository;
import br.com.catalisa.ZuPlaceApi.repository.ResourceRepository;
import br.com.catalisa.ZuPlaceApi.repository.SpaceRepository;
import br.com.catalisa.ZuPlaceApi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SpaceServiceTest {
    private static final Long ID = 1L;
private  static final String name = "faculdade";
private final  UserModel user = new UserModel();
private final ResourceModel resourceModel = new ResourceModel();
private final AddressModel addressModel = new AddressModel();
private static final double distance = 15635.635;
@InjectMocks
    private SpaceService service;
@Mock
    private SpaceRepository spaceRepository;
@Mock
    private UserRepository userRepository;
@Mock
    private ResourceRepository resourceRepository;
@Mock
    private AddressService addressService;
@Mock
    private AddressRepository addressRepository;
@Mock
    private GoogleMapsService googleMapsService;
@Mock
    private ModelMapper mapper;
private SpaceModel spaceModel;
private SpaceRequestDto spaceRequestDto;
private SpaceResponseDto spaceResponseDto;
private Optional<SpaceModel> optionalSpaceModel;
private final UserResponseDto userResponseDto = new UserResponseDto();
private final ResourceResponseDto resourceResponseDto = new ResourceResponseDto();
private  final  AddressResponseDto addressResponseDto = new AddressResponseDto();
private static final Long USER_ID = 1L;
private static final Long RESOURSE_ID = 1L;
            private ZipCodeRequestDto adress = new ZipCodeRequestDto();
private void startSpace(){

}
}
