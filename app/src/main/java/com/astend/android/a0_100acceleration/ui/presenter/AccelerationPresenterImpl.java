package com.astend.android.a0_100acceleration.ui.presenter;

import com.astend.android.a0_100acceleration.model.SpeedManager;
import com.astend.android.a0_100acceleration.ui.view.acceleration.AccelerationFragmentVIew;

public class AccelerationPresenterImpl implements AccelerationPresenter {
  AccelerationFragmentVIew view;

  public AccelerationPresenterImpl(AccelerationFragmentVIew view) {
    this.view = view;
  }

  @Override
  public void getData() {
    SpeedManager.getInstance().addListener(new SpeedManager.SpeedManagerListener() {
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
