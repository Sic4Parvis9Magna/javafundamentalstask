package epamtasks.oop.t01;

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
}
