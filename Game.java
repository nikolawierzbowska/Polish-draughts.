import java.util.Arrays;
import java.util.Scanner;


public class Game {
    private Board newboard;
    private boolean isGameContinue;

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void start() {
        int player = 1;
        this.newboard = new Board();
        this.isGameContinue = true;
        while (isGameContinue) {
            playRound();
            if (player == 1) {
                System.out.println("WHITE PAWNS MOVE");
            } else {
                System.out.println("BLACK PAWNS MOVE");
            }
            movePawn(newboard.getBoard());
            player = player == 1 ? 2 : 1;
        }
    }

    private void playRound() {
        newboard.printBoard(newboard.getBoard());
        checkWinner();

    }

    private Scanner putCoordinates() {
        System.out.println("Give coordinates: (ex. 1A/1a)");
        return new Scanner(System.in);
    }

    private static final String WRONG_COORDINATES_MESSAGE() {
        return "Wrong input, try again";
    }

    public boolean letterOrDigitCoordinate(String coordinates, int a, int b) {
        if (Character.isLetter(coordinates.charAt(a)) ||
                (Character.isLetter(coordinates.charAt(b)) && Character.isLetter(coordinates.charAt(a)))
                || (Character.isDigit(coordinates.charAt(a)) && Character.isDigit(coordinates.charAt(b)))) {
            System.out.println(WRONG_COORDINATES_MESSAGE());
            return true;
        }
        return false;

    }

    public int[] validateCoordinates() {
        int sizeBoard = newboard.getBoard().length;
        int xCoordinate = 0;
        int yCoordinate = 0;
        boolean validCoordinates = false;

        while (!validCoordinates) {
            Scanner scanner = putCoordinates();
            String coordinates = scanner.nextLine();

            if (coordinates.length() < 2 || coordinates.length() > 3) {
                System.out.println(WRONG_COORDINATES_MESSAGE());
                continue;
            }

            if (letterOrDigitCoordinate(coordinates, 0, 1)) {
                continue;
            }
            if (coordinates.length() == 2) {
                String xCord = coordinates.substring(0, 1);
                yCoordinate = (int) coordinates.toUpperCase().charAt(1) - 65;
                if (Integer.parseInt(xCord) <= sizeBoard && yCoordinate <= sizeBoard) {
                    xCoordinate = (int) coordinates.charAt(0) - 49;
                    yCoordinate = (int) coordinates.toUpperCase().charAt(1) - 65;
                    if ((xCoordinate + yCoordinate) % 2 != 0) {
                        validCoordinates = true;
                    } else {
                        System.out.println(WRONG_COORDINATES_MESSAGE());
                    }
                } else {
                    System.out.println(WRONG_COORDINATES_MESSAGE());
                }
            } else {
                String xCord = coordinates.substring(0, 2);
                yCoordinate = (int) coordinates.toUpperCase().charAt(2) - 65;
                if (Integer.parseInt(xCord) <= sizeBoard && yCoordinate <= sizeBoard) {
                    xCoordinate = Integer.parseInt(xCord) - 1;
                    yCoordinate = (int) coordinates.toUpperCase().charAt(2) - 65;
                    if ((xCoordinate + yCoordinate) % 2 != 0) {
                        validCoordinates = true;
                    } else {
                        System.out.println(WRONG_COORDINATES_MESSAGE());
                    }
                } else {
                    System.out.println(WRONG_COORDINATES_MESSAGE());
                }
            }
        }
        System.out.println(Arrays.toString(new int[]{xCoordinate, yCoordinate}));
        return new int[]{xCoordinate, yCoordinate};
    }


    private int[] chosenCoordinatesOfPawn() {
        return validateCoordinates();
    }

    private int[] CoordinatesOfPawnChange() {
        return validateCoordinates();

    }


