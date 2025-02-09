package com.example.staggeredphotogallary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.staggeredphotogallary.ui.theme.StaggeredPhotoGallaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StaggeredPhotoGallaryTheme {
                //TODO: Use LazyVerticalGrid with a staggered layout to display images of varying heights
                //TODO: load image dynamically from drawable
                //TODO: click->enlarge using coroutine animation
            }
        }
    }
}

@Composable
fun MainScreen(){
    
}