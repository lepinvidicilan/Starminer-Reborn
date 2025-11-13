/*     */ package jp.mc.ancientred.starminer.basics.dimention;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ public class WorldProviderSpace extends WorldProvider implements IZeroGravityWorldProvider {
/*  23 */   public static int cloudTickCounter = 0;
/*     */   
/*     */   public void func_76572_b() {
/*  30 */     this.field_76578_c = new WorldChunkManagerSpace(BiomeGenBase.field_76779_k, 0.5F, 0.0F);
/*  32 */     this.field_76576_e = false;
/*     */   }
/*     */   
/*     */   public String func_80007_l() {
/*  37 */     return "GeostationaryOrbit";
/*     */   }
/*     */   
/*     */   public IChunkProvider func_76555_c() {
/*  42 */     return new ChunkProviderSpace(this.field_76579_a, this.field_76579_a.func_72905_C());
/*     */   }
/*     */   
/*     */   public boolean func_76567_e() {
/*  47 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_76569_d() {
/*  52 */     return true;
/*     */   }
/*     */   
/*     */   public double getHorizon() {
/*  57 */     return 100.0D;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_76568_b(int par1, int par2) {
/*  62 */     return false;
/*     */   }
/*     */   
/*     */   public void updateWeather() {
/*  66 */     super.updateWeather();
/*  68 */     if (this.field_76579_a.field_72995_K) {
/*  70 */       cloudTickCounter++;
/*  75 */       IChunkProvider chunkProvider = this.field_76579_a.func_72863_F();
/*  76 */       if (chunkProvider instanceof net.minecraft.client.multiplayer.ChunkProviderClient)
/*  77 */         SMModContainer.proxy.canselLightGapUpdate(chunkProvider); 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Vec3 drawClouds(float partialTicks) {
/*  84 */     float f1 = this.field_76579_a.func_72826_c(partialTicks);
/*  85 */     float f2 = MathHelper.func_76134_b(f1 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/*  87 */     if (f2 < 0.0F)
/*  89 */       f2 = 0.0F; 
/*  92 */     if (f2 > 1.0F)
/*  94 */       f2 = 1.0F; 
/*  97 */     float f3 = 1.0F;
/*  98 */     float f4 = 1.0F;
/*  99 */     float f5 = 1.0F;
/* 100 */     float f6 = 1.0F;
/* 102 */     f3 *= f2 * 0.9F + 0.1F;
/* 103 */     f4 *= f2 * 0.9F + 0.1F;
/* 104 */     f5 *= f2 * 0.85F + 0.15F;
/* 106 */     return Vec3.func_72443_a((double)f3, (double)f4, (double)f5);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */