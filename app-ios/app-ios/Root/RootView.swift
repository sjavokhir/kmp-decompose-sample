//
//  RootView.swift
//  app-ios
//
//  Created by Javokhir Savriev on 27/08/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RootView: View {
    
    private let root: RootComponent

    init(_ root: RootComponent) {
        self.root = root
    }

    var body: some View {
        StackView(
            stackValue: StateValue(root.stack),
            getTitle: {
                switch $0 {
                case is RootComponentChild.ListChild: return "List"
                case is RootComponentChild.DetailsChild: return "Details"
                default: return ""
                }
            },
            onBack: root.onBackClicked,
            childContent: {
                switch $0 {
                case let child as RootComponentChild.ListChild: ListView(child.component)
                case let child as RootComponentChild.DetailsChild: DetailsView(child.component)
                default: EmptyView()
                }
            }
        )
        .edgesIgnoringSafeArea(.all)
    }
}
