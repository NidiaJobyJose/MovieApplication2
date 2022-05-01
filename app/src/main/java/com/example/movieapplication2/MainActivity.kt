package com.example.movieapplication2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.movieapplication2.navigation.MovieNav
import com.example.movieapplication2.ui.theme.MovieApplication2Theme


class MainActivity : ComponentActivity() {

    override fun onStart(){
        super.onStart()
        Log.i("MainActivity", "onStart called")
    }

    override fun onResume(){
        super.onResume()
        Log.i("MainActiviy", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActiviy", "onPause called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActiviy", "onRestart called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActiviy", "onStop called")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.i("MainActiviy", "onDestroy called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate called")

        setContent {
            MyApp{
                MovieNav()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
    MovieApplication2Theme {
        content()
    }
}

