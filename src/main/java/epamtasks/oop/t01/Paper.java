package epamtasks.oop.t01;

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


}
