package br.com.catalisa.ZuPlaceApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Schema(description = "Usuário associado ao espaço")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    @Schema(description = "Recurso associado ao espaço")
    private ResourceModel resource;

    @OneToOne
    @JoinColumn(name = "address_id")
    @Schema(description = "Endereço associado ao espaço")
    private AddressModel address;
}