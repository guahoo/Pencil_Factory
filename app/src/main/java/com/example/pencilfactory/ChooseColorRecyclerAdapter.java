package com.example.pencilfactory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChooseColorRecyclerAdapter extends RecyclerView.Adapter<ChooseColorRecyclerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Color_model> color;
    private Context context;
    private ChooseColorRecyclerAdapter.OnItemClickListener mItemClickListener;



    ChooseColorRecyclerAdapter(Context context, List <Color_model> color) {
        this.color = color;
        this.inflater = LayoutInflater.from(context);
        this.context = context;

    }



    @NonNull
    @Override
    public ChooseColorRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.listview_item, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(ChooseColorRecyclerAdapter.ViewHolder holder, int position) {
        final Color_model color_model = color.get(position);
        holder.color_view.setColorFilter(ContextCompat.getColor(context, color_model.getColor()),
                android.graphics.PorterDuff.Mode.MULTIPLY);

    }



    @Override
    public int getItemCount() {
        return color.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView color_view;

        ViewHolder(View view) {
            super(view);
            color_view = view.findViewById(R.id.recycler_view_color_circle);
            color_view.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            System.out.println("onClick");
            mItemClickListener.onItemClick(v, getAdapterPosition()); //OnItemClickListener mItemClickListener;
        }



    }
    public interface OnItemClickListener {
       void onItemClick(View view, int position);
    }

    void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    }




