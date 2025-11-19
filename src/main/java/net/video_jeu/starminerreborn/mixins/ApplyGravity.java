package net.video_jeu.starminerreborn.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import net.video_jeu.starminerreborn.gravityapi.Gravity;
import net.video_jeu.starminerreborn.gravityapi.GravityComponent;
import net.video_jeu.starminerreborn.gravityapi.RotationUtils;

@Mixin(Entity.class)
public abstract class ApplyGravity implements Gravity {
  private Direction gravity_direction = Direction.DOWN;
  public Vec3 deltaMovement;

  public Direction getGravityDirection() {
    return this.gravity_direction;
  }

  public void setDeltaMovement(Vec3 deltaMovement) {
    System.out.println(deltaMovement);
    Vec3 newDeltaMovement = RotationUtils.vecWorldToPlayer(deltaMovement, this.getGravityDirection());
    System.out.println(newDeltaMovement.x() + "," + newDeltaMovement.y() + "," + newDeltaMovement.z());

    this.deltaMovement = newDeltaMovement;
  }

  // transform move vector from local to world (the velocity is local)
  @ModifyVariable(method = "Lnet/minecraft/world/entity/Entity;move(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
  private Vec3 modify_move_Vec3d_0_0(Vec3 vec3d) {
    return RotationUtils.vecWorldToPlayer(vec3d, this.getGravityDirection());
  }

  public void setDeltaMovement(double x, double y, double z) {
    this.setDeltaMovement(new Vec3(x, y, z));
  }

  //
  public boolean setGravityDirection(Direction new_gravity_direction) {
    this.gravity_direction = new_gravity_direction;
    return true;
  }
  //
  // public Vec3 gravityMovement(Vec3 deltaMovement) {
  // Vec3i vec3i = this.gravity_direction.DOWN.getNormal();
  // return deltaMovement.multiply(Utils.vec3i_to_vec3(vec3i));
  //
  // }
  // from https://github.com/qouteall/GravityChanger/tree/1.20.4
  // @WrapOperation(method = "tick", at = @At(value = "INVOKE", target =
  // "Lnet/minecraft/world/entity/LivingEntity;getX()D", ordinal = 0))
  // private double wrapOperation_tick_getX_0(LivingEntity livingEntity,
  // Operation<Double> original) {
  // Direction gravityDirection =
  // GravityComponent.getGravityDirection(livingEntity);
  // if (gravityDirection == Direction.DOWN) {
  // return original.call(livingEntity);
  // }
  //
  // return RotationUtils.vecWorldToPlayer(original.call(livingEntity) -
  // livingEntity.xo,
  // livingEntity.getY() - livingEntity.yo, livingEntity.getZ() - livingEntity.zo,
  // gravityDirection).x
  // + livingEntity.xo;
  // }
  //
  // @WrapOperation(method = "tick", at = @At(value = "INVOKE", target =
  // "Lnet/minecraft/world/entity/LivingEntity;getZ()D", ordinal = 0))
  // private double wrapOperation_tick_getZ_0(LivingEntity livingEntity,
  // Operation<Double> original) {
  // Direction gravityDirection =
  // GravityComponent.getGravityDirection(livingEntity);
  // if (gravityDirection == Direction.DOWN) {
  // return original.call(livingEntity);
  // }
  //
  // return RotationUtils.vecWorldToPlayer(livingEntity.getX() - livingEntity.xo,
  // livingEntity.getY() - livingEntity.yo,
  // original.call(livingEntity) - livingEntity.zo, gravityDirection).z +
  // livingEntity.zo;
  // }
  // protected void applyGravity() {
  // if (this.gravity_direction == null){
  // this.gravity_direction = "DOWN";
  // }
  // System.out.println(this.gravity_direction);
  // switch (this.gravity_direction) {
  // case "DOWN":
  // this.setDeltaMovement(new Vec3(0.0, -1.0, 0.0));
  // System.out.println(-1.0);
  // case "UP":
  // this.setDeltaMovement(new Vec3(0.0, 1.0, 0.0));
  // System.out.println(1.0);
  // }
  // }
}
