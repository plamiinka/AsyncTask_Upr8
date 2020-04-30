package com.example.asynctask_upr8;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    ProgressFragment progressFragment;
    TextView textView;
    ProgressBar progressBar;
    Button button;

    private boolean downloadSuccess;
    private boolean loginSuccess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        textView = findViewById(R.id.result);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.buttonRun);

        fragmentManager = getSupportFragmentManager();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressFragment = ProgressFragment.newInstance();
                progressFragment.show(fragmentManager, "progress");
                textView.setVisibility(View.INVISIBLE);
                new DownloadTask().execute();
                new LoginTask().execute();
            }
        });
    }

    private class DownloadTask extends AsyncTask<Void, Void, Boolean> {
        Random r = new Random();
        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                int time = r.nextInt((5 - 2) + 1) + 2;
                Thread.sleep(time * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r.nextBoolean();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            downloadSuccess = r.nextBoolean();
            processCheck();
        }

    }

    private class LoginTask extends AsyncTask<Void, Void, Boolean> {
        Random r = new Random();
        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                int time = r.nextInt((5 - 3) + 1) + 3;
                 Thread.sleep(time  * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return r.nextBoolean();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            loginSuccess = r.nextBoolean();
            processCheck();
        }
    }
    private void processCheck(){
        textView.setVisibility(View.VISIBLE);
        if (loginSuccess && downloadSuccess) {
            textView.setText("Success!");
        } else {
            textView.setText("Failure..");
        }
        progressFragment.dismiss();
    }
}
