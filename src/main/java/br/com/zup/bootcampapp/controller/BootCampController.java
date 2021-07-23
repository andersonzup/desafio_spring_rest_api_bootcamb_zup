package br.com.zup.bootcampapp.controller;

import br.com.zup.bootcampapp.dto.AlunoDto;
import br.com.zup.bootcampapp.dto.MessageResponseDto;
import br.com.zup.bootcampapp.exception.AlunoNotFoundException;
import br.com.zup.bootcampapp.model.Aluno;
import br.com.zup.bootcampapp.repository.AlunoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/bootcamp")
public class BootCampController {
    AlunoRepository alunoRepository;

    public BootCampController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    /*
     Método Post
     http://localhost:8080/api/v1/bootcamp
     */
    @PostMapping
    @CacheEvict(value = "listaDeAlunos", allEntries = true)
    public ResponseEntity<MessageResponseDto> cadastrarAluno(@RequestBody @Valid Aluno aluno){
        if (aluno.getIdade() < 18){
            return new ResponseEntity<>(createdMessageResponseError("Idade não permitida"), HttpStatus.BAD_REQUEST);
        } else {
            Aluno alunoCadastrado = alunoRepository.save(aluno);
            return new ResponseEntity<>(createdMessageResponse("Aluno cadastrado com id ", alunoCadastrado.getId()),
                    HttpStatus.OK);
        }
    }
    /*
        Método Get
        http://localhost:8080/api/v1/bootcamp/1
        */
    @GetMapping("/{id}")
    public AlunoDto detalhesAluno(@PathVariable Long id) throws AlunoNotFoundException {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new AlunoNotFoundException(id));
        AlunoDto alunoDto = new AlunoDto(aluno);
        return alunoDto;
    }
    /*
    Método Get
    http://localhost:8080/api/v1/bootcamp/listaDeAlunos
    */
    @GetMapping("/listaDeAlunos")
    @Cacheable(value = "listaDeAlunos")
    public Page<AlunoDto> listarAlunos(Pageable paginacao){
        Page<Aluno> alunos = alunoRepository.findAll(paginacao);
        return AlunoDto.convertToDto(alunos);
    }

    private MessageResponseDto createdMessageResponse(String s, Long id) {
        MessageResponseDto messageResponse = new MessageResponseDto(s + id);
        return messageResponse;
    }

    private MessageResponseDto createdMessageResponseError(String s) {
        MessageResponseDto messageResponse = new MessageResponseDto(s);
        return messageResponse;
    }


}
