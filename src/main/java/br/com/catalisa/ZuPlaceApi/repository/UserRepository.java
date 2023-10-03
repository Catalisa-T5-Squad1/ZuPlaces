package br.com.catalisa.ZuPlaceApi.repository;

import br.com.catalisa.ZuPlaceApi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
