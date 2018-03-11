package epamtasks.oop.t01;

public class Pencil extends Stationery {
    private final Color color;
    private double thickness;

   public Pencil(){
        name = "PENCIL";
        color = Color.BLACK;
    }

    public Pencil setThickness(double thickness) {
        this.thickness = thickness;
        return this;
    }
    public Pencil setPrice(double price){
        this.price = price;
        return this;
    }

    public Color getColor() {
        return color;
    }
    public double getThickness() {
        return thickness;
    }


}
