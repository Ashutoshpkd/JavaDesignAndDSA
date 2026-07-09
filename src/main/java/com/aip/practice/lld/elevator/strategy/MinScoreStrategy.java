package com.aip.practice.lld.elevator.strategy;

import com.aip.practice.lld.elevator.Direction;
import com.aip.practice.lld.elevator.Elevator;
import com.aip.practice.lld.elevator.Request;
import com.aip.practice.lld.elevator.RequestType;

import java.util.List;

public class MinScoreStrategy implements SelectionStrategy {

    @Override
    public Elevator getElevator(List<Elevator> elevators, Request request) {
        Elevator e = checkIdleAndIsOnSameFloor(elevators, request.getFloor());
        if (e != null) return e;

        e = getMovingTowardsElevator(elevators, request);
        if (e != null) return e;

        e = getNearestIdleElevator(elevators, request.getFloor());
        if (e != null) return e;

        return getNearestElevator(elevators, request.getFloor());
    }

    private Elevator getNearestIdleElevator(List<Elevator> elevators, int floor) {
        int minDist = Integer.MAX_VALUE;
        Elevator bestElevator = null;

        for (Elevator e : elevators) {
            if (e.getDirection() == Direction.IDLE && !e.hasPendingRequests()) {
                int distance = Math.abs(floor - e.getCurrentFloor());

                if (minDist > distance) {
                    minDist = distance;
                    bestElevator = e;
                }
            }
        }

        return bestElevator;
    }

    private Elevator getNearestElevator(List<Elevator> elevators, int floor) {
        int minDist = Integer.MAX_VALUE;
        Elevator bestElevator = null;

        for (Elevator e : elevators) {
            int distance = Math.abs(e.getCurrentFloor() - floor);

            if (minDist > distance) {
                minDist = distance;
                bestElevator = e;
            }
        }

        return bestElevator;
    }

    private Elevator getMovingTowardsElevator(List<Elevator> elevators, Request request) {
        Direction reqDirection = request.getRequestType() == RequestType.PICKUP_UP ? Direction.UP : Direction.DOWN;
        int minDistance = Integer.MAX_VALUE;
        Elevator bestElevator = null;

        for (Elevator e : elevators) {
            if (e.getDirection() != reqDirection) {
                continue;
            }

            if (reqDirection == Direction.UP && request.getFloor() >= e.getCurrentFloor()) {
                int distance = request.getFloor() - e.getCurrentFloor();
                if (minDistance > distance) {
                    bestElevator = e;
                    minDistance = distance;
                }
            }

            if (reqDirection == Direction.DOWN && request.getFloor() <= e.getCurrentFloor()) {
                int distance = e.getCurrentFloor() - request.getFloor();
                if (minDistance > distance) {
                    bestElevator = e;
                    minDistance = distance;
                }
            }
        }

        return bestElevator;
    }

    private Elevator checkIdleAndIsOnSameFloor(List<Elevator> elevators, int floor) {
        for (Elevator e : elevators) {
            if (e.getDirection() == Direction.IDLE && !e.hasPendingRequests() && e.getCurrentFloor() == floor) return e;
        }

        return null;
    }


}
