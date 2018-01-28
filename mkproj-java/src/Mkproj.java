package mkproj;
import perezjquim.*;
import perezjquim.GUI.*;

public class Mkproj
{
	public static void main(String[] args)
	{
		GUI main = new GUI("Mkproj");

		Panel panName = new Panel("Project name");
		TextField field = new TextField();
		panName.add(field);
		main.add(panName);
				
		Panel panLang = new Panel("Programming language");
		panLang.addButtonGroup(new RadioButton("Java"),
												new RadioButton("C"),
												new RadioButton("Python"),
												new RadioButton("Prolog"),
												new RadioButton("Node.js"));
		main.add(panLang);
		
		Panel panGen = new Panel("Generate project");
		panGen.add(new Button("Select destination folder", () -> new FolderChooserWindow()));
		panGen.add(new Button("Generate", () -> Cmd.exec("echo ola")));
		main.add(panGen);
		
		main.start();
	}
}
