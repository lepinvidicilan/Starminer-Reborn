/*     */ package jp.mc.ancientred.starminer.basics.packet;
/*     */ 
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.network.FMLNetworkEvent;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.internal.FMLProxyPacket;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.io.IOException;
/*     */ import jp.mc.ancientred.starminer.api.Gravity;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.SMReflectionHelper;
/*     */ import jp.mc.ancientred.starminer.basics.entity.EntityStarSquid;
/*     */ import jp.mc.ancientred.starminer.basics.gui.ContainerStarCore;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ import net.minecraft.network.play.server.S12PacketEntityVelocity;
/*     */ 
/*     */ public class SMPacketHandlerServer {
/*     */   @SubscribeEvent
/*     */   public void onServerPacket(FMLNetworkEvent.ServerCustomPacketEvent event) throws IOException {
/*  31 */     FMLProxyPacket packet = event.packet;
/*  32 */     String channelName = event.packet.channel();
/*  34 */     NetHandlerPlayServer theNetHandlerPlayServer = (NetHandlerPlayServer)event.handler;
/*  35 */     EntityPlayerMP thePlayer = theNetHandlerPlayServer.field_147369_b;
/*  37 */     if (channelName.equals("Starminer")) {
/*  40 */       int packetType = packet.payload().readInt();
/*  43 */       if (packetType == 10)
/*  45 */         if (thePlayer != null && thePlayer.field_71070_bA instanceof ContainerStarCore) {
/*  48 */           ContainerStarCore container = (ContainerStarCore)thePlayer.field_71070_bA;
/*  49 */           container.receiveButtonAction(packet.payload().readInt());
/*     */         }  
/*  54 */       if (packetType == 20)
/*  55 */         receiveRequestMountMobRespawn(packet, thePlayer); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void receiveRequestMountMobRespawn(FMLProxyPacket packet, EntityPlayerMP player) {
/*     */     try {
/*  64 */       ByteBuf data = packet.payload();
/*  65 */       boolean isMounting = data.readBoolean();
/*  66 */       double motionY = (player.field_70170_p.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider) ? 0.10000000149011612D : 0.0D;
/*  67 */       player.field_70137_T = player.field_70163_u = (player.field_70170_p.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider) ? -10.0D : 288.0D;
/*  70 */       player.field_71135_a.func_147364_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_70126_B, player.field_70127_C);
/*  72 */       if (isMounting) {
/*  74 */         EntityStarSquid rideMob = new EntityStarSquid(player.field_70170_p, true);
/*  75 */         rideMob.func_70012_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_70177_z, player.field_70125_A);
/*  76 */         rideMob.setToBeRidByEntityIdTemp(player.func_145782_y());
/*  77 */         player.field_70170_p.func_72838_d((Entity)rideMob);
/*  78 */         player.func_70078_a((Entity)rideMob);
/*  79 */         SMReflectionHelper.ignoreHasMovedFlg((EntityPlayer)player);
/*  81 */         rideMob.field_70181_x = motionY;
/*  82 */         player.field_71135_a.func_147359_a(new S12PacketEntityVelocity((Entity)rideMob));
/*     */       } else {
/*  85 */         player.field_70181_x = motionY;
/*  86 */         player.field_71135_a.func_147359_a(new S12PacketEntityVelocity((Entity)player));
/*     */       } 
/*  89 */     } catch (Exception e) {
/*  90 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void receiveGravityStateChangeOnServer(FMLProxyPacket packet, EntityPlayerMP player) {
/*     */     try {
/*  98 */       ByteBuf data = packet.payload();
/*  99 */       int entityId = data.readInt();
/* 100 */       int gravityDirectionInt = data.readInt();
/* 102 */       if (player.func_145782_y() == entityId) {
/* 104 */         Gravity gravity = Gravity.getGravityProp((Entity)player);
/* 107 */         GravityDirection old = gravity.gravityDirection;
/* 110 */         gravity.gravityDirection = GravityDirection.values()[gravityDirectionInt];
/* 112 */         if (old != gravity.gravityDirection) {
/* 115 */           player.func_70107_b(player.field_70165_t, player.field_70163_u, player.field_70161_v);
/* 118 */           NetworkRegistry.TargetPoint targetPoint = new NetworkRegistry.TargetPoint(player.field_70170_p.field_73011_w.field_76574_g, player.field_70165_t, player.field_70163_u, player.field_70161_v, 50.0D);
/* 119 */           SMModContainer.channel.sendToAllAround(packet, targetPoint);
/*     */         } 
/*     */       } 
/* 123 */     } catch (Exception e) {
/* 124 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */