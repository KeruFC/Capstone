package com.example.capstone

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class homeFragment : Fragment() {

    private lateinit var countDownTimer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timerStart()
//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.cancel()
    }

    private fun timerStart(){
        val timeZone = TimeZone.getTimeZone("CET")
        val c = Calendar.getInstance(timeZone)
        val currentHour = c.get(Calendar.HOUR_OF_DAY)
        val currentMinute = c.get(Calendar.MINUTE)
        val currentSecond = c.get(Calendar.SECOND)
        val timeInMilliseconds =
            (currentHour * 3600000) + (currentMinute * 60000) + (currentSecond * 1000)

        val timeEnd = 86400000

        val difference = timeEnd - timeInMilliseconds

        countDownTimer = object : CountDownTimer(difference.toLong(), 1000) {
            override fun onFinish() {
                tvTimeRemaining.text = "Het is tijd!"
            }

            override fun onTick(millisUntilFinished: Long) {
                var diff = millisUntilFinished
                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60
                val hoursInMilli = minutesInMilli * 60

                val elapsedHours = diff / hoursInMilli
                diff %= hoursInMilli

                val elapsedMinutes = diff / minutesInMilli
                diff %= minutesInMilli

                val elapsedSeconds = diff / secondsInMilli
                tvTimeRemaining.text =
                    "$elapsedHours:$elapsedMinutes:$elapsedSeconds"

            }
        }.start()
    }
}