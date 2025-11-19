package net.video_jeu.starminerreborn.utils;

import net.minecraft.core.Vec3i;
import net.minecraft.world.phys.Vec3;

public class Utils{
    public static Vec3 vec3i_to_vec3(Vec3i vec3i){
        return new Vec3(vec3i.getX(), vec3i.getY(), vec3i.getZ());
    }


}
