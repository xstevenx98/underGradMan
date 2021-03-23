//package undergrad_man;

//This is the student object
public class Player extends Map
{
  char currentDirection;  //current direction of student
  char nextDirection;    //next direction student wants to go

  //Keeps track of pellets consumed to determine end of game
  int pelletsConsumed;

  //Last x and y location of student
  int prevLoc_X;
  int prevLoc_Y;
 
  //Current x and y location of student
  int currLoc_X;
  int currLoc_Y;
 
  //Which pellet the student is on top of
  int currPelletX;
  int currPelletY;

  //teleport is set to true when traveling through the teleport tunnels
  boolean teleport;
  
  //Constructor places student in initial location and orientation
  public Player(int x, int y)
  {
    teleport=false;
    pelletsConsumed=0;
    currPelletX = x/gridSQ-1;
    currPelletY = y/gridSQ-1;
    this.prevLoc_X=x;
    this.prevLoc_Y=y;
    this.currLoc_X = x;
    this.currLoc_Y = y;
    currentDirection='L';
    nextDirection='L';
  }

  //This function moves the student for one frame
  public void move()
  {
    int gridSQ=20;
    prevLoc_X=currLoc_X;
    prevLoc_Y=currLoc_Y;
     
      //Turning in the direction user wants next
     //We can only turn if we're in the center of a grid
    //Or if we are reversing our direction
    if (currLoc_X %20==0 && currLoc_Y%20==0 ||
       (nextDirection=='L' && currentDirection=='R')  ||
       (nextDirection=='R' && currentDirection=='L')  ||
       (nextDirection=='U' && currentDirection=='D')  ||
       (nextDirection=='D' && currentDirection=='U')
       )
    {
      switch(nextDirection)
      {
        case 'L':
           if ( isValid(currLoc_X-speedIncrement,currLoc_Y))
             currLoc_X -= speedIncrement;
           break;     
        case 'R':
           if ( isValid(currLoc_X+gridSQ,currLoc_Y))
             currLoc_X+= speedIncrement;
           break;     
        case 'U':
           if ( isValid(currLoc_X,currLoc_Y-speedIncrement))
             currLoc_Y-= speedIncrement;
           break;     
        case 'D':
           if ( isValid(currLoc_X,currLoc_Y+gridSQ))
             currLoc_Y+= speedIncrement;
           break;     
      }
    }
    //If we have not changed direction, then remain in current direction
    if (prevLoc_X==currLoc_X && prevLoc_Y==currLoc_Y)
    {
      switch(currentDirection)
      {
        case 'L':
           if ( isValid(currLoc_X-speedIncrement,currLoc_Y))
             currLoc_X -= speedIncrement;
           else if (currLoc_Y == 9*gridSQ && currLoc_X < 2 * gridSQ)
           {
             currLoc_X = hxw - gridSQ*1;
             teleport = true; 
           }
           break;   
        case 'R':
           if ( isValid(currLoc_X+gridSQ,currLoc_Y))
             currLoc_X+= speedIncrement;
           else if (currLoc_Y == 9*gridSQ && currLoc_X > hxw - gridSQ*2)
           {
             currLoc_X = 1*gridSQ;
             teleport=true;
           }
           break;     
        case 'U':
           if ( isValid(currLoc_X,currLoc_Y-speedIncrement))
             currLoc_Y-= speedIncrement;
           break;     
        case 'D':
           if ( isValid(currLoc_X,currLoc_Y+gridSQ))
             currLoc_Y+= speedIncrement;
           break;     
      }
    }
    //If we did change direction, then update current direction to the next direction
    else
    {
      currentDirection=nextDirection;
    }

  }

  //Updates the pellet the student is on top of
  public void updatePellet()
  {
    if (currLoc_X%gridSQ ==0 && currLoc_Y%gridSQ == 0)
    {
    currPelletX = currLoc_X/gridSQ-1;
    currPelletY = currLoc_Y/gridSQ-1;
    }
  } 
}
