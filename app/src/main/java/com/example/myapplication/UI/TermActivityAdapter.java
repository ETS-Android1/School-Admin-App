package com.example.myapplication.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

import Entity.TermEntity;

public class TermActivityAdapter extends RecyclerView.Adapter<TermActivityAdapter.MyViewHolder> {

    private final Context context;
    private List<TermEntity> termList;

    public TermActivityAdapter(Context context) {

        this.context = context;

    }

    public void setTermList(List<TermEntity> termList) {
        this.termList = termList;
        notifyDataSetChanged();
    }

    public void notifyInsert(int i) {
        notifyItemInserted(i);
    }



    @NonNull
    @Override
    public TermActivityAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermActivityAdapter.MyViewHolder holder, int position) {
        holder.tvTermName.setText(this.termList.get(position).getTermName());
        holder.tvTermStart.setText(this.termList.get(position).getTermStartDate());
        holder.tvTermEnd.setText(this.termList.get(position).getTermEndDate());

        holder.tvTermName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final TermEntity recyclerRowTerm = termList.get(holder.getAdapterPosition());
                Intent intent = new Intent(context, TermDetailEditActivity.class);
                intent.putExtra("termID", recyclerRowTerm.getTermID());
                intent.putExtra("termName", recyclerRowTerm.getTermName());
                intent.putExtra("termStart", recyclerRowTerm.getTermStartDate());
                intent.putExtra("termEnd", recyclerRowTerm.getTermEndDate());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (termList != null)
            return termList.size();
        else return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTermName;
        TextView tvTermStart;
        TextView tvTermEnd;

        public MyViewHolder(View view) {
            super(view);
            tvTermName = view.findViewById(R.id.rowTitle);
            tvTermStart = view.findViewById(R.id.rowStart);
            tvTermEnd = view.findViewById((R.id.rowEnd));


        }
    }

}
