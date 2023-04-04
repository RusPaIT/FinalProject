package com.paolorusso.FinalProject.PrenotazioneCampiOnline.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.CampoDaGioco;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.TipoCampo;

@Repository
public interface CampoDaGiocoRepository extends JpaRepository<CampoDaGioco, Integer>{
	
	@Query(
            nativeQuery = true,
            value = "SELECT * FROM campi_da_gioco WHERE città = (SELECT città FROM utenti WHERE id = :utente_id)"
        )
        List<CampoDaGioco> filtraInBaseAllaCitta(@Param("utente_id") int utente_id);
	
	@Query(
            nativeQuery = true,
            value = "SELECT * FROM campi_da_gioco WHERE città = :t"
            )
		List<CampoDaGioco> filtraByCitta(@Param("t") String t);
	
	@Query(
            nativeQuery = true,
            value = "SELECT * FROM campi_da_gioco WHERE tipo_di_campo LIKE %:t%"
            )
		List<CampoDaGioco> filtraByTipoCampo(@Param("t") String t);
	
	@Query(
            nativeQuery = true,
            value = "SELECT * FROM campi_da_gioco WHERE nome LIKE %:t%"
            )
		List<CampoDaGioco> filtraByNomeCampo(@Param("t") String t);
	
	List<CampoDaGioco> findByCitta(String citta);

}
