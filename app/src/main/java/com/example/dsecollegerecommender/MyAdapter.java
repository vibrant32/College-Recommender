package com.example.dsecollegerecommender;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myviewholder>{
    ArrayList<Model> dataholder = new ArrayList<Model>();

    public MyAdapter(ArrayList<Model> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.dtecode.setText(dataholder.get(position).getDtecode());
        holder.clgname.setText(dataholder.get(position).getCollegename());
        holder.clgloc.setText(dataholder.get(position).getLocation());
        holder.branch.setText(dataholder.get(position).getBranch());
        holder.category.setText(dataholder.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public void clear() {
        int size = dataholder.size();
        dataholder.clear();
        notifyItemRangeRemoved(0, size);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView dtecode, clgname, clgloc, branch, category;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            dtecode = (TextView)itemView.findViewById(R.id.dtecode);
            clgname = (TextView)itemView.findViewById(R.id.collegename);
            clgloc = (TextView)itemView.findViewById(R.id.location);
            branch = (TextView)itemView.findViewById(R.id.branch);
            category = (TextView)itemView.findViewById(R.id.category);
        }
    }


}
