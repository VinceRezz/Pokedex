package com.rezzoug.sigler.pokedex;

import android.app.AlertDialog;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class FirstGeneration extends AppCompatActivity {



    private ImageButton notif1;
    private ImageButton dial1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen1);

        getSupportActionBar().setTitle(getString(R.string.B1));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        notif1 = findViewById(R.id.Btsala);
        notif1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreerNotif();
            }
        });

        dial1 = findViewById(R.id.Btbulb);
        dial1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreerDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.mitem:
                Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.pokepedia.fr/Liste_des_Pok%C3%A9mon_de_la_premi%C3%A8re_g%C3%A9n%C3%A9ration"));
                startActivity(web);
        }
        return true;
    }

    public void CreerNotif() {
        NotificationCompat.Builder notifbuild = new NotificationCompat.Builder(FirstGeneration.this)
                .setSmallIcon(android.R.drawable.stat_notify_error)
                .setContentTitle(getString(R.string.Notif));
        NotificationManagerCompat notifmn =NotificationManagerCompat.from(FirstGeneration.this);
        notifmn.notify(1, notifbuild.build());
    }

    public void CreerDialog(){
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.Dialog))
                .create().show();

    }
}

