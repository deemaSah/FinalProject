package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class search extends AppCompatActivity {
private Button searchbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchbtn= findViewById(R.id.searchbtn);
        //searchDialog searchdialog = new searchDialog();
        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.adultscounter,searchdialog,"Search Dialog");
        //transaction.commit();
    }


    public void searchbtn(View view) {
      openDialog();
    }

    private void openDialog() {
     searchDialog searchsDialog = new searchDialog();
     searchsDialog.show(getSupportFragmentManager(),"searchDialog");
    }
}