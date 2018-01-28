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
	
	/* STORAGE OF ALL RADIO BUTTONS */													
	private static RadioButton[] radios;
	
	/* SELECTABLE PROGRAMMING LANGUAGES (and its project templates) */
	private static final Template[] languages =
												{
													/* C */
													new Template("C","c",".c", false,
														"#include <stdio.h>\n"+
														"#include <stdlib.h>\n"+
														"#include <unistd.h>\n"+
														"\n"+
														"int main()\n"+
														"{\n"+
														"	printf(\"Great success\\n\");\n"+
														"	return 0;\n"+
														"}"),
														
													/* JAVA */
													new Template("Java","java",".java", true,
														"package projName;\n"+
														"\n"+
														"public class projName\n"+
														"{\n"+
														"	public static void main(String[] args)\n"+
														"	{\n"+
														"		System.out.println(\"Great success\");\n"+
														"	}\n"+
														"}\n"),
														
													/* NODE.JS */
													new Template("Node.js","nodejs",".js", false,
														"console.log(\"Great success\");"),
														
													/* PYTHON */
													new Template("Python","python",".py", false,
														"print \"Great success\";"),
														
													/* PROLOG */
													new Template("Prolog","prolog",".pl", false,
														"projName:-writeln('Great success').")
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
		radios = new RadioButton[languages.length];
		for(int i = 0; i < languages.length; i++)
		{ radios[i] = new RadioButton(languages[i].getLabel()); }
		panLang.addButtonGroup(radios);									
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
			Template lang = getSelectedLanguage();
			
			// If there was no language selected
			if(lang == null)
			{	IO.popup("No language selected");		}
			
			// If everything went well
			else
			{
				// Gets the project name typed in by the user
				String projName = name.getText();
				
				// Adapts the source code to the project name (ex.: name of the class)
				lang.adaptCode(projName);
				
				// The project directory is created
				Cmd.exec("mkdir "+projName,destination);
				
				// Gets the folder path (ex.: /home/perezjquim/awesomeproject)
				File folderPath = new File(destination+"/"+projName);
				
				// The source file path is initialized with the project's folder (to be appended later)
				String srcPath = folderPath.getPath()+"/";
				
				// If the language requires 'bin' and 'src' organization (ex.: Java)
				if(lang.hasFolderStructure())
				{
					// 'bin' and 'src' folders are created
					Cmd.exec("mkdir bin",folderPath);
					Cmd.exec("mkdir src",folderPath);
					
					// 'src' folder is added to the path
					srcPath += "src/";
				}
				
				// The source file name is added to the path 
				// (ex.: /home/perezjquim/awesomeproject/awesomeproject.js or /home/perezjquim/awesomeproject/src/awesomeproject.java)
				srcPath += projName+lang.getSrcExtension();
				
				// Generates the makefile
				Cmd.exec("mkmake "+lang.getCmdArgument(),folderPath);
				
				// Writes the generic source code to the file
				IO.writeFile(srcPath,lang.getSrcCode());
				
				// Popup message
				IO.popup(lang.getLabel()+" project generated successfully");	
			}
		}		
	}
	
	/* GETS THE SELECTED LANGUAGE */
	public static Template getSelectedLanguage()
	{
		// Loop through all the options
		for(int i = 0; i < radios.length; i++)
		{
			// If found a selected one
			if(radios[i].isSelected()) 
				return languages[i];
		}
		
		// In case nothing was selected
		return null;
	}
}

