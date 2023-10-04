package br.com.catalisa.ZuPlaceApi.model;

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
    @Column
    private String socialReason;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String password;
    @Column(nullable = false, unique = true)
    private  String cpf;
    @Column(nullable = false, unique = true)
    private  String cnpj;
    private String phone;
    @Column(nullable = false, unique = true)
    private  String cep;
    @Column(nullable = false, unique = true)
    private  String numberAdress;



    @OneToMany(mappedBy = "user")
    private List<SpaceModel> spaces;
}
