/*    */ package jp.mc.ancientred.starminer.core.packet;
/*    */ 
/*    */ import cpw.mods.fml.common.network.internal.FMLProxyPacket;
/*    */ import io.netty.buffer.ByteBufOutputStream;
/*    */ import io.netty.buffer.Unpooled;
/*    */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*    */ import jp.mc.ancientred.starminer.core.SMCoreModContainer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ 
/*    */ public class SMCorePacketSender {
/*    */   public static void sendAttractedChangePacketToPlayer(EntityPlayerMP entityPlayer, boolean attractedState, boolean immidiate, int attractedX, int attractedY, int attractedZ) {
/* 26 */     ByteBufOutputStream bbos = new ByteBufOutputStream(Unpooled.buffer());
/*    */     try {
/* 29 */       bbos.writeInt(0);
/* 30 */       bbos.writeInt(entityPlayer.func_145782_y());
/* 31 */       bbos.writeBoolean(immidiate);
/* 32 */       bbos.writeBoolean(attractedState);
/* 33 */       bbos.writeInt(attractedX);
/* 34 */       bbos.writeInt(attractedY);
/* 35 */       bbos.writeInt(attractedZ);
/* 38 */       FMLProxyPacket thePacket = new FMLProxyPacket(bbos.buffer(), "StarminerCore");
/* 41 */       SMCoreModContainer.coreChannel.sendTo(thePacket, entityPlayer);
/* 43 */     } catch (Exception e) {
/* 44 */       e.printStackTrace();
/*    */     } finally {
/*    */       try {
/* 46 */         bbos.close();
/* 46 */       } catch (Exception ex) {}
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void sendGravityStatePacketToServer(Entity entity, GravityDirection newGravityDirection) {
/* 53 */     ByteBufOutputStream bbos = new ByteBufOutputStream(Unpooled.buffer());
/*    */     try {
/* 56 */       bbos.writeInt(1);
/* 57 */       bbos.writeInt(entity.func_145782_y());
/* 58 */       bbos.writeInt(newGravityDirection.ordinal());
/* 61 */       FMLProxyPacket thePacket = new FMLProxyPacket(bbos.buffer(), "StarminerCore");
/* 64 */       SMCoreModContainer.coreChannel.sendToServer(thePacket);
/* 66 */     } catch (Exception e) {
/* 67 */       e.printStackTrace();
/*    */     } finally {
/*    */       try {
/* 69 */         bbos.close();
/* 69 */       } catch (Exception ex) {}
/*    */     } 
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */