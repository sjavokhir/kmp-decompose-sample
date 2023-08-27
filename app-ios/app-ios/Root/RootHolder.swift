//
//  RootHolder.swift
//  app-ios
//
//  Created by Javokhir Savriev on 27/08/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

class RootHolder : ObservableObject {
    
    let lifecycle: LifecycleRegistry
    let root: RootComponent

    init() {
        lifecycle = LifecycleRegistryKt.LifecycleRegistry()

        root = DefaultRootComponent(
            componentContext: DefaultComponentContext(lifecycle: lifecycle)
        )

        LifecycleRegistryExtKt.create(lifecycle)
    }

    deinit {
        // Destroy the root component before it is deallocated
        LifecycleRegistryExtKt.destroy(lifecycle)
    }
}
