package csvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;

/**
 * Classe per poter gestire un campionato di calcio
 * 
 * @version 1.0.0
 */
public class Campionato {
	/**
	 * DATI DEL CAMPIONATO: serie e stagione
	 */
	private String serie;
	private String stagione;
	/**
	 * BufferedReader br per la lettura dei dati da file
	 */
	private BufferedReader br;
	/**
	 * HASHMAP partite_giornate(K,V) K = numero giornata V = Elenco parItite giocata
	 * in quella giornata
	 */
	private Map<Integer, List<Partita>> partite_giornate;
	/**
	 * TREEMAP partite_squadre(K,V) K = nome squadra V = Elenco partite giocate da
	 * quella squadra
	 */
	private Map<String, List<Partita>> partite_squadre;
	/**
	 * ARRAYLIST classifica ( del campionato ordinata per punteggio decrescente )
	 */
	private List<Squadra> classifica;

	/**
	 * Costruttore
	 * 
	 * @param serie
	 * @param stagione
	 */
	public Campionato(String serie, String stagione) {
		this.serie = serie;
		this.stagione = stagione;
	}

	/**
	 * Costruttore
	 * 
	 * @param serie
	 * @param stagione
	 * @param nome     file
	 */
	public Campionato(String serie, String stagione, String filename) {
		this.serie = serie;
		this.stagione = stagione;
		this.carica_dati(filename);
	}

