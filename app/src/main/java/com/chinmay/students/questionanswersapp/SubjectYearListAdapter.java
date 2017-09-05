package com.chinmay.students.questionanswersapp;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Chinmay on 03-09-2017.
 */

public class SubjectYearListAdapter extends BaseExpandableListAdapter {

    private static final String TAG="SubjectYearListAdapter";

    private Context context;
    private List<String> subjectNameList;
    private HashMap<String, List<String>> subjectYearPaperList;

    public SubjectYearListAdapter(Context context, List<String> subjectNameList, HashMap<String, List<String>> yearPaperList) {

        Log.e(TAG, "SubjectYearListAdapter constructor called");
        this.context = context;
        this.subjectNameList = subjectNameList;
        this.subjectYearPaperList = yearPaperList;
    }

    @Override
    public int getGroupCount() {
        Log.e(TAG, "getGroupCount called");
        return this.subjectNameList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.e(TAG, "getChildrenCount called");
        return this.subjectYearPaperList.get(this.subjectNameList.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        Log.e(TAG, "getGroup called");
        return this.subjectNameList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Log.e(TAG, "getChild called");
        return this.subjectYearPaperList.get(this.subjectNameList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        Log.e(TAG, "getGroupId called");
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        Log.e(TAG, "getChildId called");
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        Log.e(TAG, "hasStableIds called");
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        Log.e(TAG, "isChildSelectable called");
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Log.e(TAG, "getGroupView called");
        String subjectName = (String) getGroup(groupPosition);
        Log.e(TAG, "Subject name in getGroupView is "+subjectName);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.subject_list, null);
            Log.e(TAG, "LayoutInflater initialized");
        }

        TextView subjectText = (TextView) convertView
                .findViewById(R.id.subjectText);
        Log.e(TAG, "subjectText initialized");
        subjectText.setTypeface(null, Typeface.BOLD);
        subjectText.setText(subjectName);
        Log.e(TAG, "subjectText is set");
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Log.e(TAG, "getChildView called");
        String yearPaper = (String) getChild(groupPosition, childPosition);
        Log.e(TAG, "Year paper in getChildView is "+yearPaper);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.year_paper_list, null);
            Log.e(TAG, "LayoutInflater initialized");
        }

        TextView yearPaperText = (TextView) convertView
                .findViewById(R.id.yearPaperText);
        Log.e(TAG, "yearPaperText initialized");
        yearPaperText.setText(yearPaper);
        Log.e(TAG, "yearPaperText is set");
        return convertView;
    }
}
