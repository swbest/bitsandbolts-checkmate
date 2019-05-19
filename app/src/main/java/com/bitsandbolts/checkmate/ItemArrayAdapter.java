package com.bitsandbolts.checkmate;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemArrayAdapter extends ArrayAdapter {

    private List<Happening> happenings = new ArrayList<Happening>();

    public ItemArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class ItemViewHolder {
        TextView date;
        TextView time;
        TextView location;
    }

    public void add(Happening object) {
        happenings.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.happenings.size();
    }

    @Override
    public Happening getItem(int position) {
        return this.happenings.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_list_item, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.date = (TextView) row.findViewById(R.id.date);
            viewHolder.time = (TextView) row.findViewById(R.id.time);
            viewHolder.location = (TextView) row.findViewById(R.id.location);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder) row.getTag();
        }

        Happening stat = getItem(position);
        viewHolder.date.setText(stat.getDate());
        viewHolder.time.setText(stat.getTime());
        viewHolder.location.setText(stat.getLocation());
        return row;
    }
}
