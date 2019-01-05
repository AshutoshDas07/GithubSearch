package com.example.ashutosh_pc.githubsearch;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.MyViewHolder> {

    public ArrayList<Items> getItem() {
        return item;
    }

    public void setItem(ArrayList<Items> item) {
        this.item = item;
        notifyDataSetChanged();
    }

    ArrayList<Items> item;


    public GitAdapter(ArrayList<Items> item) {
        this.item = item;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_elements,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.nameTV.setText(item.get(position).getLogin());
            Picasso.get().load(item.get(position).avatar_url).into(holder.picIV);
    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView picIV;
        TextView nameTV;
        public MyViewHolder(final View itemView) {
            super(itemView);
            picIV=itemView.findViewById(R.id.pic);
            nameTV=itemView.findViewById(R.id.name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Items currentItem=item.get(getAdapterPosition());
                    Intent intent=new Intent(itemView.getContext(),UserDetailsActivity.class);
                    intent.putExtra("currentItem",currentItem.getLogin());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
