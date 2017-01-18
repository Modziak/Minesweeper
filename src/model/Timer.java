package model;

import controller.InfoListener;

public class Timer implements Runnable{
	
	private final int timerInterval = 1000;
	private final int ticsCount = 1000;
	private InfoListener infoListener;

	public void addInfoListener(InfoListener listener){
		infoListener = listener;
	}
	
	@Override
	public void run() {
		try {
			for(int i=1; i<ticsCount; i++){
				synchronized(this){
					this.wait(timerInterval);
					infoListener.updateTimer(i);
				}
			}
			while(true);
		} catch (InterruptedException e) {
		}
		
	}

}
