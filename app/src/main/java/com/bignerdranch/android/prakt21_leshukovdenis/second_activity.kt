package com.bignerdranch.android.prakt21_leshukovdenis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


class second_activity : Fragment() {
    private lateinit var spinner: Spinner
    private lateinit var button: Button
    private lateinit var sum: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_second, container, false)
        spinner = view.findViewById(R.id.myspinner) as Spinner

        val currencies = resources.getStringArray(R.array.currencies)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            currencies
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        button = view.findViewById(R.id.next) as Button
        sum = view.findViewById(R.id.sum_value) as EditText
        button.setOnClickListener{
            if(sum.text.toString() != "")
            {

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, third_activity())
                    .commit()


            }
            else{
                Snackbar.make(view, getString(R.string.error_sum), Snackbar.LENGTH_LONG)
            }


        }

        return view
    }
}