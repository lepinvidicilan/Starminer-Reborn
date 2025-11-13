/*     */ package jp.mc.ancientred.starminer.basics.packet;
/*     */ 
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.network.FMLNetworkEvent;
/*     */ import cpw.mods.fml.common.network.internal.FMLProxyPacket;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.io.IOException;
/*     */ import jp.mc.ancientred.starminer.basics.Config;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.dimention.MapFromSky;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityGravityGenerator;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityNavigator;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.network.NetHandlerPlayClient;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.server.S07PacketRespawn;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class SMPacketHandlerClient {
/*     */   @SubscribeEvent
/*     */   public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent event) throws IOException {
/*  34 */     FMLProxyPacket packet = event.packet;
/*  35 */     String channelName = event.packet.channel();
/*  37 */     NetHandlerPlayClient theNetHandlerPlayClient = (NetHandlerPlayClient)event.handler;
/*  39 */     if (channelName.equals("Starminer")) {
/*  42 */       int packetType = packet.payload().readInt();
/*  45 */       if (packetType == 14 || packetType == 16)
/*  46 */         updateTileEntityOnClient(packetType, packet); 
/*  50 */       if (packetType == 18)
/*  51 */         receiveDimentionRespawnPacket(packet); 
/*  55 */       if (packetType == 12)
/*  56 */         receiveMapFromSky(packet); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void receiveDimentionRespawnPacket(FMLProxyPacket packet) {
/*     */     try {
/*  65 */       EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
/*  66 */       ByteBuf data = packet.payload();
/*  68 */       boolean wasRiddingSquidMob = ((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof jp.mc.ancientred.starminer.basics.entity.EntityStarSquid;
/*  69 */       S07PacketRespawn packetRespawn = new S07PacketRespawn();
/*  70 */       PacketBuffer dummyBuff = new PacketBuffer(data);
/*  71 */       packetRespawn.func_148837_a(dummyBuff);
/*  73 */       ((EntityPlayer)entityClientPlayerMP).field_70137_T = ((EntityPlayer)entityClientPlayerMP).field_70163_u = (packetRespawn.func_149082_c() != 0) ? -10.0D : 288.0D;
/*  75 */       Minecraft.func_71410_x().func_147114_u().func_147280_a(packetRespawn);
/*  78 */       entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
/*  79 */       ((EntityPlayer)entityClientPlayerMP).field_70137_T = ((EntityPlayer)entityClientPlayerMP).field_70163_u = (((EntityPlayer)entityClientPlayerMP).field_70170_p.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider) ? -10.0D : 288.0D;
/*  81 */       SMPacketSender.sendRequestRespawnPlayerLookPos(wasRiddingSquidMob);
/*  83 */     } catch (Exception ex) {
/*  84 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void receiveMapFromSky(FMLProxyPacket packet) {
/*     */     try {
/*  94 */       ByteBuf data = packet.payload();
/*  95 */       Config.enableFakeRotatorOnlyVannilaBlock = data.readBoolean();
/*  96 */       int dataLength = data.readShort();
/*  98 */       if (MapFromSky.skyMapclientData == null) {
/* 100 */         MapFromSky.skyMapclientData = new byte[dataLength];
/*     */       } else {
/* 103 */         MapFromSky.doRecompileSkyMapList = true;
/*     */       } 
/* 105 */       data.readBytes(MapFromSky.skyMapclientData);
/* 106 */       MapFromSky.hasSkyMapImageData = true;
/* 108 */     } catch (Exception ex) {
/* 109 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateTileEntityOnClient(int packetType, FMLProxyPacket packet) {
/* 116 */     ByteBuf data = packet.payload();
/*     */     try {
/*     */       TileEntity tileEntity;
/* 122 */       int x = data.readInt();
/* 123 */       int y = data.readInt();
/* 124 */       int z = data.readInt();
/* 126 */       World world = SMModContainer.proxy.getClientWorld();
/* 128 */       switch (packetType) {
/*     */         case 14:
/* 130 */           tileEntity = world.func_147438_o(x, y, z);
/* 132 */           if (tileEntity != null && tileEntity instanceof TileEntityGravityGenerator) {
/* 133 */             ((TileEntityGravityGenerator)tileEntity).gravityRange = data.readDouble();
/* 134 */             ((TileEntityGravityGenerator)tileEntity).starRad = data.readDouble();
/* 135 */             ((TileEntityGravityGenerator)tileEntity).type = data.readInt();
/*     */           } 
/*     */           break;
/*     */         case 16:
/* 139 */           tileEntity = world.func_147438_o(x, y, z);
/* 141 */           if (tileEntity != null && tileEntity instanceof TileEntityNavigator) {
/* 142 */             ((TileEntityNavigator)tileEntity).lookX = data.readFloat();
/* 143 */             ((TileEntityNavigator)tileEntity).lookY = data.readFloat();
/* 144 */             ((TileEntityNavigator)tileEntity).lookZ = data.readFloat();
/* 145 */             ((TileEntityNavigator)tileEntity).activeTickCount = data.readInt();
/*     */           } 
/*     */           break;
/*     */       } 
/* 149 */     } catch (Exception e) {
/* 150 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */