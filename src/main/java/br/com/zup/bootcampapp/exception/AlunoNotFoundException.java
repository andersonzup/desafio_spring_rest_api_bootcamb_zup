package br.com.zup.bootcampapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlunoNotFoundException extends Exception {
    public AlunoNotFoundException(Long id){
        super("Aluno com id: " + id + " não encontrado.");
    }
}
