//
//  UIView+React.swift
//  AwesomeProject
//
//  Created by Boaro Lorenzo on 11/11/2018.
//  Copyright © 2018 Boaro Lorenzo. All rights reserved.
//

import UIKit
import React

extension UIView {
    func addReactView(_ reactView: ReactView, intialPros: [String: Any] = [:]) {
        let rctRootView = RCTRootView(bridge: Bridging.shared.bridge, moduleName: reactView.rawValue, initialProperties: intialPros)
        
        guard let reactView = rctRootView else {
            return
        }
        
        reactView.backgroundColor = .clear
        reactView.translatesAutoresizingMaskIntoConstraints = false
        
        addSubview(reactView)
        
        reactView.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        reactView.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
        reactView.topAnchor.constraint(equalTo: topAnchor).isActive = true
        reactView.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true
    }
}
