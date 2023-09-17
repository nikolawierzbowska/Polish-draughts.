import java.io.IOException;

public class Main {
    public static void main(String[] args) {

    Board newBoard = new Board();
    int n = newBoard.askSize();
    newBoard.createBoard(n);
//    newBoard.toStr(n);
    }
}
