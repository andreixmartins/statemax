package com.axsoftware.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.axsoftware.constants.FileEvents;
import com.axsoftware.constants.FileStates;

@Component
public class ReadDataFileComponent extends FileComponent {

	public Action<FileStates, FileEvents> execute() {

		return new Action<FileStates, FileEvents>() {

			@Override
			public void execute(StateContext<FileStates, FileEvents> context) {
				System.out.println(this.getClass().getName());
				System.out.println("CONSULTAR PENDENTES");
				System.out.println("GERAR ARQUIVO");
				
				final List<String> files = new ArrayList<>();
				files.add("File 1");
				files.add("File 2");
				files.add("File 3");
				
				context.getExtendedState().getVariables().put(PENDING_FILES, files);				
			}
		};
	}

}