package fr.esilv.s8.mobile_app_project.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import fr.esilv.s8.mobile_app_project.Constants;
import fr.esilv.s8.mobile_app_project.R;
import fr.esilv.s8.mobile_app_project.adapters.VideosAdapter;
import fr.esilv.s8.mobile_app_project.interfaces.OnVideoSelectedListener;
import fr.esilv.s8.mobile_app_project.models.ReturnObject;
import fr.esilv.s8.mobile_app_project.models.Video;
import fr.esilv.s8.mobile_app_project.models.Videos;

public class VideosActivity extends AppCompatActivity implements OnVideoSelectedListener {
    private static final String VIDEO_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&maxResults=20&q=";
    private static final String KEYWORD = "KEYWORD";
    private String keyword;
    private RecyclerView recyclerView;

    public static void start(Context context, String keyword) {
        Intent intent = new Intent(context, VideosActivity.class);
        intent.putExtra(KEYWORD, keyword);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        keyword = getIntent().getStringExtra(KEYWORD);
        //keyword = keyword.replace(" ", "%20");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getVideos();
    }

    private void getVideos() {
        StringRequest itemsRequest = new StringRequest(VIDEO_URL + keyword + "&key=" + Constants.API_KEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ReturnObject returnObject = new Gson().fromJson(response, ReturnObject.class);
                setAdapter(returnObject.getItems());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Videos", "Error");
            }
        });

        Volley.newRequestQueue(this).add(itemsRequest);
    }

    private void setAdapter(Videos videos) {
        VideosAdapter adapter = new VideosAdapter(videos);
        adapter.setOnVideoSelectedListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onVideoSelected(Video video) {
        VideoDetailsActivity.start(this, video);
    }

}
