package com.nicholaspiazza.allcaps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.state_layout.view.*

class MainActivity : AppCompatActivity(), OnStateListener{

    //variable for the recycler view
    private lateinit var recyclerView: RecyclerView

    //TAG for logcat
    private val TAG = "MainActivity"

    //String array of all the states
    val states = arrayOf("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
    "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana",
    "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan",
    "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
    "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
    "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas",
    "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming")

    //String array for all the capitals
    val capitals = arrayOf("Montgomery", "Juneau", "Phoenix", "Little Rock", "Sacramento",
    "Denver", "Hartford", "Dover", "Tallahassee", "Atlanta", "Honolulu", "Boise", "Springfield",
    "Indianapolis", "Des Moines", "Topeka", "Frankfort", "Baton Rouge", "Augusta", "Annapolis",
    "Boston", "Lansing", "Saint Paul", "Jackson", "Jefferson City", "Helena", "Lincoln",
    "Carson City", "Concord", "Trenton", "Santa Fe", "Albany", "Raleigh", "Bismarck", "Columbus",
    "Oklahoma City", "Salem", "Harrisburg", "Providence", "Columbia", "Pierre", "Nashville",
    "Austin", "Salt Lake City", "Montpelier", "Richmond", "Olympia", "Charleston", "Madison",
    "Cheyenne")

    //onCreate Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //apply TheAdapter object to RecyclerView adapter
        recyclerView = recycler_view.apply {
            adapter = TheAdapter(states, this@MainActivity)
        }
    }

    //RecyclerView adapter class with string array field and OnStateListener object field
    class TheAdapter(private val theStates: Array<String>, private val aStateListener: OnStateListener): RecyclerView.Adapter<TheAdapter.TheViewHolder>(){

        //ViewHolder class with View object field, OnStateListener object Field, and an onClickListener on the the View field
        class TheViewHolder(val view: View, aStateListener: OnStateListener) : RecyclerView.ViewHolder(view){
            init {
                view.setOnClickListener {

                    //call OnStateListener method with position of adapter as parameter
                    aStateListener.onStateClick(position = adapterPosition)
                }
            }

            //textview for the state name
            val stateText: TextView = view.findViewById(R.id.state_text_view)

        }

        //create the view holder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheViewHolder {

            //inflate layout of state layout
            val view = LayoutInflater.from(parent.context).inflate(R.layout.state_layout, parent, false)

            //pass in View object and OnStateListener object
            return TheViewHolder(view, aStateListener)
        }

        override fun onBindViewHolder(holder: TheViewHolder, position: Int) {

            //initialize state String with the string at index position of theStates array
            val state: String = theStates[position]

            //set textview text to state
            holder.view.state_text_view.setText(state)
        }

        override fun getItemCount(): Int {

            //size of string array theStates is item count
            return theStates.size
        }
    }

    override fun onStateClick(position: Int) {
        //for debugging
        Log.d(TAG, "onStateClick: click")

        //intialize captial string from string at index position of capitals array
        val capital: String = capitals[position]

        //intent object
        val intentObject: Intent = Intent(this, ProfileActvity::class.java)

        //"state" key and capital string as value
        intentObject.putExtra("state", capital)

        //startActivity method with intentObject as parameter
        startActivity(intentObject)
    }
}

//implemented by MainActivity
interface OnStateListener{

    //implemented in MainActivity
    fun onStateClick(position: Int)
}



