package com.example.usersp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usersp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var userAadapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)
        val isFistTime = preferences.getBoolean(getString(R.string.sp_fist_time), true)
        val isUsername = preferences.getString(getString(R.string.sp_username), "NA")
        Log.i("SP", "${getString(R.string.sp_fist_time)} = $isFistTime" )

        Log.i("username", "${getString(R.string.sp_username)} = $isUsername" )

        if(isFistTime){
            val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_title)
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_confirm) { _, _ ->
                    val userName =
                        dialogView.findViewById<TextInputEditText>(R.id.etUserName).text.toString()
                    with(preferences.edit())
                    {
                        putBoolean(getString(R.string.sp_fist_time), false)
                        putString(getString(R.string.sp_username), userName)
                            .apply()
                    }
                    Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show()
                }
                .setNeutralButton(R.string.dialog_invitado, null)
                //.setNegativeButton("Cncelar", null)
                .show()
        }
        else
        {
            Toast.makeText(this, "Bienvenido $isUsername", Toast.LENGTH_LONG).show()
        }

        userAadapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAadapter
        }
    }

    private fun getUsers(): MutableList<User>
    {
        val users = mutableListOf<User>()
        val alain = User(1, "Alain", "Nicolás", "https://frogames.es/wp-content/uploads/2020/09/alain-1.jpg")
        val samanta = User(2, "Samanta", "Meza", "https://upload.wikimedia.org/wikipedia/commons/b/b2/Samanta_villar.jpg")
        val javier = User(3, "Javier", "Gómez", "https://live.staticflickr.com/974/42098804942_b9ce35b1c8_b.jpg")
        val emma = User(4, "Emma", "Cruz", "https://upload.wikimedia.org/wikipedia/commons/d/d9/Emma_Wortelboer_%282018%29.jpg")

        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)

        return  users
    }

    override fun onClick(user: User, position: Int) {
        Toast.makeText(this, "$position: ${user.getFullName()}", Toast.LENGTH_SHORT)
            .show()
    }
}