package com.example.tema3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tema3.R
import com.example.tema3.fragments.BookFragment
import com.example.tema3.fragments.MainFragment
import com.example.tema3.interfaces.ActivityFragmentCommunication
import com.example.tema3.models.BookItemElement

class MainActivity : AppCompatActivity(), ActivityFragmentCommunication {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addMainFragment()
    }

    private fun addMainFragment() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = MainFragment::class.java.name
        val addTransaction = transaction.add(
            R.id.main_activity_layout,
            MainFragment.newInstance(),
            tag
        )
        addTransaction.commit()
    }

    override fun addBookFragment(book: BookItemElement?) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val tag = BookFragment::class.java.name
        val replaceTransaction = transaction.replace(
                R.id.main_activity_layout,
                BookFragment.newInstance(book),
                tag
        )
        replaceTransaction.addToBackStack(tag)
        replaceTransaction.commit()
    }

}