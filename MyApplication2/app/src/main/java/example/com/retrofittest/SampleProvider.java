package example.com.retrofittest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Jusung on 2017. 1. 23..
 */

public class SampleProvider extends ContentProvider {

    static final int ALLWORD = 1;
    static final int ONEWORD = 2;
    static final Uri CONTENT_URI = Uri.parse("content://example.com.retrofittest/data");
    Realm realm;
    static final String[] sColumns = new String[]{"full_name", "name", "id"};
    RealmQuery<Repo> query;
    RealmResults<Repo> results;
    MatrixCursor cursor;


    static UriMatcher Matcher;

    static {
        Matcher = new UriMatcher(UriMatcher.NO_MATCH);
    }


    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        realm = Realm.getDefaultInstance();
        query = realm.where(Repo.class);
        results = query.findAll();
        cursor = new MatrixCursor(sColumns);
        for (Repo repo : results) {
            Object[] rowData = new Object[]{repo.full_name, repo.name, repo.id};
            cursor.addRow(rowData);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        realm.beginTransaction();
        Repo repo=realm.createObject(Repo.class);
        repo.full_name=contentValues.getAsString(sColumns[0]);
        repo.name=contentValues.getAsString(sColumns[1]);
        repo.id=contentValues.getAsString(sColumns[2]);
        realm.commitTransaction();

        return Uri.withAppendedPath(uri,repo.id);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {

        realm.beginTransaction();
        try {
            for(ContentValues value:values){
                Repo repo=realm.createObject(Repo.class);
                repo.full_name=value.getAsString(sColumns[0]);
                repo.name=value.getAsString(sColumns[1]);
                repo.id=value.getAsString(sColumns[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            realm.commitTransaction();
        }
        return values.length;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
