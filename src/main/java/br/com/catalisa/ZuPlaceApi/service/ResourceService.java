package br.com.catalisa.ZuPlaceApi.service;

import br.com.catalisa.ZuPlaceApi.dto.ResourceRequestDto;
import br.com.catalisa.ZuPlaceApi.dto.ResourceResponseDto;
import br.com.catalisa.ZuPlaceApi.model.ResourceModel;
import br.com.catalisa.ZuPlaceApi.repository.ResourceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<ResourceResponseDto> findAll(){
        try {
            List<ResourceModel> resourceModelList = resourceRepository.findAll();
            List<ResourceResponseDto> resourceResponseDtoList = resourceModelList.stream()
                    .map(resourceModel -> modelMapper.map(resourceModel, ResourceResponseDto.class))
                    .collect(Collectors.toList());
            return resourceResponseDtoList;
        } catch (Exception e){
            throw e;
        }
    }

    public ResourceResponseDto findById(Long id) {
        try {
            Optional<ResourceModel> optionalResourceModel = resourceRepository.findById(id);

            if (optionalResourceModel.isPresent()) {
                ResourceModel resourceModel = optionalResourceModel.get();
                ResourceResponseDto resourceResponseDto = modelMapper.map(resourceModel, ResourceResponseDto.class);
                return resourceResponseDto;
            } else {
                return null;
            }
        } catch (Exception e){
            throw e;
        }
    }

    public ResourceResponseDto create(ResourceRequestDto resourceRequestDto){
       try {
           ResourceModel resourceModel = modelMapper.map(resourceRequestDto, ResourceModel.class);
           resourceModel = resourceRepository.save(resourceModel);
           ResourceResponseDto resourceResponseDto = modelMapper.map(resourceModel, ResourceResponseDto.class);
           return resourceResponseDto;
       } catch (Exception e){
           throw e;
       }
    }

    public ResourceResponseDto update(Long id, ResourceRequestDto updatedResourceDto) {
        try {
            Optional<ResourceModel> optionalResourceModel = resourceRepository.findById(id);

            if (optionalResourceModel.isPresent()) {
                ResourceModel resourceModel = optionalResourceModel.get();
                modelMapper.map(updatedResourceDto, resourceModel);

                resourceModel = resourceRepository.save(resourceModel);

                ResourceResponseDto resourceResponseDto = modelMapper.map(resourceModel, ResourceResponseDto.class);
                return resourceResponseDto;
            } else {
                return null; //retorna no futuro uma excpetion personalizada.
            }
        } catch (Exception e){
            throw e;
        }
    }

    public void delete(Long id){
        resourceRepository.deleteById(id);
    }
}
