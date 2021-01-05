package com.astend.android.a0_100acceleration.model;

import android.location.Criteria;
import android.location.Location;

public class LocationManager extends Location {

  private boolean bUseMetricUnits = false;

  public LocationManager(Location location,boolean bUseMetricUnits) {
    super(location);
    setAccuracy(Criteria.ACCURACY_FINE);
    this.bUseMetricUnits = bUseMetricUnits;

  }
  public boolean getUseMetricUnits()
  {
    return this.bUseMetricUnits;
  }

  public void setUseMetricunits(boolean bUseMetricUntis)
  {
    this.bUseMetricUnits = bUseMetricUntis;
  }

  //todo добавить getLocation;

  @Override
  public float getAccuracy() {
    float nAccuracy = super.getAccuracy();
    if(!this.getUseMetricUnits())
    {
      //Convert meters to feet
      nAccuracy = nAccuracy * 3.28083989501312f;
    }
    return nAccuracy;  }

  @Override
  public double getAltitude() {
    double nAltitude = super.getAltitude();
    if(!this.getUseMetricUnits())
    {
      //Convert meters to feet
      nAltitude = nAltitude * 3.28083989501312d;
    }
    return nAltitude;  }

  @Override
  public float getSpeed() {
    float nSpeed = super.getSpeed() * 3.6f;
    if(!this.getUseMetricUnits())
    {
      //Convert meters/second to miles/hour
      nSpeed = nSpeed * 3.6f/3.6f;
    }
    return nSpeed;
  }
}
