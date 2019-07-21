import java.awt.Dialog;

import acm.graphics.GCanvas;
import acm.io.IODialog;
import acm.program.ConsoleProgram;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class MyYatzee extends GraphicsProgram implements YahtzeeConstants
{
			public void run()
			{
				//  number of players 
				numberOFPlayers = numberOfPyayers();
				//numberOFPlayers =1;
				
				
				finalTotal = new int[numberOFPlayers];
				//names of players
			
				upperScore = new int[numberOFPlayers];
				
				lowerScore = new int[numberOFPlayers];
								
				isCategoryUsed = new boolean[numberOFPlayers][ N_CATEGORIES];
				
				for (int i = 0; i < numberOFPlayers; i++) 
				{
						
				}
				
				playersName( numberOFPlayers );
				//nameOfPlayers = new String [1];
				//nameOfPlayers[0] = "Misba";
				
				// display for Yatzee game                                                                                                                                                                                                                                        
				initDisplay();
				
				//Start palying game
				for (int i = 0; i < 3; i++) 
				{
				//		for (int total = 0; total < finalTotal.length; total++) 
			//			{
							playGame(finalTotal);
			//			}
				
			
				}
			
				for (int j = 0; j < numberOFPlayers; j++)
				{
								
						display.updateScorecard(UPPER_SCORE, j, upperScore[j]);
						if(upperScore[j] > 40)
						{
								finalTotal[j] += 35;
								display.updateScorecard(UPPER_BONUS, j, 35);
								display.updateScorecard(TOTAL, j, finalTotal[j]);
						}
						else
						{
								display.updateScorecard(UPPER_BONUS, j, 0);
						}
				}
				
				for (int j = 0; j < numberOFPlayers; j++)
				{
								
						display.updateScorecard(LOWER_SCORE, j, lowerScore[j]);
				}
			}
			private int numberOfPyayers()
			{
				IODialog dialog = getDialog();
				int result;
				while (true)
				{
						result = dialog.readInt(" Enter the number of players :");
						if (( 0 < result  ) && (result <= MAX_PLAYERS))
						{
								return result;
								
						}
						dialog.println(" Number is invalid enter again");
				}
			}
			

			private void playersName(int numberOFPlayers2) 
			{
				nameOfPlayers = new String [numberOFPlayers];
				for (int i = 0; i < numberOFPlayers; i++)
				{
					IODialog dialog = getDialog();
					nameOfPlayers[i] = dialog.readLine( " Enter name of player : "  +(i+1));
				}
			
			}
			
			private void initDisplay()
			{
				display = new YahtzeeDisplay(getGCanvas(), nameOfPlayers);
			}
		

		
				
			private void playGame( int[] finalTot) 
			{
				clickOnRoleDiceButton(finalTot);
		  
			}


			//display screen and first msg to click on roll button
			private void clickOnRoleDiceButton( int[] finalTot)
			{
				for (int i = 0; i < numberOFPlayers; i++) 
				{
					display.printMessage(nameOfPlayers[0]+ "'s turn.  Click Roll Dice button to roll the dice");
					display.waitForPlayerToClickRoll(i);
					
					// Display Dice
					rollDice(i , finalTot);
				}
			}


			// clicks on roll button and sets values on each dice 
			private void rollDice(int playerNumber , int[] finalTot) 
			{
					diceArray = new int[numberOfDice];
					for (int i = 0; i < numberOfDice ; i++)
					{
							generateNumbersOnDice(i); 
					}
					display.displayDice(diceArray);	
					//category = display.waitForPlayerToSelectCategory();
					display.printMessage(" Select the dice you wish to re-roll and click Roll Again");
					for (int i = 0; i < 2; i++)
					{
						twoMoreTurns();
						
					}
					display.printMessage(" Select the category for this role");
					selectCategoryOfDiceConfig(playerNumber  ,  finalTot);
			}

			// generates random number for each dice
			private void generateNumbersOnDice(int i) {
				diceArray[i] = rgen.nextInt(1 , 6);
			}


			// Select and deselect dice and changes value of selected dice
			private void twoMoreTurns() 
			{
					display.waitForPlayerToSelectDice();
					for (int i = 0; i < N_DICE ; i++)
					{
							if(display.isDieSelected(i) == true )
							{
								generateNumbersOnDice(i);
								
							}
					}
					
					/* diceArray[0]=1;
					diceArray[1]=2;
					diceArray[2]=3;
					diceArray[3]=4;
					diceArray[4]=5; */
					
					display.displayDice(diceArray);	
					
			}
			
			//selects category from 13 options Yatzee borad
			private void selectCategoryOfDiceConfig(int playerNumber,int[] finalTot) {
					category = display.waitForPlayerToSelectCategory();
				
					computation(playerNumber  ,finalTot);
			}


			//calculations for 13 categories
			private void computation( int playerNumber, int[] finalTotal)
			{
					summation = 0;
					switch (category)
					{
						case ONES:
							if(isCategoryUsed[playerNumber][ONES]== false)
							{
								addSameNumbers( category ,  finalTotal);
								upperScore[playerNumber] += summation;
								isCategoryUsed[playerNumber][ONES]= true;
							}
							else
							{
								displayErrorMsg();
								return;
							}
							break;
						
						case TWOS:
							if(isCategoryUsed[playerNumber][TWOS]== false)
							{
								addSameNumbers( category ,finalTotal);
								upperScore[playerNumber] += summation;
								isCategoryUsed[playerNumber][TWOS]= true;
							}
							else
							{
								displayErrorMsg();
								return;
							}
								break;
														
						case THREES:
							if(isCategoryUsed[playerNumber][THREES]== false)
							{
								addSameNumbers( category ,finalTotal);
								upperScore[playerNumber] += summation;
								isCategoryUsed[playerNumber][THREES]= true;
							}
							else
							{
								displayErrorMsg();
								return;
							}
								break;
							
						case FOURS:
							if(isCategoryUsed[playerNumber][FOURS]== false)
							{
								addSameNumbers( category ,finalTotal);
								upperScore[playerNumber] += summation;
								isCategoryUsed[playerNumber][FOURS]= true;
							}
								else
								{
									displayErrorMsg();
									return;
								}
								break;
							
						case FIVES:
							if(isCategoryUsed[playerNumber][FIVES]== false)
							{
								addSameNumbers( category , finalTotal);
								upperScore[playerNumber] += summation;
								isCategoryUsed[playerNumber][FIVES]= true;
							}

							else
							{
								displayErrorMsg();
								return;
							}
								break;
							
						case SIXES:
							if(isCategoryUsed[playerNumber][SIXES]== false)
							{
								addSameNumbers( category , finalTotal);
								upperScore[playerNumber] += summation;
								isCategoryUsed[playerNumber][SIXES]= true;
							}
								else
								{
									displayErrorMsg();
									return;
								}
								break;
							
						case UPPER_SCORE:
								
								break;
							
						case UPPER_BONUS:
							
								break;
							
						case THREE_OF_A_KIND:
							if(isCategoryUsed[playerNumber][THREE_OF_A_KIND]== false)
							{
								threeOfAKind( finalTotal);
								lowerScore[playerNumber] += summation;
								isCategoryUsed[playerNumber][THREE_OF_A_KIND]= true;
							}
								else
								{
									displayErrorMsg();
									return;
								}
								break;
							
						case FOUR_OF_A_KIND:
							if(isCategoryUsed[playerNumber][FOUR_OF_A_KIND]== false)
							{
								threeOfAKind(  finalTotal);
								lowerScore[playerNumber] += summation;
								isCategoryUsed[playerNumber][FOUR_OF_A_KIND]= true;
							}
								else
								{
									displayErrorMsg();
									return;
								}
								break;
							
						case FULL_HOUSE:
							if(isCategoryUsed[playerNumber][FULL_HOUSE]== false)
							{
								fullHouse(  finalTotal);
								lowerScore[playerNumber] += summation;
								isCategoryUsed[playerNumber][FULL_HOUSE]= true;
							}
							else
							{
								displayErrorMsg();
								return;
							}
								break;
								
						case SMALL_STRAIGHT:
							if(isCategoryUsed[playerNumber][SMALL_STRAIGHT]== false)
							{
								smallStraight( finalTotal);
								lowerScore[playerNumber] += summation;
								isCategoryUsed[playerNumber][SMALL_STRAIGHT]= true;
							}
								else
								{
									displayErrorMsg();
									return;
								}
								break;
								
						case LARGE_STRAIGHT:
							if(isCategoryUsed[playerNumber][LARGE_STRAIGHT]== false)
							{
								largeStraight ( finalTotal);
								lowerScore[playerNumber] += summation;
								isCategoryUsed[playerNumber][LARGE_STRAIGHT]= true;
							}
								else
								{
									displayErrorMsg();
									return;
								}
								break;
						case YAHTZEE:
							if(isCategoryUsed[playerNumber][YAHTZEE]== false)
							{
								yatzee(finalTotal);
								lowerScore[playerNumber] += summation;
								isCategoryUsed[playerNumber][YAHTZEE]= true;
							}
								else
								{
									displayErrorMsg();
									return;
								}
								break;
						case CHANCE:
							if(isCategoryUsed[playerNumber][CHANCE]== false)
							{
								additionOfAll( finalTotal);
								lowerScore[playerNumber] += summation;
								isCategoryUsed[playerNumber][CHANCE]= true;
							}
								else
								{
									displayErrorMsg();
									return;
								}
								break;
						case LOWER_SCORE:
	
								break;
								
						case TOTAL:
							
								break;	
							
						default:
								break;
					}
					
					display.updateScorecard(category, playerNumber, summation);
					
					finalTotal[playerNumber] = finalTotal[ playerNumber] + summation;
					display.updateScorecard(TOTAL, playerNumber, finalTotal[ playerNumber]);
					
			}
			private void displayErrorMsg() {
				display.printMessage("You cannot use same choice again and again. Please select another choice");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}



			
			// for category 1,2,3,4,5,6
			private void addSameNumbers( int category , int[] finalTot)
			{
							
					for (int i = 0; i < diceArray.length; i++) 
					{
							if (diceArray[ i] == category+1)
							{
								summation += diceArray[i];
							}
					}
				
			}


			private void yatzee( int[] finalTot) {
				int temp = diceArray[0];
				for (int i = 1; i < diceArray.length; i++) 
				{
					if(temp != diceArray[i])
					{
						return;
					}
				}
				summation=50;
			}


			private void threeOfAKind(int[] finalTot)
			{
					
					int count = 0;
					for (int i = 0; i < diceArray.length; i++) 
					{
						count=1;
						for (int j = i+1; j < diceArray.length; j++) {
							if (diceArray[j] == diceArray[i])
							{
									count++;
							}
							
						}
						if( (count >= category -5) )
						{
							for (int k = 0; k < diceArray.length; k++) 
							{
								summation +=diceArray[k];
							}
							return;
						}
							
					}
					
				
			}

			private void fullHouse( int[] finalTotal) {
				
					int count = 1;
					for (int i = 0; i < diceArray.length; i++) 
					{
						for (int j = i+1; j < diceArray.length; j++) {
							if (diceArray[j] == diceArray[i])
							{
								count++;
							}
						
						}
						if( (count == 5) )
						{
							for (int k = 0; k < diceArray.length; k++) 
							{
								summation = 25;
							}
							return;
						}
					}
			}
			
			private void smallStraight(int[] finalTot) {
				 int[] diceArray1 = new int[N_DICE];
		
				 for (int i = 0; i < diceArray.length; i++) 
				 {
					 	diceArray1[i] = diceArray[i];
						 print("\n diceArray1[i] " +diceArray1[i] );
				 }
				 
				 for (int i = 0; i < diceArray1.length; i++) 
					{
						for (int j = i+1; j < diceArray1.length; j++) 
						{
							if (diceArray1[i] < diceArray1[j])
							{
									int temp = diceArray1[i];
									diceArray1[i] = diceArray1[j];
									diceArray1[j] = temp;
							}
							
						}
						
					}
				 	int count =0 ;
				 	for (int i = 0; i < diceArray.length-1; i++) 
					{
						
							if (diceArray1[i] - diceArray1[i +1] ==1)
							{
								count++;
							}
					}
				 	
				 	if( (count == 3) )
					{
							summation = 30;
							return;
					}
					
			}
			
			
			private void largeStraight(int[] finalTotal2) {
				 int[] diceArray1 = new int[N_DICE];
					
				 for (int i = 0; i < diceArray.length; i++) 
				 {
					 	diceArray1[i] = diceArray[i];
						 print("\n diceArray1[i] " +diceArray1[i] );
				 }
				 
				 for (int i = 0; i < diceArray1.length; i++) 
					{
						for (int j = i+1; j < diceArray1.length; j++) 
						{
							if (diceArray1[i] < diceArray1[j])
							{
									int temp = diceArray1[i];
									diceArray1[i] = diceArray1[j];
									diceArray1[j] = temp;
							}
							
						}
						
					}
				 	int count =0 ;
				 	for (int i = 0; i < diceArray.length-1; i++) 
					{
						
							if (diceArray1[i] - diceArray1[i +1] ==1)
							{
								count++;
							}
					}
				 	
					 if( (count == 4) )
					{
						
							summation = 40;
							return;
					}
		
			}
			
			private void additionOfAll( int[] finalTot )
			{
				 for (int i = 0; i < diceArray.length; i++) 
				 {
					 	summation += diceArray[i];
				 }
			}


		private int numberOFPlayers;
		private YahtzeeDisplay display;
		private String[] nameOfPlayers;
		private int[] diceArray;
		int numberOfDice = N_DICE;
		int category;
		private int summation = 0;
		private int[] finalTotal;
		private int[] upperScore;
		private int[] lowerScore;
		private boolean[][] isCategoryUsed;
		RandomGenerator rgen = RandomGenerator.getInstance();
		
}




























