package com.example.lulavillalobos.android_demo.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.lulavillalobos.android_demo.R;
import com.example.lulavillalobos.android_demo.adapter.StoriesAdapter;
import com.example.lulavillalobos.android_demo.model.Story;
import com.example.lulavillalobos.android_demo.model.StoryList;
import com.example.lulavillalobos.android_demo.network.ApiClient;
import com.example.lulavillalobos.android_demo.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private List<Story> stories = new ArrayList<>();
    private RecyclerView recyclerView;
    private StoriesAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ColorDrawable background = new ColorDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        adapter = new StoriesAdapter(this, stories);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getStories();
            }
        });


        //TODO testing code
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                deleteStory(viewHolder.getAdapterPosition());
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;

                    //set background
                    background.setColor(Color.RED);
                    background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(),
                            itemView.getRight(), itemView.getBottom());
                    background.draw(c);

                    //setting delete text
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    paint.setTextSize(13);
                    paint.setTextAlign(Paint.Align.CENTER);
                    c.drawText("Delete", itemView.getRight() - 40, itemView.getTop() + (itemView.getHeight() / 2) + 5, paint);

                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }
        };
        return simpleItemTouchCallback;
    }

    private void deleteStory(final int position) {
        stories.remove(position);
        adapter.notifyItemRemoved(position);
    }


    /**
     * HTTP request using Retrofit
     */
    private void getStories() {
        swipeRefreshLayout.setRefreshing(true);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<StoryList> call = apiService.getStories();
        call.enqueue(new Callback<StoryList>() {
            @Override
            public void onResponse(Call<StoryList> call, Response<StoryList> response) {
                //clear the stories
                stories.clear();

                //add all stories
                List<Story> list = response.body().getStories();
                stories.addAll(list);

                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<StoryList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Unable to fetch json: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onRefresh() {
        getStories();
    }
}
