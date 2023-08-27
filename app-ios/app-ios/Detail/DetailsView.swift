//
//  DetailsView.swift
//  app-ios
//
//  Created by Javokhir Savriev on 27/08/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DetailsView: View {
    
    private let component: DetailsComponent

    @StateValue
    private var model: DetailsComponentModel

    init(_ component: DetailsComponent) {
        self.component = component
        _model = StateValue(component.model)
    }

    var body: some View {
        Button {
            component.onFinished()
        } label: {
            Text(model.item)
        }
    }
}
