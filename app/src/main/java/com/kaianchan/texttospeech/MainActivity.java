 package com.kaianchan.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

 public class MainActivity extends AppCompatActivity {

    // views
    EditText hear_inputbox;
    Button hear_speakbtn, hear_stopbtn;

    TextToSpeech hear_TextToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // layout
        hear_inputbox =  findViewById(R.id.hear_inputbox);
        hear_speakbtn = findViewById(R.id.hear_speakbtn);
        hear_stopbtn = findViewById(R.id.hear_stopbtn);

        // Text To Speech
        hear_TextToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // If there is no error, then set language
                if(status != TextToSpeech.ERROR) {
                    // UK Version
                    //mTextToSpeech.setLanguage(Locale.UK);

                    // Portuguese Version
                    hear_TextToSpeech.setLanguage(new Locale("pt"));

                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Speak Button OnClick
        hear_speakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get text from edit text
                String toSpeak = hear_inputbox.getText().toString().trim();

                // No any input
                if (toSpeak.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter text!", Toast.LENGTH_SHORT).show();
                } else {
                    // Have input
                    Toast.makeText(MainActivity.this, toSpeak, Toast.LENGTH_SHORT).show();
                    // Speak the text
                    hear_TextToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        // Stop speaking OnClick
        hear_stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If it is speaking
                if (hear_TextToSpeech.isSpeaking()) {
                    // Stop speaking
                    hear_TextToSpeech.stop();
                    // mTextToSpeech.shutdown();
                } else {
                    // Not speaking
                    Toast.makeText(MainActivity.this, "Not Speaking!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

     @Override
     public void onPause() {
         if (hear_TextToSpeech != null || hear_TextToSpeech.isSpeaking()) {
             hear_TextToSpeech.stop();
             // mTextToSpeech.shutdown();
         }
         super.onPause();
     }
 }
