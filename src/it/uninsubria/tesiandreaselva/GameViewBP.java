package it.uninsubria.tesiandreaselva;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class GameViewBP extends SurfaceView {
	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;
	private GameCollisionThread gameCollisionThread;
	private List<Sprite> sprites = new ArrayList<Sprite>();

	public GameViewBP(Context context) {
		super(context);
		gameLoopThread = new GameLoopThread(this);
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
						for (Sprite sprite : sprites) {
							sprite.stop();
						}
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
		final Toast toast = Toast.makeText(context, "10", Toast.LENGTH_LONG);
		toast.show();
		android.os.Debug.startMethodTracing("BP");
		new CountDownTimer(10000, 1000) {

			public void onTick(long millisUntilFinished) {
			    long sec = millisUntilFinished / 1000 + 1;
			    if(millisUntilFinished < 1000) sec = 1;
				//toast.cancel();
				toast.setText("" + sec);
				toast.show();
			}

			public void onFinish() {
				toast.cancel();
				android.os.Debug.stopMethodTracing();
			}
		}.start();
	}

	private void createSprites() {
		sprites.add(createSprite(R.drawable.loki));
		sprites.add(createSprite(R.drawable.thor));
		sprites.add(createSprite(R.drawable.ironman));
		sprites.add(createSprite(R.drawable.hawkeye));
		sprites.add(createSprite(R.drawable.blackwidow));
		sprites.add(createSprite(R.drawable.hulk));
		sprites.add(createSprite(R.drawable.america));
		sprites.add(createSprite(R.drawable.thor));
		sprites.add(createSprite(R.drawable.ironman));
		sprites.add(createSprite(R.drawable.hawkeye));
		sprites.add(createSprite(R.drawable.blackwidow));
		sprites.add(createSprite(R.drawable.hulk));
		sprites.add(createSprite(R.drawable.america));

	}

	public void setSprites(List<Sprite> sprites) {
		this.sprites = sprites;
	}

	public Sprite createSprite(int resouce) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
		Sprite sprite = new Sprite(this, bmp);
		return sprite;
	}

	public Sprite createSprite(int resouce, GameMoveThread thread) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
		Sprite sprite = new Sprite(this, bmp, thread);
		return sprite;
	}

	@SuppressLint("WrongCall")
	@Override
	protected void onDraw(Canvas canvas) {
		synchronized (getHolder()) {
			canvas.drawColor(Color.BLACK);
			for (Sprite sprite : sprites) {
				sprite.onDraw(canvas);

			}
		}
	}

}