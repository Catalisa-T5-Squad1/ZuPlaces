package br.com.catalisa.ZuPlaceApi.model;

import br.com.catalisa.ZuPlaceApi.enums.PersonType;
import br.com.catalisa.ZuPlaceApi.validation.UserGroupSequenceProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@GroupSequenceProvider(UserGroupSequenceProvider.class)
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    @Email(message = "Email inválido")
    @NotEmpty(message = "Email não pode ser vazio")
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    private String phone;

    @CPF(message = "cpf inválido")
    @CNPJ(message = "CNPJ inválido")
    private String documentType;

    @OneToMany(mappedBy = "user")
    private List<SpaceModel> spaces;
}
