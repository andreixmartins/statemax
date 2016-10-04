package com.axsoftware.component;

import java.util.List;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.axsoftware.constants.FileEvents;
import com.axsoftware.constants.FileStates;

@Component
public class UploadFileComponent extends FileComponent {

	public Action<FileStates, FileEvents> execute() {

		return new Action<FileStates, FileEvents>() {

			@Override
			public void execute(StateContext<FileStates, FileEvents> context) {
								
				System.out.println(this.getClass().getName());
				System.out.println("UPLOAD ARQUIVOS");
				System.out.println(context.getEvent());
				System.out.println(context.getSource());
				
				final List<String> files = (List<String>)context.getExtendedState().getVariables().get("PENDING_FILES");
			}
		};
	}

}