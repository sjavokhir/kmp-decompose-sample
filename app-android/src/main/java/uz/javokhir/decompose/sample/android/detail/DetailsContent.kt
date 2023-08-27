package uz.javokhir.decompose.sample.android.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import uz.javokhir.decompose.sample.detail.DetailsComponent

@Composable
fun DetailsContent(component: DetailsComponent) {
    val model by component.model.subscribeAsState()

    Text(
        text = model.item,
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .clickable {
                component.onFinished()
            }
            .padding(20.dp),
        color = MaterialTheme.colors.onPrimary,
    )
}