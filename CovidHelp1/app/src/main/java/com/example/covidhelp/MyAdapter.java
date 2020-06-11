package com.example.covidhelp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Shopkepeer>listData;
    private Context context;
    private static final String TAG = "NotesRecyclerAdapter";
//commit changees

    public MyAdapter(List<Shopkepeer> listData,Context context) {

        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_data,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Shopkepeer ld = listData.get(position);
        holder.txtid.setText(ld.getShopname());
        holder.txtname.setText(ld.getCity());
        holder.txtmovie.setText(ld.getArea());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtid,txtname,txtmovie;

        public ViewHolder(View itemView) {
            super(itemView);
            txtid=(TextView)itemView.findViewById(R.id.ls_shopnm);
            txtname=(TextView)itemView.findViewById(R.id.ls_city);
            txtmovie=(TextView)itemView.findViewById(R.id.ls_area);

        }


    }


}

