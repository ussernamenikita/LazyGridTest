package com.example.lazygridtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazygridtest.mocks.getItems
import com.example.lazygridtest.models.ItemModel
import com.example.lazygridtest.ui.theme.LazyGridTestTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val items = getItems().toImmutableList()
        setContent {
            LazyGridTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ItemsGrid(items)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazyGridTestTheme {
        Greeting("Android")
    }
}

private const val COLUMNS_COUNT = 2

@Composable
fun ItemsGrid(
    items: ImmutableList<ItemModel>
) {
    LazyVerticalGrid(
        modifier = Modifier.testTag("listing:products"),
        contentPadding = PaddingValues(
            top = 0.dp,
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        columns = GridCells.Fixed(COLUMNS_COUNT),
    ) {

        items(
            count = items.size,
            key = { items[it].id },
            contentType = { ContentType.Item }
        ) {
            Item(
                model = items[it],
            )
        }
    }
}

enum class ContentType {
    Item
}

@Composable
fun Item(
    model: ItemModel
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(4.dp)
            .background(Color.Gray, shape = RoundedCornerShape(8.dp)),
    ) {
        Text( text = model.text)
        Image(
            modifier = Modifier.aspectRatio(1f),
            painter = painterResource(id = model.imageRes),
            contentDescription = "Test"
        )
    }
}