package com.astend.android.a0_100acceleration.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import com.astend.android.a0_100acceleration.R;

import java.util.Formatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class SpeedManager extends AsyncTask implements IBaseGpsListener {
  LocationMng locationMng = null;




  public interface SpeedManagerListener{
    void onProgressSpeed(float speed);
    void updatePassedTime(long timePoint);

  }
  private static SpeedManager instance = null;
  private float speed;
  private long startTimerTime = 0L;
  private long timePoint = 0L;
  final int maxSpeed = 40;


  @SuppressLint("MissingPermission")
  public SpeedManager(Context context){
    LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, this);
    this.updateSpeed(null);

  }



  //todo разобраться
  private Set<SpeedManagerListener> speedManagerListeners = new HashSet<>();


  public static SpeedManager getInstance(Context context){
    if (instance ==null){
      instance = new SpeedManager(context);
      instance.execute();
    }
    return instance;
  }

  public void addListener(SpeedManagerListener speedManagerListener){
    speedManagerListeners.add(speedManagerListener);
  }

  @Override
  protected Object doInBackground(Object[] objects) {
    float newSpeed = locationMng.getSpeed();
    speed = newSpeed;
    timePoint = 0;
    while (newSpeed < 40){
      newSpeed = locationMng.getSpeed();
      if (speed != newSpeed){
        speed = newSpeed;
        long passedTime = System.currentTimeMillis() - startTimerTime;
        timePoint = passedTime;


      }
      publishProgress();




    try {
      Thread.sleep(40);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    }


    return null;


  }
  @Override
  protected void onProgressUpdate(Object[] values) {
    super.onProgressUpdate(values);
    for (SpeedManagerListener listener : speedManagerListeners) {
      listener.onProgressSpeed(speed);
      listener.updatePassedTime(timePoint);
    }


  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    startTimerTime = System.currentTimeMillis();



  }




  @Override
  protected void onCancelled() {
    super.onCancelled();

  }

  private void updateSpeed(LocationMng locationMng){
    float currentSpeed = 0;
    if (locationMng != null){
      currentSpeed = locationMng.getSpeed();
    }
  }


  @Override
  public void onLocationChanged(Location location) {
    if (location != null) {
      locationMng = new LocationMng(location, false);

      this.updateSpeed(locationMng);
    }

  }

  @Override
  public void onProviderDisabled(String provider) {

  }

  @Override
  public void onProviderEnabled(String provider) {

  }

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {

  }

  @Override
  public void onGpsStatusChanged(int event) {

  }






// private boolean useMetricUnits() {
//   // TODO Auto-generated method stub
//   CheckBox chkUseMetricUnits = (CheckBox) this.findViewById(R.id.chkMetricUnits);
//   return chkUseMetricUnits.isChecked();
// }

//
//
//    Formatter fmt = new Formatter(new StringBuilder());
//    fmt.format(Locale.US, "%5.1f", nCurrentSpeed);
//    String strCurrentSpeed = fmt.toString();
//    strCurrentSpeed = strCurrentSpeed.replace(' ', '0');
//
//    String strUnits = "km/hour";
////    if (this.useMetricUnits()) {
////      strUnits = "meters/second";
////    }
//
//    TextView txtCurrentSpeed = (TextView) this.findViewById(R.id.txtCurrentSpeed);
//    txtCurrentSpeed.setText(strCurrentSpeed + " " + strUnits);
//
//
//    if (nCurrentSpeed > 0 && nCurrentSpeed < maxSpeed) {
//
//      if (getStatus() == AsyncTask.Status.PENDING)
//        execute();
//      reset.setEnabled(false);
//
//    }
//    else if (nCurrentSpeed > maxSpeed) {
//      myTimer.cancel(true);
//      TimeBuff += MillisecondTime;
//
//      reset.setEnabled(true);
//    }

//  }

}