    private int[] checkCoordinatesOfFirstPawn() {
        int[] pointedPawn;
        int startXCoordinate;
        int startYCoordinate;
        boolean result;

        do {
            pointedPawn = chosenCoordinatesOfPawn();
            startXCoordinate = pointedPawn[0];
            startYCoordinate = pointedPawn[1];
            result = isFirstCoordinatesOfPawnIsCorrectToMove(startXCoordinate, startYCoordinate);
        } while (!result);
        return new int[]{startXCoordinate, startYCoordinate};
    }


    public int[] checkSecondCoordinatesToMovePawn(int x2, int y2) {
        int[] changedPawn;
        int endXCoordinate;
        int endYCoordinate;
        boolean resultSecondCoord;

        do {
            changedPawn = CoordinatesOfPawnChange();
            endXCoordinate = changedPawn[0];
            endYCoordinate = changedPawn[1];
            resultSecondCoord = isSecondCoordinatesOfPawnIsCorrectToMovePawn(endXCoordinate, endYCoordinate, x2, y2);

        } while (!resultSecondCoord);
        return new int[]{endXCoordinate, endYCoordinate};
    }


    public boolean isOutOfRangeSizeBoard1(int x, int y, int dx, int dy) {
        return x - dx >= 0 && y - dy >= 0;
    }

    public boolean isOutOfRangeSizeBoard2(int x, int y, int dx, int dy) {
        return x + dx < newboard.getBoard().length && y - dy >= 0;
    }

    public boolean isOutOfRangeSizeBoard3(int x, int y, int dx, int dy) {
        return x - dx >= 0 && y + dy < newboard.getBoard().length;
    }

    public boolean isOutOfRangeSizeBoard4(int x, int y, int dx, int dy) {
        return x + dx < newboard.getBoard().length && y + dy < newboard.getBoard().length;
    }

    public boolean isEmptyPlaceToMove(int x, int y) {
        int dx = 1;
        int dy = 1;

        if (newboard.getBoard()[x][y].getColor().equals(Color.BLACK)) {
            if ((isOutOfRangeSizeBoard2(x, y, dx, dy) && newboard.getBoard()[x + dx][y - dy] == null) ||
                    (isOutOfRangeSizeBoard4(x, y, dx, dy) && newboard.getBoard()[x + dx][y + dy] == null) ||
                    (isOutOfRangeSizeBoard2(x, y, 2, 2) && newboard.getBoard()[x + 2][y - 2] == null) ||
                    (isOutOfRangeSizeBoard4(x, y, 2, 2) && newboard.getBoard()[x + 2][y + 2] == null) ||
                    (isOutOfRangeSizeBoard1(x, y, 2, dy) && newboard.getBoard()[x - 2][y - 2] == null) ||
                    (isOutOfRangeSizeBoard4(x, y, 2, 2) && newboard.getBoard()[x - 2][y + 2] == null)) {
                return true;
            }
        }
        if (newboard.getBoard()[x][y].getColor().equals(Color.WHITE)) {
            if ((isOutOfRangeSizeBoard1(x, y, dx, dy) && newboard.getBoard()[x - dx][y - dy] == null) ||
                    (isOutOfRangeSizeBoard3(x, y, dx, dy) && newboard.getBoard()[x - dx][y + dy] == null) ||
                    (isOutOfRangeSizeBoard1(x, y, 2, 2) && newboard.getBoard()[x - 2][y - 2] == null) ||
                    (isOutOfRangeSizeBoard3(x, y, 2, 2) && newboard.getBoard()[x - 2][y + 2] == null) ||
                    (isOutOfRangeSizeBoard2(x, y, 2, 2) && newboard.getBoard()[x + 2][y - 2] == null) ||
                    (isOutOfRangeSizeBoard4(x, y, 2, 2) && newboard.getBoard()[x + 2][y + 2] == null)) {
                return true;
            }
        }
        return false;
    }


