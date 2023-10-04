package br.com.catalisa.ZuPlaceApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
private  Long id;
private  String name;
private  String socialReason;
private  String email;
private  String password;
private  String cpf;
private  String cnpj;
private  String phone;
private  String cep;
private  String numberAdress;
//...
}
