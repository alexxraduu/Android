package com.example.tema2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tema2.R
import com.example.tema2.fragments.Fragment1
import com.example.tema2.fragments.Fragment2
import com.example.tema2.fragments.Fragment3
import com.example.tema2.interfaces.ActivityFragmentCommunication
import com.example.tema2.models.Album
import com.example.tema2.models.User

class MainActivity : AppCompatActivity(), ActivityFragmentCommunication {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addF1()

    }

    private fun addF1() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = Fragment1::class.java.name
        val addTransaction = transaction.add(
                R.id.frame_activity,
                Fragment1.newInstance(),
                tag
        )
        addTransaction.commit()
    }

    override fun addF2(user: User) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = Fragment2::class.java.name
        val replaceTransaction = transaction.replace(
                R.id.frame_activity,
            Fragment2.newInstance(user),
            tag
        )
        replaceTransaction.addToBackStack(tag)
        replaceTransaction.commit()
    }

    override fun addF3(album: Album) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = Fragment3::class.java.name
        val replaceTransaction = transaction.replace(
                R.id.frame_activity,
            Fragment3.newInstance(album),
            tag
        )
        replaceTransaction.addToBackStack(tag)
        replaceTransaction.commit()
    }

}