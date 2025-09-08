import android.os.SystemClock
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.sora.sora.core.navigations.NavigationManager

// Back press handler with debounce
@Composable
fun BackPressHandler(
    navController: NavController?,
    onBackPress: (() -> Unit)?
) {
    // Time to debounce multiple back presses
    val lastBackPressedTime = remember { mutableStateOf(0L) }

    // Intercept the back press
    BackHandler {
        val currentTime = SystemClock.elapsedRealtime()
        if (currentTime - lastBackPressedTime.value > 500) { // Allow back press every 500 ms
            if (onBackPress == null) {
                navController?.popBackStack()
            } else {
                onBackPress()
            }
            lastBackPressedTime.value = currentTime
        }
    }
}
