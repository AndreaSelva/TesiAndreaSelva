package it.uninsubria.tesiandreaselva;

public class GameMoveThread extends Thread {

	private GameViewBP view;
	private Sprite sprite;
	private boolean running = false;
	static final long FPS = 10;
	private static final int BMP_COLUMNS = 4;
	private int GameViewWidth;
	private int GameViewHeight;
	private int x = 0;
	private int y = 0;
	private int xSpeed;
	private int ySpeed;
	private int currentFrame = 0;
	private int width;
	private int height;

	public GameMoveThread(GameViewBP view, Sprite sprite) {
		this.view = view;
		this.sprite = sprite;
		GameViewWidth = sprite.getGameViewWidth();
		GameViewHeight = sprite.getGameViewHeight();
		x = sprite.getX();
		y = sprite.getY();
		xSpeed = sprite.getxSpeed();
		ySpeed = sprite.getySpeed();
		currentFrame = sprite.getCurrentFrame();
		width = sprite.getWidth();
		height = sprite.getHeight();
	}

	public Sprite getSprite(){
		return sprite;
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
					if (x >= GameViewWidth - width - xSpeed || x + xSpeed <= 0) {
						xSpeed = -xSpeed;
						sprite.setxSpeed(xSpeed);
					}
					x = x + xSpeed;
					sprite.setX(x);
					if (y >= GameViewHeight - height - ySpeed
							|| y + ySpeed <= 0) {
						ySpeed = -ySpeed;
						sprite.setySpeed(ySpeed);
					}

					y = y + ySpeed;
					sprite.setY(y);
					currentFrame = ++currentFrame % BMP_COLUMNS;
					sprite.setCurrentFrame(currentFrame);

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
}
