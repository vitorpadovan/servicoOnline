package com.br.vitorhernandes.servicoOnline.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.vitorhernandes.servicoOnline.model.Usuario;

@Repository
public interface JpaUsuario extends JpaRepository<Usuario, Integer> {

	public Optional<Usuario> findByLogin(String login);
}
