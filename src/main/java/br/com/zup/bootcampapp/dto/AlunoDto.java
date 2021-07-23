package br.com.zup.bootcampapp.dto;

import br.com.zup.bootcampapp.model.Aluno;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class AlunoDto {

    private String nome;

    private String email;


    public AlunoDto(Aluno aluno) {
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }


    public static Page<AlunoDto> convertToDto(Page<Aluno> alunos){
        return alunos.map(AlunoDto::new);
    }

}
