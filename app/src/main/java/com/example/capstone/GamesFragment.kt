package com.example.capstone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_leaderboard.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GamesFragment : Fragment() {

    private val games = arrayListOf<Games>()
    private val gamesAdapter = GamesAdapter(games) { game : Games -> gameOpened(game)}

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()


    }

    private fun initRv() {
        rvLeaderboard.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvLeaderboard.adapter = gamesAdapter

        for(i in Games.TITLES.indices) {
            games.add(Games(Games.IMG_LINKS[i], Games.TITLES[i], Games.DESCRIPTIONS[i]))
        }

        gamesAdapter.notifyDataSetChanged()
    }

    private fun gameOpened(game: Games) {
        val toast = Toast.makeText(context, "clicked!", Toast.LENGTH_LONG)
        toast.show()
    }
}