package epamtasks.oop.t01;

import java.util.Objects;

public class Eraser extends Stationery {
    private boolean canErasePen;

   public Eraser(){
        name = "ERASER";

    }

    public Eraser setCanErasePen(boolean canErasePen) {
        this.canErasePen = canErasePen;
        return this;
    }
    public Eraser setPrice(double price){
       this.price = price;
       return this;
    }


    public boolean isCanErasePen() {
        return canErasePen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Eraser)) return false;
        Eraser eraser = (Eraser) o;
        return canErasePen == eraser.canErasePen;
    }

    @Override
    public int hashCode() {

        return Objects.hash(canErasePen);
    }
}
