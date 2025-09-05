import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.material.pullrefresh.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExampleScreen() {
    var isRefreshing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            scope.launch {
                isRefreshing = true
                delay(2000) // simulate network call
                isRefreshing = false
            }
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 56.dp)
        ) {
            items(listOf("Item 1", "Item 2", "Item 3")) { item ->
                Text(
                    item,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

//
//@Composable
//fun ExampleScreen() {
//    // State to manage refreshing status
//    val isRefreshing = remember { mutableStateOf(false) }
//
//    PullToRefreshSample(
//
//    ) {
//        // Your content goes here
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .wrapContentSize(Alignment.Center)
//        ) {
//            BasicText("Pull to Refresh Example")
//            if (isRefreshing.value) {
//                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
//        }
//    }
//}
//}
