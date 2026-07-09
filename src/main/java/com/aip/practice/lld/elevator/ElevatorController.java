package com.aip.practice.lld.elevator;

import com.aip.practice.lld.elevator.strategy.SelectionStrategy;

import java.util.List;

public class ElevatorController {
    private static final int MAX_FLOOR = 9;
    private static final int MIN_FLOOR = 0;

    private final List<Elevator> elevators;
    private final SelectionStrategy strategy;

    public ElevatorController(SelectionStrategy strategy, List<Elevator> elevators) {
        this.strategy = strategy;
        this.elevators = elevators;
    }

    public boolean handleHallCall(Request request) {
        if (request == null || request.getRequestType() == null) return false;

        int requestedFloor = request.getFloor();
        if (isInvalidFloor(requestedFloor)) return false;
        if (request.getRequestType() == RequestType.DESTINATION) return false;

        Elevator elevator = strategy.getElevator(elevators, request);
        if (elevator == null) return false;

        boolean isAdded = elevator.addRequest(request);
        if (isAdded) {
            System.out.println("Assigned " + request + " to elevator " + elevator.getId());
        }

        return isAdded;
    }

    public boolean handleDestinationRequest(int elevatorId, int destinationFloor) {
        if (isInvalidFloor(destinationFloor)) return false;

        for (Elevator elevator : elevators) {
            if (elevator.getId() == elevatorId) {
                boolean isAdded = elevator.handleDestinationRequest(destinationFloor);
                if (isAdded) {
                    System.out.println("Assigned destination floor " + destinationFloor + " to elevator " + elevatorId);
                }
                return isAdded;
            }
        }

        return false;
    }

    public void step() throws InterruptedException {
        step(100, 100);
    }

    public void step(int numberOfSteps, long pauseInMillis) throws InterruptedException {
        for (int i = 0; i < numberOfSteps; i++) {
            elevators.forEach(Elevator::step);
            if (pauseInMillis > 0) {
                Thread.sleep(pauseInMillis);
            }
        }
    }

    private boolean isInvalidFloor(int floor) {
        return floor > MAX_FLOOR || floor < MIN_FLOOR;
    }
}
