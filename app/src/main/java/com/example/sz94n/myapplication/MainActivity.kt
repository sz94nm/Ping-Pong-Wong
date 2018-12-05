package com.example.sz94n.myapplication

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val setsetting = sharedPreferences.getInt("setsettings", 1)
        val pointsetting = sharedPreferences.getInt("pointsettings", 11)




        when (setsetting) {
            1 -> onesetRB.isChecked = true
            3 -> threesetsRB.isChecked = true
            5 -> fivesetsRB.isChecked = true
        }

        when (pointsetting) {
            11 -> tenpointsRB.isChecked = true
            21 -> twentypointsRB.isChecked = true
        }
        //logoAnimation = logo.getBackground();

        startBTN.setOnClickListener {
            var sets: Int = 1
            var points: Int = 11
            when (setsRG.checkedRadioButtonId) {
                threesetsRB.id -> sets = 3
                fivesetsRB.id -> sets = 5
            }
            if (twentypointsRB.isChecked) {
                points = 21
            }
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = sharedPreferences.edit()
            editor.putInt("sets", sets)
            editor.putInt("points", points)
            editor.putInt("leftscore", 0)
            editor.putInt("rightscore", 0)
            editor.putInt("leftsets", 0)
            editor.putInt("rightsets", 0)




            editor.apply()

            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)

        }
        continueBTN.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }

    }

    override fun onStop() {
        super.onStop()
        var sets: Int = 1
        var points: Int = 11
        when (setsRG.checkedRadioButtonId) {
            threesetsRB.id -> sets = 3
            fivesetsRB.id -> sets = 5
        }
        if (twentypointsRB.isChecked) {
            points = 21
        }

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()
        editor.putInt("setsettings", sets)
        editor.putInt("pointsettings", points)
        editor.apply()


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        var xxx: AnimationDrawable = logo.getBackground() as AnimationDrawable
        xxx.start()
    }

}
