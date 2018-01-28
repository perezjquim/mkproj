package mkproj;
import perezjquim.*;
import perezjquim.GUI.*;

public class Mkproj
{
	private static String destination;
	private static GUI main;
	public static void main(String[] args)
	{
		destination = "";
		main = new GUI("Mkproj");

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
		panGen.add(new Button("Select destination folder", () -> 
							{
								destination = IO.askFolder();
							}));
							
		panGen.add(new Button("Generate", () -> 
							{
								generateProject();
							}));
							
		main.add(panGen);
		
		main.start();
	}
	
	public static void generateProject()
	{
		if(destination.equals(""))
		{	IO.popup("No destination folder selected");		}

		else 
		{
			
		}		
	}
}
