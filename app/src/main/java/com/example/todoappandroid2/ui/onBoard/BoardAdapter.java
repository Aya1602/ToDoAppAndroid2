package com.example.todoappandroid2.ui.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoappandroid2.R;
public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private Finish finish;

    private String[] titles = new String[] {"Hello", "How are you?", "Where are you?"};
    private int []imgLogo = new int[]{R.drawable.ic_baseline_account_circle_24,R.drawable.ic_baseline_person_24,R.drawable.ic_baseline_account_circle_24};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
    public void SetOpenHome(Finish finish){
        this.finish = finish;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLogo2;
        private Button button;
        private TextView textTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            imgLogo2 = itemView.findViewById(R.id.imageView);
            button = itemView.findViewById(R.id.btn_start);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish.btnFinishClick();
                }
            });
        }
        public void bind(int position) {
            textTitle.setText(titles[position]);
            imgLogo2.setImageResource(imgLogo[position]);

            if (position == titles.length-1) {
                button.setVisibility(View.VISIBLE);
            } else {
                button.setVisibility(View.GONE);
            }
        }
    }
}
interface Finish{
    void btnFinishClick();
}