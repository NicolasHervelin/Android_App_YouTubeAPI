package fr.esilv.s8.mobile_app_project.viewholders;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.esilv.s8.mobile_app_project.R;
import fr.esilv.s8.mobile_app_project.interfaces.OnVideoSelectedListener;
import fr.esilv.s8.mobile_app_project.models.Video;

/**
 * Created by Nicolas on 17/03/2017.
 */

public class VideosViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView description;
    private TextView publicationDate;
    private TextView author;
    private ImageView thumbnail;

    private OnVideoSelectedListener onVideoSelectedListener;

    public VideosViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.video_title);
        description = (TextView) itemView.findViewById(R.id.video_description);
        publicationDate = (TextView) itemView.findViewById(R.id.video_date);
        author = (TextView) itemView.findViewById(R.id.video_author);
        thumbnail = (ImageView) itemView.findViewById(R.id.video_thumbnail);
    }

    public void bind(final Video video) {
        title.setText(video.getSnippet().getTitle());

        String videoDescription = video.getSnippet().getDescription();
        int startIndexDescription = 30;
        int endIndexDescription = videoDescription.length() - 1;
        String toBeReplacedDescr = videoDescription.substring(startIndexDescription,endIndexDescription);
        videoDescription = videoDescription.replace(toBeReplacedDescr, "...");
        description.setText(videoDescription);

        publicationDate.setText(video.getSnippet().getPublishedAt());

        author.setText(video.getSnippet().getChannelTitle());
        //thumbnail.setImageBitmap(getBitmapFromURL(video.getSnippet().getThumbnails().get_default().getUrl()));
        Picasso.with(itemView.getContext()).load(video.getSnippet().getThumbnails().getHigh().getUrl()).into(thumbnail);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onVideoSelectedListener == null) {
                    return;
                }
                onVideoSelectedListener.onVideoSelected(video);
            }
        });
    }

    public void setOnVideoSelectedListener(OnVideoSelectedListener onVideoSelectedListener) {
        this.onVideoSelectedListener = onVideoSelectedListener;
    }

    /*
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/

}
