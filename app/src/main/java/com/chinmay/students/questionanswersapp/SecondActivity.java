package com.chinmay.students.questionanswersapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    String departmentName;
    QuestionPaper questionPaper;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    RecyclerView yearRecyclerView;
    RecyclerView.LayoutManager yearLayoutManager;
    YearListAdapter yearListAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent departmentNameIntent=getIntent();
        departmentName = departmentNameIntent.getStringExtra("departmentName");
        Log.e(TAG, "Department name is "+ this.departmentName);

        yearRecyclerView = (RecyclerView)findViewById(R.id.yearRecyclerView);
        yearLayoutManager = new LinearLayoutManager(this);
        yearRecyclerView.setLayoutManager(yearLayoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child(departmentName);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long childrenCount = dataSnapshot.getChildrenCount();
                Log.e(TAG, "Children count is is "+childrenCount);
                List<QuestionPaper> yearList=new ArrayList<>();

                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    questionPaper=new QuestionPaper();
                   questionPaper.setYear(snapshot.getKey());
                    yearList.add(questionPaper);
                    String year=questionPaper.getYear();
                    Log.e(TAG,"Year is "+year);
                    yearListAdapter = new YearListAdapter(SecondActivity.this, yearList);
                    yearListAdapter.notifyDataSetChanged();
                    yearRecyclerView.setAdapter(yearListAdapter);
                    Log.e(TAG,"setAdapter called ");

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void displaySubjects(String yearSelected) {

        String selectedYear=yearSelected;
        Intent departmentAndYearIntent=new Intent(SecondActivity.this,SubjectsActivity.class);
        departmentAndYearIntent.putExtra("departmentName", departmentName);
        departmentAndYearIntent.putExtra("selectedYear", selectedYear);
        startActivity(departmentAndYearIntent);
    }
}
