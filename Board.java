import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.System;


public class Board {
    private final String messageOfSize = "Give the number of board size from 10 to 20: ";
    int n;
    public Pawn[][] board;

    public Pawn[][] getBoard() {
        return board;
    }

    Board() {
        int n = askSize();
        board = setPawns(new Pawn[n][n], n);
    }

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
        return alphabet;
    }

    public Pawn[][] whitePawns(Pawn[][] board, int n) {
        int whitePawnsSum = 2 * n;
        for (int i = 0; i < n; i++) {
            if ((i + 1) % 2 == 0) {
                for (int j = 0; j < n; j += 2) {
                    if (whitePawnsSum > 0) {
                        board[i][j] = new Pawn(Color.WHITE, i, j);
                        whitePawnsSum--;
                    }
                }
            } else {
                for (int j = 1; j < n; j += 2) {
                    if (whitePawnsSum > 0) {
                        board[i][j] = new Pawn(Color.WHITE, i, j);
                        whitePawnsSum--;
                    }
                }
            }
        }
        return board;
    }

    public Pawn[][] blackPawns(Pawn[][] board, int n) {
        int blackPawnsSum = 2 * n;
        for (int i = n - 1; i >= 0; i--) {
            if ((i - 1) % 2 == 0) {
                for (int j = 0; j < n; j += 2) {
                    if (blackPawnsSum > 0) {
                        board[i][j] = new Pawn(Color.BLACK, i, j);
                        blackPawnsSum--;
                    }
                }
            } else {
                for (int j = 1; j < n; j += 2) {
                    if (blackPawnsSum > 0) {
                        board[i][j] = new Pawn(Color.BLACK, i, j);
                        blackPawnsSum--;
                    }
                }
            }
        }
        return board;
    }

    public Pawn[][] setPawns(Pawn[][] board, int n) {
        return whitePawns(blackPawns(board, n), n);
    }


    public void printBoard(Pawn[][] board) {
        n = board.length;
        StringBuilder strBuilderBoard = new StringBuilder();
        strBuilderBoard.append(printLetters(n));
        for (int i = 0; i < n; i++) {
            strBuilderBoard.append("    ");
            for (int z = 0; z < n; z++) {
                    strBuilderBoard.append("----");
                }
            strBuilderBoard.append("\n");
            if (i<9) {
                strBuilderBoard.append(" "+"0" + (i + 1) +" "+ "|");
            }else {
                strBuilderBoard.append(" "+(i + 1) +" "+ "|");
            }
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0) {
                    strBuilderBoard.append(" " + "-" + " " + "|");
                } else if (board[i][j] == null) {
                    strBuilderBoard.append(" " + "\u0020"  + " |");
                } else {
                    strBuilderBoard.append(" " + board[i][j].getColor().symbol + " " + "|");
                }

            }
            if (i<9) {

                strBuilderBoard.append(" "+"0" + (i + 1) +" ");
            }else {
                strBuilderBoard.append(" "+(i + 1) + " ");
            }


            strBuilderBoard.append("\n");
        }
        strBuilderBoard.append("    ");
        for (int z = 0; z < n; z++) {
            strBuilderBoard.append("----");
        }
        strBuilderBoard.append("\n");
        strBuilderBoard.append(printLetters(n));
        System.out.println(strBuilderBoard.toString());
    }

    private String printLetters(int n) {
        String result = "    |";
        List<String> alphabet = letters(n);
        for (String letter : alphabet) {
            result +=" "+ letter + " "+"|";
        }
        result += " \n";
        return result;
    }







}




