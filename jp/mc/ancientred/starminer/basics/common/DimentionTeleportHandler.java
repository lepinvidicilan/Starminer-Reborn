/*     */ package jp.mc.ancientred.starminer.basics.common;
/*     */ 
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import java.util.Iterator;
/*     */ import jp.mc.ancientred.starminer.basics.Config;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.dimention.TeleporterForSpace;
/*     */ import jp.mc.ancientred.starminer.basics.packet.SMPacketSender;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityGravityGenerator;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.play.server.S1DPacketEntityEffect;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.management.ServerConfigurationManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.Teleporter;
/*     */ import net.minecraft.world.WorldServer;
/*     */ 
/*     */ public class DimentionTeleportHandler {
/*     */   public boolean onUpdateEntityEnd(EntityPlayerMP player) {
/*  28 */     boolean teleported = false;
/*  29 */     if (player.field_71093_bK == SMModContainer.GeostationaryOrbitDimentionId) {
/*  31 */       if (player.field_70163_u < -20.0D) {
/*  32 */         if (!player.func_70045_F() && !isRidingEntityImmuneToFire(player)) {
/*  33 */           player.func_70097_a(DamageSource.field_76372_a, 4.0F);
/*  34 */           player.func_70015_d(10);
/*     */         } 
/*  36 */         if (player.field_70163_u < -64.0D) {
/*  37 */           player.field_71088_bW = player.func_82147_ab();
/*  38 */           player.field_70137_T = player.field_70163_u = 288.0D;
/*  39 */           transferPlayerToDimention(player, 0);
/*  40 */           teleported = true;
/*     */         } 
/*  42 */       } else if (player.field_70163_u > 384.0D) {
/*  44 */         player.func_70097_a(DamageSource.field_76380_i, 4.0F);
/*     */       } 
/*  47 */     } else if (player.field_71093_bK == 0) {
/*  49 */       if (player.field_70163_u > 288.0D && (
/*  50 */         Config.ticketFreeForTeleport || TileEntityGravityGenerator.isGravityReverse((Entity)player, true))) {
/*  51 */         player.field_71088_bW = player.func_82147_ab();
/*  52 */         player.field_70137_T = player.field_70163_u = -10.0D;
/*  53 */         transferPlayerToDimention(player, SMModContainer.GeostationaryOrbitDimentionId);
/*  54 */         teleported = true;
/*     */       } 
/*     */     } 
/*  58 */     return teleported;
/*     */   }
/*     */   
/*     */   private boolean isRidingEntityImmuneToFire(EntityPlayerMP player) {
/*  64 */     return (player.field_70154_o != null && player.field_70154_o.func_70045_F());
/*     */   }
/*     */   
/*     */   public void transferPlayerToDimention(EntityPlayerMP player, int dstDimentionId) {
/*  74 */     if (!player.field_70170_p.field_72995_K && !player.field_70128_L) {
/*  80 */       boolean isRidingSquidMob = false;
/*  81 */       if (player.func_70115_ae()) {
/*  82 */         isRidingSquidMob = player.field_70154_o instanceof jp.mc.ancientred.starminer.basics.entity.EntityStarSquid;
/*  83 */         double motionX = player.field_70154_o.field_70159_w;
/*  84 */         double motionY = player.field_70154_o.field_70181_x;
/*  85 */         double motionZ = player.field_70154_o.field_70179_y;
/*  86 */         player.field_70154_o.field_70153_n = null;
/*  87 */         player.field_70154_o = null;
/*     */       } else {
/*  89 */         double motionX = player.field_70159_w;
/*  90 */         double motionY = player.field_70181_x;
/*  91 */         double motionZ = player.field_70179_y;
/*     */       } 
/*  94 */       WorldServer worldserverSrc = player.field_71133_b.func_71218_a(player.field_71093_bK);
/*  95 */       WorldServer worldserverDst = player.field_71133_b.func_71218_a(dstDimentionId);
/*  96 */       TeleporterForSpace teleporter = new TeleporterForSpace(worldserverDst);
/*  99 */       transferPlayerToDimensionPrivate(player, dstDimentionId, (Teleporter)teleporter, isRidingSquidMob);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transferPlayerToDimensionPrivate(EntityPlayerMP player, int dstDimId, Teleporter teleporter, boolean isRidingSquidMob) {
/* 110 */     MinecraftServer mcServer = player.field_71133_b;
/* 111 */     ServerConfigurationManager serverConfigurationManager = mcServer.func_71203_ab();
/* 113 */     int srcDimId = player.field_71093_bK;
/* 114 */     WorldServer worldserver = mcServer.func_71218_a(player.field_71093_bK);
/* 115 */     player.field_71093_bK = dstDimId;
/* 116 */     WorldServer worldserver1 = mcServer.func_71218_a(player.field_71093_bK);
/* 119 */     player.field_71135_a.func_147364_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_70177_z, player.field_70125_A);
/* 123 */     SMPacketSender.sendRespawnPacketToPlayer(player, isRidingSquidMob, player.field_71093_bK, player.field_70170_p.field_73013_u, player.field_70170_p.func_72912_H().func_76067_t(), player.field_71134_c.func_73081_b());
/* 126 */     player.field_71135_a.func_147364_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_70177_z, player.field_70125_A);
/* 128 */     worldserver.func_72973_f((Entity)player);
/* 129 */     player.field_70128_L = false;
/* 130 */     serverConfigurationManager.transferEntityToWorld((Entity)player, srcDimId, worldserver, worldserver1, teleporter);
/* 131 */     serverConfigurationManager.func_72375_a(player, worldserver);
/* 133 */     player.field_71134_c.func_73080_a(worldserver1);
/* 134 */     serverConfigurationManager.func_72354_b(player, worldserver1);
/* 135 */     serverConfigurationManager.func_72385_f(player);
/* 136 */     Iterator<PotionEffect> iterator = player.func_70651_bq().iterator();
/* 138 */     while (iterator.hasNext()) {
/* 140 */       PotionEffect potioneffect = iterator.next();
/* 141 */       player.field_71135_a.func_147359_a(new S1DPacketEntityEffect(player.func_145782_y(), potioneffect));
/*     */     } 
/* 143 */     FMLCommonHandler.instance().firePlayerChangedDimensionEvent((EntityPlayer)player, srcDimId, dstDimId);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */