package org.j0schi.core;

import lombok.Data;
import org.j0schi.Launcher;
import org.j0schi.core.config.Config;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

@Data
public class EngineManager {

    public static final long NANOSECOND = 1000000000L;
    public static final float FRAMERATE = 1000;

    private static int fps;
    private static float frametime = 1.0f / FRAMERATE;

    public boolean isRunning;

    private WindowManager windowManager;
    private MouseInput mouseInput;
    private GLFWErrorCallback errorCallback;
    private ILogic gameLogic;

    private void init(){
        GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        windowManager = Launcher.getWindow();
        gameLogic = Launcher.getGame();
        mouseInput = new MouseInput();
        try {
            windowManager.init();
            gameLogic.init();
            mouseInput.init();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void start(){
        init();
        if(isRunning)
            return;
        run();
    }

    public void run(){

        this.isRunning = true;
        int frames = 0;
        long frameCounter = 0;
        long lastTime = System.nanoTime();
        double unprocessedTime = 0;

        while (isRunning){
            boolean render = false;
            long startTime = System.nanoTime();
            long passwdTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passwdTime / (double)NANOSECOND;
            frameCounter += passwdTime;

            input();

            while(unprocessedTime > frametime){
                render = true;
                unprocessedTime -= frametime;

                if(windowManager.windowShouldClose())
                    stop();

                if(frameCounter >= NANOSECOND){
                    fps = frames;
                    windowManager.setTitle(Config.TITLE + fps);
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if(render){
                update(0f);
                render();
                frames++;
            }
        }
        cleanup();
    }

    private void stop(){
        if(!isRunning)
            return;
        isRunning = false;
    }

    private void input(){
        mouseInput.input();
        gameLogic.input();
    }

    private void render(){
        gameLogic.render();
        windowManager.update();
    }

    private void update(float interval){
        gameLogic.update(interval, mouseInput);
    }

    private void cleanup(){
        windowManager.cleanup();
        gameLogic.cleanup();
        errorCallback.free();
        GLFW.glfwTerminate();
    }
}
