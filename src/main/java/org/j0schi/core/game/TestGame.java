package org.j0schi.core.game;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.j0schi.Launcher;
import org.j0schi.core.ILogic;
import org.j0schi.core.RenderManager;
import org.j0schi.core.WindowManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class TestGame implements ILogic {

    private int direction = 0;
    private float color = 0.0f;

    private final RenderManager render;
    private final WindowManager window;

    public TestGame(){
        render = new RenderManager();
        window = Launcher.getWindow();
    }

    @Override
    public void init() throws Exception {
        render.init();
    }

    @Override
    public void input() {
        if(window.isKeyPressed(GLFW.GLFW_KEY_UP))
            direction = 1;
        else if(window.isKeyPressed(GLFW.GLFW_KEY_DOWN))
            direction = -1;
        else
            direction = 0;
    }

    @Override
    public void update() {
        color += direction * 0.01f;
        if(color > 1)
            color = 1.0f;
        else if(color <= 0)
            color = 0.0f;
    }

    @Override
    public void render() {
        if(window.isResize()){
            GL11.glViewport(0,0,window.getWidth(), window.getHeight());
            window.setResize(true);
        }

        window.setClearColor(color, color, color, 0.0f);
        render.clenup();
    }

    @Override
    public void cleanup() {
        render.clenup();
    }
}
