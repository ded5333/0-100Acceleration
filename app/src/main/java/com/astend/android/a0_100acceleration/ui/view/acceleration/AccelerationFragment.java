package com.astend.android.a0_100acceleration.ui.view.acceleration;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astend.android.a0_100acceleration.R;
import com.astend.android.a0_100acceleration.model.SpeedManager;
import com.astend.android.a0_100acceleration.ui.presenter.AccelerationPresenter;
import com.astend.android.a0_100acceleration.ui.presenter.AccelerationPresenterImpl;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AccelerationFragment extends Fragment implements AccelerationFragmentVIew {
  public AccelerationPresenter presenter;
  public TextView tvTime;
  public TextView tvSpeed;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_acceleration, container, false);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (ActivityCompat.checkSelfPermission(getContext(),
        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


      return;
    }
      presenter = new AccelerationPresenterImpl(this, requireContext());


  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    BottomNavigationView btnNavView = view.findViewById(R.id.bottom_navigation);

    tvSpeed = view.findViewById(R.id.tvSpeed);
    tvTime = view.findViewById(R.id.tvTime);

    presenter.getData();





  }

  @Override
  public void showProgressSpeed(float speed) {
    tvSpeed.setText("" + speed);

  }

  @Override
  public void showPassedTime(float passedTime) {
    int minutes = (int) (passedTime / 1000 / 60);
    int seconds = (int) (passedTime / 1000 % 60);
    int milliSeconds = (int) (passedTime % 1000);

    tvTime.setText(minutes + ":" + seconds + ":" + milliSeconds);


  }
}