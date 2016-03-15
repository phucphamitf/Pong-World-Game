package com.demo01.pdkpro.bong_bay.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

import com.demo01.pdkpro.bong_bay.R;

import java.util.ArrayList;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class MainView extends View {
    //tạo 1 trái banh
    Ball ball;
    float x =50,y=700;
    float SpeedX = 10,SpeedY = 10;
    ArrayList<Boom> ArrBoom ;
    int BoomSizeX,BoomSizeY;
    boolean cfCreateBoom = true;
    //gat bong
    Player player;
    AI ai;
    //cờ nhấn giữ
    Boolean cfMove = false;
    ArrayList arrGUI = new ArrayList();
    ViewFlipper viewFlipper;

    public MainView(Context context){
        super(context);
        ArrBoom = new ArrayList<>();
        //this.arrGUI = arrGUI;
        //set hình nền
        this.setBackgroundResource(R.drawable.background);
    }

    public void setArrGUI(ArrayList arrGUI) {
        this.arrGUI = arrGUI;
    }

    public void setViewFlipper(ViewFlipper viewFlipper) {
        this.viewFlipper = viewFlipper;
    }
    public void rePlay(){
        if(this.ball!=null){
            this.ball.setLife(true);
        }
    }
    @Override
    public void onDraw(Canvas canvas){
        //if(this.ball.isLife()){
            //vẽ bóng
            ball.Draw(canvas);
            player.Draw(canvas);
            ai.Draw(canvas);
            ai.update(ball);
            ball.update(player, ai ,ArrBoom);
            //ve boom
            for (Boom boom:ArrBoom) {
                boom.checkCollisionToBall(ball);//kiểm tra va chạm boom với bóng
                boom.DrawBall(canvas);
            }
            try {
                Thread.sleep(30);
            }catch (Exception e){
                e.printStackTrace();
            }
            invalidate();

    }
    public void update(){

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        player.update(event,this);
        return true;
    }


    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        //khoi tao
        if (cfCreateBoom) {
            this.cfCreateBoom = false;
            //tao banh
            ball = new Ball(50,50,R.drawable.ball1,SpeedX,SpeedY);

            BoomSizeX = 50;
            BoomSizeY = 50;
            ball.setX(x);
            ball.setY(y);
            ball.setView(this);
            ball.setSong(this.getContext());
            //tao boom
            for (int j = 3; j < 5; j++) {
                int i = 3;
                while ((i+3) * 50 < (w - 50)) {
                    int x = i++ * 50;
                    int y = 2*h/3 - j*50;
                    Boom p = new Boom(BoomSizeX, BoomSizeY,R.drawable.boom, x, y, this.getContext());

                    p.setView(this);
                    ArrBoom.add(p);
                }
            }
            //tao nguoi choi
            player = new Player(100, 40, R.drawable.pink, w/3, (h-h/8), this.getContext());
            player.setView(this);
            //tao may choi
            ai = new AI(100, 40, R.drawable.star, w/2,(h/10), this.getContext());
            ai.setView(this);
        }
    }

}