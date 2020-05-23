package com.example.usesoundandroid

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var soundPool: SoundPool? = null
    private var sound: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val audioAttributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()

            soundPool = SoundPool.Builder()
                    .setMaxStreams(2)
                    .setAudioAttributes(audioAttributes)
                    .build()
        } else {
            soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
        }
        sound = soundPool?.load(this, R.raw.loading_sound, 1)

        play_button.setOnClickListener{
            soundPool?.play( 1,0.1F, 0.1F, 1, -1, 1F)
            //-1: forever loop
        }
        resume_button.setOnClickListener{
            soundPool?.resume(1)
        }
        pause_button.setOnClickListener{
            soundPool?.pause(1)
        }
        release_button.setOnClickListener{
            soundPool?.release()
        }

    }
}
