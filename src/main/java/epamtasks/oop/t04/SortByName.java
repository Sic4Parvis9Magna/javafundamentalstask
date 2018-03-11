package epamtasks.oop.t04;

import epamtasks.oop.t01.Stationery;

import java.util.Comparator;

public class SortByName implements Comparator<Stationery> {
    @Override
    public int compare(Stationery tool1, Stationery tool2) {
        return tool1.getName().compareToIgnoreCase(tool2.getName());
    }
}
