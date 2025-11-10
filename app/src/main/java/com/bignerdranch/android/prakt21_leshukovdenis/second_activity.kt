package com.bignerdranch.android.prakt21_leshukovdenis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class second_activity : Fragment() {
    private lateinit var spinner: Spinner
    private lateinit var save_btn: Button
    private lateinit var next_btn: Button
    private lateinit var sum: EditText
    private lateinit var date: DatePicker

    private val gson = Gson()

    private val PREF_KEY_CREDENTIALS = "user_json"

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

        var prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val currencies = resources.getStringArray(R.array.currencies)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            currencies
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter



        save_btn = view.findViewById(R.id.save) as Button
        next_btn = view.findViewById(R.id.next) as Button
        sum = view.findViewById(R.id.sum_value) as EditText
        date = view.findViewById(R.id.mydate) as DatePicker

        save_btn.setOnClickListener{
            if(sum.text.toString().isNotEmpty())
            {
                val year = date.year
                val month = date.month
                val day = date.dayOfMonth
                val dateStr = String.format("%02d.%02d.%04d", day, month, year)

                val user = CurrencyRecord(spinner.selectedItem.toString(), sum.text.toString().toDouble(),dateStr)


                val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
                val json = prefs.getString(PREF_KEY_CREDENTIALS, null)

                val records: MutableList<CurrencyRecord> = if (json != null) {
                    val type = object : TypeToken<MutableList<CurrencyRecord>>() {}.type
                    gson.fromJson(json, type)
                } else {
                    mutableListOf()
                }
                records.add(user)
                val updated = gson.toJson(records)
                prefs.edit().putString(PREF_KEY_CREDENTIALS, updated).apply()

            }
            else{
                Snackbar.make(view, getString(R.string.error_sum), Snackbar.LENGTH_LONG)
            }


        }
        next_btn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, third_activity())
                .commit()
        }

        return view
    }
}