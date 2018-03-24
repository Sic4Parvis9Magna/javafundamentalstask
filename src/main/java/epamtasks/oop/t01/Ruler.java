package epamtasks.oop.t01;

import java.util.Objects;

public class Ruler extends Stationery {
    private double length;
    private String units;
    public Ruler(){
        name = "RULER";
        length = 10;
        units = "cm";
    }

    public Ruler setLength(double length) {
        this.length = length;
        return this;
    }
    public Ruler setPrice(double price) {
        this.price = price;
        return this;
    }
    public Ruler setUnits(String units) {
        this.units = units;
        return this;
    }

    public double getLength() {
        return length;
    }
    public String getUnits() {
        return units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ruler)) return false;
        Ruler ruler = (Ruler) o;
        return Double.compare(ruler.length, length) == 0 &&
                Objects.equals(units, ruler.units);
    }

    @Override
    public int hashCode() {

        return Objects.hash(length, units);
    }
}
