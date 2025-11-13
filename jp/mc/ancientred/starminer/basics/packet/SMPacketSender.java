/*     */ package jp.mc.ancientred.starminer.basics.packet;
/*     */ 
/*     */ import cpw.mods.fml.common.network.internal.FMLProxyPacket;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.ByteBufOutputStream;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import jp.mc.ancientred.starminer.basics.Config;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.dimention.MapFromSky;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.server.S07PacketRespawn;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ import net.minecraft.world.WorldType;
/*     */ 
/*     */ public class SMPacketSender {
/*     */   public static void sendRequestRespawnPlayerLookPos(boolean isRidingMob) {
/*     */     ByteBuf buf;
/*  28 */     PacketBuffer buffef = new PacketBuffer(buf = Unpooled.buffer());
/*     */     try {
/*  32 */       buffef.writeInt(20);
/*  33 */       buffef.writeBoolean(isRidingMob);
/*  36 */       FMLProxyPacket thePacket = new FMLProxyPacket(buf, "Starminer");
/*  39 */       SMModContainer.channel.sendToServer(thePacket);
/*  41 */     } catch (Exception e) {
/*  42 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendRespawnPacketToPlayer(EntityPlayerMP entityPlayer, boolean isRidingSquidMob, int dimentionId, EnumDifficulty difficulty, WorldType worldType, WorldSettings.GameType gameType) {
/*     */     ByteBuf buf;
/*  55 */     PacketBuffer buffef = new PacketBuffer(buf = Unpooled.buffer());
/*     */     try {
/*  58 */       buffef.writeInt(18);
/*  60 */       S07PacketRespawn packetRespawn = new S07PacketRespawn(dimentionId, difficulty, worldType, gameType);
/*  61 */       packetRespawn.func_148840_b(buffef);
/*  64 */       FMLProxyPacket thePacket = new FMLProxyPacket(buf, "Starminer");
/*  67 */       SMModContainer.channel.sendTo(thePacket, entityPlayer);
/*  69 */     } catch (Exception e) {
/*  70 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendSkyMapPacketToPlayer(EntityPlayerMP entityPlayer) {
/*  77 */     ByteBufOutputStream bbos = new ByteBufOutputStream(Unpooled.buffer());
/*     */     try {
/*  80 */       bbos.writeInt(12);
/*  81 */       bbos.writeBoolean(Config.enableFakeRotatorOnlyVannilaBlock);
/*  82 */       bbos.writeShort(MapFromSky.mapDataFromSky.field_76198_e.length);
/*  83 */       bbos.write(MapFromSky.mapDataFromSky.field_76198_e);
/*  86 */       FMLProxyPacket thePacket = new FMLProxyPacket(bbos.buffer(), "Starminer");
/*  89 */       SMModContainer.channel.sendTo(thePacket, entityPlayer);
/*  91 */     } catch (Exception e) {
/*  92 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/*  94 */         bbos.close();
/*  94 */       } catch (Exception ex) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Packet createGUIActPacket(int data) {
/* 101 */     ByteBufOutputStream bbos = new ByteBufOutputStream(Unpooled.buffer());
/*     */     try {
/* 104 */       bbos.writeInt(10);
/* 105 */       bbos.writeInt(data);
/* 108 */       FMLProxyPacket thePacket = new FMLProxyPacket(bbos.buffer(), "Starminer");
/* 110 */       return (Packet)thePacket;
/* 111 */     } catch (Exception e) {
/* 112 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 114 */         bbos.close();
/* 114 */       } catch (Exception ex) {}
/*     */     } 
/* 116 */     return null;
/*     */   }
/*     */   
/*     */   public static Packet createTEGcoreDescriptionPacket(int xCoord, int yCoord, int zCoord, double gravityRange, double starRad, int type) {
/* 123 */     ByteBufOutputStream bbos = new ByteBufOutputStream(Unpooled.buffer());
/*     */     try {
/* 126 */       bbos.writeInt(14);
/* 127 */       bbos.writeInt(xCoord);
/* 128 */       bbos.writeInt(yCoord);
/* 129 */       bbos.writeInt(zCoord);
/* 130 */       bbos.writeDouble(gravityRange);
/* 131 */       bbos.writeDouble(starRad);
/* 132 */       bbos.writeInt(type);
/* 135 */       FMLProxyPacket thePacket = new FMLProxyPacket(bbos.buffer(), "Starminer");
/* 137 */       return (Packet)thePacket;
/* 138 */     } catch (Exception e) {
/* 139 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 141 */         bbos.close();
/* 141 */       } catch (Exception ex) {}
/*     */     } 
/* 143 */     return null;
/*     */   }
/*     */   
/*     */   public static Packet createTENaviDescriptionPacket(int xCoord, int yCoord, int zCoord, float lookX, float lookY, float lookZ, int activeTickCount) {
/* 151 */     ByteBufOutputStream bbos = new ByteBufOutputStream(Unpooled.buffer());
/*     */     try {
/* 154 */       bbos.writeInt(16);
/* 155 */       bbos.writeInt(xCoord);
/* 156 */       bbos.writeInt(yCoord);
/* 157 */       bbos.writeInt(zCoord);
/* 158 */       bbos.writeFloat(lookX);
/* 159 */       bbos.writeFloat(lookY);
/* 160 */       bbos.writeFloat(lookZ);
/* 161 */       bbos.writeInt(activeTickCount);
/* 164 */       FMLProxyPacket thePacket = new FMLProxyPacket(bbos.buffer(), "Starminer");
/* 166 */       return (Packet)thePacket;
/* 167 */     } catch (Exception e) {
/* 168 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 170 */         bbos.close();
/* 170 */       } catch (Exception ex) {}
/*     */     } 
/* 172 */     return null;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */