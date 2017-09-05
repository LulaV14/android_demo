package com.example.lulavillalobos.android_demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lulavillalobos.android_demo.R;
import com.example.lulavillalobos.android_demo.activity.WebStoryActivity;
import com.example.lulavillalobos.android_demo.model.Story;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by lulavillalobos on 9/4/17.
 */

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {

    private Context context;
    private List<Story> stories;

    private static final String EXTRA_STORY_URL = "EXTRA_STORY_URL";

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle, tvAuthor, tvCreated_at;
        LinearLayout llStoryContainer;

        public ViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.title);
            tvAuthor = (TextView) view.findViewById(R.id.author);
            tvCreated_at = (TextView) view.findViewById(R.id.created_at);
            llStoryContainer = (LinearLayout) view.findViewById(R.id.main_story_container);
            llStoryContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Story story = stories.get(getAdapterPosition());

            Intent intent = new Intent(context, WebStoryActivity.class);
            intent.putExtra(EXTRA_STORY_URL, story.getUrl());
            context.startActivity(intent);
        }
    }


    public StoriesAdapter(Context context, List<Story> stories) {
        this.context = context;
        this.stories = stories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.story_list_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Story story = stories.get(position);

        if(story.getStoryTitle() != null)
            holder.tvTitle.setText(story.getStoryTitle());
        else
            holder.tvTitle.setText(story.getTitle());
        holder.tvAuthor.setText(story.getAuthor());
        holder.tvCreated_at.setText(getTimeAgo(story.getCreatedAt()));
    }

    @Override
    public long getItemId(int position) {
        return stories.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }


    public String getTimeAgo(String timeToParse) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        long time = 0;
        try {
            time = sdf.parse(timeToParse).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long now = System.currentTimeMillis();

        CharSequence ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
        return " - " + ago;
    }
}
