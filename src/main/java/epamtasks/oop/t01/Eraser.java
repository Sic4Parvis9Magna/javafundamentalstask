package epamtasks.oop.t01;

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
}
