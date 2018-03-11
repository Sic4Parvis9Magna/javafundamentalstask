package epamtasks.oop.t01;

import java.util.Objects;

public class Pen extends Stationery {

    private Color color;
    private double thickness;

    public Pen(){
        name = "PEN";
        color = Color.BLACK;
        thickness = 0.7;
    }


    public Pen setColor(Color color) {
        this.color = color;
        return  this;
    }
    public Pen setThickness(double thickness) {
        this.thickness = thickness;
        return  this;
    }
    public Pen setPrice(double price) {
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
        if (!(o instanceof Pen)) return false;
        Pen pen = (Pen) o;
        return thickness == pen.thickness &&
                color == pen.color;
    }

    @Override
    public int hashCode() {

        return Objects.hash(color, thickness);
    }

    @Override
    public String toString() {
        return "Pen{" +
                "price=" + price +
                ", color=" + color +
                ", thickness=" + thickness +
                '}';
    }
}
