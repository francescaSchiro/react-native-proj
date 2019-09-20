//
//  AppDelegate.swift
//  AwesomeProject
//
//  Created by Boaro Lorenzo on 11/11/2018.
//  Copyright © 2018 Boaro Lorenzo. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        // React Native bridge setup
        Bridging.shared.setupBridge()
        
        let viewController = UIStoryboard.viewController(fromStoryboard: "Main", usingIdentifier: "RootViewController")
        
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = viewController
        window?.makeKeyAndVisible()
        
        return true
    }
}

