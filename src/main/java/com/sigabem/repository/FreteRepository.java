package com.sigabem.repository;

import com.sigabem.model.Frete;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreteRepository extends CrudRepository<Frete, Long> {
}
