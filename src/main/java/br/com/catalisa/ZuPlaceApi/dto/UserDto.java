package br.com.catalisa.ZuPlaceApi.dto;

import br.com.catalisa.ZuPlaceApi.enums.PersonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private PersonType personType;
    private String phone;
    private String documentType;
    private String numberAdress;
}
