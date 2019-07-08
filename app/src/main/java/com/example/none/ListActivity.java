package com.example.none;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListActivity extends AppCompatActivity {
    private ListView mLv_title;
    public ArrayList<Test> testList;
    private Map<String,String> map;
    private ArrayList<Map<String,String>> list;

    TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list );

        try{
            InputStream input = this.getResources().openRawResource(R.raw.testlist);
            testList = parseXml(input);
            list = new ArrayList<>();
            for(Test test :testList){
                map = new HashMap<>( );
                map.put( "title",test.name );
                map.put( "synopsis",test.synopsis );
                list.add( map );
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mLv_title = findViewById( R.id.lv_title );
        adapter = new TestAdapter( this,testList);
        mLv_title.setAdapter(adapter);

        mLv_title.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent( ListActivity.this,TestActivity.class );
                Map<String,String> map1 = list.get( position );
                intent.putExtra( "title",map1.get( "title" ));
                intent.putExtra( "synopsis",map1.get("synopsis"));
                startActivity( intent );
            }
        } );

    }

    private ArrayList<Test> parseXml(InputStream input) throws  Exception{
        ArrayList<Test> list = null;
        Test test = null;
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(input, "utf-8");
        int type = parser.getEventType();
        while(type != XmlPullParser.END_DOCUMENT){
            switch (type){
                case XmlPullParser.START_TAG:
                    switch(parser.getName()){
                        case "testList" : list = new ArrayList<>(); break;
                        case "test" : test = new Test(); break;
                        case "id" : test.id = Integer.parseInt(parser.nextText()); break;
                        case "name" : test.name = parser.nextText(); break;
                        case "synopsis" : test.synopsis = parser.nextText();break;
                    }
                    break;
                case XmlPullParser.END_TAG :
                    if(parser.getName().equals("test")){
                        list.add(test);
                    }
                    break;
            }
            type = parser.next();
        }
        return list;
    }



}
