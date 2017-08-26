package com.chinmay.students.questionanswersapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Chinmay on 25-08-2017.
 */

class YearListAdapter extends RecyclerView.Adapter<YearListAdapter.YearViewHolder> {

    private static final String TAG = "YearListAdapter";

    private Context context;
    private List<QuestionPaper> yearList;
    public class YearViewHolder extends RecyclerView.ViewHolder {
        TextView yearText;
        ImageButton yearSelectImageButton;

        public YearViewHolder(View yearView) {
            super(yearView);
            Log.e(TAG,"Text view is set");
            yearText= (TextView) yearView.findViewById(R.id.yearText);
            yearSelectImageButton= (ImageButton) yearView.findViewById(R.id.yearSelect);
        }
    }

    public YearListAdapter(Context context,List<QuestionPaper> yearList) {
        super();
        this.context=context;
        this.yearList=yearList;
        Log.e(TAG,"Year list is set");
    }

    @Override
    public YearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_second, parent, false);

        Log.e(TAG,"Layout is set");
        return new YearViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final YearViewHolder holder, final int position) {

        Log.e(TAG,"onBindViewHolder is called");
        QuestionPaper yearQuestionPaper = yearList.get(position);
        holder.yearText.setText(yearQuestionPaper.getYear());
        holder.yearSelectImageButton.setImageResource(R.drawable.ic_action_name);

        holder.yearSelectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                QuestionPaper selectedQuestionPaper = yearList.get(position);
                String yearSelected = selectedQuestionPaper.getYear();
                Log.e(TAG, "Year is "+yearSelected);

                ((SecondActivity)context).displaySubjects(yearSelected);
            }
        });
        Log.e(TAG,"Year is displayed");
    }

    @Override
    public int getItemCount() {
        Log.e(TAG,"getItemCount is called");
        return (yearList != null) ? yearList.size() : 0;
    }

}
