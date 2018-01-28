package mkproj;
import perezjquim.*;
import perezjquim.GUI.*;
import java.io.*;

public class Mkproj
{
	private static File destination;
	private static TextField name;
	private static GUI main;
	
	private static final RadioButton[] options =
												{
													new RadioButton("Java"),
													new RadioButton("C"),
													new RadioButton("Python"),
													new RadioButton("Prolog"),
													new RadioButton("Node.js")
												};
												 
	private static final String genC ="";
	public static void main(String[] args)
	{
		main = new GUI("Mkproj");

		Panel panName = new Panel("Project name");
		name = new TextField();
		panName.add(name);
		main.add(panName);
				
		Panel panLang = new Panel("Programming language");
		panLang.addButtonGroup(options);
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
		if(destination == null)
		{	IO.popup("No destination folder selected");		}

		else 
		{
			Cmd.exec("mkdir "+name.getText()+"\n",destination);
			Cmd.exec("mkmake c",new File(destination+"/"+name.getText()));
			Util.writeFile(destination+"/"+name.getText()+"/"+name.getText()+".c",
												"#include <stdio.h>\n"+
												"#include <stdlib.h>\n"+
												"#include <unistd.h>\n"+
												"\n"+
												"int main()\n"+
												"{\n"+
												"	printf(\"Great success\\n\");\n"+
												"	return 0;\n"+
												"}\n");
		}		
	}
}

