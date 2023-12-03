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

    private WindowManager window;
    private GLFWErrorCallback errorCallback;
    private ILogic gameLogic;

    private void init(){
        GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        window = Launcher.getWindow();
        gameLogic = Launcher.getGame();
        window.init();
        try {
            gameLogic.init();
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

                if(window.windowShouldClose())
                    stop();

                if(frameCounter >= NANOSECOND){
                    fps = frames;
                    window.setTitle(Config.TITLE + fps);
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if(render){
                update();
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
        gameLogic.input();
    }

    private void render(){
        gameLogic.render();
        window.update();
    }

    private void update(){
        gameLogic.update();
    }

    private void cleanup(){
        window.cleanup();
        gameLogic.cleanup();
        errorCallback.free();
        GLFW.glfwTerminate();
    }
}
