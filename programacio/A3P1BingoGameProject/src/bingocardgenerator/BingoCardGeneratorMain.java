package bingocardgenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author macervero
 */
public class BingoCardGeneratorMain {
    
    final static String BASE_PATH = "C:\\Users\\Usuario\\Desktop\\A_GITHUB\\dam\\programacio\\A3P1BingoGameProject";
    
    final static int NUM_PLAYERS = 4;
    
    final static int CARD_S_NROWS = 5;
    final static int CARD_S_NCOLS = 5;
    final static int CARD_S_CELLS = CARD_S_NROWS * CARD_S_NCOLS;
    
    final static int CARD_R_MAX_BLANKS = 4;
    final static int CARD_R_NROWS = 3;
    final static int CARD_R_NCOLS = 9;
    final static int CARD_R_CELLS = CARD_R_NROWS * CARD_R_NCOLS;
    
    final static int CARD_MAX_NUM = 99;
    
    final static int SQUARE_OPTION = 1;
    final static int RECTANGULAR_OPTION = 2;
    
    final static int CONSTANT_PLAYERS = 1;
    final static int RANDOM_PLAYERS = 2;

    @SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        PrintWriter writer;
        String playersFilename, cardsFilename;
        String players[];
        int option = 0, gameType = 0, nplayers;
        
        System.out.println("Introdueix el fitxer amb els jugadors de la llar "
                + "de jubilats");
        System.out.print("Fitxer: ");
        playersFilename = BASE_PATH + "/" + keyboard.nextLine();
        
        players = BingoCardGeneratorMain.loadPlayers(playersFilename);
        if(players != null) {
            System.out.println("Introdueix el nom del fitxer on vols "
                    + "guardar els cartrons de Bingo");
            System.out.print("Fitxer: ");
            cardsFilename = BASE_PATH + "/" + keyboard.nextLine();
            
            try {
                writer = new PrintWriter(cardsFilename);
            } catch(FileNotFoundException fnfe) {
                System.out.println("No s'ha trobat el fitxer!");
                return;
            }
                    
            do {
                System.out.println("Quin tipus de cartró de Bingo vols?");
                System.out.println("\t" + SQUARE_OPTION + ". Quadrat (5x5) sense forats");
                System.out.println("\t" + RECTANGULAR_OPTION + ". Rectangular (3x9) amb 12 forats");
                System.out.print("Opció: ");
                option = keyboard.nextInt();
                if(option != SQUARE_OPTION && option != RECTANGULAR_OPTION) System.out.println("Error, torna a introduir el valor");
            } while(option != SQUARE_OPTION && option != RECTANGULAR_OPTION);
            
            do {
                System.out.println("\nVols una partida de 4 jugafors o aleatòria?");
                System.out.println("\t" + CONSTANT_PLAYERS + ". Partida de 4 jugadors");
                System.out.println("\t" + RANDOM_PLAYERS + ". Partida aleatòria");
                System.out.print("Opció: ");
                gameType = keyboard.nextInt();
                if(gameType != CONSTANT_PLAYERS && gameType != RANDOM_PLAYERS) System.out.println("Error, torna a introduir el valor");
            } while(gameType != CONSTANT_PLAYERS && gameType != RANDOM_PLAYERS);
            keyboard.nextLine();
            
            switch(option) {
                case SQUARE_OPTION:
                    BingoCardGeneratorMain.generateSquareCard(players, gameType, writer);
                    break;
                case RECTANGULAR_OPTION:
                    BingoCardGeneratorMain.generateRectangularCard(players, gameType, writer);
                    break;
                default:
                    System.out.println("Opció incorrecta");
            }
            writer.close();
        }
        
