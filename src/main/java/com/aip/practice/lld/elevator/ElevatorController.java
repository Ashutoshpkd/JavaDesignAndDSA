package com.aip.practice.lld.elevator;

import com.aip.practice.lld.elevator.strategy.SelectionStrategy;

import java.util.List;

public class ElevatorController {
    private static int MAX_FLOOR = 9;
    private static int MIN_FLOOR = 0;
    private List<Elevator> elevators;
    private SelectionStrategy strategy;

    public ElevatorController(SelectionStrategy strategy, List<Elevator> elevators) {
        this.strategy = strategy;
        this.elevators = elevators;
    }

    public boolean handleHallCall(Request request) {
        int requestedFloor = request.getFloor();
        if (requestedFloor > MAX_FLOOR || requestedFloor < MIN_FLOOR) return false;
        if (request.getRequestType() == null || request.getRequestType() == RequestType.DESTINATION) return false;

        Elevator elevator = strategy.getElevator(elevators, request);

        return elevator.addRequest(request);
    }

    public void step() throws InterruptedException {
        for (int i=0; i<100; i++) {
            elevators.forEach(Elevator::step);
            Thread.sleep(100);
        }
    }
}
