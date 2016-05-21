package dk.kea.class2016february.atanasiu.heligame;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background
{
    private Bitmap bitmap;
    private int x,y, dx;

    public Background(Bitmap res)
    {
        bitmap = res;
    }
    public void update()
    {
       x = x + dx;
        if( x < -GamePanel.WIDTH)
        {
            x = 0;
        }
    }

    public void draw(Canvas canvas)
    {
       canvas.drawBitmap(bitmap, x, y, null);

       if(x < 0)
       {
           canvas.drawBitmap(bitmap, x + GamePanel.WIDTH , y + GamePanel.HEIGHT, null);
       }
    }

    public void setVector(int dx)
    {
        this.dx = dx;
    }
}
