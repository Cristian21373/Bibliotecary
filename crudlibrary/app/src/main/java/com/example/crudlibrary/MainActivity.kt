package com.example.crudlibrary

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var btnLibro =
            findViewById<Button>(R.id.btnLibro)

        var NuevoLibro =
            findViewById<Button>(R.id.NuevoLibro)

        btnLibro.setOnClickListener {
            crudlibrary(1)
        }

        NuevoLibro.setOnClickListener {
            crudlibrary(2)
        }



    }
        fun crudlibrary(position:Int){
            var fragment: Fragment
            when(position){
                1->fragment=opcionesLibro()
                2->fragment=NuevoLibro()

                else-> fragment = opcionesLibro()
            }
            val fragmentManager = supportFragmentManager

            val fragmentTransition = fragmentManager.beginTransaction()
            fragmentTransition.replace(R.id.FCV,fragment)
            fragmentTransition.commit()

    }
}