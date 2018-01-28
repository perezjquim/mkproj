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
	
	private static final Template[] languages =
												{
													new Template("c","C",".c",
														"#include <stdio.h>\n"+
														"#include <stdlib.h>\n"+
														"#include <unistd.h>\n"+
														"\n"+
														"int main()\n"+
														"{\n"+
														"	printf(\"Great success\\n\");\n"+
														"	return 0;\n"+
														"}"),
													new Template("java","Java",".java",
														"package projName;\n"+
														"\n"+
														"public class projName\n"+
														"{\n"+
														"	public static void main(String[] args)\n"+
														"	{\n"+
														"		System.out.println(\"Great success\");\n"+
														"	}\n"+
														"}\n"),
													new Template("nodejs","Node.js",".js",
														"console.log(\"Great success\");"),
													new Template("python","Python",".py",
														"print \"Great success\";"),
													new Template("prolog","Prolog",".pl",
														"projName:-writeln('Great success').")
												};
	private static RadioButton[] radios;
				
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
			radios[i] = new RadioButton(languages[i].getLabel());
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
				// Gets the project name
				String projName = name.getText();
				
				// Gets the folder path (ex.: /home/perezjquim/folderwheretheprojectisgoingto)
				String folderPath = destination+"/"+projName;
				
				// Gets the source path (ex.: /home/perezjquim/folderwheretheprojectisgoingto/sourcefile)
				// (the file extension will be added later, according to the language)
				String srcPath =  folderPath+"/"+projName;
				
				// The project directory is created
				Cmd.exec("mkdir "+projName,destination);
				
				lang.adaptCode(projName);
				Cmd.exec("mkmake "+lang.getName(),new File(folderPath));
				srcPath += lang.getSrcExtension();
				IO.writeFile(srcPath,lang.getSrcCode());
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