        keyboard.close();
    }
    
    @SuppressWarnings("resource")
	static String[] loadPlayers(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            
            if(line == null) return null;
            
            int capacity = Integer.parseInt(line);
            int nplayers = 0;
            String players[] = new String[capacity];
            
            do {
                players[nplayers] = reader.readLine();
                nplayers++;
            }while(nplayers < capacity);
            
            reader.close();
            fr.close();
            
            return players;
            
        } catch(FileNotFoundException fnfe) {
            System.err.println("Fitxer no trobat!");
        } catch(IOException ioe) {
            System.err.println("Error d'E/S");
        }
        
        return null;
    }
    
    static void generateSquareCard(String players[], int gameType, PrintWriter writer) {
        Random rnd = new Random();
        int nplayers;
        
        if(gameType == CONSTANT_PLAYERS) nplayers = BingoCardGeneratorMain.setPlayers(players, writer);
        else nplayers = BingoCardGeneratorMain.setPlayersRandom(players, writer);
        
        writer.append("#Bingo cards size\n");
        writer.append(CARD_S_NROWS + " " + CARD_S_NCOLS + "\n");
        
        while(nplayers > 0) {
            Set<Integer> card = new HashSet<>();
            while(card.size() < CARD_S_CELLS) {
                card.add(rnd.nextInt(CARD_MAX_NUM) + 1);
            }
            
            writer.append("#Bingo card " + nplayers + "\n");
            int col = 0;
            for(Integer num: card) {
                writer.print(num);
                if(col > 0 && (col%CARD_S_NCOLS) == CARD_S_NCOLS-1) writer.append("\n");
                else writer.append("\t");
                
                col++;
            }
            writer.append("\n");
            
            nplayers--;
        }
    }
    
    static void generateRectangularCard(String players[], int gameType, PrintWriter writer) {
        Random rnd = new Random();
        int nplayers, avoidNum, row, col, num, val, blanks, blankCol;
        
        if(gameType == CONSTANT_PLAYERS) nplayers = BingoCardGeneratorMain.setPlayers(players, writer);
        else nplayers = BingoCardGeneratorMain.setPlayersRandom(players, writer);
        
        writer.append("#Bingo cards size\n");
        writer.append(CARD_R_NROWS + " " + CARD_R_NCOLS + "\n");
        
        while(nplayers > 0) {
            int card[][] = new int [CARD_R_NROWS][CARD_R_NCOLS];
            Set<Integer> values = new HashSet<>();
            
            avoidNum = rnd.nextInt(10);
            
            row = 0;
            col = 0;
            num = 0;
            while(values.size() < CARD_R_CELLS) {
                if(num == avoidNum) {
                    num++;
                }
                
                val = rnd.nextInt(10) + num * 10;
                if(values.add(val)) {
                    card[row][col] = val;
                    col++;
                    num++;
                }
                if(col == CARD_R_NCOLS) {
                    num = 0;
                    col = 0;
                    row++;
                }
            }
            
            for(row=0; row<CARD_R_NROWS; row++) {
                blanks = 0;
                while(blanks < CARD_R_MAX_BLANKS) {
                    blankCol = rnd.nextInt(CARD_R_NCOLS);
                    if(card[row][blankCol] != 0) {
                        card[row][blankCol] = 0;
                        blanks++;
                    }
                }
            }

            writer.append("#Bingo card " + nplayers + "\n");
            for(row=0; row<CARD_R_NROWS; row++) {
                for(col=0; col<CARD_R_NCOLS; col++) {
                    if(card[row][col] == 0) writer.print("@");
                    else writer.print(card[row][col]);
                    if(col < CARD_R_NCOLS - 1) writer.append("\t");

                }
                writer.append("\n");
            }
            writer.append("\n");

            nplayers--;
        }
    }
    
    static int setPlayers(String players[], PrintWriter writer) {
        Random rnd = new Random();
        Set<String> playersName = new HashSet<>();
        int pos;
        while(playersName.size() < NUM_PLAYERS) {
            pos = rnd.nextInt(players.length);
            playersName.add(players[pos]);
        }
        
        writer.println("#Number of players");
        writer.println(playersName.size());
        writer.println("#Players");
        for(String player: playersName) {
            writer.println(player);
        }
        writer.print("\n");
        
        return NUM_PLAYERS;
    }
    
    static int setPlayersRandom(String players[], PrintWriter writer) {
        Random rnd = new Random();
        final int MAX_PLAYERS = Math.min(8, players.length);
        int nplayers = 0;
        while(nplayers<2 || nplayers>MAX_PLAYERS) {
            nplayers = rnd.nextInt(MAX_PLAYERS + 1);
        }
        
        Set<String> playersName = new HashSet<>();
        int pos;
        while(playersName.size() < nplayers) {
            pos = rnd.nextInt(MAX_PLAYERS);
            playersName.add(players[pos]);
        }
        
        writer.println("#Number of players");
        writer.println(playersName.size());
        writer.println("#Players");
        for(String player: playersName) {
            writer.println(player);
        }
        writer.print("\n");
        
        return nplayers;
    }
    
}
