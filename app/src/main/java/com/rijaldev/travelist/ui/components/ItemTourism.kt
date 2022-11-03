package com.rijaldev.travelist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rijaldev.travelist.ui.theme.TravelistTheme

@Composable
fun ItemTourism(
    id: Int,
    photoUrl: String,
    title: String,
    location: String,
    rating: Double,
    isFavorite: Boolean,
    onFavoriteIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
   Box(
       modifier = modifier
           .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
   ) {
       Column {
           AsyncImage(
               model = photoUrl,
               contentDescription = title,
               contentScale = ContentScale.Crop,
               modifier = Modifier
                   .fillMaxWidth()
                   .height(256.dp)
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
           imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Outlined.FavoriteBorder,
           contentDescription = null,
           tint = if (!isFavorite) Color.Black else Color.Red,
           modifier = Modifier
               .padding(16.dp)
               .align(Alignment.TopEnd)
               .size(24.dp)
               .testTag("fav_button")
               .clickable { onFavoriteIconClicked(id, !isFavorite) }
       )
   }
}

@Preview(showBackground = true)
@Composable
fun ItemTourismPreview() {
    TravelistTheme {
        ItemTourism(
            id = 0,
            photoUrl = "",
            title = "Telaga Menjer",
            location = "Wonosobo, Jawa Tengah",
            rating = 8.9,
            isFavorite = true,
            onFavoriteIconClicked = { _, _ ->  }
        )
    }
}