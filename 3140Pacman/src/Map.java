//package undergrad_man;

//The player and enemy both inherit these elements
public class Map
{
  //Counts the number of frames for animation
  int frameCount=0;

  //Contains the game map
  boolean[][] state;

  //Size of one square and height/width in the game
  //increment is the speed at which the object moves
  int gridSQ;
  int hxw;
  int speedIncrement;

  //Constructor 
  public Map()
  {
    gridSQ=20;
    speedIncrement = 4;
    hxw = 400;
    state = new boolean[20][20];
    for(int i =0;i<20;i++)
    {
      for(int j=0;j<20;j++)
      {
        state[i][j] = false;
      }
    }
  }

  //Info on the state is updated 
  public void stateUpdate(boolean[][] state)
  {
    for(int i =0;i<20;i++)
    {
      for(int j=0;j<20;j++)
      {
        this.state[i][j] = state[i][j];
      }
    }
  }

  //Checks to see if coordinates are a valid destination 
  public boolean isValid(int x, int y)
  {
	//Checks first if x and y are not out of bounds
	//Then it checks if the location is valid or not
    if ((((x)%20==0) || ((y)%20)==0) && 20<=x && x<400 && 20<= y && y<400 && state[x/20-1][y/20-1] )
    {
      return true;
    }
    return false;
  } 
}
