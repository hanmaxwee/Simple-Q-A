package com.example.none;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;

public class TestActivity extends AppCompatActivity {
    private Button mBtn_test;
    private TextView mBtv_title;
    private TextView mBtv_content;
    public static Test test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_test );
        mBtn_test = findViewById( R.id.btn_test );
        mBtv_title = findViewById( R.id.tv_title );
        String title = getIntent().getStringExtra( "title" );
        mBtv_title.setText( title );
        mBtv_content = findViewById( R.id.tv_content );
        String synopsis = getIntent().getStringExtra( "synopsis" );
        mBtv_content.setText( synopsis );

        InputStream input = this.getResources().openRawResource(R.raw.fangyu);
        test = new Test(input);

        mBtn_test.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( TestActivity.this,QuestionActivity.class );
                intent.putExtra("id",0);
                startActivity( intent );
            }
        } );
    }
}
