import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.System;


public class Board {
    String messageOfSize = "Give the number of board size from 10 to 20: ";
    int n;
    public Pawn[][] board;


    public int askSize() {
        do {
            System.out.println(messageOfSize);
            Scanner scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {
                n = scanner.nextInt();

                if (n < 10 || n > 20) {
                    System.out.println("Number is wrong. Try again");
                }
            } else {
                System.out.println("Number is wrong. Try again");
            }
        } while (n < 10 || n > 20);
        System.out.println("Board size: " + n);
        return n;
    }


    public String[][] createBoard(int n) {
        String[][] array = new String[n + 2][n + 2];

        for (int i = 0; i < n+2; i++) {
            if(i>0){
            for (int z = 0; z < n+2; z++) {
                System.out.print(("----"));
            }}

            List<String> alphabet = letters(n);
                for (int j = 0; j < n+2; j++) {

                    array[i][j]=" ";
                    if ((i == 0 && j == 0) || (i == 0 && j == n + 1) || (i == n + 1 && j == 0) || (i == n + 1 && j == n + 1)) {
                       array[i][j] = "  ";
                    } else if ((i == 0 && j > 0 && j < n + 1) || (i == n + 1 && j > 0 && j < n + 1)) {
                        array[i][j] = alphabet.get(j-1);

                    } else if ((j == 0 && i > 0 && i < 10 ) || (j == n + 1 && i > 0 && i < 10 )) {
                        array[i][j] = String.valueOf("0"+i);

                    } else if ((j == 0 && i >=10 ) || (j == n + 1 && i >=10 )) {
                        array[i][j] = String.valueOf(i);
            }

                    if(i<10 && j<n+1){
                        System.out.print(" " +array[i][j] + " " + "|");
                    }else if(i<10 && j>=n+1){
                        System.out.print( " " +array[i][j] + " " );
                    }

                    else if(i>=10 && j<n+1) {
                        System.out.print(" "+array[i][j] + " " + "|");

                    }else if(i>=10 && j>=n+1){
                        System.out.print( " " +array[i][j] + " " );
                    }

                }
            System.out.println();
        }
        return array;

    }


    public List<String> letters(int n) {
        char c;
        int k = 0;

        List<String> alphabet = new ArrayList<>();
        for (c = 'A'; c <= 'Z'; ++c) {
            if (k < n) {
                alphabet.add((String.valueOf(c)));
                k++;
            }
        }
        System.out.println();
        return alphabet;
    }

//  public void placePawns(String[][] board) {
//        int sumOfPawns =2*n;
//        if (numberOfPawn <= )
//  }


}





