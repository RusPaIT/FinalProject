package com.paolorusso.FinalProject.PrenotazioneCampiOnline;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.configurations.Beans;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.CampoDaGioco;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Ruolo;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.TipoCampo;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.TipoRuolo;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.models.Utente;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.services.CampoDaGiocoService;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.services.RuoloService;
import com.paolorusso.FinalProject.PrenotazioneCampiOnline.services.UtenteService;

@SpringBootApplication
public class PrenotazioneCampiOnlineApplication implements CommandLineRunner {
	
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
	
	
	@Autowired
	private UtenteService utServ;
	@Autowired
	private CampoDaGiocoService cgServ;
	@Autowired
	private RuoloService rlServ;
	@Autowired
	private PasswordEncoder pswE;

	public static void main(String[] args) {
		SpringApplication.run(PrenotazioneCampiOnlineApplication.class, args);
	}

	

	@Override
	public void run(String... args) throws Exception {

		//popolaDB();
		}

	
	private void popolaDB () {
	
		Utente u1 = (Utente) ctx.getBean("utente", "Gino", "Jino", "Jinetto", "jjjino@gmail.com", pswE.encode("jino123"), "Roma");
		Utente u2 = (Utente) ctx.getBean("utente", "Paolo", "Russo", "RusPa", "paolo@gmail.com", pswE.encode("paolo123"), "Reggio Calabria");
		Utente u3 = (Utente) ctx.getBean("utente", "Paolo", "Bonolis", "Paolino", "p.bonolis@gmail.com", pswE.encode("bono123"), "Milano");
		Utente u4 = (Utente) ctx.getBean("utente", "Diego", "Maratona", "LaManoDeDios", "d.maratona@gmail.com", pswE.encode("diego123"), "Napoli");
		Ruolo r1 = (Ruolo) ctx.getBean("ruolo", TipoRuolo.UTENTE);
		Ruolo r2 = (Ruolo) ctx.getBean("ruolo", TipoRuolo.AMMINISTRATORE);
		rlServ.save(r1);
		rlServ.save(r2);
		u1.setRuoli(new HashSet<>() {{add(r1);}});
		u2.setRuoli(new HashSet<>() {{add(r2);}});
		u3.setRuoli(new HashSet<>() {{add(r1);}});
		u4.setRuoli(new HashSet<>() {{add(r1);}});
		utServ.save(u1);
		utServ.save(u2);
		utServ.save(u3);
		utServ.save(u4);
		CampoDaGioco c1 = (CampoDaGioco) ctx.getBean("campo", "Campetti Mirabella", "Reggio Calabria", "Via Vito Superiore 20", "0965.881028", TipoCampo.CALCIO_A_5);
		CampoDaGioco c2 = (CampoDaGioco) ctx.getBean("campo", "Tennis Club Reggio", "Reggio Calabria", "Via Pio XI 10", "0965.112233", TipoCampo.TENNIS);
		CampoDaGioco c3 = (CampoDaGioco) ctx.getBean("campo", "PalaCalafiore", "Reggio Calabria", "Via Croce Rossa 25", "0965.872233", TipoCampo.BASKET);
		CampoDaGioco c4 = (CampoDaGioco) ctx.getBean("campo", "Calcetto Top", "Reggio Calabria", "Via Giuseppe Verdi 36", "0965.435698", TipoCampo.CALCIO_A_5);
		CampoDaGioco c5 = (CampoDaGioco) ctx.getBean("campo", "Sport Village", "Reggio Calabria", "Via Catona 20", "0965.985436", TipoCampo.CALCIO_A_5);
		CampoDaGioco c6 = (CampoDaGioco) ctx.getBean("campo", "Padel Club Reggio", "Reggio Calabria", "Via dei Missaglia 50", "0965.923656", TipoCampo.PADEL);
		CampoDaGioco c7 = (CampoDaGioco) ctx.getBean("campo", "Polisportiva Reggio", "Reggio Calabria", "Via Ludovico il Moro 15", "0965.123456", TipoCampo.CALCIO_A_11);
		CampoDaGioco c8 = (CampoDaGioco) ctx.getBean("campo", "PalaLido", "Reggio Calabria", "Via Nazionale Porto 1", "0965.886571", TipoCampo.CALCIO_A_7);
		CampoDaGioco c9 = (CampoDaGioco) ctx.getBean("campo", "Campi Mila&Shiro", "Reggio Calabria", "Via Benigno Crespi 15", "0965.123456", TipoCampo.PALLAVOLO);
		CampoDaGioco c10 = (CampoDaGioco) ctx.getBean("campo", "Calcetto 7 Reggio", "Reggio Calabria", "Via Pietro Mascagni 12", "0965.1799046", TipoCampo.CALCIO_A_7);
		CampoDaGioco c11 = (CampoDaGioco) ctx.getBean("campo", "Calcetto Napoli Sud", "Napoli", "Via San Giovanni Bosco 10", "081.214578", TipoCampo.CALCIO_A_5);
		CampoDaGioco c12 = (CampoDaGioco) ctx.getBean("campo", "Tennis Club Napoli", "Napoli", "Via Solfatara 25", "081.753068", TipoCampo.TENNIS);
		CampoDaGioco c13 = (CampoDaGioco) ctx.getBean("campo", "Palazzetto dello Sport", "Napoli", "Via Giovanni Amendola 30", "081.576834", TipoCampo.BASKET);
		CampoDaGioco c14 = (CampoDaGioco) ctx.getBean("campo", "Calcetto Napoli Est", "Napoli", "Via Vincenzo Gioberti 15", "081.256897", TipoCampo.CALCIO_A_5);
		CampoDaGioco c15 = (CampoDaGioco) ctx.getBean("campo", "Sport Club Napoli", "Napoli", "Via Foria 20", "081.316498", TipoCampo.CALCIO_A_5);
		CampoDaGioco c16 = (CampoDaGioco) ctx.getBean("campo", "Padel Club Napoli", "Napoli", "Via Arenaccia 50", "081.256798", TipoCampo.PADEL);
		CampoDaGioco c17 = (CampoDaGioco) ctx.getBean("campo", "Polisportiva Napoli", "Napoli", "Via Giordano Bruno 15", "081.164975", TipoCampo.CALCIO_A_11);
		CampoDaGioco c18 = (CampoDaGioco) ctx.getBean("campo", "Campi Sportivi Napoli", "Napoli", "Via Nazionale 1", "081.213467", TipoCampo.CALCIO_A_7);
		CampoDaGioco c19 = (CampoDaGioco) ctx.getBean("campo", "Campi Volley Napoli", "Napoli", "Via degli Aranci 15", "081.202583", TipoCampo.PALLAVOLO);
		CampoDaGioco c20 = (CampoDaGioco) ctx.getBean("campo", "Calcetto Napoli Ovest", "Napoli", "Via Marcello Malpighi 12", "081.023658", TipoCampo.CALCIO_A_7);
		CampoDaGioco c21 = (CampoDaGioco) ctx.getBean("campo", "Calcetto Milano Nord", "Milano", "Via Alessandro Manzoni 10", "06.025896", TipoCampo.CALCIO_A_5);
		CampoDaGioco c22 = (CampoDaGioco) ctx.getBean("campo", "Tennis Club Milano", "Milano", "Via Vittorio Emanuele II 25", "06.326598", TipoCampo.TENNIS);
		CampoDaGioco c23 = (CampoDaGioco) ctx.getBean("campo", "Palazzetto dello Sport", "Milano", "Via Garibaldi 25", "06.256798", TipoCampo.BASKET);
		CampoDaGioco c24 = (CampoDaGioco) ctx.getBean("campo", "Calcetto Milano Sud", "Milano", "Via Giuseppe Garibaldi 15", "06.013467", TipoCampo.CALCIO_A_7);
		CampoDaGioco c25 = (CampoDaGioco) ctx.getBean("campo", "Campetti Idroscalo Milano", "Milano", "Via Carlo Valvassori Peroni 56", "06.325468", TipoCampo.CALCIO_A_5);
		CampoDaGioco c26 = (CampoDaGioco) ctx.getBean("campo", "Padel Club Milano", "Milano", "Via Adamello 8", "06.225502", TipoCampo.PADEL);
		CampoDaGioco c27 = (CampoDaGioco) ctx.getBean("campo", "Calcetto 5 Milano", "Milano", "Via Pietro Gaggia 22", "06.346554", TipoCampo.CALCIO_A_5);
		CampoDaGioco c28 = (CampoDaGioco) ctx.getBean("campo", "PalaDesio", "Milano", "Via Luigi Cibrario 10", "06.021996", TipoCampo.CALCIO_A_7);
		CampoDaGioco c29 = (CampoDaGioco) ctx.getBean("campo", "Campi Lissone", "Milano", "Via Torquato Tasso 45", "06.235666", TipoCampo.PALLAVOLO);
		CampoDaGioco c30 = (CampoDaGioco) ctx.getBean("campo", "Calcetto 11 Milano", "Milano", "Via Alessandro Manzoni 13", "06.784442", TipoCampo.CALCIO_A_11);
		CampoDaGioco c31 = (CampoDaGioco) ctx.getBean("campo", "Sport Center Rome", "Roma", "Via del Corso 10","02.569853", TipoCampo.CALCIO_A_5);
		CampoDaGioco c32 = (CampoDaGioco) ctx.getBean("campo", "Tennis Roma", "Roma", "Via Veneto 20","02.546978", TipoCampo.TENNIS);
		CampoDaGioco c33 = (CampoDaGioco) ctx.getBean("campo", "Basket Club Roma", "Roma", "Via Appia Nuova 30","02.569853", TipoCampo.BASKET);
		CampoDaGioco c34 = (CampoDaGioco) ctx.getBean("campo", "Calcetto Roma Sud", "Roma", "Via Tuscolana 50","02.569853", TipoCampo.CALCIO_A_5);
		CampoDaGioco c35 = (CampoDaGioco) ctx.getBean("campo", "Sport City Roma", "Roma", "Via Aurelia 10","02.579833", TipoCampo.CALCIO_A_5);
		CampoDaGioco c36 = (CampoDaGioco) ctx.getBean("campo", "Padel Club Roma", "Roma", "Via Appia Pignatelli 60","02.0024698", TipoCampo.PADEL);
		CampoDaGioco c37 = (CampoDaGioco) ctx.getBean("campo", "Polisportiva Roma Nord", "Roma", "Via della Bufalotta 15","02.025896", TipoCampo.CALCIO_A_11);
		CampoDaGioco c38 = (CampoDaGioco) ctx.getBean("campo", "PalaLido Roma", "Roma", "Lungomare Lido di Ostia 1","02.487653", TipoCampo.CALCIO_A_7);
		CampoDaGioco c39 = (CampoDaGioco) ctx.getBean("campo", "Volley Club Roma", "Roma", "Via Tiburtina 25","02.118976", TipoCampo.PALLAVOLO);
		CampoDaGioco c40 = (CampoDaGioco) ctx.getBean("campo", "Calcetto Roma Nord", "Roma", "Via Nomentana 12","02.886457", TipoCampo.CALCIO_A_7);
		CampoDaGioco c41 = (CampoDaGioco) ctx.getBean("campo", "Sporting Club", "Reggio Calabria", "Via della Vittoria 25", "0965.555555", TipoCampo.TENNIS);
		CampoDaGioco c42 = (CampoDaGioco) ctx.getBean("campo", "Campo di Calcio 11", "Reggio Calabria", "Via del Mare 55", "0965.666666", TipoCampo.CALCIO_A_11);
		CampoDaGioco c43 = (CampoDaGioco) ctx.getBean("campo", "PalaSport Napoli", "Napoli", "Via del Vesuvio 10", "081.123456", TipoCampo.BASKET);
		CampoDaGioco c44 = (CampoDaGioco) ctx.getBean("campo", "Campi di Tennis Roma", "Roma", "Via Appia Nuova 100", "06.987654", TipoCampo.TENNIS);
		CampoDaGioco c45 = (CampoDaGioco) ctx.getBean("campo", "Centro Sportivo Milano", "Milano", "Via della Repubblica 30", "02.555555", TipoCampo.PALLAVOLO);
		CampoDaGioco c46 = (CampoDaGioco) ctx.getBean("campo", "Calcetto 5 Napoli", "Napoli", "Via dei Mille 50", "081.654321", TipoCampo.CALCIO_A_5);
		CampoDaGioco c47 = (CampoDaGioco) ctx.getBean("campo", "PalaSport Roma", "Roma", "Via Ostiense 200", "06.111111", TipoCampo.BASKET);
		CampoDaGioco c48 = (CampoDaGioco) ctx.getBean("campo", "Polisportiva Milano", "Milano", "Via Pietro Mascagni 12", "02.444444", TipoCampo.PALLAVOLO);
		CampoDaGioco c49 = (CampoDaGioco) ctx.getBean("campo", "Calcetto 7 Napoli", "Napoli", "Via San Paolo 75", "081.777777", TipoCampo.CALCIO_A_7);
		CampoDaGioco c50 = (CampoDaGioco) ctx.getBean("campo", "Campo di Calcio 7 Roma", "Roma", "Via Nomentana 500", "06.222222", TipoCampo.CALCIO_A_7);
		CampoDaGioco c51 = (CampoDaGioco) ctx.getBean("campo", "Centro Sportivo Torino", "Torino", "Via Roma 100", "011.555555", TipoCampo.PALLAVOLO);
		CampoDaGioco c52 = (CampoDaGioco) ctx.getBean("campo", "Campo di Calcio 5 Bologna", "Bologna", "Via Emilia 200", "051.666666", TipoCampo.CALCIO_A_5);
		CampoDaGioco c53 = (CampoDaGioco) ctx.getBean("campo", "PalaSport Firenze", "Firenze", "Via degli Artisti 50", "055.123456", TipoCampo.BASKET);
		CampoDaGioco c54 = (CampoDaGioco) ctx.getBean("campo", "Campetti Napoli", "Napoli", "Via della Libertà 75", "081.888888", TipoCampo.CALCIO_A_5);
		CampoDaGioco c55 = (CampoDaGioco) ctx.getBean("campo", "Tennis Club Torino", "Torino", "Via dei Mille 10", "011.444444", TipoCampo.TENNIS);
		CampoDaGioco c56 = (CampoDaGioco) ctx.getBean("campo", "Campo di Calcio 7 Bologna", "Bologna", "Via Zamboni 30", "051.777777", TipoCampo.CALCIO_A_7);
		CampoDaGioco c57 = (CampoDaGioco) ctx.getBean("campo", "Polisportiva Firenze", "Firenze", "Via del Carro 20", "055.654321", TipoCampo.PALLAVOLO);
		CampoDaGioco c58 = (CampoDaGioco) ctx.getBean("campo", "Centro Sportivo Napoli", "Napoli", "Via delle Rose 5", "081.777777", TipoCampo.PALLAVOLO);
		CampoDaGioco c59 = (CampoDaGioco) ctx.getBean("campo", "Calcetto Top Torino", "Torino", "Via della Consolata 25", "011.333333", TipoCampo.CALCIO_A_5);
		CampoDaGioco c60 = (CampoDaGioco) ctx.getBean("campo", "Campo di Calcio 11 Bologna", "Bologna", "Via Galliera 100", "051.888888", TipoCampo.CALCIO_A_11);
		CampoDaGioco c61 = (CampoDaGioco) ctx.getBean("campo", "PalaSport Roma 2", "Roma", "Via Tiburtina 500", "06.555555", TipoCampo.BASKET);
		CampoDaGioco c62 = (CampoDaGioco) ctx.getBean("campo", "Campo di Calcio 5 Milano", "Milano", "Via Montenapoleone 50", "02.666666", TipoCampo.CALCIO_A_5);
		CampoDaGioco c63 = (CampoDaGioco) ctx.getBean("campo", "Polisportiva Torino", "Torino", "Via San Francesco d'Assisi 15", "011.777777", TipoCampo.PALLAVOLO);
		CampoDaGioco c64 = (CampoDaGioco) ctx.getBean("campo", "Tennis Club Bologna", "Bologna", "Via Indipendenza 250", "051.999999", TipoCampo.TENNIS);
		CampoDaGioco c65 = (CampoDaGioco) ctx.getBean("campo", "Tennis Club Milano", "Milano", "Via Alessandro Volta 10", "02.12345678", TipoCampo.TENNIS);
		CampoDaGioco c66 = (CampoDaGioco) ctx.getBean("campo", "Basket Arena Milano", "Milano", "Via Marco Polo 45", "02.87654321", TipoCampo.BASKET);
		CampoDaGioco c67 = (CampoDaGioco) ctx.getBean("campo", "PalaMilano", "Milano", "Via Giuseppe Meazza 3", "02.11111111", TipoCampo.CALCIO_A_11);
		CampoDaGioco c68 = (CampoDaGioco) ctx.getBean("campo", "Padel Club Milano", "Milano", "Via Alessandro Manzoni 25", "02.22222222", TipoCampo.PADEL);
		CampoDaGioco c69 = (CampoDaGioco) ctx.getBean("campo", "Polisportiva Milano", "Milano", "Via Eugenio Montale 15", "02.33333333", TipoCampo.CALCIO_A_7);
		CampoDaGioco c70 = (CampoDaGioco) ctx.getBean("campo", "Volley Club Milano", "Milano", "Via Gabriele D'Annunzio 10", "02.44444444", TipoCampo.PALLAVOLO);
		CampoDaGioco c71 = (CampoDaGioco) ctx.getBean("campo", "Tennis Club Napoli", "Napoli", "Via Cristoforo Colombo 20", "081.123456", TipoCampo.TENNIS);
		CampoDaGioco c72 = (CampoDaGioco) ctx.getBean("campo", "Basket Arena Napoli", "Napoli", "Via Giovanni Falcone 30", "081.654321", TipoCampo.BASKET);
		CampoDaGioco c73 = (CampoDaGioco) ctx.getBean("campo", "PalaNapoli", "Napoli", "Via dei Mille 50", "081.111111", TipoCampo.CALCIO_A_11);
		CampoDaGioco c74 = (CampoDaGioco) ctx.getBean("campo", "Padel Club Napoli", "Napoli", "Via Benedetto Croce 5", "081.222222", TipoCampo.PADEL);
		CampoDaGioco c75 = (CampoDaGioco) ctx.getBean("campo", "Polisportiva Napoli", "Napoli", "Via Salvatore Allende 25", "081.333333", TipoCampo.CALCIO_A_7);
		CampoDaGioco c76 = (CampoDaGioco) ctx.getBean("campo", "Volley Club Napoli", "Napoli", "Via Antonio De Curtis 30", "081.444444", TipoCampo.PALLAVOLO);
		CampoDaGioco c77 = (CampoDaGioco) ctx.getBean("campo", "Tennis Club Roma", "Roma", "Via Appia Nuova 10", "06.12345678", TipoCampo.TENNIS);
		CampoDaGioco c78 = (CampoDaGioco) ctx.getBean("campo", "Basket Arena Roma", "Roma", "Via Cassia 45", "06.87654321", TipoCampo.BASKET);
		CampoDaGioco c79 = (CampoDaGioco) ctx.getBean("campo", "PalaMazzola", "Bari", "Via Enzo Mazzola 15", "080.123456", TipoCampo.PADEL);
		CampoDaGioco c80 = (CampoDaGioco) ctx.getBean("campo", "Polisportiva Giovinazzo", "Bari", "Via Michelangelo 20", "080.654321", TipoCampo.CALCIO_A_5);
		CampoDaGioco c81 = (CampoDaGioco) ctx.getBean("campo", "Tennis Club Bari", "Bari", "Via G. Capruzzi 30", "080.111222", TipoCampo.TENNIS);
		CampoDaGioco c82 = (CampoDaGioco) ctx.getBean("campo", "Calcetto 5 Bari", "Bari", "Via dei Gracchi 45", "080.987654", TipoCampo.CALCIO_A_5);
		CampoDaGioco c83 = (CampoDaGioco) ctx.getBean("campo", "PalaFlorio", "Bari", "Via Giuseppe Capruzzi 60", "080.555666", TipoCampo.PADEL);
		CampoDaGioco c84 = (CampoDaGioco) ctx.getBean("campo", "Polisportiva Cus Bari", "Bari", "Via N. Bixio 75", "080.222333", TipoCampo.CALCIO_A_11);
		CampoDaGioco c85 = (CampoDaGioco) ctx.getBean("campo", "PalaPanetti", "Bari", "Via Arno 25", "080.777888", TipoCampo.PALLAVOLO);
		CampoDaGioco c86 = (CampoDaGioco) ctx.getBean("campo", "Sporting Club Bari", "Bari", "Via A. Volta 5", "080.456789", TipoCampo.PADEL);
		CampoDaGioco c87 = (CampoDaGioco) ctx.getBean("campo", "Tennis Club Genova", "Genova", "Via Montallegro 20", "010.112233", TipoCampo.TENNIS);
		CampoDaGioco c88 = (CampoDaGioco) ctx.getBean("campo", "Polisportiva Cus Genova", "Genova", "Via Balbi 40", "010.444555", TipoCampo.CALCIO_A_11);
		CampoDaGioco c89 = (CampoDaGioco) ctx.getBean("campo", "Calcetto 5 Genova", "Genova", "Via Brigata Liguria 65", "010.999888", TipoCampo.CALCIO_A_5);
		CampoDaGioco c90 = (CampoDaGioco) ctx.getBean("campo", "PalaGenova", "Genova", "Via Cesarea 90", "010.777666", TipoCampo.PADEL);
		CampoDaGioco c91 = (CampoDaGioco) ctx.getBean("campo", "PalaRighi", "Genova", "Via Capolungo 30", "010.333222", TipoCampo.PALLAVOLO);
		CampoDaGioco c92 = (CampoDaGioco) ctx.getBean("campo", "Palestra Olimpia", "Genova", "Via Giuseppe Mazzini 15", "010.654321", TipoCampo.PADEL);
		CampoDaGioco c93 = (CampoDaGioco) ctx.getBean("campo", "Sporting Club", "Cagliari", "Via dei Campioni 15", "070.123456", TipoCampo.TENNIS);
		CampoDaGioco c94 = (CampoDaGioco) ctx.getBean("campo", "PalaCagliari", "Cagliari", "Via Roma 100", "070.987654", TipoCampo.BASKET);
		CampoDaGioco c95 = (CampoDaGioco) ctx.getBean("campo", "Volley Club Cagliari", "Cagliari", "Via S. Giovanni Bosco 30", "070.555666", TipoCampo.PALLAVOLO);
		CampoDaGioco c96 = (CampoDaGioco) ctx.getBean("campo", "Stadio Cagliari", "Cagliari", "Via dei Gladiatori 10", "070.333444", TipoCampo.CALCIO_A_11);
		CampoDaGioco c97 = (CampoDaGioco) ctx.getBean("campo", "Padel Cagliari", "Cagliari", "Via Carlo Alberto 50", "070.222333", TipoCampo.PADEL);
		CampoDaGioco c98 = (CampoDaGioco) ctx.getBean("campo", "Calcetto 5 Cagliari", "Cagliari", "Via Dante Alighieri 25", "070.888777", TipoCampo.CALCIO_A_5);
		CampoDaGioco c99 = (CampoDaGioco) ctx.getBean("campo", "Campetti del Mare", "Cagliari", "Via dei Pescatori 20", "070.555444", TipoCampo.CALCIO_A_7);
		CampoDaGioco c100 = (CampoDaGioco) ctx.getBean("campo", "Tennis Club Cagliari", "Cagliari", "Via del Tennis 8", "070.777888", TipoCampo.TENNIS);


		cgServ.save(c1);
		cgServ.save(c2);
		cgServ.save(c3);
		cgServ.save(c4);
		cgServ.save(c5);
		cgServ.save(c6);
		cgServ.save(c7);
		cgServ.save(c8);
		cgServ.save(c9);
		cgServ.save(c10);
		cgServ.save(c11);
		cgServ.save(c12);
		cgServ.save(c13);
		cgServ.save(c14);
		cgServ.save(c15);
		cgServ.save(c16);
		cgServ.save(c17);
		cgServ.save(c18);
		cgServ.save(c19);		
		cgServ.save(c20);
		cgServ.save(c21);
		cgServ.save(c22);
		cgServ.save(c23);
		cgServ.save(c24);
		cgServ.save(c25);
		cgServ.save(c26);
		cgServ.save(c27);
		cgServ.save(c28);
		cgServ.save(c29);
		cgServ.save(c30);
		cgServ.save(c31);
		cgServ.save(c32);
		cgServ.save(c33);
		cgServ.save(c34);
		cgServ.save(c35);
		cgServ.save(c36);
		cgServ.save(c37);
		cgServ.save(c38);
		cgServ.save(c39);
		cgServ.save(c40);
		cgServ.save(c41);
		cgServ.save(c42);
		cgServ.save(c43);
		cgServ.save(c44);
		cgServ.save(c45);
		cgServ.save(c46);
		cgServ.save(c47);
		cgServ.save(c48);
		cgServ.save(c49);
		cgServ.save(c50);
		cgServ.save(c51);
		cgServ.save(c52);
		cgServ.save(c53);
		cgServ.save(c54);
		cgServ.save(c55);
		cgServ.save(c56);
		cgServ.save(c57);
		cgServ.save(c58);
		cgServ.save(c59);
		cgServ.save(c60);
		cgServ.save(c61);
		cgServ.save(c62);
		cgServ.save(c63);
		cgServ.save(c64);
		cgServ.save(c65);
		cgServ.save(c66);
		cgServ.save(c67);
		cgServ.save(c68);
		cgServ.save(c69);
		cgServ.save(c70);
		cgServ.save(c71);
		cgServ.save(c72);
		cgServ.save(c73);
		cgServ.save(c74);
		cgServ.save(c75);
		cgServ.save(c76);
		cgServ.save(c77);
		cgServ.save(c78);
		cgServ.save(c79);
		cgServ.save(c80);
		cgServ.save(c81);
		cgServ.save(c82);
		cgServ.save(c83);
		cgServ.save(c84);
		cgServ.save(c85);
		cgServ.save(c86);
		cgServ.save(c87);
		cgServ.save(c88);
		cgServ.save(c89);
		cgServ.save(c90);
		cgServ.save(c91);
		cgServ.save(c92);
		cgServ.save(c93);
		cgServ.save(c94);
		cgServ.save(c95);
		cgServ.save(c96);
		cgServ.save(c97);
		cgServ.save(c98);
		cgServ.save(c99);
		cgServ.save(c100);
		
	}

	}
