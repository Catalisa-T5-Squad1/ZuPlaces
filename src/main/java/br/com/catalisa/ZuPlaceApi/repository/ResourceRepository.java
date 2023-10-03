package br.com.catalisa.ZuPlaceApi.repository;

import br.com.catalisa.ZuPlaceApi.model.ResourceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceModel, Long> {
}
