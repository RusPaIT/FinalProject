package com.paolorusso.FinalProject.PrenotazioneCampiOnline.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Utente;


@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM utenti WHERE username = :u")
	Optional<Utente> findUserByUsername(@Param("u") String u);
	
	@Query(nativeQuery = true, value = "SELECT * FROM utenti WHERE email = :e")
	Optional<Utente> findUserByEmail(@Param("e") String e);
	
	Boolean existsByUsername(String u);

	Boolean existsByEmail(String e);

}
