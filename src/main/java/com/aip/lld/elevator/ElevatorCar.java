package com.aip.lld.elevator;

import java.util.Objects;

class ElevatorCar {
    int currentFloor = 0;
    Direction direction = Direction.IDLE;

    ElevatorState state = ElevatorState.IDLE;

    ElevatorController controller;

    void setController(ElevatorController controller) {
        this.controller = controller;
    }

    void moveToFloor(int targetFloor) {
        state = ElevatorState.MOVING;
        direction = targetFloor > currentFloor ? Direction.UP : Direction.DOWN;

        System.out.println("Moving " + direction + " to floor " + targetFloor);

        currentFloor = targetFloor;
        stop();
    }

    void stop() {
        System.out.println("Stopped at floor " + currentFloor);
        state = ElevatorState.DOOR_OPEN;

        controller.onFloorReached(currentFloor);
    }

    void setState(ElevatorState state) {
        this.state = state;
    }
}

