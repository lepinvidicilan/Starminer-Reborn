/*     */ package jp.mc.ancientred.starminer.basics.block;
/*     */ 
/*     */ import jp.mc.ancientred.starminer.basics.Config;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.SMReflectionHelper;
/*     */ import jp.mc.ancientred.starminer.basics.common.VecUtils;
/*     */ import jp.mc.ancientred.starminer.basics.entity.EntityStarSquid;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityNavigator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockManBazooka extends Block {
/*     */   public BlockManBazooka() {
/*  23 */     super(Material.field_151576_e);
/*  24 */     func_149711_c(2.0F);
/*  25 */     func_149752_b(50.0F);
/*     */   }
/*     */   
/*     */   public boolean func_149686_d() {
/*  30 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c() {
/*  34 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b() {
/*  38 */     return 4341806;
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
/*  42 */     Vec3 shootPoint = getSpawnPoint(par1World, par2, par3, par4);
/*  43 */     if (shootPoint == null)
/*  43 */       return true; 
/*  45 */     if (!par1World.field_72995_K) {
/*  46 */       EntityStarSquid entityStarSquid = new EntityStarSquid(par1World, true);
/*  48 */       entityStarSquid.func_70012_b(shootPoint.field_72450_a, shootPoint.field_72448_b, shootPoint.field_72449_c, par5EntityPlayer.field_70177_z, par5EntityPlayer.field_70125_A);
/*  50 */       par1World.func_72838_d((Entity)entityStarSquid);
/*  51 */       par5EntityPlayer.func_70078_a((Entity)entityStarSquid);
/*  52 */       SMReflectionHelper.ignoreHasMovedFlg(par5EntityPlayer);
/*  54 */       TileEntityNavigator teNavi = null;
/*     */       int i;
/*  56 */       label43: for (i = par2 - 1; i <= par2 + 1; i++) {
/*  57 */         for (int j = par3 - 1; j <= par3 + 1; j++) {
/*  58 */           for (int k = par4 - 1; k <= par4 + 1; k++) {
/*  59 */             if (SMModContainer.NavigatorBlock == par1World.func_147439_a(i, j, k) && ((BlockNavigator)SMModContainer.NavigatorBlock).isOn(par1World.func_72805_g(i, j, k))) {
/*  61 */               teNavi = (TileEntityNavigator)par1World.func_147438_o(i, j, k);
/*     */               break label43;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*  68 */       if (teNavi != null) {
/*  70 */         ((Entity)entityStarSquid).field_70159_w = (double)teNavi.lookX * Config.bazookaStartSpeed;
/*  71 */         ((Entity)entityStarSquid).field_70181_x = (double)teNavi.lookY * Config.bazookaStartSpeed;
/*  72 */         ((Entity)entityStarSquid).field_70179_y = (double)teNavi.lookZ * Config.bazookaStartSpeed;
/*     */       } else {
/*  75 */         int meta = par1World.func_72805_g(par2, par3, par4);
/*  76 */         switch (meta) {
/*     */           case 0:
/*  78 */             ((Entity)entityStarSquid).field_70181_x = 1.3D;
/*     */             break;
/*     */           case 1:
/*  81 */             ((Entity)entityStarSquid).field_70181_x = -1.3D;
/*     */             break;
/*     */           case 2:
/*  84 */             ((Entity)entityStarSquid).field_70159_w = 1.3D;
/*     */             break;
/*     */           case 3:
/*  87 */             ((Entity)entityStarSquid).field_70159_w = -1.3D;
/*     */             break;
/*     */           case 4:
/*  90 */             ((Entity)entityStarSquid).field_70179_y = 1.3D;
/*     */             break;
/*     */           case 5:
/*  93 */             ((Entity)entityStarSquid).field_70179_y = -1.3D;
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 100 */       par5EntityPlayer.func_85030_a("fireworks.largeBlast", 0.8F, 1.2F);
/*     */     } 
/* 102 */     return true;
/*     */   }
/*     */   
/*     */   private Vec3 getSpawnPoint(World par1World, int par2, int par3, int par4) {
/* 108 */     int meta = par1World.func_72805_g(par2, par3, par4);
/* 109 */     int x = par2;
/* 110 */     int y = par3;
/* 111 */     int z = par4;
/* 112 */     for (int i = 5; i >= 1; i--) {
/* 113 */       switch (meta) {
/*     */         case 0:
/* 115 */           y = par3 + i;
/*     */           break;
/*     */         case 1:
/* 118 */           y = par3 - i;
/*     */           break;
/*     */         case 2:
/* 121 */           x = par2 + i;
/*     */           break;
/*     */         case 3:
/* 124 */           x = par2 - i;
/*     */           break;
/*     */         case 4:
/* 127 */           z = par4 + i;
/*     */           break;
/*     */         case 5:
/* 130 */           z = par4 - i;
/*     */           break;
/*     */       } 
/* 135 */       if (par1World.func_147437_c(x, y, z))
/* 136 */         return VecUtils.createVec3((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D); 
/*     */     } 
/* 139 */     return null;
/*     */   }
/*     */   
/*     */   public int func_149660_a(World par1World, int par2, int par3, int par4, int side, float par6, float par7, float par8, int par9) {
/* 153 */     int j1 = par9;
/* 155 */     if (side == 0)
/* 157 */       j1 = 1; 
/* 159 */     if (side == 1)
/* 161 */       j1 = 0; 
/* 164 */     if (side == 2)
/* 166 */       j1 = 5; 
/* 169 */     if (side == 3)
/* 171 */       j1 = 4; 
/* 174 */     if (side == 4)
/* 176 */       j1 = 3; 
/* 179 */     if (side == 5)
/* 181 */       j1 = 2; 
/* 184 */     return j1;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
/* 192 */     double lw = 0.2D;
/* 193 */     double hi = 0.8D;
/* 194 */     switch (par1World.func_72805_g(par2, par3, par4)) {
/*     */       case 1:
/* 196 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + hi, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 0:
/* 203 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + lw, (double)par4 + 1.0D);
/*     */       case 3:
/* 210 */         return AxisAlignedBB.func_72330_a((double)par2 + hi, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 2:
/* 217 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + lw, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 5:
/* 224 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + hi, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 4:
/* 231 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + lw);
/*     */     } 
/* 238 */     return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */