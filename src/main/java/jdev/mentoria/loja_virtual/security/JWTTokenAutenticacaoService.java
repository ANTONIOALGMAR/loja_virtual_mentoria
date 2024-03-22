package jdev.mentoria.loja_virtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


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
		
		
		/* USADO PARA VER NO POSTMAN PARA TEST */
		response.getWriter().write("{\"Authorization\":\""+ token +"\"}");
	
	}

}
