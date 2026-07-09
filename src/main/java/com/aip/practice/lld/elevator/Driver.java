package com.aip.practice.lld.elevator;

import com.aip.practice.lld.elevator.strategy.MinScoreStrategy;

import java.util.List;

public class Driver {

    public static void main(String[] args) throws InterruptedException {
        ElevatorController ec = new ElevatorController(
                new MinScoreStrategy(),
                List.of(new Elevator(0, Direction.IDLE, ElevatorStatus.STOPPED),
                        new Elevator(0, Direction.IDLE, ElevatorStatus.STOPPED),
                        new Elevator(0, Direction.IDLE, ElevatorStatus.STOPPED),
                        new Elevator(0, Direction.IDLE, ElevatorStatus.STOPPED)
                        )
        );

        ec.handleHallCall(new Request(1, RequestType.PICKUP_UP));
        ec.handleHallCall(new Request(5, RequestType.PICKUP_UP));
        ec.handleHallCall(new Request(5, RequestType.PICKUP_DOWN));
        ec.handleHallCall(new Request(1, RequestType.PICKUP_DOWN));
        ec.handleHallCall(new Request(6, RequestType.PICKUP_UP));
        ec.handleHallCall(new Request(4, RequestType.PICKUP_UP));

        ec.step();

    }
}
