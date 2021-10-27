package com.br.vitorhernandes.servicoOnline.form;

import java.util.Calendar;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.br.vitorhernandes.servicoOnline.model.Usuario;

public class FormLogin {

	private String login;

	private String senha;


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		Usuario u = new Usuario();
		u.setLogin(this.login);
		u.setSenha(this.senha);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 36);
		u.setDataValidade(c.getTime());
		return u;
	}

	public Usuario getUsuario(BCryptPasswordEncoder encoder) {
		Usuario u = new Usuario();
		u.setLogin(this.login);
		u.setSenha(encoder.encode(this.senha));
		return u;
	}

	@Override
	public String toString() {
		return "FormLogin [login=" + login + ", senha=" + senha + "]";
	}
}
