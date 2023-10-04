package br.com.catalisa.ZuPlaceApi.repository;

import br.com.catalisa.ZuPlaceApi.model.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatcRepository extends JpaRepository<ContactModel, Long> {
}
