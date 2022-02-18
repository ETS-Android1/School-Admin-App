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

import Entity.CourseEntity;

public class CourseActivityAdapter extends RecyclerView.Adapter<CourseActivityAdapter.MyViewHolder> {

    private final Context context;
    private List<CourseEntity> coursesList;

    public CourseActivityAdapter(Context context) {
        this.context = context;
    }

    public void setCoursesList(List<CourseEntity> coursesList) {
        this.coursesList = coursesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseActivityAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseActivityAdapter.MyViewHolder holder, int position) {
        holder.tvCourseName.setText(this.coursesList.get(position).getCourseTitle());
        holder.tvCourseStart.setText(this.coursesList.get(position).getStartDate());
        holder.tvCourseEnd.setText(this.coursesList.get(position).getEndDate());

        holder.tvCourseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CourseEntity courseToEdit = coursesList.get(holder.getAdapterPosition());
                Intent intent = new Intent(context, CourseDetailEditActivity.class);
                intent.putExtra("courseID", courseToEdit.getCourseID());
                intent.putExtra("courseName", courseToEdit.getCourseTitle());
                intent.putExtra("courseStart", courseToEdit.getStartDate());
                intent.putExtra("courseEnd", courseToEdit.getEndDate());
                intent.putExtra("courseStatus", courseToEdit.getStatus());
                intent.putExtra("courseInstructor", courseToEdit.getCourseInstructorName());
                intent.putExtra("coursePhone", courseToEdit.getCourseInstructorPhone());
                intent.putExtra("courseEmail", courseToEdit.getCourseInstructorEmail());
                intent.putExtra("courseNotes", courseToEdit.getNotes());
                intent.putExtra("courseTermID", courseToEdit.getAssociatedTermID());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (coursesList != null) {
            return  coursesList.size();
        }
        else return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvCourseName;
        TextView tvCourseStart;
        TextView tvCourseEnd;

        public MyViewHolder(View view) {
            super(view);
            tvCourseName = view.findViewById(R.id.rowTitle);
            tvCourseStart = view.findViewById(R.id.rowStart);
            tvCourseEnd = view.findViewById(R.id.rowEnd);
        }
     }
}
