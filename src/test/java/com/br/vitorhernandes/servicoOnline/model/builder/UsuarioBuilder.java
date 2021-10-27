package com.br.vitorhernandes.servicoOnline.model.builder;

import java.util.Calendar;

import com.br.vitorhernandes.servicoOnline.model.Usuario;

public class UsuarioBuilder {

	private String login;

	private String senha;


	public UsuarioBuilder comLogin(String login) {
		this.login = login;
		return this;
	}

	public UsuarioBuilder comSenha(String senha) {
		this.senha = senha;
		return this;
	}

	public Usuario build() {
		Usuario r = new Usuario();
		r.setLogin(login);
		r.setSenha(senha);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 36);
		r.setDataValidade(c.getTime());
		return r;
	}
}
