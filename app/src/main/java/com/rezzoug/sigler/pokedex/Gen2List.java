package com.rezzoug.sigler.pokedex;

        import android.app.NotificationManager;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.support.v4.app.NotificationCompat;
        import android.support.v4.content.LocalBroadcastManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.InputStream;

public class Gen2List extends AppCompatActivity {

    private final String TAG = "Gen2List";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen2);

        recyclerView = findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        MyAdapter pa = new MyAdapter(getPokemonFromFile());

        recyclerView.setAdapter(pa);

        DDLService.startActionGetPokemon(this);

        IntentFilter intentFilter = new IntentFilter(POKEMON_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new PokemonUpdate(), intentFilter);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(android.R.drawable.stat_sys_download_done);
        mBuilder.setContentTitle("POKEDEX");
        mBuilder.setContentText(getString(R.string.brcast));
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, mBuilder.build());

    }


    public static final String POKEMON_UPDATE = "pokedex.POKEMON_UPDATE";

    public class PokemonUpdate extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Intent : " + getIntent().getAction());
            JSONArray array = getPokemonFromFile();
            MyAdapter pa = (MyAdapter) recyclerView.getAdapter();
            pa.setNewPokemon(array);
            Log.d("count ", Integer.toString(array.length()));
        }
    }

    public JSONArray getPokemonFromFile(){
        try {

            InputStream is = new FileInputStream(getCacheDir() + "/" + "pokemon.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONObject(new String(buffer,"UTF-8")).getJSONArray("objects");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new JSONArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }




}