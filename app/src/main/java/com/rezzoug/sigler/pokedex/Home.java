package com.rezzoug.sigler.pokedex;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Home extends Activity {


    private Button button;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FirstGeneration.class );
                startActivity(intent);

            }
        });

        this.button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), Gen2List.class );
                startActivity(intent2);

            }
        });

    }


    public void DisplayToast (View v){
        Toast.makeText(Home.this,getString(R.string.Toast),Toast.LENGTH_SHORT).show();

    }


}
