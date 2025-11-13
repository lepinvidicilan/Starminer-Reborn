/*     */ package jp.mc.ancientred.starminer.basics.common;
/*     */ 
/*     */ import jp.mc.ancientred.starminer.api.IRotateSleepingViewHandler;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.block.DirectionConst;
/*     */ import jp.mc.ancientred.starminer.basics.block.bed.BlockStarBedHead;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class SleepingViewHandler implements IRotateSleepingViewHandler {
/*     */   public boolean rotateSleepingFPView() {
/*  19 */     EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
/*  21 */     WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
/*  22 */     ChunkCoordinates chunkcoordinates = ((EntityPlayer)entityClientPlayerMP).field_71081_bT;
/*  23 */     Block block = worldClient.func_147439_a(chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c);
/*  24 */     if (block == SMModContainer.StarBedHeadBlock) {
/*  25 */       int conDir = ((BlockStarBedHead)block).getConnectionDirection((IBlockAccess)worldClient, chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c);
/*  26 */       if (conDir == -1)
/*  26 */         return true; 
/*  27 */       conDir = DirectionConst.OPPOSITE_CNV[conDir];
/*  28 */       int gravDir = ((BlockStarBedHead)block).getGravityDirection((IBlockAccess)worldClient, chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c);
/*  30 */       float translateFixX = 0.0F;
/*  31 */       float translateFixY = 0.0F;
/*  32 */       float translateFixZ = 0.0F;
/*  34 */       switch (gravDir) {
/*     */         case 3:
/*  37 */           GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*  38 */           GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/*  39 */           switch (conDir) {
/*     */             case 0:
/*  43 */               GL11.glRotatef(-180.0F, 1.0F, 0.0F, 0.0F);
/*     */               break;
/*     */             case 5:
/*  46 */               GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*     */               break;
/*     */             case 4:
/*  49 */               GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*     */               break;
/*     */           } 
/*  53 */           GL11.glTranslatef(0.5F, 0.0F, 0.0F);
/*     */           break;
/*     */         case 2:
/*  56 */           GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  57 */           GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*  59 */           switch (conDir) {
/*     */             case 0:
/*  63 */               GL11.glRotatef(-180.0F, 1.0F, 0.0F, 0.0F);
/*     */               break;
/*     */             case 5:
/*  66 */               GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*     */               break;
/*     */             case 4:
/*  69 */               GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*     */               break;
/*     */           } 
/*  73 */           GL11.glTranslatef(-0.5F, 0.0F, 0.0F);
/*     */           break;
/*     */         case 5:
/*  76 */           GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*  77 */           switch (conDir) {
/*     */             case 3:
/*  79 */               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*     */               break;
/*     */             case 2:
/*  82 */               GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/*     */               break;
/*     */             case 0:
/*  87 */               GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
/*     */               break;
/*     */           } 
/*  91 */           GL11.glTranslatef(0.0F, 0.0F, 0.5F);
/*     */           break;
/*     */         case 4:
/*  94 */           GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*  95 */           GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  96 */           switch (conDir) {
/*     */             case 3:
/*  98 */               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*     */               break;
/*     */             case 2:
/* 101 */               GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/*     */               break;
/*     */             case 0:
/* 106 */               GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
/*     */               break;
/*     */           } 
/* 110 */           GL11.glTranslatef(0.0F, 0.0F, -0.5F);
/*     */           break;
/*     */         case 1:
/* 114 */           GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/* 115 */           switch (conDir) {
/*     */             case 3:
/* 117 */               GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*     */               break;
/*     */             case 2:
/* 120 */               GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*     */               break;
/*     */             case 5:
/* 123 */               GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */               break;
/*     */           } 
/* 129 */           GL11.glTranslatef(0.0F, 0.5F, 0.0F);
/*     */           break;
/*     */         case 0:
/* 132 */           switch (conDir) {
/*     */             case 3:
/* 134 */               GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/*     */               break;
/*     */             case 2:
/* 137 */               GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*     */               break;
/*     */             case 4:
/* 142 */               GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */               break;
/*     */           } 
/* 146 */           GL11.glTranslatef(0.0F, -0.5F, 0.0F);
/*     */           break;
/*     */       } 
/* 150 */       return true;
/*     */     } 
/* 153 */     return false;
/*     */   }
/*     */   
/*     */   public boolean rotateTPPlayerSleeping(EntityPlayer player) {
/* 157 */     World world = player.field_70170_p;
/* 158 */     ChunkCoordinates chunkcoordinates = player.field_71081_bT;
/* 159 */     Block block = world.func_147439_a(chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c);
/* 160 */     if (block == SMModContainer.StarBedHeadBlock) {
/* 161 */       int conDir = ((BlockStarBedHead)block).getConnectionDirection((IBlockAccess)world, chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c);
/* 162 */       if (conDir == -1)
/* 162 */         return false; 
/* 163 */       conDir = DirectionConst.OPPOSITE_CNV[conDir];
/* 164 */       int gravDir = ((BlockStarBedHead)block).getGravityDirection((IBlockAccess)world, chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c);
/* 166 */       switch (conDir) {
/*     */         case 3:
/* 168 */           GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/*     */           break;
/*     */         case 2:
/* 171 */           GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*     */           break;
/*     */         case 0:
/* 176 */           GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*     */           break;
/*     */         case 5:
/* 179 */           GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*     */           break;
/*     */         case 4:
/* 182 */           GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*     */           break;
/*     */       } 
/* 185 */       return true;
/*     */     } 
/* 188 */     return false;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */