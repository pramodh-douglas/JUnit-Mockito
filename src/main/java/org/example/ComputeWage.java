package org.example;

import java.util.Scanner;

public class ComputeWage {
    private String name;
    private double total;
    private int hours;
    private String emptype;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype;
    }

    public void acceptData(){
        boolean inputValid = false;
        Scanner key = new Scanner(System.in);
        System.out.println("Enter the name: ");
        name = key.nextLine();

        System.out.println("Enter the emp type: ");
        emptype = key.nextLine();

        while (!inputValid) {
            System.out.println("Enter the hours: ");
            String input = key.next();

            try {
                hours = Integer.parseInt(input);
                inputValid=true;
            } catch (NumberFormatException ex) {
                System.out.println("You didnt enter a valid number. ");
            }
        }
    }

    public double computeWage() {
        if(this.hours > 40) {
            total = (40*15) + (16.5 * (hours - 40));
        } else {
            total = hours * 15;
        }
        return total;
    }

    public void display(){
        System.out.println("The total wage of " + this.name + " is " + this.total);
    }

    public void addBonus(BonusInterface samp) {
        double totWage = getTotal() + samp.bonus(getEmptype());
        setTotal(totWage);


    }
}
