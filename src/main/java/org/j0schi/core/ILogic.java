package org.j0schi.core;

public interface ILogic {

    void init() throws Exception;

    void input();

    void update(float interval, MouseInput mouseInput);

    void render();

    void cleanup();
}
