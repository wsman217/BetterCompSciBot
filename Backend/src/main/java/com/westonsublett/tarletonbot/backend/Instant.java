package com.westonsublett.tarletonbot.backend;

public class Instant {

    private int seconds;

    public Instant(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "00:00:" + this.seconds;
    }

    public Instant add(Instant add) {
        add.seconds += this.seconds;
        return add;
    }

    // From here on is code that you probably shouldn't analyze too much
    //just read the comments and try to gather a general idea.

    public void printUsingThis() {
        System.out.println(this.seconds);
    }

    public void printUsingReference(Instant instant) {
        System.out.println(instant.seconds);
    }

    public static void main(String[] args) {
        //So first we create 2 instances and assign them 2 different values
        Instant instant1 = new Instant(5);
        Instant instant2 = new Instant(10);

        //We use the first instance to access both of these methods. The first method
        //uses the "this" keyword and it prints the value of seconds associated with instance1
        //so that shows that this.seconds in instance1 refers to the value of 5
        //in the next method you pass instant2 which uses instant.seconds which is just getting
        //the value of seconds from instant2 and printing it using the object of instant1
        instant1.printUsingThis();
        instant1.printUsingReference(instant2);
    }
}
