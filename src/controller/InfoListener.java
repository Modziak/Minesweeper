package controller;

public interface InfoListener {

	public void startTimer();
	public void stopTimer();
	public void updateTimer(int time);
	public void updateMinesCount(int minesChange);
	public void updateTilesCount(int tilesRevealed);
}
