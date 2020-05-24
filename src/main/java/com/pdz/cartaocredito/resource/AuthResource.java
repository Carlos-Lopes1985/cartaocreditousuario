package com.pdz.cartaocredito.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pdz.cartaocredito.response.Response;
import com.pdz.cartaocredito.security.JWTUtil;
import com.pdz.cartaocredito.security.UserSS;
import com.pdz.cartaocredito.security.dto.JwtAuthenticationDto;
import com.pdz.cartaocredito.security.dto.TokenDto;
import com.pdz.cartaocredito.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private static final Logger log = LoggerFactory.getLogger(AuthResource.class);
	
//	@Autowired
//	private AuthService service;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Gera e retorna um novo token JWT.
	 * 
	 * @param authenticationDto
	 * @param result
	 * @return ResponseEntity<Response<TokenDto>>
	 * @throws AuthenticationException
	 */
	@PostMapping
	public ResponseEntity<Response<TokenDto>> gerarTokenJwt(
			@Valid @RequestBody JwtAuthenticationDto authenticationDto, BindingResult result)
			throws AuthenticationException {
		Response<TokenDto> response = new Response<TokenDto>();

		if (result.hasErrors()) {
			log.error("Erro validando lançamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		log.info("Gerando token para o email {}.", authenticationDto.getEmail());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationDto.getEmail(), authenticationDto.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getEmail());
		String token = jwtUtil.generateToken(userDetails.getPassword());
		response.setData(new TokenDto(token));

		return ResponseEntity.ok(response);
	}
//	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
//	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
//		service.sendNewPassword(objDto.getEmail());
//		return ResponseEntity.noContent().build();
//	}
}
