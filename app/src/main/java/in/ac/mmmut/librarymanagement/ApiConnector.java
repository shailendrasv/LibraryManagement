package in.ac.mmmut.librarymanagement;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class ApiConnector {
    public JSONArray GetAllBooks() {
        String url = "http://libraryphp-shailu.rhcloud.com/get-data.php";
        HttpEntity httpEntity = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
        } catch (IOException e) {
            e.printStackTrace();
        }


        JSONArray jsonArray = null;
        try {
            String entityResponse = EntityUtils.toString(httpEntity);
            Log.e("Entity Response : ", entityResponse);
            JSONObject jsonObject = new JSONObject(entityResponse);
            jsonArray = jsonObject.getJSONArray("result");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("JSONARRAY : ", String.valueOf(jsonArray));

        return jsonArray;
    }
    public JSONArray GetBooksDetails(int BookID)
    {
        String url = "http://libraryphp-shailu.rhcloud.com/getBookDetails.php?id="+BookID;
        HttpEntity httpEntity = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
        }  catch (IOException e) {
            e.printStackTrace();
        }


        JSONArray jsonArray = null;
        try {
            String entityResponse = EntityUtils.toString(httpEntity);
            Log.e("Entity Response : ", entityResponse);
            JSONObject jsonObject = new JSONObject(entityResponse);
            jsonArray = jsonObject.getJSONArray("result");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return jsonArray;
    }
}
