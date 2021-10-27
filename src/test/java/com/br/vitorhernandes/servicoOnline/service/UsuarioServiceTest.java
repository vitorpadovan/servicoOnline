package com.br.vitorhernandes.servicoOnline.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.vitorhernandes.servicoOnline.model.Usuario;
import com.br.vitorhernandes.servicoOnline.model.builder.UsuarioBuilder;
import com.br.vitorhernandes.servicoOnline.repo.JpaUsuario;
import com.br.vitorhernandes.servicoOnline.service.exception.DataIntegrityException;

@SpringBootTest
class UsuarioServiceTest {

	private List<Usuario> usuarios;

	private JpaUsuario repo;


	@BeforeEach
	private void construirUsuariosDuplicados() {
		usuarios = new ArrayList<Usuario>();
		usuarios.add(new UsuarioBuilder().comLogin("Vitor123").comSenha("123").build());
		usuarios.add(new UsuarioBuilder().comLogin("Vitor123").comSenha("123").build());
	}

	@AfterEach
	private void deletarUsuariosDoBanco() {
		if (usuarios.size() > 0)
			usuarios.stream().forEach((p) -> {
				try {
					repo.delete(p);
				} catch (Exception ex) {
				}
			});
	}


	private static Usuario u;

	@Autowired
	private UsuarioService s;


	@Test
	void naoPodeCadastrarDuplicidade() {
		s.salvarUsuario(usuarios.get(0));
		assertThrows(DataIntegrityException.class, () -> {
			s.salvarUsuario(usuarios.get(1));
		});
	}

	@Test
	void naoPodeCadastrarUsuarioSemLogin() {
		Usuario u = new UsuarioBuilder().comLogin("").comSenha("123").build();
		assertThrows(DataIntegrityException.class, () -> {
			s.salvarUsuario(u);
		});
	}

	@Test
	void naoPodeCadastrarUsuarioSemSenha() {
		Usuario u = new UsuarioBuilder().comLogin("Vitor").comSenha("").build();
		assertThrows(DataIntegrityException.class, () -> {
			s.salvarUsuario(u);
		});
	}
}
