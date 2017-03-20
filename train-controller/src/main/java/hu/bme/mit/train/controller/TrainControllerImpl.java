package hu.bme.mit.train.controller;

import java.util.Timer;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private speedTimer timer;
	
	public TrainControllerImpl(){
		timer = new speedTimer(this);
		Thread t = new Thread(timer);
		t.start();
	}
	
static class speedTimer implements Runnable {
		
		TrainControllerImpl parent;
		
		speedTimer(TrainControllerImpl parent) {
			this.parent = parent;
		}
		
		public void run(){
			while (true) {
				parent.followSpeed();
				try {
					wait(1000);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	}

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}
	
	

}
