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

//        this.isGameContinue = true;
//        while (isGameContinue) {

        playRound();


        movePawn(newboard.getBoard());
//            player = player == 1 ? 2 : 1;
//        }

    }

    public void playRound() {
        newboard.printBoard(newboard.getBoard());


    }

    public static Scanner putCoordinates() {
        System.out.println("Give coordinates: ex. 1A");
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }

    public static String wrongCoordinatesMessage() {
        return "Wrong input, try again";
    }


    public static boolean letterOrDigitCoordinate(String coordinates, int a, int b) {
        if (Character.isLetter(coordinates.charAt(a)) ||
                (Character.isLetter(coordinates.charAt(b)) && Character.isLetter(coordinates.charAt(a)))
                || (Character.isDigit(coordinates.charAt(a)) && Character.isDigit(coordinates.charAt(b)))) {
            System.out.println(wrongCoordinatesMessage());
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
                System.out.println(wrongCoordinatesMessage());
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
                        System.out.println(wrongCoordinatesMessage());
                    }
                } else {
                    System.out.println(wrongCoordinatesMessage());
                }
            } else if (coordinates.length() == 3) {
                String xCord = coordinates.substring(0, 2);
                yCoordinate = (int) coordinates.toUpperCase().charAt(2) - 65;
                if (Integer.parseInt(xCord) <= sizeBoard && yCoordinate <= sizeBoard) {
                    xCoordinate = Integer.parseInt(xCord) - 1;
                    yCoordinate = (int) coordinates.toUpperCase().charAt(2) - 65;
                    if ((xCoordinate + yCoordinate) % 2 != 0) {
                        validCoordinates = true;
                    } else {
                        System.out.println(wrongCoordinatesMessage());
                    }
                } else {
                    System.out.println(wrongCoordinatesMessage());
                }
            }

        }
        System.out.println(Arrays.toString(new int[]{xCoordinate, yCoordinate}));
        return new int[]{xCoordinate, yCoordinate};
    }


    public int[] chosenCoordinatesOfPawn() {
        return validateCoordinates();
    }

    public int[] CoordinatesOfPawnChange() {
        return validateCoordinates();

    }


    public int[] checkCoordinatesOfFirstPawn() {
        int[] pointedPawn;
        int startXCoordinate;
        int startYCoordinate;
        boolean result;

        do {
            pointedPawn = chosenCoordinatesOfPawn();
            startXCoordinate = pointedPawn[0];
            startYCoordinate = pointedPawn[1];
            result = isFirstCoordinatesOfPawnIsCorrect(startXCoordinate, startYCoordinate);

        } while (!result);
        System.out.println("pierwszy pionek ok");
        return new int[]{startXCoordinate, startYCoordinate};
    }


    public int[] checkSecondCoordinatesToMovePawn() {
        int[] changedPawn;
        int endXCoordinate;
        int endYCoordinate;
        boolean resultSecondCoord;


        do {
            changedPawn = CoordinatesOfPawnChange();
            endXCoordinate = changedPawn[0];
            endYCoordinate = changedPawn[1];
            resultSecondCoord = isSecondCoordinatesOfPawnIsCorrectToMovePawn(endXCoordinate, endYCoordinate);

        } while (!resultSecondCoord);
        System.out.println("drugi pionek ok");
        return new int[]{endXCoordinate, endYCoordinate};

    }


    public boolean isEmptyPlaceToMove(int x, int y) {
        if (newboard.getBoard()[x][y].getColor().equals(Color.BLACK)) {

            if ((x + 1 < newboard.getBoard().length) || (x - 1) > 0 || (y + 1) < newboard.getBoard().length || (y - 1) > 0) {
                if ((y - 1 >= 0 && newboard.getBoard()[x + 1][y - 1] == null) || (y + 1 < newboard.getBoard().length && newboard.getBoard()[x + 1][y + 1] == null)) {
                    System.out.println("black moze zrobic ruch o 1");
                    return true;
                }
            }
        }
        if (newboard.getBoard()[x][y].getColor().equals(Color.WHITE)) {
            if ((x - 1) > 0 || (y - 1) > 0 || (y + 1) < newboard.getBoard().length || (x + 1) < newboard.getBoard().length) {

                if (((y - 1) > 0 && newboard.getBoard()[x - 1][y - 1] == null) || ((y + 1) < newboard.getBoard().length && newboard.getBoard()[x - 1][y + 1] == null)) {
                    System.out.println("biały moze zrobic ruch o 1");
                    return true;
                }
            }
        }
        return false;

    }


    public boolean isBlockedPlaceByTheSameColor(int x, int y) {
        if (newboard.getBoard()[x][y].getColor().equals(Color.BLACK)) {
            if (((y - 1) > 0 && newboard.getBoard()[x + 1][y - 1] == null) || ((y + 1) < newboard.getBoard().length &&
                    newboard.getBoard()[x + 1][y + 1] == null)) {
                return false;
            } else if (((y - 1) > 0 && newboard.getBoard()[x + 1][y - 1].getColor().equals(Color.BLACK)) ||
                    ((y + 1) < newboard.getBoard().length && newboard.getBoard()[x + 1][y + 1].getColor().equals(Color.BLACK))) {
                System.out.println("przed black stoi black");
                return true;
            }
        }
        if (newboard.getBoard()[x][y].getColor().equals(Color.WHITE)) {
            if (((y - 1) > 0 && newboard.getBoard()[x - 1][y - 1] == null) || ((y + 1) < newboard.getBoard().length && newboard.getBoard()[x - 1][y + 1] == null)) {
                return false;
            } else if (((y - 1) > 0 && newboard.getBoard()[x - 1][y - 1].getColor().equals(Color.WHITE)) ||
                    ((y + 1) < newboard.getBoard().length && newboard.getBoard()[x - 1][y + 1].getColor().equals(Color.WHITE))) {
                System.out.println("przed white stoi white");
                return true;

            }
        }
        return false;
    }


    public boolean isPossibleHit(int x, int y) {
        if (newboard.getBoard()[x][y].getColor().equals(Color.BLACK)) {
            if ((newboard.getBoard()[x + 1][y - 1].getColor().equals(Color.WHITE) && newboard.getBoard()[x + 2][y - 2] == null) ||
                    (newboard.getBoard()[x + 1][y + 1].getColor().equals(Color.WHITE) && newboard.getBoard()[x + 2][y + 2] == null)) {
                System.out.println("możliwe zbicie 1 pionka");
                return true;
            }
        }
        if (newboard.getBoard()[x][y].getColor().equals(Color.WHITE)) {
            if ((newboard.getBoard()[x - 1][y - 1].getColor().equals(Color.BLACK) && newboard.getBoard()[x - 2][y - 2] == null) ||
                    (newboard.getBoard()[x - 1][y + 1].getColor().equals(Color.BLACK) && newboard.getBoard()[x - 2][y + 2] == null)) {
                System.out.println("możliwe zbicie 1 pionka");
                return true;
            }
        }
        return false;
    }


    public boolean isFirstCoordinatesOfPawnIsCorrect(int x, int y) {
        boolean isValidateMove = false;
//        boolean isHit = isPossibleHit(x,y);


        while (!isValidateMove) {

            if (newboard.getBoard()[x][y] != null) {
                if (isBlockedPlaceByTheSameColor(x, y)) {
                    return false;
                } else if (isEmptyPlaceToMove(x, y)) {
                    return true;

                }

            }
            if (newboard.getBoard()[x][y] == null) {
                System.out.println("This place is invalid, try again");
                break;
            }

        }
        return isValidateMove;

    }

    public boolean isEmptyPlaceToPutPawn(int x, int y) {
        if (((y - 1) > 0 && (x + 1) < newboard.getBoard().length && newboard.getBoard()[x + 1][y - 1] != null) ||
                ((y + 1) < newboard.getBoard().length && (x + 1) < newboard.getBoard().length
                 && newboard.getBoard()[x + 1][y + 1] != null)) {
            return true;
        }

        if (((y - 1) > 0 && (x - 1) >0 && newboard.getBoard()[x - 1][y - 1] != null) ||
                ((y + 1) < newboard.getBoard().length && (x - 1) >0  &&
                newboard.getBoard()[x - 1][y + 1] != null)) {
            return true;
        }
        return false;
    }


    public boolean isSecondCoordinatesOfPawnIsCorrectToMovePawn(int x, int y) {
        boolean isValidateMove = false;

        while (!isValidateMove) {

            if (newboard.getBoard()[x][y] == null &&  isEmptyPlaceToPutPawn(x,y) ) {
                System.out.println("ok wolne miejsce ");
                return true;
            } else if (newboard.getBoard()[x][y] != null) {
                return false;
            }


        }
        return isValidateMove;
    }


//    public void checkForWinner() {
//        Point source = new Point(1,2);
//        Point source1 = new Point(1,2);
//    }


    public void movePawn(Pawn[][] board) {
        int[] firstCoordinates = checkCoordinatesOfFirstPawn();
        int[] secondCoordinates = checkSecondCoordinatesToMovePawn();
        newboard.removePawn(board, firstCoordinates[0],firstCoordinates[1] );
//      jak wydrukowac ze zmienionym nowym pionkiem
//

    }


}
