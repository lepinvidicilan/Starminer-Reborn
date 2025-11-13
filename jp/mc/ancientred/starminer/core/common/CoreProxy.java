/*    */ package jp.mc.ancientred.starminer.core.common;
/*    */ 
/*    */ import jp.mc.ancientred.starminer.core.entity.EntityLivingGravitized;
/*    */ import jp.mc.ancientred.starminer.core.entity.ExtendedPropertyGravity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class CoreProxy {
/*    */   public void registerNetworkHandler() {}
/*    */   
/*    */   public boolean isOtherPlayer(EntityPlayer player) {
/* 10 */     return true;
/*    */   }
/*    */   
/*    */   public void setFlyMovementByGravity(EntityPlayer entityPlayer) {}
/*    */   
/*    */   public void warnOrKickIllegalGravity(EntityLivingGravitized entityLivingGravitized, ExtendedPropertyGravity gravity) {}
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */