package it.uninsubria.tesiandreaselva;

import java.util.ArrayList;
import java.util.List;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class GameCollisionThread extends Thread {

	private GameViewBP view;
	private List<Sprite> sprites = new ArrayList<Sprite>();
	private boolean running = false;
	static final long FPS = 10;

	public GameCollisionThread(GameViewBP view, List<Sprite> sprites) {
		this.view = view;
		this.sprites = sprites;
	}

	public void setRunning(boolean run) {
		running = run;
	}

	@Override
	public void run() {
		long ticksPS = 1500 / FPS;
		long startTime = System.currentTimeMillis();
		long sleepTime;
		while (running) {
			try {
				startTime = System.currentTimeMillis();
				synchronized (view.getHolder()) {
					for (int i = sprites.size() - 1; i >= 1; i--) {
						Sprite sprite = sprites.get(i);
						if (sprite.getBounds().intersect(
								sprites.get(0).getBounds())) {
							sprites.get(0).stop();
							sprites.remove(0);
							sprites.add(0, view.createSprite(R.drawable.loki));
							view.setSprites(sprites);
							break;
						}
					}
				}
			} catch (Exception e) {
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

	public void setSprites(List<Sprite> sprites) {
		this.sprites = sprites;
	}
}
