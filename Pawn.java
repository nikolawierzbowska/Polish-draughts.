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




}
