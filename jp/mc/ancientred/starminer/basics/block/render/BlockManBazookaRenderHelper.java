/*     */ package jp.mc.ancientred.starminer.basics.block.render;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import jp.mc.ancientred.starminer.basics.common.VecUtils;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockCauldron;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ public class BlockManBazookaRenderHelper implements ISimpleBlockRenderingHandler {
/*     */   public static final int RENDER_TYPE = 4341806;
/*     */   
/*     */   public int getRenderId() {
/*  21 */     return 4341806;
/*     */   }
/*     */   
/*     */   public void renderInventoryBlock(Block par1Block, int metadata, int modelID, RenderBlocks renderer) {}
/*     */   
/*  27 */   private static Vec3[] vec = new Vec3[60];
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/*     */     int i;
/*  30 */     double mainHight = 1.2D;
/*  32 */     double tNTHight = 0.4D;
/*  33 */     double sleeveHight = 0.1D;
/*  34 */     double bodyHight = mainHight - sleeveHight;
/*  36 */     double marginLw = 0.1D;
/*  37 */     double marginHi = 1.0D - marginLw;
/*  38 */     double inbound = 0.1D;
/*  39 */     int meta = world.func_72805_g(x, y, z);
/*  41 */     double lw = 0.2D;
/*  42 */     double hi = 1.0D - lw;
/*  43 */     float roatateX = 0.0F;
/*  44 */     float roatateZ = 0.0F;
/*  47 */     IIcon iconCauldron = BlockCauldron.func_150026_e("inner");
/*  48 */     IIcon iconTnt = Blocks.field_150335_W.func_149691_a(2, 0);
/*  49 */     IIcon iconIron = Blocks.field_150385_bj.func_149691_a(0, 0);
/*  50 */     float cDRNmaxU = iconCauldron.func_94212_f();
/*  51 */     float cDRNmaxV = iconCauldron.func_94210_h();
/*  52 */     float cDRNminV = iconCauldron.func_94206_g();
/*  53 */     float cDRNminU = iconCauldron.func_94209_e();
/*  55 */     float tNTmaxU = iconTnt.func_94212_f();
/*  56 */     float tNTmaxV = iconTnt.func_94207_b(2.0D);
/*  57 */     float tNTminU = iconTnt.func_94209_e();
/*  58 */     float tNTminV = iconTnt.func_94207_b(4.0D);
/*  60 */     float iRONmaxU = iconIron.func_94212_f();
/*  61 */     float iRONmaxV = iconIron.func_94207_b(2.0D);
/*  62 */     float iRONminU = iconIron.func_94209_e();
/*  63 */     float iRONminV = iconIron.func_94207_b(4.0D);
/*  65 */     Tessellator tes = Tessellator.field_78398_a;
/*  66 */     int brightness = block.func_149677_c(world, x, y, z);
/*  67 */     tes.func_78380_c(brightness);
/*  68 */     tes.func_78386_a(1.0F, 1.0F, 1.0F);
/*  70 */     double xMin = marginLw + inbound;
/*  71 */     double xMax = marginHi - inbound;
/*  73 */     int vecCount = 0;
/*  74 */     vec[vecCount++] = VecUtils.createVec3(xMax, tNTHight, marginHi);
/*  75 */     vec[vecCount++] = VecUtils.createVec3(xMin, bodyHight, marginHi);
/*  76 */     vec[vecCount++] = VecUtils.createVec3(xMax, 0.0D, marginHi);
/*  77 */     vec[vecCount++] = VecUtils.createVec3(xMin, tNTHight, marginHi);
/*  78 */     vec[vecCount++] = VecUtils.createVec3(xMax, bodyHight, marginHi);
/*  79 */     vec[vecCount++] = VecUtils.createVec3(xMin, mainHight, marginHi);
/*  81 */     vec[vecCount++] = VecUtils.createVec3(xMax, tNTHight, marginLw);
/*  82 */     vec[vecCount++] = VecUtils.createVec3(xMin, bodyHight, marginLw);
/*  83 */     vec[vecCount++] = VecUtils.createVec3(xMax, 0.0D, marginLw);
/*  84 */     vec[vecCount++] = VecUtils.createVec3(xMin, tNTHight, marginLw);
/*  85 */     vec[vecCount++] = VecUtils.createVec3(xMax, bodyHight, marginLw);
/*  86 */     vec[vecCount++] = VecUtils.createVec3(xMin, mainHight, marginLw);
/*  88 */     double zMin = marginLw + inbound;
/*  89 */     double zMax = marginHi - inbound;
/*  91 */     vec[vecCount++] = VecUtils.createVec3(marginLw, tNTHight, zMax);
/*  92 */     vec[vecCount++] = VecUtils.createVec3(marginLw, bodyHight, zMin);
/*  93 */     vec[vecCount++] = VecUtils.createVec3(marginLw, 0.0D, zMax);
/*  94 */     vec[vecCount++] = VecUtils.createVec3(marginLw, tNTHight, zMin);
/*  95 */     vec[vecCount++] = VecUtils.createVec3(marginLw, bodyHight, zMax);
/*  96 */     vec[vecCount++] = VecUtils.createVec3(marginLw, mainHight, zMin);
/*  98 */     vec[vecCount++] = VecUtils.createVec3(marginHi, tNTHight, zMax);
/*  99 */     vec[vecCount++] = VecUtils.createVec3(marginHi, bodyHight, zMin);
/* 100 */     vec[vecCount++] = VecUtils.createVec3(marginHi, 0.0D, zMax);
/* 101 */     vec[vecCount++] = VecUtils.createVec3(marginHi, tNTHight, zMin);
/* 102 */     vec[vecCount++] = VecUtils.createVec3(marginHi, bodyHight, zMax);
/* 103 */     vec[vecCount++] = VecUtils.createVec3(marginHi, mainHight, zMin);
/* 104 */     xMin = marginLw;
/* 105 */     xMax = marginLw + inbound;
/* 106 */     zMin = marginHi - inbound;
/* 107 */     zMax = marginHi;
/* 109 */     vec[vecCount++] = VecUtils.createVec3(xMax, tNTHight, zMax);
/* 110 */     vec[vecCount++] = VecUtils.createVec3(xMin, bodyHight, zMin);
/* 111 */     vec[vecCount++] = VecUtils.createVec3(xMax, 0.0D, zMax);
/* 112 */     vec[vecCount++] = VecUtils.createVec3(xMin, tNTHight, zMin);
/* 113 */     vec[vecCount++] = VecUtils.createVec3(xMax, bodyHight, zMax);
/* 114 */     vec[vecCount++] = VecUtils.createVec3(xMin, mainHight, zMin);
/* 115 */     xMin = marginHi - inbound;
/* 116 */     xMax = marginHi;
/* 117 */     zMin = marginHi - inbound;
/* 118 */     zMax = marginHi;
/* 120 */     vec[vecCount++] = VecUtils.createVec3(xMax, tNTHight, zMin);
/* 121 */     vec[vecCount++] = VecUtils.createVec3(xMin, bodyHight, zMax);
/* 122 */     vec[vecCount++] = VecUtils.createVec3(xMax, 0.0D, zMin);
/* 123 */     vec[vecCount++] = VecUtils.createVec3(xMin, tNTHight, zMax);
/* 124 */     vec[vecCount++] = VecUtils.createVec3(xMax, bodyHight, zMin);
/* 125 */     vec[vecCount++] = VecUtils.createVec3(xMin, mainHight, zMax);
/* 127 */     xMin = marginLw;
/* 128 */     xMax = marginLw + inbound;
/* 129 */     zMin = marginLw;
/* 130 */     zMax = marginLw + inbound;
/* 132 */     vec[vecCount++] = VecUtils.createVec3(xMax, tNTHight, zMin);
/* 133 */     vec[vecCount++] = VecUtils.createVec3(xMin, bodyHight, zMax);
/* 134 */     vec[vecCount++] = VecUtils.createVec3(xMax, 0.0D, zMin);
/* 135 */     vec[vecCount++] = VecUtils.createVec3(xMin, tNTHight, zMax);
/* 136 */     vec[vecCount++] = VecUtils.createVec3(xMax, bodyHight, zMin);
/* 137 */     vec[vecCount++] = VecUtils.createVec3(xMin, mainHight, zMax);
/* 139 */     xMin = marginHi - inbound;
/* 140 */     xMax = marginHi;
/* 141 */     zMin = marginLw;
/* 142 */     zMax = marginLw + inbound;
/* 144 */     vec[vecCount++] = VecUtils.createVec3(xMax, tNTHight, zMax);
/* 145 */     vec[vecCount++] = VecUtils.createVec3(xMin, bodyHight, zMin);
/* 146 */     vec[vecCount++] = VecUtils.createVec3(xMax, 0.0D, zMax);
/* 147 */     vec[vecCount++] = VecUtils.createVec3(xMin, tNTHight, zMin);
/* 148 */     vec[vecCount++] = VecUtils.createVec3(xMax, bodyHight, zMax);
/* 149 */     vec[vecCount++] = VecUtils.createVec3(xMin, mainHight, zMin);
/* 152 */     double tntSize = 0.35D;
/* 155 */     switch (meta) {
/*     */       case 0:
/* 157 */         for (i = 0; i < vecCount; i += 2) {
/*     */           float maxU, maxV, minU, minV;
/* 158 */           int A = i, B = i + 1;
/* 159 */           (vec[A]).field_72450_a += (double)x;
/* 159 */           (vec[A]).field_72448_b += (double)y;
/* 159 */           (vec[A]).field_72449_c += (double)z;
/* 160 */           (vec[B]).field_72450_a += (double)x;
/* 160 */           (vec[B]).field_72448_b += (double)y;
/* 160 */           (vec[B]).field_72449_c += (double)z;
/* 162 */           if (i % 6 == 0) {
/* 163 */             maxU = cDRNmaxU;
/* 164 */             maxV = cDRNmaxV;
/* 165 */             minU = cDRNminU;
/* 166 */             minV = cDRNminV;
/* 167 */           } else if (i % 6 == 2) {
/* 168 */             maxU = tNTmaxU;
/* 169 */             maxV = tNTmaxV;
/* 170 */             minU = tNTminU;
/* 171 */             minV = tNTminV;
/*     */           } else {
/* 173 */             maxU = iRONmaxU;
/* 174 */             maxV = iRONmaxV;
/* 175 */             minU = iRONminU;
/* 176 */             minV = iRONminV;
/*     */           } 
/* 178 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)maxU, (double)maxV);
/* 179 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[B]).field_72448_b, (vec[A]).field_72449_c, (double)maxU, (double)minV);
/* 180 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)minU, (double)minV);
/* 181 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[A]).field_72448_b, (vec[B]).field_72449_c, (double)minU, (double)maxV);
/* 183 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[A]).field_72448_b, (vec[B]).field_72449_c, (double)maxU, (double)maxV);
/* 184 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)maxU, (double)minV);
/* 185 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[B]).field_72448_b, (vec[A]).field_72449_c, (double)minU, (double)minV);
/* 186 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)minU, (double)maxV);
/*     */         } 
/* 188 */         renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, lw, 1.0D);
/* 189 */         renderer.func_147757_a(Blocks.field_150417_aV.func_149691_a(0, 0));
/* 190 */         renderer.func_147784_q(block, x, y, z);
/* 192 */         renderer.func_147757_a(Blocks.field_150335_W.func_149691_a(2, 0));
/* 193 */         renderer.func_147782_a(0.5D - tntSize, 0.05D, 0.5D - tntSize, 0.5D + tntSize, 0.05D + tntSize, 0.5D + tntSize);
/* 194 */         renderer.func_147784_q(block, x, y, z);
/*     */         break;
/*     */       case 1:
/* 198 */         for (i = 0; i < vecCount; i += 2) {
/*     */           float maxU, maxV, minU, minV;
/* 199 */           int A = i, B = i + 1;
/* 200 */           (vec[A]).field_72448_b = 1.0D - (vec[A]).field_72448_b;
/* 201 */           (vec[B]).field_72448_b = 1.0D - (vec[B]).field_72448_b;
/* 203 */           (vec[A]).field_72450_a += (double)x;
/* 203 */           (vec[A]).field_72448_b += (double)y;
/* 203 */           (vec[A]).field_72449_c += (double)z;
/* 204 */           (vec[B]).field_72450_a += (double)x;
/* 204 */           (vec[B]).field_72448_b += (double)y;
/* 204 */           (vec[B]).field_72449_c += (double)z;
/* 206 */           if (i % 6 == 0) {
/* 207 */             maxU = cDRNmaxU;
/* 208 */             maxV = cDRNmaxV;
/* 209 */             minU = cDRNminU;
/* 210 */             minV = cDRNminV;
/* 211 */           } else if (i % 6 == 2) {
/* 212 */             maxU = tNTmaxU;
/* 213 */             maxV = tNTmaxV;
/* 214 */             minU = tNTminU;
/* 215 */             minV = tNTminV;
/*     */           } else {
/* 217 */             maxU = iRONmaxU;
/* 218 */             maxV = iRONmaxV;
/* 219 */             minU = iRONminU;
/* 220 */             minV = iRONminV;
/*     */           } 
/* 222 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)maxU, (double)maxV);
/* 223 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[B]).field_72448_b, (vec[A]).field_72449_c, (double)maxU, (double)minV);
/* 224 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)minU, (double)minV);
/* 225 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[A]).field_72448_b, (vec[B]).field_72449_c, (double)minU, (double)maxV);
/* 227 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[A]).field_72448_b, (vec[B]).field_72449_c, (double)maxU, (double)maxV);
/* 228 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)maxU, (double)minV);
/* 229 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[B]).field_72448_b, (vec[A]).field_72449_c, (double)minU, (double)minV);
/* 230 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)minU, (double)maxV);
/*     */         } 
/* 232 */         renderer.func_147782_a(0.0D, hi, 0.0D, 1.0D, 1.0D, 1.0D);
/* 233 */         renderer.func_147757_a(Blocks.field_150417_aV.func_149691_a(0, 0));
/* 234 */         renderer.func_147784_q(block, x, y, z);
/* 236 */         renderer.func_147757_a(Blocks.field_150335_W.func_149691_a(2, 0));
/* 237 */         renderer.func_147782_a(0.5D - tntSize, 0.95D - tntSize, 0.5D - tntSize, 0.5D + tntSize, 0.95D, 0.5D + tntSize);
/* 238 */         renderer.func_147784_q(block, x, y, z);
/*     */         break;
/*     */       case 2:
/* 242 */         for (i = 0; i < vecCount; i += 2) {
/*     */           float maxU, maxV, minU, minV;
/* 243 */           int A = i, B = i + 1;
/* 244 */           double swap = (vec[A]).field_72450_a;
/* 245 */           (vec[A]).field_72450_a = (vec[A]).field_72448_b;
/* 246 */           (vec[A]).field_72448_b = swap;
/* 248 */           swap = (vec[B]).field_72450_a;
/* 249 */           (vec[B]).field_72450_a = (vec[B]).field_72448_b;
/* 250 */           (vec[B]).field_72448_b = swap;
/* 252 */           (vec[A]).field_72450_a += (double)x;
/* 252 */           (vec[A]).field_72448_b += (double)y;
/* 252 */           (vec[A]).field_72449_c += (double)z;
/* 253 */           (vec[B]).field_72450_a += (double)x;
/* 253 */           (vec[B]).field_72448_b += (double)y;
/* 253 */           (vec[B]).field_72449_c += (double)z;
/* 255 */           if (i % 6 == 0) {
/* 256 */             maxU = cDRNmaxU;
/* 257 */             maxV = cDRNmaxV;
/* 258 */             minU = cDRNminU;
/* 259 */             minV = cDRNminV;
/* 260 */           } else if (i % 6 == 2) {
/* 261 */             maxU = tNTmaxU;
/* 262 */             maxV = tNTmaxV;
/* 263 */             minU = tNTminU;
/* 264 */             minV = tNTminV;
/*     */           } else {
/* 266 */             maxU = iRONmaxU;
/* 267 */             maxV = iRONmaxV;
/* 268 */             minU = iRONminU;
/* 269 */             minV = iRONminV;
/*     */           } 
/* 271 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)maxU, (double)maxV);
/* 272 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)maxU, (double)minV);
/* 273 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)minU, (double)minV);
/* 274 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)minU, (double)maxV);
/* 276 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)maxU, (double)maxV);
/* 277 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)maxU, (double)minV);
/* 278 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)minU, (double)minV);
/* 279 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)minU, (double)maxV);
/*     */         } 
/* 281 */         renderer.func_147782_a(0.0D, 0.0D, 0.0D, lw, 1.0D, 1.0D);
/* 282 */         renderer.func_147757_a(Blocks.field_150417_aV.func_149691_a(0, 0));
/* 283 */         renderer.func_147784_q(block, x, y, z);
/* 285 */         renderer.func_147757_a(Blocks.field_150335_W.func_149691_a(2, 0));
/* 286 */         renderer.func_147782_a(0.05D, 0.5D - tntSize, 0.5D - tntSize, 0.05D + tntSize, 0.5D + tntSize, 0.5D + tntSize);
/* 287 */         renderer.func_147784_q(block, x, y, z);
/*     */         break;
/*     */       case 3:
/* 290 */         for (i = 0; i < vecCount; i += 2) {
/*     */           float maxU, maxV, minU, minV;
/* 291 */           int A = i, B = i + 1;
/* 294 */           double swap = (vec[A]).field_72450_a;
/* 295 */           (vec[A]).field_72450_a = 1.0D - (vec[A]).field_72448_b;
/* 296 */           (vec[A]).field_72448_b = swap;
/* 298 */           swap = (vec[B]).field_72450_a;
/* 299 */           (vec[B]).field_72450_a = 1.0D - (vec[B]).field_72448_b;
/* 300 */           (vec[B]).field_72448_b = swap;
/* 302 */           (vec[A]).field_72450_a += (double)x;
/* 302 */           (vec[A]).field_72448_b += (double)y;
/* 302 */           (vec[A]).field_72449_c += (double)z;
/* 303 */           (vec[B]).field_72450_a += (double)x;
/* 303 */           (vec[B]).field_72448_b += (double)y;
/* 303 */           (vec[B]).field_72449_c += (double)z;
/* 305 */           if (i % 6 == 0) {
/* 306 */             maxU = cDRNmaxU;
/* 307 */             maxV = cDRNmaxV;
/* 308 */             minU = cDRNminU;
/* 309 */             minV = cDRNminV;
/* 310 */           } else if (i % 6 == 2) {
/* 311 */             maxU = tNTmaxU;
/* 312 */             maxV = tNTmaxV;
/* 313 */             minU = tNTminU;
/* 314 */             minV = tNTminV;
/*     */           } else {
/* 316 */             maxU = iRONmaxU;
/* 317 */             maxV = iRONmaxV;
/* 318 */             minU = iRONminU;
/* 319 */             minV = iRONminV;
/*     */           } 
/* 321 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)maxU, (double)maxV);
/* 322 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)maxU, (double)minV);
/* 323 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)minU, (double)minV);
/* 324 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)minU, (double)maxV);
/* 326 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)maxU, (double)maxV);
/* 327 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)maxU, (double)minV);
/* 328 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)minU, (double)minV);
/* 329 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)minU, (double)maxV);
/*     */         } 
/* 331 */         renderer.func_147782_a(hi, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 332 */         renderer.func_147757_a(Blocks.field_150417_aV.func_149691_a(0, 0));
/* 333 */         renderer.func_147784_q(block, x, y, z);
/* 335 */         renderer.func_147757_a(Blocks.field_150335_W.func_149691_a(2, 0));
/* 336 */         renderer.func_147782_a(0.95D - tntSize, 0.5D - tntSize, 0.5D - tntSize, 0.95D, 0.5D + tntSize, 0.5D + tntSize);
/* 337 */         renderer.func_147784_q(block, x, y, z);
/*     */         break;
/*     */       case 4:
/* 340 */         for (i = 0; i < vecCount; i += 2) {
/*     */           float maxU, maxV, minU, minV;
/* 341 */           int A = i, B = i + 1;
/* 344 */           double swap = (vec[A]).field_72449_c;
/* 345 */           (vec[A]).field_72449_c = (vec[A]).field_72448_b;
/* 346 */           (vec[A]).field_72448_b = swap;
/* 348 */           swap = (vec[B]).field_72449_c;
/* 349 */           (vec[B]).field_72449_c = (vec[B]).field_72448_b;
/* 350 */           (vec[B]).field_72448_b = swap;
/* 352 */           (vec[A]).field_72450_a += (double)x;
/* 352 */           (vec[A]).field_72448_b += (double)y;
/* 352 */           (vec[A]).field_72449_c += (double)z;
/* 353 */           (vec[B]).field_72450_a += (double)x;
/* 353 */           (vec[B]).field_72448_b += (double)y;
/* 353 */           (vec[B]).field_72449_c += (double)z;
/* 355 */           if (i % 6 == 0) {
/* 356 */             maxU = cDRNmaxU;
/* 357 */             maxV = cDRNmaxV;
/* 358 */             minU = cDRNminU;
/* 359 */             minV = cDRNminV;
/* 360 */           } else if (i % 6 == 2) {
/* 361 */             maxU = tNTmaxU;
/* 362 */             maxV = tNTmaxV;
/* 363 */             minU = tNTminU;
/* 364 */             minV = tNTminV;
/*     */           } else {
/* 366 */             maxU = iRONmaxU;
/* 367 */             maxV = iRONmaxV;
/* 368 */             minU = iRONminU;
/* 369 */             minV = iRONminV;
/*     */           } 
/* 371 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)maxU, (double)maxV);
/* 372 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[B]).field_72449_c, (double)maxU, (double)minV);
/* 373 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)minU, (double)minV);
/* 374 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[A]).field_72449_c, (double)minU, (double)maxV);
/* 376 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[A]).field_72449_c, (double)maxU, (double)maxV);
/* 377 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)maxU, (double)minV);
/* 378 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[B]).field_72449_c, (double)minU, (double)minV);
/* 379 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)minU, (double)maxV);
/*     */         } 
/* 381 */         renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, lw);
/* 382 */         renderer.func_147757_a(Blocks.field_150417_aV.func_149691_a(0, 0));
/* 383 */         renderer.func_147784_q(block, x, y, z);
/* 385 */         renderer.func_147757_a(Blocks.field_150335_W.func_149691_a(2, 0));
/* 386 */         renderer.func_147782_a(0.5D - tntSize, 0.5D - tntSize, 0.05D, 0.5D + tntSize, 0.5D + tntSize, 0.05D + tntSize);
/* 387 */         renderer.func_147784_q(block, x, y, z);
/*     */         break;
/*     */       case 5:
/* 390 */         for (i = 0; i < vecCount; i += 2) {
/*     */           float maxU, maxV, minU, minV;
/* 391 */           int A = i, B = i + 1;
/* 394 */           double swap = (vec[A]).field_72449_c;
/* 395 */           (vec[A]).field_72449_c = 1.0D - (vec[A]).field_72448_b;
/* 396 */           (vec[A]).field_72448_b = swap;
/* 398 */           swap = (vec[B]).field_72449_c;
/* 399 */           (vec[B]).field_72449_c = 1.0D - (vec[B]).field_72448_b;
/* 400 */           (vec[B]).field_72448_b = swap;
/* 402 */           (vec[A]).field_72450_a += (double)x;
/* 402 */           (vec[A]).field_72448_b += (double)y;
/* 402 */           (vec[A]).field_72449_c += (double)z;
/* 403 */           (vec[B]).field_72450_a += (double)x;
/* 403 */           (vec[B]).field_72448_b += (double)y;
/* 403 */           (vec[B]).field_72449_c += (double)z;
/* 405 */           if (i % 6 == 0) {
/* 406 */             maxU = cDRNmaxU;
/* 407 */             maxV = cDRNmaxV;
/* 408 */             minU = cDRNminU;
/* 409 */             minV = cDRNminV;
/* 410 */           } else if (i % 6 == 2) {
/* 411 */             maxU = tNTmaxU;
/* 412 */             maxV = tNTmaxV;
/* 413 */             minU = tNTminU;
/* 414 */             minV = tNTminV;
/*     */           } else {
/* 416 */             maxU = iRONmaxU;
/* 417 */             maxV = iRONmaxV;
/* 418 */             minU = iRONminU;
/* 419 */             minV = iRONminV;
/*     */           } 
/* 421 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)maxU, (double)maxV);
/* 422 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[B]).field_72449_c, (double)maxU, (double)minV);
/* 423 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)minU, (double)minV);
/* 424 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[A]).field_72449_c, (double)minU, (double)maxV);
/* 426 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[A]).field_72449_c, (double)maxU, (double)maxV);
/* 427 */           tes.func_78374_a((vec[B]).field_72450_a, (vec[B]).field_72448_b, (vec[B]).field_72449_c, (double)maxU, (double)minV);
/* 428 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[B]).field_72449_c, (double)minU, (double)minV);
/* 429 */           tes.func_78374_a((vec[A]).field_72450_a, (vec[A]).field_72448_b, (vec[A]).field_72449_c, (double)minU, (double)maxV);
/*     */         } 
/* 431 */         renderer.func_147782_a(0.0D, 0.0D, hi, 1.0D, 1.0D, 1.0D);
/* 432 */         renderer.func_147757_a(Blocks.field_150417_aV.func_149691_a(0, 0));
/* 433 */         renderer.func_147784_q(block, x, y, z);
/* 435 */         renderer.func_147757_a(Blocks.field_150335_W.func_149691_a(2, 0));
/* 436 */         renderer.func_147782_a(0.5D - tntSize, 0.5D - tntSize, 0.95D - tntSize, 0.5D + tntSize, 0.5D + tntSize, 0.95D);
/* 437 */         renderer.func_147784_q(block, x, y, z);
/*     */         break;
/*     */     } 
/* 442 */     renderer.func_147771_a();
/* 443 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 448 */     return false;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */