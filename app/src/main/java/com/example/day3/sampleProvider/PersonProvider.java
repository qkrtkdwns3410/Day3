package com.example.day3.sampleProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 *packageName    : com.example.day3.SampleProvider
 * fileName       : PersonProvider
 * author         : ipeac
 * date           : 2022-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-02-26        ipeac       최초 생성
 */
public class PersonProvider extends ContentProvider {
	  private static final String AUTHORITY = "com.example.day3";
	  private static final String BASE_PATH = "person";
	  
	  public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
	  
	  private static final int PERSONS = 1;
	  private static final int PERSON_ID = 2;
	  
	  private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	  
	  static {
			URI_MATCHER.addURI(AUTHORITY, BASE_PATH, PERSONS);
			URI_MATCHER.addURI(AUTHORITY, BASE_PATH + "/#", PERSON_ID);
	  }
	  
	  private SQLiteDatabase database;
	  
	  @Override
	  public boolean onCreate() {
			DatabaseHelper helper = new DatabaseHelper(getContext());
			database = helper.getWritableDatabase();
			
			return true;
	  }
	  
	  @Nullable
	  @Override
	  public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
			Cursor cursor;
			switch (URI_MATCHER.match(uri)) {
				  case PERSONS:
						cursor = database.query(DatabaseHelper.TABLE_NAME, DatabaseHelper.ALL_COLUMNS, s, null, null,
							null, DatabaseHelper.PERSON_NAME + " ASC");
						break;
				  default:
						throw new IllegalArgumentException("알수 없는 URI " + uri);
			}
			cursor.setNotificationUri(getContext( ).getContentResolver( ), uri);
			
			return cursor;
	  }
	  
	  @Nullable
	  @Override
	  public String getType(@NonNull Uri uri) {
			switch (URI_MATCHER.match(uri)) {
				  case PERSONS:
						return "vnd.android.cursor.dir/persons";
				  default:
						throw new IllegalArgumentException("알수 없는 URI " + uri);
			}
			
	  }
	  
	  @Nullable
	  @Override
	  public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
			
			long id = database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
		 
			if (id > 0) {
				  Uri _uri = ContentUris.withAppendedId(CONTENT_URI, id);
				  getContext( ).getContentResolver( ).notifyChange(_uri, null);
				  return _uri;
			}
			
			throw new IllegalArgumentException("추가 실패 > URI: " + uri);
	  }
	  
	  @Override
	  public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
			int count = 0;
			switch (URI_MATCHER.match(uri)) {
				  case PERSONS:
						count = database.delete(DatabaseHelper.TABLE_NAME, s, strings);
						break;
				  default:
						throw new IllegalArgumentException("알 수 없는 URI "+uri);
			}
			getContext( ).getContentResolver( ).notifyChange(uri, null);
			
			return count;
	  }
	  
	  @Override
	  public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s,
		  @Nullable String[] strings) {
			int count = 0;
			switch (URI_MATCHER.match(uri)) {
				  case PERSONS:
						count = database.update(DatabaseHelper.TABLE_NAME, contentValues, s, strings);
						break;
				  default:
						throw new IllegalArgumentException("알 수 없는 URI : " + uri);
			}
			getContext( ).getContentResolver( ).notifyChange(uri, null);
		 
			return count;
	  }
}




























