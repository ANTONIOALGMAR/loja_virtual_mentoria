package jdev.mentoria.loja_virtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdev.mentoria.loja_virtual.ApplicationContextLoad;
import jdev.mentoria.loja_virtual.model.Usuario;
import jdev.mentoria.loja_virtual.repository.UsuarioRepository;


/* CRIAR AUTENTICACAO E RETORNAR TAMBEM A AUTENTICACAO JWT*/
@Service
@Component
public class JWTTokenAutenticacaoService {
	
	/* TOKEN DE VALIDADE DE 11 DIAS */
	private static final Long EXPIRATION_TIME = (long) 959990000;
	
	/* CHAVE DE SENHA PARA JUNTAR COM JWT */
	private static final String  SECRET = "ss/-*-*sds565dsd-s/d-s*dsds";
	
	private static final String TOKEN_PREFIX = "Bearer"; 
	
	private static final String HEADER_STRING = "Authorization";
	
	/* GERA O TOKEN E DA A RESPOSTA PARA CLIENTE COM JWT*/
	public void addAuthentication(HttpServletResponse response, String username) throws Exception{
		
		/* MONTAGEM DO TOKEN*/
		
		String JWT = Jwts.builder(). /* CHAMA O GERADOR DE TOKEN*/
				setSubject(username) /* ADICIONA O USER*/
				.setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME)) /* TEMPO DE EXPIRACAO*/
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		String token = TOKEN_PREFIX + " " + JWT;
		
		
		/* DA A RESPOSTA PARA A TELA E PARA O CLIENTE, OUTRA API, NAVEGADOR, APLICATIVO, JAVASCRIPT, OUTRA CHAMDA JAVA*/
		response.addHeader(HEADER_STRING, token);
		
		
		liberacaoCors(response);
		
		
		/* USADO PARA VER NO POSTMAN PARA TEST */
		response.getWriter().write("{\"Authorization\":\""+ token +"\"}");
	
	}
	
	
	/* RETORNA O USUARIO VALIDADO COM TOKEN OU CASO NAO SEJA VALIDO RETORNA NULL */
	
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		String token = request.getHeader(HEADER_STRING);
		
		if(token != null){
			
			String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
			
			/* FAZ A VALIDACAO DO TOKEN DO USUARIO NA REQUISICAO E OBTEM O USER */
			String user = Jwts.parser().
					setSigningKey(SECRET)
					.parseClaimsJws(tokenLimpo)
					.getBody().getSubject();  /*ADMIN OU ALEX*/
			
		if (user != null) {
			
			Usuario usuario = ApplicationContextLoad.
					getApplicationContext().
					getBean(UsuarioRepository.class).FindUserByLogin(user);
			
			if (usuario != null) {
				
				return new UsernamePasswordAuthenticationToken(
						usuario.getLogin(), 
						usuario.getSenha(), 
						usuario.getAuthorities());
				
			}
			
		}
			
		}
		
		liberacaoCors(response);
		return null;
	}
	
	/* FAZENDO LIBERACAO CONTRA ERRO DE CORS NO NAVEGADOR */
	private void liberacaoCors(HttpServletResponse response) {
		
		if(response.getHeader("Acess-Control-Allow-Origin")== null) {
			response.addHeader("Acess-Control-Allow-Origin", "*");
		}
		
		if(response.getHeader("Acess-Control-Allow-Headers")== null) {
			response.addHeader("Acess-Control-Allow-Headers", "*");
		}
		
		if(response.getHeader("Acess-Control-Request-Headers")== null) {
			response.addHeader("Acess-Control-Request-Headers", "*");
		}
		
		if(response.getHeader("Acess-Control-Allow-Methods")== null) {
			response.addHeader("Acess-Control-Allow-Methods", "*");
		}
	}

}
