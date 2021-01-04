package com.example.capstone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_announcement.view.*

class AnnouncementAdapter(private val announcements: List<Announcements>) :
    RecyclerView.Adapter<AnnouncementAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_announcement, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(announcements[position])
    }

    override fun getItemCount(): Int {
        return announcements.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun dataBind(announcements: Announcements) {
            itemView.tvAnnouncement.text = announcements.announcement
        }
    }
}