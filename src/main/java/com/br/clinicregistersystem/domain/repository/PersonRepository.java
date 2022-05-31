package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByDocumentCpf(Long documentCpf);

}
