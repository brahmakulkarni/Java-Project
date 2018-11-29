package sample;

import java.util.*;
import flockbase.Bird;
import flockbase.Flock;
import flockbase.Position;

public class Flock_IMT2017011 extends flockbase.Flock{
    private ArrayList<Bird> birds = new ArrayList<Bird>();
    private Bird leader;
    public Flock_IMT2017011(){
        super();
    }

    public void addBird(Bird bird){
        birds.add(bird);
        bird.setFlock(this);
    }

    public void setLeader(Bird lead){
        this.leader = lead;
        this.leader.becomeLeader();
    }

    public ArrayList<Bird> getBirds(){
        return this.birds;
    }

    public Bird getLeader(){
        return this.leader;
    }

    public Flock split(int pos){
        Bird birdAtIndex = birds.get(pos);
        Flock newFlock = new Flock_IMT2017011();
        birdAtIndex.becomeLeader();
        newFlock.addBird(birdAtIndex);
        newFlock.setLeader(birdAtIndex);

        for (int i = pos+1; i < birds.size(); i++) {
            newFlock.addBird(birds.get(i));
        }
        birds.remove(pos);
        for (int i = pos; i < birds.size(); i++) {
            birds.remove(i);
        }
        return newFlock;
    }
    
    public void joinFlock(Flock f){
        this.getLeader().retireLead();
        for (Bird bird : getBirds()){
            f.addBird(bird);
        }
    }
}