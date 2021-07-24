package br.com.zup.bootcampapp.repository;


import br.com.zup.bootcampapp.model.AlunoLoguin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AlunoLoguinRepositoryTest{

    @Autowired
    private AlunoLoguinRepository alunoLoguinRepository;


    @Test
    public void findByEmail(){// Deve devolver um aluno pelo email
        String email = "admin@admin.com";
        AlunoLoguin alunoLoguin = alunoLoguinRepository.findByEmail(email);
        Assertions.assertNotNull(alunoLoguin);
        Assertions.assertEquals(email, alunoLoguin.getEmail());
    }
    @Test
    public void notFindByEmail(){// Deve devolver um aluno pelo email
        String email = "adm@admin.com";
        AlunoLoguin alunoLoguin = alunoLoguinRepository.findByEmail(email);
        Assertions.assertNull(alunoLoguin);
    }


}
