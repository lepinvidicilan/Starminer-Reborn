/*     */ package jp.mc.ancientred.starminer.core;
/*     */ 
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.api.IRotateSleepingViewHandler;
/*     */ import jp.mc.ancientred.starminer.core.common.VecUtils;
/*     */ import jp.mc.ancientred.starminer.core.entity.ExtendedPropertyGravity;
/*     */ import jp.mc.ancientred.starminer.core.obfuscar.SMCoreReflectionHelper;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class TransformClientHelper {
/*     */   public static void orientCameraByGravity(float par1) {
/*  24 */     Minecraft mc = Minecraft.func_71410_x();
/*  25 */     EntityLivingBase entityLivingBase = mc.field_71451_h;
/*  26 */     ExtendedPropertyGravity gravity = ExtendedPropertyGravity.getExtendedPropertyGravity((Entity)mc.field_71439_g);
/*  28 */     GravityDirection dir = gravity.gravityDirection;
/*  30 */     GL11.glRotatef(180.0F * dir.rotX, 1.0F, 0.0F, 0.0F);
/*  31 */     GL11.glRotatef(180.0F * dir.rotZ, 0.0F, 0.0F, 1.0F);
/*  33 */     float pitch = entityLivingBase.field_70127_C + (entityLivingBase.field_70125_A - entityLivingBase.field_70127_C) * par1;
/*  34 */     GL11.glRotatef(pitch * dir.pitchRotDirX, 1.0F, 0.0F, 0.0F);
/*  35 */     GL11.glRotatef(pitch * dir.pitchRotDirY, 0.0F, 1.0F, 0.0F);
/*  37 */     float yaw = entityLivingBase.field_70126_B + (entityLivingBase.field_70177_z - entityLivingBase.field_70126_B) * par1 + 180.0F;
/*  38 */     GL11.glRotatef(yaw * dir.yawRotDirX, 1.0F, 0.0F, 0.0F);
/*  39 */     GL11.glRotatef(yaw * dir.yawRotDirY, 0.0F, 1.0F, 0.0F);
/*  40 */     GL11.glRotatef(yaw * dir.yawRotDirZ, 0.0F, 0.0F, 1.0F);
/*  42 */     float fixHeight = entityLivingBase.field_70129_M - entityLivingBase.field_70130_N / 2.0F;
/*  43 */     GL11.glTranslatef(fixHeight * dir.shiftEyeX, fixHeight * dir.shiftEyeY, fixHeight * dir.shiftEyeZ);
/*  48 */     GL11.glTranslatef(entityLivingBase.field_70139_V * dir.shiftSneakX, entityLivingBase.field_70139_V * dir.shiftSneakY, entityLivingBase.field_70139_V * dir.shiftSneakZ);
/*  53 */     if (gravity.turnRate < 1.0F)
/*  54 */       GL11.glRotatef(90.0F * (gravity.prevTurnRate + (gravity.turnRate - gravity.prevTurnRate) * par1), gravity.onChangeRoatDirX, gravity.onChangeRoatDirY, gravity.onChangeRoatDirZ); 
/*     */   }
/*     */   
/*     */   public static void rotateCorpseByGravity(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/*  60 */     switch (ExtendedPropertyGravity.getGravityDirection((Entity)par1EntityLivingBase)) {
/*     */       case southTOnorth_ZN:
/*  62 */         GL11.glTranslatef(0.0F, par1EntityLivingBase.field_70130_N / 2.0F, -par1EntityLivingBase.field_70130_N / 2.0F);
/*  63 */         GL11.glRotatef(180.0F - par3, 0.0F, 0.0F, 1.0F);
/*  64 */         GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/*     */         break;
/*     */       case northTOsouth_ZP:
/*  67 */         GL11.glTranslatef(0.0F, par1EntityLivingBase.field_70130_N / 2.0F, par1EntityLivingBase.field_70130_N / 2.0F);
/*  68 */         GL11.glRotatef(180.0F + par3, 0.0F, 0.0F, 1.0F);
/*  69 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*     */         break;
/*     */       case westTOeast_XP:
/*  72 */         GL11.glTranslatef(par1EntityLivingBase.field_70130_N / 2.0F, par1EntityLivingBase.field_70130_N / 2.0F, 0.0F);
/*  73 */         GL11.glRotatef(180.0F + par3, 1.0F, 0.0F, 0.0F);
/*  74 */         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
/*     */         break;
/*     */       case eastTOwest_XN:
/*  77 */         GL11.glTranslatef(-par1EntityLivingBase.field_70130_N / 2.0F, par1EntityLivingBase.field_70130_N / 2.0F, 0.0F);
/*  78 */         GL11.glRotatef(180.0F - par3, 1.0F, 0.0F, 0.0F);
/*  79 */         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
/*     */         break;
/*     */       case downTOup_YP:
/*  82 */         GL11.glTranslatef(0.0F, par1EntityLivingBase.field_70131_O, 0.0F);
/*  83 */         GL11.glRotatef(180.0F + par3, 0.0F, 1.0F, 0.0F);
/*  84 */         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
/*     */         break;
/*     */       default:
/*  87 */         GL11.glRotatef(180.0F - par3, 0.0F, 1.0F, 0.0F);
/*     */         break;
/*     */     } 
/*  93 */     if (par1EntityLivingBase.field_70725_aQ > 0) {
/*  95 */       float f3 = ((float)par1EntityLivingBase.field_70725_aQ + par4 - 1.0F) / 20.0F * 1.6F;
/*  96 */       f3 = MathHelper.func_76129_c(f3);
/*  98 */       if (f3 > 1.0F)
/* 100 */         f3 = 1.0F; 
/* 102 */       if (par1EntityLivingBase instanceof net.minecraft.entity.monster.EntitySpider || par1EntityLivingBase instanceof net.minecraft.entity.monster.EntitySilverfish) {
/* 103 */         GL11.glRotatef(f3 * 180.0F, 0.0F, 0.0F, 1.0F);
/*     */       } else {
/* 105 */         GL11.glRotatef(f3 * 90.0F, 0.0F, 0.0F, 1.0F);
/*     */       } 
/*     */     } else {
/* 110 */       String s = EnumChatFormatting.func_110646_a(par1EntityLivingBase.func_70005_c_());
/* 112 */       if ((s.equals("Dinnerbone") || s.equals("Grumm")) && (!(par1EntityLivingBase instanceof EntityPlayer) || !((EntityPlayer)par1EntityLivingBase).func_82238_cc())) {
/* 114 */         GL11.glTranslatef(0.0F, par1EntityLivingBase.field_70131_O + 0.1F, 0.0F);
/* 115 */         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setFlyMovementByGravity(EntityPlayer entityPlayer) {
/* 121 */     SMCoreModContainer.proxy.setFlyMovementByGravity(entityPlayer);
/*     */   }
/*     */   
/*     */   public static boolean roatate3rdPersonViewByGravity(double d3, float par1) {
/*     */     double d4, d5, d6;
/*     */     float zVal, zFix, yVal, yFix, xVal, xFix;
/* 124 */     Minecraft mc = Minecraft.func_71410_x();
/* 125 */     EntityLivingBase entitylivingbase = mc.field_71451_h;
/* 126 */     ExtendedPropertyGravity gravity = ExtendedPropertyGravity.getExtendedPropertyGravity((Entity)mc.field_71451_h);
/* 128 */     float f1 = entitylivingbase.field_70129_M - 1.62F;
/* 129 */     double d0 = entitylivingbase.field_70169_q + (entitylivingbase.field_70165_t - entitylivingbase.field_70169_q) * (double)par1;
/* 130 */     double d1 = entitylivingbase.field_70167_r + (entitylivingbase.field_70163_u - entitylivingbase.field_70167_r) * (double)par1 - (double)f1;
/* 131 */     double d2 = entitylivingbase.field_70166_s + (entitylivingbase.field_70161_v - entitylivingbase.field_70166_s) * (double)par1;
/* 133 */     float f3 = entitylivingbase.field_70177_z;
/* 134 */     float f2 = entitylivingbase.field_70125_A;
/* 136 */     if (mc.field_71474_y.field_74320_O == 2)
/* 138 */       f2 += 180.0F; 
/* 150 */     switch (ExtendedPropertyGravity.getGravityDirection((Entity)entitylivingbase)) {
/*     */       case southTOnorth_ZN:
/* 152 */         zVal = MathHelper.func_76126_a(-f2 * 0.017453292F);
/* 153 */         zFix = MathHelper.func_76134_b(-f2 * 0.017453292F);
/* 154 */         yVal = MathHelper.func_76134_b(-f3 * 0.017453292F - 3.1415927F);
/* 155 */         xVal = -MathHelper.func_76126_a(-f3 * 0.017453292F - 3.1415927F);
/* 156 */         d4 = (double)(xVal * zFix) * d3;
/* 157 */         d6 = (double)(yVal * zFix) * d3;
/* 158 */         d5 = (double)zVal * d3;
/*     */         break;
/*     */       case northTOsouth_ZP:
/* 161 */         zVal = -MathHelper.func_76126_a(-f2 * 0.017453292F);
/* 162 */         zFix = MathHelper.func_76134_b(-f2 * 0.017453292F);
/* 163 */         yVal = -MathHelper.func_76134_b(-f3 * 0.017453292F - 3.1415927F);
/* 164 */         xVal = -MathHelper.func_76126_a(-f3 * 0.017453292F - 3.1415927F);
/* 165 */         d4 = (double)(xVal * zFix) * d3;
/* 166 */         d6 = (double)(yVal * zFix) * d3;
/* 167 */         d5 = (double)zVal * d3;
/*     */         break;
/*     */       case westTOeast_XP:
/* 170 */         xVal = -MathHelper.func_76126_a(-f2 * 0.017453292F);
/* 171 */         xFix = MathHelper.func_76134_b(-f2 * 0.017453292F);
/* 172 */         zVal = -MathHelper.func_76134_b(-f3 * 0.017453292F - 3.1415927F);
/* 173 */         yVal = -MathHelper.func_76126_a(-f3 * 0.017453292F - 3.1415927F);
/* 174 */         d4 = (double)xVal * d3;
/* 175 */         d6 = (double)(yVal * xFix) * d3;
/* 176 */         d5 = (double)(zVal * xFix) * d3;
/*     */         break;
/*     */       case eastTOwest_XN:
/* 179 */         xVal = MathHelper.func_76126_a(-f2 * 0.017453292F);
/* 180 */         xFix = MathHelper.func_76134_b(-f2 * 0.017453292F);
/* 181 */         zVal = -MathHelper.func_76134_b(-f3 * 0.017453292F - 3.1415927F);
/* 182 */         yVal = MathHelper.func_76126_a(-f3 * 0.017453292F - 3.1415927F);
/* 183 */         d4 = (double)xVal * d3;
/* 184 */         d6 = (double)(yVal * xFix) * d3;
/* 185 */         d5 = (double)(zVal * xFix) * d3;
/*     */         break;
/*     */       case downTOup_YP:
/* 188 */         yVal = -MathHelper.func_76126_a(-f2 * 0.017453292F);
/* 189 */         yFix = MathHelper.func_76134_b(-f2 * 0.017453292F);
/* 190 */         zVal = MathHelper.func_76134_b(-f3 * 0.017453292F - 3.1415927F);
/* 191 */         xVal = -MathHelper.func_76126_a(-f3 * 0.017453292F - 3.1415927F);
/* 192 */         d4 = (double)(xVal * yFix) * d3;
/* 193 */         d6 = (double)yVal * d3;
/* 194 */         d5 = (double)(zVal * yFix) * d3;
/*     */         break;
/*     */       default:
/* 197 */         yVal = MathHelper.func_76126_a(-f2 * 0.017453292F);
/* 198 */         yFix = -MathHelper.func_76134_b(-f2 * 0.017453292F);
/* 199 */         zVal = MathHelper.func_76134_b(-f3 * 0.017453292F - 3.1415927F);
/* 200 */         xVal = MathHelper.func_76126_a(-f3 * 0.017453292F - 3.1415927F);
/* 201 */         d4 = (double)(xVal * yFix) * d3;
/* 202 */         d6 = (double)yVal * d3;
/* 203 */         d5 = (double)(zVal * yFix) * d3;
/*     */         break;
/*     */     } 
/* 206 */     Vec3 vecEye = TransformUtils.fixEyePositionByGravityClient((Entity)entitylivingbase, VecUtils.createVec3(d0, d1, d2));
/* 208 */     for (int l = 0; l < 8; l++) {
/* 210 */       float f4 = (float)((l & 0x1) * 2 - 1);
/* 211 */       float f5 = (float)((l >> 1 & 0x1) * 2 - 1);
/* 212 */       float f6 = (float)((l >> 2 & 0x1) * 2 - 1);
/* 213 */       f4 *= 0.1F;
/* 214 */       f5 *= 0.1F;
/* 215 */       f6 *= 0.1F;
/* 216 */       MovingObjectPosition movingobjectposition = mc.field_71441_e.func_72933_a(VecUtils.createVec3(vecEye.field_72450_a + (double)f4, vecEye.field_72448_b + (double)f5, vecEye.field_72449_c + (double)f6), VecUtils.createVec3(vecEye.field_72450_a - d4 + (double)f4 + (double)f6, vecEye.field_72448_b - d6 + (double)f5, vecEye.field_72449_c - d5 + (double)f6));
/* 226 */       if (movingobjectposition != null) {
/* 228 */         double d7 = movingobjectposition.field_72307_f.func_72438_d(vecEye);
/* 230 */         if (d7 < d3)
/* 232 */           d3 = d7; 
/*     */       } 
/*     */     } 
/* 237 */     if (mc.field_71474_y.field_74320_O == 2)
/* 239 */       GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); 
/* 242 */     GL11.glRotatef(entitylivingbase.field_70125_A - f2, 1.0F, 0.0F, 0.0F);
/* 243 */     GL11.glRotatef(entitylivingbase.field_70177_z - f3, 0.0F, 1.0F, 0.0F);
/* 244 */     GL11.glTranslatef(0.0F, 0.0F, (float)-d3);
/* 245 */     GL11.glRotatef(f3 - entitylivingbase.field_70177_z, 0.0F, 1.0F, 0.0F);
/* 246 */     GL11.glRotatef(f2 - entitylivingbase.field_70125_A, 1.0F, 0.0F, 0.0F);
/* 248 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean rotateSleepingViewByGravity(float par1) {
/* 252 */     for (int i = 0; i < IRotateSleepingViewHandler.handlerList.size(); i++) {
/* 253 */       IRotateSleepingViewHandler handler = IRotateSleepingViewHandler.handlerList.get(0);
/* 254 */       if (handler.rotateSleepingFPView())
/* 255 */         return false; 
/*     */     } 
/* 258 */     return true;
/*     */   }
/*     */   
/*     */   public static void rotateCorpseProxy(RendererLivingEntity renderer, EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/* 262 */     if (par1EntityLivingBase instanceof AbstractClientPlayer) {
/* 263 */       AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)par1EntityLivingBase;
/* 264 */       if (abstractClientPlayer.func_70089_S() && abstractClientPlayer.func_70608_bn())
/* 266 */         if (rotatePlayerSleeping((EntityPlayer)abstractClientPlayer))
/*     */           return;  
/*     */     } 
/* 271 */     if (SMCoreReflectionHelper.method_rotateCorpsePublic == null)
/* 272 */       SMCoreReflectionHelper.initMethodAccessRotateCorpsePublic(); 
/* 276 */     SMCoreReflectionHelper.method_rotateCorpsePublic_args[0] = par2;
/* 277 */     SMCoreReflectionHelper.method_rotateCorpsePublic_args[1] = par3;
/* 278 */     SMCoreReflectionHelper.method_rotateCorpsePublic_args[2] = par4;
/*     */     try {
/* 280 */       SMCoreReflectionHelper.method_rotateCorpsePublic.invoke(renderer, par1EntityLivingBase, SMCoreReflectionHelper.method_rotateCorpsePublic_args);
/* 283 */     } catch (Exception ex) {
/* 284 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean rotatePlayerSleeping(EntityPlayer player) {
/* 288 */     for (int i = 0; i < IRotateSleepingViewHandler.handlerList.size(); i++) {
/* 289 */       IRotateSleepingViewHandler handler = IRotateSleepingViewHandler.handlerList.get(0);
/* 290 */       if (handler.rotateTPPlayerSleeping(player))
/* 291 */         return true; 
/*     */     } 
/* 294 */     return false;
/*     */   }
/*     */   
/*     */   public static Vec3 getPositionForgeHook(EntityPlayer pThis, float par1) {
/* 299 */     if (par1 == 1.0F)
/* 302 */       return TransformUtils.fixEyePositionByGravityClient((Entity)pThis, Vec3.func_72443_a(pThis.field_70165_t, pThis.field_70163_u + (double)(pThis.func_70047_e() - pThis.getDefaultEyeHeight()), pThis.field_70161_v)); 
/* 308 */     double d0 = pThis.field_70169_q + (pThis.field_70165_t - pThis.field_70169_q) * (double)par1;
/* 309 */     double d1 = pThis.field_70167_r + (pThis.field_70163_u - pThis.field_70167_r) * (double)par1 + (double)(pThis.func_70047_e() - pThis.getDefaultEyeHeight());
/* 310 */     double d2 = pThis.field_70166_s + (pThis.field_70161_v - pThis.field_70166_s) * (double)par1;
/* 312 */     return TransformUtils.fixEyePositionByGravityClient((Entity)pThis, VecUtils.createVec3(d0, d1, d2));
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */