package br.com.catalisa.ZuPlaceApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestDto {
    @Schema(description = "Nome", example = "Ricardo")
    private String name;

    @Schema(description = "E-mail", example = "usuario@zup.com.br")
    private String email;

    @Schema(description = "Telefone", example = "(11) 9 8888-7777")
    private String phone;
}
