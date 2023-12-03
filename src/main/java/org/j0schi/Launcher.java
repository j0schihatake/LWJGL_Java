package org.j0schi;

import org.j0schi.core.EngineManager;
import org.j0schi.core.WindowManager;
import org.j0schi.core.utils.Consts;

public class Launcher {

    private static WindowManager window;
    private static EngineManager engine;

    public static void main(String[] args) {

        window = new WindowManager(Consts.TITLE, 1600, 900, false);
        engine = new EngineManager();
        try{
            engine.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static WindowManager getWindow(){
        return  window;
    }
}