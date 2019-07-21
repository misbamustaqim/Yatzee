/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.util.*;

import javax.swing.JFrame;

import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	
	
	
	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() 
	{
			addComponentListener(this);
			listOfNameEntries= new ArrayList<>();
	}
	
	
		/**
	* Clears the list of name surfer entries stored inside this class.
	*/
		public void clear() 
		{
					removeAll();
					drawGraphOutline();
					listOfNameEntries.clear();
		}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/    
			public void addEntry(NameSurferEntry entry) 
			{
					// You fill this in //
					
			
					if(entry != null)
					{
						listOfNameEntries.add(entry);
						update();
					}
					
					
			}
	
	
			
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
			public void update()
			{
				removeAll();
				drawGraphOutline();

				int constant = 80;
				
				double newHeight = getHeight() - constant;
				double x1 = drawGraphOutline();
				double y1 = newHeight/1000;
				
				
				//draw a Graph
			
		        for (int i = 0; i < listOfNameEntries.size(); i++) 
		        {
		        	for (int j = 0; j < 11; j++) 
					{
		        		GLine lineBetweenTwoYears;
		        		int rank1 = listOfNameEntries.get(i).getRank(j);
		        		int rank2 = listOfNameEntries.get(i).getRank(j+1);
		        		int yone,ytwo;
		        		
		        		yone = (int) (y1*rank1);
		        		ytwo = (int) (y1*rank2);
		        		
		        		if(rank1 == 0)
		        		{
		        			yone = (int) newHeight;
		        		}
		        		if(rank2==0)
		        		{
		        			ytwo= (int)newHeight;
		        		}
		        		lineBetweenTwoYears = new GLine(x1 * j, yone, x1 * (j+1), ytwo);
		        	
		        		lineBetweenTwoYears.setColor(Color.blue);
						add(lineBetweenTwoYears);
						GLabel name = new GLabel(listOfNameEntries.get(i).getName(), x1 * j, yone);
						add(name);
					}
		        	GLabel name = new GLabel(listOfNameEntries.get(i).getName(), x1 * 12, 12);
					add(name);
				
				}
			}


	private double drawGraphOutline() 
	{
		int constant = 80;
		
		double newHeight = getHeight() - constant;
		double width = getWidth();
		double height = getHeight() -50;
		
		//line for X axis
		GLine lineForXaxis = new GLine(0, height, width, height);
		add(lineForXaxis);
		GLine lineForXaxis1 = new GLine(0, newHeight, width, newHeight);
		add(lineForXaxis1);
		
		
		
		double x1 = width /12 ;
		
		for (int i = 1; i <= 12; i++) 
		{
				GLine twelveLines= new GLine(x1 * i, 0 , x1 * i, height);
				add(twelveLines);

		}
		
		String[] years = {"1900" ,"1910","1920","1930","1940","1950","1960","1970","1980","1990","2000","2010"};
		for (int i = 0; i < years.length; i++) 
		{
				GLabel entriesOnXaxis = new GLabel(years[i], x1 * i,height);
				add(entriesOnXaxis);
		}
		return x1;
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private ArrayList<NameSurferEntry> listOfNameEntries ;

	Random rand = new Random();
	
}
