package it.uninsubria.tesiandreaselva;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameViewBP extends SurfaceView {
	private SurfaceHolder holder;
	private GameLoopThreadBP gameLoopThread;
	private GameCollisionThread gameCollisionThread;
	private List<SpriteBP> sprites = new ArrayList<SpriteBP>();

	public GameViewBP(Context context) {
		super(context);
		gameLoopThread = new GameLoopThreadBP(this);
		gameCollisionThread = new GameCollisionThread(this, sprites);
		holder = getHolder();
		holder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				gameLoopThread.setRunning(false);
				gameCollisionThread.setRunning(false);
				while (retry) {
					try {
						gameLoopThread.join();
						gameCollisionThread.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				createSprites();
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
				gameCollisionThread.setSprites(sprites);
				gameCollisionThread.setRunning(true);
				gameCollisionThread.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});
	}

	private void createSprites() {
		sprites.add(createSprite(R.drawable.loki));
		sprites.add(createSprite(R.drawable.thor));
		sprites.add(createSprite(R.drawable.ironman));
		sprites.add(createSprite(R.drawable.hawkeye));
		sprites.add(createSprite(R.drawable.blackwidow));
		sprites.add(createSprite(R.drawable.hulk));
		sprites.add(createSprite(R.drawable.america));

	}
	public void setSprites(List<SpriteBP> sprites){
		this.sprites = sprites;
	}

	private SpriteBP createSprite(int resouce) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
		return new SpriteBP(this, bmp);
	}

	@SuppressLint("WrongCall")
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		synchronized (getHolder()) {
			for (SpriteBP sprite : sprites) {
				sprite.onDraw(canvas);
			}
		}
	}
	
}