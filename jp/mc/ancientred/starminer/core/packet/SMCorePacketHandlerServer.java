/*    */ package jp.mc.ancientred.starminer.core.packet;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.common.network.FMLNetworkEvent;
/*    */ import cpw.mods.fml.common.network.NetworkRegistry;
/*    */ import cpw.mods.fml.common.network.internal.FMLProxyPacket;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.IOException;
/*    */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*    */ import jp.mc.ancientred.starminer.core.SMCoreModContainer;
/*    */ import jp.mc.ancientred.starminer.core.entity.ExtendedPropertyGravity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.network.NetHandlerPlayServer;
/*    */ 
/*    */ public class SMCorePacketHandlerServer {
/*    */   @SubscribeEvent
/*    */   public void onServerPacket(FMLNetworkEvent.ServerCustomPacketEvent event) throws IOException {
/* 26 */     FMLProxyPacket packet = event.packet;
/* 27 */     String channelName = event.packet.channel();
/* 29 */     NetHandlerPlayServer theNetHandlerPlayServer = (NetHandlerPlayServer)event.handler;
/* 30 */     EntityPlayerMP thePlayer = theNetHandlerPlayServer.field_147369_b;
/* 32 */     if (channelName.equals("StarminerCore")) {
/* 35 */       int packetType = packet.payload().readInt();
/* 38 */       if (packetType == 1)
/* 39 */         receiveGravityStateChangeOnServer(packet, thePlayer); 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void receiveGravityStateChangeOnServer(FMLProxyPacket packet, EntityPlayerMP player) {
/*    */     try {
/* 49 */       ByteBuf data = packet.payload();
/* 50 */       EntityPlayerMP entityPlayerMP = player;
/* 52 */       int entityId = data.readInt();
/* 53 */       int gravityDirectionInt = data.readInt();
/* 55 */       if (entityPlayerMP.func_145782_y() == entityId) {
/* 57 */         ExtendedPropertyGravity gravity = ExtendedPropertyGravity.getExtendedPropertyGravity((Entity)entityPlayerMP);
/* 60 */         GravityDirection old = gravity.gravityDirection;
/* 63 */         gravity.gravityDirection = GravityDirection.values()[gravityDirectionInt];
/* 65 */         if (old != gravity.gravityDirection) {
/* 68 */           entityPlayerMP.func_70107_b(((EntityPlayer)entityPlayerMP).field_70165_t, ((EntityPlayer)entityPlayerMP).field_70163_u, ((EntityPlayer)entityPlayerMP).field_70161_v);
/* 71 */           NetworkRegistry.TargetPoint targetPoint = new NetworkRegistry.TargetPoint(((EntityPlayer)entityPlayerMP).field_70170_p.field_73011_w.field_76574_g, ((EntityPlayer)entityPlayerMP).field_70165_t, ((EntityPlayer)entityPlayerMP).field_70163_u, ((EntityPlayer)entityPlayerMP).field_70161_v, 50.0D);
/* 72 */           SMCoreModContainer.coreChannel.sendToAllAround(packet, targetPoint);
/*    */         } 
/*    */       } 
/* 76 */     } catch (Exception e) {
/* 77 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */