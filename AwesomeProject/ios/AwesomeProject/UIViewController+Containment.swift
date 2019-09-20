//
//  UIViewController+Containment.swift
//  AwesomeProject
//
//  Created by Boaro Lorenzo on 11/11/2018.
//  Copyright © 2018 Boaro Lorenzo. All rights reserved.
//

import Foundation

extension UIViewController {
    func add(_ child: UIViewController, into view: UIView, usingDefaultConstraints: Bool = true) {
        guard let childView = child.view else {
            return
        }
        addChild(child)
        view.addSubview(childView)
        child.didMove(toParent: self)
        
        child.view.translatesAutoresizingMaskIntoConstraints = false
        
        if usingDefaultConstraints {
            view.leadingAnchor.constraint(equalTo: childView.leadingAnchor).isActive = true
            view.trailingAnchor.constraint(equalTo: childView.trailingAnchor).isActive = true
            view.topAnchor.constraint(equalTo: childView.topAnchor).isActive = true
            view.bottomAnchor.constraint(equalTo: childView.bottomAnchor).isActive = true
        }
    }
    
    func remove() {
        guard parent != nil else {
            return
        }
        willMove(toParent: nil)
        removeFromParent()
        view.removeFromSuperview()
    }
}
