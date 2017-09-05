package com.chinmay.students.questionanswersapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button button;
    String buttonText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void departmentSelection(View view) {

        switch (view.getId()) {
            case R.id.firstYear:
                button= (Button) findViewById(R.id.firstYear);
                buttonText = button.getText().toString();
                intent=new Intent(MainActivity.this, YearActivity.class);
                intent.putExtra("departmentName", buttonText);
                startActivity(intent);
                break;

            case R.id.electronicsAndCommunication:
                button= (Button) findViewById(R.id.electronicsAndCommunication);
                buttonText = button.getText().toString();
                intent=new Intent(MainActivity.this, YearActivity.class);
                intent.putExtra("departmentName", buttonText);
                startActivity(intent);
                break;

            case R.id.computerScience:
                button= (Button) findViewById(R.id.computerScience);
                buttonText = button.getText().toString();
                intent=new Intent(MainActivity.this, YearActivity.class);
                intent.putExtra("departmentName", buttonText);
                startActivity(intent);
                break;

            case R.id.informationScience:
                button= (Button) findViewById(R.id.informationScience);
                buttonText = button.getText().toString();
                intent=new Intent(MainActivity.this, YearActivity.class);
                intent.putExtra("departmentName", buttonText);
                startActivity(intent);
                break;

            case R.id.electrical:
                button= (Button) findViewById(R.id.electrical);
                buttonText = button.getText().toString();
                intent=new Intent(MainActivity.this, YearActivity.class);
                intent.putExtra("departmentName", buttonText);
                startActivity(intent);
                break;

            case R.id.mechanical:
                button= (Button) findViewById(R.id.mechanical);
                buttonText = button.getText().toString();
                intent=new Intent(MainActivity.this, YearActivity.class);
                intent.putExtra("departmentName", buttonText);
                startActivity(intent);
                break;

            case R.id.civil:
                button= (Button) findViewById(R.id.civil);
                buttonText = button.getText().toString();
                intent=new Intent(MainActivity.this, YearActivity.class);
                intent.putExtra("departmentName", buttonText);
                startActivity(intent);
                break;
        }

    }
}
