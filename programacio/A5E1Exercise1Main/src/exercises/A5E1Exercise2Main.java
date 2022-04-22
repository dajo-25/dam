package exercises;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class A5E1Exercise2Main {

	public static final int N_MAX = 250;
	public static final int N_MIN = 10;
	public static final int N_VALUE_MAX = 10000;
	public static final int N_VALUE_MIN = 10;
	
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int option = -1;
		
		while(option != 0) {
			
			System.out.println("\n~~~~ GESTIÓ DE FITXERS ~~~~\n" + 
					"0. Sortir \n" +
					"1. Crear un nou fitxer \n" +
					"2. Ordenar d'arxius que conté \n" +
					"Opció:");
			
			option = Integer.parseInt(scan.nextLine());
			
			switch (option) {
			case 0:
				
				System.out.println("A reveure!");
				
				break;

			case 1:
				
				System.out.println("Introdueixi el nom del nou fitxer:");
				
				File file = new File(scan.nextLine());
				
				try {
					
					if (!file.exists()) {
						
						if(!A5E1Exercise2Main.foldersReady(file)) {
							A5E1Exercise2Main.createFolders(file);
						}
						
						file.createNewFile();
						
						A5E1Exercise2Main.writeFile(file);
						
					}else {
						System.out.println("El fitxer ja existeix");
					}

				} catch (IOException e) {
					System.out.println("No s'ha pogut crear");
				}
				
				break;
				
			case 2:
				
				System.out.println("Quin arxiu vol ordenar?");
				
				File sortingFile = new File(scan.nextLine());
				
				if (sortingFile.exists()) {
					
					
					
				}else {
					
					
					
				}
				
				break;
			default:
				
				System.out.println("Opció invàlida");
				
				break;
			}
			
		}
		
		
		scan.close();
	}

	private static void writeFile(File file) throws IOException {
		
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		int numbersAmount = (int) Math.round(Math.random() * N_MAX + N_MIN);
		
		for (int i = 0; i < numbersAmount; i++) {
			
			String stringNumber = "" + (int) Math.round(Math.random() * N_VALUE_MAX + N_VALUE_MIN);
			
			bufferedWriter.write(stringNumber);
			bufferedWriter.newLine();
		}
		
		bufferedWriter.close();
		fileWriter.close();
	}

	private static void createFolders(File file) {

		String foldersPath = file.getPath().substring(0, file.getPath().lastIndexOf(File.separator));
		File dirs = new File(foldersPath);
		dirs.mkdirs();
	}

	private static boolean foldersReady(File file) {
		
		if (file.getPath().lastIndexOf(File.separator) != -1) {
			String foldersPath = file.getPath().substring(0, file.getPath().lastIndexOf(File.separator));
			File dirs = new File(foldersPath);
			if(dirs.exists()) {
				
				return true;
			}else {
				return false;
			}
		}else {
			
			return true;
			
		}
	}

}
