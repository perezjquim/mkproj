package mkproj;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame
{
	private Container con;
	public static void main(String[] args)
	{ new GUI(); }
	
	/***********************************/
	/***********************************/	
	/***********************************/
	public GUI()
	{
		super("MKPROJ");
		init();

		Panel panName = new Panel("Project name");
		TextField field = new TextField();
		panName.add(field);
		con.add(panName);
				
		Panel panLang = new Panel("Programming language");
		panLang.addButtonGroup(new RadioButton("Java"),
												new RadioButton("C"),
												new RadioButton("Python"),
												new RadioButton("Prolog"),
												new RadioButton("Node.js"));
		con.add(panLang);
		
		Panel panGen = new Panel("Generate project");
		panGen.add(new Button("Select destination folder", () -> execCmd("echo ola")));
		panGen.add(new Button("Generate", () -> execCmd("echo ola")));
		con.add(panGen);
		
		start();
	}
	/***********************************/
	/***********************************/	
	/***********************************/	

	
	/***********************************/
	/***********************************/
	private class Button extends JButton
	{
		public Button(String label,Runnable action)
		{
			super(label);
			addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e)
			{
				action.run();	
			}});
		}
	}
	private class Panel extends JPanel
	{
		public Panel(String label)
		{
			super();
			JPanel panel = new JPanel();
			panel.add(new JLabel("<html><i>"+label+"</i></html>"));
			con.add(panel);
		}
		public void addButtonGroup (AbstractButton ... buttons)
		{
			ButtonGroup group = new ButtonGroup();
			for (AbstractButton b : buttons) 
			{
				group.add(b);
				this.add(b);
			}
		}
	}
	private class TextField extends JFormattedTextField
	{
		public TextField()
		{
			super();
			this.setColumns(30);
		}
	}
	private class RadioButton extends JRadioButton
	{
		public RadioButton(String label)
		{
			super(label);
			this.setActionCommand(label);
		}
	}
	/***********************************/	
	/***********************************/
	
	/***********************************/
	/***********************************/
	public void execCmd(String cmd)
	{ //Util.execCmd(cmd,console); 
	}
	/***********************************/
	/***********************************/

	public void init()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		con = this.getContentPane();
		con.setLayout(new BoxLayout(con,BoxLayout.Y_AXIS));
	}	
	public void start()
	{
		pack();
		setVisible(true);
	}
	/***********************************/
	/***********************************/
}
