package br.com.dauster.manga3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.dauster.manga3.database.DataContract.ChapterContract;
import br.com.dauster.manga3.database.DataContract.MangaContract;
import br.com.dauster.manga3.database.DataContract.PageContract;


public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mangas.db";
    private static final int DATABASE_VERSION = 10;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        final String SQL_CREATE_MANGA_TABLE = "CREATE TABLE " +
                MangaContract.ENTITY_NAME + " (" +
                MangaContract.COLUMN_MANGAID + " TEXT NOT NULL, " +
                MangaContract.COLUMN_HREF + " TEXT , " +
                MangaContract.COLUMN_ARTIST + " TEXT , " +
                MangaContract.COLUMN_AUTHOR + " TEXT , " +
                MangaContract.COLUMN_COVER + " TEXT , " +
                MangaContract.COLUMN_GENRES + " TEXT , " +
                MangaContract.COLUMN_INFO + " TEXT , " +
                MangaContract.COLUMN_LASTUPDATE + " TEXT , " +
                MangaContract.COLUMN_NAME + " TEXT NOT NULL, " +
                MangaContract.COLUMN_STATUS + " TEXT , " +
                MangaContract.COLUMN_YEAROFRELEASE + " INTEGER, "+
                " PRIMARY KEY (" + MangaContract.COLUMN_MANGAID + ") ON CONFLICT REPLACE);";


        final String SQL_CREATE_CHAPTER_TABLE = "CREATE TABLE " +
                ChapterContract.ENTITY_NAME + " (" +
                ChapterContract.COLUMN_CHAPTERID + " INTEGER NOT NULL , " +
                ChapterContract.COLUMN_MANGAID + " TEXT NOT NULL, " +
                ChapterContract.COLUMN_NAME + " TEXT , " +
                ChapterContract.COLUMN_LASTUPDATE + " TEXT , " +
                " PRIMARY KEY (" + ChapterContract.COLUMN_CHAPTERID + ") ON CONFLICT REPLACE, " +
                "UNIQUE (" +
                ChapterContract.COLUMN_CHAPTERID + ", " +
                ChapterContract.COLUMN_MANGAID +
                ") ON CONFLICT REPLACE );";


        final String SQL_CREATE_PAGE_TABLE = "CREATE TABLE " +
                PageContract.ENTITY_NAME + " (" +
                PageContract.COLUMN_PAGEID + " INTEGER NOT NULL , " +
                PageContract.COLUMN_CHAPTERID + " INTEGER NOT NULL, " +
                PageContract.COLUMN_URL + " TEXT NOT NULL ,"+
                " PRIMARY KEY (" + PageContract.COLUMN_PAGEID + ") ON CONFLICT REPLACE, " +
                "UNIQUE (" +
                PageContract.COLUMN_PAGEID + ", " +
                PageContract.COLUMN_CHAPTERID +
                ") ON CONFLICT REPLACE );";


        db.execSQL(SQL_CREATE_MANGA_TABLE);
        db.execSQL(SQL_CREATE_CHAPTER_TABLE);
        db.execSQL(SQL_CREATE_PAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MangaContract.ENTITY_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ChapterContract.ENTITY_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PageContract.ENTITY_NAME);
        onCreate(db);
    }

//    @Override
//    public void onConfigure(SQLiteDatabase db) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            db.setForeignKeyConstraintsEnabled(true);
//        }
//    }

}