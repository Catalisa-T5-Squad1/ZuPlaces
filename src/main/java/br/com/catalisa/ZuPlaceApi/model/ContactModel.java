package br.com.catalisa.ZuPlaceApi.model;
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
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Contato")
public class ContactModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nome compelto", example = "Ricardo dos Santos")
    private String name;

    @Schema(description = "E-mail", example = "usuario@zup.com.br")
    private String email;

    @Schema(description = "Telefone", example = "11988887777")
    private String phone;

    @Schema(description = "Descrição", example = "Gostaria de indicar esse espaço na empresa XPTO")
    private String description;

    @Schema(description = "Usuário", example = "USP")
    private UserModel userModel;

    @Schema(description = "Espaço", example = "Biblioteca - Bloco A")
    private SpaceModel spaceModel;
}