    public boolean isBlockedPlaceByTheSameColor(int x, int y) {
        if (newboard.getBoard()[x][y].getColor().equals(Color.BLACK)) {
            if ((isOutOfRangeSizeBoard2(x, y, 1, 1) && newboard.getBoard()[x + 1][y - 1] == null) ||
                    (isOutOfRangeSizeBoard4(x, y, 1, 1) && newboard.getBoard()[x + 1][y + 1] == null)) {
                return false;
            } else if ((isOutOfRangeSizeBoard2(x, y, 1, 1) && newboard.getBoard()[x + 1][y - 1].getColor().equals(Color.BLACK)) ||
                    (isOutOfRangeSizeBoard4(x, y, 1, 1) && newboard.getBoard()[x + 1][y + 1].getColor().equals(Color.BLACK))) {
                return true;
            }
        }
        if (newboard.getBoard()[x][y].getColor().equals(Color.WHITE)) {
            if (((isOutOfRangeSizeBoard1(x, y, 1, 1) && newboard.getBoard()[x - 1][y - 1] == null) ||
                    (isOutOfRangeSizeBoard3(x, y, 1, 1) && newboard.getBoard()[x - 1][y + 1] == null))) {
                return false;
            } else if ((isOutOfRangeSizeBoard1(x, y, 1, 1) && newboard.getBoard()[x - 1][y - 1].getColor().equals(Color.WHITE)) ||
                    (isOutOfRangeSizeBoard3(x, y, 1, 1) && newboard.getBoard()[x - 1][y + 1].getColor().equals(Color.WHITE))) {
            }

        }
        return false;
    }


    public boolean isPossibleToHitOnlyOne(int x, int y, int x2, int y2) {
        if (newboard.getBoard()[x][y] == null) {
            return false;
        } else if (newboard.getBoard()[(x + x2) / 2][(y + y2) / 2] == null) {
            return false;
        } else if (newboard.getBoard()[x][y].getColor().equals(Color.BLACK)) {
            return newboard.getBoard()[(x + x2) / 2][(y + y2) / 2].getColor().equals(Color.WHITE);
        } else if (newboard.getBoard()[x][y].getColor().equals(Color.WHITE)) {
            return newboard.getBoard()[(x + x2) / 2][(y + y2) / 2].getColor().equals(Color.BLACK);
        }
        return false;
    }


    public boolean isFirstCoordinatesOfPawnIsCorrectToMove(int x, int y) {
        if (newboard.getBoard()[x][y] != null) {
            if (isBlockedPlaceByTheSameColor(x, y)) {
                System.out.println(WRONG_COORDINATES_MESSAGE());
                return false;
            } else if (isEmptyPlaceToMove(x, y)) {
                return true;
            }

        }
        System.out.println(WRONG_COORDINATES_MESSAGE());
        return false;
    }

    public boolean isFirstCoordinatesOfPawnIsCorrectToHit(int x, int y, int x2, int y2) {
        if (newboard.getBoard()[x][y] != null) {
            if (isPossibleToHitOnlyOne(x, y, x2, y2)) {
                return true;
            }
        }
        return false;
    }

