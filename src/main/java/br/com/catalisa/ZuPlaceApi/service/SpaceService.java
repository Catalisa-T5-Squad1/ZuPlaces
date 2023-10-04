package br.com.catalisa.ZuPlaceApi.service;

import br.com.catalisa.ZuPlaceApi.dto.ResourceResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.SpaceResponseDto;
import br.com.catalisa.ZuPlaceApi.exception.SpaceNotFound;
import br.com.catalisa.ZuPlaceApi.model.ResourceModel;
import br.com.catalisa.ZuPlaceApi.model.SpaceModel;
import br.com.catalisa.ZuPlaceApi.repository.SpaceRepository;
import org.modelmapper.ModelMapper;
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

}
