package epamtasks.oop.t04;


import epamtasks.oop.t03.BeginnersToolkit;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SortedTools {
    public static void main(String[] args) {
        BeginnersToolkit toolkit1 = new BeginnersToolkit();
        BeginnersToolkit toolkit2 = new BeginnersToolkit();
        BeginnersToolkit toolkit3 = new BeginnersToolkit();
        log.info("BEFORE SORT BY PRICE");
        toolkit1.printContent();
        toolkit1.getToolkit().sort(new SortByPrice());
        log.info("AFTER SORT BY PRICE");
        toolkit1.printContent();

        log.info("BEFORE SORT BY NAME");
        toolkit2.printContent();
        toolkit2.getToolkit().sort(new SortByName());
        log.info("AFTER SORT BY NAME");
        toolkit2.printContent();

        log.info("BEFORE SORT BY NAME AND THEN BY PRICE");
        toolkit3.printContent();
        toolkit3.getToolkit().sort(new SortByName().thenComparing(new SortByPrice()));
        log.info("AFTER SORT BY NAME AND THEN BY PRICE");
        toolkit3.printContent();






    }



}
