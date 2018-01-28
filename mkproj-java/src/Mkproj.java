package mkproj;
import perezjquim.*;
import perezjquim.GUI.*;
import java.io.*;

public class Mkproj
{
	/* MAIN WINDOW */
	private static GUI main;
	
	/* USER-INPUT DESTINATION FOLDER */
	private static File destination;
	
	/* FIELD FOR THE PROJECT NAME */
	private static TextField name;
	
	/* SELECTABLE PROGRAMMING LANGUAGES */
	private static final RadioButton[] radios =
												{
													new RadioButton("Java"),
													new RadioButton("C"),
													new RadioButton("Python"),
													new RadioButton("Prolog"),
													new RadioButton("Node.js")
												};
				
	/* MAIN FUNCTION */									 
	public static void main(String[] args)
	{
		main = new GUI("Mkproj");

		/* PANEL 'PROJECT NAME' */
		Panel panName = new Panel("Project name");

		// Text field to be filled by the user
		name = new TextField();													
		panName.add(name);
		main.add(panName);
				
		/* PANEL 'PROGRAMMING LANGUAGE' */
		Panel panLang = new Panel("Programming language");
		
		// The options/radio buttons are added to the panel
		panLang.addButtonGroup(options);									
		main.add(panLang);
		
		/* PANEL 'GENERATE PROJECT' */
		Panel panGen = new Panel("Generate project");
		
		// Button that leads to user's selection of the destination folder
		panGen.add(new Button("Select destination folder", () -> 	
							{
								destination = IO.askFolder();
							}));
							
		// Button that leads to the generation of a certain project					
		panGen.add(new Button("Generate", () -> 					
							{
								generateProject();
							}));
							
		main.add(panGen);
		
		// The main window becomes visible
		main.start();																	
	}
	
	// PROJECT GENERATION
	public static void generateProject()
	{
		if(destination == null)														// Checks
		{	IO.popup("No destination folder selected");		}
		
		else
		{
			String lang = getSelectedOption();
			
			if(lang == null)
			{	IO.popup("No language selected");		}
			
			else
			{
				String projName = name.getText();
				String folderPath = destination+"/"+projName;
				String srcPath =  folderPath+"/"+projName;
				Cmd.exec("mkdir "+projName,destination);
				
				switch(lang)
				{
					case "C":
						Cmd.exec("mkmake c",new File(folderPath));
						srcPath += ".c";
						IO.writeFile(srcPath,
											"#include <stdio.h>\n"+
											"#include <stdlib.h>\n"+
											"#include <unistd.h>\n"+
											"\n"+
											"int main()\n"+
											"{\n"+
											"	printf(\"Great success\\n\");\n"+
											"	return 0;\n"+
											"}");
						IO.popup("C project generated successfully");
						break;
					case "Java":
						Cmd.exec("mkmake java",new File(folderPath));
						srcPath += ".java";
						IO.writeFile(srcPath,
											"package "+projName+";\n"+
											"\n"+
											"public class "+projName+"\n"+
											"{\n"+
											"	public static void main(String[] args)\n"+
											"	{\n"+
											"		System.out.println(\"Great success\");\n"+
											"	}\n"+
											"}\n");
						IO.popup("Java project generated successfully");
						break;
					case "Node.js":
						Cmd.exec("mkmake nodejs",new File(folderPath));
						srcPath += ".js";
						IO.writeFile(srcPath,
											"console.log(\"Great success\")");
						IO.popup("Node.js project generated successfully");
						break;
					case "Python":
						Cmd.exec("mkmake python",new File(folderPath));
						srcPath += ".py";
						IO.writeFile(srcPath,
											"print \"Great success\";");
						IO.popup("Python project generated successfully");
						break;
					case "Prolog":
						Cmd.exec("mkmake prolog",new File(folderPath));
						srcPath += ".pl";
						IO.writeFile(srcPath,
											projName+":-writeln('Great success').");
						IO.popup("Prolog project generated successfully");
						break;
				}			
			}
		}		
	}
	public static String getSelectedOption()
	{
		for(RadioButton r : options)
		{
			if(r.isSelected())
			{ return r.getText(); }
		}
		return null;
	}
}

