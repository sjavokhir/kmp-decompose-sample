package uz.javokhir.decompose.sample.android.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import uz.javokhir.decompose.sample.list.ListComponent

@Composable
fun ListContent(component: ListComponent) {
    val model by component.model.subscribeAsState()

    LazyColumn {
        items(model.items) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        component.onItemClicked(item = item)
                    }
                    .padding(16.dp),
            )
        }
    }
}