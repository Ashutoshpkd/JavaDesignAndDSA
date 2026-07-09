package com.aip.practice.lld.elevator.strategy;

import com.aip.practice.lld.elevator.Elevator;
import com.aip.practice.lld.elevator.Request;

import java.util.List;

public interface SelectionStrategy {

    Elevator getElevator(List<Elevator> elevators, Request request);
}
