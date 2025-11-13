/*     */ package jp.mc.ancientred.starminer.basics.block.gravitized;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.block.DirectionConst;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockBush;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockBushGravitized extends BlockBush implements IGravitizedPlants {
/*  21 */   public double W_D = 0.2D;
/*     */   
/*  22 */   public float W_F = 0.2F;
/*     */   
/*  24 */   public double HEIGHT_D = 0.6000000000000001D;
/*     */   
/*  25 */   public float HEIGHT_F = 0.6F;
/*     */   
/*     */   public BlockBushGravitized(Material par2Material, double wd, double heightD) {
/*  32 */     this.W_D = wd;
/*  33 */     this.W_F = (float)wd;
/*  34 */     this.HEIGHT_D = heightD;
/*  35 */     this.HEIGHT_F = (float)heightD;
/*  37 */     func_149711_c(0.0F);
/*  38 */     func_149672_a(Block.field_149779_h);
/*     */   }
/*     */   
/*     */   protected BlockBushGravitized(double wd, double heightD) {
/*  45 */     this(Material.field_151585_k, wd, heightD);
/*     */   }
/*     */   
/*     */   public BlockBushGravitized() {
/*  51 */     this(0.2D, 0.6000000000000001D);
/*     */   }
/*     */   
/*     */   public boolean allowPlantOn(Block block, int meta) {
/*  55 */     return (Blocks.field_150346_d == block || Blocks.field_150349_c == block || Blocks.field_150458_ak == block || SMModContainer.DirtGrassExBlock == block);
/*     */   }
/*     */   
/*     */   public int func_149645_b() {
/*  60 */     return 4341801;
/*     */   }
/*     */   
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
/*  65 */     Block block = par1World.func_147439_a(par2, par3, par4);
/*  66 */     return (block == null || block.isReplaceable((IBlockAccess)par1World, par2, par3, par4));
/*     */   }
/*     */   
/*     */   public boolean func_149718_j(World par1World, int par2, int par3, int par4) {
/*  73 */     int meta = par1World.func_72805_g(par2, par3, par4);
/*     */     int dir;
/*  74 */     if ((dir = DirectionConst.getPlantGravityDirection((IBlockAccess)par1World, par2, par3, par4)) == -1)
/*  75 */       return DirectionConst.tryStayable(par1World, par2, par3, par4, meta, this); 
/*  78 */     if (!DirectionConst.isStayableAtOppositeSide(par1World, par2, par3, par4, dir, meta, this))
/*  79 */       return DirectionConst.tryStayable(par1World, par2, par3, par4, meta, this); 
/*  81 */     return true;
/*     */   }
/*     */   
/*     */   private boolean tryGravitizeCorrectly() {
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149660_a(World par1World, int x, int y, int z, int side, float par6, float par7, float par8, int par9) {
/*  89 */     int dir = 0;
/*  90 */     int gAirX = x;
/*  91 */     int gAirY = y;
/*  92 */     int gAirZ = z;
/*  93 */     switch (side) {
/*     */       case 0:
/*  95 */         dir = 1;
/*  96 */         gAirY--;
/*     */         break;
/*     */       case 1:
/*  99 */         dir = 0;
/* 100 */         gAirY++;
/*     */         break;
/*     */       case 2:
/* 103 */         dir = 5;
/* 104 */         gAirZ--;
/*     */         break;
/*     */       case 3:
/* 107 */         dir = 4;
/* 108 */         gAirZ++;
/*     */         break;
/*     */       case 4:
/* 111 */         dir = 3;
/* 112 */         gAirX--;
/*     */         break;
/*     */       case 5:
/* 115 */         dir = 2;
/* 116 */         gAirX++;
/*     */         break;
/*     */     } 
/* 120 */     if (par1World.func_147437_c(gAirX, gAirY, gAirZ))
/* 121 */       DirectionConst.setDummyAirBlockWithMeta(par1World, gAirX, gAirY, gAirZ, dir + 1, 3); 
/* 123 */     return super.func_149660_a(par1World, x, y, z, side, par6, par7, par8, par9);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
/*     */     int dir;
/* 130 */     if ((dir = DirectionConst.getPlantGravityDirection((IBlockAccess)par1World, par2, par3, par4)) == -1)
/* 131 */       dir = 0; 
/* 133 */     double f = this.W_D;
/* 134 */     double lw = this.HEIGHT_D;
/* 135 */     double hi = 1.0D - this.HEIGHT_D;
/* 136 */     switch (dir) {
/*     */       case 1:
/* 138 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.5D - f, (double)par3 + hi, (double)par4 + 0.5D - f, (double)par2 + 0.5D + f, (double)par3 + 1.0D, (double)par4 + 0.5D + f);
/*     */       case 0:
/* 145 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.5D - f, (double)par3 + 0.0D, (double)par4 + 0.5D - f, (double)par2 + 0.5D + f, (double)par3 + lw, (double)par4 + 0.5D + f);
/*     */       case 3:
/* 152 */         return AxisAlignedBB.func_72330_a((double)par2 + hi, (double)par3 + 0.5D - f, (double)par4 + 0.5D - f, (double)par2 + 1.0D, (double)par3 + 0.5D + f, (double)par4 + 0.5D + f);
/*     */       case 2:
/* 159 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.5D - f, (double)par4 + 0.5D - f, (double)par2 + lw, (double)par3 + 0.5D + f, (double)par4 + 0.5D + f);
/*     */       case 5:
/* 166 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.5D - f, (double)par3 + 0.5D - f, (double)par4 + hi, (double)par2 + 0.5D + f, (double)par3 + 0.5D + f, (double)par4 + 1.0D);
/*     */       case 4:
/* 173 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.5D - f, (double)par3 + 0.5D - f, (double)par4 + 0.0D, (double)par2 + 0.5D + f, (double)par3 + 0.5D + f, (double)par4 + lw);
/*     */     } 
/* 180 */     return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess par1World, int par2, int par3, int par4) {
/*     */     int dir;
/* 191 */     if ((dir = DirectionConst.getPlantGravityDirection(par1World, par2, par3, par4)) == -1)
/* 192 */       dir = 0; 
/* 194 */     setBoundBasedOnAirMeataAbove(dir);
/*     */   }
/*     */   
/*     */   private void setBoundBasedOnAirMeataAbove(int dir) {
/* 198 */     float f = this.W_F;
/* 199 */     float lw = this.HEIGHT_F;
/* 200 */     float hi = 1.0F - this.HEIGHT_F;
/* 202 */     switch (dir) {
/*     */       case 1:
/* 204 */         func_149676_a(0.5F - f, hi, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
/*     */         break;
/*     */       case 0:
/* 207 */         func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, lw, 0.5F + f);
/*     */         break;
/*     */       case 3:
/* 210 */         func_149676_a(hi, 0.5F - f, 0.5F - f, 1.0F, 0.5F + f, 0.5F + f);
/*     */         break;
/*     */       case 2:
/* 213 */         func_149676_a(0.0F, 0.5F - f, 0.5F - f, lw, 0.5F + f, 0.5F + f);
/*     */         break;
/*     */       case 5:
/* 216 */         func_149676_a(0.5F - f, 0.5F - f, hi, 0.5F + f, 0.5F + f, 1.0F);
/*     */         break;
/*     */       case 4:
/* 219 */         func_149676_a(0.5F - f, 0.5F - f, 0.0F, 0.5F + f, 0.5F + f, lw);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */