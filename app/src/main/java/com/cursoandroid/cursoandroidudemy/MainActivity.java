package com.cursoandroid.cursoandroidudemy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekVolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.teste);
        inicializarSeekBar();

    }

    private void inicializarSeekBar() {
        seekVolume = findViewById(R.id.seekBarVolume);

        //configurar o audio mananger
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //recuperar os valores de volume máximo e o volume atual
        int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //configurar os valores máximos para o SeekBar
        seekVolume.setMax( volumeMaximo );
        seekVolume.setProgress( volumeAtual );

        seekVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume( AudioManager.STREAM_MUSIC, progress, 0 );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public void executarSom(View view) {

        if( mediaPlayer != null ) {
            mediaPlayer.start();
        }

    }

    public void pausarMusica(View view) {
        if( mediaPlayer.isPlaying() ) {
            mediaPlayer.pause();
        }
    }

    public void pararMusica(View view) {
        if( mediaPlayer.isPlaying() ) {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.teste);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if( mediaPlayer != null && mediaPlayer.isPlaying() ) {
            mediaPlayer.stop();
            mediaPlayer.release(); //Responsavel por liberar todos os recursos de memoria apagando qualquer evento salvo no app.
            mediaPlayer = null;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        if( mediaPlayer.isPlaying() ) {
            mediaPlayer.pause();
        }

    }
}