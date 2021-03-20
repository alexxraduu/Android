package com.example.tema1.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tema1.R
import com.example.tema1.fragments.Fragment1Activity2
import com.example.tema1.fragments.Fragment2Activity2
import com.example.tema1.fragments.Fragment3Activity2
import com.example.tema1.interfaces.ActivityFragmentCommunication

class SecondActivityKotlin : AppCompatActivity(), ActivityFragmentCommunication {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_kotlin)
        addF1A2()
    }

    private fun addF1A2(){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = Fragment1Activity2::class.java.name
        val addTransaction = transaction.add(
                R.id.frame_activity2,
                Fragment1Activity2.newInstance(),
                tag
        )
        addTransaction.commit()
    }
    override fun replaceWithF2A2() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = Fragment2Activity2::class.java.name
        val replaceTransaction = transaction.replace(
                R.id.frame_activity2,
                Fragment2Activity2.newInstance(),
                tag
        )
        replaceTransaction.addToBackStack(tag)
        replaceTransaction.commit()

    }

    override fun replaceWithF3A2() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = Fragment3Activity2::class.java.name
        val replaceTransaction = transaction.replace(
                R.id.frame_activity2,
                Fragment3Activity2.newInstance(),
                tag
        )
        replaceTransaction.addToBackStack(tag)
        replaceTransaction.commit()
    }

    override fun closeActivity() {
        finish()
    }

    override fun openSecondActivity() {
        TODO("Not yet implemented")
    }

    override fun goBackToF1A2() {
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStack()
    }
}