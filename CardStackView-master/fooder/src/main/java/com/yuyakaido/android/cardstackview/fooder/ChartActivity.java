package com.yuyakaido.android.cardstackview.fooder;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;


import java.util.ArrayList;
import java.util.List;

import static com.yuyakaido.android.cardstackview.fooder.MainActivity.REQUEST_IMAGE_CAPTURE;


public class ChartActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progressBar));

        Pie pie = AnyChart.pie();

        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
                Toast.makeText(ChartActivity.this, event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Minerals", 637));
        data.add(new ValueDataEntry("Protein", 789));
        data.add(new ValueDataEntry("Water", 721));
        data.add(new ValueDataEntry("Fat", 148));
        data.add(new ValueDataEntry("Carbohydrates", 120));

        pie.data(data);

        pie.title("Intake Prediction (Today)");

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Nutrition")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        anyChartView.setChart(pie);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item_search = menu.findItem(R.id.action_chart);
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
            case R.id.action_history:
                Intent backUploads = new Intent(this,FoodSelectionActivity.class);
                this.startActivity(backUploads);
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
