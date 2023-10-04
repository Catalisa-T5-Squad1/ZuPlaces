package br.com.catalisa.ZuPlaceApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ZipCodeRequestDto {
    @Schema(description = "CEP", example = "87000-000")
    private String cep;
}
