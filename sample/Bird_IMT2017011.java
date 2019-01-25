package sample;

import flockbase.Bird;
import flockbase.Flock;
import flockbase.Position;

public class Bird_IMT2017011 extends flockbase.Bird{
    private int isLeader;
    private double bubbleRadius = 50.0;
    // int vx = getMaxSpeed()/Math.sqrt(2);
    // int vy = getMaxSpeed()/Math.sqrt(2);
    // private Position velocity = new Position(vx,vy);
    private Position velocity = new Position(5,5);    

    public Bird_IMT2017011(){
        super();
    }

    public String getName(){
        if (isLeader == 0)
            return "Bird_IMT2017011";
        return "Leader_IMT2017011";
    }
    
    public double absoluteDistanceBetweenBirds(Bird bird){
        double x1 = this.getPos().getX();
        double y1 = this.getPos().getY();
        double x2 = bird.getPos().getX();
        double y2 = bird.getPos().getX();
        double distance = Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
        return distance;
    }

    public boolean mightCollideWithTarget(){
        int x1 = this.getTarget().getX();
        int y1 = this.getTarget().getY();
        int x2 = this.getPos().getX();
        int y2 = this.getPos().getY();

        boolean risk = false;
        double distance = Math.sqrt((Math.pow((x1 - x2), 2)) + Math.pow((y1 - y2), 2));
        if (distance < bubbleRadius){
            risk = true;
        }
        return risk;
    }

    public Position collisionAvoidance(Bird bird){
        Position newPos = new Position(0,0);
        int x = 0;
        int y = 0;
        for (Bird b : getFlock().getBirds()){
            if (b != bird){
                if((Math.abs(b.getPos().getX() - bird.getPos().getX()) < 50) && (Math.abs(b.getPos().getY() - bird.getPos().getY()) < 50)){
                    x = x - (b.getPos().getX() - bird.getPos().getX());
                    y = y - (b.getPos().getY() - bird.getPos().getY());
                }
            }
        }
        newPos.setPos(x,y);
        return newPos;
    }

    public void updatePos(){
        int bird_x = this.getPos().getX();
        int bird_y = this.getPos().getY();
        
        if (isLeader == 0){
            Position leaderPos = this.getFlock().getLeader().getPos();
            this.setTarget(leaderPos.getX(), leaderPos.getY());
        }

        int xt = getTarget().getX();
        int yt = getTarget().getY();

        double dx = 0.0;
        double dy = 0.0;

        if (mightCollideWithTarget()){
            dx = 0.0;
            dy = 0.0;
        }
        else {
            if (xt == bird_x){
                if (yt > bird_y){
                    dy = 1.0;
                }
                else{
                    dy = -1.0;
                }
                dx = 0.0;
            }
            else {
                if (yt == bird_y){
                    if (xt > bird_x){
                        dx = 1.0;
                    }
                    else {
                        dx = -1.0;
                    }
                    dy = 0.0;
                }
                else {
                    double slope = (double)((yt-bird_y)/(xt - bird_x));
                    if (xt > bird_x){
                        dx = 1.0;
                    }
                    else {
                        dx = -1.0;
                    }
                    if (slope > 50.0){
                        slope = 50.0;
                    }
                    else if (slope < -50.0){
                        slope = -50.0;
                    }
                    dx = dx*velocity.getX();
                    dy = slope*dx;
                }
            }
        }

        double Dx = dx + this.collisionAvoidance(this).getX();
        double Dy = dy + this.collisionAvoidance(this).getY();

        if (((bird_x + (int)dx) < 975) && ((bird_y + (int)dy) < 975) || ((bird_x+(int)dx) > 25 && (bird_y+(int)dy) > 25)) {
            this.setPos(bird_x + (int)Dx, bird_y + (int)Dy);
        }
        else {
            this.setPos(bird_x + (int)dx, bird_y + (int)dy);
        }
    }

    public void becomeLeader(){
        isLeader = 1;
        // this.getFlock().setLeader(this);
    }
    public void retireLead(){
        isLeader = 0;
        // this.getFlock().setLeader(null);
    }
}