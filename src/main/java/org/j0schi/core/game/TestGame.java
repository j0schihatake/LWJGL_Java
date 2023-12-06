package org.j0schi.core.game;

import org.j0schi.Launcher;
import org.j0schi.core.*;
import org.j0schi.core.config.Config;
import org.j0schi.core.entity.Entity;
import org.j0schi.core.entity.Model;
import org.j0schi.core.entity.Texture;
import org.j0schi.core.lighting.DirectionalLight;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class TestGame implements ILogic {

    private static final float CAMERA_MOVE_SPEED = 0.05f;

    private final RenderManager render;
    private final WindowManager window;

    private final ObjectLoader loader;
    private Entity entity;
    private Camera camera;

    private float lightAngle;
    private DirectionalLight directionalLight;

    Vector3f cameraInc;

    public TestGame(){
        render = new RenderManager();
        window = Launcher.getWindow();
        loader = new ObjectLoader();
        camera = new Camera();
        cameraInc = new Vector3f(0,0,0);
        lightAngle = -90;
    }

    @Override
    public void init() throws Exception {

        render.init();

        Model model = loader.loadObjModel("/models/bunny.obj");
        model.setTexture(new Texture(loader.loadTexture("textures/grassblock.png")), 1f);
        entity = new Entity(model, new Vector3f(0,0,-5), new Vector3f(0,0,0), 1);

        float lightIntensity = 1.0f;
        Vector3f lightPosition = new Vector3f(-1, -10, 0);
        Vector3f lightColour = new Vector3f(1,1,1);
        directionalLight = new DirectionalLight(lightColour, lightPosition, lightIntensity);

    }

    @Override
    public void input() {

        cameraInc.set(0,0,0);

        if(window.isKeyPressed(GLFW.GLFW_KEY_W))
            cameraInc.z = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_S))
            cameraInc.z = 1;

        if(window.isKeyPressed(GLFW.GLFW_KEY_A))
            cameraInc.x = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_D))
            cameraInc.x = 1;

        if(window.isKeyPressed(GLFW.GLFW_KEY_Z))
            cameraInc.y = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_X))
            cameraInc.y = 1;
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        camera.movePosition(
                cameraInc.x * CAMERA_MOVE_SPEED,
                cameraInc.y * CAMERA_MOVE_SPEED,
                cameraInc.z * CAMERA_MOVE_SPEED);

        if(mouseInput.isRightButtonPress()){
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * Config.MOUSE_SENSITIVITY, rotVec.y * Config.MOUSE_SENSITIVITY, 0);
        }

        //entity.incRotation(0.0f, 0.2f,0.0f);

        lightAngle += 0.5f;
        if(lightAngle > 90){
            directionalLight.setIntensity(0);
            if(lightAngle >= 360){
                lightAngle = -90;
            }
        } else if (lightAngle <= -80 || lightAngle >= 80) {
            float factor = 1 - (float)(Math.abs(lightAngle) - 80) / 10.0f;
            directionalLight.setIntensity(factor);
            directionalLight.getColour().y = Math.max(factor, 0.9f);
            directionalLight.getColour().z = Math.max(factor, 0.5f);
        } else{
            directionalLight.setIntensity(1);
            directionalLight.getColour().x = 1;
            directionalLight.getColour().y = 1;
            directionalLight.getColour().z = 1;
        }
        double angRad = Math.toRadians(lightAngle);
        directionalLight.getDirection().x = (float)Math.sin(angRad);
        directionalLight.getDirection().y = (float)Math.cos(angRad);
    }

    @Override
    public void render() {
        if(window.isResize()){
            GL11.glViewport(0,0,window.getWidth(), window.getHeight());
            window.setResize(true);
        }
        render.render(entity, camera, directionalLight);
    }

    @Override
    public void cleanup() {
        render.clenup();
        loader.cleanup();
    }
}
