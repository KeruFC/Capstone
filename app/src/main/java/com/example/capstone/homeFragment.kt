package com.example.capstone

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class homeFragment : Fragment() {

    private lateinit var countDownTimer: CountDownTimer
    private lateinit var database: DatabaseReference
    private var announcementListener: ValueEventListener? = null

    private val announcements = arrayListOf<Announcements>()
    private val announcementsAdapter = AnnouncementAdapter(announcements)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance().reference.child("announcements")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvAnnouncements.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvAnnouncements.adapter = announcementsAdapter
        rvAnnouncements.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        getAnnouncements()

        timerStart()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.cancel()
    }

    private fun getAnnouncements(){
        this.announcementListener = null
        val announcementListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var id = 0
                for(ds: DataSnapshot in snapshot.children){
                    val announcement = Announcements(ds.value.toString())
                    announcements.add(announcement)
                    id += 1
                }
                announcementsAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        }
        database.addValueEventListener(announcementListener)
        this.announcementListener = announcementListener
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