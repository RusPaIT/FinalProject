package com.paolorusso.FinalProject.PrenotazioneCampiOnline.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "utenti", uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
@Builder
public class Utente {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Il campo nome non può essere vuoto")
    private String nome;
    @NotBlank(message = "Il campo cognome non può essere vuoto")
    private String cognome;
    @NotBlank(message = "Il campo username non può essere vuoto")
    private String username;
    @NotBlank(message = "Il campo email non può essere vuoto")
    @Email
    private String email;
    @NotBlank(message = "Il campo password non può essere vuoto")
    private String password;
    @Column(name="città")
    @NotBlank(message = "Il campo città non può essere vuoto")
    private String citta;
	@ManyToMany
	@JoinTable(name = "ruoli_utenti", joinColumns = @JoinColumn(name = "utente_id"), inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
	private Set<Ruolo> ruoli = new HashSet<>();	
}



