package com.aip.lld.elevator;

class Request {
    final int floor;
    final Direction direction; // null for INTERNAL
    final RequestType type;

    Request(int floor, Direction direction, RequestType type) {
        this.floor = floor;
        this.direction = direction;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Request{" +
                "floor=" + floor +
                ", direction=" + direction +
                ", type=" + type +
                '}';
    }
}
