package com.example.artspace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.data.artworks_data

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                val currImageId = remember { mutableIntStateOf(0) }
                ArtSpaceApp(
                    currImageId.intValue,
                ) { currImageId.intValue = it}

        }
    }
}

@Composable
fun ArtSpaceApp(
    currImageId: Int,
    onChange: (Int) -> Unit,
) {
    Log.d("TAG", "currImageId.intValue : ${currImageId+1}")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(),
            shadowElevation = 8.dp

        ){
            Image(
                painter = painterResource(artworks_data[currImageId].img),
                contentDescription = "Artwork image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding(32.dp)
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(60, 64, 67, 20)),
            verticalArrangement = Arrangement.Center,
        ){
            Text(
                text = "${currImageId + 1}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = artworks_data[currImageId].title,
                fontWeight = FontWeight(300),
                fontFamily = FontFamily.SansSerif,
                fontSize = 24.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 16.dp, end = 16.dp))
            Text(
                text = "${artworks_data[currImageId].artist} (${artworks_data[currImageId].year})",
                fontFamily = FontFamily.SansSerif,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 16.dp, start = 16.dp, end = 16.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (currImageId == 0) {
                        onChange(artworks_data.size - 1)
                    } else {
                        onChange(currImageId - 1)
                    }
                },
                modifier = Modifier
                    .width(120.dp)
                    .height(50.dp)
            )
            {
                Text(
                    text = "Previous",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = {
                    if (currImageId == artworks_data.size - 1) {
                        onChange(0)
                    } else {
                        onChange(currImageId + 1)
                    }
                },
                modifier = Modifier
                    .width(120.dp)
                    .height(50.dp)
            )
            {
                Text(text = "Next", fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold)
            }
        }
    }
}
