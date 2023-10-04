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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    private String phone;

    @Column(nullable = false)
    private String documentType;

    @Column(nullable = false, unique = true)
    private  String numberAdress;

    @OneToMany(mappedBy = "user")
    private List<SpaceModel> spaces;
}
