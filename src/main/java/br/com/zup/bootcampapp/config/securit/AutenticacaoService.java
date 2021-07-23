package br.com.zup.bootcampapp.config.securit;

import java.util.Optional;

import br.com.zup.bootcampapp.model.AlunoLoguin;
import br.com.zup.bootcampapp.repository.AlunoLoguinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private AlunoLoguinRepository LoguinRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AlunoLoguin> aluno = LoguinRepository.findByEmail(email);
        System.out.println(aluno);
        if (aluno.isPresent()) {
            return aluno.get();
        }

        throw new UsernameNotFoundException("Dados inválidos!");
    }

}