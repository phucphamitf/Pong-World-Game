package com.demo01.pdkpro.bong_bay.Control;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.demo01.pdkpro.bong_bay.Game.MainView;

public class GamePlayScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MainView(this));
    }

}
