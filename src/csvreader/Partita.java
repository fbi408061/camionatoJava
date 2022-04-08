package csvreader;


/**
 * Classe che si occupa di gestire le singole partite di calcio tra le squadre
 * 
 * @version 1.0.0
 */
public class Partita implements Comparable<Partita> {
	int giornata;
	String data;
	String squadra_casa;
	String punteggio;
	String squadra_ospite;

	public Partita() {
	}

	public Partita(int giornata, String data, String squadra_casa, String punteggio, String squadra_ospite) {
		this.giornata = giornata;
		this.data = data;
		this.squadra_casa = squadra_casa;
		this.punteggio = punteggio;
		this.squadra_ospite = squadra_ospite;
	}

	@Override
	public String toString() {
		String toString = "";
		toString += "| " + giornata + "° \t   | " + data + " \t | ";
		
		if (squadra_casa.length() > 23) toString += squadra_casa + "\t | ";
		else if (squadra_casa.length() > 15) toString += squadra_casa + "\t\t | ";
		else toString += squadra_casa + "   \t\t | ";
		
		toString += punteggio + " \t\t | ";
		
		if (squadra_ospite.length() > 23) toString += squadra_ospite + "\t | ";
		else if (squadra_ospite.length() > 15) toString += squadra_ospite + "\t\t | ";
		else toString += squadra_ospite + "   \t\t | ";
		
		return toString;
	}

	public int getGiornata() {
		return giornata;
	}

	public void setGiornata(int giornata) {
		this.giornata = giornata;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSquadra_casa() {
		return squadra_casa;
	}

	public void setSquadra_casa(String squadra_casa) {
		this.squadra_casa = squadra_casa;
	}

	public String getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(String punteggio) {
		this.punteggio = punteggio;
	}

	public String getSquadra_ospite() {
		return squadra_ospite;
	}

	public void setSquadra_ospite(String squadra_ospite) {
		this.squadra_ospite = squadra_ospite;
	}

	@Override
	public int compareTo(Partita o) {
		return o.getGiornata() - this.getGiornata();
	}
}