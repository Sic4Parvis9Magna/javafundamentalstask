package epamtasks.oop.t01;

import java.util.Objects;

public class Pen implements Stationery {
    private double price;
    private Color color;
    private int thickness;

    public Pen(){}

    public double getPrice() {
        return price;
    }

    public Color getColor() {
        return color;
    }

    public int getThickness() {
        return thickness;
    }

    public void setPrice(double price) {
        this.price = price;
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
