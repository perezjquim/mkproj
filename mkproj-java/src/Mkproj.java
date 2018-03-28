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
	
	private static final String languages[] = { 
												"C", 
												"C#", 
												"Java" , 
												"Node.js", 
												"Python", 
												"Prolog", 
												"Swift" 
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
		{ radios[i] = new RadioButton(languages[i]); }
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
		int selectedRadio = -1;

		// Loop through all the options
		for(int i = 0; i < radios.length; i++)
		{
			// If found a selected one
			if(radios[i].isSelected()) 
				selectedRadio = i;
		}

		// If the user didn't select a destination folder
		if(destination == null)														
		{ 
			IO.popup("No destination folder selected"); 
			return;
		}

		if(selectedRadio == -1)
		{
			IO.popup("No language selected");
			return;
		}
		
		// Gets the project name typed in by the user
		String projName = name.getText();			


		String language = languages[selectedRadio]
								.toLowerCase()
								.replace(".","");

		Cmd.exec("mkproj " + language + " "+projName, destination);	

		IO.popup("Project generated successfully!");
	}
}

