package com.br.vitorhernandes.servicoOnline.model;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sun.istack.NotNull;

@Entity
public class Usuario implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;

	@NotNull
	@Column(length = 50, nullable = false, unique = true)
	private String login;

	@Column(length = 500, nullable = false)
	private String senha;

	@Column(columnDefinition = "date", nullable = false)
	private Date dataValidade;

	@Column(columnDefinition = "boolean default true", nullable = false)
	private Boolean ativo = true;

	@ManyToMany
	@JoinTable(
			name = "JTUsuarioPerfil", joinColumns = @JoinColumn(name = "idUsuario"), inverseJoinColumns = @JoinColumn(
					name = "idPeril"
			)
	)
	private Set<PerfilUsuario> perfils;


	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

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

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Set<PerfilUsuario> getPerfils() {
		return perfils;
	}

	public void setPerfils(Set<PerfilUsuario> perfils) {
		this.perfils = perfils;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.ativo;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.ativo;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.ativo;
	}

	@Override
	public boolean isEnabled() {
		return this.ativo;
	}
}
