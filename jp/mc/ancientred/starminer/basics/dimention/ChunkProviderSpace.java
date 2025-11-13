/*     */ package jp.mc.ancientred.starminer.basics.dimention;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import jp.mc.ancientred.starminer.basics.entity.EntityStarSquid;
/*     */ import jp.mc.ancientred.starminer.basics.structure.MapGenStar;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockSand;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.world.ChunkPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ public class ChunkProviderSpace implements IChunkProvider {
/*     */   private Random spaceRNG;
/*     */   
/*     */   private World spaceWorld;
/*     */   
/*     */   private double[] densities;
/*     */   
/*  25 */   private MapGenAsteroidBelt asteroidBeltGenerator = new MapGenAsteroidBelt();
/*     */   
/*  26 */   private MapGenStar starGenerator = new MapGenStar();
/*     */   
/*     */   protected List spawnableListSpace;
/*     */   
/*     */   private BiomeGenBase[] biomesForGeneration;
/*     */   
/*     */   public ChunkProviderSpace(World par1World, long par2) {
/*  34 */     this.spaceWorld = par1World;
/*  35 */     this.spaceRNG = new Random(par2);
/*  36 */     this.starGenerator.maxDistanceBetweenStars = 15;
/*  37 */     this.starGenerator.minDistanceBetweenStars = 4;
/*  38 */     this.spawnableListSpace = new ArrayList();
/*  39 */     this.spawnableListSpace.add(new BiomeGenBase.SpawnListEntry(EntityStarSquid.class, 10, 4, 4));
/*     */   }
/*     */   
/*     */   public void generateTerrain(int xChunkPos, int zChunkPos, byte[] par3ArrayOfByte) {}
/*     */   
/*     */   public void replaceBlocksForBiome(int xChunkPos, int zChunkPos, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase) {}
/*     */   
/*     */   public Chunk func_73158_c(int par1, int par2) {
/*  55 */     return func_73154_d(par1, par2);
/*     */   }
/*     */   
/*     */   public Chunk func_73154_d(int xChunkPos, int zChunkPos) {
/*  64 */     this.spaceRNG.setSeed((long)xChunkPos * 341873128712L + (long)zChunkPos * 132897987541L);
/*  65 */     Block[] blocksData = new Block[65536];
/*  66 */     byte[] blockMetas = new byte[65536];
/*  67 */     this.biomesForGeneration = this.spaceWorld.func_72959_q().func_76933_b(this.biomesForGeneration, xChunkPos * 16, zChunkPos * 16, 16, 16);
/*  69 */     this.asteroidBeltGenerator.generate(this, this.spaceWorld, xChunkPos, zChunkPos, blocksData);
/*  70 */     this.starGenerator.func_151539_a(this, this.spaceWorld, xChunkPos, zChunkPos, null);
/*  71 */     this.starGenerator.generateStructuresImmidiateInChunk(this.spaceWorld, this.spaceRNG, xChunkPos, zChunkPos, blocksData, blockMetas);
/*  75 */     Chunk chunk = new Chunk(this.spaceWorld, xChunkPos, zChunkPos);
/*  76 */     ChunkHelper.fillinChunk(chunk, this.spaceWorld, blocksData, blockMetas, xChunkPos, zChunkPos);
/*  77 */     byte[] abyte1 = chunk.func_76605_m();
/*  78 */     for (int k = 0; k < abyte1.length; k++)
/*  80 */       abyte1[k] = (byte)(this.biomesForGeneration[k]).field_76756_M; 
/*  83 */     chunk.func_76603_b();
/*  84 */     return chunk;
/*     */   }
/*     */   
/*     */   public boolean func_73149_a(int par1, int par2) {
/*  92 */     return true;
/*     */   }
/*     */   
/*     */   public void func_73153_a(IChunkProvider par1IChunkProvider, int xChunkPos, int zChunkPos) {
/* 100 */     BlockSand.field_149832_M = true;
/* 101 */     int k = xChunkPos * 16;
/* 102 */     int l = zChunkPos * 16;
/* 106 */     this.starGenerator.func_75051_a(this.spaceWorld, this.spaceRNG, xChunkPos, zChunkPos);
/* 108 */     BlockSand.field_149832_M = false;
/*     */   }
/*     */   
/*     */   public boolean func_73151_a(boolean par1, IProgressUpdate par2IProgressUpdate) {
/* 117 */     return true;
/*     */   }
/*     */   
/*     */   public void func_104112_b() {}
/*     */   
/*     */   public boolean func_73156_b() {
/* 131 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_73157_c() {
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   public String func_73148_d() {
/* 147 */     return "RandomLevelSource";
/*     */   }
/*     */   
/*     */   public List func_73155_a(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
/* 155 */     return null;
/*     */   }
/*     */   
/*     */   public ChunkPosition func_147416_a(World par1World, String par2Str, int par3, int par4, int par5) {
/* 164 */     return null;
/*     */   }
/*     */   
/*     */   public int func_73152_e() {
/* 169 */     return 0;
/*     */   }
/*     */   
/*     */   public void func_82695_e(int par1, int par2) {}
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */