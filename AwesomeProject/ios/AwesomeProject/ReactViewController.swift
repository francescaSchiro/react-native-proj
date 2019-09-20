//
//  EventBridge.swift
//  AwesomeProject
//
//  Created by Boaro Lorenzo on 11/11/2018.
//  Copyright © 2018 Boaro Lorenzo. All rights reserved.
//

import UIKit
import React
import MSREventBridge

protocol ReactViewDelegate: class {
    func onEventDidReceive(_ event: ReactEvent, info: [AnyHashable : Any]?)
}

class ReactViewController: UIViewController {
    @IBOutlet var reactContainerView: UIView!
    
    private var reactConfiguration: ReactConfiguration!
    private weak var delegate: ReactViewDelegate?
    private var events = [String: ReactEvent]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        reactContainerView.addReactView(reactConfiguration.reactView, intialPros: reactConfiguration.initialProps)
    }
    
    func addEvent(_ event: ReactEvent) {
        events[event.rawValue] = event
    }
    
    func removeEvent(_ event: ReactEvent) {
        events[event.rawValue] = nil
    }
}

extension ReactViewController {
    static func createWitReactConfiguration(_ reactConfiguration: ReactConfiguration, delegate: ReactViewDelegate? = nil) -> ReactViewController {
        let reactViewController = UIStoryboard.viewController(fromStoryboard: "React", usingIdentifier: "ReactViewController") as! ReactViewController
        reactViewController.reactConfiguration = reactConfiguration
        reactViewController.delegate = delegate
        return reactViewController
    }
}

extension ReactViewController: MSREventBridgeEventReceiver {
    func onEvent(withName name: String, info: [AnyHashable : Any]?) {
        guard let event = events[name] else {
            return
        }
        delegate?.onEventDidReceive(event, info: info)
    }
}
