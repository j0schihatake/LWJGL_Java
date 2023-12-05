package org.j0schi.core.entity;

import lombok.Data;

@Data
public class Model {

    private int id;
    private int vertexCount;
    private Material material;

    public Model(int id, int vertexCount){
        this.id = id;
        this.vertexCount = vertexCount;
        this.material = new Material();
    }

    public Model(int id, int vertexCount, Texture texture){
        this.id = id;
        this.vertexCount = vertexCount;
        this.material = new Material(texture);
    }

    public Model(Model model, Texture texture){
        this.id = id;
        this.vertexCount = model.getVertexCount();
        this.material = model.getMaterial();
        this.material.setTexture(texture);
    }

    public Texture getTexture(){
        return material.getTexture();
    }

    public void setTexture(Texture texture){
        material.setTexture(texture);
    }

    public void setTexture(Texture texture, float reflectance){
        material.setTexture(texture);
        material.setReflectance(reflectance);
    }
}
