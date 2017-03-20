package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController
{

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	
	public TrainControllerImpl()
	{
		ThreadClass threadclass = new ThreadClass(this);
		Thread thread = new Thread(threadclass);
		thread.start();
	}
	
	@Override
	public void followSpeed() 
	{
		if (referenceSpeed < 0) 
		{
			referenceSpeed = 0;
		} 
		else 
		{
		    if(referenceSpeed+step > 0) 
		    {
                referenceSpeed += step;
            }
		    else 
		    {
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
	public void setJoystickPosition(int joystickPosition) 
	{
		this.step = joystickPosition;		
	}
	
	static class ThreadClass implements Runnable
	{
		
		TrainControllerImpl controller;
		Thread t;
		
		public ThreadClass(TrainControllerImpl controller)
		{
			this.controller = controller;
		}
		
		public void run()
		{
			while(true)
			{
				controller.followSpeed();
				try 
				{
					wait(1000);
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		/*public void start()
		{
	         t = new Thread();
	         t.start ();
		}*/
		
	}

}
