package labyrinth.main;

import java.io.IOException;
import java.util.Scanner;

import labyrinth.controller.Labyrinth;
import labyrinth.tools.CellType;

public class OtherDebuggingMain {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int option = -1;
		
		while (option != 0) {
			
			System.out.println("~~~~ BENVINGUT AL JOC DEL LABERINT ~~~~" +
					"\n0. Tancar el joc" +
					"\n1. Carregar partida" +
					"\n2. Crear nova partida");
			
			//option = Integer.parseInt(scan.nextLine());
			option = 1;
			
			switch (option) {
			
			case 0:
				
				System.out.println("A reveure!");
		
			break;
		
			case 1:
				
				System.out.println("Entri el nom de la partida:");
				//String fileName = scan.nextLine();
				String fileName = "prova";
				
				Labyrinth labyrinth = new Labyrinth();
				
				try {
					labyrinth.loadLabyrinth(fileName);
				} catch (IOException e) {
					System.out.println("La partida amb nom " + fileName + " no s'ha trobat.");
				}
				
				System.out.println("Entri el nom del jugador:");
				
				//String playerName = scan.nextLine();
				String playerName = "Tester Johnson";
				labyrinth.startGame(playerName);
				System.out.println("~~~~ INICI DE LA PARTIDA! ~~~~");
				
				if(!labyrinth.traverseCell()) {
					
					System.out.println("~~~~ GAME OVER! ~~~~");
					
				}
				
				while (!labyrinth.isGameEnded()) {
					
					System.out.println(labyrinth.toString());
					
					char mov = scan.nextLine().charAt(0);
					
					while (mov != 'U' && mov != 'D' && mov != 'R' && mov != 'L') {
						
						System.out.println("Moviment invàlid, intenti-ho de nou!");
						mov = scan.nextLine().charAt(0);
						
					}
					
					labyrinth.movePlayer(mov);
					
					if (labyrinth.getPlayerCellType() == CellType.NORMAL_EXIT) {
					
						labyrinth.traverseCell();
						System.out.println(labyrinth.toString() + "\nHa sortit del laberint!!");
					
					}else {
						
						while (labyrinth.getPlayerCellType() == CellType.TELEPORTATION) {
							
							labyrinth.traverseCell();
							
						}
						
						if(!labyrinth.traverseCell()) {
							
							
							System.out.println(labyrinth.toString());
							System.out.println("~~~~ GAME OVER! ~~~~");
							
						}
						
					}
					
				}
				
			break;
			
			case 2:
				
				System.out.println("Quin nom rebrà la partida?");
				
				String newfileName = scan.nextLine();
				
				System.out.println("Quantes columnes tindrà? (mínim 5)");
				int newCols = Integer.parseInt(scan.nextLine());
						
				while (newCols < 5) {
					
					System.out.println("Quantes columnes tindrà? (mínim 5)");
					newCols = Integer.parseInt(scan.nextLine());
					
				}
				
				System.out.println("Quantes files tindrà? (mínim 3)");
				int newRows = Integer.parseInt(scan.nextLine());
						
				while (newRows < 3) {
					
					System.out.println("Quantes columnes tindrà? (mínim 3)");
					newRows = Integer.parseInt(scan.nextLine());
					
				}
				
			try {
				Labyrinth.createLabyrinth(newfileName, newCols, newRows);
			} catch (IOException e) {
				System.out.println("No s'ha pogut crear la partida amb el nom " + newfileName + ".");
			}
				
			break;
			
			default:
				System.out.println("Opció invàlida!");
			
			break;
			}
			
		}
		
	}

}
