package com.czy.dvdmanager.enums;

public enum State {

    ISDEL(0,"已删除"),NOTDEL(1,"未删除"),
    ISLEND(2,"已借出"),NOTLEND(3,"未借出");

    private final int value;
    private final String message;

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessage(int value){
        for (State state : values()) {
            if (state.getValue() == value){
                return state.getMessage();
            }
        }

        return null;
    }

    private State(int value, String message){
        this.value = value;
        this.message = message;
    }

}
