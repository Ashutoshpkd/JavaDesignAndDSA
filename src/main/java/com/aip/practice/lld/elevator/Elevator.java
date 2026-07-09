package com.aip.practice.lld.elevator;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private final int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorStatus elevatorStatus;
    private NavigableSet<Request> upServe;
    private NavigableSet<Request> downServe;

    public Elevator(int currentFloor, Direction direction, ElevatorStatus elevatorStatus) {
        this(ID_GENERATOR.getAndIncrement(), currentFloor, direction, elevatorStatus);
    }

    public Elevator(int id, int currentFloor, Direction direction, ElevatorStatus elevatorStatus) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.direction = direction;
        this.elevatorStatus = elevatorStatus;
        this.upServe = new TreeSet<>(Comparator.comparingInt(Request::getFloor)
                .thenComparing(Request::getRequestType));
        this.downServe = new TreeSet<>((r1, r2) -> {
            int floorCompare = Integer.compare(r2.getFloor(), r1.getFloor());
            if (floorCompare != 0) return floorCompare;
            return r1.getRequestType().compareTo(r2.getRequestType());
        });
    }

    public void step() {
        if (areAllRequestServed()) {
            return;
        }

        NavigableSet<Request> servingSet = getRequestSet();
        Request request = servingSet.first();

        if (request.getFloor() > currentFloor) {
            direction = Direction.UP;
            elevatorStatus = ElevatorStatus.MOVING;
            currentFloor++;
            printStatus();
        } else if (request.getFloor() < currentFloor) {
            direction = Direction.DOWN;
            elevatorStatus = ElevatorStatus.MOVING;
            currentFloor--;
            printStatus();
        } else {
            serveRequest(request, servingSet);
            handleDirectionChange();
        }
    }

    private void handleDirectionChange() {
        if (upServe.isEmpty() && downServe.isEmpty()) {
            direction = Direction.IDLE;
            elevatorStatus = ElevatorStatus.STOPPED;
            return;
        }

        if (direction == Direction.UP && upServe.isEmpty() && !downServe.isEmpty()) {
            direction = Direction.DOWN;
            return;
        }

        if (direction == Direction.DOWN && downServe.isEmpty() && !upServe.isEmpty()) {
            direction = Direction.UP;
            return;
        }

        if (direction == Direction.IDLE) {
            direction = upServe.isEmpty() ? Direction.DOWN : Direction.UP;
        }
    }

    private boolean areAllRequestServed() {
        if (upServe.isEmpty() && downServe.isEmpty()) {
            elevatorStatus = ElevatorStatus.STOPPED;
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
        if (request == null || request.getRequestType() == null
         || request.getFloor() == currentFloor) return false;

        if (request.getRequestType() == RequestType.DESTINATION) {
            return addDestinationRequest(request);
        }

        return addStopByPickupFloor(request);
    }

    private boolean addStopByPickupFloor(Request request) {
        boolean isAdded;

        if (request.getFloor() > currentFloor) {
            isAdded = upServe.add(request);
            if (direction == Direction.IDLE) direction = Direction.UP;
        } else {
            isAdded = downServe.add(request);
            if (direction == Direction.IDLE) direction = Direction.DOWN;
        }

        if (isAdded) {
            elevatorStatus = ElevatorStatus.MOVING;
        }

        return isAdded;
    }

    public boolean handleDestinationRequest(int floor) {
        return addRequest(new Request(floor, RequestType.DESTINATION));
    }

    private boolean addDestinationRequest(Request request) {
        return addStopByPickupFloor(request);
    }

    private void serveRequest(Request request, NavigableSet<Request> servingSet) {
        elevatorStatus = ElevatorStatus.SERVED;
        System.out.println("Elevator " + id + " served " + request + " at floor " + currentFloor);
        servingSet.remove(request);
    }

    private void printStatus() {
        System.out.println("Elevator " + id + " status, currentFloor = " + currentFloor +
                ", direction = " + direction);
    }

    public boolean hasPendingRequests() {
        return !upServe.isEmpty() || !downServe.isEmpty();
    }

    public int getId() {
        return id;
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
