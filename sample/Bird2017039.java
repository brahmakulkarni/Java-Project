package sample;

import flockbase.Position;
import flockbase.*;
import java.io.PrintStream;
import java.util.*;

public class Bird2017039 extends flockbase.Bird
{
  private int speed = 10;
  private boolean amLeader;

  public Bird2017039() {}
  public String getName()
  {
    return "Bird039";
  }
  private int dist(Position b1,Position b2)
  {
      return (int)Math.sqrt(((b1.getX()-b2.getX())*(b1.getX()-b2.getX()))+((b1.getY()-b2.getY())*(b1.getY()-b2.getY())));
  }
  protected void updatePos()
  {
    if(getFlock().getBirds().size() > 0)
    {
      Position currPos = getPos();
    int x = currPos.getX();
    int y = currPos.getY();
    Flock p = getFlock();
    int dist = 0;
    ArrayList<Bird>temp = p.getBirds();
    if(amLeader == false)
    {
      Position tar = getFlock().getLeader().getPos();
      int cur_dist = (int)Math.sqrt(((tar.getX()-getPos().getX())*(tar.getX()-getPos().getX()))+((tar.getY()-getPos().getY())*(tar.getY()-getPos().getY())));
      Position req= null;
      for(int i=0;i<temp.size();i++)
      {
          Position t = temp.get(i).getPos();
          int distance = (int) Math.sqrt((tar.getX()-t.getX())*(tar.getX()-t.getX())+(tar.getY()-t.getY())*(tar.getY()-t.getY()));
          if(distance<cur_dist)
          {
              if(dist<cur_dist-distance)
              {
                    dist = cur_dist - distance;
                    req = t;
              }
          }
        }
        if(req!=null)
        {
          setPos((1*req.getX() + 49*getPos().getX())/50,(1*req.getY() + 49*getPos().getY())/50);
        }
  }
  else
  {
      Position req = getTarget();
      setPos((1*req.getX() + 19*getPos().getX())/20,(1*req.getY() + 19*getPos().getY())/20);
  }}
}


  public void becomeLeader()
  {
    amLeader = true;
  }

  public void retireLead()
  {
    amLeader = false;
  }
}
