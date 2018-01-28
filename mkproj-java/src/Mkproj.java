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
	
	/* PROJECT GENERATION */
	public static void generateProject()
	{
		// If the user didn't select a destination folder
		if(destination == null)														
		{ IO.popup("No destination folder selected"); }
		
		// If the user did select a destination folder
		else
		{
			// Gets the selected language
			String lang = getSelectedOption();
			
			// If there was no language selected
			if(lang == null)
			{	IO.popup("No language selected");		}
			
			// If everything went well
			else
			{
				// Gets the project name
				String projName = name.getText();
				
				// Gets the folder path (ex.: /home/perezjquim/folderwheretheprojectisgoingto)
				String folderPath = destination+"/"+projName;
				
				// Gets the source path (ex.: /home/perezjquim/folderwheretheprojectisgoingto/sourcefile)
				// (the file extension will be added later, according to the language)
				String srcPath =  folderPath+"/"+projName;
				
				// The project directory is created
				Cmd.exec("mkdir "+projName,destination);
				
				switch(lang)
				{
					/* C */
					case "C":
						// The makefile is generated
						Cmd.exec("mkmake c",new File(folderPath));
						
						// The file extension is added
						srcPath += ".c";
						
						// The generic source code is written
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
											
						// Popup message
						IO.popup("C project generated successfully");
						break;
						
					/* JAVA */
					case "Java":
						// The makefile is generated
						Cmd.exec("mkmake java",new File(folderPath));
						
						// The file extension is added
						srcPath += ".java";
						
						// The generic source code is written
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
											
						// Popup message
						IO.popup("Java project generated successfully");
						break;
						
					/* NODE.JS */
					case "Node.js":
						// The makefile is generated
						Cmd.exec("mkmake nodejs",new File(folderPath));
						
						// The file extension is added
						srcPath += ".js";
						
						// The generic source code is written
						IO.writeFile(srcPath,
											"console.log(\"Great success\")");
											
						// Popup message
						IO.popup("Node.js project generated successfully");
						break;
						
					/* PYTHON */
					case "Python":
						// The makefile is generated
						Cmd.exec("mkmake python",new File(folderPath));
						
						// The file extension is added
						srcPath += ".py";
						
						// The generic source code is written
						IO.writeFile(srcPath,
											"print \"Great success\";");
											
						// Popup message
						IO.popup("Python project generated successfully");
						break;
						
					/* PROLOG */
					case "Prolog":
						// The makefile is generated
						Cmd.exec("mkmake prolog",new File(folderPath));
						
						// The file extension is added
						srcPath += ".pl";
						
						// The generic source code is written
						IO.writeFile(srcPath,
											projName+":-writeln('Great success').");
											
						// Popup message
						IO.popup("Prolog project generated successfully");
						break;
				}			
			}
		}		
	}
	
	/* GETS THE SELECTED LANGUAGE */
	public static String getSelectedOption()
	{
		// Loop through all the options
		for(RadioButton r : options)
		{
			// If found a selected one
			if(r.isSelected()) 
				return r.getText();
		}
		
		// In case nothing was selected
		return null;
	}
}

