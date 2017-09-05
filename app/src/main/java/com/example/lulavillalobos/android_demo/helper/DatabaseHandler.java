package com.example.lulavillalobos.android_demo.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lulavillalobos.android_demo.model.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulavillalobos on 9/4/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "StoriesManager";
    private static final String TABLE_STORY = "Story";

    // Table column names
    private static final String KEY_STORY_ID = "story_id";
    private static final String KEY_STORY_TITLE = "story_title";
    private static final String KEY_STORY_CREATED_AT = "story_created_at";
    private static final String KEY_STORY_URL = "story_url";
    private static final String KEY_STORY_AUTHOR = "story_author";
    private static final String KEY_STORY_DELETED = "story_deleted";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STORY_TABLE = "CREATE TABLE " + TABLE_STORY + "("
                + KEY_STORY_ID + " INTEGER PRIMARY KEY, " + KEY_STORY_TITLE + " TEXT, "
                + KEY_STORY_URL + " TEXT, " + KEY_STORY_AUTHOR + " TEXT, "
                + KEY_STORY_CREATED_AT + " TEXT, " + KEY_STORY_DELETED + " INTEGER DEFAULT 0 )";
        db.execSQL(CREATE_STORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORY);
        onCreate(db);
    }


    //CRUD Operations

    //add Story
    public void addStory(Story story) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Check which title is null and set both the title
        if(story.getStoryTitle() != null)
            story.setTitle(story.getStoryTitle());
        else
            story.setStoryTitle(story.getTitle());

        if(story.getStoryUrl() != null)
            story.setUrl(story.getStoryUrl());
        else
            story.setStoryUrl(story.getUrl());

        ContentValues values = new ContentValues();
        values.put(KEY_STORY_ID, story.getId());
        values.put(KEY_STORY_TITLE, story.getTitle());
        values.put(KEY_STORY_URL, story.getUrl());
        values.put(KEY_STORY_AUTHOR, story.getAuthor());
        values.put(KEY_STORY_CREATED_AT, story.getCreatedAt());
        values.put(KEY_STORY_DELETED, story.getDeleted());

        //insert row
        db.insert(TABLE_STORY, null, values);
        db.close();
    }

    //add several Stories
    public void addStories(List<Story> stories) {
        for(Story story : stories) {
            addStory(story);
        }
    }

    //get Story
    public Story getStory(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_STORY, new String[] { KEY_STORY_ID, KEY_STORY_TITLE,
                KEY_STORY_URL, KEY_STORY_AUTHOR, KEY_STORY_CREATED_AT, KEY_STORY_DELETED }, KEY_STORY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        Story story = new Story(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5));

        cursor.close();
        return story;
    }

    //get all Stories
    public List<Story> getAllStories() {
        List<Story> stories = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_STORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //add rows to list
        if(cursor.moveToFirst()) {
            do {
                Story story = new Story();
                story.setId(cursor.getInt(0));
                story.setTitle(cursor.getString(1));
                story.setStoryTitle(cursor.getString(1));
                story.setUrl(cursor.getString(2));
                story.setAuthor(cursor.getString(3));
                story.setCreatedAt(cursor.getString(4));
                story.setDeleted(cursor.getInt(5));
                //add to list
                stories.add(story);
            } while(cursor.moveToNext());
        }
        cursor.close();

        return stories;
    }

    //get stories count
    public int getStoriesCount() {
        String countQuery = "SELECT * FROM " + TABLE_STORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    //update single story
    public int updateStory(Story story) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STORY_TITLE, story.getTitle());
        values.put(KEY_STORY_URL, story.getUrl());
        values.put(KEY_STORY_AUTHOR, story.getAuthor());
        values.put(KEY_STORY_CREATED_AT, story.getCreatedAt());
        values.put(KEY_STORY_DELETED, story.getDeleted());

        int update = db.update(TABLE_STORY, values, KEY_STORY_ID + "=?",
                new String[] { String.valueOf(story.getId()) });

        db.close();
        return update;
    }

    //delete single story
    public int deleteStory(Story story) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STORY_DELETED, 1);

        int delete = db.update(TABLE_STORY, values, KEY_STORY_ID + "=?",
                new String[] { String.valueOf(story.getId()) });
        db.close();
        return delete;
    }

    //get undeleted stories
    public List<Story> getUndeletedStories() {
        List<Story> stories = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_STORY
                + " WHERE " + KEY_STORY_DELETED + " = 0";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //add rows to list
        if(cursor.moveToFirst()) {
            do {
                Story story = new Story();
                story.setId(cursor.getInt(0));
                story.setTitle(cursor.getString(1));
                story.setStoryTitle(cursor.getString(1));
                story.setUrl(cursor.getString(2));
                story.setStoryUrl(cursor.getString(2));
                story.setAuthor(cursor.getString(3));
                story.setCreatedAt(cursor.getString(4));
                story.setDeleted(cursor.getInt(5));
                //add to list
                stories.add(story);
            } while(cursor.moveToNext());
        }
        cursor.close();

        return stories;
    }

}
