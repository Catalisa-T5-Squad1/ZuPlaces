package br.com.catalisa.ZuPlaceApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceResponseDto {

    private String name;

    private UserResponseDto user;

    private ResourceResponseDto resource;

    private AddressResponseDto address;

}
