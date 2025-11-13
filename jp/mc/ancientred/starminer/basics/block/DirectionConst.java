/*    */ package jp.mc.ancientred.starminer.basics.block;
/*    */ 
/*    */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*    */ import jp.mc.ancientred.starminer.basics.block.gravitized.IGravitizedPlants;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class DirectionConst {
/*    */   public static final int SHIFT = 1;
/*    */   
/*    */   public static final int PLACEDIR_YBOT = 0;
/*    */   
/*    */   public static final int PLACEDIR_YTOP = 1;
/*    */   
/*    */   public static final int PLACEDIR_XBOT = 2;
/*    */   
/*    */   public static final int PLACEDIR_XTOP = 3;
/*    */   
/*    */   public static final int PLACEDIR_ZBOT = 4;
/*    */   
/*    */   public static final int PLACEDIR_ZTOP = 5;
/*    */   
/* 21 */   public static final int[][] CHECKNEIGHBOR_LIST = new int[][] { new int[] { 0, 1, 0, 0 }, new int[] { 0, -1, 0, 1 }, new int[] { 1, 0, 0, 2 }, new int[] { -1, 0, 0, 3 }, new int[] { 0, 0, 1, 4 }, new int[] { 0, 0, -1, 5 } };
/*    */   
/* 28 */   public static final int[] OPPOSITE_CNV = new int[] { 1, 0, 3, 2, 5, 4 };
/*    */   
/*    */   public static final int getPlantGravityDirection(IBlockAccess par1World, int parX, int parY, int parZ) {
/* 30 */     int len = CHECKNEIGHBOR_LIST.length;
/* 33 */     for (int i = 0; i < len; i++) {
/* 34 */       int[] neighbor = CHECKNEIGHBOR_LIST[i];
/* 35 */       Block block = par1World.func_147439_a(parX + neighbor[0], parY + neighbor[1], parZ + neighbor[2]);
/* 36 */       if (block == Blocks.field_150350_a && 
/* 37 */         par1World.func_72805_g(parX + neighbor[0], parY + neighbor[1], parZ + neighbor[2]) - 1 == neighbor[3])
/* 38 */         return neighbor[3]; 
/*    */     } 
/* 42 */     return -1;
/*    */   }
/*    */   
/*    */   public static final int[] getBlockBedHeadNeighborBody(IBlockAccess par1World, int parX, int parY, int parZ) {
/* 46 */     int len = CHECKNEIGHBOR_LIST.length;
/* 49 */     for (int i = 0; i < len; i++) {
/* 50 */       int[] neighbor = CHECKNEIGHBOR_LIST[i];
/* 51 */       Block block = par1World.func_147439_a(parX + neighbor[0], parY + neighbor[1], parZ + neighbor[2]);
/* 52 */       if (block == SMModContainer.StarBedBodyBlock && 
/* 53 */         par1World.func_72805_g(parX + neighbor[0], parY + neighbor[1], parZ + neighbor[2]) == OPPOSITE_CNV[neighbor[3]])
/* 54 */         return neighbor; 
/*    */     } 
/* 58 */     return null;
/*    */   }
/*    */   
/*    */   public static boolean isStayableAtOppositeSide(World par1World, int par2, int par3, int par4, int dir, int meta, IGravitizedPlants plant) {
/* 62 */     if (dir < 0 || OPPOSITE_CNV.length <= dir)
/* 62 */       return false; 
/* 63 */     int[] neighborToCheck = CHECKNEIGHBOR_LIST[OPPOSITE_CNV[dir]];
/* 64 */     Block oppositeSblock = par1World.func_147439_a(par2 + neighborToCheck[0], par3 + neighborToCheck[1], par4 + neighborToCheck[2]);
/* 65 */     return plant.allowPlantOn(oppositeSblock, meta);
/*    */   }
/*    */   
/*    */   public static final boolean tryStayable(World par1World, int par2, int par3, int par4, int meta, IGravitizedPlants plant) {
/* 69 */     int len = CHECKNEIGHBOR_LIST.length;
/* 73 */     for (int dir = 0; dir < len; dir++) {
/* 74 */       int[] neighborToCheck = CHECKNEIGHBOR_LIST[OPPOSITE_CNV[dir]];
/* 75 */       Block oppositeSblock = par1World.func_147439_a(par2 + neighborToCheck[0], par3 + neighborToCheck[1], par4 + neighborToCheck[2]);
/* 76 */       if (plant.allowPlantOn(oppositeSblock, meta)) {
/* 77 */         neighborToCheck = CHECKNEIGHBOR_LIST[dir];
/* 78 */         if (par1World.func_147439_a(par2 + neighborToCheck[0], par3 + neighborToCheck[1], par4 + neighborToCheck[2]) == Blocks.field_150350_a && par1World.func_72805_g(par2 + neighborToCheck[0], par3 + neighborToCheck[1], par4 + neighborToCheck[2]) == 0) {
/* 80 */           par1World.func_72921_c(par2 + neighborToCheck[0], par3 + neighborToCheck[1], par4 + neighborToCheck[2], dir + 1, 2);
/* 81 */           return true;
/*    */         } 
/*    */       } 
/*    */     } 
/* 85 */     return false;
/*    */   }
/*    */   
/*    */   public static final void setDummyAirBlockWithMeta(World world, int parX, int parY, int parZ, int dir, int flag) {
/* 90 */     world.func_147465_d(parX, parY, parZ, Blocks.field_150350_a, dir, flag);
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */