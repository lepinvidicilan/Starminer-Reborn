/*    */ package jp.mc.ancientred.starminer.core.common;
/*    */ 
/*    */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*    */ import jp.mc.ancientred.starminer.core.SMCoreModContainer;
/*    */ import jp.mc.ancientred.starminer.core.entity.EntityLivingGravitized;
/*    */ import jp.mc.ancientred.starminer.core.entity.ExtendedPropertyGravity;
/*    */ import jp.mc.ancientred.starminer.core.packet.SMCorePacketHandlerClient;
/*    */ import jp.mc.ancientred.starminer.core.packet.SMCorePacketHandlerServer;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class CoreProxyClient extends CoreProxy {
/*    */   public void registerNetworkHandler() {
/* 15 */     SMCoreModContainer.coreChannel.register(new SMCorePacketHandlerClient());
/* 16 */     SMCoreModContainer.coreChannel.register(new SMCorePacketHandlerServer());
/*    */   }
/*    */   
/*    */   public boolean isOtherPlayer(EntityPlayer player) {
/* 20 */     return player instanceof net.minecraft.client.entity.EntityOtherPlayerMP;
/*    */   }
/*    */   
/*    */   public void setFlyMovementByGravity(EntityPlayer entityPlayer) {
/* 24 */     if (entityPlayer instanceof EntityPlayerSP) {
/* 25 */       EntityPlayerSP entityPlayerSP = (EntityPlayerSP)entityPlayer;
/* 26 */       if (entityPlayerSP.field_71075_bZ.field_75100_b)
/* 27 */         switch (ExtendedPropertyGravity.getGravityDirection((Entity)entityPlayerSP)) {
/*    */           case southTOnorth_ZN:
/* 29 */             if (entityPlayerSP.field_71158_b.field_78899_d) {
/* 30 */               entityPlayerSP.field_70179_y -= 0.15D;
/* 30 */               entityPlayerSP.field_70181_x += 0.15D;
/*    */             } 
/* 32 */             if (entityPlayerSP.field_71158_b.field_78901_c) {
/* 33 */               entityPlayerSP.field_70179_y += 0.15D;
/* 33 */               entityPlayerSP.field_70181_x -= 0.15D;
/*    */             } 
/*    */             break;
/*    */           case northTOsouth_ZP:
/* 37 */             if (entityPlayerSP.field_71158_b.field_78899_d) {
/* 38 */               entityPlayerSP.field_70179_y += 0.15D;
/* 38 */               entityPlayerSP.field_70181_x += 0.15D;
/*    */             } 
/* 40 */             if (entityPlayerSP.field_71158_b.field_78901_c) {
/* 41 */               entityPlayerSP.field_70179_y -= 0.15D;
/* 41 */               entityPlayerSP.field_70181_x -= 0.15D;
/*    */             } 
/*    */             break;
/*    */           case westTOeast_XP:
/* 45 */             if (entityPlayerSP.field_71158_b.field_78899_d) {
/* 46 */               entityPlayerSP.field_70159_w += 0.15D;
/* 46 */               entityPlayerSP.field_70181_x += 0.15D;
/*    */             } 
/* 48 */             if (entityPlayerSP.field_71158_b.field_78901_c) {
/* 49 */               entityPlayerSP.field_70159_w -= 0.15D;
/* 49 */               entityPlayerSP.field_70181_x -= 0.15D;
/*    */             } 
/*    */             break;
/*    */           case eastTOwest_XN:
/* 53 */             if (entityPlayerSP.field_71158_b.field_78899_d) {
/* 54 */               entityPlayerSP.field_70159_w -= 0.15D;
/* 54 */               entityPlayerSP.field_70181_x += 0.15D;
/*    */             } 
/* 56 */             if (entityPlayerSP.field_71158_b.field_78901_c) {
/* 57 */               entityPlayerSP.field_70159_w += 0.15D;
/* 57 */               entityPlayerSP.field_70181_x -= 0.15D;
/*    */             } 
/*    */             break;
/*    */           case downTOup_YP:
/* 61 */             if (entityPlayerSP.field_71158_b.field_78899_d) {
/* 62 */               entityPlayerSP.field_70181_x += 0.15D;
/* 62 */               entityPlayerSP.field_70181_x += 0.15D;
/*    */             } 
/* 64 */             if (entityPlayerSP.field_71158_b.field_78901_c) {
/* 65 */               entityPlayerSP.field_70181_x -= 0.15D;
/* 65 */               entityPlayerSP.field_70181_x -= 0.15D;
/*    */             } 
/*    */             break;
/*    */         }  
/*    */     } 
/*    */   }
/*    */   
/*    */   public void warnOrKickIllegalGravity(EntityLivingGravitized entityLivingGravitized, ExtendedPropertyGravity gravity) {}
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */