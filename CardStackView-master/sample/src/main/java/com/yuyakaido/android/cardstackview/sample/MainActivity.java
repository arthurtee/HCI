package com.yuyakaido.android.cardstackview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.RewindAnimationSetting;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CardStackListener {

    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private CardStackView cardStackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupCardStackView();
        setupButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                refresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {
        Log.d("CardStackView", "onCardDragging: d = " + direction.name() + ", r = " + ratio);
    }

    @Override
    public void onCardSwiped(Direction direction) {
        Log.d("CardStackView", "onCardSwiped: p = " + manager.getTopPosition() + ", d = " + direction);
        if (manager.getTopPosition() == adapter.getItemCount() - 5) {
            adapter.addSpots(createSpots());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCardRewound() {
        Log.d("CardStackView", "onCardRewound: " + manager.getTopPosition());
    }

    @Override
    public void onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled:" + manager.getTopPosition());
    }

    private void setupCardStackView() {
        refresh();
    }

    private void setupButton() {
        View skip = findViewById(R.id.skip_button);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Left)
                        .setDuration(200)
                        .setInterpolator(new AccelerateInterpolator())
                        .build();
                manager.setSwipeAnimationSetting(setting);
                cardStackView.swipe();
            }
        });

        View rewind = findViewById(R.id.rewind_button);
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RewindAnimationSetting setting = new RewindAnimationSetting.Builder()
                        .setDirection(Direction.Bottom)
                        .setDuration(200)
                        .setInterpolator(new DecelerateInterpolator())
                        .build();
                manager.setRewindAnimationSetting(setting);
                cardStackView.rewind();
            }
        });

        View like = findViewById(R.id.like_button);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Right)
                        .setDuration(200)
                        .setInterpolator(new AccelerateInterpolator())
                        .build();
                manager.setSwipeAnimationSetting(setting);
                cardStackView.swipe();
            }
        });
    }

    private void refresh() {
        manager = new CardStackLayoutManager(getApplicationContext(), this);
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(true);
        adapter = new CardStackAdapter(this, createSpots());
        cardStackView = findViewById(R.id.card_stack_view);
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
    }

    private List<Spot> createSpots() {
        List<Spot> spots = new ArrayList<>();
        spots.add(new Spot("Chicken Noodle Soup", "Lee Hsien Loong", "https://source.unsplash.com/L1ZhjK-R6uc/600x800"));
        spots.add(new Spot("Curry Meat Vegetable", "Caroline", "https://source.unsplash.com/yzFO7e_87fs/600x800"));
        spots.add(new Spot("Rice Meat Vegetable", "Mory", "https://source.unsplash.com/O4CVzHODjjM/600x800"));
        spots.add(new Spot("Salmon Sushi", "HAKATO", "https://source.unsplash.com/M1M9PVArnlE/600x800"));
        spots.add(new Spot("Lemon Cream Salmon", "Jennifer", "https://source.unsplash.com/8pUjhBm4cLw/600x800"));
        spots.add(new Spot("Mushroom Burger", "Jeremy", "https://source.unsplash.com/xrFOiqwpJdU/600x800"));
        spots.add(new Spot("Meat Burger Fries", "Ashley", "https://source.unsplash.com/UbTUTDRqj-o/600x800"));
        spots.add(new Spot("Spicy Chicken Ramen", "Edward", "https://source.unsplash.com/Xn0cdekXuSk/600x800"));
        spots.add(new Spot("Melon", "AI FENG", "https://source.unsplash.com/bw0dAspQHYk/600x800"));
        spots.add(new Spot("Fish Egg Rice", "Lisa", "https://source.unsplash.com/q4pns2LtGwk/600x800"));
        return spots;
    }

}
