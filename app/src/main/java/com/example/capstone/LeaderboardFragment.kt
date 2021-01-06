package com.example.capstone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
class LeaderboardFragment : Fragment() {

    private lateinit var database: DatabaseReference
    private var leaderboardListener: ValueEventListener? = null

    private val leaderboards = arrayListOf<Leaderboard>()
    private val leaderboardAdapter = LeaderboardAdapter(leaderboards)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance().reference.child("users")
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        getLeaderboard()

    }

    private fun initRv() {
        rvLeaderboard.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvLeaderboard.adapter = leaderboardAdapter
//        rvLeaderboard.addItemDecoration(
//                DividerItemDecoration(
//                        activity,
//                        DividerItemDecoration.VERTICAL
//                )
//        )
    }

    private fun getLeaderboard(){
        this.leaderboardListener = null
        val leaderboardListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var id = 0
                val usernames = ArrayList<String>()
                val inv_worth = ArrayList<String>()

                for(ds: DataSnapshot in snapshot.children){
                    id += 1
                    val position = "#$id"
                    usernames.add(ds.child("username").value.toString())
                    inv_worth.add(ds.child("inv_worth").value.toString())
                    val leaderboard = Leaderboard(position, usernames[id-1],
                            inv_worth[id-1])
                    leaderboards.add(leaderboard)
                }
                leaderboards.sortByDescending { it.money?.toInt() }
                leaderboardAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        }
        database.addValueEventListener(leaderboardListener)
        this.leaderboardListener = leaderboardListener
    }
}