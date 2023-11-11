package com.compose.mycomposeapp

import android.support.v4.os.IResultReceiver.Default
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.mycomposeapp.ui.theme.Purple40

@Preview(showBackground = true, device = Devices.NEXUS_7, showSystemUi = true)
@Composable
fun _previewGymsScreen() {
    GymsScreen()
}

@Composable
fun GymsScreen() {
    val viewModel: GymsViewModel = viewModel()
    LazyColumn {
        items(viewModel.getGyms()) { item: Gym ->
            GymItem(gym = item)
        }
    }
}

@Composable
fun GymItem(gym: Gym) {
    var isFavourite by remember {
        mutableStateOf(false)
    }
    var favIcon = if (isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(15.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            DefaultIcon(Icons.Filled.LocationOn, Modifier.weight(0.15f),"location icon")
            GymDetails(gym = gym, Modifier.weight(0.70f))
            DefaultIcon(favIcon, Modifier.weight(.15f),"favourite icon") {
                isFavourite = !isFavourite
            }
        }
    }
}

@Composable
fun DefaultIcon(favIcon: ImageVector,
            modifier: Modifier,
            contentDesc:String,
            onCLickAction: () -> Unit={}) {
    Image(
        imageVector = favIcon,
        contentDescription = contentDesc,
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onCLickAction.invoke()
            },
        colorFilter = ColorFilter.tint(
            Color.DarkGray
        ))
}

@Composable
fun GymDetails(gym: Gym, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = gym.name,
            style = MaterialTheme.typography.headlineLarge,
            color = Purple40
        )

        Text(
            text = gym.details,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.alpha(.5f)
        )


    }
}

