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
            newboard.movePawn();
            player = player ==1 ?2:1;
        }

    }

    public void playRound(){
        newboard.printBoard(newboard.getBoard());


    }


    public static int[] insertCoordinates(){
        System.out.println("Give coordinates: ex. A1");
        Scanner scanner = new Scanner(System.in);
        int xCoordinate;
        int yCoordinate;
        String coordinates = scanner.nextLine();
        xCoordinate =(int)coordinates.toUpperCase().charAt(0)-65;

        String yCord = coordinates.substring(1);
        yCoordinate = Integer.parseInt(yCord)-1;

        System.out.println(Arrays.toString(new int[]{xCoordinate, yCoordinate}));

        return new int[]{xCoordinate,yCoordinate};
    }

    public void  tryToMakeMove(){





    }



    public void checkForWinner(){

    }


}