	/**
	 * Carica dati: inizializza le strutture dati, apre il file, carica i dati nelle
	 * mappe e nell'arrayList
	 * 
	 * @param ...
	 * @throws IOException
	 * 
	 */
	public void carica_dati(String filename) {
		partite_giornate = new HashMap<Integer, List<Partita>>();
		partite_squadre = new TreeMap<String, List<Partita>>();
		classifica = new ArrayList<>();
		List<Partita> partite = new ArrayList<>();
		Partita app;
		String data[] = new String[5];
		String line;

		try {
			br = new BufferedReader(new FileReader(filename));
			String header = br.readLine();

			while ((line = br.readLine()) != null) {
				data = line.split(",");
				app = new Partita(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]);
				
				if (partite_giornate.containsKey(app.getGiornata())) { 
					partite = partite_giornate.get(app.getGiornata());
				} 
				partite.add(app);
				partite_giornate.put(app.getGiornata(), new ArrayList<Partita>(partite));
				partite.clear();
				
				if (partite_squadre.containsKey(app.getSquadra_casa())) { 
					partite = partite_squadre.get(app.getSquadra_casa());
				} 
				partite.add(app);
				partite_squadre.put(app.getSquadra_casa(), new ArrayList<Partita>(partite));
				partite.clear();
				
				if (partite_squadre.containsKey(app.getSquadra_ospite())) { 
					partite = partite_squadre.get(app.getSquadra_ospite());
				} 
				partite.add(app);
				partite_squadre.put(app.getSquadra_ospite(), new ArrayList<Partita>(partite));
				partite.clear();
				
				/*
				// Inserimento nella HashMap le partite per giornata
				partite.add(app);
				if (partite_giornate.containsKey(app.getGiornata())) { // Controllo se nella mappa sono già presenti
																		// alcune partite per quella giornata
					// Se presenti le inserisco nell'ArrayList con la nuova partita in quella
					// giornata
					partite.addAll(partite_giornate.get(app.getGiornata()));
					// Sostituisco i valori presenti nella mappa con il nuovo ArrayList
					partite_giornate.replace(app.getGiornata(), new ArrayList<Partita>(partite));
				} else {
					// Se non presenti partite in quella giornata allora aggiungo alla mappa la
					// partita trovatain quella giornata
					partite_giornate.put(app.getGiornata(), new ArrayList<Partita>(partite));
				}
				partite.clear();
				

				// Inserimento nella TreeMap le partite in casa per squadra
				partite.add(app);
				if (partite_squadre.containsKey(app.getSquadra_casa())) { // Controllo se la squadra è già presente
																			// nella mappa
					// Se presente mi prendo i valori già presenti nella mappa e li aggiungo a
					// quello nuovo
					partite.addAll(partite_squadre.get(app.getSquadra_casa()));
					// Sostituisco i vecchi valori con quelli nuovi
					partite_squadre.replace(app.getSquadra_casa(), new ArrayList<Partita>(partite));
				} else {
					// Se non presente creo aggiungo alla mappa la squadra con la partita fatta per
					// aggiornarla più tardi
					partite_squadre.put(app.getSquadra_casa(), new ArrayList<Partita>(partite));
				}
				partite.clear();

				// Inserimento nella TreeMap le partite come ospite per squadra
				partite.add(app);
				if (partite_squadre.containsKey(app.getSquadra_ospite())) { // Controllo se la squadra è già presente
																			// nella mappa
					// Se presente mi prendo i valori già presenti nella mappa e li aggiungo a
					// quello nuovo
					partite.addAll(partite_squadre.get(app.getSquadra_ospite()));
					// Sostituisco i vecchi valori con quelli nuovi
					partite_squadre.replace(app.getSquadra_ospite(), new ArrayList<Partita>(partite));
				} else {
					// Se non presente creo aggiungo alla mappa la squadra con la partita fatta per
					// aggiornarla più tardi
					partite_squadre.put(app.getSquadra_ospite(), new ArrayList<Partita>(partite));
				}
				partite.clear();
				*/

				// Calcolo punteggio per la classifica
				// Uso split in modo da ottenere i gol della squadra in casa e ospite
				String punteggio[] = data[3].split("-");
				// Controllo se la squadra in casa ha vinto
				if (Integer.parseInt(punteggio[0]) > Integer.parseInt(punteggio[1])) {
					// Se ha vinto si incrmentano i suoi punti di 3 e le sue vittorie di 1
					this.classifica.get(aggiornaClassifica(data[2])).incrementaPunti(3);
					this.classifica.get(aggiornaClassifica(data[2])).incrementaVittorie();
					// Incremento sconfitte della squadra che ha perso di 1
					this.classifica.get(aggiornaClassifica(data[4])).incrementaSconfitte();
				} else if (Integer.parseInt(punteggio[0]) < Integer.parseInt(punteggio[1])) {
					// Se ha perso si incrementano i punti della squadra ospite di 3 e le sue
					// vittorie di 1
					this.classifica.get(aggiornaClassifica(data[4])).incrementaPunti(3);
					this.classifica.get(aggiornaClassifica(data[4])).incrementaVittorie();
					// Incremento sconfitte della squadra che ha perso di 1
					this.classifica.get(aggiornaClassifica(data[2])).incrementaSconfitte();
				} else {
					// In caso di pareggio si devono incrementare i punti di entrambe le squadre di 1
					this.classifica.get(aggiornaClassifica(data[2])).incrementaPunti(1);
					this.classifica.get(aggiornaClassifica(data[4])).incrementaPunti(1);
					// e si incrementano il numero di pareggi di 1
					this.classifica.get(aggiornaClassifica(data[2])).incrementaPareggi();
					this.classifica.get(aggiornaClassifica(data[4])).incrementaPareggi();
				}
			}
			br.close();

		} catch (IOException e) {
			System.out.println("Errore durante la apertura/lettura del file.");
		}
	}

	/**
	 * Stampa partite della giornata richiesta
	 * 
	 * @param giornata
	 */
	public void StampaPartiteGiornate(int giornata) {
		// Stampa giornata

		if (partite_giornate.containsKey(giornata)) {
			List<Partita> partite = new ArrayList<>();

			partite = partite_giornate.get(giornata);
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------");
			System.out.println("| Giornata | Data \t\t | Squadra in casa \t\t | Punteggio\t | Squadra ospite \t\t |");
			for (int i = partite.size() - 1; i >= 0; i--) {
				System.out.println(partite.get(i).toString());
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------");
			System.out.println();
		} else {
			System.out.println("Giornata non presente nel campionato.");
		}

	}

	/**
	 * Stampa partite della squadra richiesta
	 * 
	 * @param squadra
	 */
	public void StampaPartiteSquadre(String squadra) {
		// Stampa squadra
		squadra = squadra.toLowerCase(); // ABC -> abc

		String squadre[] = new String[partite_squadre.size()];
		String chiavi[] = partite_squadre.keySet().toArray(new String[partite_squadre.size()]);
		
		// partite_squadre.keySet() -> Set di chiavi
		// keySet().toArray(new String[partite_squadre.size()]) -> array di stringhe
		
		int index = 0;
		for (String s : chiavi) {
			if (s.toLowerCase().startsWith(squadra)) {
				squadre[index++] = s;
			}
		}
		
		if (index == 0) {
			System.out.println("Nessuna squadra presente con quel prefisso o nome.");
			return;
		}
		
		if (index == 1) squadra = squadre[0];
		else if (index > 1) {
			System.out.println("Ci son più squadre con il prefisso indicato, sceglierne una:");
			for (int i = 0; i < index; i++) {
				if (i < 9) System.out.println((i + 1) + "°)  " + squadre[i]);
				else System.out.println((i + 1) + "°) " + squadre[i]);
			}
			
			Scanner sc = new Scanner(System.in);
			int scelta;
			do {
				System.out.print("Squadra numero: ");
				scelta = sc.nextInt();
				sc.nextLine();
				if (scelta < 1 || scelta > index) System.out.println("Inserire uno dei numeri delle squadre, non fuori dall'intervallo.");
			} while (scelta < 1 || scelta > index);
			
			squadra = squadre[scelta - 1];
		}
		
		// Il controllo del prefisso di una squadra si può fare anche usando l'ArrayList classifica
		
		List<Partita> partite = new ArrayList<>();

		partite = partite_squadre.get(squadra);
		Collections.sort(partite);
		System.out.println("Partite giocate dalla " + squadra + ":");
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.println("| Giornata | Data \t\t | Squadra in casa \t\t | Punteggio\t | Squadra ospite \t\t |");
		for (int i = partite.size() - 1; i >= 0; i--) {
			System.out.println(partite.get(i).toString());
		}
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.println();
	}

	private int aggiornaClassifica(String nome) {
		int i = 0;
		for (Squadra s : this.classifica) {
			if (nome.compareToIgnoreCase(s.getNome()) == 0) {
				return i;
			}
			i++;
		}
		this.classifica.add(new Squadra(nome));
		return this.classifica.size() - 1;
	}

	/**
	 * Stampa della classifica del campionato
	 * 
	 */
	public void StampaClassifica() {
		List<Squadra> partite = new ArrayList<Squadra>(classifica);
		// Ordinamento classifica per punteggio (1° quello con più punti)
		Collections.sort(partite);

		// Stampa Classifica
		System.out.println("Classifica " + serie + ", stagione " + stagione + ":");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("| Pos  | Squadra \t\t\t | Punti | Vittorie | Pareggi   | Sconfitte |");
		for (int i = 0; i < partite.size(); i++) {
			if (i < 9)
				System.out.print("| " + (i + 1) + "°   | ");
			else
				System.out.print("| " + (i + 1) + "°  | ");
			System.out.println(partite.get(i).toStringClassifica());
		}
		System.out.println("-------------------------------------------------------------------------------------");

	}

}
