package co.mobilemakers.packrats;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by gonzalo.lodi on 05/02/2015.
 */
public class Document {

    public final static String ID = "_id";
    public final static String TITLE= "title";
    public final static String CONTENTS = "contents";
    public final static String CREATION = "creation";

    @DatabaseField(generatedId = true, columnName = ID) private int _id;
    @DatabaseField(columnName = TITLE) private String mTitle;
    @DatabaseField(columnName = CONTENTS) private String mContents;
    @DatabaseField(columnName = CREATION) private Date mCreation = new Date();

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getContents() {
        return mContents;
    }

    public void setContents(String mContents) {
        this.mContents = mContents;
    }

    public Date getCreation() {
        return mCreation;
    }

    public String toString(){
        return mTitle+" : " + mContents;
    }

}
