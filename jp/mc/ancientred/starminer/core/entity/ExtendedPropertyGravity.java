/*     */ package jp.mc.ancientred.starminer.core.entity;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import jp.mc.ancientred.starminer.api.Gravity;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.api.IAttractableTileEntity;
/*     */ import jp.mc.ancientred.starminer.core.TransformUtils;
/*     */ import jp.mc.ancientred.starminer.core.common.VecUtils;
/*     */ import jp.mc.ancientred.starminer.core.packet.SMCorePacketSender;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ExtendedPropertyGravity extends Gravity {
/*     */   private static final String NBT_TAG_KEY = "stmn.gravity";
/*     */   
/*     */   private static final float TURN_SPEED_START = 0.05F;
/*     */   
/*     */   private WeakReference<Entity> entityWeakRef;
/*     */   
/*     */   public int temporatyZeroGTick;
/*     */   
/*     */   public int keepGravityDirTick;
/*     */   
/*     */   public int normalGravityEffectRedistTick;
/*     */   
/*  41 */   public float turnRate = 100.0F;
/*     */   
/*  42 */   public float prevTurnRate = 100.0F;
/*     */   
/*  43 */   public float turnSpeed = 0.0F;
/*     */   
/*  45 */   public float onChangeRoatDirX = 0.0F;
/*     */   
/*  46 */   public float onChangeRoatDirY = 0.0F;
/*     */   
/*  47 */   public float onChangeRoatDirZ = 0.0F;
/*     */   
/*  48 */   public float onChangeTurnYaw = 0.0F;
/*     */   
/*  51 */   public int illegalGStateTickCount = 0;
/*     */   
/*  53 */   public int unsynchronizedWarnCount = 0;
/*     */   
/*     */   public boolean changeGravityImmidiate;
/*     */   
/*     */   public void init(Entity entity, World world) {
/*  61 */     this.entityWeakRef = new WeakReference<Entity>(entity);
/*     */   }
/*     */   
/*     */   public void saveNBTData(NBTTagCompound compound) {
/*  67 */     NBTTagCompound myNBT = new NBTTagCompound();
/*  68 */     myNBT.func_74776_a("turnRate", this.turnRate);
/*  69 */     myNBT.func_74776_a("turnSpeed", this.turnSpeed);
/*  71 */     myNBT.func_74776_a("onChangeRoatDirX", this.onChangeRoatDirX);
/*  72 */     myNBT.func_74776_a("onChangeRoatDirY", this.onChangeRoatDirY);
/*  73 */     myNBT.func_74776_a("onChangeRoatDirZ", this.onChangeRoatDirZ);
/*  74 */     myNBT.func_74776_a("onChangeTurnYaw", this.onChangeTurnYaw);
/*  76 */     myNBT.func_74757_a("isAttracted", this.isAttracted);
/*  77 */     myNBT.func_74768_a("attractedPosX", this.attractedPosX);
/*  78 */     myNBT.func_74768_a("attractedPosY", this.attractedPosY);
/*  79 */     myNBT.func_74768_a("attractedPosZ", this.attractedPosZ);
/*  81 */     compound.func_74782_a("stmn.gravity", (NBTBase)myNBT);
/*     */   }
/*     */   
/*     */   public void loadNBTData(NBTTagCompound compound) {
/*  87 */     if (compound.func_74764_b("stmn.gravity")) {
/*  88 */       NBTTagCompound myNBT = compound.func_74775_l("stmn.gravity");
/*  89 */       this.turnRate = myNBT.func_74760_g("turnRate");
/*  90 */       this.turnSpeed = myNBT.func_74760_g("turnSpeed");
/*  92 */       this.onChangeRoatDirX = myNBT.func_74760_g("onChangeRoatDirX");
/*  93 */       this.onChangeRoatDirY = myNBT.func_74760_g("onChangeRoatDirY");
/*  94 */       this.onChangeRoatDirZ = myNBT.func_74760_g("onChangeRoatDirZ");
/*  95 */       this.onChangeTurnYaw = myNBT.func_74760_g("onChangeTurnYaw");
/*  97 */       this.isAttracted = myNBT.func_74767_n("isAttracted");
/*  98 */       this.attractedPosX = myNBT.func_74762_e("attractedPosX");
/*  99 */       this.attractedPosY = myNBT.func_74762_e("attractedPosY");
/* 100 */       this.attractedPosZ = myNBT.func_74762_e("attractedPosZ");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isZeroGravity() {
/* 109 */     Entity entity = this.entityWeakRef.get();
/* 110 */     if (entity != null)
/* 110 */       return isZeroGravity(entity.field_70170_p.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider); 
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isAttracted() {
/* 115 */     return this.isAttracted;
/*     */   }
/*     */   
/*     */   public void setAttractedBy(IAttractableTileEntity tileEntity) {
/* 119 */     if (tileEntity instanceof TileEntity) {
/* 120 */       TileEntity te = (TileEntity)tileEntity;
/* 122 */       Entity entity = this.entityWeakRef.get();
/* 123 */       if (entity != null && entity instanceof EntityPlayerMP) {
/* 125 */         this.isAttracted = true;
/* 126 */         this.attractedPosX = te.field_145851_c;
/* 127 */         this.attractedPosY = te.field_145848_d;
/* 128 */         this.attractedPosZ = te.field_145849_e;
/* 130 */         SMCorePacketSender.sendAttractedChangePacketToPlayer((EntityPlayerMP)entity, true, false, te.field_145851_c, te.field_145848_d, te.field_145849_e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isAttractedBy(IAttractableTileEntity tileEntity) {
/* 141 */     if (this.isAttracted && tileEntity instanceof TileEntity) {
/* 142 */       TileEntity te = (TileEntity)tileEntity;
/* 143 */       if (te.field_145851_c == this.attractedPosX && te.field_145848_d == this.attractedPosY && te.field_145849_e == this.attractedPosZ)
/* 146 */         return true; 
/*     */     } 
/* 149 */     return false;
/*     */   }
/*     */   
/*     */   public void loseAttractedBy() {
/* 153 */     Entity entity = this.entityWeakRef.get();
/* 154 */     if (entity != null && entity instanceof EntityPlayerMP) {
/* 155 */       this.isAttracted = false;
/* 157 */       SMCorePacketSender.sendAttractedChangePacketToPlayer((EntityPlayerMP)entity, false, false, 0, 0, 0);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Vec3 getGravityFixedLook(float pitch, float yaw) {
/*     */     float zFix, xFix;
/* 175 */     switch (this.gravityDirection) {
/*     */       case southTOnorth_ZN:
/* 177 */         zVal = MathHelper.func_76126_a(-pitch * 0.017453292F);
/* 178 */         zFix = MathHelper.func_76134_b(-pitch * 0.017453292F);
/* 179 */         yVal = MathHelper.func_76134_b(-yaw * 0.017453292F - 3.1415927F);
/* 180 */         xVal = -MathHelper.func_76126_a(-yaw * 0.017453292F - 3.1415927F);
/* 181 */         return VecUtils.createVec3((double)(xVal * zFix), (double)(yVal * zFix), (double)zVal);
/*     */       case northTOsouth_ZP:
/* 183 */         zVal = -MathHelper.func_76126_a(-pitch * 0.017453292F);
/* 184 */         zFix = MathHelper.func_76134_b(-pitch * 0.017453292F);
/* 185 */         yVal = -MathHelper.func_76134_b(-yaw * 0.017453292F - 3.1415927F);
/* 186 */         xVal = -MathHelper.func_76126_a(-yaw * 0.017453292F - 3.1415927F);
/* 187 */         return VecUtils.createVec3((double)(xVal * zFix), (double)(yVal * zFix), (double)zVal);
/*     */       case westTOeast_XP:
/* 189 */         xVal = -MathHelper.func_76126_a(-pitch * 0.017453292F);
/* 190 */         xFix = MathHelper.func_76134_b(-pitch * 0.017453292F);
/* 191 */         zVal = -MathHelper.func_76134_b(-yaw * 0.017453292F - 3.1415927F);
/* 192 */         yVal = -MathHelper.func_76126_a(-yaw * 0.017453292F - 3.1415927F);
/* 193 */         return VecUtils.createVec3((double)xVal, (double)(yVal * xFix), (double)(zVal * xFix));
/*     */       case eastTOwest_XN:
/* 195 */         xVal = MathHelper.func_76126_a(-pitch * 0.017453292F);
/* 196 */         xFix = MathHelper.func_76134_b(-pitch * 0.017453292F);
/* 197 */         zVal = -MathHelper.func_76134_b(-yaw * 0.017453292F - 3.1415927F);
/* 198 */         yVal = MathHelper.func_76126_a(-yaw * 0.017453292F - 3.1415927F);
/* 199 */         return VecUtils.createVec3((double)xVal, (double)(yVal * xFix), (double)(zVal * xFix));
/*     */       case downTOup_YP:
/* 201 */         yVal = -MathHelper.func_76126_a(-pitch * 0.017453292F);
/* 202 */         yFix = MathHelper.func_76134_b(-pitch * 0.017453292F);
/* 203 */         zVal = MathHelper.func_76134_b(-yaw * 0.017453292F - 3.1415927F);
/* 204 */         xVal = -MathHelper.func_76126_a(-yaw * 0.017453292F - 3.1415927F);
/* 205 */         return VecUtils.createVec3((double)(xVal * yFix), (double)yVal, (double)(zVal * yFix));
/*     */     } 
/* 207 */     float yVal = MathHelper.func_76126_a(-pitch * 0.017453292F);
/* 208 */     float yFix = -MathHelper.func_76134_b(-pitch * 0.017453292F);
/* 209 */     float zVal = MathHelper.func_76134_b(-yaw * 0.017453292F - 3.1415927F);
/* 210 */     float xVal = MathHelper.func_76126_a(-yaw * 0.017453292F - 3.1415927F);
/* 211 */     return VecUtils.createVec3((double)(xVal * yFix), (double)yVal, (double)(zVal * yFix));
/*     */   }
/*     */   
/*     */   public Vec3 getGravityFixedPlayerEyePoz(EntityPlayer parEntityPlayer, float partialTick) {
/* 216 */     World world = parEntityPlayer.field_70170_p;
/* 217 */     double d0 = parEntityPlayer.field_70169_q + (parEntityPlayer.field_70165_t - parEntityPlayer.field_70169_q) * (double)partialTick;
/* 218 */     double d1 = parEntityPlayer.field_70167_r + (parEntityPlayer.field_70163_u - parEntityPlayer.field_70167_r) * (double)partialTick + (double)(world.field_72995_K ? (parEntityPlayer.func_70047_e() - parEntityPlayer.getDefaultEyeHeight()) : parEntityPlayer.func_70047_e());
/* 219 */     double d2 = parEntityPlayer.field_70166_s + (parEntityPlayer.field_70161_v - parEntityPlayer.field_70166_s) * (double)partialTick;
/* 221 */     Vec3 fixedPozVec3 = VecUtils.createVec3(d0, d1, d2);
/* 223 */     if (parEntityPlayer instanceof EntityPlayerMP) {
/* 224 */       fixedPozVec3 = TransformUtils.fixEyePositionByGravityServer(parEntityPlayer, fixedPozVec3);
/*     */     } else {
/* 226 */       fixedPozVec3 = TransformUtils.fixEyePositionByGravityClient((Entity)parEntityPlayer, fixedPozVec3);
/*     */     } 
/* 228 */     return fixedPozVec3;
/*     */   }
/*     */   
/*     */   public void setGravityFixedPlayerShootVec(EntityPlayer parShooterEntity, Entity projectileEntity, float partialTick) {
/* 233 */     Vec3 fixedPozVec3 = getGravityFixedPlayerEyePoz(parShooterEntity, partialTick);
/* 235 */     World world = parShooterEntity.field_70170_p;
/* 236 */     float f1 = parShooterEntity.field_70127_C + (parShooterEntity.field_70125_A - parShooterEntity.field_70127_C) * partialTick;
/* 237 */     float f2 = parShooterEntity.field_70126_B + (parShooterEntity.field_70177_z - parShooterEntity.field_70126_B) * partialTick;
/* 239 */     float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - 3.1415927F);
/* 240 */     float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - 3.1415927F);
/* 241 */     float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
/* 242 */     float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
/* 243 */     float f7 = f4 * f5;
/* 244 */     float f8 = f3 * f5;
/* 246 */     Vec3 vecLook = parShooterEntity.func_70676_i(partialTick);
/* 247 */     projectileEntity.field_70165_t = fixedPozVec3.field_72450_a + vecLook.field_72450_a;
/* 248 */     projectileEntity.field_70163_u = fixedPozVec3.field_72448_b + vecLook.field_72448_b;
/* 249 */     projectileEntity.field_70161_v = fixedPozVec3.field_72449_c + vecLook.field_72449_c;
/* 250 */     projectileEntity.func_70107_b(projectileEntity.field_70165_t, projectileEntity.field_70163_u, projectileEntity.field_70161_v);
/* 251 */     projectileEntity.field_70129_M = 0.0F;
/* 253 */     projectileEntity.field_70159_w = vecLook.field_72450_a;
/* 254 */     projectileEntity.field_70179_y = vecLook.field_72449_c;
/* 255 */     projectileEntity.field_70181_x = vecLook.field_72448_b;
/*     */   }
/*     */   
/*     */   public void setResistInOpaqueBlockDamegeTick(int tick) {
/* 260 */     Entity entity = this.entityWeakRef.get();
/* 261 */     if (entity instanceof EntityLivingGravitized)
/* 262 */       ((EntityLivingGravitized)entity).redistInOpaqueBlockDamageTick = 20; 
/*     */   }
/*     */   
/*     */   public void updateGravityDirectionState(Entity entity) {
/* 272 */     GravityDirection gravityDirNew = this.gravityDirection;
/* 275 */     if (this.normalGravityEffectRedistTick > 0)
/*     */       return; 
/* 278 */     if (this.turnRate < 1.0F)
/*     */       return; 
/* 280 */     if (!this.isAttracted) {
/* 284 */       gravityDirNew = GravityDirection.upTOdown_YN;
/*     */     } else {
/* 288 */       TileEntity te = entity.field_70170_p.func_147438_o(this.attractedPosX, this.attractedPosY, this.attractedPosZ);
/* 289 */       if (te == null || !(te instanceof IAttractableTileEntity))
/*     */         return; 
/* 290 */       IAttractableTileEntity teGravity = (IAttractableTileEntity)te;
/* 293 */       gravityDirNew = teGravity.getCurrentGravity(entity);
/*     */     } 
/* 297 */     if (this.gravityDirection != gravityDirNew) {
/* 299 */       if (this.changeGravityImmidiate) {
/* 301 */         this.gravityDirection = gravityDirNew;
/* 302 */         this.changeGravityImmidiate = false;
/*     */       } else {
/* 305 */         changeGravityDirection(gravityDirNew);
/*     */       } 
/* 309 */       this.keepGravityDirTick = 15;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setTemporaryZeroGravity(int tick) {
/* 313 */     this.temporatyZeroGTick = tick;
/*     */   }
/*     */   
/*     */   public void setTemporaryGravityDirection(GravityDirection nextGravityDirection, int tick) {
/* 320 */     GravityDirection old = this.gravityDirection;
/* 322 */     if (this.keepGravityDirTick <= 0 && this.turnRate >= 1.0F)
/* 323 */       changeGravityDirection(nextGravityDirection); 
/* 326 */     this.normalGravityEffectRedistTick = tick;
/* 328 */     if (old != this.gravityDirectionNext)
/* 331 */       this.keepGravityDirTick = 30; 
/*     */   }
/*     */   
/*     */   public void changeGravityDirection(GravityDirection nextGravityDirection) {
/* 338 */     if (this.gravityDirection == nextGravityDirection)
/*     */       return; 
/* 339 */     this.prevTurnRate = this.turnRate = 0.0F;
/* 340 */     this.turnSpeed = 0.05F;
/* 341 */     this.onChangeRoatDirX = 0.0F;
/* 342 */     this.onChangeRoatDirY = 0.0F;
/* 343 */     this.onChangeRoatDirZ = 0.0F;
/* 344 */     this.onChangeTurnYaw = 0.0F;
/* 346 */     switch (this.gravityDirection) {
/*     */       case northTOsouth_ZP:
/* 348 */         switch (nextGravityDirection) {
/*     */           case southTOnorth_ZN:
/* 353 */             this.onChangeRoatDirX = -2.0F;
/*     */             break;
/*     */           case westTOeast_XP:
/* 356 */             this.onChangeRoatDirY = -1.0F;
/* 357 */             this.onChangeTurnYaw = -90.0F;
/*     */             break;
/*     */           case eastTOwest_XN:
/* 360 */             this.onChangeRoatDirY = 1.0F;
/* 361 */             this.onChangeTurnYaw = 90.0F;
/*     */             break;
/*     */           case downTOup_YP:
/* 364 */             this.onChangeRoatDirX = 1.0F;
/*     */             break;
/*     */           case upTOdown_YN:
/* 367 */             this.onChangeRoatDirX = -1.0F;
/*     */             break;
/*     */         } 
/*     */         break;
/*     */       case southTOnorth_ZN:
/* 372 */         switch (nextGravityDirection) {
/*     */           case northTOsouth_ZP:
/* 374 */             this.onChangeRoatDirX = -2.0F;
/*     */             break;
/*     */           case westTOeast_XP:
/* 380 */             this.onChangeRoatDirY = 1.0F;
/* 381 */             this.onChangeTurnYaw = 90.0F;
/*     */             break;
/*     */           case eastTOwest_XN:
/* 384 */             this.onChangeRoatDirY = -1.0F;
/* 385 */             this.onChangeTurnYaw = -90.0F;
/*     */             break;
/*     */           case downTOup_YP:
/* 388 */             this.onChangeRoatDirX = -1.0F;
/*     */             break;
/*     */           case upTOdown_YN:
/* 391 */             this.onChangeRoatDirX = 1.0F;
/*     */             break;
/*     */         } 
/*     */         break;
/*     */       case westTOeast_XP:
/* 396 */         switch (nextGravityDirection) {
/*     */           case northTOsouth_ZP:
/* 398 */             this.onChangeRoatDirY = 1.0F;
/* 399 */             this.onChangeTurnYaw = 90.0F;
/*     */             break;
/*     */           case southTOnorth_ZN:
/* 402 */             this.onChangeRoatDirY = -1.0F;
/* 403 */             this.onChangeTurnYaw = -90.0F;
/*     */             break;
/*     */           case eastTOwest_XN:
/* 409 */             this.onChangeRoatDirZ = -2.0F;
/*     */             break;
/*     */           case downTOup_YP:
/* 412 */             this.onChangeRoatDirZ = -1.0F;
/* 413 */             this.onChangeTurnYaw = -180.0F;
/*     */             break;
/*     */           case upTOdown_YN:
/* 416 */             this.onChangeRoatDirZ = 1.0F;
/*     */             break;
/*     */         } 
/*     */         break;
/*     */       case eastTOwest_XN:
/* 421 */         switch (nextGravityDirection) {
/*     */           case northTOsouth_ZP:
/* 423 */             this.onChangeRoatDirY = -1.0F;
/* 424 */             this.onChangeTurnYaw = -90.0F;
/*     */             break;
/*     */           case southTOnorth_ZN:
/* 427 */             this.onChangeRoatDirY = 1.0F;
/* 428 */             this.onChangeTurnYaw = 90.0F;
/*     */             break;
/*     */           case westTOeast_XP:
/* 431 */             this.onChangeRoatDirZ = -2.0F;
/*     */             break;
/*     */           case downTOup_YP:
/* 437 */             this.onChangeRoatDirZ = 1.0F;
/* 438 */             this.onChangeTurnYaw = -180.0F;
/*     */             break;
/*     */           case upTOdown_YN:
/* 441 */             this.onChangeRoatDirZ = -1.0F;
/*     */             break;
/*     */         } 
/*     */         break;
/*     */       case downTOup_YP:
/* 446 */         switch (nextGravityDirection) {
/*     */           case northTOsouth_ZP:
/* 448 */             this.onChangeRoatDirX = -1.0F;
/*     */             break;
/*     */           case southTOnorth_ZN:
/* 451 */             this.onChangeRoatDirX = 1.0F;
/*     */             break;
/*     */           case westTOeast_XP:
/* 454 */             this.onChangeRoatDirZ = 1.0F;
/* 455 */             this.onChangeTurnYaw = 180.0F;
/*     */             break;
/*     */           case eastTOwest_XN:
/* 458 */             this.onChangeRoatDirZ = -1.0F;
/* 459 */             this.onChangeTurnYaw = 180.0F;
/*     */             break;
/*     */           case upTOdown_YN:
/* 465 */             this.onChangeRoatDirX = -2.0F;
/*     */             break;
/*     */         } 
/*     */         break;
/*     */       case upTOdown_YN:
/* 470 */         switch (nextGravityDirection) {
/*     */           case northTOsouth_ZP:
/* 472 */             this.onChangeRoatDirX = 1.0F;
/*     */             break;
/*     */           case southTOnorth_ZN:
/* 475 */             this.onChangeRoatDirX = -1.0F;
/*     */             break;
/*     */           case westTOeast_XP:
/* 478 */             this.onChangeRoatDirZ = -1.0F;
/*     */             break;
/*     */           case eastTOwest_XN:
/* 481 */             this.onChangeRoatDirZ = 1.0F;
/*     */             break;
/*     */           case downTOup_YP:
/* 484 */             this.onChangeRoatDirX = -2.0F;
/*     */             break;
/*     */         } 
/*     */         break;
/*     */     } 
/* 492 */     this.gravityDirectionNext = nextGravityDirection;
/*     */   }
/*     */   
/*     */   public void updateAtractedStateAndGravityZero(EntityPlayerMP entityPlayer) {
/* 501 */     if (this.isAttracted && 
/* 502 */       !isStillAttracted((EntityPlayer)entityPlayer))
/* 504 */       loseAttractedBy(); 
/*     */   }
/*     */   
/*     */   private boolean isStillAttracted(EntityPlayer entityPlayer) {
/* 513 */     Block block = entityPlayer.field_70170_p.func_147439_a(this.attractedPosX, this.attractedPosY, this.attractedPosZ);
/* 514 */     if (block == null)
/* 514 */       return false; 
/* 517 */     TileEntity tileEntity = entityPlayer.field_70170_p.func_147438_o(this.attractedPosX, this.attractedPosY, this.attractedPosZ);
/* 518 */     if (!(tileEntity instanceof IAttractableTileEntity))
/* 518 */       return false; 
/* 521 */     return ((IAttractableTileEntity)tileEntity).isStillInAttractedState((Entity)entityPlayer);
/*     */   }
/*     */   
/*     */   public void validateGravity(EntityPlayer entityPlayer, boolean isGravityZero) {
/* 527 */     if (!this.isAttracted && !isGravityZero && this.gravityDirection != GravityDirection.upTOdown_YN && !entityPlayer.func_70608_bn())
/* 533 */       if (this.acceptExceptionalGravityTick > 0)
/* 535 */         this.illegalGStateTickCount += 2;  
/* 540 */     this.illegalGStateTickCount--;
/* 541 */     if (this.illegalGStateTickCount < 0)
/* 542 */       this.illegalGStateTickCount = 0; 
/*     */   }
/*     */   
/*     */   public static ExtendedPropertyGravity getExtendedPropertyGravity(Entity entity) {
/* 552 */     return (ExtendedPropertyGravity)entity.getExtendedProperties("starminer.Gravity");
/*     */   }
/*     */   
/*     */   public boolean isZeroGravity(boolean isOutSpace) {
/* 558 */     return (this.temporatyZeroGTick > 0 || (!this.isAttracted && isOutSpace && this.normalGravityEffectRedistTick <= 0));
/*     */   }
/*     */   
/*     */   public static boolean isAttracted(Entity entity) {
/* 564 */     if (entity != null) {
/* 565 */       ExtendedPropertyGravity gp = getExtendedPropertyGravity(entity);
/* 566 */       return (gp != null && gp.isAttracted);
/*     */     } 
/* 568 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isEntityAbnormalGravity(Entity entity) {
/* 574 */     if (entity != null) {
/* 575 */       ExtendedPropertyGravity gp = getExtendedPropertyGravity(entity);
/* 576 */       return (gp != null && gp.gravityDirection != GravityDirection.upTOdown_YN);
/*     */     } 
/* 578 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isEntityNormalGravity(Entity entity) {
/* 584 */     if (entity != null) {
/* 585 */       ExtendedPropertyGravity gp = getExtendedPropertyGravity(entity);
/* 586 */       return (gp == null || gp.gravityDirection == GravityDirection.upTOdown_YN);
/*     */     } 
/* 588 */     return true;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */