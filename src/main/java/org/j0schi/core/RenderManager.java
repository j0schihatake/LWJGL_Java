package org.j0schi.core;

import org.j0schi.Launcher;
import org.lwjgl.opengl.GL11;

public class RenderManager {

    private final WindowManager window;

    public RenderManager(){
        window = Launcher.getWindow();
    }

    public void init(){

    }

    public void render(){

    }

    public void clenup(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }
}
