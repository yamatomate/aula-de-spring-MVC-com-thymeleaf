package br.sei_la.com.aprendendo_spring.repository;

import br.sei_la.com.aprendendo_spring.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
    
} 