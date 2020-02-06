//
//  ViewController.swift
//  ios-kotlin-mpp
//
//  Created by Fabio Bombardi on 05/02/2020.
//  Copyright © 2020 Fabio Bombardi. All rights reserved.
//

import UIKit
import kotlin_mpp

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        let debugMsg = Platform().name
        print("Hello, \(debugMsg)\n")
        print("Singleton (\(Singleton().self)): \(Singleton().printMyName())")
        print("MyClass: \(MyClass(platformName: Platform().name).printMyName())")
        print("MyClass (\(Singleton().self)): \(Singleton().myClass.printMyName())")
        print("MyClass2 (\(Singleton().self)): \(Singleton().myClass2.printHerName())")
        print("Platform (\(Singleton().self)): \(Singleton().platform)")
    }


}

