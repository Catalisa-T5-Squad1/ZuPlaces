package br.com.catalisa.ZuPlaceApi.repository;

import br.com.catalisa.ZuPlaceApi.model.AddresModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddresRepository extends JpaRepository<AddresModel, Long> {
}
