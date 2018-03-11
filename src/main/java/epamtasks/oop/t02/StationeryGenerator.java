package epamtasks.oop.t02;

import epamtasks.oop.t01.*;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.apache.logging.log4j.LogManager.*;

public class StationeryGenerator {
   private StationeryGenerator(){}
    private static final Logger log = getLogger(StationeryGenerator.class);
    private static final Random r = new Random();

    public static List<Stationery> generateStationery(int numberOfTools){
        ArrayList<Stationery> result = new ArrayList<>();
        Random r = new Random();
        for (int i =0; i<numberOfTools;i++){

            switch (r.nextInt(5)) {
                case 0: {
                    result.add(getRandomEraser());
                    break;
                }
                case 1:{
                    result.add(getRandomPaper());
                    break;
                }
                case 2:{
                    result.add(getRandomPen());
                    break;
                }
                case 3:{
                    result.add(getRandomPencil());
                    break;
                }
                case 4:{
                    result.add(getRandomRuler());
                    break;
                }
                default:  break;
            }

        }
        return result;
    }

    public static Eraser getRandomEraser(){
        Eraser eraser =  new Eraser();
        eraser.setCanErasePen((r.nextBoolean())).setPrice(r.nextDouble()+5*10);
        return eraser;
    }

    public static Paper getRandomPaper(){
        Paper paper = new Paper();
        paper.setLength(r.nextDouble()*200+15)
                .setWidth(r.nextDouble()*200+25)
                .setPrice(r.nextDouble()*20+10);
        return paper;
    }
    public static Pen getRandomPen(){
        Pen pen = new Pen();
        pen.setPrice(r.nextDouble()*300+50).setThickness(r.nextDouble()+0.5);
        return pen;
    }
    public static Pencil getRandomPencil(){
        Pencil pencil = new Pencil();
        pencil.setThickness(r.nextDouble()+0.5)
                .setPrice(r.nextDouble()*20+10);
        return pencil;
    }
    public static Ruler getRandomRuler(){
        Ruler ruler = new Ruler();
        ruler.setLength(r.nextDouble()*20+10)
                .setPrice(r.nextDouble()*10+15);
        return ruler;
    }


    public static void printGeneratedTools(List<Stationery> tools){
        for (Stationery stationery:tools){
            log.info("tool {} with price {} ",stationery.getName(),stationery.getPrice());

        }
    }


}
