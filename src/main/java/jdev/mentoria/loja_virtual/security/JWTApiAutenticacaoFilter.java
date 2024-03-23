package jdev.mentoria.loja_virtual.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTApiAutenticacaoFilter extends GenericFilterBean {

	/* FILTRO ONDE TODAS AS REQUISICOES SERAO CAPTURADAS PARA AUTENTICAR*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		/* ESTABELECER A AUTENTICACAO DO USUARIO */
		Authentication autentication = new JWTTokenAutenticacaoService().
				getAuthentication((HttpServletRequest)request, (HttpServletResponse)response);
		
		
		/* COLOCA O PROCESSO DE AUTENTICACAO PARA O SPRING SECURITY */
		SecurityContextHolder.getContext().setAuthentication(autentication);
		
		chain.doFilter(request, response);
		
	}

}
