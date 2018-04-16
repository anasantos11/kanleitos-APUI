package br.com.kanleitos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanleitos.models.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

}
