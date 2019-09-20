//
//  ReactNativeEvent.swift
//  AwesomeProject
//
//  Created by Boaro Lorenzo on 18/11/2018.
//  Copyright © 2018 Boaro Lorenzo. All rights reserved.
//

import Foundation

enum ReactEvent: RawRepresentable {
    case presentDetail
    
    init?(rawValue: String) {
        switch rawValue {
        case "PresentDetail":
            self = .presentDetail
        default:
            return nil
        }
    }
    
    var rawValue: String {
        switch self {
        case .presentDetail:
            return "PresentDetail"
        }
    }
}
