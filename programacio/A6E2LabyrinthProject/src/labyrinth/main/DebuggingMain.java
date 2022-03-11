package labyrinth.main;

import java.io.IOException;

import labyrinth.controller.Labyrinth;
import labyrinth.models.Player;

public class DebuggingMain {

	public static void main(String[] args) {
		
		try {
			Labyrinth.createLabyrinth("prova.txt", 10, 10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Labyrinth lab = new Labyrinth();
		try {
			lab.loadLabyrinth("prova.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lab.startGame("caca");
		
		System.out.println(lab.toString());
		lab.toString();
		
	}

}
