package dk.kea.class2016february.atanasiu.heligame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    private MainThread thread;
    private Background background;

    public GamePanel(Context context)
    {
        super(context);

        //add the callback to the surfaceholder to intercept events

        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);

        //make GamePanel focusable so it can handle events
        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        while(retry)
        {
            //try to stop the thread
            try
            {
                thread.setRunning(false);
                thread.join();
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }
           retry = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));
        background.setVector(-5);
        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return super.onTouchEvent(event);
    }
    public void update()
    {
       background.update();
    }
    @Override
    public void draw(Canvas canvas)
    {
        final float scaleFactorX = getWidth()/WIDTH;
        final float scaleFactorY = getHeight()/HEIGHT;
        if(canvas != null)
        {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            background.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }

}
