package br.com.catalisa.ZuPlaceApi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    private String description;

    private double distance;


}