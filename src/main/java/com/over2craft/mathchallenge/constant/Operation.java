package com.over2craft.mathchallenge.constant;

public enum Operation {
    
    MULTIPLICATION(0, "x"),
    SUBTRACTION(1, "-"),
    ADDITION(2, "+"),
    NONE(3, "");

    private int id;
    private String sign;

    Operation(int id, String sign) {
        this.id = id;
        this.sign = sign;
    }

    public int getId() {
        return id;
    }

    public String getSign() {
        return sign;
    }

    public static Operation findById(int id) {
        for(Operation operation : values()) {
            if(operation.getId() == id) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Not matching operation id");
    }
}