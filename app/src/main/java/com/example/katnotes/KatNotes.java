package com.example.katnotes;

import android.os.Parcel;
import android.os.Parcelable;

public class KatNotes implements Parcelable {

    int notes_id;
    String notes_content;

    public KatNotes() {

    }

    public int getNotesId(){
        return notes_id;
    }

    public void setNotesId(int notes_id){
        this.notes_id = notes_id;
    }

    public String getNotesContent(){
        return  notes_content;
    }

    public void setNotesContent(String notes_content){
        this.notes_content = notes_content;
    }


    protected KatNotes(Parcel in) {
        this.notes_id = in.readInt();
        this.notes_content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(notes_id);
        dest.writeString(notes_content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<KatNotes> CREATOR = new Parcelable.Creator<KatNotes>() {
        @Override
        public KatNotes createFromParcel(Parcel in) {
            return new KatNotes(in);
        }

        @Override
        public KatNotes[] newArray(int size) {
            return new KatNotes[size];
        }
    };
}
