package epamtasks.oop.t06;


import java.util.ArrayList;

public class TestBay {
    public static void main(String[] args) {
        AtomicSubmarine mySubmarine1 = new AtomicSubmarine();
        AtomicSubmarine mySubmarine2 = new AtomicSubmarine("Red October");
        AtomicSubmarine mySubmarine3 = new AtomicSubmarine(1984);
        AtomicSubmarine mySubmarine4 = new AtomicSubmarine("Victoria",68532);

       ArrayList <AtomicSubmarine>subArr = new ArrayList<>();
        subArr.add(mySubmarine1);
        subArr.add(mySubmarine2);
        subArr.add(mySubmarine3);
        subArr.add(mySubmarine4);
        for (AtomicSubmarine sub: subArr) {
            sub.startMoving();
            sub.stopMoving();
            sub.startMoving();
            sub.emergencyStop();
        }
    }
}
