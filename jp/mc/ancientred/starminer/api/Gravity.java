/*    */ package jp.mc.ancientred.starminer.api;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraftforge.common.IExtendedEntityProperties;
/*    */ 
/*    */ public abstract class Gravity implements IExtendedEntityProperties {
/*    */   public static final String EXTENDED_PROP_GRAVITY_KEY = "starminer.Gravity";
/*    */   
/* 16 */   public GravityDirection gravityDirection = GravityDirection.upTOdown_YN;
/*    */   
/* 19 */   public GravityDirection gravityDirectionNext = GravityDirection.upTOdown_YN;
/*    */   
/* 22 */   public boolean isAttracted = false;
/*    */   
/* 24 */   public int attractedPosX = 0;
/*    */   
/* 26 */   public int attractedPosY = 0;
/*    */   
/* 28 */   public int attractedPosZ = 0;
/*    */   
/* 34 */   public int attractUpdateTickCount = 0;
/*    */   
/* 40 */   public int acceptExceptionalGravityTick = 0;
/*    */   
/*    */   public static Gravity getGravityProp(Entity entity) {
/* 49 */     return (Gravity)entity.getExtendedProperties("starminer.Gravity");
/*    */   }
/*    */   
/*    */   public static final GravityDirection getGravityDirection(Entity entity) {
/* 58 */     Gravity gp = getGravityProp(entity);
/* 59 */     if (gp == null)
/* 59 */       return GravityDirection.upTOdown_YN; 
/* 60 */     return gp.gravityDirection;
/*    */   }
/*    */   
/*    */   public static boolean isEntityZeroGravity(Entity entity) {
/* 69 */     if (entity != null) {
/* 70 */       Gravity gp = getGravityProp(entity);
/* 71 */       return (gp != null && gp.isZeroGravity());
/*    */     } 
/* 73 */     return false;
/*    */   }
/*    */   
/*    */   public abstract boolean isZeroGravity();
/*    */   
/*    */   public abstract boolean isAttracted();
/*    */   
/*    */   public abstract void setAttractedBy(IAttractableTileEntity paramIAttractableTileEntity);
/*    */   
/*    */   public abstract boolean isAttractedBy(IAttractableTileEntity paramIAttractableTileEntity);
/*    */   
/*    */   public abstract void loseAttractedBy();
/*    */   
/*    */   public abstract void setTemporaryGravityDirection(GravityDirection paramGravityDirection, int paramInt);
/*    */   
/*    */   public abstract void setTemporaryZeroGravity(int paramInt);
/*    */   
/*    */   public abstract Vec3 getGravityFixedLook(float paramFloat1, float paramFloat2);
/*    */   
/*    */   public abstract Vec3 getGravityFixedPlayerEyePoz(EntityPlayer paramEntityPlayer, float paramFloat);
/*    */   
/*    */   public abstract void setGravityFixedPlayerShootVec(EntityPlayer paramEntityPlayer, Entity paramEntity, float paramFloat);
/*    */   
/*    */   public abstract void setResistInOpaqueBlockDamegeTick(int paramInt);
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */