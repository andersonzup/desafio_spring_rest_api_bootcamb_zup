package br.com.zup.bootcampapp.repository;

import br.com.zup.bootcampapp.model.AlunoLoguin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoLoguinRepository extends JpaRepository<AlunoLoguin, Long> {

    Optional<AlunoLoguin> findByEmail(String email);
}
