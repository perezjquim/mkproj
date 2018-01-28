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
					default:
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

