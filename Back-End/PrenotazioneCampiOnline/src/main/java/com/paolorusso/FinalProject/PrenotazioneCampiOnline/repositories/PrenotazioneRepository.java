package com.paolorusso.FinalProject.PrenotazioneCampiOnline.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer>{
	
	@Query(
            nativeQuery = true,
            value = "SELECT * FROM prenotazioni WHERE campo_prenotato_id = :campoDaPrenotare AND data_prenotazione = :dataPrenotazione AND orario = :orario"
        )
    List<Prenotazione> findByCampoPrenotatoAndDataPrenotazione(int campoDaPrenotare, LocalDate dataPrenotazione, int orario);

	@Query(
            nativeQuery = true,
            value = "SELECT * FROM prenotazioni  WHERE utente_id = :userId"
           )
	List<Prenotazione> findByUserId(@Param("userId") int userId);
}
