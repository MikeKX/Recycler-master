package Utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 9/21/2016.
 */
public class DataEntry {

    private Context context;

    public static final String DATA_PATH = "data.json";

    public DataEntry(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String readStringFromAsset() {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open(DATA_PATH);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public List<MediaObject> getAllMedias() {
        List<MediaObject> result = new ArrayList<>();

        try {

            JSONArray jsonArray = new JSONObject(readStringFromAsset()).getJSONArray("items");
            int length = jsonArray.length();

            JSONObject jsonObjectTmp;
            MediaObject mediaObjectTmp;
            String titleTmp;
            String imageUrlTmp;

            for (int i = 0; i < length; i++) {

                jsonObjectTmp = jsonArray.getJSONObject(i).getJSONObject("snippet");

                titleTmp = jsonObjectTmp.getString("title");
                imageUrlTmp = jsonObjectTmp.getJSONObject("thumbnails").getJSONObject("medium").getString("url");

                mediaObjectTmp = new MediaObject();

                mediaObjectTmp.setTitle(titleTmp);
                mediaObjectTmp.setImageUrl(imageUrlTmp);

                result.add(mediaObjectTmp);
//                Log.d("MediaObject", "Title : " + mediaObjectTmp.getTitle());
//                Log.d("MediaObject", "ImageUrl : " + mediaObjectTmp.getImageUrl());
            }
            titleTmp = null;
            imageUrlTmp = null;
            jsonObjectTmp = null;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
