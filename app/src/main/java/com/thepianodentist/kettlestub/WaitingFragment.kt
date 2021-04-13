package com.thepianodentist.kettlestub

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.lang.String


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class WaitingFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_waiting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.textview_waiting) as TextView?
//        textView =
//        id.text1 = findViewById(R.id.textView1) as TextView
//
        textView?.let {
            var dots = 0
            // can probably just use a regular timer?
            // this works for my demonstration
            object : CountDownTimer(16069000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    dots = (dots + 1) % 4
                    var dot_txt = ""
                    for (i in 0..dots) {
                        dot_txt += "."
                    }
                    it.text = "B☕iling in pr☕gress" + dot_txt
                }

                override fun onFinish() {
                    it.text = "close the app you moron"
                }
            }.start()
        }


    }
}