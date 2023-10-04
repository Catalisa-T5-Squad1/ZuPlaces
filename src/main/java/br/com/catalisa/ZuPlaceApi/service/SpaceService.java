package br.com.catalisa.ZuPlaceApi.service;

import br.com.catalisa.ZuPlaceApi.dto.ResourceResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.SpaceRequestDto;
import br.com.catalisa.ZuPlaceApi.dto.SpaceResponseDto;
import br.com.catalisa.ZuPlaceApi.exception.ResourceNotFoud;
import br.com.catalisa.ZuPlaceApi.exception.SpaceNotFound;
import br.com.catalisa.ZuPlaceApi.exception.UserNotFoud;
import br.com.catalisa.ZuPlaceApi.model.ResourceModel;
import br.com.catalisa.ZuPlaceApi.model.SpaceModel;
import br.com.catalisa.ZuPlaceApi.model.UserModel;
import br.com.catalisa.ZuPlaceApi.repository.ResourceRepository;
import br.com.catalisa.ZuPlaceApi.repository.SpaceRepository;
import br.com.catalisa.ZuPlaceApi.repository.UserRepository;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceService {

    private static final Logger logger = LoggerFactory.getLogger(SpaceService.class);

    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<SpaceResponseDto> findAll(){
        try {
            logger.debug("Listando todos os espaços.");

            List<SpaceModel> spaceModelList = spaceRepository.findAll();
            List<SpaceResponseDto> spaceResponseDto = spaceModelList.stream()
                    .map(spaceModel -> modelMapper.map(spaceModel, SpaceResponseDto.class))
                    .collect(Collectors.toList());

            logger.info("Espaços listados com sucesso.");
            return spaceResponseDto;
        } catch (SpaceNotFound e) {
            logger.error("Erro ao listar os espaços, não existe espaços cadastrados.");
            throw e;
        } catch (Exception e){
            logger.error("Erro ao listar espaços.", e);
            throw e;
        }
    }

    public SpaceResponseDto findById(Long id) {
        try {
            logger.debug("Buscando espaço pelo ID: {}", id);

            return spaceRepository.findById(id)
                    .map(spaceModel -> modelMapper.map(spaceModel, SpaceResponseDto.class))
                    .orElseThrow(() -> {
                        logger.error("Espaço com ID {} não encontrado.", id);
                        return new SpaceNotFound(id);
                    });
        } catch (SpaceNotFound e) {
            logger.error("Espaço com ID {} não encontrado.", id);
            throw e;
        } catch (Exception e) {
            logger.error("Erro ao buscar Espaço pelo ID: {}", id, e);
            throw e;
        }
    }

    public SpaceResponseDto create(SpaceRequestDto spaceRequestDto) {
        try {
            logger.debug("Criando um novo espaço");

            UserModel userModel = userRepository.findById(spaceRequestDto.getUserId())
                    .orElseThrow(() -> new UserNotFoud(spaceRequestDto.getUserId()));

            ResourceModel resourceModel = resourceRepository.findById(spaceRequestDto.getResourceId())
                    .orElseThrow(() -> new ResourceNotFoud(spaceRequestDto.getResourceId()));

            SpaceModel spaceModel = modelMapper.map(spaceRequestDto, SpaceModel.class);

            spaceModel.setUser(userModel);
            spaceModel.setResource(resourceModel);

            spaceRepository.save(spaceModel);

            logger.debug("Espaço criado com sucesso. ID: {}", spaceModel.getId());

            SpaceResponseDto spaceResponseDto = modelMapper.map(spaceModel, SpaceResponseDto.class);
            logger.debug("Espaço model foi convertido para SpaceResponseDto");

            return spaceResponseDto;
        } catch (Exception e) {
            logger.error("Erro ao criar espaço.", e);
            throw e;
        }
    }

    public void delete(Long id){
        try{
            logger.debug("Excluindo espaço pelo ID: {}", id);
            spaceRepository.deleteById(id);
            logger.debug("Espaço excluido com sucesso. ID: {}", id);
        } catch (SpaceNotFound e){
            logger.error("Erro ao excluir o espaço pelo ID. {}", id, e);
            throw e;
        }
    }

}
