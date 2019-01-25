package sample;
import java.lang.Math;

import flockbase.Position;
import flockbase.Bird;
import java.io.PrintStream;

public class Bird_IMT2017025 extends flockbase.Bird
{

  public Bird_IMT2017025() {}

  public String getName() {
		return "Bird_IMT2017025";
	}
  
  protected void updatePos()
  {
    Position currentPosition = getPos();
    int xCoordinate = currentPosition.getX();
    int yCoordinate = currentPosition.getY();
    int targetXCoordinate, targetYCoordinate;

    if(!isLeader){
      Position target = getFlock().getLeader().getPos();
      targetXCoordinate = target.getX();
      targetYCoordinate = target.getY();
    }
    else
    {
      Position target = getTarget();
      targetXCoordinate = target.getX();
      targetYCoordinate = target.getY();
    }


    setTarget(targetXCoordinate, targetYCoordinate);

    // go to coordinate 10 units towards target
      double m = ((double)(targetYCoordinate - yCoordinate)) / (targetXCoordinate - xCoordinate);
      double d = 10;
      int x,y;
      if(m > 0 )
      {
        if( targetYCoordinate < yCoordinate){
          x =  xCoordinate - (int)(d*(Math.sqrt(((1/((m*m) + 1))))));
          y =  yCoordinate - (int)(d*m*(Math.sqrt(((1/((m*m) + 1))))));
        }
        else {
          x =  xCoordinate + (int)(d*(Math.sqrt(((1/((m*m) + 1))))));
          y =  yCoordinate + (int)(d*m*(Math.sqrt(((1/((m*m) + 1))))));
        }
      }
      else 
      {
        if( targetXCoordinate < xCoordinate){
          x =  xCoordinate - (int)(d*(Math.sqrt(((1/((m*m) + 1))))));
          y =  yCoordinate - (int)(d*m*(Math.sqrt(((1/((m*m) + 1))))));
        }
        else {
          x =  xCoordinate + (int)(d*(Math.sqrt(((1/((m*m) + 1))))));
          y =  yCoordinate + (int)(d*m*(Math.sqrt(((1/((m*m) + 1))))));

        }
      }

      if(collidingNeighbours(xCoordinate, yCoordinate))
      {
        x = x-1;
        y = y-1;
      }

      setPos(x,y);
  }
  

  public void becomeLeader()
  {
    isLeader = true;
  }
  
  public void retireLead()
  {
    isLeader = false;
  }

  private boolean collidingNeighbours(int xCoordinate, int yCoordinate)
  {
    int x,y;
    Position position;
    for(Bird bird : getFlock().getBirds()) {
      position = bird.getPos();
      x = position.getX();
      y = position.getY();
      int dist = (int)Math.sqrt((xCoordinate - x)^2 + (yCoordinate - y)^2);
      int x_diff = Math.abs(x - xCoordinate);
      if(dist < 5 && dist != 0)
      {
        return true;
      }

      int y_diff = Math.abs(y - yCoordinate);
      if(dist < 5 && dist != 0)
      {
        return true;
      }
    }

    return false;
  }

  private boolean isLeader;
  private int speed = 10;
  
}
