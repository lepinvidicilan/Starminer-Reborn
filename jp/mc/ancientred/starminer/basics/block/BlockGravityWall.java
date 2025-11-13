/*     */ package jp.mc.ancientred.starminer.basics.block;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import jp.mc.ancientred.starminer.api.Gravity;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockGravityWall extends Block {
/*     */   public static final double HEIGHT_D = 0.0625D;
/*     */   
/*     */   public static final float HEIGHT_F = 0.0625F;
/*     */   
/*     */   private static final int GWALL_AFFECT_TICK = 15;
/*     */   
/*     */   public BlockGravityWall() {
/*  25 */     super(Material.field_151576_e);
/*  26 */     func_149711_c(9.0F);
/*  27 */     func_149752_b(2.0F);
/*  28 */     func_111047_d(0);
/*     */   }
/*     */   
/*     */   public boolean func_149686_d() {
/*  32 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c() {
/*  36 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b() {
/*  40 */     return 4341804;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149701_w() {
/*  46 */     return 1;
/*     */   }
/*     */   
/*     */   public int func_149660_a(World par1World, int par2, int par3, int par4, int side, float par6, float par7, float par8, int par9) {
/*  60 */     int j1 = par9;
/*  62 */     if (side == 0)
/*  64 */       j1 = 1; 
/*  66 */     if (side == 1)
/*  68 */       j1 = 0; 
/*  71 */     if (side == 2)
/*  73 */       j1 = 5; 
/*  76 */     if (side == 3)
/*  78 */       j1 = 4; 
/*  81 */     if (side == 4)
/*  83 */       j1 = 3; 
/*  86 */     if (side == 5)
/*  88 */       j1 = 2; 
/*  91 */     return j1;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
/* 100 */     double lw = 0.0625D;
/* 101 */     double hi = 0.9375D;
/* 102 */     switch (par1World.func_72805_g(par2, par3, par4)) {
/*     */       case 1:
/* 104 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + hi, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 0:
/* 111 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + lw, (double)par4 + 1.0D);
/*     */       case 3:
/* 118 */         return AxisAlignedBB.func_72330_a((double)par2 + hi, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 2:
/* 125 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + lw, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 5:
/* 132 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + hi, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 4:
/* 139 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + lw);
/*     */     } 
/* 146 */     return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
/* 161 */     return func_149668_a(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   public void func_149683_g() {
/* 169 */     func_111047_d(0);
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess par1IWorld, int par2, int par3, int par4) {
/* 177 */     func_111047_d(par1IWorld.func_72805_g(par2, par3, par4));
/*     */   }
/*     */   
/*     */   protected void func_111047_d(int par1) {
/* 181 */     float lw = 0.0625F;
/* 182 */     float hi = 0.9375F;
/* 183 */     switch (par1) {
/*     */       case 1:
/* 185 */         func_149676_a(0.0F, hi, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         break;
/*     */       case 0:
/* 188 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, lw, 1.0F);
/*     */         break;
/*     */       case 3:
/* 191 */         func_149676_a(hi, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         break;
/*     */       case 2:
/* 194 */         func_149676_a(0.0F, 0.0F, 0.0F, lw, 1.0F, 1.0F);
/*     */         break;
/*     */       case 5:
/* 197 */         func_149676_a(0.0F, 0.0F, hi, 1.0F, 1.0F, 1.0F);
/*     */         break;
/*     */       case 4:
/* 200 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, lw);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_149670_a(World par1World, int par2, int par3, int par4, Entity par5Entity) {
/* 206 */     if (par5Entity instanceof net.minecraft.entity.player.EntityPlayer) {
/* 207 */       Gravity gravity = Gravity.getGravityProp(par5Entity);
/* 208 */       if (par1World.field_72995_K) {
/* 210 */         par5Entity.field_70143_R = 0.0F;
/* 212 */         if (gravity != null)
/* 213 */           switch (par1World.func_72805_g(par2, par3, par4)) {
/*     */             case 1:
/* 215 */               gravity.setTemporaryGravityDirection(GravityDirection.downTOup_YP, 15);
/*     */               break;
/*     */             case 0:
/* 218 */               gravity.setTemporaryGravityDirection(GravityDirection.upTOdown_YN, 15);
/*     */               break;
/*     */             case 3:
/* 221 */               gravity.setTemporaryGravityDirection(GravityDirection.westTOeast_XP, 15);
/*     */               break;
/*     */             case 2:
/* 224 */               gravity.setTemporaryGravityDirection(GravityDirection.eastTOwest_XN, 15);
/*     */               break;
/*     */             case 5:
/* 227 */               gravity.setTemporaryGravityDirection(GravityDirection.northTOsouth_ZP, 15);
/*     */               break;
/*     */             case 4:
/* 230 */               gravity.setTemporaryGravityDirection(GravityDirection.southTOnorth_ZN, 15);
/*     */               break;
/*     */           }  
/*     */       } else {
/* 236 */         par5Entity.field_70143_R = 0.0F;
/* 239 */         if (gravity != null)
/* 239 */           gravity.acceptExceptionalGravityTick = 45; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */