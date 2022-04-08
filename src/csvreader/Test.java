package csvreader;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Campionato c = new Campionato("Premier league", "2019-2020");
		Scanner sc = new Scanner(System.in);
		
		c.carica_dati("league2020.csv");
		
		// Menu
		int risposta;

		do {
			risposta = 0;
			System.out.println("---------------Menu---------------");
			System.out.println("| 1) Elenco partite per giornate |");
			System.out.println("| 2) Elenco partite per squadra  |");
			System.out.println("| 3) Classifica del campionato   |");
			System.out.println("| 0) Fine programma              |");
			System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

			System.out.print("Cosa si vuole fare? ");
			risposta = sc.nextInt();
			sc.nextLine();
			System.out.println("\n");

			switch (risposta) {
			case 1: {
				System.out.print("Che giornata del campionato si vuole vedere? ");
				int giornata = sc.nextInt();
				
				c.StampaPartiteGiornate(giornata);
				System.out.println("\n");
				
				break;
			}
			case 2: {
				System.out.print("Che squadra del campionato si vuole vedere? (prefisso o nome completo) ");
				String squadra = sc.nextLine();

				c.StampaPartiteSquadre(squadra);
				System.out.println("\n");
				
				break;
			}
			case 3: {
				c.StampaClassifica();
				System.out.println("\n");
				
				break;
			}
			default: {
				System.out.println("Opzione non presente tra le scelte disponibili.");
				System.out.println();
			}
			
			case 0: {}
			}
		} while (risposta != 0);
		
		
		sc.close();
	}

}
