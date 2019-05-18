package com.bitsandbolts.checkmate;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ListView;

import java.io.InputStream;
import java.util.List;

public class HappeningActivity extends Activity {

    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happenings);

        listView = (ListView) findViewById(R.id.list_view);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.single_list_item);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.data);
        CSVReader csv = new CSVReader(inputStream);
        List<Happening> happenings = csv.read();

        for(Happening happening : happenings) {
            itemArrayAdapter.add(happening);
        }
    }
}
