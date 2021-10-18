package com.br.vitorhernandes.servicoOnline.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.br.vitorhernandes.servicoOnline.JpaUsuario;
import com.br.vitorhernandes.servicoOnline.model.Usuario;

@Component
public class UsuarioService implements UserDetailsService {

	@Autowired
	JpaUsuario repoUsuario;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optUsuariou = repoUsuario.findByLogin(username);
		System.out.println("Pesquisando o login");
		if (!optUsuariou.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		return optUsuariou.get();
	}
}