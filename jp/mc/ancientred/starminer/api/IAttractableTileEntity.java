package jp.mc.ancientred.starminer.api;

import net.minecraft.entity.Entity;

public interface IAttractableTileEntity {
  GravityDirection getCurrentGravity(Entity paramEntity);
  
  boolean isStillInAttractedState(Entity paramEntity);
}


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */