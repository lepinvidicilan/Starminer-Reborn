package net.video_jeu.starminerreborn.gravityapi;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;

public class GravityComponent {
  public static Direction getGravityDirection(LivingEntity living_entity) {
    Gravity entity_gravity = (Gravity) living_entity;
    return entity_gravity.getGravityDirection();
  }

  // public void setGravityDirection(Direction new_gravity_direction) {
  // this.gravity_direction = new_gravity_direction;
  // }
}
