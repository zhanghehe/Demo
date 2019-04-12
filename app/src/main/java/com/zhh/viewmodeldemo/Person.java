package com.zhh.viewmodeldemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhanghehe on 2019/3/29.
 * desc:
 */
public class Person implements Parcelable {
    public String mName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
    }

    public Person() {
    }

    public Person(String name) {
        mName = name;
    }

    protected Person(Parcel in) {
        this.mName = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public void readFromParcel(Parcel in) {
        mName = in.readString();
    }
}