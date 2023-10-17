package br.com.catalisa.ZuPlaceApi.dto;

import br.com.catalisa.ZuPlaceApi.model.AddressModel;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaceResponseProximityLocationDto {

    private String name;

    private UserResponseDto user;

    private ResourceResponseDto resource;

    private AddressModel address;

    private String description;

    private double distance;



}
