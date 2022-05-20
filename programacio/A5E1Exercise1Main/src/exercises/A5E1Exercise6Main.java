package exercises;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A5E1Exercise6Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("---- Gestió dades del camp informàtic ----\r\n"
				+ "0. Sortir\r\n"
				+ "1. Consultar els salaris amb més de 5 xifres en €\r\n"
				+ "2. Consultar els salaris dels treballs relacionats amb \"3D\" o \"Research\"\r\n"
				+ "3. Consultar els articles modificats entre el 05/11/2019 i el 27/01/2021\r\n"
				+ "4. Comptabilitzar quants articles parlen de JAVA, C++, Python i SQL");
		
		int option = Integer.parseInt(scan.nextLine());
		
		while(option != 0) {
			
			switch (option) {
			case 1:
				
				ArrayList<String> matchingLines = A5E1Exercise6Main.findSalaries();
				
				System.out.println(matchingLines.toString());
				
				break;

			case 2:
				
				break;
				
			case 3:
				
				break;

			case 4:
				
				break;

			default:
				
				System.out.println("Valor invàlid");
				
				break;
			}
			
			System.out.println("---- Gestió dades del camp informàtic ----\r\n"
					+ "0. Sortir\r\n"
					+ "1. Consultar els salaris amb més de 5 xifres en €\r\n"
					+ "2. Consultar els salaris dels treballs relacionats amb \"3D\" o \"Research\"\r\n"
					+ "3. Consultar els articles modificats entre el 05/11/2019 i el 27/01/2021\r\n"
					+ "4. Comptabilitzar quants articles parlen de JAVA, C++, Python i SQL");
			
			option = Integer.parseInt(scan.nextLine());
			
		}
		
		scan.close();
		
	}

	public static ArrayList<String> findSalaries() {
		
		ArrayList<String> matchingLines = new ArrayList<>();
				
		File salariesFile = new File("docs" + File.separator + "data_science_jobs_salaries.csv");
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		try {
			
			fileReader = new FileReader(salariesFile);
			bufferedReader = new BufferedReader(fileReader);
			
			String currentLine = bufferedReader.readLine();
						
			Pattern pattern2;
			pattern2 = Pattern.compile("\\d{5,},EUR");
			
			Matcher matcher = pattern2.matcher(currentLine);
			
			while (currentLine != null) {
				
				if (matcher.find()) {
					
					System.out.println(currentLine);
					
				}
				
				currentLine = bufferedReader.readLine();
				
				if (currentLine != null) {
					matcher = pattern2.matcher(currentLine);
				}
				
			}
			
			bufferedReader.close();
			fileReader.close();
			
		} catch (IOException e) {
			System.out.println("File not found");
		}
		
		return matchingLines;
		
	}
}
