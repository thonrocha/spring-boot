package br.com.alura.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private String expiration;

	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date expiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		return Jwts.builder().setIssuer("API Forum").setSubject(logado.getId().toString()).setIssuedAt(hoje)
				.setExpiration(expiracao).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
