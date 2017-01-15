package gsm.ccv.dam.isi.frsf.utn.edu.ar.lab09c2016.gsm;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    float[] linear_acceleration = {0,0,0};

    TextView x;
    TextView y;
    TextView z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x = (TextView) findViewById(R.id.textViewX);
        y = (TextView) findViewById(R.id.textViewY);
        z = (TextView) findViewById(R.id.textViewZ);

        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] gravity = new float[3];
        float alpha= 0.8f;

        gravity[0] = alpha* gravity[0] + (1 -alpha) * event.values[0];
        gravity[1] = alpha* gravity[1] + (1 -alpha) * event.values[1];
        gravity[2] = alpha* gravity[2] + (1 -alpha) * event.values[2];
        if(event.values[0] - gravity[0] > linear_acceleration[0]){
            linear_acceleration[0] = event.values[0] - gravity[0];
            String date = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
            x.setText("El mayor valor en X es: " + String.valueOf(linear_acceleration[0]) + "\nCapturado: " + date);
        }
        if(event.values[1] - gravity[1] > linear_acceleration[1]){
            linear_acceleration[1] = event.values[1] - gravity[1];
            String date = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
            y.setText("El mayor valor en Y es: " + String.valueOf(linear_acceleration[1]) + "\nCapturado: " + date);
        }
        if(event.values[2] - gravity[2] > linear_acceleration[2]){
            linear_acceleration[2] = event.values[2] - gravity[2];
            String date = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
            z.setText("El mayor valor en Z es: " + String.valueOf(linear_acceleration[2]) + "\nCapturado: " + date);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
