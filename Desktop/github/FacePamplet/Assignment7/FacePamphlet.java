/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.TextField;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.text.ChangedCharSetException;

public class FacePamphlet extends Program 
					implements FacePamphletConstants
{

					/**
					 * This method has the responsibility for initializing the 
					 * interactors in the application, and taking care of any other 
					 * initialization that needs to be performed.
					 */
					public void init() 
					{
									NorthAndWestAreaOfCanvas();
									
									canvas = new FacePamphletCanvas();
									add(canvas);
									
									database = new FacePamphletDatabase();
									
									
				    }


					public void NorthAndWestAreaOfCanvas()
					{
								nameTextField = new JTextField( TEXT_FIELD_SIZE );
								nameTextField.setActionCommand("Name");
								nameTextField.addActionListener(this);
								add( new JLabel ( "Name" ) , NORTH );
								add ( nameTextField , NORTH);
								
								addButton = new JButton("Add");
								add(addButton , NORTH);
								addButton.addActionListener(this);
	
								
								deleteButton = new JButton("Delete");
								deleteButton.addActionListener(this);
								add(deleteButton , NORTH);
		
								lookupButton = new JButton("Lookup");
								lookupButton.addActionListener(this);
								add(lookupButton , NORTH);
								
								
								changeStatusTextField = new JTextField( TEXT_FIELD_SIZE );
								changeStatusTextField.setActionCommand("Change Status");
								changeStatusTextField.addActionListener(this);
								add( new JLabel ( EMPTY_LABEL_TEXT ) , WEST );
								add(changeStatusTextField , WEST );
								
								changeStatusButton = new JButton("Change Status");
								changeStatusButton.addActionListener(this);
								add(changeStatusButton , WEST);
								
								changePictureTextField = new JTextField( TEXT_FIELD_SIZE );
								changePictureTextField.setActionCommand("Change Picture");
								changePictureTextField.addActionListener(this);
								add( new JLabel ( EMPTY_LABEL_TEXT ) , WEST );
								add(changePictureTextField , WEST);
								
								changePictureButton = new JButton("Change Picture");
								changePictureButton.addActionListener(this);
								add(changePictureButton , WEST);
								
								addFriendTextField = new JTextField( TEXT_FIELD_SIZE );
								addFriendTextField.setActionCommand("Add Friend");
								addFriendTextField.addActionListener(this);
								add( new JLabel ( EMPTY_LABEL_TEXT ) , WEST );
								add(addFriendTextField , WEST);
								
								addFriendButton = new JButton("Add Friend");
								addFriendButton.addActionListener(this);
								add(addFriendButton , WEST);
					}
				    
				  
				    /**
				     * This class is responsible for detecting when the buttons are
				     * clicked or interactors are used, so you will have to add code
				     * to respond to these actions.
				     */
				    public void actionPerformed(ActionEvent e) 
				    {
								String cmd = e.getActionCommand();
								
								if( cmd.equals( "Add"))
								{
											FacePamphletProfile profile = new FacePamphletProfile(nameTextField.getText());
											print("\n Add :"  );
											if(database.containsProfile(nameTextField.getText()) == true)
											{
														canvas.showMessage(" The profile with the name " +profile.getName()+ " already exists" );;
											}
											else
											{
														database.addProfile(profile);
														canvas.showMessage("New profle created");
												
											}
											
								}
											
								
								if( cmd.equals( "Delete"))
								{
											//FacePamphletProfile profile = new FacePamphletProfile(nameTextField.getText());
											print("\n Delete :" +nameTextField.getText() );
					
											if (database.containsProfile(nameTextField.getText())  == false) 
											{
														canvas.showMessage("profile with name :" +nameTextField.getText()+ " does not exists");
											}
											else
											{
														database.deleteProfile(nameTextField.getText());
														canvas.showMessage("\n profile with name :" +nameTextField.getText()+ " is deleted");
											}		
								}
								
								if( cmd.equals( "Lookup"))
								{
											print("\n Lookup :" +nameTextField.getText() );
											
											if(database.containsProfile(nameTextField.getText()) == false)
											{
														canvas.showMessage("\n Profile with " +nameTextField.getText()+ " does not exists");
											}
											else
											{
																	FacePamphletProfile profile = database.getProfile(nameTextField.getText());
																	
																	String nameOfAllFriends = "" ;
																	Iterator<String> it = profile.getFriends();
																	while (it.hasNext()) 
																	{
																				nameOfAllFriends+= " " + it.next();
																	}
																	canvas.displayProfile(profile);
																	canvas.showMessage("\n" +profile.getName()+ " (" +profile.getStatus()+ ") :" +nameOfAllFriends );
											}
								}
								
								if( cmd.equals( "Change Status"))
								{
											print("\n Change Status :" );
											FacePamphletProfile profile ;
											if(database.containsProfile(nameTextField.getText()) == false)
											{
												canvas.showMessage("\n Select a profile to change the status of" );
											}
											else
											{
														profile= database.getProfile(nameTextField.getText());
														profile.setStatus(changeStatusTextField.getText());
														canvas.showMessage("\n status of " +nameTextField.getText()+ " is " +profile.getStatus());
											}
								}
								
								if( cmd.equals("Change Picture"))
								{
											print("\n Change Picture :");
											GImage image = null;
											FacePamphletProfile profile;
											if(database.getProfile(nameTextField.getText()) != null)
											{
														profile= database.getProfile(nameTextField.getText());
														profile.setImage(image);
														canvas.showMessage(" \n picture updated" );
											}
											else
											{
													canvas.showMessage("\n Select a profile to change the image of" );
											}
											
											
								}
								
								if( cmd.equals( "Add Friend"))
								{
											canvas.showMessage("\n Add Friend :" +addFriendTextField.getText() );
											FacePamphletProfile profile;
											if ( database.getProfile(nameTextField.getText()) != null)
											{
														profile= database.getProfile(nameTextField.getText());
														if(database.getProfile(addFriendTextField.getText())!= null)
														{
																	FacePamphletProfile friendProfile = database.getProfile(addFriendTextField.getText());
																	
																	if(profile.addFriend(addFriendTextField.getText() )== false)
																	{
																			friendProfile.addFriend(profile.getName());
																			
																			canvas.showMessage("\n frind  "  +friendProfile.getName()+ " added to the list" );
																	}
																	else
																	{
																		canvas.showMessage("\n frind  "  +addFriendTextField.getText()+ " already added to the list" );
																	}
														}
														else
														{
															canvas.showMessage("\n profile of " +addFriendTextField.getText()+ " does not exist");
														}
											}
											else
											{
												canvas.showMessage("\n Select a profile " );
											}
								}
					}
				    
				    private FacePamphletCanvas canvas;
				    
				    private JTextField nameTextField;
				    
				    private JButton addButton;
				    
				    private JButton deleteButton;
				    
				    private JButton lookupButton;
				    
				    private JTextField changeStatusTextField;
				    
				    private JButton changeStatusButton;
				    
				    private JTextField changePictureTextField;
				    
				    private JButton changePictureButton;
				    
				    private JTextField addFriendTextField;

				    private JButton addFriendButton;
				    
				    private FacePamphletDatabase database;
				    
}
