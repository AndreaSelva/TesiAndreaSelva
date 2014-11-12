package it.uninsubria.tesiandreaselva;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

public class GameLoopThread extends Thread {
	static final long FPS = 10;
	private GameViewNBP viewNBP;
	private GameViewBP viewBP;
	private boolean running = false;

	public GameLoopThread(GameViewNBP view) {
		this.viewNBP = view;
	}

	public GameLoopThread(GameViewBP view) {
		this.viewBP = view;
	}

	public void setRunning(boolean run) {
		running = run;
	}

	@SuppressLint("WrongCall")
	@Override
	public void run() {
		if (viewBP == null) {
			long ticksPS = 1500 / FPS;
			long startTime;
			long sleepTime;
			while (running) {
				Canvas c = null;
				startTime = System.currentTimeMillis();
				try {
					c = viewNBP.getHolder().lockCanvas();
					synchronized (viewNBP.getHolder()) {
						viewNBP.onDraw(c);
					}
				} finally {
					if (c != null) {
						viewNBP.getHolder().unlockCanvasAndPost(c);
					}
				}
				sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
				try {
					if (sleepTime > 0)
						sleep(sleepTime);
					else
						sleep(10);
				} catch (Exception e) {
				}
			}
		} else {
			long ticksPS = 1500 / FPS;
			long startTime;
			long sleepTime;
			while (running) {
				Canvas c = null;
				startTime = System.currentTimeMillis();
				try {
					c = viewBP.getHolder().lockCanvas();
					synchronized (viewBP.getHolder()) {
						viewBP.onDraw(c);
					}
				} finally {
					if (c != null) {
						viewBP.getHolder().unlockCanvasAndPost(c);
					}
				}
				sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
				try {
					if (sleepTime > 0)
						sleep(sleepTime);
					else
						sleep(10);
				} catch (Exception e) {
				}
			}
		}
	}
}
