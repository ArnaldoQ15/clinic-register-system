package com.br.clinicregister.domain.repository;

import com.br.clinicregister.model.Consults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultsRepository extends JpaRepository<Consults, Long> {
}
