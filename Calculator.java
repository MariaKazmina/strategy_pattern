package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private String example;  // строка с примером, которую вводят с клавиатуры
    private double x;
    private double y;
    Operation operation;

    public Calculator() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите выражение:  ");
        this.example = in.nextLine();
        performCalc();
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    //передает выполнение вычислений другому классу
    private void performCalc() {
        try {
            operation = getOperation();
            System.out.println(example + " = " + operation.calcul(x, y));
        } catch (NullPointerException e) {
            System.out.println("Некорректная запись. Повторите ввод снова");
        }
    }

    private Operation getOperation() {
        String oper = parseString();
        switch (oper) {
            case "-":
                operation = new Substraction();
                return operation;
            case "+":
                operation = new Addition();
                return operation;
            case "*":
                operation = new Multiplication();
                return operation;
            case "/":
                operation = new Division();
                return operation;

        }
        return null;
    }

    private String parseString() {
        String op = "";
        Pattern p = Pattern.compile("(\\d+)", Pattern.MULTILINE);
        Pattern r = Pattern.compile("([*+/-])", Pattern.MULTILINE);
        Matcher m = p.matcher(example);
        Matcher rm = r.matcher(example);
        while (m.find()) {
            if (op.isEmpty()) {
                op += m.group();
            }
            if (x == 0) {
                x = Integer.parseInt(op);
                op = "";
            } else {
                y = Integer.parseInt(op);
            }
        }
        op = "";
        while (rm.find()) {
            op += rm.group();
        }
        return op;
    }
}
