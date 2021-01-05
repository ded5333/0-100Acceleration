package com.astend.android.a0_100acceleration.model;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

public class SpeedManager extends AsyncTask implements IBaseGpsListener {
  LocationManager locationManager;



  public interface SpeedManagerListener{
    void onProgressSpeed(float speed);
    void updatePassedTime(long timePoint);

  }
  private static SpeedManager instance = null;
  private float speed = 0f;
  private long startTimerTime = 0L;
  private long timePoint = 0L;



  //todo разобраться
  private Set<SpeedManagerListener> speedManagerListeners = new HashSet<>();

  private SpeedManager(){

  }

  public static SpeedManager getInstance(){
    if (instance ==null){
      instance = new SpeedManager();
      instance.execute();
    }
    return instance;
  }

  public void addListener(SpeedManagerListener speedManagerListener){
    speedManagerListeners.add(speedManagerListener);
  }
  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    startTimerTime = System.currentTimeMillis();



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
  protected Object doInBackground(Object[] objects) {
    float newSpeed = locationManager.getSpeed();
    speed = newSpeed;
    timePoint = 0;
    while (newSpeed < 40){
      newSpeed = locationManager.getSpeed();
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
  protected void onCancelled() {
    super.onCancelled();

  }







 // LocationManager locationManagerr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);






  @Override
  public void onLocationChanged(Location location) {

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
}
