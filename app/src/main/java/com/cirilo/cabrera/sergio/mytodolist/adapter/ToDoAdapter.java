package com.cirilo.cabrera.sergio.mytodolist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cirilo.cabrera.sergio.mytodolist.R;
import com.cirilo.cabrera.sergio.mytodolist.model.ToDoItem;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoHolder> {

    private List<ToDoItem> items;
    private ToDoHolder.OnItemClickListener listener;


    public ToDoAdapter(@NonNull List<ToDoItem> items, ToDoHolder.OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ToDoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ToDoHolder holder = new ToDoHolder(view, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoHolder holder, int position) {
        ToDoItem item = items.get(position);

        holder.iconImv.setImageResource(R.drawable.ic_security);
        holder.titleTxv.setText(item.getTitle());
        holder.subtitleTxv.setText(item.getSubtitle());

        if(item.isStatus()) {
            holder.statusImv.setImageResource(R.drawable.ic_done);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ToDoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iconImv;
        TextView titleTxv;
        TextView subtitleTxv;
        ImageView statusImv;

        OnItemClickListener onItemClickListener;

        ToDoHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);

            iconImv = view.findViewById(R.id.item_icon);
            titleTxv = view.findViewById(R.id.item_title);
            subtitleTxv = view.findViewById(R.id.item_subtitle);
            statusImv = view.findViewById(R.id.item_status);

            this.onItemClickListener = onItemClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.OnClickItem(getAdapterPosition());
        }

        public interface OnItemClickListener {
            void OnClickItem(int position);
        }

    }
}
