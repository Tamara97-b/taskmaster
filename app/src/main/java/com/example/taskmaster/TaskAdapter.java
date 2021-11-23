package com.example.taskmaster;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    List<com.amplifyframework.datastore.generated.model.Task> allTask = new ArrayList<com.amplifyframework.datastore.generated.model.Task>();

    public TaskAdapter(List<com.amplifyframework.datastore.generated.model.Task> allTask) {
        this.allTask = allTask;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public Task task;
        public View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_blank,parent,false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.task = allTask.get(position);

        TextView title = holder.itemView.findViewById(R.id.titleFragment1);
        TextView body = holder.itemView.findViewById(R.id.bodeFragment1);
        TextView state = holder.itemView.findViewById(R.id.stateFragment1);
        Button gotodetails =holder.itemView.findViewById(R.id.goToDetailsButton);

        title.setText(holder.task.getTitle());
        body.setText(holder.task.getBody());
        state.setText(holder.task.getState());
        gotodetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskTitle=holder.task.getTitle();
                Intent goToDetailsPage=new Intent(v.getContext(),TaskDetail.class);
                goToDetailsPage.putExtra("task detail",taskTitle);
                v.getContext().startActivity(goToDetailsPage);
            }
        });
    }
    @Override
    public int getItemCount() {
        return allTask.size();
    }
}
