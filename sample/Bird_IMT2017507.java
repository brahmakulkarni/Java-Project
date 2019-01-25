package sample;

import java.util.*;
import flockbase.*;

public class Bird_IMT2017507 extends Bird {
	private boolean MakeLeader = false;

	public String getName() {
		return "Bird_IMT2017507";
	}

	public Bird_IMT2017507() {
		Position pos = new Position(0, 0);
		Position target = new Position(0, 0);
	}

	public Bird_IMT2017507(Position p, Position t) {
		Position pos = new Position(0, 0);
		Position target = new Position(0, 0);
	}

	/*
	 * public void set_false(){ private boolean amLeader = false; }
	 */

	// leader condition
	public void becomeLeader() {
		MakeLeader = true;
	}

	// remove leader
	public void retireLead() {
		MakeLeader = false;
	}

	// update postion method
	protected void updatePos() {
		ArrayList<Bird> Flock_birds = getFlock().getBirds();

		int x = getPos().getX();
		int y = getPos().getY();

		if (!MakeLeader) {
			// making an instance of position...
			Position pos_ins = getFlock().getLeader().getPos();
			setTarget(pos_ins.getX(), pos_ins.getY());
		}

		int x_target = getTarget().getX();
		int y_target = getTarget().getY();

		double CirDistance = Math.pow(Math.pow(x_target - x, 2) + Math.pow(y_target - y, 2), 0.5);
		// if hits ... condition

		double x_collision = ((x_target - x) * getMaxSpeed()) / CirDistance;
		double y_collision = ((y_target - y) * getMaxSpeed()) / CirDistance;

		for (Bird birds : Flock_birds) {
			double x_second = birds.getPos().getX();
			double y_second = birds.getPos().getY();

			CirDistance = Math.pow(Math.pow(x_second - x, 2) + Math.pow(y_second - y, 2), 0.5);

			if (CirDistance != 0) {
				if (CirDistance <= 2 * getMaxSpeed()) {
					x_collision += ((x - x_second) * getMaxSpeed()) / CirDistance;
					y_collision += ((y - y_second) * getMaxSpeed()) / CirDistance;
				}

			}
		}

		setPos(x + (int) x_collision, y + (int) y_collision);
	}

}
