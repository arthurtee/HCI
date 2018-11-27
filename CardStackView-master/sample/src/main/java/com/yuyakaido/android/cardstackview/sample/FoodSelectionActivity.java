package com.yuyakaido.android.cardstackview.sample;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static com.yuyakaido.android.cardstackview.sample.MainActivity.REQUEST_IMAGE_CAPTURE;


public class FoodSelectionActivity extends AppCompatActivity {

    String[] foodNames = {"Salad Peanut", "Chicken Chop", "Rice Fish Egg", "Salad Broccoli", "Banana"};
    int[] foodImages = {R.drawable.happy_24dp,R.drawable.sad_24dp,R.drawable.sad_24dp,R.drawable.happy_24dp,R.drawable.happy_24dp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_selection);


        //finding listview
        ListView listView = (ListView) findViewById(R.id.listview);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Toast.makeText(getApplicationContext(),foodNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
                intent.putExtra("name", foodNames[i]);
                intent.putExtra("image",foodImages[i]);
                startActivity(intent);

            }
        });




    }

    private class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return foodImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.food_data,null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.food);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(foodNames[i]);
            image.setImageResource(foodImages[i]);
            return view1;



        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item_search = menu.findItem(R.id.action_history);
        item_search.setVisible(false);

        //MenuItem item_refresh = menu.findItem(R.id.refresh);
        //item_refresh.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.upload:
                dispatchTakePictureIntent();
                break;
            case R.id.home:
                Intent backHome = new Intent(this,MainActivity.class);
                this.startActivity(backHome);
                break;
            case R.id.action_chart:
                Intent toChart = new Intent(this,ChartActivity.class);
                this.startActivity(toChart);
                break;
            case R.id.action_trends:
                Intent toTrends = new Intent(this,TrendsActivity.class);
                this.startActivity(toTrends);
                break;
            case R.id.action_about_us:
                Intent toAboutUs = new Intent(this,AboutUsActivity.class);
                this.startActivity(toAboutUs);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

}
