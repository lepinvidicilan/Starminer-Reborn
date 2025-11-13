/*    */ package jp.mc.ancientred.starminer.basics.dimention;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.ChunkPosition;
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ import net.minecraft.world.biome.WorldChunkManager;
/*    */ 
/*    */ public class WorldChunkManagerSpace extends WorldChunkManager {
/*    */   private BiomeGenBase biomeToUse;
/*    */   
/*    */   private float rainfall;
/*    */   
/*    */   public WorldChunkManagerSpace(BiomeGenBase par1BiomeGenBase, float par2, float par3) {
/* 21 */     this.biomeToUse = par1BiomeGenBase;
/* 22 */     this.rainfall = par3;
/*    */   }
/*    */   
/*    */   public BiomeGenBase func_76935_a(int par1, int par2) {
/* 30 */     return this.biomeToUse;
/*    */   }
/*    */   
/*    */   public BiomeGenBase[] func_76937_a(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
/* 38 */     if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
/* 40 */       par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5]; 
/* 43 */     Arrays.fill(par1ArrayOfBiomeGenBase, 0, par4 * par5, this.biomeToUse);
/* 44 */     return par1ArrayOfBiomeGenBase;
/*    */   }
/*    */   
/*    */   public float[] func_76936_a(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
/* 52 */     if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
/* 54 */       par1ArrayOfFloat = new float[par4 * par5]; 
/* 57 */     Arrays.fill(par1ArrayOfFloat, 0, par4 * par5, this.rainfall);
/* 58 */     return par1ArrayOfFloat;
/*    */   }
/*    */   
/*    */   public BiomeGenBase[] func_76933_b(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
/* 67 */     if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
/* 69 */       par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5]; 
/* 72 */     Arrays.fill(par1ArrayOfBiomeGenBase, 0, par4 * par5, this.biomeToUse);
/* 73 */     return par1ArrayOfBiomeGenBase;
/*    */   }
/*    */   
/*    */   public BiomeGenBase[] func_76931_a(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6) {
/* 82 */     return func_76933_b(par1ArrayOfBiomeGenBase, par2, par3, par4, par5);
/*    */   }
/*    */   
/*    */   public ChunkPosition func_150795_a(int par1, int par2, int par3, List par4List, Random par5Random) {
/* 91 */     return par4List.contains(this.biomeToUse) ? new ChunkPosition(par1 - par3 + par5Random.nextInt(par3 * 2 + 1), 0, par2 - par3 + par5Random.nextInt(par3 * 2 + 1)) : null;
/*    */   }
/*    */   
/*    */   public boolean func_76940_a(int par1, int par2, int par3, List par4List) {
/* 99 */     return par4List.contains(this.biomeToUse);
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */