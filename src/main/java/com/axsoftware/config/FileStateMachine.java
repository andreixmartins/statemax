package com.axsoftware.config;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.axsoftware.component.GenerateFileComponent;
import com.axsoftware.component.InvoiceFileComponent;
import com.axsoftware.component.ReadDataFileComponent;
import com.axsoftware.component.UploadFileComponent;
import com.axsoftware.constants.FileEvents;
import com.axsoftware.constants.FileStates;

@Configuration
@EnableStateMachine
public class FileStateMachine extends EnumStateMachineConfigurerAdapter<FileStates, FileEvents> {

	
	@Autowired
	private InvoiceFileComponent invoiceFileComponent;
	
	@Autowired
	private ReadDataFileComponent readDataFileComponent;
	
	@Autowired
	private GenerateFileComponent generateFileComponent;
	
	@Autowired
	private UploadFileComponent uploadFileComponent;
	
    @Override
    public void configure(StateMachineStateConfigurer<FileStates, FileEvents> states) throws Exception {
    	
        states
        	.withStates()
	        	.initial(FileStates.WAITING)
	        	.end(FileStates.FINISHING)
	        	.states(EnumSet.allOf(FileStates.class));
        
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<FileStates, FileEvents> transitions) throws Exception {

    	
    	transitions
    		.withExternal()
	    		.source(FileStates.WAITING)
	    		.target(FileStates.BUILDING)
	    		.event(FileEvents.START)
	    		.action(build())
//	    		.source(FileStates.BUILDING)
	    		.target(FileStates.UPLOADING)
	    		.event(FileEvents.START)
	    		.action(generate());
//		    	.source(FileStates.UPLOADING)
//		    	.target(FileStates.CHECKING)
//		    	.action(upload())
//		    	.source(FileStates.UPLOADING)
//		    	.target(FileStates.FINISHING)
//		    	.action(upload());
    	
		    	
    	
//        transitions
//        	.withExternal()
//	        	.source(FileStates.WAITING)	
//	        	.target(FileStates.BUILDING)
//	        	.event(FileEvents.START)
//	        	.action(generate())
//	        	.and()
//	        .withExternal()
//	        	.source(FileStates.BUILDING)
//	        	.target(FileStates.UPLOADING)
//	        	.event(FileEvents.UPLOAD)
//	        	.action(upload());
	        	    
    	
    }
    
    public Action<FileStates, FileEvents> build() {
        return readDataFileComponent.execute();
    }
    
    public Action<FileStates, FileEvents> generate() {
    	return generateFileComponent.execute();
    }
    
    public Action<FileStates, FileEvents> upload() {
    	return uploadFileComponent.execute();
    }
    
    public Action<FileStates, FileEvents> finish() {
    	return invoiceFileComponent.execute();
    }
}
