package com.example.havadurumu.ui.scene.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.havadurumu.R
import com.example.havadurumu.data.model.WeatherModel

@Composable
fun HomeScene(modifier: Modifier) {
    val viewmodel: HomeViewModel = hiltViewModel()
    val data by viewmodel.data.collectAsState()
    if (data != null) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.background_image),
                    contentScale = ContentScale.FillBounds,
                    alpha = 0.9f
                )
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart
                ) {
                    currentCityDescription(data)
                }
                Row {
                    weekInfoCard(modifier, data)
                }
            }
        }
    }

}

@Composable
private fun weekInfoCard(modifier: Modifier, data: WeatherModel?) {
    Box(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(300.dp)
            .fillMaxSize()
            .background(
                color = Color(0XFF1C6E91).copy(alpha = 0.8f), shape = RoundedCornerShape(25.dp)
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text(
                text = "10 GÜNLÜK TAHMİN", style = TextStyle(color = Color.White.copy(alpha = 0.5f))
            )
            Divider(modifier = modifier.padding(vertical = 10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                LazyColumn {
                    items(data!!.result) { item ->
                        RowItem(modifier, item)

                    }
                }

            }
        }
    }
}

@Composable
private fun RowItem(modifier: Modifier, item: WeatherModel.Result) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = item.date, style = TextStyle(
                color = Color.White.copy(alpha = 0.5f), fontWeight = FontWeight.Bold, fontSize = 16.sp
            )
        )
        Box(modifier = modifier.size(30.dp)) {
            AsyncImage(model = item.icon, contentDescription = "")
        }

        val degreeText = item.degree.toDouble().toInt().toString()
        Text(
            text = "${degreeText}°", style = TextStyle(
                color = Color.White.copy(alpha = 0.5f), fontWeight = FontWeight.Bold, fontSize = 16.sp
            )
        )
    }
}

@Composable
private fun currentCityDescription(data: WeatherModel?) {
    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .padding(start = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = data?.city ?: "",
                style = TextStyle(color = Color.White, fontSize = 40.sp),
                modifier = Modifier.padding(end = 8.dp),
                textAlign = TextAlign.Start
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }

        val degreeText = data?.result?.get(0)?.degree?.toDouble()?.toInt().toString()
        Text(
            text = "${degreeText}°",
            style = TextStyle(color = Color.White, fontSize = 100.sp),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Text(
            text = data?.result?.get(0)?.description?.uppercase() ?: "",
            style = TextStyle(color = Color.White, fontSize = 30.sp),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}


@Preview
@Composable
fun HomeScenePreview() {
    val data = WeatherModel(
        city = "Ankara", success = true, result = listOf(
            WeatherModel.Result(
                date = "20.08.2024",
                day = "Salı",
                degree = "29.19",
                description = "açık",
                humidity = "33",
                icon = "https://cdnydm.com/permedia/LC1i84c3Z2MtLUDNbzWz8A.png?size=512x512",
                max = "34.76",
                min = "21.02",
                night = "26.8",
                status = "Clear"
            )
        )
    )
    Column {
        currentCityDescription(
            data = data
        )
    }
}