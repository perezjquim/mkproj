package perezjquim.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IO
{
	public static String askFolder()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int state = chooser.showOpenDialog(new JFrame());
		if(state == JFileChooser.APPROVE_OPTION) 
		{
			return chooser.getSelectedFile().getAbsolutePath();
		}
		else return "";
	}
	public static String askFile()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int state = chooser.showOpenDialog(new JFrame());
		if(state == JFileChooser.APPROVE_OPTION) 
		{
			return chooser.getSelectedFile().getAbsolutePath();
		}
		else return "";
	}
	public static void popup(String message)
	{
		JOptionPane.showMessageDialog(new JFrame(), message);
	}
}
