package com.aip.practice.lld.elevator;

import com.aip.practice.lld.elevator.strategy.MinScoreStrategy;

import java.util.List;

public class Driver {

    public static void main(String[] args) throws InterruptedException {
        ElevatorController ec = new ElevatorController(
                new MinScoreStrategy(),
                List.of(new Elevator(1, 0, Direction.IDLE, ElevatorStatus.STOPPED),
                        new Elevator(2, 0, Direction.IDLE, ElevatorStatus.STOPPED),
                        new Elevator(3, 0, Direction.IDLE, ElevatorStatus.STOPPED),
                        new Elevator(4, 0, Direction.IDLE, ElevatorStatus.STOPPED)
                        )
        );

        ec.handleHallCall(new Request(1, RequestType.PICKUP_UP));
        ec.handleHallCall(new Request(5, RequestType.PICKUP_UP));
        ec.handleHallCall(new Request(5, RequestType.PICKUP_DOWN));
        ec.handleHallCall(new Request(1, RequestType.PICKUP_DOWN));
        ec.handleHallCall(new Request(6, RequestType.PICKUP_UP));
        ec.handleHallCall(new Request(4, RequestType.PICKUP_UP));

        ec.step(30, 0);

        System.out.println();
        System.out.println("Scenario: elevator is at floor 5, pickup UP comes from floor 2, then passenger selects floor 8");

        ElevatorController secondController = new ElevatorController(
                new MinScoreStrategy(),
                List.of(new Elevator(5, 5, Direction.IDLE, ElevatorStatus.STOPPED))
        );

        secondController.handleHallCall(new Request(2, RequestType.PICKUP_UP));
        secondController.step(4, 0);
        secondController.handleDestinationRequest(5, 8);
        secondController.step(8, 0);

    }
}
