package com.example.sz94n.myapplication

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.DocumentsContract
import android.view.Gravity
import android.view.View
import android.widget.PopupWindow
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        var leftscore = sharedPreferences.getInt("leftscore", 0)
        var rightscore = sharedPreferences.getInt("rightscore", 0)
        var leftsets = sharedPreferences.getInt("leftsets", 0)
        var rightsets = sharedPreferences.getInt("rightsets", 0)
        val sets = sharedPreferences.getInt("sets", 0)
        val points = sharedPreferences.getInt("points", 0)
        var serve: String = "none"
        leftscoretext.text = leftscore.toString()
        rightscoretext.text = rightscore.toString()
        leftsettext.text = leftsets.toString()
        rightsetstext.text = rightsets.toString()
        leftserve.visibility = View.INVISIBLE
        rightserve.visibility = View.INVISIBLE



        homeBTN.setOnClickListener {
            val intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)
        }

        leftpointBTN.setOnClickListener {

            if (leftscore == 0 && rightscore == 0) {
                if (leftsets == 0 && rightsets == 0 && serve == "none") {
                    leftscore -= 1
                    serve = "left"
                    leftserve.visibility = View.VISIBLE
                    var xxx: AnimationDrawable = leftserve.getBackground() as AnimationDrawable
                    xxx.start()

                }
            }
            leftscore++
            if (((leftscore + rightscore) % 5 == 0)&&((leftscore+rightscore)>0)){
                if (serve == "left") {
                    serve = "right"
                    rightserve.visibility = View.VISIBLE
                    leftserve.visibility = View.INVISIBLE
                } else {
                    serve = "left"
                    leftserve.visibility = View.VISIBLE
                    rightserve.visibility = View.INVISIBLE
                }}
            if (leftscore == points) {
                leftscore = 0
                rightscore = 0
                leftsets += 1
                serve = "right"
                rightserve.visibility = View.VISIBLE
                leftserve.visibility = View.INVISIBLE
            }
            if ((rightscore == points - 2 && leftscore == points - 2) || (rightscore == points - 1 && leftscore == points - 1)) {
                leftscore -= 5
                rightscore -= 5
            }
            leftscoretext.text = leftscore.toString()
            rightscoretext.text = rightscore.toString()
            leftsettext.text = leftsets.toString()
            rightsetstext.text = rightsets.toString()

            if(leftsets==1&&sets==1||leftsets==2&&sets==3||leftsets==3&&sets==5)
            {
                Toast.makeText(this,"Chicken Dinner To BLUE Guy! ",Toast.LENGTH_LONG).show()
                leftscore=0
                rightscore=0
                leftsets=0
                rightsets=0
                leftscoretext.text=0.toString()
                rightscoretext.text=0.toString()
                leftsettext.text=0.toString()
                rightsetstext.text=0.toString()
                leftserve.visibility = View.INVISIBLE
                rightserve.visibility = View.INVISIBLE
                serve="none"

            }

        }


        rightpointsBTN.setOnClickListener {
            if (leftscore == 0 && rightscore == 0)
                if (leftsets == 0 && rightsets == 0 && serve == "none") {
                    rightscore -= 1
                    serve = "right"
                    rightserve.visibility = View.VISIBLE
                    var yyy: AnimationDrawable = rightserve.getBackground() as AnimationDrawable
                    yyy.start()
                }
            rightscore++
            if(((leftscore + rightscore) % 5 == 0)&&((leftscore+rightscore)>0)){
                if (serve == "left") {
                    serve = "right"
                    rightserve.visibility = View.VISIBLE
                    leftserve.visibility = View.INVISIBLE
                } else {
                    serve = "left"
                    leftserve.visibility = View.VISIBLE
                    rightserve.visibility = View.INVISIBLE
                }}
            if (rightscore == points) {
                leftscore = 0
                rightscore = 0
                rightsets += 1
                serve = "left"
                leftserve.visibility = View.VISIBLE
                rightserve.visibility = View.INVISIBLE
            }
            if ((rightscore == points - 2 && leftscore == points - 2) || (rightscore == points - 1 && leftscore == points - 1)) {
                leftscore -= 5
                rightscore -= 5
            }
            leftscoretext.text = leftscore.toString()
            rightscoretext.text = rightscore.toString()
            leftsettext.text = leftsets.toString()
            rightsetstext.text = rightsets.toString()
            if(rightsets==1&&sets==1||rightsets==2&&sets==3||rightsets==3&&sets==5)
            {
                leftscore=0
                rightscore=0
                leftsets=0
                rightsets=0
                leftscoretext.text=0.toString()
                rightscoretext.text=0.toString()
                leftsettext.text=0.toString()
                rightsetstext.text=0.toString()
                leftserve.visibility = View.INVISIBLE
                rightserve.visibility = View.INVISIBLE
                serve="none"
                Toast.makeText(this,"Chicken Dinner To RED Guy! ",Toast.LENGTH_LONG).show()
            }
        }
        resetBTN.setOnClickListener {
            leftscore=0
            rightscore=0
            leftsets=0
            rightsets=0
            leftscoretext.text=0.toString()
            rightscoretext.text=0.toString()
            leftsettext.text=0.toString()
            rightsetstext.text=0.toString()
            leftserve.visibility = View.INVISIBLE
            rightserve.visibility = View.INVISIBLE
            serve="none"
        }

    }


    override fun onStop() {
        super.onStop()

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()
        val leftscore = leftscoretext.text.toString()
        val rightscore = rightscoretext.text.toString()
        val leftsets = leftsettext.text.toString()
        val rightsets = rightsetstext.text.toString()

        editor.putInt("leftscore", leftscore.toInt())
        editor.putInt("rightscore", rightscore.toInt())
        editor.putInt("leftsets", leftsets.toInt())
        editor.putInt("rightsets", rightsets.toInt())


        editor.apply()

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        var yyy: AnimationDrawable = rightserve.getBackground() as AnimationDrawable
        yyy.start()
        var xxx: AnimationDrawable = leftserve.getBackground() as AnimationDrawable
        xxx.start()
    }

}
