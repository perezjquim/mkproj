package perezjquim.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FileChooserWindow extends GUI
{
	public FileChooserWindow()
	{
		super("Choose a file");
		
		this.add(new JFileChooser());
		
		start();
		
	}
}
