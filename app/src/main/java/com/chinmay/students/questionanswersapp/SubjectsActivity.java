package com.chinmay.students.questionanswersapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubjectsActivity extends AppCompatActivity {

    private static final String TAG = "SubjectsActivity";

    QuestionPaper subjectQuestionPaper;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference subjectDatabaseReference, yearPaperDatabaseReference;

    SubjectYearListAdapter subjectYearListAdapter;
    ExpandableListView expandableListView;
    List<String> yearPaperList;
    private List<String> subjectNameList;
    private HashMap<String, List<String>> subjectYearPaperList;

    String subjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        Intent departmentAndYearIntent=getIntent();
        final String departmentName = departmentAndYearIntent.getStringExtra("departmentName");
        final String selectedYear = departmentAndYearIntent.getStringExtra("selectedYear");
        Log.e(TAG, "Department name is "+departmentName+" and Year is "+selectedYear);
        getSupportActionBar().setTitle(departmentName);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        Log.e(TAG, "ExpandableListView initialized");

        firebaseDatabase = FirebaseDatabase.getInstance();
        subjectDatabaseReference=firebaseDatabase.getReference().child(departmentName).child(selectedYear);
        Log.e(TAG, "databaseReference1 initialized");

        subjectDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {

                Log.e(TAG, "databaseReference1 onDataChange called");

                long subjectCount = dataSnapshot1.getChildrenCount();
                Log.e(TAG, "Subject count is is "+subjectCount);
                subjectNameList=new ArrayList<>();
                Log.e(TAG, "subjectNameList initialized");
                subjectYearPaperList=new HashMap<>();
                Log.e(TAG, "subjectYearPaperList initialized");

                for (final DataSnapshot snapshot1:dataSnapshot1.getChildren())
                {
                    Log.e(TAG, "databaseReference1 for loop called");
                    subjectQuestionPaper=new QuestionPaper();
                    Log.e(TAG, "subjectQuestionPaper initialized");
                    subjectQuestionPaper.setSubjectName(snapshot1.getKey());
                    Log.e(TAG, "Subject name is set");

                    subjectNameList.add(subjectQuestionPaper.getSubjectName());
                    Log.e(TAG, "subjectNameList added subject name");
                     subjectName=subjectQuestionPaper.getSubjectName();
                    Log.e(TAG,"Subject name is "+subjectName);

                    yearPaperDatabaseReference=subjectDatabaseReference.child(subjectName);
                    Log.e(TAG, "databaseReference2 initialized");
                    yearPaperDatabaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot2) {
                            Log.e(TAG, "databaseReference2 onDataChange called");
                            long yearPaperCount = dataSnapshot2.getChildrenCount();
                            Log.e(TAG, "Year paper count is "+yearPaperCount);

                           yearPaperList=new ArrayList<>();
                            Log.e(TAG, "yearPaperList initialized");
                            for (DataSnapshot snapshot2:dataSnapshot2.getChildren())
                            {
                                Log.e(TAG, "databaseReference2  for loop called");
                                String yearPaper=snapshot2.getKey();
                                Log.e(TAG,"Year Paper is "+yearPaper);
                                yearPaperList.add(yearPaper);
                                Log.e(TAG,"yearPaperList added year paper ");

                            }
                            Log.e(TAG, "subjectYearPaperList put values");
                            subjectYearPaperList.put(subjectName, yearPaperList);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                subjectYearListAdapter=new SubjectYearListAdapter(SubjectsActivity.this, subjectNameList, subjectYearPaperList);
                Log.e(TAG, "subjectYearListAdapter initialized");
                subjectYearListAdapter.notifyDataSetChanged();
                Log.e(TAG, "notifyDataSetChanged called");
                expandableListView.setAdapter(subjectYearListAdapter);
                Log.e(TAG,"setAdapter called ");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
