package uz.javokhir.decompose.sample.android.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import uz.javokhir.decompose.sample.android.detail.DetailsContent
import uz.javokhir.decompose.sample.android.list.ListContent
import uz.javokhir.decompose.sample.root.RootComponent

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(fade() + scale()),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.ListChild -> ListContent(component = child.component)
            is RootComponent.Child.DetailsChild -> DetailsContent(component = child.component)
        }
    }
}