package uz.javokhir.decompose.sample.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import uz.javokhir.decompose.sample.android.root.RootContent
import uz.javokhir.decompose.sample.root.DefaultRootComponent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = DefaultRootComponent(
            componentContext = defaultComponentContext(),
        )

        setContent {
            AppTheme {
                Surface {
                    RootContent(
                        component = root,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
