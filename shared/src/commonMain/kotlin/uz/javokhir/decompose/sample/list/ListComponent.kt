package uz.javokhir.decompose.sample.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

interface ListComponent {
    val model: Value<Model>

    fun onItemClicked(item: String)

    data class Model(
        val items: List<String>,
    )
}

class DefaultListComponent(
    componentContext: ComponentContext,
    private val onItemSelected: (item: String) -> Unit,
) : ListComponent {

    override val model: Value<ListComponent.Model> =
        MutableValue(ListComponent.Model(items = List(100) { "Item $it" }))

    override fun onItemClicked(item: String) {
        onItemSelected(item)
    }
}