/*     */ package jp.mc.ancientred.starminer.basics.dummy;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.shader.TesselatorVertexState;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TesselatorWrapper extends Tessellator {
/*     */   private Tessellator wrapped;
/*     */   
/*     */   private GravityDirection gdir;
/*     */   
/*     */   private double centerX;
/*     */   
/*     */   private double centerY;
/*     */   
/*     */   private double centerZ;
/*     */   
/*  19 */   private double[] conv = new double[3];
/*     */   
/*     */   public Tessellator wrap(Tessellator wrapped, GravityDirection gdir, double centerX, double centerY, double centerZ) {
/*  23 */     this.wrapped = wrapped;
/*  24 */     this.gdir = gdir;
/*  25 */     this.centerX = centerX;
/*  26 */     this.centerY = centerY;
/*  27 */     this.centerZ = centerZ;
/*  28 */     return this;
/*     */   }
/*     */   
/*     */   public void func_78374_a(double parX, double parY, double parZ, double texU, double texV) {
/*  35 */     double[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  36 */     parX = rotated[0];
/*  36 */     parY = rotated[1];
/*  36 */     parZ = rotated[2];
/*  38 */     this.wrapped.func_78374_a(parX, parY, parZ, texU, texV);
/*     */   }
/*     */   
/*     */   public void func_78377_a(double parX, double parY, double parZ) {
/*  46 */     double[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  47 */     parX = rotated[0];
/*  47 */     parY = rotated[1];
/*  47 */     parZ = rotated[2];
/*  49 */     this.wrapped.func_78377_a(parX, parY, parZ);
/*     */   }
/*     */   
/*     */   public int func_78381_a() {
/*  56 */     return this.wrapped.func_78381_a();
/*     */   }
/*     */   
/*     */   public TesselatorVertexState func_147564_a(float p_147564_1_, float p_147564_2_, float p_147564_3_) {
/*  61 */     return this.wrapped.func_147564_a(p_147564_1_, p_147564_2_, p_147564_3_);
/*     */   }
/*     */   
/*     */   public void func_147565_a(TesselatorVertexState p_147565_1_) {
/*  66 */     this.wrapped.func_147565_a(p_147565_1_);
/*     */   }
/*     */   
/*     */   public void func_78382_b() {
/*  74 */     this.wrapped.func_78382_b();
/*     */   }
/*     */   
/*     */   public void func_78371_b(int p_78371_1_) {
/*  82 */     this.wrapped.func_78371_b(p_78371_1_);
/*     */   }
/*     */   
/*     */   public void func_78385_a(double p_78385_1_, double p_78385_3_) {
/*  90 */     this.wrapped.func_78385_a(p_78385_1_, p_78385_3_);
/*     */   }
/*     */   
/*     */   public void func_78380_c(int p_78380_1_) {
/*  95 */     this.wrapped.func_78380_c(p_78380_1_);
/*     */   }
/*     */   
/*     */   public void func_78386_a(float p_78386_1_, float p_78386_2_, float p_78386_3_) {
/* 103 */     this.wrapped.func_78386_a(p_78386_1_, p_78386_2_, p_78386_3_);
/*     */   }
/*     */   
/*     */   public void func_78369_a(float p_78369_1_, float p_78369_2_, float p_78369_3_, float p_78369_4_) {
/* 111 */     this.wrapped.func_78369_a(p_78369_1_, p_78369_2_, p_78369_3_, p_78369_4_);
/*     */   }
/*     */   
/*     */   public void func_78376_a(int p_78376_1_, int p_78376_2_, int p_78376_3_) {
/* 119 */     this.wrapped.func_78376_a(p_78376_1_, p_78376_2_, p_78376_3_);
/*     */   }
/*     */   
/*     */   public void func_78370_a(int p_78370_1_, int p_78370_2_, int p_78370_3_, int p_78370_4_) {
/* 127 */     this.wrapped.func_78370_a(p_78370_1_, p_78370_2_, p_78370_3_, p_78370_4_);
/*     */   }
/*     */   
/*     */   public void func_154352_a(byte p_154352_1_, byte p_154352_2_, byte p_154352_3_) {
/* 132 */     this.wrapped.func_154352_a(p_154352_1_, p_154352_2_, p_154352_3_);
/*     */   }
/*     */   
/*     */   public void func_78378_d(int p_78378_1_) {
/* 140 */     this.wrapped.func_78378_d(p_78378_1_);
/*     */   }
/*     */   
/*     */   public void func_78384_a(int p_78384_1_, int p_78384_2_) {
/* 148 */     this.wrapped.func_78384_a(p_78384_1_, p_78384_2_);
/*     */   }
/*     */   
/*     */   public void func_78383_c() {
/* 156 */     this.wrapped.func_78383_c();
/*     */   }
/*     */   
/*     */   public void func_78375_b(float p_78375_1_, float p_78375_2_, float p_78375_3_) {
/* 164 */     this.wrapped.func_78375_b(p_78375_1_, p_78375_2_, p_78375_3_);
/*     */   }
/*     */   
/*     */   public void func_78373_b(double p_78373_1_, double p_78373_3_, double p_78373_5_) {
/* 172 */     this.wrapped.func_78373_b(p_78373_1_, p_78373_3_, p_78373_5_);
/*     */   }
/*     */   
/*     */   public void func_78372_c(float p_78372_1_, float p_78372_2_, float p_78372_3_) {
/* 180 */     this.wrapped.func_78372_c(p_78372_1_, p_78372_2_, p_78372_3_);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */