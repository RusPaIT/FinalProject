package com.paolorusso.FinalProject.PrenotazioneCampiOnline.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Ruolo;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.TipoRuolo;


@Repository
public interface RuoloRepository extends JpaRepository<Ruolo, Integer> {
	
	Optional<Ruolo> findByName(TipoRuolo r);
}