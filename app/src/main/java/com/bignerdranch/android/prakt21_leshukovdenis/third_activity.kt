package com.bignerdranch.android.prakt21_leshukovdenis

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class third_activity : Fragment()
{
    private val PREF_KEY_CREDENTIALS = "user_json"
    private val gson = Gson()
    private lateinit var button: Button
    private lateinit var recycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val view = inflater.inflate(R.layout.activity_third, container, false)

        button = view.findViewById(R.id.go_reg) as Button
        recycler = view.findViewById(R.id.myList) as RecyclerView

        val json = prefs.getString(PREF_KEY_CREDENTIALS,null)

        val userList: List<CurrencyRecord> = if (json != null) {
            val type = object : TypeToken<List<CurrencyRecord>>() {}.type
            gson.fromJson<List<CurrencyRecord>>(json, type)
        } else {
            emptyList()
        }
        val adapter = AdapterRecycler(userList)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        button.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, first_activity())
                .commit()

        }
        return view
    }
}
