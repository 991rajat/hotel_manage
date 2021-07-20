package com.example.hotel.exception;

public class RoomNotAvailableException extends Exception{
    public RoomNotAvailableException() {
        super();
    }

    public RoomNotAvailableException(String s) {
        super(s);
    }

    public RoomNotAvailableException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RoomNotAvailableException(Throwable throwable) {
        super(throwable);
    }

    protected RoomNotAvailableException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
