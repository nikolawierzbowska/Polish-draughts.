import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.System;


public class Board {
    private final String messageOfSize = "Give the number of board size from 10 to 20: ";
    private int sizeBoard;
    private final Pawn[][] board;

    public Pawn[][] getBoard() {
        return board;
    }

    Board() {

        this.sizeBoard = getSize();
        board= new Pawn[sizeBoard][sizeBoard];
        setPawns();

    }

    public int getSize() {
        do {
            System.out.println(messageOfSize);
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                sizeBoard = scanner.nextInt();
                if (sizeBoard < 10 || sizeBoard > 20) {
                    System.out.println("Number is wrong. Try again!");
                }
            } else {
                System.out.println("Number is wrong. Try again!");
            }
        } while (sizeBoard < 10 || sizeBoard > 20);
        System.out.println("Board size: " + sizeBoard);
        return sizeBoard;
    }


    public List<String> getLetters(int n) {
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

    public void getBlackPawns() {
        int blackPawnsSum = 2 * sizeBoard;
        for (int i = 0; i < sizeBoard; i++) {
            if ((i + 1) % 2 == 0) {
                for (int j = 0; j < sizeBoard; j += 2) {
                    if (blackPawnsSum > 0) {
                        board[i][j] = new Pawn(Color.BLACK, i, j);
                        blackPawnsSum--;
                    }
                }
            } else {
                for (int j = 1; j < sizeBoard; j += 2) {
                    if (blackPawnsSum > 0) {
                        board[i][j] = new Pawn(Color.BLACK, i, j);
                        blackPawnsSum--;
                    }
                }
            }
        }

    }

    public void getWhitePawns() {
        int whitePawnsSum = 2 * sizeBoard;
        for (int i = sizeBoard - 1; i >= 0; i--) {
            if ((i - 1) % 2 == 0) {
                for (int j = 0; j < sizeBoard; j += 2) {
                    if (whitePawnsSum > 0) {
                        board[i][j] = new Pawn(Color.WHITE, i, j);
                        whitePawnsSum--;
                    }
                }
            } else {
                for (int j = 1; j < sizeBoard; j += 2) {
                    if (whitePawnsSum > 0) {
                        board[i][j] = new Pawn(Color.WHITE, i, j);
                        whitePawnsSum--;
                    }
                }
            }
        }

    }

    public void  setPawns() {
        getWhitePawns();
        getBlackPawns();
    }


    public void printBoard(Pawn[][] board) {
        sizeBoard = board.length;
        StringBuilder strBuilderBoard = new StringBuilder();
        strBuilderBoard.append(printLetters(sizeBoard));
        for (int i = 0; i < sizeBoard; i++) {
            strBuilderBoard.append("     ");
            for (int z = 0; z < sizeBoard; z++) {
                strBuilderBoard.append("----");
            }
            strBuilderBoard.append("\n");
            if (i < 9) {
                strBuilderBoard.append(" " + "0" + (i + 1) + " " + "|");
            } else {
                strBuilderBoard.append(" " + (i + 1) + " " + "|");
            }
            for (int j = 0; j < sizeBoard; j++) {
                if ((i + j) % 2 == 0) {
                    strBuilderBoard.append(" " + "-" + " " + "|");
                } else if (board[i][j] == null) {
                    strBuilderBoard.append(" " + "\u0020" + " |");
                } else {
                    strBuilderBoard.append(" " + board[i][j].getColor().symbol + " " + "|");
                }
            }
            if (i < 9) {
                strBuilderBoard.append(" " + "0" + (i + 1) + " ");
            } else {
                strBuilderBoard.append(" " + (i + 1) + " ");
            }
            strBuilderBoard.append("\n");
        }
        strBuilderBoard.append("    ");
        for (int z = 0; z < sizeBoard; z++) {
            strBuilderBoard.append("----");
        }
        strBuilderBoard.append("\n");
        strBuilderBoard.append(printLetters(sizeBoard));
        System.out.println(strBuilderBoard);
    }

    private String printLetters(int n) {
        String result = "    |";
        List<String> alphabet = getLetters(n);
        for (String letter : alphabet) {
            result += " " + letter + " " + "|";
        }
        result += " \n";
        return result;
    }


    public void removePawn(Pawn[][] board, int x, int y) {
        board[x][y] = null;

    }












}




