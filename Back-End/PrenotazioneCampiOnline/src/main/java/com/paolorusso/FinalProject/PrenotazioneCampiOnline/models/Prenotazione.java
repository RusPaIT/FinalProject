package com.paolorusso.FinalProject.PrenotazioneCampiOnline.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class Prenotazione {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 @ManyToOne
	 private Utente utente;
	 @OneToOne
	 private CampoDaGioco campoPrenotato;
	 private LocalDate dataInserimento = LocalDate.now();
	 private LocalDate dataPrenotazione;
	 private int Orario;
	 
	 
}
