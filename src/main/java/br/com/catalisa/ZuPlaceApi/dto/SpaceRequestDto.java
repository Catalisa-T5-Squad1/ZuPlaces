package br.com.catalisa.ZuPlaceApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceRequestDto {

    private String name;

    private UserDto user;

    private ResourceRequestDto resource;

    private ZipCodeRequestDto address;
}