        public boolean isEmptyPlaceToPutPawn(int x, int y, int x2, int y2) {
        if (newboard.getBoard()[x][y] == null && newboard.getBoard()[x2][y2].getColor().equals(Color.BLACK)) {
            if (((isOutOfRangeSizeBoard3(x, y, 1, 1) && newboard.getBoard()[x - 1][y + 1] == newboard.getBoard()[x2][y2]) ||
                    (isOutOfRangeSizeBoard1(x, y, 1, 1) && newboard.getBoard()[x - 1][y - 1] == newboard.getBoard()[x2][y2])) ||
                    (isOutOfRangeSizeBoard3(x, y, 2, 2) && newboard.getBoard()[x - 2][y + 2] == newboard.getBoard()[x2][y2]) ||
                    (isOutOfRangeSizeBoard1(x, y, 2, 2) && newboard.getBoard()[x - 2][y - 2] == newboard.getBoard()[x2][y2]) ||
                    (isOutOfRangeSizeBoard4(x, y, 2, 2) && newboard.getBoard()[x + 2][y + 2] == newboard.getBoard()[x2][y2]) ||
                    (isOutOfRangeSizeBoard2(x, y, 2, 2) && newboard.getBoard()[x + 2][y - 2] == newboard.getBoard()[x2][y2])) {
                return true;

            }
        } else if (newboard.getBoard()[x][y] == null && newboard.getBoard()[x2][y2].getColor().equals(Color.WHITE)) {
            if ((isOutOfRangeSizeBoard2(x, y, 1, 1) && newboard.getBoard()[x + 1][y - 1] == newboard.getBoard()[x2][y2]) ||
                    (isOutOfRangeSizeBoard4(x, y, 1, 1) && newboard.getBoard()[x + 1][y + 1] == newboard.getBoard()[x2][y2]) ||
                    (isOutOfRangeSizeBoard2(x, y, 2, 2) && newboard.getBoard()[x + 2][y - 2] == newboard.getBoard()[x2][y2]) ||
                    (isOutOfRangeSizeBoard4(x, y, 2, 2) && newboard.getBoard()[x + 2][y + 2] == newboard.getBoard()[x2][y2]) ||
                    (isOutOfRangeSizeBoard1(x, y, 2, 2) && newboard.getBoard()[x - 2][y - 2] == newboard.getBoard()[x2][y2]) ||
                    (isOutOfRangeSizeBoard3(x, y, 2, 2) && newboard.getBoard()[x - 2][y + 2] == newboard.getBoard()[x2][y2])
            ) {
                return true;
            }
        }
        return false;
    }


    public boolean isSecondCoordinatesOfPawnIsCorrectToMovePawn(int x, int y, int x2, int y2) {
        if (newboard.getBoard()[x][y] == null && isEmptyPlaceToPutPawn(x, y, x2, y2)) {
            return true;
        } else {
            System.out.println(WRONG_COORDINATES_MESSAGE());
            return false;
        }

    }

    public void movePawn(Pawn[][] board) {
        int[] firstCoordinates = checkCoordinatesOfFirstPawn();
        int[] secondCoordinates = checkSecondCoordinatesToMovePawn(firstCoordinates[0], firstCoordinates[1]);
        Color color = newboard.getBoard()[firstCoordinates[0]][firstCoordinates[1]].getColor();

        if (isFirstCoordinatesOfPawnIsCorrectToHit(firstCoordinates[0], firstCoordinates[1], secondCoordinates[0], secondCoordinates[1])) {
            newboard.removePawn(board, firstCoordinates[0], firstCoordinates[1]);
            newboard.removePawn(board, ((firstCoordinates[0] + secondCoordinates[0]) / 2), (firstCoordinates[1] + secondCoordinates[1]) / 2);
            newboard.printNewPawn(board, secondCoordinates[0], secondCoordinates[1], color);

        } else {
            newboard.removePawn(board, firstCoordinates[0], firstCoordinates[1]);
            newboard.printNewPawn(board, secondCoordinates[0], secondCoordinates[1], color);
        }

    }

    public String checkAmountPawns() {
        int whitePawns = 0;
        int blackPawns = 0;
        for (int i = 0; i < newboard.getBoard().length; i++) {
            for (int j = 0; j < i; j++) {
                if (newboard.getBoard()[i][j] != null) {
                    if (newboard.getBoard()[i][j].getColor().equals(Color.WHITE)) {
                        whitePawns += 1;
                    } else if (newboard.getBoard()[i][j].getColor().equals(Color.BLACK)) {
                        blackPawns += 1;
                    }
                }
            }
        }
        if (blackPawns == 0) {
            return "white";
        } else if (whitePawns == 0) {
            return "black";
        } else {
            return "neither";
        }
    }

    public String checkWinner() {
        String result = checkAmountPawns();
        if (result.equals("WHITE")) {
            return "Black won";
        } else if (result.equals("BLACK")) {
            return "White won";
        }
        return "No winner yet";
    }

}
