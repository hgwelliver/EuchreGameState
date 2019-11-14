package com.example.euchregamestate.Euchre;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.euchregamestate.R;

import java.io.Serializable;

public class help extends AppCompatActivity implements Serializable {

    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets layout to helpMenu
        setContentView(R.layout.activity_help);
        exitButton = (Button)findViewById(R.id.exitButton);


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //returns to game
                finish();
            }
        });
    }

}
