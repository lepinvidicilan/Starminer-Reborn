/*     */ package jp.mc.ancientred.starminer.basics.entity;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import jp.mc.ancientred.starminer.api.Gravity;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IProjectile;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.play.server.S2BPacketChangeGameState;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityGProjectile extends EntityArrow implements IProjectile {
/*     */   private static final int DW_TYPE = 17;
/*     */   
/*     */   private static final int DW_RELATED_ENTITY = 18;
/*     */   
/*  36 */   private int xTile = -1;
/*     */   
/*  37 */   private int yTile = -1;
/*     */   
/*  38 */   private int zTile = -1;
/*     */   
/*     */   private Block inTile;
/*     */   
/*     */   private int inData;
/*     */   
/*     */   private boolean inGround;
/*     */   
/*     */   public int field_70251_a;
/*     */   
/*     */   public int field_70249_b;
/*     */   
/*     */   public Entity field_70250_c;
/*     */   
/*     */   private int ticksInGround;
/*     */   
/*     */   private int ticksInAir;
/*     */   
/*  47 */   private double damage = 2.0D;
/*     */   
/*     */   private int knockbackStrength;
/*     */   
/*     */   private int hitBlockSide;
/*     */   
/*     */   public enum GProjectileType {
/*  55 */     gArrow, gRappleHook;
/*     */   }
/*     */   
/*     */   public EntityGProjectile(World parWorld) {
/*  61 */     super(parWorld);
/*  62 */     this.field_70155_l = 10.0D;
/*  63 */     func_70105_a(0.5F, 0.5F);
/*     */   }
/*     */   
/*     */   public EntityGProjectile(World parWorld, double parX, double parY, double parZ) {
/*  68 */     super(parWorld);
/*  69 */     this.field_70155_l = 10.0D;
/*  70 */     func_70105_a(0.5F, 0.5F);
/*  71 */     func_70107_b(parX, parY, parZ);
/*  72 */     this.field_70129_M = 0.0F;
/*     */   }
/*     */   
/*     */   public EntityGProjectile(World parWorld, EntityPlayer parShooterEntity, float shootSpeed, GProjectileType type) {
/*  77 */     super(parWorld);
/*  78 */     setGProjectileType(type);
/*  79 */     this.field_70155_l = 10.0D;
/*  80 */     this.field_70250_c = (Entity)parShooterEntity;
/*  81 */     setShooterEntityToDataWatcher(this.field_70250_c);
/*  83 */     if (type == GProjectileType.gArrow && parShooterEntity instanceof EntityPlayer)
/*  85 */       this.field_70251_a = 1; 
/*  87 */     func_70105_a(0.5F, 0.5F);
/*  88 */     func_70012_b(parShooterEntity.field_70165_t, parShooterEntity.field_70163_u + (double)parShooterEntity.func_70047_e(), parShooterEntity.field_70161_v, parShooterEntity.field_70177_z, parShooterEntity.field_70125_A);
/*  95 */     Gravity gravity = Gravity.getGravityProp((Entity)parShooterEntity);
/*  96 */     if (gravity != null)
/*  96 */       gravity.setGravityFixedPlayerShootVec(parShooterEntity, (Entity)this, 1.0F); 
/*  98 */     func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, shootSpeed * 1.5F, 1.0F);
/*     */   }
/*     */   
/*     */   public void func_70186_c(double p_70186_1_, double p_70186_3_, double p_70186_5_, float shootSpeed, float p_70186_8_) {
/* 135 */     float f2 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
/* 136 */     p_70186_1_ /= (double)f2;
/* 137 */     p_70186_3_ /= (double)f2;
/* 138 */     p_70186_5_ /= (double)f2;
/* 139 */     p_70186_1_ += this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean() ? -1 : true) * 0.007499999832361937D * (double)p_70186_8_;
/* 140 */     p_70186_3_ += this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean() ? -1 : true) * 0.007499999832361937D * (double)p_70186_8_;
/* 141 */     p_70186_5_ += this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean() ? -1 : true) * 0.007499999832361937D * (double)p_70186_8_;
/* 142 */     p_70186_1_ *= (double)shootSpeed;
/* 143 */     p_70186_3_ *= (double)shootSpeed;
/* 144 */     p_70186_5_ *= (double)shootSpeed;
/* 145 */     this.field_70159_w = p_70186_1_;
/* 146 */     this.field_70181_x = p_70186_3_;
/* 147 */     this.field_70179_y = p_70186_5_;
/* 148 */     float f3 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
/* 149 */     this.field_70126_B = this.field_70177_z = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / Math.PI);
/* 150 */     this.field_70127_C = this.field_70125_A = (float)(Math.atan2(p_70186_3_, (double)f3) * 180.0D / Math.PI);
/* 151 */     this.ticksInGround = 0;
/*     */   }
/*     */   
/*     */   protected void func_70088_a() {
/* 156 */     this.field_70158_ak = true;
/* 157 */     this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
/* 158 */     this.field_70180_af.func_75682_a(17, Integer.valueOf(0));
/* 159 */     this.field_70180_af.func_75682_a(18, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public void setShooterEntityToDataWatcher(Entity entity) {
/* 163 */     if (!this.field_70170_p.field_72995_K)
/* 164 */       this.field_70180_af.func_75692_b(18, Integer.valueOf(entity.func_145782_y())); 
/*     */   }
/*     */   
/*     */   public void setGProjectileType(GProjectileType type) {
/* 169 */     this.field_70180_af.func_75692_b(17, Integer.valueOf(type.ordinal()));
/*     */   }
/*     */   
/*     */   public GProjectileType getGProjectileType() {
/* 173 */     return GProjectileType.values()[this.field_70180_af.func_75679_c(17)];
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/* 178 */     if (getGProjectileType() == GProjectileType.gRappleHook)
/* 179 */       if (!this.field_70170_p.field_72995_K) {
/* 181 */         if (this.field_70250_c != null && this.field_70250_c instanceof EntityPlayerMP) {
/* 183 */           this.field_70250_c.field_70143_R = 0.0F;
/* 187 */           Gravity gravity = Gravity.getGravityProp(this.field_70250_c);
/* 188 */           if (gravity != null)
/* 188 */             gravity.acceptExceptionalGravityTick = 45; 
/* 190 */           ItemStack itemstack = ((EntityPlayerMP)this.field_70250_c).func_71045_bC();
/* 192 */           if (this.field_70250_c.field_70128_L || !this.field_70250_c.func_70089_S() || this.field_70250_c.func_70093_af() || func_70068_e(this.field_70250_c) > 3000.0D) {
/* 198 */             func_70106_y();
/*     */             return;
/*     */           } 
/*     */         } else {
/* 202 */           func_70106_y();
/*     */           return;
/*     */         } 
/*     */       } else {
/* 207 */         int EntityId = this.field_70180_af.func_75679_c(18);
/* 208 */         if (EntityId != 0)
/* 209 */           this.field_70250_c = this.field_70170_p.func_73045_a(EntityId); 
/* 212 */         if (this.inGround && this.field_70250_c != null) {
/* 213 */           Entity entityToPull = this.field_70250_c;
/* 216 */           entityToPull.field_70143_R = 0.0F;
/* 218 */           Gravity gravity = Gravity.getGravityProp(entityToPull);
/* 220 */           double distance = entityToPull.func_70011_f(this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v);
/* 222 */           double distX = this.field_70165_t - entityToPull.field_70165_t;
/* 223 */           double motionForX = distX / distance * 0.05D;
/* 226 */           double distY = this.field_70163_u + 1.0D - entityToPull.field_70163_u;
/* 227 */           double motionForY = distY / distance * 0.05D;
/* 230 */           double distZ = this.field_70161_v - entityToPull.field_70161_v;
/* 231 */           double motionForZ = distZ / distance * 0.05D;
/* 233 */           double acl = 4.0D;
/* 234 */           boolean ignore = false;
/* 235 */           boolean xNearCheck = false;
/* 236 */           boolean yNearCheck = false;
/* 237 */           boolean zNearCheck = false;
/* 238 */           GravityDirection againstWallGravityDir = GravityDirection.upTOdown_YN;
/* 239 */           switch (this.hitBlockSide) {
/*     */             case 0:
/* 241 */               againstWallGravityDir = GravityDirection.downTOup_YP;
/* 242 */               ignore = true;
/*     */             case 1:
/* 244 */               if (!ignore)
/* 244 */                 againstWallGravityDir = GravityDirection.upTOdown_YN; 
/* 246 */               motionForX *= acl;
/* 247 */               motionForZ *= acl;
/* 249 */               yNearCheck = true;
/*     */               break;
/*     */             case 2:
/* 252 */               againstWallGravityDir = GravityDirection.northTOsouth_ZP;
/* 253 */               ignore = true;
/*     */             case 3:
/* 255 */               if (!ignore)
/* 255 */                 againstWallGravityDir = GravityDirection.southTOnorth_ZN; 
/* 257 */               motionForX *= acl;
/* 258 */               motionForY *= acl;
/* 260 */               zNearCheck = true;
/*     */               break;
/*     */             case 4:
/* 263 */               againstWallGravityDir = GravityDirection.westTOeast_XP;
/* 264 */               ignore = true;
/*     */             case 5:
/* 266 */               if (!ignore)
/* 266 */                 againstWallGravityDir = GravityDirection.eastTOwest_XN; 
/* 268 */               motionForY *= acl;
/* 269 */               motionForZ *= acl;
/* 271 */               xNearCheck = true;
/*     */               break;
/*     */           } 
/* 274 */           entityToPull.field_70159_w += motionForX;
/* 275 */           entityToPull.field_70181_x += motionForY;
/* 276 */           entityToPull.field_70179_y += motionForZ;
/* 278 */           if (Math.abs(distX) < 1.0D) {
/* 279 */             entityToPull.field_70159_w *= Math.abs(distX) / 50.0D;
/* 280 */             if (distX > 0.2D)
/* 281 */               entityToPull.field_70159_w += distX / Math.abs(distX) * 0.05D; 
/* 283 */             xNearCheck = true;
/*     */           } 
/* 285 */           if (Math.abs(distY) < 1.0D) {
/* 286 */             entityToPull.field_70181_x *= Math.abs(distY) / 50.0D;
/* 287 */             if (distY > 0.2D)
/* 288 */               entityToPull.field_70181_x += distY / Math.abs(distY) * 0.05D; 
/* 290 */             yNearCheck = true;
/*     */           } 
/* 292 */           if (Math.abs(distZ) < 1.0D) {
/* 293 */             entityToPull.field_70179_y *= Math.abs(distZ) / 50.0D;
/* 294 */             if (distZ > 0.2D)
/* 295 */               entityToPull.field_70179_y += distZ / Math.abs(distZ) * 0.05D; 
/* 297 */             zNearCheck = true;
/*     */           } 
/* 300 */           if ((xNearCheck && yNearCheck && zNearCheck) || distance < 4.0D) {
/* 301 */             gravity.setTemporaryGravityDirection(againstWallGravityDir, 15);
/*     */           } else {
/* 303 */             gravity.setTemporaryZeroGravity(2);
/*     */           } 
/*     */         } 
/*     */       }  
/* 309 */     onUpdateOriginal();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70056_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
/* 318 */     func_70107_b(p_70056_1_, p_70056_3_, p_70056_5_);
/* 319 */     func_70101_b(p_70056_7_, p_70056_8_);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
/* 326 */     this.field_70159_w = p_70016_1_;
/* 327 */     this.field_70181_x = p_70016_3_;
/* 328 */     this.field_70179_y = p_70016_5_;
/* 330 */     if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
/* 332 */       float f = MathHelper.func_76133_a(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
/* 333 */       this.field_70126_B = this.field_70177_z = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0D / Math.PI);
/* 334 */       this.field_70127_C = this.field_70125_A = (float)(Math.atan2(p_70016_3_, (double)f) * 180.0D / Math.PI);
/* 335 */       this.field_70127_C = this.field_70125_A;
/* 336 */       this.field_70126_B = this.field_70177_z;
/* 337 */       func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
/* 338 */       this.ticksInGround = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onUpdateOriginal() {
/* 346 */     func_70030_z();
/* 348 */     if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
/* 350 */       float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 351 */       this.field_70126_B = this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
/* 352 */       this.field_70127_C = this.field_70125_A = (float)(Math.atan2(this.field_70181_x, (double)f) * 180.0D / Math.PI);
/*     */     } 
/* 355 */     Block block = this.field_70170_p.func_147439_a(this.xTile, this.xTile, this.zTile);
/* 357 */     if (block.func_149688_o() != Material.field_151579_a) {
/* 359 */       block.func_149719_a((IBlockAccess)this.field_70170_p, this.xTile, this.xTile, this.zTile);
/* 360 */       AxisAlignedBB axisalignedbb = block.func_149668_a(this.field_70170_p, this.xTile, this.xTile, this.zTile);
/* 362 */       if (axisalignedbb != null && axisalignedbb.func_72318_a(Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v)))
/* 364 */         this.inGround = true; 
/*     */     } 
/* 368 */     if (this.field_70249_b > 0)
/* 370 */       this.field_70249_b--; 
/* 373 */     if (this.inGround) {
/* 375 */       int j = this.field_70170_p.func_72805_g(this.xTile, this.xTile, this.zTile);
/* 377 */       if (block == this.inTile && j == this.inData) {
/* 379 */         this.ticksInGround++;
/* 381 */         if (this.ticksInGround == 1200)
/* 383 */           func_70106_y(); 
/*     */       } else {
/* 388 */         this.inGround = false;
/* 389 */         this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
/* 390 */         this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
/* 391 */         this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
/* 392 */         this.ticksInGround = 0;
/* 393 */         this.ticksInAir = 0;
/*     */       } 
/*     */     } else {
/* 398 */       this.ticksInAir++;
/* 399 */       Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 400 */       Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/* 401 */       MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec31, vec3, false, true, false);
/* 402 */       vec31 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 403 */       vec3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/* 405 */       if (movingobjectposition != null)
/* 407 */         vec3 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c); 
/* 410 */       Entity entity = null;
/* 411 */       List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
/* 412 */       double d0 = 0.0D;
/* 416 */       for (int i = 0; i < list.size(); i++) {
/* 418 */         Entity entity1 = list.get(i);
/* 420 */         if (entity1.func_70067_L() && (entity1 != this.field_70250_c || this.ticksInAir >= 5)) {
/* 422 */           float f = 0.3F;
/* 423 */           AxisAlignedBB axisalignedbb1 = entity1.field_70121_D.func_72314_b((double)f, (double)f, (double)f);
/* 424 */           MovingObjectPosition movingobjectposition1 = axisalignedbb1.func_72327_a(vec31, vec3);
/* 426 */           if (movingobjectposition1 != null) {
/* 428 */             double d1 = vec31.func_72438_d(movingobjectposition1.field_72307_f);
/* 430 */             if (d1 < d0 || d0 == 0.0D) {
/* 432 */               entity = entity1;
/* 433 */               d0 = d1;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 439 */       if (entity != null)
/* 441 */         movingobjectposition = new MovingObjectPosition(entity); 
/* 444 */       if (movingobjectposition != null && movingobjectposition.field_72308_g != null && movingobjectposition.field_72308_g instanceof EntityPlayer) {
/* 446 */         EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.field_72308_g;
/* 448 */         if (entityplayer.field_71075_bZ.field_75102_a || (this.field_70250_c instanceof EntityPlayer && !((EntityPlayer)this.field_70250_c).func_96122_a(entityplayer)))
/* 450 */           movingobjectposition = null; 
/*     */       } 
/* 457 */       if (movingobjectposition != null)
/* 459 */         if (movingobjectposition.field_72308_g != null) {
/* 461 */           float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/* 462 */           int k = MathHelper.func_76143_f((double)f * this.damage);
/* 464 */           if (func_70241_g())
/* 466 */             k += this.field_70146_Z.nextInt(k / 2 + 2); 
/* 469 */           DamageSource damagesource = null;
/* 471 */           if (this.field_70250_c == null) {
/* 473 */             damagesource = DamageSource.func_76353_a(this, (Entity)this);
/*     */           } else {
/* 477 */             damagesource = DamageSource.func_76353_a(this, this.field_70250_c);
/*     */           } 
/* 480 */           if (func_70027_ad() && !(movingobjectposition.field_72308_g instanceof net.minecraft.entity.monster.EntityEnderman))
/* 482 */             movingobjectposition.field_72308_g.func_70015_d(5); 
/* 485 */           if (movingobjectposition.field_72308_g.func_70097_a(damagesource, (float)k)) {
/* 487 */             if (movingobjectposition.field_72308_g instanceof EntityLivingBase) {
/* 489 */               EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.field_72308_g;
/* 491 */               if (!this.field_70170_p.field_72995_K)
/* 493 */                 entitylivingbase.func_85034_r(entitylivingbase.func_85035_bI() + 1); 
/* 496 */               if (this.knockbackStrength > 0) {
/* 498 */                 float f4 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 500 */                 if (f4 > 0.0F)
/* 502 */                   movingobjectposition.field_72308_g.func_70024_g(this.field_70159_w * (double)this.knockbackStrength * 0.6000000238418579D / (double)f4, 0.1D, this.field_70179_y * (double)this.knockbackStrength * 0.6000000238418579D / (double)f4); 
/*     */               } 
/* 506 */               if (this.field_70250_c != null && this.field_70250_c instanceof EntityLivingBase) {
/* 508 */                 EnchantmentHelper.func_151384_a(entitylivingbase, this.field_70250_c);
/* 509 */                 EnchantmentHelper.func_151385_b((EntityLivingBase)this.field_70250_c, (Entity)entitylivingbase);
/*     */               } 
/* 512 */               if (this.field_70250_c != null && movingobjectposition.field_72308_g != this.field_70250_c && movingobjectposition.field_72308_g instanceof EntityPlayer && this.field_70250_c instanceof EntityPlayerMP)
/* 514 */                 ((EntityPlayerMP)this.field_70250_c).field_71135_a.func_147359_a(new S2BPacketChangeGameState(6, 0.0F)); 
/*     */             } 
/* 518 */             func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/* 520 */             if (!(movingobjectposition.field_72308_g instanceof net.minecraft.entity.monster.EntityEnderman))
/* 522 */               func_70106_y(); 
/*     */           } else {
/* 527 */             this.field_70159_w *= -0.10000000149011612D;
/* 528 */             this.field_70181_x *= -0.10000000149011612D;
/* 529 */             this.field_70179_y *= -0.10000000149011612D;
/* 530 */             this.field_70177_z += 180.0F;
/* 531 */             this.field_70126_B += 180.0F;
/* 532 */             this.ticksInAir = 0;
/*     */           } 
/*     */         } else {
/* 537 */           this.hitBlockSide = movingobjectposition.field_72310_e;
/* 538 */           this.xTile = movingobjectposition.field_72311_b;
/* 539 */           this.xTile = movingobjectposition.field_72312_c;
/* 540 */           this.zTile = movingobjectposition.field_72309_d;
/* 541 */           this.inTile = this.field_70170_p.func_147439_a(this.xTile, this.xTile, this.zTile);
/* 542 */           this.inData = this.field_70170_p.func_72805_g(this.xTile, this.xTile, this.zTile);
/* 543 */           this.field_70159_w = (double)(float)(movingobjectposition.field_72307_f.field_72450_a - this.field_70165_t);
/* 544 */           this.field_70181_x = (double)(float)(movingobjectposition.field_72307_f.field_72448_b - this.field_70163_u);
/* 545 */           this.field_70179_y = (double)(float)(movingobjectposition.field_72307_f.field_72449_c - this.field_70161_v);
/* 546 */           float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/* 547 */           this.field_70165_t -= this.field_70159_w / (double)f * 0.05000000074505806D;
/* 548 */           this.field_70163_u -= this.field_70181_x / (double)f * 0.05000000074505806D;
/* 549 */           this.field_70161_v -= this.field_70179_y / (double)f * 0.05000000074505806D;
/* 550 */           func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/* 551 */           this.inGround = true;
/* 552 */           this.field_70249_b = 7;
/* 553 */           func_70243_d(false);
/* 555 */           if (this.inTile.func_149688_o() != Material.field_151579_a)
/* 557 */             this.inTile.func_149670_a(this.field_70170_p, this.xTile, this.xTile, this.zTile, (Entity)this); 
/*     */         }  
/* 562 */       if (func_70241_g())
/* 564 */         for (int j = 0; j < 4; j++)
/* 566 */           this.field_70170_p.func_72869_a("crit", this.field_70165_t + this.field_70159_w * (double)j / 4.0D, this.field_70163_u + this.field_70181_x * (double)j / 4.0D, this.field_70161_v + this.field_70179_y * (double)j / 4.0D, -this.field_70159_w, -this.field_70181_x + 0.2D, -this.field_70179_y);  
/* 570 */       this.field_70165_t += this.field_70159_w;
/* 571 */       this.field_70163_u += this.field_70181_x;
/* 572 */       this.field_70161_v += this.field_70179_y;
/* 573 */       float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 574 */       this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
/* 576 */       for (this.field_70125_A = (float)(Math.atan2(this.field_70181_x, (double)f2) * 180.0D / Math.PI); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F);
/* 581 */       while (this.field_70125_A - this.field_70127_C >= 180.0F)
/* 583 */         this.field_70127_C += 360.0F; 
/* 586 */       while (this.field_70177_z - this.field_70126_B < -180.0F)
/* 588 */         this.field_70126_B -= 360.0F; 
/* 591 */       while (this.field_70177_z - this.field_70126_B >= 180.0F)
/* 593 */         this.field_70126_B += 360.0F; 
/* 596 */       this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
/* 597 */       this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
/* 598 */       float f3 = 0.99F;
/* 599 */       float f1 = 0.05F;
/* 601 */       if (func_70090_H()) {
/* 603 */         for (int l = 0; l < 4; l++) {
/* 605 */           float f4 = 0.25F;
/* 606 */           this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * (double)f4, this.field_70163_u - this.field_70181_x * (double)f4, this.field_70161_v - this.field_70179_y * (double)f4, this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */         } 
/* 609 */         f3 = 0.8F;
/*     */       } 
/* 612 */       if (func_70026_G())
/* 614 */         func_70066_B(); 
/* 617 */       if (getGProjectileType() == GProjectileType.gArrow && !(this.field_70170_p.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider)) {
/* 618 */         this.field_70159_w *= (double)f3;
/* 619 */         this.field_70181_x *= (double)f3;
/* 620 */         this.field_70179_y *= (double)f3;
/* 621 */         this.field_70181_x -= (double)f1;
/*     */       } 
/* 623 */       func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 624 */       func_145775_I();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound p_70014_1_) {
/* 630 */     p_70014_1_.func_74777_a("gpType", (short)getGProjectileType().ordinal());
/* 631 */     p_70014_1_.func_74777_a("xTile", (short)this.xTile);
/* 632 */     p_70014_1_.func_74777_a("yTile", (short)this.xTile);
/* 633 */     p_70014_1_.func_74777_a("zTile", (short)this.zTile);
/* 634 */     p_70014_1_.func_74777_a("life", (short)this.ticksInGround);
/* 635 */     p_70014_1_.func_74774_a("inTile", (byte)Block.func_149682_b(this.inTile));
/* 636 */     p_70014_1_.func_74774_a("inData", (byte)this.inData);
/* 637 */     p_70014_1_.func_74774_a("shake", (byte)this.field_70249_b);
/* 638 */     p_70014_1_.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
/* 639 */     p_70014_1_.func_74774_a("pickup", (byte)this.field_70251_a);
/* 640 */     p_70014_1_.func_74780_a("damage", this.damage);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound p_70037_1_) {
/* 645 */     setGProjectileType(GProjectileType.values()[p_70037_1_.func_74765_d("gpType")]);
/* 646 */     this.xTile = p_70037_1_.func_74765_d("xTile");
/* 647 */     this.xTile = p_70037_1_.func_74765_d("yTile");
/* 648 */     this.zTile = p_70037_1_.func_74765_d("zTile");
/* 649 */     this.ticksInGround = p_70037_1_.func_74765_d("life");
/* 650 */     this.inTile = Block.func_149729_e(p_70037_1_.func_74771_c("inTile") & 0xFF);
/* 651 */     this.inData = p_70037_1_.func_74771_c("inData") & 0xFF;
/* 652 */     this.field_70249_b = p_70037_1_.func_74771_c("shake") & 0xFF;
/* 653 */     this.inGround = (p_70037_1_.func_74771_c("inGround") == 1);
/* 655 */     if (p_70037_1_.func_150297_b("damage", 99))
/* 657 */       this.damage = p_70037_1_.func_74769_h("damage"); 
/* 660 */     if (p_70037_1_.func_150297_b("pickup", 99)) {
/* 662 */       this.field_70251_a = p_70037_1_.func_74771_c("pickup");
/* 664 */     } else if (p_70037_1_.func_150297_b("player", 99)) {
/* 666 */       this.field_70251_a = p_70037_1_.func_74767_n("player") ? 1 : 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_70100_b_(EntityPlayer p_70100_1_) {
/* 672 */     if (getGProjectileType() == GProjectileType.gArrow && 
/* 673 */       !this.field_70170_p.field_72995_K && this.inGround && this.field_70249_b <= 0) {
/* 675 */       boolean flag = (this.field_70251_a == 1 || (this.field_70251_a == 2 && p_70100_1_.field_71075_bZ.field_75098_d));
/* 677 */       if (this.field_70251_a == 1 && !p_70100_1_.field_71071_by.func_70441_a(new ItemStack(SMModContainer.GArrowItem, 1)))
/* 679 */         flag = false; 
/* 682 */       if (flag) {
/* 684 */         func_85030_a("random.pop", 0.2F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 685 */         p_70100_1_.func_71001_a((Entity)this, 1);
/* 686 */         func_70106_y();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean func_70041_e_() {
/* 694 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R() {
/* 699 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void func_70239_b(double p_70239_1_) {
/* 704 */     this.damage = p_70239_1_;
/*     */   }
/*     */   
/*     */   public double func_70242_d() {
/* 709 */     return this.damage;
/*     */   }
/*     */   
/*     */   public void func_70240_a(int p_70240_1_) {
/* 714 */     this.knockbackStrength = p_70240_1_;
/*     */   }
/*     */   
/*     */   public boolean func_70075_an() {
/* 719 */     return false;
/*     */   }
/*     */   
/*     */   public void func_70243_d(boolean p_70243_1_) {
/* 724 */     byte b0 = this.field_70180_af.func_75683_a(16);
/* 726 */     if (p_70243_1_) {
/* 728 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 0x1)));
/*     */     } else {
/* 732 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean func_70241_g() {
/* 739 */     byte b0 = this.field_70180_af.func_75683_a(16);
/* 740 */     return ((b0 & 0x1) != 0);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */