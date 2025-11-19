package net.video_jeu.starminerreborn.gravityapi;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class RotationUtils {

    // from https://github.com/qouteall/GravityChanger/tree/1.20.4
    public static Vec3 coordWorldToPlayer(double x, double y, double z, Direction gravity_direction) {
        return vecWorldToPlayer(new Vec3(x, y, z), gravity_direction);
    }

    public static Vec3 vecWorldToPlayer(Vec3 pos, Direction gravity_direction) {
        double x = pos.x();
        double y = pos.y();
        double z = pos.z();
        return switch (gravity_direction) {
            case DOWN -> new Vec3(x, y, z);
            case UP -> new Vec3(x, -y, z);
            case NORTH -> new Vec3(x, z, -y);
            case SOUTH -> new Vec3(-x, -z, -y);
            case WEST -> new Vec3(-z, x, -y);
            case EAST -> new Vec3(z, -x, -y);
        };
    }
}
