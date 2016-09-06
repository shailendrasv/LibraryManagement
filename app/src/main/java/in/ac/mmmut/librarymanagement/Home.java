package in.ac.mmmut.librarymanagement;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    private static final String[] IMAGES = new String[]{
            "http://ecx.images-amazon.com/images/I/51fQw8OX4-L._SX303_BO1,204,203,200_.jpg",
            "http://4.bp.blogspot.com/-LwDlh5jrDac/UcwJz06FdUI/AAAAAAAAAFM/GOKDuI_iD_E/s1600/operating-system-concepts-by-galvin.jpg",
            "http://highered.mcgraw-hill.com/sites/dl/free/0070131511/cover/cormen-lg_cover.jpg",
            "http://www.gatecounsellor.com/books/images/programming-with-c-3-edition-9780070145900.jpg",
            "http://images.contentreserve.com/ImageType-100/0018-1/%7B9E60640B-CB0B-40A5-8AC0-77089E20028C%7DImg100.jpg",
            "http://img.docstoccdn.com/thumb/orig/121637281.png"

    };
    private ViewPager pager;
    private ListView GetAllBooksListView;
    private JSONArray jsonArray;
    String username;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        // Inflate the layout for this fragment
        pager = (ViewPager)view.findViewById(R.id.pager);

        ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
        username=getActivity().getIntent().getStringExtra("username");

        if (username!=null) {
            pagerAdapter.addAll(Arrays.asList(IMAGES));
            pager.setAdapter(pagerAdapter);
            CirclePageIndicator indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
            indicator.setViewPager(pager);
            this.GetAllBooksListView = (ListView) view.findViewById(R.id.GetAllBooksListview);
            new GetAllBooksTask().execute(new ApiConnector());
            this.GetAllBooksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        JSONObject bookClicked = jsonArray.getJSONObject(position);

                        Intent showDetails = new Intent(getActivity(), BooksDetailsActivity.class);
                        showDetails.putExtra("BookID", bookClicked.getInt("id"));
                        startActivity(showDetails);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        else {
            getActivity().getFragmentManager().popBackStackImmediate();
            getActivity().finish();
            Toast.makeText(getActivity().getApplicationContext(), "Incorrect Username or password !!!\n or Check your Internet connection", Toast.LENGTH_LONG).show();
        }
        return view;
    }
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            super.getActivity().onBackPressed();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }
    public void setListAdapter(JSONArray jsonArray)
    {
        this.jsonArray = jsonArray;
        this.GetAllBooksListView.setAdapter(new GetAllBooksListViewAdapter(jsonArray,getActivity()));
    }

    private class GetAllBooksTask extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {

            return params[0].GetAllBooks();}

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            setListAdapter(jsonArray);

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}