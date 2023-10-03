package br.com.catalisa.ZuPlaceApi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Schema(name = "Endereço")
public class AddresModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "CEP", example = "87000-00")
    private String cep;

    @Schema(description = "Rua/Av", example = "Rua Juruá")
    private String logradouro;

    @Schema(description = "Bairro", example = "Jardim Antunes")
    private String bairro;

    @Schema(description = "Cidade", example = "Maringá")
    private String localidade;

    @Schema(description = "Estado", example = "PR")
    private String uf;
}
