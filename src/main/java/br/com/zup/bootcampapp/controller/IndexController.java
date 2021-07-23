package br.com.zup.bootcampapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class IndexController {


    /**
     *  http://localhost:8080/api/v1
     */
    @GetMapping
    public String testeApi(){
        return "Bem vindo a API de controle de avaliação do Orange Talents 7";
    }


}
