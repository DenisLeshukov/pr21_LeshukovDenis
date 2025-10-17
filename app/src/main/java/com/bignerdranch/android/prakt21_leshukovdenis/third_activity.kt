package com.bignerdranch.android.prakt21_leshukovdenis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class third_activity : Fragment()
{
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_third, container, false)
        button = view.findViewById(R.id.go_reg) as Button
        button.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, first_activity())
                .commit()

        }
        return view
    }
}
