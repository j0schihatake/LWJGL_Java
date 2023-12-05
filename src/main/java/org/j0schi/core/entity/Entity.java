package org.j0schi.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joml.Vector3f;

@Data
@AllArgsConstructor
public class Entity {
    private Model model;
    private Vector3f pos, rotation;
    private float scale;

    public void incPos(float x, float y, float z){
        this.pos.x += x;
        this.pos.y += y;
        this.pos.z += z;
    }

    public void setPos(float x, float y, float z){
        this.pos.x = x;
        this.pos.y = y;
        this.pos.z = z;
    }

    public void incRotation(float x, float y, float z){
        this.rotation.x += x;
        this.rotation.y += y;
        this.rotation.z += z;
    }

    public void setRotation(float x, float y, float z){
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }
}
