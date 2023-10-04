package br.com.catalisa.ZuPlaceApi.model;

import br.com.catalisa.ZuPlaceApi.enums.PersonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    private String phone;

    private String documentType;

    @OneToMany(mappedBy = "user")
    private List<SpaceModel> spaces;
}
