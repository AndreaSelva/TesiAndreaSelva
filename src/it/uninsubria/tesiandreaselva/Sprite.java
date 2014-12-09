package it.uninsubria.tesiandreaselva;

import java.util.Random;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

@SuppressLint("DrawAllocation")
public class Sprite {
	// direction = 0 up, 1 left, 2 down, 3 right,
	// animation = 3 back, 1 left, 0 front, 2 right
	int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
	private static final int BMP_ROWS = 4;
	private static final int BMP_COLUMNS = 4;
	private static final int MAX_SPEED = 7;
	private GameViewNBP gameViewNBP;
	private GameViewBP gameViewBP;
	private int GameViewWidth;
	private int GameViewHeight;
	private Bitmap bmp;
	private int x = 0;
	private int y = 0;
	private int xSpeed;
	private int ySpeed;
	private int currentFrame = 0;
	private int width;
	private int height;
	private GameMoveThread gameMoveThread;

	public Sprite(GameViewBP gameView, Bitmap bmp) {
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight() / BMP_ROWS;
		this.gameViewBP = gameView;
		this.bmp = bmp;
		GameViewWidth = gameViewBP.getWidth();
		GameViewHeight = gameViewBP.getHeight();

		Random rnd = new Random();
		x = rnd.nextInt(gameView.getWidth() - width);
		y = rnd.nextInt(gameView.getHeight() - height);
		xSpeed = rnd.nextInt(MAX_SPEED * 3) - MAX_SPEED;
		ySpeed = rnd.nextInt(MAX_SPEED * 3) - MAX_SPEED;

		gameMoveThread = new GameMoveThread(gameView, this);
		gameMoveThread.setRunning(true);
		gameMoveThread.start();
	}
	public Sprite(GameViewBP gameView, Bitmap bmp, GameMoveThread thread) {
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight() / BMP_ROWS;
		this.gameViewBP = gameView;
		this.bmp = bmp;
		GameViewWidth = gameViewBP.getWidth();
		GameViewHeight = gameViewBP.getHeight();

		Random rnd = new Random();
		x = rnd.nextInt(gameView.getWidth() - width);
		y = rnd.nextInt(gameView.getHeight() - height);
		xSpeed = rnd.nextInt(MAX_SPEED * 3) - MAX_SPEED;
		ySpeed = rnd.nextInt(MAX_SPEED * 3) - MAX_SPEED;

		thread.setSprite(this);
		gameMoveThread = thread;
	}

	public Sprite(GameViewNBP gameView, Bitmap bmp) {
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight() / BMP_ROWS;
		this.gameViewNBP = gameView;
		this.bmp = bmp;
		GameViewWidth = gameViewNBP.getWidth();
		GameViewHeight = gameViewNBP.getHeight();

		Random rnd = new Random();
		x = rnd.nextInt(gameView.getWidth() - width);
		y = rnd.nextInt(gameView.getHeight() - height);
		xSpeed = rnd.nextInt(MAX_SPEED * 3) - MAX_SPEED;
		ySpeed = rnd.nextInt(MAX_SPEED * 3) - MAX_SPEED;
	}

	public void stop() {
		boolean retry = true;
		while (retry) {
			try {
				gameMoveThread.setRunning(false);
				gameMoveThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			retry = false;
		}
	}
	public GameMoveThread getThread(){
		return gameMoveThread;
	}

	private void updateNBP() {
		int a=0;
		for(int i=0; i<1500000; i++){
			a=a+i;
		}
		if (x >= GameViewWidth - width - xSpeed || x + xSpeed <= 0) {
			xSpeed = -xSpeed;
		}
		x = x + xSpeed;
		if (y >= GameViewHeight - height - ySpeed || y + ySpeed <= 0) {
			ySpeed = -ySpeed;
		}

		y = y + ySpeed;
		currentFrame = ++currentFrame % BMP_COLUMNS;

	}

	public Rect getBounds() {
		return new Rect(x, y, x + width, y + height);
	}

	@SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas) {
		if (gameViewBP == null)
			updateNBP();
		int srcX = currentFrame * width;
		int srcY = getAnimationRow() * height;
		Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
		Rect dst = new Rect(x, y, x + width, y + height);
		canvas.drawBitmap(bmp, src, dst, null);
	}

	private int getAnimationRow() {
		double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
		int direction = (int) Math.round(dirDouble) % BMP_ROWS;
		return DIRECTION_TO_ANIMATION_MAP[direction];
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public int getGameViewWidth() {
		return GameViewWidth;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getGameViewHeight() {
		return GameViewHeight;
	}

}