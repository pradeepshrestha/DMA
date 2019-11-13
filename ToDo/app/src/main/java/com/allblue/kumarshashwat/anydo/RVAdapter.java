package com.allblue.kumarshashwat.anydo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {

    public class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tv;
        ImageView imv;
        public CardViewHolder(View itemView) {
            super(itemView);
            cv=(CardView)itemView.findViewById(R.id.cardview);
            tv=(TextView)itemView.findViewById(R.id.cardtext);
            imv=(ImageView)itemView.findViewById(R.id.card_image);
        }
    }
    List<Todo> alpha;
    RVAdapter(List<Todo> alpha){this.alpha=alpha;}

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        CardViewHolder cvh=new CardViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.tv.setText(alpha.get(position).cardtext);
        holder.imv.setImageResource(alpha.get(position).photo_id);
    }

    @Override
    public int getItemCount() {return alpha.size();}
}
