package com.graywolf.bassdrop;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public static class PlaceholderFragment extends Fragment {

        private MediaPlayer mMediaPlayer;
        private View mRootView;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mRootView = rootView;

            class MyTimerTask extends TimerTask {
                @Override
                public void run(){

                    if(isAdded()){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                YoYo.with(Techniques.Wobble)
                                        .duration(700)
                                        .playOn(mRootView.findViewById(R.id.dropTextView));
                            }
                        });
                    }
                }
            }

            MyTimerTask myTast = new MyTimerTask();
            Timer myTimer = new Timer();

            myTimer.schedule(myTast,1000,4000);

            Button playButton = (Button) rootView.findViewById(R.id.playButton);
            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getActivity().getApplicationContext(), PlayActivity.class);
                    startActivity(intent);
                }
            });

            return rootView;
        }
    }
}