package android.example.com.providersample;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mTextViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewResult=(TextView)findViewById(R.id.tv_result);
        String uri="content://example.com.retrofittest/data";
        ContentResolver contentResolver=getContentResolver();
        Cursor cursor=contentResolver.query(Uri.parse(uri),null,null,null,null);
        StringBuffer stringBuffer=new StringBuffer();
        while(cursor.moveToNext()) {

            stringBuffer.append(cursor.getString(cursor.getColumnIndex("full_name"))+"\n");
        }
        mTextViewResult.setText(stringBuffer.toString());

    }
}
