package com.example.staggeredphotogallary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

//TODO: set up photos.xml
//TODO: use XmlPullParser to parse into drawable
//TODO: load photo
@Composable
fun MainScreen(){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp) //ChatGPT
    ) {
        /*
        items(photos){photo ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(photo.second.dp)
            ){
                Image(
                    painter = rememberImagePainter(photo.first),
                    contentDescription = null, //TODO
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

         */
    }
}