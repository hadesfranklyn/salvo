package com.github.hadesfranklyn.cambioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.hadesfranklyn.cambioservice.model.Cambio;

@Repository
public interface CambioRepository extends JpaRepository<Cambio, Long>{

	Cambio findByFromAndTo(String from, String to);
}
