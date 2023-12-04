package org.j0schi.core.entity;

import lombok.Data;

@Data
public class Model {

    private int id;
    private int vertexCount;
    private Texture texture;

    public Model(int id, int vertexCount){
        this.id = id;
        this.vertexCount = vertexCount;
    }

    public Model(int id, int vertexCount, Texture texture){
        this.id = id;
        this.vertexCount = vertexCount;
        this.texture = texture;
    }
}
