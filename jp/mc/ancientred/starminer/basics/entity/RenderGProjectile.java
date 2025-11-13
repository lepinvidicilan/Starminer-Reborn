/*     */ package jp.mc.ancientred.starminer.basics.entity;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import jp.mc.ancientred.starminer.api.Gravity;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderGProjectile extends Render {
/*  22 */   private static final ResourceLocation particlesTexture = new ResourceLocation("textures/particle/particles.png");
/*     */   
/*  23 */   private static final ResourceLocation arrowTextures = new ResourceLocation("textures/entity/arrow.png");
/*     */   
/*     */   private double calcTickDiv(double p_110828_1_, double p_110828_3_, double p_110828_5_) {
/*  27 */     return p_110828_1_ + (p_110828_3_ - p_110828_1_) * p_110828_5_;
/*     */   }
/*     */   
/*     */   public void renderGProjectile(EntityGProjectile parEntityGrappleHook, double parPosX, double parPosY, double parPosZ, float p_110827_8_, float parPartialTick) {
/*  31 */     doRenderArrowPart(parEntityGrappleHook, parPosX, parPosY, parPosZ, p_110827_8_, parPartialTick);
/*  32 */     if (parEntityGrappleHook.getGProjectileType() == EntityGProjectile.GProjectileType.gRappleHook)
/*  33 */       doRenderLeashPart(parEntityGrappleHook, parPosX, parPosY, parPosZ, p_110827_8_, parPartialTick); 
/*     */   }
/*     */   
/*     */   public void doRenderLeashPart(EntityGProjectile parEntityGrappleHook, double parRenderBasePosX, double parRenderBasePosY, double parRenderBasePosZ, float p_110827_8_, float parPartialTick) {
/*  38 */     if (parEntityGrappleHook.field_70250_c == null || !(parEntityGrappleHook.field_70250_c instanceof EntityPlayer))
/*     */       return; 
/*  42 */     EntityPlayer entityPlayer = (EntityPlayer)parEntityGrappleHook.field_70250_c;
/*  43 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  45 */     Gravity gravity = Gravity.getGravityProp((Entity)entityPlayer);
/*  46 */     if (gravity == null)
/*     */       return; 
/*  47 */     Vec3 vecEyePoz = gravity.getGravityFixedPlayerEyePoz(entityPlayer, parPartialTick);
/*  48 */     vecEyePoz.field_72448_b -= 0.12D;
/*  49 */     float playerPitch = entityPlayer.field_70127_C + (entityPlayer.field_70125_A - entityPlayer.field_70127_C) * parPartialTick;
/*  50 */     float playerYaw = entityPlayer.field_70126_B + (entityPlayer.field_70177_z - entityPlayer.field_70126_B) * parPartialTick;
/*  51 */     Vec3 vecLook = gravity.getGravityFixedLook(playerPitch + 10.0F, playerYaw + 25.0F);
/*  53 */     double padding = 0.4D;
/*  54 */     double gunTopX = vecEyePoz.field_72450_a + vecLook.field_72450_a * padding;
/*  55 */     double gunTopY = vecEyePoz.field_72448_b + vecLook.field_72448_b * padding;
/*  56 */     double gunTopZ = vecEyePoz.field_72449_c + vecLook.field_72449_c * padding;
/*  60 */     double yaw = calcTickDiv((double)parEntityGrappleHook.field_70126_B, (double)parEntityGrappleHook.field_70177_z, (double)(parPartialTick * 0.5F)) * 0.01745329238474369D;
/*  61 */     double ptc = calcTickDiv((double)parEntityGrappleHook.field_70127_C, (double)parEntityGrappleHook.field_70125_A, (double)(parPartialTick * 0.5F)) * 0.01745329238474369D;
/*  62 */     double yVal = Math.sin(-ptc);
/*  63 */     double yFix = -Math.cos(-ptc);
/*  64 */     double zVal = -Math.cos(-yaw - Math.PI);
/*  65 */     double xVal = Math.sin(-yaw - Math.PI);
/*  66 */     padding = 0.65D;
/*  67 */     double addX = xVal * yFix * padding;
/*  68 */     double addY = yVal * padding - 1.5D;
/*  69 */     double addZ = zVal * yFix * padding;
/*  70 */     parRenderBasePosX += addX;
/*  71 */     parRenderBasePosY += addY;
/*  72 */     parRenderBasePosZ += addZ;
/*  74 */     double arrowX = parEntityGrappleHook.field_70169_q + (parEntityGrappleHook.field_70165_t - parEntityGrappleHook.field_70169_q) * (double)parPartialTick;
/*  75 */     double arrowY = parEntityGrappleHook.field_70167_r + (parEntityGrappleHook.field_70163_u - parEntityGrappleHook.field_70167_r) * (double)parPartialTick;
/*  76 */     double arrowZ = parEntityGrappleHook.field_70166_s + (parEntityGrappleHook.field_70161_v - parEntityGrappleHook.field_70166_s) * (double)parPartialTick;
/*  77 */     arrowX += addX;
/*  78 */     arrowY += addY;
/*  79 */     arrowZ += addZ;
/*  81 */     double distanceX = (double)(float)(gunTopX - arrowX);
/*  82 */     double distanceY = (double)(float)(gunTopY - arrowY);
/*  83 */     double distanceZ = (double)(float)(gunTopZ - arrowZ);
/*  85 */     GL11.glDisable(3553);
/*  86 */     GL11.glDisable(2896);
/*  87 */     GL11.glDisable(2884);
/*  89 */     tessellator.func_78371_b(5);
/*  90 */     for (int j = 0; j <= 24; j++) {
/*  92 */       if (j % 2 == 0) {
/*  94 */         tessellator.func_78369_a(0.5F, 0.4F, 0.3F, 1.0F);
/*     */       } else {
/*  98 */         tessellator.func_78369_a(0.35F, 0.28F, 0.21000001F, 1.0F);
/*     */       } 
/* 100 */       float f2 = (float)j / 24.0F;
/* 101 */       tessellator.func_78377_a(parRenderBasePosX + distanceX * (double)f2 + 0.0D, parRenderBasePosY + distanceY * (double)(f2 * f2 + f2) * 0.5D + (double)((24.0F - (float)j) / 18.0F + 0.125F), parRenderBasePosZ + distanceZ * (double)f2);
/* 102 */       tessellator.func_78377_a(parRenderBasePosX + distanceX * (double)f2 + 0.025D, parRenderBasePosY + distanceY * (double)(f2 * f2 + f2) * 0.5D + (double)((24.0F - (float)j) / 18.0F + 0.125F) + 0.025D, parRenderBasePosZ + distanceZ * (double)f2);
/*     */     } 
/* 104 */     tessellator.func_78381_a();
/* 106 */     tessellator.func_78371_b(5);
/* 107 */     for (int i = 0; i <= 24; i++) {
/* 109 */       if (i % 2 == 0) {
/* 111 */         tessellator.func_78369_a(0.5F, 0.4F, 0.3F, 1.0F);
/*     */       } else {
/* 115 */         tessellator.func_78369_a(0.35F, 0.28F, 0.21000001F, 1.0F);
/*     */       } 
/* 117 */       float f2 = (float)i / 24.0F;
/* 118 */       tessellator.func_78377_a(parRenderBasePosX + distanceX * (double)f2 + 0.0D, parRenderBasePosY + distanceY * (double)(f2 * f2 + f2) * 0.5D + (double)((24.0F - (float)i) / 18.0F + 0.125F) + 0.025D, parRenderBasePosZ + distanceZ * (double)f2);
/* 119 */       tessellator.func_78377_a(parRenderBasePosX + distanceX * (double)f2 + 0.025D, parRenderBasePosY + distanceY * (double)(f2 * f2 + f2) * 0.5D + (double)((24.0F - (float)i) / 18.0F + 0.125F), parRenderBasePosZ + distanceZ * (double)f2 + 0.025D);
/*     */     } 
/* 121 */     tessellator.func_78381_a();
/* 123 */     GL11.glEnable(2896);
/* 124 */     GL11.glEnable(3553);
/* 125 */     GL11.glEnable(2884);
/*     */   }
/*     */   
/*     */   public void doRenderArrowPart(EntityGProjectile parEntityGrappleHook, double parPosX, double parPosY, double parPosZ, float p_76986_8_, float parPartialTick) {
/* 129 */     func_110776_a(arrowTextures);
/* 130 */     GL11.glPushMatrix();
/* 131 */     GL11.glTranslatef((float)parPosX, (float)parPosY, (float)parPosZ);
/* 132 */     GL11.glRotatef(parEntityGrappleHook.field_70126_B + (parEntityGrappleHook.field_70177_z - parEntityGrappleHook.field_70126_B) * parPartialTick - 90.0F, 0.0F, 1.0F, 0.0F);
/* 133 */     GL11.glRotatef(parEntityGrappleHook.field_70127_C + (parEntityGrappleHook.field_70125_A - parEntityGrappleHook.field_70127_C) * parPartialTick, 0.0F, 0.0F, 1.0F);
/* 134 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 135 */     byte b0 = 0;
/* 136 */     float f2 = 0.0F;
/* 137 */     float f3 = 0.5F;
/* 138 */     float f4 = (float)(0 + b0 * 10) / 32.0F;
/* 139 */     float f5 = (float)(5 + b0 * 10) / 32.0F;
/* 140 */     float f6 = 0.0F;
/* 141 */     float f7 = 0.15625F;
/* 142 */     float f8 = (float)(5 + b0 * 10) / 32.0F;
/* 143 */     float f9 = (float)(10 + b0 * 10) / 32.0F;
/* 144 */     float f10 = 0.05625F;
/* 145 */     GL11.glEnable(32826);
/* 146 */     float f11 = (float)parEntityGrappleHook.field_70249_b - parPartialTick;
/* 148 */     if (f11 > 0.0F) {
/* 150 */       float f12 = -MathHelper.func_76126_a(f11 * 3.0F) * f11;
/* 151 */       GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
/*     */     } 
/* 154 */     GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
/* 155 */     GL11.glScalef(f10, f10, f10);
/* 156 */     GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
/* 157 */     GL11.glNormal3f(f10, 0.0F, 0.0F);
/* 158 */     tessellator.func_78382_b();
/* 159 */     tessellator.func_78374_a(-7.0D, -2.0D, -2.0D, (double)f6, (double)f8);
/* 160 */     tessellator.func_78374_a(-7.0D, -2.0D, 2.0D, (double)f7, (double)f8);
/* 161 */     tessellator.func_78374_a(-7.0D, 2.0D, 2.0D, (double)f7, (double)f9);
/* 162 */     tessellator.func_78374_a(-7.0D, 2.0D, -2.0D, (double)f6, (double)f9);
/* 163 */     tessellator.func_78381_a();
/* 164 */     GL11.glNormal3f(-f10, 0.0F, 0.0F);
/* 165 */     tessellator.func_78382_b();
/* 166 */     tessellator.func_78374_a(-7.0D, 2.0D, -2.0D, (double)f6, (double)f8);
/* 167 */     tessellator.func_78374_a(-7.0D, 2.0D, 2.0D, (double)f7, (double)f8);
/* 168 */     tessellator.func_78374_a(-7.0D, -2.0D, 2.0D, (double)f7, (double)f9);
/* 169 */     tessellator.func_78374_a(-7.0D, -2.0D, -2.0D, (double)f6, (double)f9);
/* 170 */     tessellator.func_78381_a();
/* 172 */     for (int i = 0; i < 4; i++) {
/* 174 */       GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
/* 175 */       GL11.glNormal3f(0.0F, 0.0F, f10);
/* 176 */       tessellator.func_78382_b();
/* 177 */       tessellator.func_78374_a(-8.0D, -2.0D, 0.0D, (double)f2, (double)f4);
/* 178 */       tessellator.func_78374_a(8.0D, -2.0D, 0.0D, (double)f3, (double)f4);
/* 179 */       tessellator.func_78374_a(8.0D, 2.0D, 0.0D, (double)f3, (double)f5);
/* 180 */       tessellator.func_78374_a(-8.0D, 2.0D, 0.0D, (double)f2, (double)f5);
/* 181 */       tessellator.func_78381_a();
/*     */     } 
/* 184 */     GL11.glDisable(32826);
/* 185 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityGProjectile p_110775_1_) {
/* 192 */     return particlesTexture;
/*     */   }
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity p_110775_1_) {
/* 200 */     return getEntityTexture((EntityGProjectile)p_110775_1_);
/*     */   }
/*     */   
/*     */   public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float parPartialTick) {
/* 211 */     renderGProjectile((EntityGProjectile)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, parPartialTick);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */