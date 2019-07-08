package com.example.none;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView mTv_testname;
    private TextView mTv_score;
    private TextView mTv_text;
    private TextView mTv_time;
    Record record = new Record();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_result );
        mTv_testname = findViewById( R.id.tv_testname );
        mTv_score = findViewById( R.id.tv_score );
        mTv_text = findViewById( R.id.tv_text );
        mTv_time = findViewById( R.id.tv_time );
        record = (Record) getIntent().getSerializableExtra( "record" );
        mTv_testname.setText( record.testName );
        mTv_score.setText( String.valueOf(record.score ));
        mTv_text.setText( record.text );
        mTv_time.setText( record.time );
    }
}
