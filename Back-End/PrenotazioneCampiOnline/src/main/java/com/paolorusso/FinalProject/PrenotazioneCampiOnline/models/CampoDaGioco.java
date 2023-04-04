package com.paolorusso.FinalProject.PrenotazioneCampiOnline.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "campi_da_gioco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class CampoDaGioco {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @Column(name="città")
    private String citta;
    private String indirizzo;
    @Column(name="numero_di_telefono")
    private String numeroTelefono;
    @Enumerated(EnumType.STRING)
    @Column(name="tipo_di_campo")
    private TipoCampo tipoCampo;
    
}
