package com.chinmay.students.questionanswersapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Chinmay on 26-08-2017.
 */

class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.SubjectViewHolder> {

    private static final String TAG = "SubjectListAdapter";

    private Context context;
    private List<QuestionPaper> subjectList;

    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        TextView yearText;
        public SubjectViewHolder(View subjectView) {
            super(subjectView);
            yearText= (TextView) subjectView.findViewById(R.id.subjectText);
        }
    }

    public SubjectListAdapter(List<QuestionPaper> subjectList) {
        super();
        this.subjectList=subjectList;
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_subjects, parent, false);

        Log.e(TAG,"Layout is set");
        return new SubjectListAdapter.SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {
        Log.e(TAG,"onBindViewHolder is called");
        QuestionPaper subjectQuestionPaper = subjectList.get(position);
        holder.yearText.setText(subjectQuestionPaper.getSubjectName());
    }

    @Override
    public int getItemCount() {
        Log.e(TAG,"getItemCount is called");
        return (subjectList != null) ? subjectList.size() : 0;
    }


}
