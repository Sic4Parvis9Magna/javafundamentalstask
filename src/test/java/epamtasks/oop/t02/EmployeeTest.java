package epamtasks.oop.t02;

import epamtasks.oop.t01.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static epamtasks.oop.t01.Color.*;
import static epamtasks.oop.t02.Position.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void addStationeryToolAndGetStationaryListTest() {
        Employee emp1 =new Employee()
                .setFirstName("Mark")
                .setLastName("Renton")
                .setPosition(MANAGER);
        assertTrue(emp1.getStationeryList().isEmpty());
        Paper paper = new Paper()
                .setLength(12)
                .setPrice(32)
                .setWidth(14);
        Pen pen = new Pen()
                .setPrice(12)
                .setThickness(0.7);
        emp1.addStationeryTool(pen).addStationeryTool(paper);
        assertTrue(emp1.getStationeryList().contains(pen));
        assertTrue(emp1.getStationeryList().contains(paper));

    }

    @Test
    void addAllTools() {
        Pen pen1 = new Pen()
                .setPrice(32)
                .setThickness(0.5)
                .setColor(BLACK);
        Pen pen2 = new Pen()
                .setPrice(24)
                .setThickness(0.5)
                .setColor(GREEN);
        Pen pen3 = new Pen()
                .setPrice(12)
                .setThickness(0.5)
                .setColor(RED);
        List<Stationery> stationeries = new ArrayList<>();
        stationeries.add(pen1);
        stationeries.add(pen2);
        stationeries.add(pen3);

        Employee emp2 = new Employee()
                .setFirstName("Bill")
                .setLastName("Charzton")
                .addAllTools(stationeries);
        assertTrue(emp2.getStationeryList().contains(pen1));
        assertTrue(emp2.getStationeryList().contains(pen2));
        assertTrue(emp2.getStationeryList().contains(pen3));

    }

    @Test
    void getTotalPrice() {
        Employee emp3 =new Employee()
                .setFirstName("Mark")
                .setLastName("Renton")
                .setPosition(MANAGER);
        assertTrue(emp3.getStationeryList().isEmpty());
        Paper paper = new Paper()
                .setLength(12)
                .setPrice(32)
                .setWidth(14);
        Pen pen = new Pen()
                .setPrice(12)
                .setThickness(0.7);
        Pencil pencil = new Pencil()
                .setPrice(10)
                .setThickness(0.3);
        emp3.addStationeryTool(pen)
                .addStationeryTool(paper)
                .addStationeryTool(pencil);
        assertTrue(emp3.getStationeryList().contains(pen));
        assertTrue(emp3.getStationeryList().contains(paper));
        double totalPrice = paper.getPrice() + pen.getPrice()+ pencil.getPrice();
        assertEquals((12+32+10),totalPrice);
        assertEquals(totalPrice,emp3.getTotalPrice());
    }
}