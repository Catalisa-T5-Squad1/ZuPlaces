package br.com.catalisa.ZuPlaceApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nome do Espaço", example = "Faculdade Estácio")
    private String name;

    @Schema(description = "ID do Usuário", example = "3")
    private UserModel user;

    @Schema(description = "ID do Recurso", example = "2")
    private ResourceModel resource;

    @Schema(description = "ID do Endereço", example = "2")
    private AddresModel addres;
}
