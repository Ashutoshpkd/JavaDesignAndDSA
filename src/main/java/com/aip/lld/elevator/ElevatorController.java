package com.aip.lld.elevator;

import java.util.*;

class ElevatorController {

    private final ElevatorCar elevator;

    private Direction currentDirection = Direction.IDLE;

    private final NavigableSet<Request> upQueue =
            new TreeSet<>((a, b) -> Integer.compare(a.floor, b.floor));

    private final NavigableSet<Request> downQueue =
            new TreeSet<>((a, b) -> Integer.compare(b.floor, a.floor));

    ElevatorController(ElevatorCar elevator) {
        this.elevator = elevator;
        this.elevator.setController(this);
    }

    void submitRequest(Request request) {
        StringBuilder s = new StringBuilder();

        System.out.println("Received " + request);

        if (request.type == RequestType.INTERNAL) {
            addInternalRequest(request);
        } else {
            addExternalRequest(request);
        }

        if (currentDirection == Direction.IDLE) {
            decideInitialDirection();
            moveNext();
            List<Integer> l = new ArrayList<>();
            
        }
    }

    private void addInternalRequest(Request request) {
        if (request.floor > elevator.currentFloor) {
            upQueue.add(request);
        } else if (request.floor < elevator.currentFloor) {
            downQueue.add(request);
        }
    }

    private void addExternalRequest(Request request) {
        if (request.direction == Direction.UP) {
            upQueue.add(request);
        } else {
            downQueue.add(request);
        }
    }

    private void decideInitialDirection() {
        if (!upQueue.isEmpty()) {
            currentDirection = Direction.UP;
        } else if (!downQueue.isEmpty()) {
            currentDirection = Direction.DOWN;
        }
    }

    void onFloorReached(int floor) {
        upQueue.removeIf(r -> r.floor == floor);
        downQueue.removeIf(r -> r.floor == floor);

        moveNext();
    }

    private void moveNext() {
        if (currentDirection == Direction.UP) {
            if (!upQueue.isEmpty()) {
                Request next = upQueue.first();
                elevator.moveToFloor(next.floor);
                return;
            }
            currentDirection = Direction.DOWN;
        }

        if (currentDirection == Direction.DOWN) {
            if (!downQueue.isEmpty()) {
                Request next = downQueue.first();
                elevator.moveToFloor(next.floor);
                return;
            }
            currentDirection = Direction.UP;
        }

        if (upQueue.isEmpty() && downQueue.isEmpty()) {
            currentDirection = Direction.IDLE;
            System.out.println("Elevator is IDLE");
        }
    }
}

