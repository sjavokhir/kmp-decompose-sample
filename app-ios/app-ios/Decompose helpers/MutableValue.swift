//
//  MutableValue.swift
//  app-ios
//
//  Created by Javokhir Savriev on 27/08/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared

func mutableValue<T: AnyObject>(_ initialValue: T) -> MutableValue<T> {
    return MutableValueBuilderKt.MutableValue(initialValue: initialValue) as! MutableValue<T>
}
