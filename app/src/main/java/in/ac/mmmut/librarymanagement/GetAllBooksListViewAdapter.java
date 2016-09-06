package in.ac.mmmut.librarymanagement;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class GetAllBooksListViewAdapter extends BaseAdapter {
    private JSONArray dataArray;
    private Activity activity;
    private static final String baseUrlForImage = "http://php-dr22libraryapp.rhcloud.com/Images/";

    private static LayoutInflater inflater = null;

    public GetAllBooksListViewAdapter(JSONArray jsonArray, Activity a){
        this.dataArray = jsonArray;
        this.activity = a;

        inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return this.dataArray.length();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ListCell cell;
        if (convertView==null)
        {
            convertView = inflater.inflate(R.layout.get_all_books_list_view_cell,null);
            cell = new ListCell();
            cell.BookName = (TextView) convertView.findViewById(R.id.book_full_name);
            cell.AuthorName = (TextView) convertView.findViewById(R.id.author_full_name);
            cell.book_image = (ImageView) convertView.findViewById(R.id.book_image);
            convertView.setTag(cell);
        }
        else {
            cell = (ListCell) convertView.getTag();

        }

        try {
            JSONObject jsonObject = this.dataArray.getJSONObject(position);
            cell.BookName.setText(jsonObject.getString("name"));
            cell.AuthorName.setText(jsonObject.getString("author"));

            String nameOfImage= jsonObject.getString("imageName");

            String urlForImageInServer= baseUrlForImage + nameOfImage;
            new AsyncTask<String,Void,Bitmap>()
            {

                @Override
                protected Bitmap doInBackground(String... params) {
                    String url = params[0];
                    Bitmap icon = null;

                    try {
                        InputStream in = new java.net.URL(url).openStream();
                        icon = BitmapFactory.decodeStream(in);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    return icon;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    cell.book_image.setImageBitmap(bitmap);
                }
            }.execute(urlForImageInServer);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return convertView;
    }

    private class ListCell{
        private TextView BookName;
        private TextView AuthorName;
        private ImageView book_image;
    }
}
