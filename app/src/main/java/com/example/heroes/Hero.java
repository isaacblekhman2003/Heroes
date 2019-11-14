package com.example.heroes;

import android.os.Parcel;
import android.os.Parcelable;

public class Hero implements Parcelable, Comparable<Hero>{
    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int ranking;
    private String description;

    private String superpower;
    private String image;
    private String name;

    public Hero(){}

    @Override
    public int compareTo(Hero hero)
    {
        return this.getName().compareTo(hero.getName());
        // this.getRanking()   hero.getRanking()   //  1   v   5    want (-)  4 v 2   want +
    }

    @Override
    public String toString(){
        return ranking + " " + name +  " " + superpower;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ranking);
        dest.writeString(this.description);
        dest.writeString(this.superpower);
        dest.writeString(this.image);
        dest.writeString(this.name);
    }

    protected Hero(Parcel in) {
        this.ranking = in.readInt();
        this.description = in.readString();
        this.superpower = in.readString();
        this.image = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Hero> CREATOR = new Parcelable.Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel source) {
            return new Hero(source);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

}
