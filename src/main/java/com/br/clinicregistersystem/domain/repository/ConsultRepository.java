package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.Consult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {

    List<Consult> findByPersonId(Long personId);

}
