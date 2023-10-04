package br.com.catalisa.ZuPlaceApi.service;

import br.com.catalisa.ZuPlaceApi.dto.ResourceRequestDto;
import br.com.catalisa.ZuPlaceApi.dto.ResourceResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.UserRequestDto;
import br.com.catalisa.ZuPlaceApi.dto.UserResponseDto;
import br.com.catalisa.ZuPlaceApi.model.ResourceModel;
import br.com.catalisa.ZuPlaceApi.model.UserModel;
import br.com.catalisa.ZuPlaceApi.repository.UserRepository;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    public List<UserModel> findAll(){
        return repository.findAll();
    }
    public UserModel findById(Long id){
        return  repository.findById(id).orElse(null);
    }

    public UserResponseDto create(UserRequestDto userRequestDto){
        try {
            UserModel userModel = mapper.map(userRequestDto, UserModel.class);
            userModel = repository.save(userModel);
            return mapper.map(userModel, UserResponseDto.class);
        } catch (Exception e){
            throw e;
        }
    }

    public UserModel update (UserResponseDto userResponseDto){
        return repository.save(mapper.map(userResponseDto, UserModel.class));
    }

    public void delete(Long id){
        UserModel validateId = findById(id);
        repository.deleteById(validateId.getId());
    }
}


