package com.thepianodentist.kettlestub

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.thepianodentist.kettlestub.api.Retriever
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private val retriever = Retriever()
    private var token: String = ""
    private val viewModel: TokenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private val messageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            Log.e(TAG, "in main act onReceive")
            val finishedBoil = intent.getStringExtra("message") == "Kettle boiled"
            val duration = Toast.LENGTH_LONG //if (finishedBoil) Toast.LENGTH_LONG else Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, intent.getStringExtra("message"), duration)
            toast.show()
            if (finishedBoil) {
                // I should learn what the backstack is
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, FirstFragment()).commit()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "in onstart")
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, IntentFilter("MyData"))
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                // 2
                Log.e(TAG, "in token oncomplete")
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }
                // 3
                token = task.result ?: "cant find"

                // 4
                Log.i(TAG, token)
                //Toast.makeText(baseContext, token, Toast.LENGTH_LONG).show()
                viewModel.setToken(token)
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}