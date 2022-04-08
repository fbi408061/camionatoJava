package csvreader;

/**
 * Classe che si occupa di gestire le singole squadre di calcio
 * 
 * @version 1.0.0
 */

public class Squadra implements Comparable<Squadra> {
	private String nome;
	private int punti;
	private int vittorie;
	private int pareggi;
	private int sconfitte;

	public Squadra(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public int getVittorie() {
		return vittorie;
	}

	public void setVittorie(int vittorie) {
		this.vittorie = vittorie;
	}

	
	public int getPareggi() {
		return pareggi;
	}

	public void setPareggi(int pareggi) {
		this.pareggi = pareggi;
	}
	
	public int getSconfitte() {
		return sconfitte;
	}

	public void setSconfitte(int sconfitte) {
		this.sconfitte = sconfitte;
	}

	public int getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti = punti;
	}

	public void incrementaVittorie() {
		this.vittorie++;
	}

	public void incrementaPareggi() {
		this.pareggi++;
	}

	public void incrementaSconfitte() {
		this.sconfitte++;
	}
	
	public void incrementaPunti(int punteggio) {
		this.punti += punteggio;
	}

	@Override
	public String toString() {
		return nome + ": " + punti + ", " + vittorie + ", " + pareggi + ", " + sconfitte;
	}
	
	public String toStringClassifica() {
		String toString = "";
		int spazi = 32 - nome.length();
		String formatter = "%s%"+spazi+"s| ";
		System.out.format(formatter, nome, ""); // Test stampa con formatter
		//if (nome.length() > 23) toString += nome + " \t | ";
		//else if (nome.length() > 14) toString += nome + "\t\t | ";
		//else toString += nome + "\t\t\t | ";
		toString += punti + " \t | ";
		toString += vittorie + " \t    | ";
		toString += pareggi + " \t| ";
		toString += sconfitte + " \t    | ";
		
		return toString;
	}

	@Override
	public int compareTo(Squadra o) {
		// ORDINAMENTO DECRESCENTE PER LA CLASSIFICA
		return o.getPunti() - this.getPunti();
	}

}
