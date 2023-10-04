package br.com.catalisa.ZuPlaceApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceRequestDto {
    @Schema(description = "Nome", example = "Internet")
    private String name;
}
