package com.example.sqlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.R;
import com.example.sqlite.models.User;

import java.util.ArrayList;

public class CustomListAdapterDeleteRows extends RecyclerView.Adapter<CustomListAdapterDeleteRows.ViewHolder> {
    private Context context;
    private ArrayList<User> userList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDeleteClick(User user);
    }

    public CustomListAdapterDeleteRows(Context context, ArrayList<User> userList, OnItemClickListener listener) {
        this.context = context;
        this.userList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_delete, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userName.setText(user.getUsername());
        holder.userPhone.setText(user.getPhone());
        holder.userEmail.setText(user.getEmail());
        holder.deleteButton.setOnClickListener(v -> listener.onDeleteClick(user));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userPhone, userEmail;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userPhone = itemView.findViewById(R.id.userPhone);
            userEmail = itemView.findViewById(R.id.userEmail);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}