/*     */ package jp.mc.ancientred.starminer.basics;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemReed;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ import net.minecraft.profiler.Profiler;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldServer;
/*     */ 
/*     */ public class SMReflectionHelper {
/*     */   public static Field field_hasMoved;
/*     */   
/*     */   public static Method field_setSize;
/*     */   
/*     */   public static Field field_sleeping;
/*     */   
/*     */   public static Field field_sleepTimer;
/*     */   
/*     */   public static Field field_allPlayersSleeping;
/*     */   
/*     */   public static Field field_field_150935_a;
/*     */   
/*     */   public static Field field_worldProvider;
/*     */   
/*     */   public static Field field_theProfiler;
/*     */   
/*     */   public static void ignoreHasMovedFlg(EntityPlayer par5EntityPlayer) {
/*  25 */     if (par5EntityPlayer instanceof EntityPlayerMP)
/*     */       try {
/*  27 */         if (field_hasMoved == null)
/*  28 */           field_hasMoved = ReflectionHelper.findField(NetHandlerPlayServer.class, new String[] { "hasMoved", "field_147380_r" }); 
/*  30 */         field_hasMoved.setBoolean(((EntityPlayerMP)par5EntityPlayer).field_71135_a, true);
/*  31 */       } catch (Exception ex) {
/*  32 */         ex.printStackTrace();
/*     */       }  
/*     */   }
/*     */   
/*     */   public static void setSize(EntityPlayer player, float width, float hight) {
/*     */     try {
/*  42 */       if (field_setSize == null)
/*  43 */         field_setSize = ReflectionHelper.findMethod(Entity.class, player, new String[] { "setSize", "func_70105_a" }, new Class<?>[] { float.class, float.class }); 
/*  45 */       field_setSize.invoke(player, width, hight);
/*  46 */     } catch (Exception ex) {
/*  47 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setSleeping(EntityPlayer player) {
/*     */     try {
/*  56 */       if (field_sleeping == null)
/*  57 */         field_sleeping = ReflectionHelper.findField(EntityPlayer.class, new String[] { "sleeping", "field_71083_bS" }); 
/*  59 */       field_sleeping.setBoolean(player, true);
/*  60 */     } catch (Exception ex) {
/*  61 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setSleepTimer(EntityPlayer player, int value) {
/*     */     try {
/*  70 */       if (field_sleepTimer == null)
/*  71 */         field_sleepTimer = ReflectionHelper.findField(EntityPlayer.class, new String[] { "sleepTimer", "field_71076_b" }); 
/*  73 */       field_sleepTimer.setInt(player, value);
/*  74 */     } catch (Exception ex) {
/*  75 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setallPlayersSleeping(WorldServer worldServer, boolean value) {
/*     */     try {
/*  84 */       if (field_allPlayersSleeping == null)
/*  85 */         field_allPlayersSleeping = ReflectionHelper.findField(WorldServer.class, new String[] { "allPlayersSleeping", "field_73068_P" }); 
/*  87 */       field_allPlayersSleeping.setBoolean(worldServer, value);
/*  88 */     } catch (Exception ex) {
/*  89 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Block getField_150935_a(ItemReed itemReed) {
/*     */     try {
/*  98 */       if (field_field_150935_a == null)
/*  99 */         field_field_150935_a = ReflectionHelper.findField(ItemReed.class, new String[] { "field_150935_a", "field_150935_a" }); 
/* 101 */       return (Block)field_field_150935_a.get(itemReed);
/* 102 */     } catch (Exception ex) {
/* 103 */       ex.printStackTrace();
/* 105 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setWrappedWorldFinalField(World world, WorldProvider worldProvider, Profiler theProfiler) {
/*     */     try {
/* 114 */       if (field_worldProvider == null) {
/* 115 */         field_worldProvider = ReflectionHelper.findField(World.class, new String[] { "provider", "field_73011_w" });
/* 116 */         Field modifiersField = Field.class.getDeclaredField("modifiers");
/* 117 */         modifiersField.setAccessible(true);
/* 118 */         modifiersField.setInt(field_worldProvider, field_worldProvider.getModifiers() & 0xFFFFFFEF);
/*     */       } 
/* 120 */       if (field_theProfiler == null) {
/* 121 */         field_theProfiler = ReflectionHelper.findField(World.class, new String[] { "theProfiler", "field_72984_F" });
/* 122 */         Field modifiersField = Field.class.getDeclaredField("modifiers");
/* 123 */         modifiersField.setAccessible(true);
/* 124 */         modifiersField.setInt(field_theProfiler, field_theProfiler.getModifiers() & 0xFFFFFFEF);
/*     */       } 
/* 127 */       field_worldProvider.set(world, worldProvider);
/* 128 */       field_theProfiler.set(world, theProfiler);
/* 129 */     } catch (Exception ex) {
/* 130 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */