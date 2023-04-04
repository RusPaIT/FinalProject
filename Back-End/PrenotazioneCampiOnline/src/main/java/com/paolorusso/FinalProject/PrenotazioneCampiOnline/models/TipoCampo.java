package com.paolorusso.FinalProject.PrenotazioneCampiOnline.models;

public enum TipoCampo {

    PADEL,
    TENNIS,
    CALCIO_A_5,
    CALCIO_A_11,
    CALCIO_A_7("Calcio a 7"),
    BASKET,
    PALLAVOLO;
	
	TipoCampo() {}
	
    private String descrizione;

	TipoCampo(String descrizione) {
		this.descrizione = descrizione;
		
	}

	@Override
	public String toString() {
		return descrizione != null ? descrizione : name().replace("_", " ").toLowerCase();
	}
}

