/*    */ package jp.mc.ancientred.starminer.core.common;
/*    */ 
/*    */ import cpw.mods.fml.server.FMLServerHandler;
/*    */ import jp.mc.ancientred.starminer.core.CoreConfig;
/*    */ import jp.mc.ancientred.starminer.core.SMCoreModContainer;
/*    */ import jp.mc.ancientred.starminer.core.entity.EntityLivingGravitized;
/*    */ import jp.mc.ancientred.starminer.core.entity.ExtendedPropertyGravity;
/*    */ import jp.mc.ancientred.starminer.core.packet.SMCorePacketHandlerServer;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ 
/*    */ public class CoreProxyServer extends CoreProxy {
/*    */   public void registerNetworkHandler() {
/* 15 */     SMCoreModContainer.coreChannel.register(new SMCorePacketHandlerServer());
/*    */   }
/*    */   
/*    */   public boolean isOtherPlayer(EntityPlayer player) {
/* 19 */     return true;
/*    */   }
/*    */   
/*    */   public void setFlyMovementByGravity(EntityPlayer entityPlayer) {}
/*    */   
/*    */   public void warnOrKickIllegalGravity(EntityLivingGravitized entityLivingGravitized, ExtendedPropertyGravity gravity) {
/* 27 */     if (FMLServerHandler.instance() != null) {
/* 28 */       if (CoreConfig.showUnsynchronizedWarning)
/* 31 */         FMLServerHandler.instance().getServer().func_71236_h(((EntityPlayerMP)entityLivingGravitized).func_70005_c_() + " had gravity unsynchronization long time!"); 
/* 34 */       if (CoreConfig.unsynchronizedWarnToKick != 0 && gravity.unsynchronizedWarnCount >= CoreConfig.unsynchronizedWarnToKick) {
/* 36 */         FMLServerHandler.instance().getServer().func_71236_h(((EntityPlayerMP)entityLivingGravitized).func_70005_c_() + " was kicked for gravity unsynchronization for far long time!");
/* 38 */         ((EntityPlayerMP)entityLivingGravitized).field_71135_a.func_147360_c("You had too long time gravity unsynchronized with server!!!");
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */