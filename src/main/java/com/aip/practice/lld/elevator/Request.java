package com.aip.practice.lld.elevator;

import java.util.Objects;

public class Request {
    private int floor;
    private RequestType requestType;

    public Request(int floor, RequestType requestType) {
        this.floor = floor;
        this.requestType = requestType;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Request request)) return false;
        return floor == request.floor && requestType == request.requestType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(floor, requestType);
    }
}

