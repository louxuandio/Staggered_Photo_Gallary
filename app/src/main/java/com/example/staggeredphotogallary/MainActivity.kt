package com.example.staggeredphotogallary

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import com.example.staggeredphotogallary.ui.theme.StaggeredPhotoGallaryTheme
import org.xmlpull.v1.XmlPullParser

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StaggeredPhotoGallaryTheme {
                //TODO: click->enlarge using coroutine animation
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){
    var context = LocalContext.current
    val photos = loadPhotos(context)
    //val coroutineScope = rememberCoroutineScope()
    val selectedImage = remember { mutableStateOf<Int>(-1) }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp), //ChatGPT
        verticalArrangement = Arrangement.spacedBy(0.dp)

    ) {
        items(photos){ photo ->
            val (resId, title) = photo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clickable {
                        selectedImage.value = if (selectedImage.value == resId) -1 else resId
                    }
            ){
                val targetSize by animateDpAsState(targetValue = if (selectedImage.value == resId) 400.dp else 200.dp) //ChatGPT
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = title, //TODO
                    modifier = Modifier.fillMaxSize()
                        .width(targetSize)
                        .height((100..300).random().dp) //ChatGPT
                )
            }
        }
    }
    if (selectedImage.value != -1){
        val resId = selectedImage.value
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray)
        ){
            Image(
                painter = painterResource(id = resId),
                contentDescription = "selected",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { selectedImage.value = -1 }
                    .padding(32.dp)
            )
        }
    }
}
fun loadPhotos(context: Context): List<Pair<Int, String>>{
    val photos = mutableListOf<Pair<Int, String>>()
    val parser = context.resources.getXml(R.xml.photos)

    while (parser.next() != XmlPullParser.END_DOCUMENT) {
        if (parser.eventType == XmlPullParser.START_TAG && parser.name == "photo") {
            var title = ""
            var fileName = ""

            parser.nextTag()
            if (parser.name == "title") title = parser.nextText()
            parser.nextTag()
            if (parser.name == "file") fileName = parser.nextText()

            val resId = context.resources.getIdentifier(fileName.replace(".jpg", ""), "drawable", context.packageName)
            if (resId != 0) photos.add(resId to title)
        }
    }

    return photos
}
