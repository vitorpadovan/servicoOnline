package com.br.vitorhernandes.servicoOnline.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.br.vitorhernandes.servicoOnline.form.FormLogin;
import com.br.vitorhernandes.servicoOnline.model.Usuario;
import com.br.vitorhernandes.servicoOnline.repo.JpaUsuario;
import com.br.vitorhernandes.servicoOnline.service.exception.BaseException;
import com.br.vitorhernandes.servicoOnline.service.exception.DataIntegrityException;

@Component
public class UsuarioService implements UserDetailsService {

	@Autowired
	JpaUsuario repoUsuario;

	@Autowired
	BCryptPasswordEncoder bCrypt;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optUsuariou = repoUsuario.findByLogin(username);
		System.out.println("Pesquisando o login");
		if (!optUsuariou.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		return optUsuariou.get();
	}

	public Usuario salvarUsuario(FormLogin f) throws BaseException {
		return this.salvarUsuario(f.getUsuario());
	}

	public Usuario salvarUsuario(Usuario u) throws BaseException {
		if (u.getSenha().trim().length() == 0) {
			throw new DataIntegrityException("Impossível salvar usuário s/ senha");
		}
		if (u.getLogin().trim().length() == 0) {
			throw new DataIntegrityException("Impossível salvar usuário s/ login");
		}
		Optional<Usuario> optU = repoUsuario.findByLogin(u.getSenha());
		if (optU.isPresent() || !optU.isEmpty()) {
			throw new DataIntegrityException("Usuário já existe");
		}
		try {
			u.setSenha(bCrypt.encode(u.getSenha()));
			repoUsuario.save(u);
		} catch (Exception ex) {
			throw new DataIntegrityException("Impossível de salvar o usuário: " + u.getLogin(), ex);
		}
		return u;
	}
}