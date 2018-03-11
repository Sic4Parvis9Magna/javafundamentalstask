package epamtasks.oop.t04;

import epamtasks.oop.t01.Stationery;

import java.util.Comparator;

public class SortByPrice implements Comparator<Stationery> {
    @Override
    public int compare(Stationery tool1, Stationery tool2) {
       return Double.valueOf(tool1.getPrice()).compareTo(tool2.getPrice());
    }
}
