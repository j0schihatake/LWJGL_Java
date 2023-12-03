package org.j0schi;

import org.j0schi.core.EngineManager;
import org.j0schi.core.ILogic;
import org.j0schi.core.RenderManager;
import org.j0schi.core.WindowManager;
import org.j0schi.core.config.Config;
import org.j0schi.core.game.TestGame;

public class Launcher {

    private static WindowManager window;
    private static TestGame game;

    public static void main(String[] args) {

        window = new WindowManager(Config.TITLE, 1600, 900, false);
        game = new TestGame();
        EngineManager engine = new EngineManager();
        try{
            engine.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ILogic getGame(){
        return game;
    }

    public static WindowManager getWindow(){
        return  window;
    }
}