package com.rijaldev.travelist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rijaldev.travelist.ui.theme.TravelistTheme

@Composable
fun ItemTourism(
    photoUrl: String,
    title: String,
    location: String,
    rating: Double,
    modifier: Modifier = Modifier,
) {
   Box(
       modifier = modifier
           .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
           .clickable {  }
   ) {
       Column {
           AsyncImage(
               model = photoUrl,
               contentDescription = title,
               contentScale = ContentScale.Crop,
               modifier = Modifier
                   .fillMaxWidth()
                   .aspectRatio(1f)
                   .clip(RoundedCornerShape(16.dp))
           )
           Spacer(modifier = Modifier.height(8.dp))
           Row(
               verticalAlignment = Alignment.CenterVertically
           ) {
               Text(
                   text = title,
                   fontWeight = FontWeight.Bold,
                   maxLines = 2,
                   overflow = TextOverflow.Ellipsis,
                   modifier = Modifier.weight(1f)
               )
               Icon(
                   imageVector = Icons.Default.Star,
                   contentDescription = null,
                   modifier = Modifier
                       .padding(start = 8.dp, end = 2.dp)
                       .size(16.dp)
               )
               Text(
                   text = rating.toString(),
                   fontWeight = FontWeight.Light
               )
           }
           Text(
               text = location
           )
       }
       Icon(
           imageVector = Icons.Outlined.FavoriteBorder,
           contentDescription = null,
           modifier = Modifier
               .align(Alignment.TopEnd)
               .padding(16.dp)
               .size(24.dp)
       )
   }
}

@Preview(showBackground = true)
@Composable
fun ItemTourismPreview() {
    TravelistTheme {
        ItemTourism(
            photoUrl = "",
            title = "Telaga Menjer",
            location = "Wonosobo, Jawa Tengah",
            rating = 8.9
        )
    }
}