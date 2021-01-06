package com.example.capstone

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LoginFragment : Fragment() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance().reference
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btLogin.setOnClickListener {
            val discordId = etDiscordID.text.toString()
            val success = 0

            val getdata = object : ValueEventListener{

                override fun onCancelled(error: DatabaseError) {
                }

                @SuppressLint("SetTextI18n")
                override fun onDataChange(snapshot: DataSnapshot) {

                    for (i in snapshot.children){
                        val hasData = i.child(discordId).child("cash").value.toString()

                        if(hasData != "null") {
                            tvDiscordname.text =
                                i.child(discordId).child("username").value.toString()
                            tvPunten.text = "Aantal punten: " + i.child(discordId)
                                .child("score").value.toString()
                            tvStreak.text = "Streak 0000: " + i.child(discordId)
                                .child("streak").value.toString()
                            tvLaatste0000.text = "Laatste 0000: " + i.child(discordId)
                                .child("last_time").value.toString()
                            tvGeld.text =
                                "Geld: €" + i.child(discordId).child("cash").value.toString()
                            tvInvWaarde.text = "Inv Waarde: €" + i.child(discordId)
                                .child("inv_worth").value.toString()
                            tvAantalCases.text = "Aantal Cases: " + i.child(discordId)
                                .child("cases").value.toString()
                        } else if (hasData == "null"){
                            tvDiscordname.text = getString(R.string.invalid_id)
                            tvPunten.text = ""
                            tvStreak.text = ""
                            tvLaatste0000.text = ""
                            tvGeld.text = ""
                            tvInvWaarde.text = ""
                            tvAantalCases.text = ""
                        }
                    }
                }
            }

            database.addValueEventListener(getdata)
        }


    }
}

