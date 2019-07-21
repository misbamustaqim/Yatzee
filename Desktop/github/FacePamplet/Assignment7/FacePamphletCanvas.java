/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
					
					/** 
					 * Constructor
					 * This method takes care of any initialization needed for 
					 * the display
					 */
					public FacePamphletCanvas() 
					{
						
					}
				
					
					/** 
					 * This method displays a message string near the bottom of the 
					 * canvas.  Every time this method is called, the previously 
					 * displayed message (if any) is replaced by the new message text 
					 * passed in.
					 */
					public void showMessage(String msg) 
					{
								if(lastlabel != null)
								{
									remove(lastlabel);
								}
							    displymsg = new GLabel(msg, getWidth()/2 , getHeight()- BOTTOM_MESSAGE_MARGIN);
								displymsg.setFont(MESSAGE_FONT);
								add(displymsg);
								lastlabel = displymsg;
								//remove(displymsg);
					}
					
					
					/** 
					 * This method displays the given profile on the canvas.  The 
					 * canvas is first cleared of all existing items (including 
					 * messages displayed near the bottom of the screen) and then the 
					 * given profile is displayed.  The profile display includes the 
					 * name of the user from the profile, the corresponding image 
					 * (or an indication that an image does not exist), the status of
					 * the user, and a list of the user's friends in the social network.
					 */
					public void displayProfile(FacePamphletProfile profile)
					{
								removeAll();
								double yAxixOfName = TOP_MARGIN;
								displyname = new GLabel(profile.getName() , LEFT_MARGIN, yAxixOfName);
								displyname.setFont(PROFILE_NAME_FONT);
								displyname.setColor(Color.blue);
								add(displyname);
								
								
								
								double yAxixOfImage = yAxixOfName + (displyname.getBounds().getHeight()) + IMAGE_MARGIN;
								if(profile.getImage() != null)
								{
										displyImage = new GImage(profile.getImage().getImage(), IMAGE_WIDTH, IMAGE_HEIGHT);
										
										add(displyImage);
								}
								else
								{
										GRect image1= new GRect(LEFT_MARGIN, yAxixOfImage, IMAGE_WIDTH, IMAGE_HEIGHT);
										image1.setFilled(true);
										image1.setColor(Color.WHITE);
										GLabel noImageLabel = new GLabel(" NO IMAGE ");
										noImageLabel.setLocation(LEFT_MARGIN/2 - noImageLabel.getWidth() , yAxixOfImage/2 - noImageLabel.getHeight());
										add(noImageLabel);
										add(image1);
								}
								
								double yAxixOfstatus = yAxixOfImage + IMAGE_HEIGHT + STATUS_MARGIN;
								String statusMessage = profile.getName() + " is " + profile.getStatus();
								displyStatus = new GLabel(statusMessage , LEFT_MARGIN , yAxixOfstatus);
								displyStatus.setFont(PROFILE_STATUS_FONT);
								add(displyStatus);
								
								
								GLabel friend = new GLabel("Friends :", getWidth() / 2, yAxixOfImage);
								friend.setFont(PROFILE_FRIEND_LABEL_FONT);
								add(friend);
								
								String nameOfAllFriends = "" ;
								Iterator<String> it = profile.getFriends();
								while (it.hasNext()) 
								{
											nameOfAllFriends+= " " + it.next();
								}
								
								displyFriends = new GLabel( nameOfAllFriends, getWidth() / 2, yAxixOfImage + friend.getHeight());
								displyFriends.setFont(PROFILE_FRIEND_FONT);
								add(displyFriends);
								
					}
					GLabel displymsg;
					
					GLabel displyname;
					
					GImage displyImage;
					
					GLabel displyStatus;
					
					GLabel displyFriends;
					
					GLabel lastlabel;
	
}
