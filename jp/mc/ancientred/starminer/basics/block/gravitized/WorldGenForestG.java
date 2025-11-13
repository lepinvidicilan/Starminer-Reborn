/*     */ package jp.mc.ancientred.starminer.basics.block.gravitized;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class WorldGenForestG extends WorldGenAbstractTreeEx {
/*     */   public int minTreeHeight;
/*     */   
/*     */   public WorldGenForestG(boolean par1) {
/*  17 */     super(par1);
/*     */   }
/*     */   
/*     */   public WorldGenForestG(boolean par1, int minTreeHeight, int argDir) {
/*  21 */     super(par1);
/*  22 */     this.minTreeHeight = minTreeHeight;
/*  23 */     this.dir = argDir;
/*     */   }
/*     */   
/*     */   public boolean func_76484_a(World par1World, Random par2Random, int posX, int posY, int posZ) {
/*  28 */     this.saved.x = posX;
/*  29 */     this.saved.y = posY;
/*  30 */     this.saved.z = posZ;
/*  32 */     int treeHight = par2Random.nextInt(3) + this.minTreeHeight;
/*  33 */     boolean canGrow = true;
/*  43 */     if (par1World != null)
/*  45 */       for (int yCnt = posY; yCnt <= posY + 1 + treeHight; yCnt++) {
/*  47 */         byte b0 = 1;
/*  49 */         if (yCnt == posY)
/*  51 */           b0 = 0; 
/*  54 */         if (yCnt >= posY + 1 + treeHight - 2)
/*  56 */           b0 = 2; 
/*  59 */         for (int xCnt = posX - b0; xCnt <= posX + b0 && canGrow; xCnt++) {
/*  61 */           for (int zCnt = posZ - b0; zCnt <= posZ + b0 && canGrow; zCnt++) {
/*  65 */             translateXYZ(xCnt, yCnt, zCnt);
/*  67 */             Block block = par1World.func_147439_a(this.trans.x, this.trans.y, this.trans.z);
/*  69 */             if (!isReplaceable(par1World, this.trans.x, this.trans.y, this.trans.z))
/*  71 */               canGrow = false; 
/*     */           } 
/*     */         } 
/*     */       }  
/*  83 */     if (!canGrow)
/*  85 */       return false; 
/*  98 */     for (int i = posY - 3 + treeHight; i <= posY + treeHight; i++) {
/* 100 */       int xCnt = i - (posY + treeHight);
/* 101 */       int zCnt = 1 - xCnt / 2;
/* 103 */       for (int xCnt2 = posX - zCnt; xCnt2 <= posX + zCnt; xCnt2++) {
/* 105 */         int j2 = xCnt2 - posX;
/* 107 */         for (int zCnt2 = posZ - zCnt; zCnt2 <= posZ + zCnt; zCnt2++) {
/* 109 */           int l2 = zCnt2 - posZ;
/* 111 */           if (Math.abs(j2) != zCnt || Math.abs(l2) != zCnt || (par2Random.nextInt(2) != 0 && xCnt != 0)) {
/* 113 */             translateXYZ(xCnt2, i, zCnt2);
/* 115 */             if (par1World != null) {
/* 116 */               Block block = par1World.func_147439_a(this.trans.x, this.trans.y, this.trans.z);
/* 118 */               if (block == null || block.canBeReplacedByLeaves((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z))
/* 120 */                 func_150516_a(par1World, this.trans.x, this.trans.y, this.trans.z, (Block)Blocks.field_150362_t, 2); 
/*     */             } else {
/* 123 */               setBlockForChunkProvide(this.trans.x, this.trans.y, this.trans.z, (Block)Blocks.field_150362_t, 2);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 130 */     for (int yCnt2 = 0; yCnt2 < treeHight; yCnt2++) {
/* 132 */       translateXYZ(posX, posY + yCnt2, posZ);
/* 134 */       if (par1World != null) {
/* 135 */         Block block = par1World.func_147439_a(this.trans.x, this.trans.y, this.trans.z);
/* 137 */         if (block == null || block.isAir((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z) || block.isLeaves((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z))
/* 140 */           func_150516_a(par1World, this.trans.x, this.trans.y, this.trans.z, Blocks.field_150364_r, convertWoodMeta(2)); 
/*     */       } else {
/* 143 */         setBlockForChunkProvide(this.trans.x, this.trans.y, this.trans.z, Blocks.field_150364_r, convertWoodMeta(2));
/*     */       } 
/*     */     } 
/* 147 */     return true;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */