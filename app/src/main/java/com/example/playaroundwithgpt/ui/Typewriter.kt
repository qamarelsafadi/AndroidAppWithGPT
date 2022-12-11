package com.example.playaroundwithgpt.ui

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class Typewriter : AppCompatTextView {
    private var mText: CharSequence? = null
    private var mIndex = 0
    private var mDelay: Long = 500 //Default 500ms delay

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}

    private val mHandler: Handler = Handler()
    private val characterAdder: Runnable = object : Runnable {
        override fun run() {
            setText(mText!!.subSequence(0, mIndex++))
            if (mIndex <= mText!!.length) {
                mHandler.postDelayed(this, mDelay)
            }
        }
    }

    fun animateText(text: CharSequence?) {
        mText = text
        mIndex = 0
        setText("")
        mHandler.removeCallbacks(characterAdder)
        mHandler.postDelayed(characterAdder, mDelay)
    }

    fun setCharacterDelay(millis: Long) {
        mDelay = millis
    }
}