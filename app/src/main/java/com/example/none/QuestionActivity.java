package com.example.none;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class QuestionActivity extends AppCompatActivity {
    private TextView mBtv_title;
    private Button btn_former;
    private Button btn_result;
    private RadioGroup radioGroup;
    private ArrayList<RadioButton> rbList = new ArrayList<RadioButton>();
    private Test test;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_question );
        mBtv_title = findViewById( R.id.tv_title );
        btn_former =findViewById( R.id.btn_test );

        test = TestActivity.test;
        id=getIntent().getIntExtra( "id" ,0);
        mBtv_title.setText( test.questions.get( id ).title );

        radioGroup = findViewById(R.id.radioGroup);
        RadioButton rb;
        for(Option option : test.questions.get( id ).options)
        {
            rb = new RadioButton(this );
            rb.setId(option.id);
            rb.setText(option.text);
            radioGroup.addView(rb);
        }
        radioGroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                test.questions.get( id ).selectOption( checkedId );
                if(test.questions.size() >id+1){
                    Intent intent =new Intent( QuestionActivity.this,QuestionActivity.class );
                    intent.putExtra("id",id+1);
                    startActivity( intent );
                }
            }
        } );
        btn_result = findViewById( R.id.btn_result );
        if(test.questions.size() ==id+1){
            btn_result.setVisibility( View.VISIBLE );
            btn_result.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int totalscore = test.countScore();
                    Result res = null;
                    for(Result result:test.results){
                        if(totalscore <= result.upperLimit && totalscore >= result.downLimit){
                            res = result;
                        }
                    }
                    Record record = new Record();
                    record.score =totalscore;
                    record.testId = test.id;
                    record.testName = test.name;
                    record.text = res.text;
                    Intent intent = new Intent( QuestionActivity.this,ResultActivity.class );
                    intent.putExtra( "record",record );
                    startActivity( intent );
                }
            } );
        }
    }
}
