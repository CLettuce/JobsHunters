package com.example.jobshunters;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Click_Corazon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offer_view);

        ImageView iconoCorazon = findViewById(R.id.iconoCorazon);
        final boolean[] isClicked = {false};

        iconoCorazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked[0]) {
                    iconoCorazon.setImageResource(R.drawable.ic_corazon);
                } else {
                    iconoCorazon.setImageResource(R.drawable.ic_corazon_act);
                }

                // Cambiar el estado
                isClicked[0] = !isClicked[0];


            }
        });
    }
}
