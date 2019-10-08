package com.example.myappfirebase.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myappfirebase.Model.MyModel;
import com.example.myappfirebase.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyRow>{


    private List<MyModel> userModelList;

    public MyAdapter(List<MyModel> gamesModelList) {  // Constructor
        this.userModelList = gamesModelList;
    }

    @Override
    public MyRow onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cell, parent, false);
        MyRow viewHolder = new MyRow(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRow holder, int position) {  // Pinta celda
        String receivedName = userModelList.get(position).getNombre();  // Obtengo el String que quiero pintar en la celda
        holder.name.setText(receivedName);
        String receivedEdad = userModelList.get(position).getApellido();
        holder.subtitle.setText(receivedEdad);


    }

    @Override
    public int getItemCount() {  // Tamaño de la Lista (RecyclerView)
        return userModelList.size();
    }

    static class MyRow extends RecyclerView.ViewHolder {  // Crea mi vista celda, la cual tiene un TextView
        private TextView name;
        private TextView subtitle;

        MyRow(View v) {
            super(v);
            name = v.findViewById(R.id.tvTittle);
            subtitle = v.findViewById(R.id.tvSubtittle);

        }
    }

    public void loadData (ArrayList<MyModel> myModelArrayList ) {
        userModelList = myModelArrayList;
        notifyDataSetChanged();

    }
 /*
    public void addNote (String title, String subtitle) {

        MyModel newCell = new MyModel();
        newCell.setNombre(title);
        newCell.setApellido(subtitle);

        userModelList.add(1, newCell);

        notifyDataSetChanged();



        //notifyItemInserted(0);

    }


   public void removeNote (int position) {
        if (position <= userModelList.size()-1) {
            userModelList.remove(position);
            notifyDataSetChanged();


        }

    }


    public void moveNote (int posicionOrigen) {

        MyModel local = userModelList.get(posicionOrigen);

        userModelList.remove(posicionOrigen);

        int position = userModelList.size();
        userModelList.add(position, local);

        notifyDataSetChanged();

    }*/

}
