package br.com.catalisa.ZuPlaceApi.service;

import br.com.catalisa.ZuPlaceApi.dto.UserDto;
import br.com.catalisa.ZuPlaceApi.model.UserModel;
import br.com.catalisa.ZuPlaceApi.repository.UserRepository;
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
public  UserModel create(UserDto userDto){
        return repository.save(mapper.map(userDto, UserModel.class));
}
public  UserModel update (UserDto userDto){
        return repository.save(mapper.map(userDto, UserModel.class));
}
public  void delete(Long id){
        UserModel validateId = findById(id);
        repository.deleteById(validateId.getId());
}
    }


