package in.ac.mmmut.librarymanagement;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;


public class BooksDetailsActivity extends AppCompatActivity {
    private EditText BookName;
    private EditText AuthorName;
    private EditText Availability;

    private int BookID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        this.BookName = (EditText) findViewById(R.id.book_name);
        this.AuthorName = (EditText) findViewById(R.id.author_name);
        this.Availability = (EditText) findViewById(R.id.availability);

        this.BookID = getIntent().getIntExtra("BookID", -1);
        if (this.BookID>0)
        {
            new GetBookDetails().execute(new ApiConnector());
        }
    }
    private class GetBookDetails extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {

            return params[0].GetBooksDetails(BookID);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            try {
                JSONObject book = jsonArray.getJSONObject(0);
                BookName.setText(book.getString("name"));
                AuthorName.setText(book.getString("author"));
                Availability.setText(book.getString("availability"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}
