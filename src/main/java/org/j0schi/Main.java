package org.j0schi;

import org.j0schi.core.WindowManager;
import org.lwjgl.Version;

public class Main {

    public static void main(String[] args) {

        System.out.println(Version.getVersion());

        WindowManager window = new WindowManager("DEV J0SCHI ENGINE", 1600, 900, false);
        window.init();

        while (!window.windowShouldClose()) {
            window.update();
        }

        window.cleanup();
    }
}