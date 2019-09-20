//
//  UIStoryboard+React.swift
//  AwesomeProject
//
//  Created by Boaro Lorenzo on 11/11/2018.
//  Copyright © 2018 Boaro Lorenzo. All rights reserved.
//

import Foundation

extension UIStoryboard {    
    static func viewController(fromStoryboard storyboard: String, usingIdentifier identifier: String) -> UIViewController {
        let storyboard = UIStoryboard(name: storyboard, bundle: nil)
        let viewController = storyboard.instantiateViewController(withIdentifier: identifier)
        return viewController
    }
}

