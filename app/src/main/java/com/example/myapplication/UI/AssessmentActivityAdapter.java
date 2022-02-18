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

import Entity.AssessmentEntity;
import Entity.CourseEntity;

public class AssessmentActivityAdapter extends RecyclerView.Adapter<AssessmentActivityAdapter.MyViewHolder> {

    private final Context context;
    private List<AssessmentEntity> assessmentList;

    public AssessmentActivityAdapter(Context context) {
        this.context = context;
    }

    public void setAssessmentList(List<AssessmentEntity> assessmentList) {
        this.assessmentList = assessmentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AssessmentActivityAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentActivityAdapter.MyViewHolder holder, int position) {
        holder.tvAssessName.setText(this.assessmentList.get(position).getAssessmentTitle());
        holder.tvAssessStart.setText(this.assessmentList.get(position).getAssessmentStartDate());
        holder.tvAssessEnd.setText(this.assessmentList.get(position).getAssessmentEndDate());

        holder.tvAssessName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AssessmentEntity assessmentToEdit = assessmentList.get(holder.getAdapterPosition());
                Intent intent = new Intent(context, AssessmentDetailEditActivity.class);
                intent.putExtra("assessID", assessmentToEdit.getAssessmentID());
                intent.putExtra("assessTitle", assessmentToEdit.getAssessmentTitle());
                intent.putExtra("assessType", assessmentToEdit.getAssessmentType());
                intent.putExtra("assessStart", assessmentToEdit.getAssessmentStartDate());
                intent.putExtra("assessEnd", assessmentToEdit.getAssessmentEndDate());
                intent.putExtra("assessCourseID", assessmentToEdit.getAssociatedCourse());

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (assessmentList != null) {
            return assessmentList.size();
        } else return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvAssessName;
        TextView tvAssessStart;
        TextView tvAssessEnd;

        public MyViewHolder(View view) {

            super(view);
            tvAssessName = view.findViewById(R.id.rowTitle);
            tvAssessStart = view.findViewById(R.id.rowStart);
            tvAssessEnd = view.findViewById(R.id.rowEnd);
        }

    }
}
