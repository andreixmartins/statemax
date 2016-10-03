package com.axsoftware;

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

//@Configuration
//@EnableStateMachine
public class SimpleStateMachine extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
    	
        states
            .withStates()
                .initial(States.STATE1)
                .states(EnumSet.allOf(States.class));
        
        
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
    	
        transitions
            .withExternal()
            	.source(States.STATE1)
            	.event(Events.EVENT1)
                .target(States.STATE2)
                .action(action())
                .guard(guard())
                .and()
            .withExternal()
                .source(States.STATE2)
                .target(States.STATE1)
                .event(Events.EVENT2)
                .action(action2());
        
    }
    
    @Bean
    public Guard<States, Events> guard() {
        return new Guard<States, Events>() {

            @Override
            public boolean evaluate(StateContext<States, Events> context) {
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
                System.out.println("FAZER ALGUMA COISA NA ACTION ");
            }
        };
    }
    
    @Bean
    public Action<States, Events> action2() {
    	return new Action<States, Events>() {
    		
    		@Override
    		public void execute(StateContext<States, Events> context) {
    			System.out.println("FAZER ALGUMA COISA NA ACTION 2");
    		}
    	};
    }
}