package com.axsoftware.component;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.axsoftware.constants.FileEvents;
import com.axsoftware.constants.FileStates;

@Component
public class InvoiceFileComponent extends FileComponent {

	public Action<FileStates, FileEvents> execute() {

		return new Action<FileStates, FileEvents>() {

			@Override
			public void execute(StateContext<FileStates, FileEvents> context) {
				
			}
		};
	}

}