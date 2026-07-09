package com.aip.practice.lld.elevator;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Elevator {
    private int currentFloor;
    private Direction direction;
    private ElevatorStatus elevatorStatus;
    private NavigableSet<Request> upServe;
    private NavigableSet<Request> downServe;

    public Elevator(int currentFloor, Direction direction, ElevatorStatus elevatorStatus) {
        this.currentFloor = currentFloor;
        this.direction = direction;
        this.elevatorStatus = elevatorStatus;
        this.upServe = new TreeSet<>(Comparator.comparingInt(Request::getFloor));
        this.downServe = new TreeSet<>((r1, r2) -> r2.getFloor() - r1.getFloor());
    }

    public void step() {
        if (areAllRequestServed()) {
            return;
        }

        NavigableSet<Request> servingSet = getRequestSet();
        Request request = servingSet.first();

        if (request.getFloor() > currentFloor) {
            direction = Direction.UP;
        } else if (request.getFloor() < currentFloor) {
            direction = Direction.DOWN;
        } else {
            System.out.println("Request served = " + currentFloor);
            servingSet.remove(request);
            if (areAllRequestServed()) return;
            handleDirectionChange();
        }

        if (direction == Direction.DOWN) currentFloor--;
        if (direction == Direction.UP) currentFloor++;

        System.out.println("Elevator status, currentFloor = " + currentFloor + ", direction = " + direction);
    }

    private void handleDirectionChange() {
        if (direction == Direction.UP && upServe.isEmpty() && !downServe.isEmpty()) direction = Direction.DOWN;
        if (direction == Direction.DOWN && downServe.isEmpty() && !upServe.isEmpty()) direction = Direction.UP;
    }

    private boolean areAllRequestServed() {
        if (upServe.isEmpty() && downServe.isEmpty()) {
            elevatorStatus = ElevatorStatus.SERVED;
            direction = Direction.IDLE;
            return true;
        }

        return false;
    }

    private NavigableSet<Request> getRequestSet() {
        if (upServe.isEmpty()) return downServe;
        if (downServe.isEmpty()) return upServe;

        return direction == Direction.DOWN ? downServe : upServe;
    }

    public boolean addRequest(Request request) {
        boolean isAdded = false;
        if (request.getFloor() == currentFloor) return isAdded;
        if (request.getRequestType() == RequestType.DESTINATION) {
            return addDestinationRequest(request);
        }

        if (direction == Direction.UP) isAdded = upServe.add(request);
        else isAdded = downServe.add(request);

        return isAdded;
    }

    public boolean handleDestinationRequest(int floor) {
        Request request = new Request(floor, RequestType.DESTINATION);
        return addRequest(request);
    }

    private boolean addDestinationRequest(Request request) {
        boolean isAdded;
        if (currentFloor > request.getFloor()) {
            isAdded = downServe.add(request);
        } else {
            isAdded = upServe.add(request);
        }
        return isAdded;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ElevatorStatus getElevatorStatus() {
        return elevatorStatus;
    }

    public void setElevatorStatus(ElevatorStatus elevatorStatus) {
        this.elevatorStatus = elevatorStatus;
    }
}
