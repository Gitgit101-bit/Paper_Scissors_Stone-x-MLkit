package com.nuwarobotics.app.posedetectorsample.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GrpcSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder = this.getHolder();

    public GrpcSurfaceView(Context context) {
        super(context);
        init();
    }

    public GrpcSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GrpcSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void drawBitmap(Bitmap bitmap) {
        Canvas canvas = holder.lockCanvas();
        if (canvas == null) {
            return;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) getWidth()) / width;
        float scaleHeight = ((float) getHeight()) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap scaledBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
                true);
        canvas.drawBitmap(scaledBmp, 0, 0, null);
        holder.unlockCanvasAndPost(canvas);
        if (bitmap != null & !bitmap.isRecycled())
        {
            bitmap.recycle();
        }

        if (scaledBmp != null) {
            scaledBmp.recycle();
        }
    }
}
