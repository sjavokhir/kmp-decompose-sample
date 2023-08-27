//
//  ListView.swift
//  app-ios
//
//  Created by Javokhir Savriev on 27/08/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ListView: View {
    
    private let component: ListComponent

    @StateValue
    private var model: ListComponentModel

    init(_ component: ListComponent) {
        self.component = component
        _model = StateValue(component.model)
    }

    var body: some View {
        List(model.items, id: \.self) { item in
            Button {
                component.onItemClicked(item: item)
            } label: {
                Text(item)
            }
        }
    }
}
