/*package com.example.neon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<ListNode> Node;

    public ListAdapter(Context context,ArrayList<ListNode> node) {
        mContext = context;
        Node = node;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return Node.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ListNode getItem(int position) {
        return Node.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.listview_custom, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.poster);
        TextView movieName = (TextView)view.findViewById(R.id.movieName);
        TextView grade = (TextView)view.findViewById(R.id.grade);

        imageView.setImageResource(Node.get(position).getIcon());
        movieName.setText(Node.get(position).getAquariumName());
        grade.setText(Node.get(position).getAquariumCount());

        return view;
    }
}
*/