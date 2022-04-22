package exercises;

import java.io.File;
import java.util.Scanner;

public class A5E1Exercise1Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Indiqui el directori:");
		String dpath = scan.nextLine();
		
		File d = new File(dpath);
				
		if (d.exists() && d.isDirectory()) {
			
			int dirCount = 0;
			
			for (File file : d.listFiles()) {
				
				if (file.isDirectory()) {
					dirCount++;
				}
				
			}
			
			System.out.println("El directori " + dpath + " conté " + dirCount + " carpetes, i són les següents:");
			
			for (File file : d.listFiles()) {
				
				if (file.isDirectory()) {
					System.out.println(file);
				}
				
			}
			
			int fileCount = 0;
			
			for (File file : d.listFiles()) {
				
				if (!file.isDirectory()) {
					fileCount++;
				}
				
			}
			
			System.out.println("El directori " + dpath + " conté " + fileCount + " fitxers, i són els següents:");
			
			for (File file : d.listFiles()) {
				
				if (!file.isDirectory()) {
					System.out.println(file.toString() + " (" + file.length()/1000 + " KB)");
				}
				
			}
			
		}else {
			
			System.out.println("No s'ha trobat el directori.");
			
		}
		
		scan.close();
		
	}

}
