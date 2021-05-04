package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class qnaList extends AppCompatActivity {
    private static qna qnalist[];
    private ListView qlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_list);
        qlist = (ListView) findViewById(R.id.qlist);

    }
}
