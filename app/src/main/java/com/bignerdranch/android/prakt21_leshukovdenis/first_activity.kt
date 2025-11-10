package com.bignerdranch.android.prakt21_leshukovdenis


import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceManager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import androidx.core.content.edit
import android.widget.Button

class first_activity : Fragment() {
    private lateinit var button: Button
    private lateinit var login: EditText
    private lateinit var password: EditText
    private val PREF_KEY_CREDENTIALS = "user_json_login"
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_first, container, false)
        button = view.findViewById(R.id.sign_in) as Button
        login = view.findViewById(R.id.login) as EditText
        password = view.findViewById(R.id.password) as EditText



        var prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val json = prefs.getString(PREF_KEY_CREDENTIALS,null)
        if(json !=null){
            val user = gson.fromJson(json, UserLog::class.java)

            login.setText(user.login)
            password.setText(user.password)
        }

            button.setOnClickListener {
                val loginText = login.text.toString().trim()
                val passwordText = password.text.toString().trim()
                if (login.text.toString() != "" && password.text.toString() != "") {
                    val user = UserLog(loginText, passwordText)
                    val json = gson.toJson(user)
                    var prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
                    prefs.edit().putString(PREF_KEY_CREDENTIALS,json).apply()


                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, second_activity())
                        .commit()
                    }
                else {
                    Snackbar.make(view, getString(R.string.error_reg), Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        return view
    }

    override fun onStart() {
        super.onStart()


    }
}