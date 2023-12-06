package org.j0schi.core.lighting;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joml.Vector3f;

@Data
@AllArgsConstructor
public class DirectionalLight {

    private Vector3f colour, direction;

    private float intensity;
}