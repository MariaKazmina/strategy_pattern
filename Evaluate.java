package com.company;

public class Evaluate extends Calculator {
    public Evaluate(double x, double y) {
        operation = new Division();
        operation.calcul(x, y);
    }
}
