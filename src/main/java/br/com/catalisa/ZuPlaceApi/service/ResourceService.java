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
        Optional<ResourceModel> optionalResourceModel = resourceRepository.findById(id);

        if (optionalResourceModel.isPresent()) {
            ResourceModel resourceModel = optionalResourceModel.get();
            ResourceResponseDto resourceResponseDto = modelMapper.map(resourceModel, ResourceResponseDto.class);
            return resourceResponseDto;
        } else {
            return null;
        }
    }


    public ResourceResponseDto register(ResourceRequestDto resourceRequestDto){
       ResourceModel resourceModel = modelMapper.map(resourceRequestDto, ResourceModel.class);
       resourceModel = resourceRepository.save(resourceModel);
       ResourceResponseDto resourceResponseDto = modelMapper.map(resourceModel, ResourceResponseDto.class);
       return resourceResponseDto;
    }

    public ResourceResponseDto update(Long id, ResourceRequestDto updatedResourceDto) {
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
    }

    public void delete(Long id){
        resourceRepository.deleteById(id);
    }
}
