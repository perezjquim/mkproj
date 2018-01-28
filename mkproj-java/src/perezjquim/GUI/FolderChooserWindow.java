package perezjquim.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FolderChooserWindow extends GUI
{
	public FolderChooserWindow()
	{
		super("Choose a folder");
		
		Panel pan = new Panel();
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		pan.add(chooser);
		add(pan);
		
		start();
	}
}
