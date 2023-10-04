package br.com.catalisa.ZuPlaceApi.service;

import br.com.catalisa.ZuPlaceApi.dto.ContactRequestDto;
import br.com.catalisa.ZuPlaceApi.dto.ContactResponseDto;
import br.com.catalisa.ZuPlaceApi.dto.ResourceRequestDto;
import br.com.catalisa.ZuPlaceApi.dto.ResourceResponseDto;
import br.com.catalisa.ZuPlaceApi.model.ContactModel;
import br.com.catalisa.ZuPlaceApi.model.ResourceModel;
import br.com.catalisa.ZuPlaceApi.repository.ContatcRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContatcService {
    @Autowired
    ContatcRepository contactRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<ContactResponseDto> findAll(){
        try {
            List<ContactModel> contactModelList = contactRepository.findAll();
            List<ContactResponseDto> contactResponseDtoList = contactModelList.stream()
                    .map(contatcModel -> modelMapper.map(contatcModel, ContactResponseDto.class))
                    .collect(Collectors.toList());
            return contactResponseDtoList;
        } catch (Exception e){
            throw e;
        }
    }

    public ContactResponseDto findById(Long id) {
        try {
            Optional<ContactModel> optionalContactModel = contactRepository.findById(id);

            if (optionalContactModel.isPresent()) {
                ContactModel contactModel = optionalContactModel.get();
                ContactResponseDto contactResponseDto = modelMapper.map(contactModel, ContactResponseDto.class);
                return contactResponseDto;
            } else {
                return null;
            }
        } catch (Exception e){
            throw e;
        }
    }

    public ContactResponseDto create(ContactRequestDto contactRequestDto){
        try {
            ContactModel contactModel = modelMapper.map(contactRequestDto, ContactModel.class);
            contactModel = contactRepository.save(contactModel);
            ContactResponseDto contactResponseDto = modelMapper.map(contactModel, ContactResponseDto.class);
            return contactResponseDto;
        } catch (Exception e){
            throw e;
        }
    }

    public ContactResponseDto update(Long id, ContactRequestDto updatedContactDto) {
        try {
            Optional<ContactModel> optionalContactModel = contactRepository.findById(id);

            if (optionalContactModel.isPresent()) {
                ContactModel contactModel = optionalContactModel.get();
                modelMapper.map(updatedContactDto, contactModel);

                contactModel = contactRepository.save(contactModel);

                ContactResponseDto contactResponseDto = modelMapper.map(contactModel, ContactResponseDto.class);
                return contactResponseDto;
            } else {
                return null; //retorna no futuro uma excpetion personalizada.
            }
        } catch (Exception e){
            throw e;
        }
    }

    public void delete(Long id){
        contactRepository.deleteById(id);
    }
}
