public class Pawn {
    private Color colorPawn;
    Coordinates coordinates;


    public Pawn(Color color, int x, int y){
        colorPawn =color;
        coordinates = new Coordinates(x,y);


    }
    public Color getColor() {
        return colorPawn;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }



    public boolean validateMove(int x, int y) {
        if (coordinates.getX()==x && coordinates.getY()==y) {
            System.out.println("ok");
            return true;
        }
        return false;
    }
}
