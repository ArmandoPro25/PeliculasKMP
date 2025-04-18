import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Screen(modifier: Modifier= Modifier, content: @Composable ()-> Unit){
    MaterialTheme {
        Surface (
            modifier = Modifier.fillMaxWidth(),
            content = content
        )
    }
}