package com.chinmay.students.questionanswersapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubjectsActivity extends AppCompatActivity {

    private static final String TAG = "SubjectsActivity";

    QuestionPaper questionPaper;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    RecyclerView subjectRecyclerView;
    RecyclerView.LayoutManager subjectLayoutManager;
    SubjectListAdapter subjectListAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        Intent departmentAndYearIntent=getIntent();
        String departmentName = departmentAndYearIntent.getStringExtra("departmentName");
        String selectedYear = departmentAndYearIntent.getStringExtra("selectedYear");
        Log.e(TAG, "Department name is "+departmentName+" and Year is "+selectedYear);

        subjectRecyclerView = (RecyclerView)findViewById(R.id.subjectRecyclerView);
        subjectLayoutManager = new LinearLayoutManager(this);
        subjectRecyclerView.setLayoutManager(subjectLayoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child(departmentName).child(selectedYear);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long childrenCount = dataSnapshot.getChildrenCount();
                Log.e(TAG, "Children count is is "+childrenCount);
                List<QuestionPaper> subjectList=new ArrayList<>();

                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    questionPaper=new QuestionPaper();
                    questionPaper.setSubjectName(snapshot.getKey());
                    subjectList.add(questionPaper);
                    String subjectName=questionPaper.getYear();
                    Log.e(TAG,"Subject name is "+subjectName);
                    subjectListAdapter = new SubjectListAdapter(subjectList);
                    subjectListAdapter.notifyDataSetChanged();
                    subjectRecyclerView.setAdapter(subjectListAdapter);
                    Log.e(TAG,"setAdapter called ");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
