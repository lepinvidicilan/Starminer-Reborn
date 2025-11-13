/*     */ package jp.mc.ancientred.starminer.basics.block.gravitized;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class WorldGenTreesG extends WorldGenAbstractTreeEx {
/*     */   public int minTreeHeight;
/*     */   
/*     */   public int metaWood;
/*     */   
/*     */   public int metaLeaves;
/*     */   
/*  26 */   private Block logBlock = Blocks.field_150364_r;
/*     */   
/*  27 */   private Block leafBlock = (Block)Blocks.field_150362_t;
/*     */   
/*     */   public WorldGenTreesG(boolean par1) {
/*  30 */     super(par1);
/*     */   }
/*     */   
/*     */   public WorldGenTreesG(boolean par1, int argDir, boolean asNewTreeSet) {
/*  35 */     this(par1, 4, 0, 0, false, argDir, true);
/*     */   }
/*     */   
/*     */   public WorldGenTreesG(boolean par1, int argDir) {
/*  39 */     this(par1, 4, 0, 0, false, argDir, false);
/*     */   }
/*     */   
/*     */   public WorldGenTreesG(boolean par1, int par2, int par3, int par4, boolean par5, int argDir) {
/*  43 */     this(par1, par2, par3, par4, par5, argDir, false);
/*     */   }
/*     */   
/*     */   public WorldGenTreesG(boolean par1, int par2, int par3, int par4, boolean par5, int argDir, boolean asNewTreeSet) {
/*  47 */     super(par1);
/*  48 */     this.dir = argDir;
/*  49 */     this.minTreeHeight = par2;
/*  50 */     this.metaWood = par3;
/*  51 */     this.metaLeaves = par4;
/*  54 */     setAsNewTreeSet(true);
/*     */   }
/*     */   
/*     */   public void setAsNewTreeSet(boolean asNewTreeSet) {
/*  57 */     if (asNewTreeSet) {
/*  58 */       this.logBlock = Blocks.field_150363_s;
/*  59 */       this.leafBlock = (Block)Blocks.field_150361_u;
/*     */     } else {
/*  61 */       this.logBlock = Blocks.field_150364_r;
/*  62 */       this.leafBlock = (Block)Blocks.field_150362_t;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean func_76484_a(World par1World, Random par2Random, int posX, int posY, int posZ) {
/*  68 */     this.saved.x = posX;
/*  69 */     this.saved.y = posY;
/*  70 */     this.saved.z = posZ;
/*  72 */     int treeHight = par2Random.nextInt(3) + this.minTreeHeight;
/*  73 */     boolean canGrow = true;
/*  82 */     if (par1World != null)
/*  84 */       for (int i1 = posY; i1 <= posY + 1 + treeHight; i1++) {
/*  86 */         byte b = 1;
/*  88 */         if (i1 == posY)
/*  90 */           b = 0; 
/*  93 */         if (i1 >= posY + 1 + treeHight - 2)
/*  95 */           b = 2; 
/*  98 */         for (int xCnt = posX - b; xCnt <= posX + b && canGrow; xCnt++) {
/* 100 */           for (int j = posZ - b; j <= posZ + b && canGrow; j++) {
/* 104 */             translateXYZ(xCnt, i1, j);
/* 106 */             Block block = par1World.func_147439_a(this.trans.x, this.trans.y, this.trans.z);
/* 108 */             if (!isReplaceable(par1World, this.trans.x, this.trans.y, this.trans.z))
/* 110 */               canGrow = false; 
/*     */           } 
/*     */         } 
/*     */       }  
/* 122 */     if (!canGrow)
/* 124 */       return false; 
/* 135 */     byte b0 = 3;
/* 136 */     byte b1 = 0;
/* 141 */     for (int i = posY - b0 + treeHight; i <= posY + treeHight; i++) {
/* 143 */       int k1 = i - (posY + treeHight);
/* 144 */       int i2 = b1 + 1 - k1 / 2;
/* 146 */       for (int xCnt = posX - i2; xCnt <= posX + i2; xCnt++) {
/* 148 */         int k2 = xCnt - posX;
/* 150 */         for (int zCnt = posZ - i2; zCnt <= posZ + i2; zCnt++) {
/* 152 */           int i3 = zCnt - posZ;
/* 154 */           if (Math.abs(k2) != i2 || Math.abs(i3) != i2 || (par2Random.nextInt(2) != 0 && k1 != 0)) {
/* 156 */             translateXYZ(xCnt, i, zCnt);
/* 158 */             if (par1World != null) {
/* 160 */               Block block = par1World.func_147439_a(this.trans.x, this.trans.y, this.trans.z);
/* 162 */               if (block == null || block.canBeReplacedByLeaves((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z))
/* 164 */                 func_150516_a(par1World, this.trans.x, this.trans.y, this.trans.z, this.leafBlock, this.metaLeaves); 
/*     */             } else {
/* 167 */               setBlockForChunkProvide(this.trans.x, this.trans.y, this.trans.z, this.leafBlock, this.metaLeaves);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 174 */     for (int yCnt = 0; yCnt < treeHight; yCnt++) {
/* 176 */       translateXYZ(posX, posY + yCnt, posZ);
/* 177 */       if (par1World != null) {
/* 178 */         Block block = par1World.func_147439_a(this.trans.x, this.trans.y, this.trans.z);
/* 180 */         if (block == null || block.isAir((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z) || block.isLeaves((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z))
/* 182 */           func_150516_a(par1World, this.trans.x, this.trans.y, this.trans.z, this.logBlock, convertWoodMeta(this.metaWood)); 
/*     */       } else {
/* 213 */         setBlockForChunkProvide(this.trans.x, this.trans.y, this.trans.z, this.logBlock, convertWoodMeta(this.metaWood));
/*     */       } 
/*     */     } 
/* 278 */     return true;
/*     */   }
/*     */   
/*     */   private void growVines(World par1World, int par2, int par3, int par4, int par5) {}
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */