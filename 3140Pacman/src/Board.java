//package undergrad_man;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.lang.Math;

public class Board extends JPanel 
{
	  //Images used for the game
	  Image bulldogR = new ImageIcon(Board.class.getResource("bulldog.png")).getImage();
	  Image bulldogL = new ImageIcon(Board.class.getResource("bulldogL1T.png")).getImage();
	  Image bulldogU = new ImageIcon(Board.class.getResource("bulldogUp.png")).getImage();
	  Image bulldogD = new ImageIcon(Board.class.getResource("bulldogDown.png")).getImage();
	  Image profR = new ImageIcon(Board.class.getResource("prof.png")).getImage();
	  Image profL = new ImageIcon(Board.class.getResource("profL.png")).getImage();
	  Image startScreenImage = new ImageIcon(Board.class.getResource("tempStart.jpg")).getImage();
	  Image gameOverImage = new ImageIcon(Board.class.getResource("endScreen.jpg")).getImage();
	  Image winScreenImage = new ImageIcon(Board.class.getResource("winScreen.jpg")).getImage();

	  //Initialize the player and the enemies
	  Player student = new Player(200,300);
	  Enemy prof1 = new Enemy(180,180);
	  Enemy prof2 = new Enemy(200,180);
	  Enemy prof3 = new Enemy(220,180);
	  Enemy prof4 = new Enemy(220,180);

	  //Student number of lives and score
	  int score;
	  int lives=2;

	  //Contains the game map, passed to player and enemies
	  boolean[][] state;

	  //Contains the state of all pellets
	  boolean[][] pellets;

	  //Size of one square and height/width in the game
	  int gridSQ; 
	  int hxw;		
	  
	  //Font used for Score
	  Font font = new Font("Helvetica",Font.BOLD, 12);
	  
	  //Flag for start, winner, loser, and play
	  boolean freeze; 
	  boolean startScreen;
	  boolean winnerScreen = false;
	  boolean loserScreen = false;
	  int play;

	  //Color of maze
	  public Color mazeColor;

	  //Constructor for board
	  public Board() 
	  { 
		score=0;
	    freeze=false;
	    hxw=400;
	    gridSQ=20;
	    play=0;
	    startScreen = true;
	    mazeColor = new Color(140, 0, 26); //burgundy color
	  }
  
	  //Displays number of lives
	  public void drawLives(Graphics g)
	  {
	    //Clear the bottom bar
		g.setColor(Color.BLACK);
	    g.fillRect(0,hxw+5,600,gridSQ);
	    for(int i = 0;i<lives;i++)
	    {
	      g.drawImage(bulldogR, gridSQ*(i+1), hxw+5, this);
	    }
	  }
	  
	  /*
	  *Draws the board. Rectangles are placed in manually to build the maze.
	  *MapUpdate is called to make those coordinates untraversable  
	  */
	  public void drawBoard(Graphics g)
	  {
		    //Colors board black
	        g.setColor(Color.BLACK);
	        g.fillRect(0,0,600,600);

	        //Draws the border for maze
	        g.setColor(mazeColor); 
	        g.drawRect(19,19,382,382);

	        //Rectangles that make up the maze
	        g.fillRect(40,40,60,20);
	          MapUpdate(40,40,60,20);
	        g.fillRect(120,40,60,20);
	          MapUpdate(120,40,60,20);
	        g.fillRect(200,20,20,40);
	          MapUpdate(200,20,20,40);
	        g.fillRect(240,40,60,20);
	          MapUpdate(240,40,60,20);
	        g.fillRect(320,40,60,20);
	          MapUpdate(320,40,60,20);
	        g.fillRect(40,80,60,20);
	          MapUpdate(40,80,60,20);
	        g.fillRect(160,80,100,20);
	          MapUpdate(160,80,100,20);
	        g.fillRect(200,80,20,60);
	          MapUpdate(200,80,20,60);
	        g.fillRect(320,80,60,20);
	          MapUpdate(320,80,60,20);

	        g.fillRect(20,120,80,60);
	          MapUpdate(20,120,80,60);
	        g.fillRect(320,120,80,60);
	          MapUpdate(320,120,80,60);
	        g.fillRect(20,200,80,60);
	          MapUpdate(20,200,80,60);
	        g.fillRect(320,200,80,60);
	          MapUpdate(320,200,80,60);

	        g.fillRect(160,160,40,20);
	          MapUpdate(160,160,40,20);
	        g.fillRect(220,160,40,20);
	          MapUpdate(220,160,40,20);
	        g.fillRect(160,180,20,20);
	          MapUpdate(160,180,20,20);
	        g.fillRect(160,200,100,20);
	          MapUpdate(160,200,100,20);
	        g.fillRect(240,180,20,20);
	          MapUpdate(240,180,20,20);

	        g.fillRect(120,120,60,20);
	          MapUpdate(120,120,60,20);
	        g.fillRect(120,80,20,100);
	          MapUpdate(120,80,20,100);
	        g.fillRect(280,80,20,100);
	          MapUpdate(280,80,20,100);
	        g.fillRect(240,120,60,20);
	          MapUpdate(240,120,60,20);

	        g.fillRect(280,200,20,60);
	          MapUpdate(280,200,20,60);
	        g.fillRect(120,200,20,60);
	          MapUpdate(120,200,20,60);
	        g.fillRect(160,240,100,20);
	          MapUpdate(160,240,100,20);
	        g.fillRect(200,260,20,40);
	          MapUpdate(200,260,20,40);

	        g.fillRect(120,280,60,20);
	          MapUpdate(120,280,60,20);
	        g.fillRect(240,280,60,20);
	          MapUpdate(240,280,60,20);

	        g.fillRect(40,280,60,20);
	          MapUpdate(40,280,60,20);
	        g.fillRect(80,280,20,60);
	          MapUpdate(80,280,20,60);
	        g.fillRect(320,280,60,20);
	          MapUpdate(320,280,60,20);
	        g.fillRect(320,280,20,60);
	          MapUpdate(320,280,20,60);

	        g.fillRect(20,320,40,20);
	          MapUpdate(20,320,40,20);
	        g.fillRect(360,320,40,20);
	          MapUpdate(360,320,40,20);
	        g.fillRect(160,320,100,20);
	          MapUpdate(160,320,100,20);
	        g.fillRect(200,320,20,60);
	          MapUpdate(200,320,20,60);

	        g.fillRect(40,360,140,20);
	          MapUpdate(40,360,140,20);
	        g.fillRect(240,360,140,20);
	          MapUpdate(240,360,140,20);
	        g.fillRect(280,320,20,40);
	          MapUpdate(280,320,20,60);
	        g.fillRect(120,320,20,40);
	          MapUpdate(120,320,20,60);
	        drawLives(g);
	  }
	  
	  /*
	   * This function is called when drawing the maze
	   * The portion of the map that has a barrier are invalid locations to place pellets,
	   * So the map and pellet arrays are updated accordingly to note that these are invalid locations
	   * to travel or place pellets
	   */
	  public void MapUpdate(int x,int y, int width, int height)
	  {
	    for (int i =x/gridSQ; i<x/gridSQ+width/gridSQ;i++)
	    {
	      for (int j=y/gridSQ;j<y/gridSQ+height/gridSQ;j++)
	      {
	        state[i-1][j-1]=false;
	        pellets[i-1][j-1]=false;
	      }
	    }
	  }
	  
	  //Draws the pellets on the screen
	  public void drawPellets(Graphics g)
	  {
	        g.setColor(Color.YELLOW);
	        for (int i=1; i<20; i++)
	        {
	          for (int j=1; j<20; j++)
	          {
	        	//if the location is not part of the map, draw the pellet  
	            if (pellets[i-1][j-1])
	            	g.fillOval(i*20+8,j*20+8,4,4);
	          }
	        }
	  }

	  //Draws one individual pellet
	  //Used to redraw pellets that enemies have run over
	  public void fillPellet(int x, int y, Graphics g)
	  {
	    g.setColor(Color.YELLOW);
	    g.fillOval(x*20+28,y*20+28,4,4);
	  }
	  
	  //This is the main function that draws one entire frame of the game
	  public void paint(Graphics g)
	  {
	    //If this is the start screen, then draw it and return
	    if (startScreen)
	    {
	      g.setColor(Color.BLACK);
	      g.fillRect(0,0,600,600);
	      g.drawImage(startScreenImage,0,0,Color.BLACK,null);

	      play=1;
	      return;
	    } 

	    //If this is the winner screen, then draw it and return
	    else if (winnerScreen)
	    {
	      g.setColor(Color.BLACK);
	      g.fillRect(0,0,600,600);
	      g.drawImage(winScreenImage,0,0,Color.BLACK,null);
	      
	      play = 1;
	      return;
	    }

	    //If this is the loser screen, then draw it and return
	    else if (loserScreen)
	    {
	      g.setColor(Color.BLACK);
	      g.fillRect(0,0,600,600);
	      g.drawImage(gameOverImage,0,0,Color.BLACK,null);
	      
	      play = 1;
	      return;
	    }
   
	    //dead is set to true when student has lost a life  
	    boolean dead=false;
	    
	    //Initialize the game
	    if (play==1)
	    {
	      resetGame();
	      student = new Player(200,300);
	      prof1 = new Enemy(180,180);
	      prof2 = new Enemy(200,180);
	      prof3 = new Enemy(220,180);
	      prof4 = new Enemy(220,180);
	      score = 0;
	      
	      drawBoard(g);
	      drawPellets(g);
	      drawLives(g);
	      
	      //Send the game map to player and all ghosts
	      student.stateUpdate(state);
	      //Don't let the player go in the enemy box
	      student.state[9][7]=false; 
	      prof1.stateUpdate(state);
	      prof2.stateUpdate(state);
	      prof3.stateUpdate(state);
	      prof4.stateUpdate(state);
	   
	      //The top bar for displaying Score
	      g.setColor(Color.YELLOW);
	      g.setFont(font);
	      g.drawString("Score: "+(score),20,15);
	      play=0;
	    }
 
	    //Collision Detection
	    if (student.currLoc_X == prof1.currLoc_X && Math.abs(student.currLoc_Y-prof1.currLoc_Y) < 10)
	      dead=true;
	    else if (student.currLoc_X == prof2.currLoc_X && Math.abs(student.currLoc_Y-prof2.currLoc_Y) < 10)
	      dead=true;
	    else if (student.currLoc_X == prof3.currLoc_X && Math.abs(student.currLoc_Y-prof3.currLoc_Y) < 10)
	      dead=true;
	    else if (student.currLoc_X == prof4.currLoc_X && Math.abs(student.currLoc_Y-prof4.currLoc_Y) < 10)
	      dead=true;
	    else if (student.currLoc_Y == prof1.currLoc_Y && Math.abs(student.currLoc_X-prof1.currLoc_X) < 10)
	      dead=true;
	    else if (student.currLoc_Y == prof2.currLoc_Y && Math.abs(student.currLoc_X-prof2.currLoc_X) < 10)
	      dead=true;
	    else if (student.currLoc_Y == prof3.currLoc_Y && Math.abs(student.currLoc_X-prof3.currLoc_X) < 10)
	      dead=true;
	    else if (student.currLoc_Y == prof4.currLoc_Y && Math.abs(student.currLoc_X-prof4.currLoc_X) < 10)
	      dead=true;

	    //Kill the Student if there has been a collision
	    if (dead && !freeze)
	    {
	      //Decrement lives, and update screen to reflect that
	      //Set appropriate flags and timers 
	      lives--;
	      freeze=true;
	      drawLives(g);
	    }
	    
	    //if all lives have been depleted, then transition into loser screen
        if (lives==-1)
        {
          loserScreen=true;
          return;
        }

	    //Delete the student and enemies traces that are left behind
	    g.setColor(Color.BLACK);
	    g.fillRect(student.prevLoc_X,student.prevLoc_Y,20,20);
	    g.fillRect(prof1.prevLoc_X,prof1.prevLoc_Y,20,20);
	    g.fillRect(prof2.prevLoc_X,prof2.prevLoc_Y,20,20);
	    g.fillRect(prof3.prevLoc_X,prof3.prevLoc_Y,20,20);
	    g.fillRect(prof4.prevLoc_X,prof4.prevLoc_Y,20,20);

	    //Consume pellets
	    if (pellets[student.currPelletX][student.currPelletY]) 
	    {
	      //Increment pellets eaten value to track for the end of the game
	      student.pelletsConsumed++;

	      //Deletes the pellet that has been consumed 
	      pellets[student.currPelletX][student.currPelletY]=false;

	      //Increment the student score
	      score += 50;

	      //Update the screen to reflect the new student score
	      g.setColor(Color.BLACK);
	      g.fillRect(0,0,600,20);
	      g.setColor(Color.YELLOW);
	      g.setFont(font);
	      g.drawString("Score: "+(score),20,15);

	      //If this was the last remaining pellet, then transition into winner screen
	      if (student.pelletsConsumed == 173)
	      {
	    	winnerScreen = true;
	        return;
	      }
	    }

	    //Replace pellets that have been run over by enemies
	    if ( pellets[prof1.prevPelletX][prof1.prevPelletY])
	      fillPellet(prof1.prevPelletX,prof1.prevPelletY,g);
	    if ( pellets[prof2.prevPelletX][prof2.prevPelletY])
	      fillPellet(prof2.prevPelletX,prof2.prevPelletY,g);
	    if ( pellets[prof3.prevPelletX][prof3.prevPelletY])
	      fillPellet(prof3.prevPelletX,prof3.prevPelletY,g);
	    if ( pellets[prof4.prevPelletX][prof4.prevPelletY])
	      fillPellet(prof4.prevPelletX,prof4.prevPelletY,g);

	    //Draw the enemies
	    if (prof1.frameCount < 5)
	    {
	      //Draw first frame of enemies //Right side
	      g.drawImage(profR,prof1.currLoc_X,prof1.currLoc_Y,Color.BLACK,null); 
	      g.drawImage(profR,prof2.currLoc_X,prof2.currLoc_Y,Color.BLACK,null); 
	      g.drawImage(profR,prof3.currLoc_X,prof3.currLoc_Y,Color.BLACK,null); 
	      g.drawImage(profR,prof4.currLoc_X,prof4.currLoc_Y,Color.BLACK,null);
	     	
	      prof1.frameCount++;
	    }
	    else
	    {
	      //Draw second frame of enemies //Left side 
	      g.drawImage(profL,prof1.currLoc_X,prof1.currLoc_Y,Color.BLACK,null); 
	      g.drawImage(profL,prof2.currLoc_X,prof2.currLoc_Y,Color.BLACK,null); 
	      g.drawImage(profL,prof3.currLoc_X,prof3.currLoc_Y,Color.BLACK,null); 
	      g.drawImage(profL,prof4.currLoc_X,prof4.currLoc_Y,Color.BLACK,null);
	      if (prof1.frameCount >=15)
	        prof1.frameCount=0;
	      else
	        prof1.frameCount++;
	    }

	    switch(student.currentDirection)
	    {
	    case 'L':
	    	g.drawImage(bulldogL,student.currLoc_X,student.currLoc_Y,Color.BLACK,null); //LeftImage
	    	break;     
	    case 'R':
	    	g.drawImage(bulldogR,student.currLoc_X,student.currLoc_Y,Color.BLACK,null); //RightImage
	    	break;     
	    case 'U':
	    	g.drawImage(bulldogU,student.currLoc_X,student.currLoc_Y,Color.BLACK,null); //UpImage
	    	break;     
	    case 'D':
	    	g.drawImage(bulldogD,student.currLoc_X,student.currLoc_Y,Color.BLACK,null); //DownImage
	    	break;     
	    }

	    //ReDraws the border around the game in case it was overwritten by student/enemy movement
	    g.setColor(mazeColor);
	    g.drawRect(19,19,382,382);

	  }
	  
	  //Reset occurs on a new game
	  public void resetGame()
	  {
	    lives=2;
	    state = new boolean[20][20];
	    pellets = new boolean[20][20];

	    //Clears the state and pellets arrays
	    for(int i=0;i<20;i++)
	    {
	      for(int j=0;j<20;j++)
	      {
	        state[i][j]=true;
	        pellets[i][j]=true;
	      }
	    }

	    //For there to be no pellets in the middle of board
	    for(int i = 5;i<14;i++)
	    {
	      for(int j = 5;j<12;j++)
	      {
	        pellets[i][j]=false;
	      }
	    }
	    pellets[9][7] = false;
	    pellets[8][8] = false;
	    pellets[9][8] = false;
	    pellets[10][8] = false;
	  }
}
