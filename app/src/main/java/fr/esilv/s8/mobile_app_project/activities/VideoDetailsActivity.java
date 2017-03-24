package fr.esilv.s8.mobile_app_project.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import fr.esilv.s8.mobile_app_project.Constants;
import fr.esilv.s8.mobile_app_project.R;
import fr.esilv.s8.mobile_app_project.models.ReturnObject;
import fr.esilv.s8.mobile_app_project.models.ReturnStatsObject;
import fr.esilv.s8.mobile_app_project.models.Video;
import fr.esilv.s8.mobile_app_project.models.VideoDetails;
import fr.esilv.s8.mobile_app_project.models.VideoID;
import fr.esilv.s8.mobile_app_project.models.Videos;

public class VideoDetailsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final String VIDEO_DETAILS_ULR = "https://www.googleapis.com/youtube/v3/videos?part=statistics&id=";
    private static final String URL = "https://www.youtube.com/watch?v=";
    private static final String VIDEO = "VIDEO";
    private Video video;
    private String videoId;
    private TextView title;
    private TextView author;
    private TextView description;
    private TextView likesView;
    private TextView disLikesView;
    private TextView nbViewsView;
    private TextView nbCommentsView;
    private String likes;
    private String disLikes;
    private String nbViews;
    private String nbComments;
    private YouTubePlayerView youTubePlayerView;

    public static void start(Context context, Video video) {
        Intent intent = new Intent(context, VideoDetailsActivity.class);
        intent.putExtra(VIDEO, video);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_details);
        video = (Video) getIntent().getSerializableExtra(VIDEO);
        videoId = video.getId().getVideoId();
        final String URL_VIDEO = URL + videoId;

        getStats();

        title = (TextView) findViewById(R.id.video_title);
        title.setText(video.getSnippet().getTitle());

        author = (TextView) findViewById(R.id.video_author);
        author.setText(video.getSnippet().getChannelTitle());

        description = (TextView) findViewById(R.id.video_description);
        description.setText(video.getSnippet().getDescription());

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(URL_VIDEO, this);
    }

    private void getStats() {
        StringRequest itemsRequest = new StringRequest(VIDEO_DETAILS_ULR + videoId + "&key=" + Constants.API_KEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ReturnStatsObject returnStatsObject = new Gson().fromJson(response, ReturnStatsObject.class);
                likes = returnStatsObject.getItems().get(0).getStatistics().getLikeCount();
                disLikes = returnStatsObject.getItems().get(0).getStatistics().getDislikeCount();
                nbViews = returnStatsObject.getItems().get(0).getStatistics().getViewCount();
                nbComments = returnStatsObject.getItems().get(0).getStatistics().getCommentCount();
                likesView = (TextView) findViewById(R.id.video_likes);
                likesView.setText(likes);
                disLikesView = (TextView) findViewById(R.id.video_dislikes);
                disLikesView.setText(disLikes);
                nbViewsView = (TextView) findViewById(R.id.video_views);
                nbViewsView.setText(nbViews);
                nbCommentsView = (TextView) findViewById(R.id.video_comments);
                nbCommentsView.setText(nbComments);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Videos", "Error");
            }
        });

        Volley.newRequestQueue(this).add(itemsRequest);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(videoId);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
