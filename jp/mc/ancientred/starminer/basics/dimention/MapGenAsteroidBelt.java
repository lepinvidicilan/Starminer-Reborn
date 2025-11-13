/*     */ package jp.mc.ancientred.starminer.basics.dimention;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ public class MapGenAsteroidBelt {
/*  16 */   protected int range = 8;
/*     */   
/*  19 */   protected Random rand = new Random();
/*     */   
/*     */   protected World worldObj;
/*     */   
/*     */   public void generate(IChunkProvider par1IChunkProvider, World world, int xChunkPos, int zChunkPos, Block[] blocksData) {
/*  26 */     int rangeLoc = this.range;
/*  27 */     this.worldObj = world;
/*  28 */     this.rand.setSeed(world.func_72905_C());
/*  29 */     long l = this.rand.nextLong();
/*  30 */     long i1 = this.rand.nextLong();
/*  32 */     for (int workChunkX = xChunkPos - rangeLoc; workChunkX <= xChunkPos + rangeLoc; workChunkX++) {
/*  34 */       for (int workChunkZ = zChunkPos - rangeLoc; workChunkZ <= zChunkPos + rangeLoc; workChunkZ++) {
/*  36 */         long l1 = (long)workChunkX * l;
/*  37 */         long i2 = (long)workChunkZ * i1;
/*  38 */         this.rand.setSeed(l1 ^ i2 ^ world.func_72905_C());
/*  39 */         recursiveGenerate(world, workChunkX, workChunkZ, xChunkPos, zChunkPos, blocksData);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void recursiveGenerate(World world, int workChunkX, int workChunkZ, int xChunkPos, int zChunkPos, Block[] blocksData) {
/*  49 */     int i1 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(4) + 1) + 1);
/*  51 */     if (this.rand.nextInt(15) != 0)
/*  53 */       i1 = 0; 
/*  56 */     for (int j1 = 0; j1 < i1; j1++) {
/*  58 */       double workPosX = (double)(workChunkX * 16 + this.rand.nextInt(16));
/*  59 */       double workPosY = (double)this.rand.nextInt(this.rand.nextInt(230) + 15);
/*  60 */       double workPosZ = (double)(workChunkZ * 16 + this.rand.nextInt(16));
/*  61 */       int k1 = 1;
/*  71 */       float f = this.rand.nextFloat() * 3.1415927F * 2.0F;
/*  72 */       float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
/*  73 */       float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
/*  75 */       generateCaveNode(this.rand.nextLong(), xChunkPos, zChunkPos, blocksData, workPosX, workPosY, workPosZ, f2, f, f1, 0, 0, 1.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void generateCaveNode(long par1, int xChunkPos, int zChunkPos, Block[] blocksData, double workPosX, double workPosY, double workPosZ, float par12, float hDirectionYawRad, float vDirectionPitchRad, int par15, int par16, double par17) {
/*  94 */     double chunkCenterX = (double)(xChunkPos * 16 + 8);
/*  95 */     double chunkCenterZ = (double)(zChunkPos * 16 + 8);
/*  96 */     float f3 = 0.0F;
/*  97 */     float f4 = 0.0F;
/*  98 */     Random random = new Random(par1);
/* 100 */     if (par16 <= 0) {
/* 102 */       int j1 = this.range * 16 * 4 - 16;
/* 103 */       par16 = j1 - random.nextInt(j1 / 4);
/*     */     } 
/* 106 */     boolean flag = false;
/* 108 */     if (par15 == -1) {
/* 110 */       par15 = par16 / 2;
/* 111 */       flag = true;
/*     */     } 
/* 114 */     int k1 = random.nextInt(par16 / 2) + par16 / 4;
/* 116 */     for (boolean flag1 = (random.nextInt(6) == 0); par15 < par16; par15++) {
/* 118 */       double d6 = 16.0D + (double)(MathHelper.func_76126_a((float)par15 * 3.1415927F / (float)par16) * par12 * 1.0F);
/* 119 */       double d7 = d6 * par17;
/* 120 */       float vPercentage = MathHelper.func_76134_b(vDirectionPitchRad);
/* 121 */       float vDirection = MathHelper.func_76126_a(vDirectionPitchRad);
/* 122 */       workPosX += (double)(MathHelper.func_76134_b(hDirectionYawRad) * vPercentage);
/* 123 */       workPosY += (double)vDirection;
/* 124 */       workPosZ += (double)(MathHelper.func_76126_a(hDirectionYawRad) * vPercentage);
/* 126 */       if (flag1) {
/* 128 */         vDirectionPitchRad *= 0.92F;
/*     */       } else {
/* 132 */         vDirectionPitchRad *= 0.7F;
/*     */       } 
/* 135 */       vDirectionPitchRad += f4 * 0.1F;
/* 136 */       hDirectionYawRad += f3 * 0.1F;
/* 137 */       f4 *= 0.9F;
/* 138 */       f3 *= 0.75F;
/* 139 */       f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
/* 140 */       f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
/* 150 */       if (flag || random.nextInt(4) != 0) {
/* 152 */         double d8 = workPosX - chunkCenterX;
/* 153 */         double d9 = workPosZ - chunkCenterZ;
/* 154 */         double d10 = (double)(par16 - par15);
/* 155 */         double d11 = (double)(par12 + 2.0F + 16.0F);
/* 157 */         if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11)
/*     */           return; 
/* 162 */         if (workPosX >= chunkCenterX - 16.0D - d6 * 2.0D && workPosZ >= chunkCenterZ - 16.0D - d6 * 2.0D && workPosX <= chunkCenterX + 16.0D + d6 * 2.0D && workPosZ <= chunkCenterZ + 16.0D + d6 * 2.0D) {
/* 167 */           int xMin = MathHelper.func_76128_c(workPosX - d6) - xChunkPos * 16 - 1;
/* 168 */           int xMax = MathHelper.func_76128_c(workPosX + d6) - xChunkPos * 16 + 1;
/* 169 */           int yMin = MathHelper.func_76128_c(workPosY - d7) - 1;
/* 170 */           int yMax = MathHelper.func_76128_c(workPosY + d7) + 1;
/* 171 */           int zMin = MathHelper.func_76128_c(workPosZ - d6) - zChunkPos * 16 - 1;
/* 172 */           int zMax = MathHelper.func_76128_c(workPosZ + d6) - zChunkPos * 16 + 1;
/* 174 */           if (xMin < 0)
/* 176 */             xMin = 0; 
/* 179 */           if (xMax > 16)
/* 181 */             xMax = 16; 
/* 184 */           if (yMin < 1)
/* 186 */             yMin = 1; 
/* 189 */           if (yMax > 240)
/* 191 */             yMax = 240; 
/* 194 */           if (zMin < 0)
/* 196 */             zMin = 0; 
/* 199 */           if (zMax > 16)
/* 201 */             zMax = 16; 
/* 204 */           boolean isOceanBlock = false;
/* 208 */           for (int x = xMin; !isOceanBlock && x < xMax; x++) {
/* 210 */             for (int l3 = zMin; !isOceanBlock && l3 < zMax; l3++) {
/* 212 */               for (int i4 = yMax + 1; !isOceanBlock && i4 >= yMin - 1; i4--) {
/* 214 */                 int z = (x * 16 + l3) * 128 + i4;
/* 216 */                 if (i4 >= 0 && i4 < 256)
/* 218 */                   if (i4 != yMin - 1 && x != xMin && x != xMax - 1 && l3 != zMin && l3 != zMax - 1)
/* 220 */                     i4 = yMin;  
/*     */               } 
/*     */             } 
/*     */           } 
/* 227 */           if (!isOceanBlock) {
/* 229 */             for (int i = xMin; i < xMax; i++) {
/* 231 */               double d12 = ((double)(i + xChunkPos * 16) + 0.5D - workPosX) / d6;
/* 233 */               for (int z = zMin; z < zMax; z++) {
/* 235 */                 double d13 = ((double)(z + zChunkPos * 16) + 0.5D - workPosZ) / d6;
/* 236 */                 boolean flag3 = false;
/* 238 */                 if (d12 * d12 + d13 * d13 < 1.0D)
/* 240 */                   for (int y = yMax - 1; y >= yMin; y--) {
/* 242 */                     int index = y << 8 | z << 4 | i;
/* 243 */                     double d14 = ((double)y + 0.5D - workPosY) / d7;
/* 245 */                     if (d14 > -0.7D && d12 * d12 + d14 * d14 + d13 * d13 < 1.0D) {
/* 247 */                       int randVal = random.nextInt(32768);
/* 248 */                       if (randVal == 1) {
/* 249 */                         blocksData[index] = Blocks.field_150403_cj;
/* 250 */                       } else if (randVal <= 4) {
/* 251 */                         blocksData[index] = Blocks.field_150346_d;
/* 252 */                       } else if (randVal <= 8) {
/* 253 */                         blocksData[index] = Blocks.field_150347_e;
/*     */                       } 
/*     */                     } 
/* 257 */                     index--;
/*     */                   }  
/*     */               } 
/*     */             } 
/* 263 */             if (flag)
/*     */               break; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isOceanBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ) {
/* 275 */     return (data[index] == Blocks.field_150355_j);
/*     */   }
/*     */   
/*     */   private boolean isExceptionBiome(BiomeGenBase biome) {
/* 281 */     if (biome == BiomeGenBase.field_76789_p)
/* 281 */       return true; 
/* 282 */     if (biome == BiomeGenBase.field_76787_r)
/* 282 */       return true; 
/* 283 */     if (biome == BiomeGenBase.field_76769_d)
/* 283 */       return true; 
/* 284 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isTopBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ) {
/* 291 */     BiomeGenBase biome = this.worldObj.func_72807_a(x + chunkX * 16, z + chunkZ * 16);
/* 292 */     return isExceptionBiome(biome) ? ((data[index] == Blocks.field_150349_c)) : ((data[index] == biome.field_76752_A));
/*     */   }
/*     */   
/*     */   protected void digBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
/* 312 */     data[index] = Blocks.field_150346_d;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */