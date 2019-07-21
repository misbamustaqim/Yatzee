/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.graphics.GCanvas;
import acm.program.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
		public void init() 
		{
				 text = new JTextField(10);
				 text.setActionCommand("Graph");
				 text.addActionListener(this);
				 add(new JLabel("Name") , NORTH);
				 add(text , NORTH);
				 
				 
				  graphButton=new JButton("Graph");
				  add(graphButton , NORTH);
				  addActionListeners();
				  
				  clearButton=new JButton("Clear");
				  add(clearButton , NORTH);
				  addActionListeners();
				  
				  canvas = new NameSurferGraph();
				  add(canvas);
				  
				  try {
					database = new NameSurferDataBase(NAMES_DATA_FILE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
				  addActionListeners();
				  text.addActionListener(this);

		}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
		public void actionPerformed(ActionEvent e) 
		{
				String cmd = e.getActionCommand();
				if(cmd.equals("Graph"))
				{
						canvas.addEntry(database.findEntry(text.getText()));
				}
				else if(cmd.equals("Clear"))
				{
					canvas.clear();
					
				}
				if(e.getSource() == text)
				{
					 //canvas.dis
				}
		}
		
		private JTextField text;
		private JButton graphButton;
		private JButton clearButton;
		private NameSurferGraph canvas;
		private NameSurferDataBase database;
}
