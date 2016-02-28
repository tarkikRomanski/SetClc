package SetAction;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.widget.ImageView;

public class DrawingSets {
    private int deviceWidth;
    private int leftac1;
    private int left1;
    private int diametr;
    private int leftac2;
    private int left2 = -60;
    private int radius;
    private int left3;
    private int canvaHeight;


    private Bitmap bitmap;
    private Canvas canvas = new Canvas();
    private Paint paint;

    private ImageView ivResult;

    private int primaryColor;
    private int darkprimaryColor;
    private int accentColor;
    private float txtSize;

    public void setTxtSize(float txtSize) {
        this.txtSize = txtSize;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public void setDarkprimaryColor(int darkprimaryColor) {
        this.darkprimaryColor = darkprimaryColor;
    }

    public void setAccentColor(int accentColor) {
        this.accentColor = accentColor;
    }

    public ImageView getIvResult() {
        return ivResult;
    }

    public void setIvResult(ImageView ivResult) {
        this.ivResult = ivResult;
    }

    public DrawingSets(int deviceWidth) {
        this.deviceWidth = deviceWidth;
        leftac1 = (deviceWidth*5)/100;
        left1 = (deviceWidth*6)/100 + 30;
        diametr = (deviceWidth*44)/100;
        leftac2 = (deviceWidth*2)/100;
        radius = diametr/2;
        left3 = left1 + diametr + left2 + radius;
        canvaHeight = diametr + 2*left1;
        bitmap = Bitmap.createBitmap(deviceWidth, canvaHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    public DrawingSets() {}

    public void setDeviceWidth(int deviceWidth) {
        this.deviceWidth = deviceWidth;
        leftac1 = (deviceWidth*5)/100;
        left1 = (deviceWidth*6)/100 + 30;
        diametr = (deviceWidth*44)/100;
        leftac2 = (deviceWidth*2)/100;
        radius = diametr/2;
        left3 = left1 + diametr + left2 + radius;
        canvaHeight = diametr + 2*left1;
        bitmap = Bitmap.createBitmap(deviceWidth, canvaHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }


    public void drawCrossing() {
        clearDrawing();
        paint.setColor(primaryColor);
        paint.setAlpha(100);
        canvas.drawCircle((left1 + radius), (canvaHeight / 2), radius, paint);
        paint.setColor(darkprimaryColor);
        paint.setAlpha(100);
        canvas.drawCircle(left3, (canvaHeight / 2), radius, paint);
        paint.setTextSize(txtSize);
        paint.setColor(accentColor);
        canvas.drawText("A", (left1 + radius), (canvaHeight / 2), paint);
        canvas.drawText("B", left3, (canvaHeight / 2), paint);
        ivResult.setImageBitmap(bitmap);

    }

    public void drawUnion() {
        clearDrawing();
        paint.setColor(primaryColor);
        canvas.drawCircle((left1 + radius), (canvaHeight / 2), radius, paint);
        canvas.drawCircle(left3, (canvaHeight / 2), radius, paint);
        paint.setTextSize(darkprimaryColor);
        paint.setColor(accentColor);
        canvas.drawText("A", (left1 + radius), (canvaHeight / 2), paint);
        canvas.drawText("B", left3, (canvaHeight / 2), paint);

        ivResult.setImageBitmap(bitmap);
    }

    public void drawMinus() {
        clearDrawing();
        paint.setColor(primaryColor);
        canvas.drawCircle((left1 + radius), (canvaHeight / 2), radius, paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(left3, (canvaHeight / 2), radius, paint);
        paint.setTextSize(darkprimaryColor);
        paint.setColor(accentColor);
        canvas.drawText("A", (left1 + radius), (canvaHeight / 2), paint);
        canvas.drawText("B", left3, (canvaHeight / 2), paint);
        ivResult.setImageBitmap(bitmap);
    }

    public void drawFull() {
        clearDrawing();
        paint.setColor(primaryColor);
        paint.setAlpha(100);
        canvas.drawCircle((leftac1 + radius), (canvaHeight / 2), radius, paint);
        paint.setColor(darkprimaryColor);
        paint.setAlpha(100);
        canvas.drawCircle((leftac1 + diametr + leftac2 + radius), (canvaHeight / 2), radius, paint);
        paint.setTextSize(txtSize);
        paint.setColor(accentColor);
        canvas.drawText("A", (leftac1 + radius), (canvaHeight / 2), paint);
        canvas.drawText("B", (leftac1 + diametr + leftac2 + radius), (canvaHeight / 2), paint);
        ivResult.setImageBitmap(bitmap);
    }

    public void clearDrawing() {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }
}
