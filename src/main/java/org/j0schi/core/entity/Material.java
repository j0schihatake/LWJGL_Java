package org.j0schi.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.j0schi.core.config.Config;
import org.joml.Vector4f;

@Data
@AllArgsConstructor
public class Material {

    private Vector4f ambientColour, diffuseColour, specularColour;
    private float reflectance;
    private Texture texture;

    public Material(){
        this.ambientColour = Config.DEFAULT_COLOUR;
        this.diffuseColour = Config.DEFAULT_COLOUR;
        this.specularColour = Config.DEFAULT_COLOUR;
        this.texture = null;
        this.reflectance = 0;
    }

    public Material(Vector4f colour, float reflectance){
        this(colour, colour, colour, reflectance, null);
    }

    public Material(Vector4f colour, float reflectance, Texture texture){
        this(colour, colour, colour, reflectance, texture);
    }

    public Material(Texture texture){
        this(Config.DEFAULT_COLOUR, Config.DEFAULT_COLOUR, Config.DEFAULT_COLOUR, 0, texture);
    }

    public boolean hasTexture(){
        return  this.texture != null;
    }
}
