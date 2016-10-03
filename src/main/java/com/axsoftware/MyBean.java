package com.axsoftware;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine(name="simpleStateMachine")
public class MyBean {

    @OnTransition(target = "STATE1")
    void toState1() {
    	System.out.println("TOSTATE1");
    }

    @OnTransition(target = "STATE2")
    void toState2() {
    	System.out.println("TOSTATE2");
    }
}