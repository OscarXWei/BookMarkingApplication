package com.example.bookmarkingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> urlList;
    private ArrayAdapter<String> adapter;
    private EditText urlEditText;
    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlList = new ArrayList<>();
        urlList.add("https://www.anu.edu.au/");
        urlList.add("https://www.google.com/");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, urlList);
        ListView listView = findViewById(R.id.url_list);
        listView.setAdapter(adapter);

        urlEditText = findViewById(R.id.editTextText);
        Button addButton = findViewById(R.id.button);

        User user = (User) getIntent().getExtras().getSerializable("USER");
        ArrayList<String> userUrls = user.getUserUrls(this, user.getId());
        for (String url: userUrls) {
            urlList.add(url);
        }



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newUrl = urlEditText.getText().toString().trim();
//                if (!newUrl.isEmpty()) {
//                    urlList.add(newUrl);
//                    adapter.notifyDataSetChanged();
//                    urlEditText.setText("");
//                }
                urlList.add(newUrl);
                adapter.notifyDataSetChanged();
                urlEditText.setText("");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedUrl = urlList.get(position);
                Intent intent = new Intent(getApplicationContext(), ActivityWeb.class);
                intent.putExtra("URL_KEY", clickedUrl.toString());
                startActivity(intent);
            }
        });



    }
}