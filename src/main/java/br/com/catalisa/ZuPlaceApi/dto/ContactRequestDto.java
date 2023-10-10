package br.com.catalisa.ZuPlaceApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestDto {
    @Schema(description = "Nome completo", example = "Ricardo dos Santos")
    private String name;

    @Schema(description = "E-mail", example = "usuario@zup.com.br")
    private String email;

    @Schema(description = "Telefone", example = "11988887777")
    private String phone;

    @Schema(description = "Usuário", example = "USP")
    private UserRequestDto userRequestDto;

    @Schema(description = "Espaço", example = "Biblioteca - Bloco A")
    private SpaceRequestDto spaceRequestDto;
}
