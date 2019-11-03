package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech tts1, tts2;
    EditText et1;
    Button b1,b2;
    SeekBar sb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.editText);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button1);
        sb1=(SeekBar)findViewById(R.id.seekBar1);

        tts1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts1.setLanguage(Locale.UK);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String speak = et1.getText().toString();
                float speed = (float) sb1.getProgress() / 50;
                if (speed < 0.1) speed = 0.1f;
                tts1.setSpeechRate(speed);
                Toast.makeText(getApplicationContext(), speak,Toast.LENGTH_SHORT).show();
                tts1.speak(speak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });


        tts2=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts2.setLanguage(Locale.US);
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String speak = et1.getText().toString();
                float speed = (float) sb1.getProgress() / 50;
                if (speed < 0.1) speed = 0.1f;
                tts2.setSpeechRate(speed);
                Toast.makeText(getApplicationContext(), speak,Toast.LENGTH_SHORT).show();
                tts2.speak(speak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    public void onDestroy(){
        if(tts1 !=null){
            tts1.stop();
            tts1.shutdown();
        }
        else if(tts2 !=null){
            tts2.stop();
            tts2.shutdown();
        }
        super.onDestroy();
    }
}