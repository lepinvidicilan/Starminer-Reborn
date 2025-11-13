/*     */ package jp.mc.ancientred.starminer.basics.block.render;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraftforge.fluids.BlockFluidBase;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class BlockLiquieHookRenderHelper implements ISimpleBlockRenderingHandler {
/*     */   public static final int RENDER_TYPE = 4341900;
/*     */   
/*     */   public int getRenderId() {
/*  26 */     return 4341900;
/*     */   }
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/*  30 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  31 */     block.func_149683_g();
/*  32 */     renderer.func_147775_a(block);
/*  33 */     GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  34 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  35 */     tessellator.func_78382_b();
/*  36 */     tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
/*  37 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 0, metadata));
/*  38 */     tessellator.func_78381_a();
/*  39 */     tessellator.func_78382_b();
/*  40 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/*  41 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 1, metadata));
/*  42 */     tessellator.func_78381_a();
/*  43 */     tessellator.func_78382_b();
/*  44 */     tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*  45 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 2, metadata));
/*  46 */     tessellator.func_78381_a();
/*  47 */     tessellator.func_78382_b();
/*  48 */     tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/*  49 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 3, metadata));
/*  50 */     tessellator.func_78381_a();
/*  51 */     tessellator.func_78382_b();
/*  52 */     tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
/*  53 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 4, metadata));
/*  54 */     tessellator.func_78381_a();
/*  55 */     tessellator.func_78382_b();
/*  56 */     tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
/*  57 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 5, metadata));
/*  58 */     tessellator.func_78381_a();
/*  59 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */   
/*  61 */   private static Vec3[] vec = new Vec3[60];
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int parX, int parY, int parZ, Block block, int modelId, RenderBlocks renderer) {
/*  64 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  65 */     int l = block.func_149720_d(renderer.field_147845_a, parX, parY, parZ);
/*  66 */     float f = (float)(l >> 16 & 0xFF) / 255.0F;
/*  67 */     float f1 = (float)(l >> 8 & 0xFF) / 255.0F;
/*  68 */     float f2 = (float)(l & 0xFF) / 255.0F;
/*  69 */     boolean flag = block.func_149646_a(renderer.field_147845_a, parX, parY + 1, parZ, 1);
/*  70 */     boolean flag1 = block.func_149646_a(renderer.field_147845_a, parX, parY - 1, parZ, 0);
/*  71 */     boolean[] aboolean = { block.func_149646_a(renderer.field_147845_a, parX, parY, parZ - 1, 2), block.func_149646_a(renderer.field_147845_a, parX, parY, parZ + 1, 3), block.func_149646_a(renderer.field_147845_a, parX - 1, parY, parZ, 4), block.func_149646_a(renderer.field_147845_a, parX + 1, parY, parZ, 5) };
/*  76 */     if (!flag && !flag1 && !aboolean[0] && !aboolean[1] && !aboolean[2] && !aboolean[3])
/*  78 */       return false; 
/*  82 */     boolean flag2 = false;
/*  83 */     float f3 = 0.5F;
/*  84 */     float f4 = 1.0F;
/*  85 */     float f5 = 0.8F;
/*  86 */     float f6 = 0.6F;
/*  87 */     double d0 = 0.0D;
/*  88 */     double d1 = 1.0D;
/*  89 */     int i1 = renderer.field_147845_a.func_72805_g(parX, parY, parZ);
/*  90 */     double d2 = 1.0D;
/*  91 */     double d3 = 1.0D;
/*  92 */     double d4 = 1.0D;
/*  93 */     double d5 = 1.0D;
/*  94 */     double d6 = 0.0010000000474974513D;
/*  99 */     if (renderer.field_147837_f || flag) {
/*     */       double d7, d8, d9, d10, d11, d12, d13, d14;
/* 101 */       flag2 = true;
/* 102 */       IIcon icon = renderer.func_147787_a(block, 1, i1);
/* 103 */       float f10 = (float)BlockFluidBase.getFlowDirection(renderer.field_147845_a, parX, parY, parZ);
/* 107 */       if (f10 > -999.0F)
/* 109 */         icon = renderer.func_147787_a(block, 1, i1); 
/* 112 */       d2 -= d6;
/* 113 */       d3 -= d6;
/* 114 */       d4 -= d6;
/* 115 */       d5 -= d6;
/* 125 */       if (f10 < -999.0F) {
/* 127 */         d8 = (double)icon.func_94214_a(0.0D);
/* 128 */         d12 = (double)icon.func_94207_b(0.0D);
/* 129 */         d7 = d8;
/* 130 */         d11 = (double)icon.func_94207_b(16.0D);
/* 131 */         d10 = (double)icon.func_94214_a(16.0D);
/* 132 */         d14 = d11;
/* 133 */         d9 = d10;
/* 134 */         d13 = d12;
/*     */       } else {
/* 138 */         float f11 = MathHelper.func_76126_a(f10) * 0.25F;
/* 139 */         float f8 = MathHelper.func_76134_b(f10) * 0.25F;
/* 140 */         float f7 = 8.0F;
/* 141 */         d8 = (double)icon.func_94214_a((double)(8.0F + (-f8 - f11) * 16.0F));
/* 142 */         d12 = (double)icon.func_94207_b((double)(8.0F + (-f8 + f11) * 16.0F));
/* 143 */         d7 = (double)icon.func_94214_a((double)(8.0F + (-f8 + f11) * 16.0F));
/* 144 */         d11 = (double)icon.func_94207_b((double)(8.0F + (f8 + f11) * 16.0F));
/* 145 */         d10 = (double)icon.func_94214_a((double)(8.0F + (f8 + f11) * 16.0F));
/* 146 */         d14 = (double)icon.func_94207_b((double)(8.0F + (f8 - f11) * 16.0F));
/* 147 */         d9 = (double)icon.func_94214_a((double)(8.0F + (f8 - f11) * 16.0F));
/* 148 */         d13 = (double)icon.func_94207_b((double)(8.0F + (-f8 - f11) * 16.0F));
/*     */       } 
/* 151 */       tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, parX, parY, parZ));
/* 152 */       float f9 = 1.0F;
/* 153 */       tessellator.func_78386_a(f4 * f9 * f, f4 * f9 * f1, f4 * f9 * f2);
/* 154 */       tessellator.func_78374_a((double)(parX + 0), (double)parY + d2, (double)(parZ + 0), d8, d12);
/* 155 */       tessellator.func_78374_a((double)(parX + 0), (double)parY + d3, (double)(parZ + 1), d7, d11);
/* 156 */       tessellator.func_78374_a((double)(parX + 1), (double)parY + d4, (double)(parZ + 1), d10, d14);
/* 157 */       tessellator.func_78374_a((double)(parX + 1), (double)parY + d5, (double)(parZ + 0), d9, d13);
/*     */     } 
/* 160 */     if (renderer.field_147837_f || flag1) {
/* 162 */       tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, parX, parY - 1, parZ));
/* 163 */       float f11 = 1.0F;
/* 164 */       tessellator.func_78386_a(f3 * f11, f3 * f11, f3 * f11);
/* 165 */       renderer.func_147768_a(block, (double)parX, (double)parY + d6, (double)parZ, renderer.func_147777_a(block, 0));
/* 166 */       flag2 = true;
/*     */     } 
/* 169 */     for (int j1 = 0; j1 < 4; j1++) {
/* 171 */       int k1 = parX;
/* 172 */       int l1 = parZ;
/* 174 */       if (j1 == 0)
/* 176 */         l1 = parZ - 1; 
/* 179 */       if (j1 == 1)
/* 181 */         l1++; 
/* 184 */       if (j1 == 2)
/* 186 */         k1 = parX - 1; 
/* 189 */       if (j1 == 3)
/* 191 */         k1++; 
/* 194 */       IIcon icon1 = renderer.func_147787_a(block, 1, i1);
/* 196 */       if (renderer.field_147837_f || aboolean[j1]) {
/*     */         double d15, d16, d17, d18, d19, d20;
/* 205 */         if (j1 == 0) {
/* 207 */           d15 = d2;
/* 208 */           d17 = d5;
/* 209 */           d16 = (double)parX;
/* 210 */           d18 = (double)(parX + 1);
/* 211 */           d19 = (double)parZ + d6;
/* 212 */           d20 = (double)parZ + d6;
/* 214 */         } else if (j1 == 1) {
/* 216 */           d15 = d4;
/* 217 */           d17 = d3;
/* 218 */           d16 = (double)(parX + 1);
/* 219 */           d18 = (double)parX;
/* 220 */           d19 = (double)(parZ + 1) - d6;
/* 221 */           d20 = (double)(parZ + 1) - d6;
/* 223 */         } else if (j1 == 2) {
/* 225 */           d15 = d3;
/* 226 */           d17 = d2;
/* 227 */           d16 = (double)parX + d6;
/* 228 */           d18 = (double)parX + d6;
/* 229 */           d19 = (double)(parZ + 1);
/* 230 */           d20 = (double)parZ;
/*     */         } else {
/* 234 */           d15 = d5;
/* 235 */           d17 = d4;
/* 236 */           d16 = (double)(parX + 1) - d6;
/* 237 */           d18 = (double)(parX + 1) - d6;
/* 238 */           d19 = (double)parZ;
/* 239 */           d20 = (double)(parZ + 1);
/*     */         } 
/* 242 */         flag2 = true;
/* 243 */         float f12 = icon1.func_94214_a(0.0D);
/* 244 */         float f9 = icon1.func_94214_a(8.0D);
/* 245 */         float f8 = icon1.func_94207_b((1.0D - d15) * 16.0D * 0.5D);
/* 246 */         float f7 = icon1.func_94207_b((1.0D - d17) * 16.0D * 0.5D);
/* 247 */         float f13 = icon1.func_94207_b(8.0D);
/* 248 */         tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, k1, parY, l1));
/* 249 */         float f14 = 1.0F;
/* 251 */         if (j1 < 2) {
/* 253 */           f14 *= f5;
/*     */         } else {
/* 257 */           f14 *= f6;
/*     */         } 
/* 260 */         tessellator.func_78386_a(f4 * f14 * f, f4 * f14 * f1, f4 * f14 * f2);
/* 261 */         tessellator.func_78374_a(d16, (double)parY + d15, d19, (double)f12, (double)f8);
/* 262 */         tessellator.func_78374_a(d18, (double)parY + d17, d20, (double)f9, (double)f7);
/* 263 */         tessellator.func_78374_a(d18, (double)(parY + 0), d20, (double)f9, (double)f13);
/* 264 */         tessellator.func_78374_a(d16, (double)(parY + 0), d19, (double)f12, (double)f13);
/*     */       } 
/*     */     } 
/* 268 */     renderer.field_147855_j = d0;
/* 269 */     renderer.field_147857_k = d1;
/* 270 */     return flag2;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 276 */     return true;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */