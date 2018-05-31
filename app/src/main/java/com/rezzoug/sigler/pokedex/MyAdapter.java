package com.rezzoug.sigler.pokedex;

        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import org.json.JSONArray;
        import org.json.JSONException;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PokemonHolder> {

    JSONArray pokemonArray;

    public MyAdapter(JSONArray array){
        this.pokemonArray = array;
    }

    @Override
    public PokemonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cell,parent,false);
        PokemonHolder holder = new PokemonHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PokemonHolder holder, int position) {
        try {
            String name = pokemonArray.getJSONObject(position).getString("name");
            String no = pokemonArray.getJSONObject(position).getString("national_id").toString();
            holder.changeText(name,no);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return pokemonArray.length();
    }

    public void setNewPokemon(JSONArray array){
        this.pokemonArray = array;
        notifyDataSetChanged();
    }

    public class PokemonHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_no;

        public PokemonHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.rv_element_name);
            tv_no = itemView.findViewById(R.id.rv_element_no);
        }

        private void changeText(String name,String no){
            tv_name.setText(name);
            tv_no.setText(no);


        }
    }

}
