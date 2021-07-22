package com.example.todoappandroid2.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoappandroid2.OnItemClickListener;
import com.example.todoappandroid2.R;
import com.example.todoappandroid2.model.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ArrayList<Task> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    /*public void addItems(ArrayList<String> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addItem(String str){
        this.list.add(str);
        notifyDataSetChanged();
    }
*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(Task task) {
        list.add(task);
        notifyItemInserted(list.indexOf(task));
    }

    public Task getItem(int position) {
        return list.get(position);
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /*public void addItems(ArrayList<String> list) {

    }*/

   /* public void addItems(Task task) {
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle,setData,setTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            setData = itemView.findViewById(R.id.setData);
            setTime = itemView.findViewById(R.id.setTime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onItemLongClick(getAdapterPosition());
                    return true;
                }
            });
        }

        public void bind(Task task) {
            textTitle.setText(task.getTitle());
            setTime.setText(task.getSetTime());
            setData.setText(task.getSetData());

            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
            String timeText = timeFormat.format(currentDate);

            setData.setText(dateText);
            setTime.setText(timeText);
        }


        /*public void onBind(String s) {
            textTitle.setText(s);
        }*/
    }
}