package com.rijaldev.travelist.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rijaldev.travelist.model.Tourism
import com.rijaldev.travelist.ui.components.ItemTourism
import com.rijaldev.travelist.ui.components.SearchView
import com.rijaldev.travelist.ui.theme.TravelistTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    HomeContent()
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier
) {
    val list = listOf(
        Tourism(1, "Wonoland", "", "Wonosobo, Jawa Tengah", "https://salsawisata.b-cdn.net/wp-content/uploads/2022/02/Tempat-Wisata-Wonosobo.jpg", 7.6),
        Tourism(2, "Puncak Sikunir", "", "Wonosobo, Jawa Tengah", "https://radarsemarang.jawapos.com/wp-content/uploads/2021/04/wisata.jpg", 9.6),
        Tourism(3, "Danau Toba", "", "Sumatera Utara", "https://ds393qgzrxwzn.cloudfront.net/resize/m600x500/cat1/img/images/0/ryidSKJ08m.jpg", 5.6),
        Tourism(4, "Gunung Bromo", "", "Malang, Jawa Timur", "https://t-2.tstatic.net/tribunnewswiki/foto/bank/images/bromo.jpg", 8.6),
    )
    Column {
        SearchView()
        ListTourism(data = list)
    }
}

@Composable
fun ListTourism(
    data: List<Tourism>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(data, key = { it.id }) { item ->
            ItemTourism(
                photoUrl = item.photoUrl,
                title = item.name,
                location = item.location,
                rating = item.rating
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    TravelistTheme {
        HomeContent()
    }
}