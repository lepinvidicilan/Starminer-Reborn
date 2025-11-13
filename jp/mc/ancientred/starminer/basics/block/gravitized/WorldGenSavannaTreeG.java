/*     */ package jp.mc.ancientred.starminer.basics.block.gravitized;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class WorldGenSavannaTreeG extends WorldGenAbstractTreeEx {
/*     */   public int minTreeHeight;
/*     */   
/*     */   public WorldGenSavannaTreeG(boolean par1) {
/*  18 */     super(par1);
/*     */   }
/*     */   
/*     */   public WorldGenSavannaTreeG(boolean par1, int minTreeHeight, int argDir) {
/*  23 */     super(par1);
/*  24 */     this.minTreeHeight = minTreeHeight;
/*  25 */     this.dir = argDir;
/*     */   }
/*     */   
/*     */   public boolean func_76484_a(World par1World, Random par2Random, int posX, int posY, int posZ) {
/*  30 */     this.saved.x = posX;
/*  31 */     this.saved.y = posY;
/*  32 */     this.saved.z = posZ;
/*  34 */     int l = par2Random.nextInt(3) + par2Random.nextInt(3) + 5;
/*  35 */     boolean flag = true;
/*  43 */     if (par1World != null)
/*  45 */       for (int yCnt = posY; yCnt <= posY + 1 + l; yCnt++) {
/*  47 */         byte b0 = 1;
/*  49 */         if (yCnt == posY)
/*  51 */           b0 = 0; 
/*  54 */         if (yCnt >= posY + 1 + l - 2)
/*  56 */           b0 = 2; 
/*  59 */         for (int i = posX - b0; i <= posX + b0 && flag; i++) {
/*  61 */           for (int j = posZ - b0; j <= posZ + b0 && flag; j++) {
/*  65 */             translateXYZ(i, yCnt, j);
/*  67 */             Block block = par1World.func_147439_a(this.trans.x, this.trans.y, this.trans.z);
/*  69 */             if (!isReplaceable(par1World, this.trans.x, this.trans.y, this.trans.z))
/*  71 */               flag = false; 
/*     */           } 
/*     */         } 
/*     */       }  
/*  83 */     if (!flag)
/*  85 */       return false; 
/*  95 */     int j3 = par2Random.nextInt(4);
/*  96 */     int xCnt = l - par2Random.nextInt(4) - 1;
/*  97 */     int k1 = 3 - par2Random.nextInt(3);
/*  98 */     int k3 = posX;
/*  99 */     int l1 = posZ;
/* 100 */     int i2 = 0;
/*     */     int j2;
/* 104 */     for (j2 = 0; j2 < l; j2++) {
/* 106 */       int k2 = posY + j2;
/* 108 */       if (j2 >= xCnt && k1 > 0) {
/* 110 */         k3 += Direction.field_71583_a[j3];
/* 111 */         l1 += Direction.field_71581_b[j3];
/* 112 */         k1--;
/*     */       } 
/* 115 */       translateXYZ(k3, k2, l1);
/* 117 */       if (par1World != null) {
/* 118 */         Block block1 = par1World.func_147439_a(this.trans.x, this.trans.y, this.trans.z);
/* 120 */         if (block1.isAir((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z) || block1.isLeaves((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z)) {
/* 122 */           func_150516_a(par1World, this.trans.x, this.trans.y, this.trans.z, Blocks.field_150363_s, convertWoodMeta(0));
/* 123 */           i2 = k2;
/*     */         } 
/*     */       } else {
/* 126 */         setBlockForChunkProvide(this.trans.x, this.trans.y, this.trans.z, Blocks.field_150363_s, convertWoodMeta(0));
/* 127 */         i2 = k2;
/*     */       } 
/*     */     } 
/* 131 */     for (j2 = -1; j2 <= 1; j2++) {
/* 133 */       for (int k2 = -1; k2 <= 1; k2++) {
/* 135 */         translateXYZ(k3 + j2, i2 + 1, l1 + k2);
/* 136 */         setLeaf(par1World, this.trans.x, this.trans.y, this.trans.z);
/*     */       } 
/*     */     } 
/* 140 */     translateXYZ(k3 + 2, i2 + 1, l1);
/* 141 */     setLeaf(par1World, this.trans.x, this.trans.y, this.trans.z);
/* 142 */     translateXYZ(k3 - 2, i2 + 1, l1);
/* 143 */     setLeaf(par1World, this.trans.x, this.trans.y, this.trans.z);
/* 144 */     translateXYZ(k3, i2 + 1, l1 + 2);
/* 145 */     setLeaf(par1World, this.trans.x, this.trans.y, this.trans.z);
/* 146 */     translateXYZ(k3, i2 + 1, l1 - 2);
/* 147 */     setLeaf(par1World, this.trans.x, this.trans.y, this.trans.z);
/* 149 */     for (j2 = -3; j2 <= 3; j2++) {
/* 151 */       for (int k2 = -3; k2 <= 3; k2++) {
/* 153 */         if (Math.abs(j2) != 3 || Math.abs(k2) != 3) {
/* 155 */           translateXYZ(k3 + j2, i2, l1 + k2);
/* 156 */           setLeaf(par1World, this.trans.x, this.trans.y, this.trans.z);
/*     */         } 
/*     */       } 
/*     */     } 
/* 161 */     k3 = posX;
/* 162 */     l1 = posZ;
/* 163 */     j2 = par2Random.nextInt(4);
/* 165 */     if (j2 != j3) {
/* 167 */       int k2 = xCnt - par2Random.nextInt(2) - 1;
/* 168 */       int l3 = 1 + par2Random.nextInt(3);
/* 169 */       i2 = 0;
/* 173 */       for (int l2 = k2; l2 < l && l3 > 0; l3--) {
/* 175 */         if (l2 >= 1) {
/* 177 */           int i3 = posY + l2;
/* 178 */           k3 += Direction.field_71583_a[j2];
/* 179 */           l1 += Direction.field_71581_b[j2];
/* 181 */           translateXYZ(k3, i3, l1);
/* 182 */           if (par1World != null) {
/* 183 */             Block block2 = par1World.func_147439_a(this.trans.x, this.trans.y, this.trans.z);
/* 185 */             if (block2.isAir((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z) || block2.isLeaves((IBlockAccess)par1World, this.trans.x, this.trans.y, this.trans.z)) {
/* 187 */               func_150516_a(par1World, this.trans.x, this.trans.y, this.trans.z, Blocks.field_150363_s, convertWoodMeta(0));
/* 188 */               i2 = i3;
/*     */             } 
/*     */           } else {
/* 191 */             setBlockForChunkProvide(this.trans.x, this.trans.y, this.trans.z, Blocks.field_150363_s, convertWoodMeta(0));
/* 192 */             i2 = i3;
/*     */           } 
/*     */         } 
/* 196 */         l2++;
/*     */       } 
/* 199 */       if (i2 > 0) {
/* 201 */         for (int i = -1; i <= 1; i++) {
/* 203 */           for (int i3 = -1; i3 <= 1; i3++) {
/* 205 */             translateXYZ(k3 + i, i2 + 1, l1 + i3);
/* 206 */             setLeaf(par1World, this.trans.x, this.trans.y, this.trans.z);
/*     */           } 
/*     */         } 
/* 210 */         for (int j = -2; j <= 2; j++) {
/* 212 */           for (int i3 = -2; i3 <= 2; i3++) {
/* 214 */             if (Math.abs(j) != 2 || Math.abs(i3) != 2) {
/* 216 */               translateXYZ(k3 + j, i2, l1 + i3);
/* 217 */               setLeaf(par1World, this.trans.x, this.trans.y, this.trans.z);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 224 */     return true;
/*     */   }
/*     */   
/*     */   private void setLeaf(World parWorld, int p_150525_2_, int p_150525_3_, int p_150525_4_) {
/* 240 */     if (parWorld != null) {
/* 241 */       Block block = parWorld.func_147439_a(p_150525_2_, p_150525_3_, p_150525_4_);
/* 243 */       if (block.isAir((IBlockAccess)parWorld, p_150525_2_, p_150525_3_, p_150525_4_) || block.isLeaves((IBlockAccess)parWorld, p_150525_2_, p_150525_3_, p_150525_4_))
/* 245 */         func_150516_a(parWorld, p_150525_2_, p_150525_3_, p_150525_4_, (Block)Blocks.field_150361_u, 0); 
/*     */     } else {
/* 248 */       setBlockForChunkProvide(p_150525_2_, p_150525_3_, p_150525_4_, (Block)Blocks.field_150361_u, 0);
/*     */     } 
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */