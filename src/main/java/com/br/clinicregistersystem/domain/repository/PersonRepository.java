package com.br.clinicregistersystem.domain.repository;

import com.br.clinicregistersystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Boolean existsByPersonEmail(String personEmail);

    Boolean existsByPersonDocumentCpf(String personDocumentCpf);

}
