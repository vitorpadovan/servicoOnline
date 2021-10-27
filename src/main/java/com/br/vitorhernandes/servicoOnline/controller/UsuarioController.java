package com.br.vitorhernandes.servicoOnline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.vitorhernandes.servicoOnline.form.FormLogin;
import com.br.vitorhernandes.servicoOnline.model.Usuario;
import com.br.vitorhernandes.servicoOnline.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService servico;


	@PostMapping
	// TODO adicionar o @valid
	public ResponseEntity<Usuario> salvarUsuario(@RequestBody FormLogin login) {
		// TODO verificar o ResponseEntity.created(uri).build();
		// REF
		// https://github.com/acenelio/spring-boot-ionic-backend/blob/master/src/main/java/com/nelioalves/cursomc/resources/CategoriaResource.java
		return ResponseEntity.ok().body(servico.salvarUsuario(login));
	}
}
