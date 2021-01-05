package com.astend.android.a0_100acceleration.ui.presenter;

import android.content.Context;

import com.astend.android.a0_100acceleration.model.SpeedManager;
import com.astend.android.a0_100acceleration.ui.view.acceleration.AccelerationFragmentVIew;

public class AccelerationPresenterImpl implements AccelerationPresenter {
  AccelerationFragmentVIew view;
  Context context;

  public AccelerationPresenterImpl(AccelerationFragmentVIew view, Context context) {
    this.view = view;
    this.context = context;
  }

  @Override
  public void getData() {
    SpeedManager.getInstance(context).addListener(new SpeedManager.SpeedManagerListener() {
      @Override
      public void onProgressSpeed(float speed) {
        view.showProgressSpeed(speed);

      }

      @Override
      public void updatePassedTime(long timePoint) {
        view.showPassedTime(timePoint);


      }
    });

  }
}
