package net.video_jeu.starminerreborn.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

@Mixin(Entity.class)
public abstract class ApplyGravity {
  public Vec3 deltaMovement;

  public void setDeltaMovement(Vec3 deltaMovement) {
    System.out.println("Oui !!");

    this.deltaMovement = new Vec3(90F, 90F, 90F);
  }

}
