package exercises;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
			
			System.out.println("\n~~~~ GESTI� DE FITXERS ~~~~\n" + 
					"0. Sortir \n" +
					"1. Crear un nou fitxer \n" +
					"2. Ordenar d'arxius que cont� \n" +
					"Opci�:");
			
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
				
				System.out.println("En quin arxiu ho vol guardar?");
				
				File outputFile = new File(scan.nextLine());
				
				
				if (sortingFile.exists() && !sortingFile.isDirectory()) {
					
					FileReader fileReader = null;
					
					try {
						fileReader = new FileReader(sortingFile);
					} catch (FileNotFoundException e) {
						System.out.println("No s'ha trobat el fitxer");
					}
					
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					
					try {
						
						ArrayList<Integer> values = new ArrayList<>();
						
						String currentLine = bufferedReader.readLine();
						
						while (currentLine != null) {
							values.add(Integer.parseInt(currentLine));
							currentLine = bufferedReader.readLine();
						}
						
						values.sort(null);
						
						FileWriter fileWriter = new FileWriter(outputFile);
						BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
						
						try {
							
							if (!outputFile.exists()) {
								
								if(!A5E1Exercise2Main.foldersReady(outputFile)) {
									A5E1Exercise2Main.createFolders(outputFile);
								}
								
								outputFile.createNewFile();
								
								Iterator<Integer> it = values.iterator();
								
								while (it.hasNext()) {
									Integer integer = (Integer) it.next();
									String num = "" + integer;
									bufferedWriter.write(num);
									bufferedWriter.newLine();
									
								}
								
							}else {
								System.out.println("El fitxer ja existeix");
							}

						} catch (IOException e) {
							System.out.println("No s'ha pogut crear");
						}
						
						System.out.println(values.toString());
						
						bufferedWriter.close();
						fileWriter.close();
						
					} catch (IOException e) {
						
					}
					
				}else {
					
					
					
				}
				
				break;
			default:
				
				System.out.println("Opci� inv�lida");
				
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
