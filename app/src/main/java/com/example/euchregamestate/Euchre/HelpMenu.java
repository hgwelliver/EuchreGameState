package com.example.euchregamestate.Euchre;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.euchregamestate.R;

import java.io.Serializable;

public class HelpMenu extends AppCompatActivity implements Serializable {

    private Button returnButton;

    @Override
    /**
     * An onCreate method that will set the content view to the help menu to see the rules
     *
     * @param savedInstancesState simple state
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets layout to helpMenu
        //setContentView(R.layout.put_help_menu_name_here);

        //need to make exitButton
        //returnButton = (Button)findViewById(R.id.exitButton);


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//ends current activity and returns to game
            }
        });
    }

}
