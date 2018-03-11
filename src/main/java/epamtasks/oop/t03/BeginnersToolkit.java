package epamtasks.oop.t03;

import epamtasks.oop.t01.Stationery;
import epamtasks.oop.t02.StationeryGenerator;

import java.util.ArrayList;
import java.util.List;

public class BeginnersToolkit {
   private List<Stationery> toolkit;

   public BeginnersToolkit(){
       toolkit = new ArrayList<>();
       toolkit.add(StationeryGenerator.getRandomPaper());
       toolkit.add(StationeryGenerator.getRandomEraser());
       toolkit.add(StationeryGenerator.getRandomPen());
       toolkit.add(StationeryGenerator.getRandomPencil());
       toolkit.add(StationeryGenerator.getRandomRuler());
   }

    public List<Stationery> getToolkit() {
        return toolkit;
    }
    public void printContent(){
       StationeryGenerator.printGeneratedTools(toolkit);
   }
}
