package org.example;

import org.example.controller.IHMGlobal;
import org.example.util.SessionfactorySingleton;

public class Main {
    public static void main(String[] args) {
        IHMGlobal ihmGlobal = new IHMGlobal();
        ihmGlobal.start();
    }
}
