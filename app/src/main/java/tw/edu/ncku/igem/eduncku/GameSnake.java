package tw.edu.ncku.igem.eduncku;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GameSnake extends Activity {
    SurfaceView gameSurfaceView;
    SurfaceHolder surfaceHolder;
    Thread gameThread;
    Boolean isGameThreadStop = true;
    GameObj backimg;
    int gameFPS = 50;
    KeyHandler keyHandler = new KeyHandler();
    TouchPoint touchPoint = new TouchPoint();
    //PowerManager.WakeLock wakeLock;
    drawAction nowDrawWork;
    SnakeObj snake;
    AppleObj apple;
    GameStat gameStat;

    private Toast toast;

    private int eaten_apple_num = 0;
    private Boolean gameThread_status = Boolean.TRUE;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toast = Toast.makeText(this, "", Toast.LENGTH_LONG);



        // 隱藏狀態列
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 隱藏視窗標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 防止手機因手持方向不同 而觸發螢幕方向旋轉
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // 電源管理服務取得
        //PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        //wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "GameSnake PowerControl");


        gameSurfaceView = new SurfaceView(this);
        surfaceHolder = gameSurfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            public void surfaceDestroyed(SurfaceHolder arg0) {
            }

            public void surfaceCreated(SurfaceHolder arg0) {
                if (backimg == null) {
                    // 第一次Activity載入時
                    Resources rs = getResources();
                    backimg = new GameObj(rs.getDrawable(R.drawable.backing, null));
                    SurfaceView sv = gameSurfaceView;
                    backimg.setRect(new Rect(sv.getLeft(), sv.getTop(), sv
                            .getRight(), sv.getBottom()));
                    readyGame();
                } else {
                    // 經由Activity返回載入時
                    draw(nowDrawWork);
                    openOptionsMenu();

                }
            }

            public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
                                       int arg3) {

            }
        });
        setContentView(gameSurfaceView);
    }

    /**
     * 電源控制 防止進入休眠狀態切換
     */
    protected void powerControl(boolean needWake) {

        if (needWake){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }


        /*
                if (needWake && !wakeLock.isHeld()) {
                    wakeLock.acquire();
                } else if (!needWake && wakeLock.isHeld()) {
                    wakeLock.release();
                }
                */
    }

    @Override
    protected void onPause() {
        pauseGame();
        super.onPause();
    };

    protected static final int MENU_Resume = Menu.FIRST;
    protected static final int MENU_Reset = Menu.FIRST + 1;
    protected static final int MENU_Quit = Menu.FIRST + 2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_Resume, 0, "繼續");
        menu.add(0, MENU_Reset, 0, "重新開始");
        menu.add(0, MENU_Quit, 0, "離開");
        return super.onCreateOptionsMenu(menu);
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_Resume:
                resumeGame();
                break;
            case MENU_Reset:
                readyGame();
                break;
            case MENU_Quit:
                gameExit();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        pauseGame();
        return super.onMenuOpened(featureId, menu);
    };

    void gameExit() {
        gameThreadStop();
        if (gameThread != null) {
            try {
                gameThread.join();
            } catch (InterruptedException e) {

            }
        }
        finish();// 結束遊戲
    }

    @Override
    public boolean onTouchEvent(android.view.MotionEvent event) {
        if (nowDrawWork == drawAction.game)
            touchPoint.update(event);
        return true;
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        keyHandler.keyDown(keyCode);
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        keyHandler.keyUp(keyCode);
        return super.onKeyUp(keyCode, event);
    }

    public void gameThreadStart() {
        isGameThreadStop = false;
        powerControl(true);
        if (gameThread == null) {
            gameThread = new Thread(gameRun);
            gameThread.start();
        } else if (!gameThread.isAlive()) {
            gameThread = new Thread(gameRun);
            gameThread.start();
        }
    }

    public void gameThreadStop() {
        isGameThreadStop = true;
        powerControl(false);
    }

    // 準備遊戲
    void readyGame() {
        gameThreadStop();
        nowDrawWork = drawAction.ready;
        Resources rs = getResources();
        snake = new SnakeObj(GameSnake.this, backimg.getRect());
        apple = new AppleObj(rs.getDrawable(R.drawable.apple, null), backimg
                .getRect());
        apple.random(backimg.getRect());
        gameStat = new GameStat(System.currentTimeMillis() + 3000);
        gameThreadStart();
    }

    // 開始遊戲
    void startGame() {
        gameStat = new GameStat(System.currentTimeMillis() + 30000);
        nowDrawWork = drawAction.game;
    }

    // 暫停遊戲
    void pauseGame() {
        gameThreadStop();
        if (nowDrawWork != drawAction.over) {
            gameStat.timePause();
            draw(drawAction.pause);
        }

    }

    // 繼續遊戲
    void resumeGame() {
        if (nowDrawWork != drawAction.over) {
            gameThreadStart();
            gameStat.timeResume();
        }
    }

    Runnable gameRun = new Runnable() {
        public void run() {
            long delayTime = 1000 / gameFPS;


            while (!isGameThreadStop) {
                long startTime = System.currentTimeMillis();
                if (nowDrawWork == drawAction.game){
                    gameUpdate(); //  Crash occur here
                }


                if (gameThread_status){
                    draw(nowDrawWork);
                    long endTime = System.currentTimeMillis();
                    long waitTime = delayTime - (startTime - endTime);
                    if (waitTime > 0) {
                        try {
                            Thread.sleep(waitTime);
                        } catch (InterruptedException e) {
                        }
                    }
                }

            }
        }
    };

    boolean isKeyDown(int keyCode) {
        return keyHandler.isKeyDown(keyCode);
    }

    /**
     * 遊戲更新
     */
    void gameUpdate() {
        boolean isChangeMove = false;

        // 觸控事件處理
        if (touchPoint.isChangeVector) {
            snake.move(touchPoint.lastVectorX, touchPoint.lastVectorY);
            isChangeMove = true;
        } else {
            // 按鍵事件處理
            if (isKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT)) {
                snake.move(1, 0);
                isChangeMove = true;
            }
            if (isKeyDown(KeyEvent.KEYCODE_DPAD_LEFT)) {
                snake.move(-1, 0);
                isChangeMove = true;
            }
            if (isKeyDown(KeyEvent.KEYCODE_DPAD_UP)) {
                snake.move(0, -1);
                isChangeMove = true;
            }
            if (isKeyDown(KeyEvent.KEYCODE_DPAD_DOWN)) {
                snake.move(0, 1);
                isChangeMove = true;
            }
        }

        // 沒有改變移動則往之前方向移動
        if (!isChangeMove)
            snake.move();

        // 更新貪食蛇
        snake.update();

        // 吃到蘋果處理
        if (snake.isEatApple(apple)) {
            // 增加長度
            snake.add();
            //showTip("The length of snake have been added");
            // 增加時間
            gameStat.addTime(3000);

            // Record the num    of apples have been eaten
            eaten_apple_num++;
            switch (eaten_apple_num){
                case 1:
                    showTip("The First apple u have got!!");
                    break;
                case 5:
                    showTip("Bravo! U got the fifth ones");
                    break;
                default:
                    break;
            }

            // 蘋果位置變更
            while (snake.isEatApple(apple))
                apple.random(backimg.getRect());
        }
        // 更新遊戲分數
        gameStat.updateScroe(snake.getLength());

        // 判斷是否結束遊戲
        if (gameStat.isTimeOver()){
            showTip("Yyou are already dead");
            nowDrawWork = drawAction.over;
            gameThread.interrupt();
            gameThread = null;
            gameThread_status = Boolean.FALSE;
            this.finish(); // Close the Activity cuz the game is over
        }
    }

    // 畫面繪圖種類
    enum drawAction {
        ready, game, pause, over
    }

    // 畫面繪圖處理
    void draw(drawAction action) {
        Canvas canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas(null);
            synchronized (surfaceHolder) {
                draw(action, canvas);
            }
        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    // 畫面繪圖函數選擇
    void draw(drawAction action, Canvas canvas) {
        switch (action) {
            case ready:
                drawReady(canvas);
                break;
            case game:
                drawGame(canvas);
                break;
            case pause:
                drawPause(canvas);
                break;
            case over:
                drawOver(canvas);
                break;
        }
    }

    // 畫準備開始
    void drawReady(Canvas canvas) {
        clear(canvas);
        Paint pt = new Paint();
        pt.setTextAlign(Paint.Align.CENTER);
        pt.setARGB(255, 0, 0, 255);
        pt.setTextSize(30);
        canvas.drawText(gameStat.getCountdownTime() + "秒後遊戲開始-", backimg
                .centerX(), backimg.centerY(), pt);
        if (gameStat.isTimeOver())
            startGame();
    }

    // 畫遊戲中
    void drawGame(Canvas canvas) {
        clear(canvas);
        apple.draw(canvas);
        snake.draw(canvas);
        gameStat.draw(canvas);
        touchPoint.draw(canvas);
    }

    // 畫暫停
    void drawPause(Canvas canvas) {
        draw(nowDrawWork, canvas);
        Paint pt = new Paint();
        pt.setARGB(30, 0, 0, 100);
        canvas.drawRect(backimg.getRect(), pt);
        pt.setTextAlign(Paint.Align.CENTER);
        pt.setARGB(150, 200, 200, 200);
        pt.setTextSize(50);
        canvas.drawText("-遊戲暫停-", backimg.centerX(), backimg.centerY(), pt);
    }

    // 畫遊戲結束
    void drawOver(Canvas canvas) {
        // 執行緒停止
        gameThreadStop();
        drawGame(canvas);
        Paint pt = new Paint();
        pt.setARGB(30, 30, 30, 30);
        canvas.drawRect(backimg.getRect(), pt);
        pt.setTextAlign(Paint.Align.CENTER);
        pt.setARGB(100, 0, 0, 255);
        pt.setTextSize(50);
        canvas.drawText("-遊戲結束-", backimg.centerX(), backimg.centerY(), pt);
    }

    void clear(Canvas canvas) {
        Paint p = new Paint();
        p.setARGB(100, 0, 0, 0);
        backimg.draw(canvas);
    }

    private void showTip(final String str) {

        toast.setText(str);
        toast.show();
    }

}
