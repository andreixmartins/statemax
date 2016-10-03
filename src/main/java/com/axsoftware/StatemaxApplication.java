package com.axsoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class StatemaxApplication implements CommandLineRunner{

    @Autowired
    StateMachine<States, Events> choiceStateMachine;
	
//	@Autowired
//	StateMachine<States, Events> simpleStateMachine;
	
	public static void main(String[] args) {
		SpringApplication.run(StatemaxApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

//		simpleStateMachine.start();
//		simpleStateMachine.sendEvent(Events.EVENT1);
//		simpleStateMachine.sendEvent(Events.EVENT2);
		
		choiceStateMachine.start();
		choiceStateMachine.sendEvent(Events.EVENT1);
//		choiceStateMachine.sendEvent(Events.EVENT2);
		
	}	

}
