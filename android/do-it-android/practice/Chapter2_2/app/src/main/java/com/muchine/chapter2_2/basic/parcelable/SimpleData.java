package com.muchine.chapter2_2.basic.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sejoonlim on 8/6/16.
 */
public class SimpleData implements Parcelable {

    private int number;
    private String message;

    public SimpleData(int number, String message) {
        this.number = number;
        this.message = message;
    }

    public SimpleData(Parcel src) {
        number = src.readInt();
        message = src.readString();
    }

    public int getNumber() {
        return number;
    }

    public String getMessage() {
        return message;
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new SimpleData(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new SimpleData[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number);
        parcel.writeString(message);
    }
}
