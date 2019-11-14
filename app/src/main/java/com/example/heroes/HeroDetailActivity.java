package com.example.heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class
HeroDetailActivity extends AppCompatActivity {
    private TextView textViewName;
    private  TextView textViewRanking;
    private TextView textViewDescription;
    private TextView textViewSuperpower;
    private ImageView imageViewPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);
        Intent intense= getIntent();
        Hero hero = intense.getParcelableExtra(MainActivity.EXTRA_HERO);
        wireWidgets();
        textViewDescription.setText(hero.getDescription());
                textViewName.setText(hero.getName());
                textViewRanking.setText(hero.getRanking()+"");
        textViewSuperpower.setText(hero.getSuperpower());

        int resourceImage = getResources().getIdentifier(hero.getImage(), "drawable", getPackageName());
        imageViewPicture.setImageDrawable(getResources().getDrawable(resourceImage));


    }

    private void wireWidgets() {
         textViewName = findViewById(R.id.textView_HeroDetailActivity_name);
         textViewRanking = findViewById(R.id.textView_HeroDetailActivity_actualRanking);
                 textViewDescription = findViewById(R.id.textView_HeroDetailActivity_actualDescription);
        textViewSuperpower = findViewById(R.id.textView_HeroDetailActivity_actualSuperpower);
        imageViewPicture= findViewById(R.id.imageView_HeroDetailActivity_image);
    }


}
