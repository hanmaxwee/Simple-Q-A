package com.example.none;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TestAdapter extends BaseAdapter {
    public ArrayList<Test> testList;

    private Context context;

    private LayoutInflater layoutInflater;

    public TestAdapter(Context context, ArrayList<Test> testList) {
        this.testList = testList;
        this.context = context;

        layoutInflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return testList.size();
    }

    @Override
    public Object getItem(int position) {
        return testList.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Test test = testList.get(position);
        holder.bindData(test);
        return convertView;
    }

    class ViewHolder {

        TextView title;

        public ViewHolder(View v) {

            title= v.findViewById(R.id.tv_title);
        }

        public void bindData(Test test) {

            title.setText(test.name);

        }
    }
}
