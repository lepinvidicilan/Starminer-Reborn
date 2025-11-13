/*     */ package jp.mc.ancientred.starminer.basics.structure;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.structure.MapGenStructure;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ import net.minecraft.world.gen.structure.StructureStart;
/*     */ 
/*     */ public class MapGenStar extends MapGenStructure {
/*  25 */   public int maxDistanceBetweenStars = 16;
/*     */   
/*  26 */   public int minDistanceBetweenStars = 8;
/*     */   
/*     */   public MapGenStar() {}
/*     */   
/*     */   public MapGenStar(Map par1Map) {
/*  33 */     this();
/*  34 */     Iterator<Map.Entry> iterator = par1Map.entrySet().iterator();
/*  36 */     while (iterator.hasNext()) {
/*  38 */       Map.Entry entry = iterator.next();
/*  40 */       if (((String)entry.getKey()).equals("distance"))
/*  42 */         this.maxDistanceBetweenStars = MathHelper.func_82714_a((String)entry.getValue(), this.maxDistanceBetweenStars, this.minDistanceBetweenStars + 1); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean generateStructuresImmidiateInChunk(World par1World, Random par2Random, int par3, int par4, Block[] blocksData, byte[] blockMetas) {
/*  51 */     int k = par3 << 4;
/*  52 */     int l = par4 << 4;
/*  53 */     boolean flag = false;
/*  54 */     Iterator<StructureStarStart> iterator = this.field_75053_d.values().iterator();
/*  56 */     while (iterator.hasNext()) {
/*  58 */       StructureStarStart structurestart = iterator.next();
/*  60 */       if (structurestart.func_75069_d() && structurestart.func_75071_a().func_78885_a(k, l, k + 15, l + 15)) {
/*  62 */         structurestart.generateStructureImmidiate(par1World, par2Random, new StructureBoundingBox(k, l, k + 15, l + 15), blocksData, blockMetas);
/*  63 */         flag = true;
/*     */       } 
/*     */     } 
/*  67 */     return flag;
/*     */   }
/*     */   
/*     */   public String func_143025_a() {
/*  74 */     return "Star";
/*     */   }
/*     */   
/*     */   protected boolean func_75047_a(int parX, int parY) {
/*  79 */     int k = parX;
/*  80 */     int l = parY;
/*  82 */     if (parX < 0)
/*  84 */       parX -= this.maxDistanceBetweenStars - 1; 
/*  87 */     if (parY < 0)
/*  89 */       parY -= this.maxDistanceBetweenStars - 1; 
/*  92 */     int i1 = parX / this.maxDistanceBetweenStars;
/*  93 */     int j1 = parY / this.maxDistanceBetweenStars;
/*  94 */     Random random = this.field_75039_c.func_72843_D(i1, j1, 58939324);
/*  95 */     i1 *= this.maxDistanceBetweenStars;
/*  96 */     j1 *= this.maxDistanceBetweenStars;
/*  97 */     i1 += random.nextInt(this.maxDistanceBetweenStars - this.minDistanceBetweenStars);
/*  98 */     j1 += random.nextInt(this.maxDistanceBetweenStars - this.minDistanceBetweenStars);
/* 100 */     if (k == i1 && l == j1)
/* 102 */       return true; 
/* 105 */     return false;
/*     */   }
/*     */   
/*     */   protected StructureStart func_75049_b(int par1, int par2) {
/* 110 */     return new StructureStarStart(this.field_75039_c, this.field_75038_b, par1, par2);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */