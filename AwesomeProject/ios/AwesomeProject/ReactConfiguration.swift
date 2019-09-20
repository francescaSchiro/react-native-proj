//
//  ReactNativeConfiguration.swift
//  AwesomeProject
//
//  Created by Boaro Lorenzo on 11/11/2018.
//  Copyright © 2018 Boaro Lorenzo. All rights reserved.
//

import Foundation
import UIKit

enum ReactView: RawRepresentable {
    case home
    case list
    case detail
    
    init?(rawValue: String) {
        switch rawValue {
        case "Home":
            self = .home
        case "List":
            self = .list
        case "Detail":
            self = .detail
        default:
            return nil
        }
    }
    
    var rawValue: String {
        switch self {
        case .home:
            return "Home"
        case .detail:
            return "Detail"
        case .list:
            return "List"
        }
    }
}

struct ReactConfiguration {
    let navigationBarHidden: Bool
    let reactView: ReactView
    let initialProps: [String: Any]
    
    private let defaultProps = ["screenWidth": UIScreen.main.bounds.width]
    
    private init(navigationBarHidden: Bool = false, reactView: ReactView, initialProps: [String: Any] = [:]) {
        self.navigationBarHidden = navigationBarHidden
        self.reactView = reactView
        self.initialProps = initialProps.merging(defaultProps) { (current, _) in current }
    }
    
    static func from(_ reactView: ReactView, navigationBarHidden: Bool = true, initialProps: [String: Any] = [:]) -> ReactConfiguration {
        return ReactConfiguration(navigationBarHidden: navigationBarHidden, reactView: reactView, initialProps: initialProps)
    }
}
