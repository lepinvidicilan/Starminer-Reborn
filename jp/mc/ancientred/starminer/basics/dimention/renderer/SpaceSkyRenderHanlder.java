/*     */ package jp.mc.ancientred.starminer.basics.dimention.renderer;
/*     */ 
/*     */ import jp.mc.ancientred.starminer.basics.SMReflectionHelperClient;
/*     */ import jp.mc.ancientred.starminer.basics.dimention.MapFromSky;
/*     */ import jp.mc.ancientred.starminer.basics.dimention.WorldProviderSpace;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import net.minecraft.launchwrapper.LogWrapper;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraftforge.client.IRenderHandler;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class SpaceSkyRenderHanlder extends IRenderHandler {
/*  23 */   private static final ResourceLocation locationMoonPhasesPng = new ResourceLocation("textures/environment/moon_phases.png");
/*     */   
/*  24 */   private static final ResourceLocation locationSunPng = new ResourceLocation("textures/environment/sun.png");
/*     */   
/*  25 */   private static final ResourceLocation locationCloudsPng = new ResourceLocation("textures/environment/clouds.png");
/*     */   
/*  26 */   private static final ResourceLocation locationEndSkyPng = new ResourceLocation("textures/environment/end_sky.png");
/*     */   
/*     */   private DynamicTexture bufferedImage;
/*     */   
/*     */   private ResourceLocation skyMap;
/*     */   
/*  30 */   private int[] intArray = new int[16384];
/*     */   
/*  32 */   private int RenderGlobal_starGLCallList = -1;
/*     */   
/*  33 */   private int RenderGlobal_glSkyList = -1;
/*     */   
/*  34 */   private int RenderGlobal_glSkyList2 = -1;
/*     */   
/*     */   public void render(float partialTicks, WorldClient world, Minecraft mc) {
/*  38 */     if (this.skyMap == null) {
/*  39 */       this.bufferedImage = new DynamicTexture(128, 128);
/*  40 */       this.skyMap = mc.func_110434_K().func_110578_a("skymap", this.bufferedImage);
/*  41 */       this.intArray = this.bufferedImage.func_110565_c();
/*  42 */       for (int i = 0; i < this.intArray.length; i++)
/*  44 */         this.intArray[i] = 0; 
/*     */     } 
/*  47 */     if (this.RenderGlobal_starGLCallList == -1) {
/*  48 */       this.RenderGlobal_starGLCallList = SMReflectionHelperClient.getStarGLCallList(mc.field_71438_f);
/*  49 */       this.RenderGlobal_glSkyList = this.RenderGlobal_starGLCallList + 1;
/*  50 */       this.RenderGlobal_glSkyList2 = this.RenderGlobal_starGLCallList + 2;
/*     */     } 
/*  54 */     Tessellator tessellator1 = Tessellator.field_78398_a;
/*  56 */     GL11.glDisable(3553);
/*  60 */     float f1 = 0.0F;
/*  61 */     float f2 = 0.0F;
/*  62 */     float f3 = 0.0F;
/*  66 */     if (mc.field_71474_y.field_74337_g) {
/*  68 */       float f5 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
/*  69 */       float f6 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
/*  70 */       float f11 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
/*  71 */       f1 = f5;
/*  72 */       f2 = f6;
/*  73 */       f3 = f11;
/*     */     } 
/*  79 */     GL11.glDepthMask(false);
/*  80 */     GL11.glEnable(2912);
/*  81 */     GL11.glColor3f(f1, f2, f3);
/*  82 */     GL11.glPushMatrix();
/*  83 */     GL11.glTranslatef(0.0F, -10.0F, 0.0F);
/*  84 */     GL11.glCallList(this.RenderGlobal_glSkyList);
/*  85 */     GL11.glPopMatrix();
/*  86 */     GL11.glDisable(2912);
/*  87 */     GL11.glDisable(3008);
/*  88 */     GL11.glEnable(3042);
/*  89 */     GL11.glBlendFunc(770, 771);
/*  90 */     RenderHelper.func_74518_a();
/*  93 */     float[] afloat = world.field_73011_w.func_76560_a(world.func_72826_c(partialTicks), partialTicks);
/* 100 */     if (afloat != null) {
/* 102 */       GL11.glDisable(3553);
/* 103 */       GL11.glShadeModel(7425);
/* 104 */       GL11.glPushMatrix();
/* 105 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 106 */       GL11.glRotatef((MathHelper.func_76126_a(world.func_72929_e(partialTicks)) < 0.0F) ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
/* 107 */       GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/* 108 */       float f5 = afloat[0];
/* 109 */       float f6 = afloat[1];
/* 110 */       float f11 = afloat[2];
/* 113 */       if (mc.field_71474_y.field_74337_g) {
/* 115 */         float f12 = (f5 * 30.0F + f6 * 59.0F + f11 * 11.0F) / 100.0F;
/* 116 */         float f13 = (f5 * 30.0F + f6 * 70.0F) / 100.0F;
/* 117 */         float f19 = (f5 * 30.0F + f11 * 70.0F) / 100.0F;
/* 118 */         f5 = f12;
/* 119 */         f6 = f13;
/* 120 */         f11 = f19;
/*     */       } 
/* 123 */       tessellator1.func_78371_b(6);
/* 124 */       tessellator1.func_78369_a(f5, f6, f11, afloat[3]);
/* 125 */       tessellator1.func_78377_a(0.0D, 100.0D, 0.0D);
/* 126 */       byte b0 = 16;
/* 127 */       tessellator1.func_78369_a(afloat[0], afloat[1], afloat[2], 0.0F);
/* 129 */       for (int j = 0; j <= b0; j++) {
/* 131 */         float f19 = (float)j * 3.1415927F * 2.0F / (float)b0;
/* 132 */         float f12 = MathHelper.func_76126_a(f19);
/* 133 */         float f13 = MathHelper.func_76134_b(f19);
/* 134 */         tessellator1.func_78377_a((double)(f12 * 120.0F), (double)(f13 * 120.0F), (double)(-f13 * 40.0F * afloat[3]));
/*     */       } 
/* 137 */       tessellator1.func_78381_a();
/* 138 */       GL11.glPopMatrix();
/* 139 */       GL11.glShadeModel(7424);
/*     */     } 
/* 143 */     GL11.glEnable(3553);
/* 145 */     GL11.glBlendFunc(770, 1);
/* 146 */     GL11.glPushMatrix();
/* 148 */     float f4 = 1.0F - world.func_72867_j(partialTicks);
/* 149 */     float f7 = 0.0F;
/* 150 */     float f8 = 0.0F;
/* 151 */     float f9 = 0.0F;
/* 152 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 154 */     GL11.glTranslatef(f7, f8, f9);
/* 156 */     GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
/* 158 */     GL11.glRotatef(world.func_72826_c(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
/* 160 */     float f10 = 30.0F;
/* 161 */     mc.field_71446_o.func_110577_a(locationSunPng);
/* 162 */     tessellator1.func_78382_b();
/* 163 */     tessellator1.func_78374_a((double)-f10, 100.0D, (double)-f10, 0.0D, 0.0D);
/* 164 */     tessellator1.func_78374_a((double)f10, 100.0D, (double)-f10, 1.0D, 0.0D);
/* 165 */     tessellator1.func_78374_a((double)f10, 100.0D, (double)f10, 1.0D, 1.0D);
/* 166 */     tessellator1.func_78374_a((double)-f10, 100.0D, (double)f10, 0.0D, 1.0D);
/* 167 */     tessellator1.func_78381_a();
/* 169 */     f10 = 20.0F;
/* 170 */     mc.field_71446_o.func_110577_a(locationMoonPhasesPng);
/* 171 */     int k = world.func_72853_d();
/* 172 */     int l = k % 4;
/* 173 */     int i1 = k / 4 % 2;
/* 174 */     float f14 = (float)(l + 0) / 4.0F;
/* 175 */     float f15 = (float)(i1 + 0) / 2.0F;
/* 176 */     float f16 = (float)(l + 1) / 4.0F;
/* 177 */     float f17 = (float)(i1 + 1) / 2.0F;
/* 178 */     tessellator1.func_78382_b();
/* 179 */     tessellator1.func_78374_a((double)-f10, -100.0D, (double)f10, (double)f16, (double)f17);
/* 180 */     tessellator1.func_78374_a((double)f10, -100.0D, (double)f10, (double)f14, (double)f17);
/* 181 */     tessellator1.func_78374_a((double)f10, -100.0D, (double)-f10, (double)f14, (double)f15);
/* 182 */     tessellator1.func_78374_a((double)-f10, -100.0D, (double)-f10, (double)f16, (double)f15);
/* 183 */     tessellator1.func_78381_a();
/* 186 */     GL11.glDisable(3553);
/* 188 */     float f18 = 1.0F;
/* 189 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 190 */     GL11.glCallList(this.RenderGlobal_starGLCallList);
/* 192 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 193 */     GL11.glDisable(3042);
/* 194 */     GL11.glEnable(3008);
/* 195 */     GL11.glEnable(2912);
/* 196 */     GL11.glPopMatrix();
/* 197 */     GL11.glDisable(3553);
/* 198 */     float ca = mc.field_71441_e.func_72826_c(partialTicks);
/* 199 */     float ca2 = MathHelper.func_76134_b(ca * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/* 200 */     if (ca2 < 0.0F)
/* 200 */       ca2 = 0.0F; 
/* 201 */     if (ca2 > 1.0F)
/* 201 */       ca2 = 1.0F; 
/* 203 */     GL11.glColor3f(0.3F * ca2, 0.3F * ca2, 1.0F * ca2);
/* 211 */     GL11.glPushMatrix();
/* 212 */     GL11.glTranslatef(0.0F, 8.0F, 0.0F);
/* 213 */     GL11.glCallList(this.RenderGlobal_glSkyList2);
/* 214 */     GL11.glPopMatrix();
/* 217 */     if (world.field_73011_w.func_76561_g()) {
/* 219 */       GL11.glColor3f(f1 * 0.2F + 0.04F, f2 * 0.2F + 0.04F, f3 * 0.6F + 0.1F);
/*     */     } else {
/* 223 */       GL11.glColor3f(f1, f2, f3);
/*     */     } 
/* 230 */     GL11.glEnable(3553);
/* 232 */     GL11.glPushMatrix();
/* 233 */     renderGround(partialTicks, world, mc);
/* 234 */     GL11.glPopMatrix();
/* 236 */     double eyePosY = (mc.field_71439_g.func_70666_h(partialTicks)).field_72448_b;
/* 238 */     GL11.glDisable(3553);
/* 239 */     if (eyePosY < -20.0D) {
/* 240 */       GL11.glDepthMask(false);
/* 242 */       GL11.glPushMatrix();
/* 243 */       GL11.glEnable(2912);
/* 244 */       GL11.glEnable(3042);
/* 245 */       GL11.glBlendFunc(770, 1);
/* 246 */       float foggy = -((float)((eyePosY + 20.0D) / 28.0D));
/* 247 */       if (foggy > 1.0F)
/* 247 */         foggy = 1.0F; 
/* 248 */       GL11.glColor4f(1.0F, 0.2F, 0.1F, foggy);
/* 249 */       f8 = 10.0F;
/* 250 */       f9 = 10.0F;
/* 251 */       f10 = -f8;
/* 252 */       tessellator1.func_78382_b();
/* 253 */       tessellator1.func_78377_a((double)-f8, (double)f9, (double)f8);
/* 254 */       tessellator1.func_78377_a((double)f8, (double)f9, (double)f8);
/* 255 */       tessellator1.func_78377_a((double)f8, (double)f10, (double)f8);
/* 256 */       tessellator1.func_78377_a((double)-f8, (double)f10, (double)f8);
/* 258 */       tessellator1.func_78377_a((double)-f8, (double)f10, (double)-f8);
/* 259 */       tessellator1.func_78377_a((double)f8, (double)f10, (double)-f8);
/* 260 */       tessellator1.func_78377_a((double)f8, (double)f9, (double)-f8);
/* 261 */       tessellator1.func_78377_a((double)-f8, (double)f9, (double)-f8);
/* 263 */       tessellator1.func_78377_a((double)f8, (double)f10, (double)-f8);
/* 264 */       tessellator1.func_78377_a((double)f8, (double)f10, (double)f8);
/* 265 */       tessellator1.func_78377_a((double)f8, (double)f9, (double)f8);
/* 266 */       tessellator1.func_78377_a((double)f8, (double)f9, (double)-f8);
/* 268 */       tessellator1.func_78377_a((double)-f8, (double)f9, (double)-f8);
/* 269 */       tessellator1.func_78377_a((double)-f8, (double)f9, (double)f8);
/* 270 */       tessellator1.func_78377_a((double)-f8, (double)f10, (double)f8);
/* 271 */       tessellator1.func_78377_a((double)-f8, (double)f10, (double)-f8);
/* 273 */       tessellator1.func_78377_a((double)f8, (double)f9, (double)-f8);
/* 274 */       tessellator1.func_78377_a((double)f8, (double)f9, (double)f8);
/* 275 */       tessellator1.func_78377_a((double)-f8, (double)f9, (double)f8);
/* 276 */       tessellator1.func_78377_a((double)-f8, (double)f9, (double)-f8);
/* 278 */       tessellator1.func_78377_a((double)-f8, (double)f10, (double)-f8);
/* 279 */       tessellator1.func_78377_a((double)-f8, (double)f10, (double)f8);
/* 280 */       tessellator1.func_78377_a((double)f8, (double)f10, (double)f8);
/* 281 */       tessellator1.func_78377_a((double)f8, (double)f10, (double)-f8);
/* 282 */       tessellator1.func_78381_a();
/* 284 */       GL11.glPopMatrix();
/*     */     } 
/* 286 */     GL11.glEnable(3553);
/* 287 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 288 */     GL11.glDisable(3042);
/* 289 */     GL11.glDepthMask(true);
/*     */   }
/*     */   
/*     */   private void bindMapFromSky() {
/* 293 */     byte[] colors = MapFromSky.skyMapclientData;
/* 294 */     for (int i = 0; i < 16384; i++) {
/* 296 */       byte b0 = colors[i];
/* 298 */       if (b0 / 4 == 0) {
/* 300 */         this.intArray[i] = (i + i / 128 & 0x1) * 8 + 16 << 24;
/*     */       } else {
/* 304 */         int j = (MapColor.field_76281_a[b0 / 4]).field_76291_p;
/* 305 */         int k = b0 & 0x3;
/* 306 */         short short1 = 220;
/* 308 */         if (k == 2)
/* 310 */           short1 = 255; 
/* 313 */         if (k == 0)
/* 315 */           short1 = 180; 
/* 318 */         int l = (j >> 16 & 0xFF) * short1 / 255;
/* 319 */         int i1 = (j >> 8 & 0xFF) * short1 / 255;
/* 320 */         int j1 = (j & 0xFF) * short1 / 255;
/* 321 */         this.intArray[i] = 0xFF000000 | l << 16 | i1 << 8 | j1;
/*     */       } 
/*     */     } 
/* 324 */     this.bufferedImage.func_110564_a();
/*     */   }
/*     */   
/* 328 */   private int renderGroundCallList = -1;
/*     */   
/*     */   private void renderGround(float partialTicks, WorldClient world, Minecraft mc) {
/* 330 */     GL11.glDisable(2884);
/* 331 */     float f1 = (float)(mc.field_71451_h.field_70137_T + (mc.field_71451_h.field_70163_u - mc.field_71451_h.field_70137_T) * (double)partialTicks);
/* 332 */     byte b0 = 32;
/* 333 */     int i = 256 / b0;
/* 334 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 335 */     GL11.glEnable(3042);
/* 336 */     GL11.glBlendFunc(770, 771);
/* 337 */     Vec3 vec3 = world.func_72824_f(partialTicks);
/* 338 */     float f2 = (float)vec3.field_72450_a;
/* 339 */     float f3 = (float)vec3.field_72448_b;
/* 340 */     float f4 = (float)vec3.field_72449_c;
/* 343 */     if (mc.field_71474_y.field_74337_g) {
/* 345 */       float f11 = (f2 * 30.0F + f3 * 59.0F + f4 * 11.0F) / 100.0F;
/* 346 */       float f6 = (f2 * 30.0F + f3 * 70.0F) / 100.0F;
/* 347 */       float f7 = (f2 * 30.0F + f4 * 70.0F) / 100.0F;
/* 348 */       f2 = f11;
/* 349 */       f3 = f6;
/* 350 */       f4 = f7;
/*     */     } 
/* 352 */     GL11.glDepthMask(false);
/* 353 */     float f5 = 0.001953125F;
/* 355 */     double d0 = (double)((float)WorldProviderSpace.cloudTickCounter + partialTicks);
/* 356 */     double d1 = mc.field_71451_h.field_70169_q + (mc.field_71451_h.field_70165_t - mc.field_71451_h.field_70169_q) * (double)partialTicks + d0 * 0.029999999329447746D;
/* 357 */     double d2 = mc.field_71451_h.field_70166_s + (mc.field_71451_h.field_70161_v - mc.field_71451_h.field_70166_s) * (double)partialTicks;
/* 358 */     int j = MathHelper.func_76128_c(d1 / 2048.0D);
/* 359 */     int k = MathHelper.func_76128_c(d2 / 2048.0D);
/* 360 */     d1 -= (double)(j * 2048);
/* 361 */     d2 -= (double)(k * 2048);
/* 362 */     float f8 = -22.0F;
/* 363 */     float f9 = (float)(d1 * (double)f5);
/* 364 */     float f10 = (float)(d2 * (double)f5);
/* 367 */     GL11.glColor4f(f2, f3, f4, 0.8F);
/* 368 */     if (MapFromSky.hasSkyMapImageData) {
/* 370 */       GL11.glColor4f(f2, f3, f4, 0.8F);
/* 371 */       if (this.renderGroundCallList == -1 || MapFromSky.doRecompileSkyMapList) {
/* 372 */         LogWrapper.log(Level.INFO, "[Starminer]Compiling gllist for ground texture..", new Object[0]);
/* 374 */         bindMapFromSky();
/* 376 */         if (MapFromSky.doRecompileSkyMapList && this.renderGroundCallList != -1 && GL11.glIsList(this.renderGroundCallList)) {
/* 378 */           GL11.glDeleteLists(this.renderGroundCallList, 1);
/* 380 */           LogWrapper.log(Level.INFO, "[Starminer]Deleted compiled gllist for ground texture..", new Object[0]);
/* 382 */           MapFromSky.doRecompileSkyMapList = false;
/*     */         } 
/* 384 */         this.renderGroundCallList = GLAllocation.func_74526_a(1);
/* 385 */         GL11.glNewList(this.renderGroundCallList, 4864);
/* 387 */         int ii = i *= 8;
/* 388 */         tessellator.func_78382_b();
/* 389 */         mc.field_71446_o.func_110577_a(this.skyMap);
/* 391 */         double ff5 = (double)(f5 * 4.0F);
/* 392 */         for (int m = -b0 * ii; m < b0 * ii; m += b0) {
/* 394 */           for (int i1 = -b0 * ii; i1 < b0 * ii; i1 += b0) {
/* 396 */             tessellator.func_78374_a((double)(m + 0), (double)f8, (double)(i1 + b0), (double)(float)(m + 0) * ff5 + 0.5D, (double)(float)(i1 + b0) * ff5 + 0.5D);
/* 397 */             tessellator.func_78374_a((double)(m + b0), (double)f8, (double)(i1 + b0), (double)(float)(m + b0) * ff5 + 0.5D, (double)(float)(i1 + b0) * ff5 + 0.5D);
/* 398 */             tessellator.func_78374_a((double)(m + b0), (double)f8, (double)(i1 + 0), (double)(float)(m + b0) * ff5 + 0.5D, (double)(float)(i1 + 0) * ff5 + 0.5D);
/* 399 */             tessellator.func_78374_a((double)(m + 0), (double)f8, (double)(i1 + 0), (double)(float)(m + 0) * ff5 + 0.5D, (double)(float)(i1 + 0) * ff5 + 0.5D);
/*     */           } 
/*     */         } 
/* 403 */         tessellator.func_78381_a();
/* 404 */         GL11.glEndList();
/* 405 */         LogWrapper.log(Level.INFO, "[Starminer]Compiled gllist for ground texture, done.", new Object[0]);
/*     */       } else {
/* 407 */         GL11.glCallList(this.renderGroundCallList);
/*     */       } 
/*     */     } 
/* 412 */     f9 = (float)(d0 * 3.0E-5D);
/* 413 */     f10 = 0.0F;
/* 414 */     mc.field_71446_o.func_110577_a(locationCloudsPng);
/* 415 */     tessellator.func_78382_b();
/* 416 */     for (int l = -b0 * i; l < b0 * i; l += b0) {
/* 418 */       for (int i1 = -b0 * i; i1 < b0 * i; i1 += b0) {
/* 420 */         tessellator.func_78374_a((double)(l + 0), (double)f8, (double)(i1 + b0), (double)((float)(l + 0) * f5 + f9), (double)((float)(i1 + b0) * f5 + f10));
/* 421 */         tessellator.func_78374_a((double)(l + b0), (double)f8, (double)(i1 + b0), (double)((float)(l + b0) * f5 + f9), (double)((float)(i1 + b0) * f5 + f10));
/* 422 */         tessellator.func_78374_a((double)(l + b0), (double)f8, (double)(i1 + 0), (double)((float)(l + b0) * f5 + f9), (double)((float)(i1 + 0) * f5 + f10));
/* 423 */         tessellator.func_78374_a((double)(l + 0), (double)f8, (double)(i1 + 0), (double)((float)(l + 0) * f5 + f9), (double)((float)(i1 + 0) * f5 + f10));
/*     */       } 
/*     */     } 
/* 426 */     tessellator.func_78381_a();
/* 428 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 429 */     GL11.glDisable(3042);
/* 430 */     GL11.glEnable(2884);
/* 431 */     GL11.glDepthMask(true);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */