//
//  ViewController.swift
//  AwesomeProject
//
//  Created by Boaro Lorenzo on 11/11/2018.
//  Copyright © 2018 Boaro Lorenzo. All rights reserved.
//

import UIKit

enum Section {
    case home
    case settings
}

class RootViewController: UIViewController {
    @IBOutlet var containerView: UIView!
    @IBOutlet var tabBar: UITabBar!
    @IBOutlet var homeTabBarItem: UITabBarItem!
    @IBOutlet var listsTabBarItem: UITabBarItem!    
    
    private let notificationCenter = NotificationCenter.default
    
    private var currentViewController: UINavigationController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tabBar.delegate = self
        
        tabBar.selectedItem = homeTabBarItem
        
        addViewController(createHomeViewController())
    }
    
    private func createHomeViewController() -> UIViewController {
        let viewController = ReactViewController.createWitReactConfiguration(.from(.home))
        viewController.title = NSLocalizedString("Beer of the month", comment: "")
        return viewController
    }
    
    private func createListViewController() -> UIViewController {
        let viewController = ReactViewController.createWitReactConfiguration(.from(.list), delegate: self)
        viewController.addEvent(.presentDetail)
        viewController.title = NSLocalizedString("Beers list", comment: "")
        return viewController
    }
    
    private func addViewController(_ viewController: UIViewController) {
        currentViewController = UINavigationController(rootViewController: viewController)
        add(currentViewController, into: containerView)
    }
}

extension RootViewController: UITabBarDelegate {
    func tabBar(_ tabBar: UITabBar, didSelect item: UITabBarItem) {
        
        currentViewController.remove()
        
        if item == homeTabBarItem {
            addViewController(createHomeViewController())
        } else if item == listsTabBarItem {
            addViewController(createListViewController())
        }
    }
}

extension RootViewController: ReactViewDelegate {
    func onEventDidReceive(_ event: ReactEvent, info: [AnyHashable: Any]?) {
        guard event == .presentDetail else {
            return
        }
        
        let viewController = ReactViewController.createWitReactConfiguration(.from(.detail))
        viewController.title = NSLocalizedString("Beer detail", comment: "")
        currentViewController.pushViewController(viewController, animated: true)
    }
}


