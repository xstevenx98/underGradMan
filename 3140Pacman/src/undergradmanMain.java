//package undergrad_man;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class undergradmanMain extends JApplet implements KeyListener
{
	//Create a new board 
	Board board=new Board();
	
	//Timer for requesting new frames to be drawn
	//Used to fire one or more ActionEvents at certain time intervals
	javax.swing.Timer frameTime;
	
	public undergradmanMain()
	{
		JFrame frame = new JFrame();            //Creates the window frame
		frame.setSize(420,460);		 		   //Size of the window
		frame.add(board,BorderLayout.CENTER); //Adds board to frame
		frame.setResizable(false);           //Disables resizing
		frame.setLocationRelativeTo(null);	//Place window in the center
		frame.setVisible(true);  		   //Makes the frame visible
		
		//Set listener for actions
		board.addKeyListener(this);
		
		//Begins a new game
		board.play=1;
		
		//Calling first frame to start game
		frameStep(true);
		
		//Creating a timer
		//Used to to call frame every 30 milliseconds
		frameTime = new javax.swing.Timer(30,
		new ActionListener(){
			public void actionPerformed(ActionEvent e){
				   frameStep(false);
			}
		});
		
		//Starting the timer
		frameTime.start();
		
		//Request to set board to a focused state
		board.requestFocus();
		
	}
	
	  //Function to step one frame forward in game
	  public void frameStep(boolean newGame)
	  {
	    //Display start screen if true
	    if (board.startScreen)
	    {
	      board.repaint();
	      return;
	    }
	 
	    //Display winner or loser screen if true
	    else if (board.winnerScreen || board.loserScreen)
	    {
	      board.repaint();
	      return;
	    }

	    //Move all elements and update pellet status, if normal game state 
	    if (!newGame)
	    {
	      //Move player
	      board.student.move();

	      //Move the professors and update the pellets
	      board.prof1.move(); 
	      board.prof2.move(); 
	      board.prof3.move(); 
	      board.prof4.move(); 
	      
	      board.student.updatePellet();
	      board.prof1.updatePellet();
	      board.prof2.updatePellet();
	      board.prof3.updatePellet();
	      board.prof4.updatePellet();
	    }

	    //Reseting the board if we have a new game or the player has died
	    if (board.freeze || newGame)
	    {
	      //Stop advancing frames momentarily
	      frameTime.stop();

	      //Move all elements of the game back to starting positions
	      board.student.currentDirection='L';
	      board.student.nextDirection='L';
	      board.student.currLoc_X = 200;
	      board.student.currLoc_Y = 300;
	      board.prof1.currLoc_X = 180;
	      board.prof1.currLoc_Y = 180;
	      board.prof2.currLoc_X = 200;
	      board.prof2.currLoc_Y = 180;
	      board.prof3.currLoc_X = 220;
	      board.prof3.currLoc_Y = 180;
	      board.prof4.currLoc_X = 220;
	      board.prof4.currLoc_Y = 180;

	      //Advance a frame to display main state
	      board.repaint(0,0,600,600);

	      //Begin advancing frames again
	      board.freeze=false;
	      frameTime.start();
	    }
	    //normal game state, advance one frame
	    else
	    {
	      board.repaint();
	    }
	  }
	  //Handles user key presses
	  public void keyPressed(KeyEvent e) 
	  {
	    //Pressing a key in the start screen starts a game 
	    if (board.startScreen)
	    {
	      board.startScreen = false;
	      return;
	    }
	    //Pressing a key in the winner or loser screen returns to start screen
	    else if (board.winnerScreen || board.loserScreen)
	    {
	      board.startScreen = true;
	      board.winnerScreen = false;
	      board.loserScreen = false;
	      return;
	    }

	    //Player controls
	    switch(e.getKeyCode())
	    {
	      case KeyEvent.VK_LEFT:
	       board.student.nextDirection='L';
	       break;     
	      case KeyEvent.VK_RIGHT:
	       board.student.nextDirection='R';
	       break;     
	      case KeyEvent.VK_UP:
	       board.student.nextDirection='U';
	       break;     
	      case KeyEvent.VK_DOWN:
	       board.student.nextDirection='D';
	       break;     
	    }

	    board.repaint();
	  }
	  
	  public void keyReleased(KeyEvent e){}
	  public void keyTyped(KeyEvent e){}
	
	 //Main function creates a new undergradman instance
	 public static void main(String [] args)
	 {
	      undergradmanMain ugm = new undergradmanMain();
	 } 

}
