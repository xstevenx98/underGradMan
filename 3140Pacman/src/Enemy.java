//package undergrad_man;

import java.util.HashSet;
import java.util.Set;

//Controls the movement of the professors 
public class Enemy extends Map
{ 
  //direction of the enemy
  char direction;

  //Last x and y location of enemy
  int prevLoc_X;
  int prevLoc_Y;

  //Current x and y location of enemy
  int currLoc_X;
  int currLoc_Y;

  //Which pellet the enemy is on top of
  int currPelletX,currPelletY;

  //The pellet the enemy was previously on top of
  int prevPelletX,prevPelletY;

  //Constructor places enemies and updates their states
  public Enemy(int x, int y)
  {
    currPelletX=x/gridSQ-1;
    currPelletY=x/gridSQ-1;
    prevPelletX=currPelletX;
    prevPelletY=currPelletY;
    this.prevLoc_X = x;
    this.prevLoc_Y = y;
    this.currLoc_X = x;
    this.currLoc_Y = y;
    direction='L';
  }

  //Random move function for ghost
  public void move()
  {
    prevLoc_X=currLoc_X;
    prevLoc_Y=currLoc_Y;
 
    //If a decision is made, then pick a new direction randomly
    if (decisiveness())
    {
      direction = newDirection();
    }
    
    //If the direction is valid, then proceed that way
    switch(direction)
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
  
  //Determines if the enemy has to make a decision based on their location
  public boolean decisiveness()
  {
	  if ( currLoc_X%gridSQ==0 && currLoc_Y%gridSQ==0 )
	  {
		  return true;
	  }
	  return false;
  }
  
  //A new direction is chosen randomly for the enemy to move
  public char newDirection()
  { 
	  int random;
	  char reverseDirection='U';
	  int lookUpX=currLoc_X;
	  int lookUpY=currLoc_Y;
	  Set<Character> set = new HashSet<Character>();
	  switch(direction)
	  {
	  case 'L':
		  reverseDirection='R';
		  break;     
	  case 'R':
		  reverseDirection='L';
		  break;     
	  case 'U':
		  reverseDirection='D';
		  break;     
	  case 'D':
		  reverseDirection='U';
		  break;     
	  }
	  
	  char newDirection = reverseDirection;
	  
	  //While we still have not found a valid direction
	  while (newDirection == reverseDirection || !isValid(lookUpX,lookUpY))
	  {
		  //If every location had been attempted, then reverse direction and break the loop
		  if (set.size()==3)
		  {
			  newDirection=reverseDirection;
			  break;
		  }
		  
		  lookUpX=currLoc_X;
		  lookUpY=currLoc_Y;
		  
		  //We Randomly choose a direction
		  random = (int)(Math.random()*4) + 1;
		  if (random == 1)
		  {
			  newDirection = 'L';
			  lookUpX-= speedIncrement;
		  }
		  else if (random == 2)
		  {
			  newDirection = 'R';
			  lookUpX+= gridSQ;
		  }
		  else if (random == 3)
		  {
			  newDirection = 'U';
			  lookUpY-=speedIncrement;
		  }
		  else if (random == 4)
		  {
			  newDirection = 'D';
			  lookUpY+=gridSQ;
		  }
		  if (newDirection != reverseDirection)
		  {
			  set.add(new Character(newDirection));
		  }
	  } 
	  return newDirection;
  }
  
  //update the status of the pellet the enemy is on top of
  public void updatePellet()
  {
	  int tmpX,tmpY;
	  tmpX = currLoc_X/gridSQ-1;
	  tmpY = currLoc_Y/gridSQ-1;
	  if (tmpX != currPelletX || tmpY != currPelletY)
	  {
		  prevPelletX = currPelletX;
		  prevPelletY = currPelletY;
		  currPelletX = tmpX;
		  currPelletY = tmpY;
	  } 
  } 
}

