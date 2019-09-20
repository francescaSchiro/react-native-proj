//
//  Bridging.swift
//  AwesomeProject
//
//  Created by Boaro Lorenzo on 11/11/2018.
//  Copyright © 2018 Boaro Lorenzo. All rights reserved.
//

import Foundation
import React
import MSREventBridge

final class Bridging: NSObject {
    static let shared = Bridging()
    
    private(set) var bridge: RCTBridge!
    
    func setupBridge() {
        self.bridge = RCTBridge(delegate: self, launchOptions: [:])
    }
}

extension Bridging: RCTBridgeDelegate {
    func sourceURL(for bridge: RCTBridge!) -> URL! {
        return RCTBundleURLProvider.sharedSettings().jsBundleURL(forBundleRoot: "index", fallbackResource: nil)
    }
    
    func extraModules(for bridge: RCTBridge!) -> [RCTBridgeModule]! {
        return []
    }
}
