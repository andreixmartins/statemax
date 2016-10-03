package com.axsoftware.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import com.axsoftware.Events;
import com.axsoftware.States;
import com.axsoftware.constants.FileEvents;
import com.axsoftware.constants.FileStates;

@Configuration
@EnableStateMachine
public class FileStateMachine extends EnumStateMachineConfigurerAdapter<FileStates, FileEvents> {

	
    @Override
    public void configure(StateMachineStateConfigurer<FileStates, FileEvents> states) throws Exception {
    	
        states
        	.withStates()
	        	.initial(FileStates.WAITING)
	        	.states(EnumSet.allOf(FileStates.class))
	        	.choice(FileStates.UPLOADING)
	        	.end(FileStates.FINISHING);
        
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<FileStates, FileEvents> transitions) throws Exception {
    	
        transitions
        	.withExternal()
        	.source(FileStates.WAITING)
        	.target(FileStates.BUILDING)
        	.event(FileEvents.START)
        	.guard(guardGerarArquivo())
        	.and()
        	.withChoice()
	            .source(FileStates.UPLOADING)
	            .first(States.STATE2, guard())
	            .then(States.STATE3, guard2())
	            .last(FileStates.FINISHING);
    	
    	
    }
    
    @Bean
    public Guard<FileStates, FileEvents> guardGerarArquivo() {
        return new Guard<FileStates, FileEvents>() {

            @Override
            public boolean evaluate(StateContext<FileStates, FileEvents> context) {
            	System.out.println(this.getClass().getName());
            	System.out.println("FAZER ALGUMA COISA NO GUARD");
                return false;
            }
        };
    }
    
    @Bean
    public Guard<States, Events> guard2() {
    	return new Guard<States, Events>() {
    		
    		@Override
    		public boolean evaluate(StateContext<States, Events> context) {
    			System.out.println(this.getClass().getName());
    			System.out.println("FAZER ALGUMA COISA NO GUARD 2");
    			return true;
    		}
    	};
    }
    
    @Bean
    public Action<States, Events> action() {
        return new Action<States, Events>() {

            @Override
            public void execute(StateContext<States, Events> context) {
                System.out.println("FAZER ALGUMA COISA");
            }
        };
    }
    
    @Bean
    public Action<States, Events> action2() {
    	return new Action<States, Events>() {
    		
    		@Override
    		public void execute(StateContext<States, Events> context) {
    			System.out.println("FAZER ALGUMA COISA 2");
    		}
    	};
    }	
}
