package com.example.heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private Hero[] heroes;
    private List<Hero> heroList;
    private Gson gson;
    private  ArrayAdapter<Hero> heroesArrayAdapter;
    private ListView heroview;
    public static final String EXTRA_HERO = "hero";
    private MenuItem menuItem;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_hero_menu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.sort_by_name:
              Arrays.sort(heroes);
                heroesArrayAdapter.notifyDataSetChanged();
                return true;
            case R.id.sort_by_rank:{

                Collections.sort(heroList, new Comparator<Hero>() {
                    @Override
                    public int compare(Hero hero, Hero t1) {
                       return hero.getRanking() - t1.getRanking();
                    }
                });
                heroesArrayAdapter.notifyDataSetChanged();
                return true;

        }

            default:
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_heroes_list);
         gson = new Gson();
        InputStream jsonFileInputStream   = getResources().openRawResource(R.raw.heroes);
        String json = readTextFile(jsonFileInputStream);

        heroes =  gson.fromJson(json, Hero[].class);

        heroList = Arrays.asList(heroes);

        heroesArrayAdapter = new HeroAdapter(heroList);
        wirewidgets();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        heroview.setAdapter(heroesArrayAdapter);
        heroview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Hero hero = heroList.get(i);
                Intent targetIntent =  new Intent(MainActivity.this, HeroDetailActivity.class);
                targetIntent.putExtra(EXTRA_HERO,hero);
                startActivity(targetIntent);


            }
        });
    }

    private class HeroAdapter extends ArrayAdapter<Hero>{
        private TextView textViewName;
       private  TextView textViewRanking;
        private TextView textViewDescription;

        private List<Hero> heroList;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_hero,parent,false);
            }
            textViewName = convertView.findViewById(R.id.textView_itemhero_name);
             textViewRanking = convertView.findViewById(R.id.textView_itemhero_ranking);
            textViewDescription = convertView.findViewById(R.id.textView_itemhero_description);

            textViewName.setText(heroList.get(position).getName());
            textViewRanking.setText(heroList.get(position).getRanking() + "");
            textViewDescription.setText(heroList.get(position).getDescription());
            return convertView;


            }


        public HeroAdapter(List<Hero> heroList) {
            super(MainActivity.this, -1, heroList);
            this.heroList=heroList;



         }
    }

    private void wirewidgets() {
      heroview= findViewById(R.id.ListView_herolist_list);
    }


    public String readTextFile (InputStream jsonFileInputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = jsonFileInputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            jsonFileInputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }}

