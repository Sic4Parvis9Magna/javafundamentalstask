package epamtasks.oop.t01;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pencil)) return false;
        Pencil pencil = (Pencil) o;
        return Double.compare(pencil.thickness, thickness) == 0 &&
                color == pencil.color;
    }

    @Override
    public int hashCode() {

        return Objects.hash(color, thickness);
    }
}
