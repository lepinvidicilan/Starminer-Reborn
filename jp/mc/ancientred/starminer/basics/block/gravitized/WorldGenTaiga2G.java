/*     */ package jp.mc.ancientred.starminer.basics.block.gravitized;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class WorldGenTaiga2G extends WorldGenAbstractTreeEx {
/*     */   public int minTreeHeight;
/*     */   
/*     */   public WorldGenTaiga2G(boolean par1) {
/*  16 */     super(par1);
/*     */   }
/*     */   
/*     */   public WorldGenTaiga2G(boolean par1, int minTreeHeight, int argDir) {
/*  20 */     super(par1);
/*  21 */     this.minTreeHeight = minTreeHeight;
/*  22 */     this.dir = argDir;
/*     */   }
/*     */   
/*     */   public boolean func_76484_a(World par1World, Random par2Random, int posX, int posY, int posZ) {
/*  27 */     this.saved.x = posX;
/*  28 */     this.saved.y = posY;
/*  29 */     this.saved.z = posZ;
/*  31 */     int treeHight = par2Random.nextInt(4) + this.minTreeHeight;
/*  32 */     int i1 = 1 + par2Random.nextInt(2);
/*  33 */     int j1 = treeHight - i1;
/*  34 */     int k1 = 2 + par2Random.nextInt(2);
/*  35 */     boolean cangrow = true;
/*  44 */     if (par1World != null)
/*  46 */       for (int yCnt = posY; yCnt <= posY + 1 + treeHight && cangrow; yCnt++) {
/*     */         int j;
/*  48 */         boolean flag1 = true;
/*  50 */         if (yCnt - posY < i1) {
/*  52 */           j = 0;
/*     */         } else {
/*  56 */           j = k1;
/*     */         } 
/*  59 */         for (int i = posX - j; i <= posX + j && cangrow; i++) {
/*  61 */           for (int zCnt = posZ - j; zCnt <= posZ + j && cangrow; zCnt++) {
/*  65 */             translateXYZ(i, yCnt, zCnt);
/*  67 */             Block block = par1World.func_147439_a(this.trans.x, this.trans.y, this.trans.z);
/*  69 */             if (!isReplaceable(par1World, this.trans.x, this.trans.y, this.trans.z))
/*  74 */               cangrow = false; 
/*     */           } 
/*     */         } 
/*     */       }  
/*  86 */     if (!cangrow)
/*  88 */       return false; 
/*  99 */     int k2 = par2Random.nextInt(2);
/* 100 */     int xCnt = 1;
/* 101 */     byte b0 = 0;
/*     */     int j2;
/* 105 */     for (j2 = 0; j2 <= j1; j2++) {
/* 107 */       int i = posY + treeHight - j2;
/* 109 */       for (int xCnt2 = posX - k2; xCnt2 <= posX + k2; xCnt2++) {
/* 111 */         int k3 = xCnt2 - posX;
/* 113 */         for (int zCnt2 = posZ - k2; zCnt2 <= posZ + k2; zCnt2++) {
/* 115 */           int i4 = zCnt2 - posZ;
/* 117 */           translateXYZ(xCnt2, i, zCnt2);
/* 118 */           if (par1World != null) {
/* 119 */             Block block = par1World.func_147439_a(this.trans.x, this.trans.y, this.trans.z);
/* 121 */             if ((Math.abs(k3) != k2 || Math.abs(i4) != k2 || k2 <= 0) && (block == null || block.isAir((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z) || block.canBeReplacedByLeaves((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z)))
/* 126 */               func_150516_a(par1World, this.trans.x, this.trans.y, this.trans.z, (Block)Blocks.field_150362_t, 1); 
/*     */           } else {
/* 129 */             setBlockForChunkProvide(this.trans.x, this.trans.y, this.trans.z, (Block)Blocks.field_150362_t, 1);
/*     */           } 
/*     */         } 
/*     */       } 
/* 134 */       if (k2 >= xCnt) {
/* 136 */         k2 = b0;
/* 137 */         b0 = 1;
/* 138 */         xCnt++;
/* 140 */         if (xCnt > k1)
/* 142 */           xCnt = k1; 
/*     */       } else {
/* 147 */         k2++;
/*     */       } 
/*     */     } 
/* 151 */     j2 = par2Random.nextInt(3);
/* 153 */     for (int j3 = 0; j3 < treeHight - j2; j3++) {
/* 155 */       translateXYZ(posX, posY + j3, posZ);
/* 156 */       if (par1World != null) {
/* 157 */         Block block = par1World.func_147439_a(this.trans.x, this.trans.y, this.trans.z);
/* 159 */         if (block == null || block.isAir((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z) || block.isLeaves((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z))
/* 163 */           func_150516_a(par1World, this.trans.x, this.trans.y, this.trans.z, Blocks.field_150364_r, convertWoodMeta(1)); 
/*     */       } else {
/* 166 */         setBlockForChunkProvide(this.trans.x, this.trans.y, this.trans.z, Blocks.field_150364_r, convertWoodMeta(1));
/*     */       } 
/*     */     } 
/* 170 */     return true;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */