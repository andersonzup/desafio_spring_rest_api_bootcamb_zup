package br.com.zup.bootcampapp.config.securit;

import br.com.zup.bootcampapp.model.AlunoLoguin;
import br.com.zup.bootcampapp.repository.AlunoLoguinRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private AlunoLoguinRepository repository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, AlunoLoguinRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(httpServletRequest);
        boolean valido = tokenService.isTokenValido(token);
        if (valido) {
            autenticarAluno(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticarAluno(String token) {
        Long idAluno = tokenService.getIdAluno(token);
        AlunoLoguin aluno = repository.findById(idAluno).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(aluno, null, aluno.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
