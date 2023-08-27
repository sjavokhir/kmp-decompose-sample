package uz.javokhir.decompose.sample.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import uz.javokhir.decompose.sample.detail.DefaultDetailsComponent
import uz.javokhir.decompose.sample.detail.DetailsComponent
import uz.javokhir.decompose.sample.list.DefaultListComponent
import uz.javokhir.decompose.sample.list.ListComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    // It's possible to pop multiple screens at a time on iOS
    fun onBackClicked(toIndex: Int)

    // Defines all possible child components
    sealed class Child {
        class ListChild(val component: ListComponent) : Child()
        class DetailsChild(val component: DetailsComponent) : Child()
    }
}

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.List, // The initial child component is List
            handleBackButton = true, // Automatically pop from the stack on back button presses
            childFactory = ::child,
        )

    private fun child(
        config: Config,
        componentContext: ComponentContext,
    ): RootComponent.Child =
        when (config) {
            is Config.List -> RootComponent.Child.ListChild(listComponent(componentContext))
            is Config.Details -> RootComponent.Child.DetailsChild(
                detailsComponent(
                    componentContext,
                    config
                )
            )
        }

    private fun listComponent(componentContext: ComponentContext): ListComponent =
        DefaultListComponent(
            componentContext = componentContext,
            onItemSelected = { item: String -> // Supply dependencies and callbacks
                navigation.push(Config.Details(item = item)) // Push the details component
            },
        )

    private fun detailsComponent(
        componentContext: ComponentContext,
        config: Config.Details,
    ): DetailsComponent =
        DefaultDetailsComponent(
            componentContext = componentContext,
            item = config.item, // Supply arguments from the configuration
            onFinished = navigation::pop, // Pop the details component
        )

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    @Parcelize // The `kotlin-parcelize` plugin must be applied if you are targeting Android
    private sealed interface Config : Parcelable {
        object List : Config
        data class Details(val item: String) : Config
    }
}