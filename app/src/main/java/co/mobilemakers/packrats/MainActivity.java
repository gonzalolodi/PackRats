package co.mobilemakers.packrats;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {


        private final static String LOG_TAG = "document";

        EditText mEditTextTitle;
        EditText mEditTextContents;
        DatabaseHelper mDBHelper = null;

        public DatabaseHelper getDBHelper() {
            if (mDBHelper == null){
                mDBHelper = OpenHelperManager.getHelper(getActivity(),DatabaseHelper.class);
            }
            return mDBHelper;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            wireUpViews(rootView);
            Button mButtonSave = (Button) rootView.findViewById(R.id.button_save);
            mButtonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Document document = createDocumentFromData();
                    saveDocument(document);

                }
            });
            return rootView;
        }

        private void saveDocument(Document document) {
            try {
                Dao<Document, Integer> dao = getDBHelper().getDocumentDao();
            } catch (SQLException e) {
                Log.e(LOG_TAG, "Failure to compile", e);
            }

        }

        private Document createDocumentFromData() {
            Document document= new Document();
            document.setTitle(mEditTextTitle.getText().toString());
            document.setContents(mEditTextContents.getText().toString());
            return document;
        }

        private void wireUpViews(View rootView) {
            mEditTextTitle = (EditText) rootView.findViewById(R.id.edit_text_title);
            mEditTextContents = (EditText) rootView.findViewById(R.id.edit_text_contents);
        }
    }
}
