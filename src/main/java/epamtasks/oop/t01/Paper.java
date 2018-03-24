package epamtasks.oop.t01;

import java.util.Objects;

public class Paper extends Stationery {

    private double width;
    private double length;

   public Paper(){
    name = "PAPER";
    width = 210;
    length = 297;
    }

    public Paper setWidth(double width) {
        this.width = width;
        return this;
    }
    public Paper setLength(double length) {
        this.length = length;
        return this;
    }
    public Paper setPrice(double price) {
        this.price = price;
        return this;
    }

    public double getLength() {
        return length;
    }
    public double getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paper)) return false;
        Paper paper = (Paper) o;
        return Double.compare(paper.width, width) == 0 &&
                Double.compare(paper.length, length) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(width, length);
    }
}
