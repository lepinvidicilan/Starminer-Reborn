/*     */ package jp.mc.ancientred.starminer.core.packet;
/*     */ 
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.network.FMLNetworkEvent;
/*     */ import cpw.mods.fml.common.network.internal.FMLProxyPacket;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.io.IOException;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.core.entity.ExtendedPropertyGravity;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.network.NetHandlerPlayClient;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class SMCorePacketHandlerClient {
/*     */   @SubscribeEvent
/*     */   public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent event) throws IOException {
/*  27 */     FMLProxyPacket packet = event.packet;
/*  28 */     String channelName = event.packet.channel();
/*  30 */     NetHandlerPlayClient theNetHandlerPlayClient = (NetHandlerPlayClient)event.handler;
/*  32 */     if (channelName.equals("StarminerCore")) {
/*  35 */       int packetType = packet.payload().readInt();
/*  38 */       if (packetType == 0)
/*  39 */         receiveAttractPacketOnClient(packet); 
/*  44 */       if (packetType == 1)
/*  45 */         receiveGravityStateChangeOnClient(packet); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void receiveAttractPacketOnClient(FMLProxyPacket packet) {
/*     */     try {
/*  54 */       ByteBuf data = packet.payload();
/*  55 */       EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
/*  57 */       int entityId = data.readInt();
/*  58 */       boolean immidiate = data.readBoolean();
/*  59 */       boolean attractedState = data.readBoolean();
/*  60 */       int attractedX = data.readInt();
/*  61 */       int attractedY = data.readInt();
/*  62 */       int attractedZ = data.readInt();
/*  64 */       if (entityClientPlayerMP.func_145782_y() == entityId) {
/*  66 */         Entity entity = ((EntityPlayer)entityClientPlayerMP).field_70170_p.func_73045_a(entityId);
/*  67 */         if (entity != null && entity == entityClientPlayerMP) {
/*  68 */           ExtendedPropertyGravity gravity = ExtendedPropertyGravity.getExtendedPropertyGravity((Entity)entityClientPlayerMP);
/*  71 */           if (attractedState) {
/*  72 */             gravity.isAttracted = true;
/*  73 */             gravity.attractedPosX = attractedX;
/*  74 */             gravity.attractedPosY = attractedY;
/*  75 */             gravity.attractedPosZ = attractedZ;
/*  77 */             gravity.changeGravityImmidiate = immidiate;
/*     */           } else {
/*  79 */             gravity.isAttracted = false;
/*  80 */             gravity.attractedPosX = 0;
/*  81 */             gravity.attractedPosY = 0;
/*  82 */             gravity.attractedPosZ = 0;
/*     */           } 
/*     */         } 
/*     */       } 
/*  86 */     } catch (Exception ex) {
/*  87 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void receiveGravityStateChangeOnClient(FMLProxyPacket packet) {
/*     */     try {
/*  95 */       ByteBuf data = packet.payload();
/*  96 */       EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
/*  98 */       int entityId = data.readInt();
/*  99 */       int gravityDirectionInt = data.readInt();
/* 101 */       if (entityClientPlayerMP.func_145782_y() != entityId) {
/* 103 */         World world = ((EntityPlayer)entityClientPlayerMP).field_70170_p;
/* 104 */         if (world == null)
/*     */           return; 
/* 105 */         Entity entity = world.func_73045_a(entityId);
/* 106 */         if (entity == null)
/*     */           return; 
/* 107 */         ExtendedPropertyGravity gravity = ExtendedPropertyGravity.getExtendedPropertyGravity(entity);
/* 108 */         if (gravity == null)
/*     */           return; 
/* 111 */         GravityDirection old = gravity.gravityDirection;
/* 114 */         gravity.gravityDirection = GravityDirection.values()[gravityDirectionInt];
/* 116 */         if (old != gravity.gravityDirection)
/* 119 */           entity.func_70107_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v); 
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