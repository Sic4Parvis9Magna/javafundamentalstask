package epamtasks.oop.t02;

import epamtasks.oop.t01.Stationery;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String firstName;
    private String lastName;
    private int id;
    private Position position;
    private List<Stationery> stationeryList;

    private static int employeeCounter =1;

    Employee(){
        firstName = "UNKNOWN";
        lastName = "UNKNOWN";
        id = employeeCounter++;
        position = Position.UNKNOWN;
        stationeryList = new ArrayList<>();
    }

    public Employee setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public Employee setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public Employee setPosition(Position position) {
        this.position = position;
        return this;
    }

    public Employee addStationeryTool(Stationery tool){
        stationeryList.add(tool);
        return this;
    }
    public Employee addAllTools(List<Stationery> tools){
        stationeryList.addAll(tools);
        return this;
    }
    public double getTotalPrice(){
        double totalPrice=0;
        for (Stationery tool:stationeryList){
          totalPrice += tool.getPrice();
        }
        return totalPrice;
    }


    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getId() {
        return id;
    }
    public Position getPosition() {
        return position;
    }
    public List<Stationery> getStationeryList() {
        return stationeryList;
    }
    public static int getEmployeeCounter() {
        return employeeCounter;
    }
}
