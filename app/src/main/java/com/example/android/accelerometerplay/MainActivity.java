package com.example.android.accelerometerplay;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class MainActivity extends Activity implements SensorEventListener {
    GraphView graph;
    ArrayList data= new ArrayList();


    private SensorManager sensorManager;
    boolean D=false;

    TextView x;
    TextView y;
    TextView z;

    String sx, sy, sz;
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
    GraphView graph1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        x = (TextView) findViewById (R.id.textView2);
        y = (TextView) findViewById (R.id.textView3);
        z = (TextView) findViewById (R.id.textView4);
        graph1 = (GraphView) findViewById(R.id.graph);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorManager.registerListener(this, sensorManager.getDefaultSensor
                (Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }



    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

            float xVal = event.values[0];
            float yVal = event.values[1];
            float zVal = event.values[2];
            if (D==true){
                data.add(zVal);
            }

            sx = "X Value : <font color = '#800080'> " + xVal + "</font>";
            sy = "Y Value : <font color = '#800080'> " + yVal + "</font>";
            sz = "Z Value : <font color = '#800080'> " + zVal + "</font>";

            x.setText(Html.fromHtml(sx));
            y.setText(Html.fromHtml(sy));
            z.setText(Html.fromHtml(sz));
        }
    }
    public void onstart(View v){
        D=true;
        data.clear();

    }
    public void onstop(View v){
        D=false;


    }
    public void onplot(View v){
        int E=data.size();
        for(int i=0;i<E;i++){
            float F=(float)data.get(i);
            series.appendData(new DataPoint(i,F),true,E,false);


        }
        graph1.addSeries(series);

    }
}
