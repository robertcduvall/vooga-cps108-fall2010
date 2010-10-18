package vooga.games.towerdefense;

import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;

import vooga.engine.core.Game;
import vooga.engine.overlay.Stat;
import vooga.engine.overlay.StatInt;
import vooga.engine.player.control.PlayerSprite;
import vooga.engine.resource.Resources;
import vooga.engine.resource.ResourcesBeta;

/**
 * The enemy sprite class which is defined by a speed, a path, a location, and 
 * health for each enemy.
 * 
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 */

public class Enemy extends Sprite {

	protected ArrayList<PathPoint> myPath;
	private ArrayList<PathPoint> myCurrentPath;
	private int mySpeed;
	protected int myLoc;
	private long myTotalTime;
	private int myFreq;
	private int myTempLoc;
	private boolean myRestart;
	protected Stat<Integer> mySelfEstem;
	protected Stat<Integer> myScore;
	protected Stat<Integer> myMoney;
	private int myLives;

	public Enemy(ArrayList<PathPoint> path, int speed, int lives,
			Stat<Integer> selfEstem, Stat<Integer> score, Stat<Integer> money) {
		super(-100, -100);
		myPath = path;
		mySpeed = speed;
		myLoc = 0;
		myTotalTime = 0;
		myFreq = 0;
		myTempLoc = 0;
		myRestart = true;
		mySelfEstem = selfEstem;
		myScore = score;
		myMoney = money;
		myLives = lives;
		setImage();
	}

	/**
	 * sets the image based on the number of lives
	 */
	protected void setImage() {

		if (myLives == 3) {
			setImage(ResourcesBeta.getImage("duvallFaceRed"));
		} else if (myLives == 2) {
			setImage(ResourcesBeta.getImage("duvallFaceBlue"));
		} else if (myLives == 1) {
			setImage(ResourcesBeta.getImage("duvallFace"));
		}

	}

	/**
	 * updates the parameters of the sprite based on the time
	 */
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (myRestart) {
			int[] dist = getDistance();
			// System.out.println(dist[0]);
			createPath(dist[0], (int) ((((double) dist[0]) / mySpeed) * 50),
					myLoc, dist[1]);
			// System.out.println(myCurrentPath.size());
			// System.out.println(myTempLoc);
			// PathPoint point = myCurrentPath.get(myTempLoc);
			myFreq = 20;
			// setLocation(point.getX(), point.getY());
			// System.out.println(dist[1]);
			// System.out.println(myPath.size());
			// myTempLoc++;
			myTotalTime = 0;
			myRestart = false;
			// System.out.println(myLoc + " : " + myPath.size());
			if (myLoc >= myPath.size() - 1) {
				finish();
			}
			myLoc = dist[1];
			// System.out.println(myLoc + " : " + myPath.size() + "a");
		} else {
			myTotalTime += elapsedTime;
			if (myTotalTime >= myFreq * myTempLoc) {
				// System.out.println(myLoc + " : " + myPath.size());
				PathPoint point = myCurrentPath.get(myTempLoc);
				setLocation(point.getX(), point.getY());
				if (myTempLoc == myCurrentPath.size() - 1) {
					myRestart = true;
					myTempLoc = 0;
				} else {
					myTempLoc++;
				}
			}

		}
	}

	private void finish() {
		setActive(false);
		mySelfEstem.setStat(mySelfEstem.getStat() - myLives);
	}

	/**
	 * displays whether or not an enemy is hit
	 */
	public void gotHit() {
		myScore.setStat(myScore.getStat() + 10);
		myMoney.setStat(myMoney.getStat() + 1);
		if (myLives == 1) {
			myMoney.setStat(myMoney.getStat() + 1);
			setActive(false);
		}
		myLives--;
		setImage();
	}

	private int[] getDistance() {
		PathPoint current = myPath.get(myLoc);
		PathPoint end;
		double dist = 0;
		for (int k = myLoc + 1; k < myPath.size(); k++) {
			end = myPath.get(k);
			dist += getDistance(current, end);
			if (dist >= mySpeed) {
				return new int[] { (int) dist, k };
			}
			current = end;
		}
		return new int[] { (int) dist, myPath.size() - 1 };

	}

	private double getDistance(PathPoint beg, PathPoint end) {
		double changeXsq = Math.pow(beg.getX() - end.getX(), 2);
		double changeYsq = Math.pow(beg.getY() - end.getY(), 2);

		return Math.sqrt((changeXsq + changeYsq));

	}

	/**
	 * generates the path to follow
	 */
	private void createPath(double totalDistance, int segments, int start,
			int end) {
		int current = start + 1;
		// System.out.println(segments + "seg");
		myCurrentPath = new ArrayList<PathPoint>();
		myCurrentPath.add(myPath.get(start));
		double diff = totalDistance / segments;
		// System.out.println(start + " : " + end);
		while (start < end) {
			// System.out.println("a");
			while (true) {
				if (current >= end) {
					start = current;
					current++;
					break;
				}
				double dist = getDistance(myPath.get(start),
						myPath.get(current));
				// System.out.println(current + " : " + start);
				if (dist >= diff) {
					myCurrentPath.add(myPath.get(current));
					start = current;
					current++;
					break;
				} else {
					current++;
				}
			}

		}
		// System.out.println(myCurrentPath.size() + "act");

	}

}
