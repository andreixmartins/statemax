package com.axsoftware.component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.axsoftware.constants.FileEvents;
import com.axsoftware.constants.FileStates;

@Component
public class GenerateFileComponent extends FileComponent {

	public Action<FileStates, FileEvents> execute() {

		return new Action<FileStates, FileEvents>() {

			@Override
			public void execute(StateContext<FileStates, FileEvents> context) {
				System.out.println(this.getClass().getName());
				System.out.println("GERAR ARQUIVO");
				
				List<String> data = (List<String>)context.getExtendedState().getVariables().get(PENDING_FILES);
				
				File file = new File("c:\\tmp\\teste-sm\\sm.txt");
				try {
					Path path = java.nio.file.Files.write(file.toPath(),data);
					
					new ByteArrayInputStream(data.);
					
					System.out.println(path.getFileName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	}

}