package org.gamayun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import org.gamayun.datatypes.wordcloud.WordCloudElement;
import org.gamayun.datatypes.wordcloud.WordCloudResult;
import org.gamayun.parsers.WordCloudParser;

class WordCloudViewer extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread drawThread;
    private WordCloudResult wcResult;

    public WordCloudViewer(Context context, String filename) {
        super(context);
        getHolder().addCallback(this);
        this.wcResult = WordCloudParser.parseString(FileHandler.readSDCardFile(filename));
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder());
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    class DrawThread extends Thread {

        private boolean running = false;
        private SurfaceHolder surfaceHolder;

        public DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            Canvas canvas;
            while (running) {
                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    if (canvas == null)
                        continue;
                    canvas.drawColor(Color.rgb(255, 255, 255));
                    for (WordCloudElement wcElement: wcResult.getElements()) {
                        Paint paint = new Paint();
                        paint.setColor(wcElement.getColor());
                        paint.setTextSize(wcElement.getSize());
                        canvas.drawText(
                                wcElement.getText(),
                                wcElement.getX(),
                                wcElement.getY(),
                                paint
                        );
                    }

                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }
}