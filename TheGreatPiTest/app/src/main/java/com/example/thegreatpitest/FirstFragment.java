package com.example.thegreatpitest;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.VibrationEffect;
import android.os.Vibrator;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.fragment.NavHostFragment;

import com.example.thegreatpitest.databinding.FragmentFirstBinding;

import java.util.Vector;

public class FirstFragment extends Fragment {
    private static final String TAG = "FirstFragment";

    private FragmentFirstBinding binding;
    private static final String hundredNumsPi = "1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";
    private int delay = 0;
    private static final String testNums = "202";
    boolean stopVibration = false;
    public Vector<Integer> vibeLength = new Vector<>(5);

    public int index = 0;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Vibrator vibrator;
        vibeLength.add(10);
        vibeLength.add(20);
        vibeLength.add(30);
        vibeLength.add(40);
        vibeLength.add(50);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        EditText numberField = (EditText)getActivity().findViewById(R.id.editTextNumber);
        //CHANGE DELAY BUTTON
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delay = Integer.parseInt(numberField.getText().toString()) * 1000;
                Log.i(TAG, "Here is the delay number: " + delay);

            }
        });
        binding.stopVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.cancel();
                stopVibration = true;
            }
        });

        binding.vibeLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index < vibeLength.size() - 1) {
                    ++index;
                }
                else {
                    index = 0;
                }
                Log.i(TAG, String.valueOf(vibeLength.elementAt(index)));

            }
        });

        binding.halfway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopVibration = false;
                VibrationEffect vibe = VibrationEffect.createOneShot(122, 1);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(vibrator.hasVibrator()) {
                    final long[] pattern = {0, vibeLength.elementAt(index), 50};
                    new Thread() {
                        @Override
                        public void run() {
                            int numRuns = 0;
                            for (int i = (hundredNumsPi.length() / 2) - 1; i < hundredNumsPi.length(); i++) { //FIXME: Change back to hundredNumsPi
                                Log.i(TAG, "Length of hundrednums: " + hundredNumsPi.length() + " Here is the index: " + i);
                                if (stopVibration) {
                                    vibrator.cancel();
                                    break;
                                }

                                numRuns = Character.getNumericValue(hundredNumsPi.charAt(i));//FIXME: Change back to hundredNumsPi
                                Log.i(TAG, "Pi number: " + numRuns);
                                if (numRuns == 0) {
                                    numRuns = 10;
                                }
                                for (int j = 0; j < numRuns; j++) { //repeat the pattern 5 times

                                    if (stopVibration) {
                                        vibrator.cancel();
                                        break;
                                    }
                                    vibrator.vibrate(pattern, -1);
                                    try {
                                        Thread.sleep(250);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                try {
                                    Thread.sleep(900);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                }
            }
        });
        binding.timedVibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopVibration = false;
                VibrationEffect vibe = VibrationEffect.createOneShot(122, 1);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(vibrator.hasVibrator()) {
                    final long[] pattern = {0, vibeLength.elementAt(index), 50};
                    Log.i(TAG, "Size hundredNumsPi: " + hundredNumsPi.length());
                    new Thread() {
                        @Override
                        public void run() {
                            int numRuns = 0;
                            for (int i = 0; i < hundredNumsPi.length(); i++) { //FIXME: Change back to hundredNumsPi
                                if (stopVibration) {
                                    vibrator.cancel();
                                    break;
                                }
                                if (i == (hundredNumsPi.length() / 2) - 1) {
                                    long[] halfway = {0, 300, 25};
                                    vibrator.vibrate(halfway, -1);
                                }

                                numRuns = Character.getNumericValue(hundredNumsPi.charAt(i));//FIXME: Change back to hundredNumsPi
                                Log.i(TAG, "Pi number: " + numRuns);
                                if (numRuns == 0) {
                                    numRuns = 10;
                                }
                                for (int j = 0; j < numRuns; j++) { //repeat the pattern 5 times

                                    if (stopVibration) {
                                        vibrator.cancel();
                                        break;
                                    }
                                    vibrator.vibrate(pattern, -1);
                                    try {
                                        Thread.sleep(250);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                try {
                                    Thread.sleep(900);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}