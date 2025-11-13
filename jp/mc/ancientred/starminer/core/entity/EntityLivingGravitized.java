/*      */ package jp.mc.ancientred.starminer.core.entity;
/*      */ 
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*      */ import jp.mc.ancientred.starminer.core.CoreConfig;
/*      */ import jp.mc.ancientred.starminer.core.SMCoreModContainer;
/*      */ import jp.mc.ancientred.starminer.core.TransformUtils;
/*      */ import jp.mc.ancientred.starminer.core.common.VecUtils;
/*      */ import jp.mc.ancientred.starminer.core.obfuscar.SMCoreReflectionHelper;
/*      */ import jp.mc.ancientred.starminer.core.packet.SMCorePacketSender;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.server.management.ItemInWorldManager;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ReportedException;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraftforge.common.ForgeHooks;
/*      */ 
/*      */ public abstract class EntityLivingGravitized extends EntityLivingBase {
/*   39 */   private static ThreadLocal<List> thListHold = new ThreadLocal<List>();
/*      */   
/*      */   private int nextStepDistancePrivate;
/*      */   
/*      */   public EntityLivingGravitized(World par1World) {
/*   44 */     super(par1World);
/*   45 */     this.nextStepDistancePrivate = 1;
/*      */   }
/*      */   
/*   48 */   private int invalidGravityTickCount = 0;
/*      */   
/*   50 */   private int gravityUpdateTick = 0;
/*      */   
/*   52 */   public int redistInOpaqueBlockDamageTick = 0;
/*      */   
/*   54 */   public int commonInstanceFlag = 0;
/*      */   
/*   56 */   private boolean hasInitPrivate = false;
/*      */   
/*      */   protected boolean collidedGravityHorizontally;
/*      */   
/*      */   private void init() {
/*   62 */     if (this instanceof EntityPlayerMP) {
/*   64 */       ItemInWorldManager itemInWorldManager = ((EntityPlayerMP)this).field_71134_c;
/*   65 */       if (itemInWorldManager != null)
/*   66 */         itemInWorldManager.setBlockReachDistance(itemInWorldManager.getBlockReachDistance() + 2.0D); 
/*      */     } 
/*   69 */     this.hasInitPrivate = true;
/*      */   }
/*      */   
/*      */   public void func_70030_z() {
/*   74 */     ExtendedPropertyGravity gravity = ExtendedPropertyGravity.getExtendedPropertyGravity((Entity)this);
/*   75 */     boolean isOutSpace = this.field_70170_p.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider;
/*   76 */     boolean isGravityZero = gravity.isZeroGravity(isOutSpace);
/*   79 */     if (this instanceof EntityPlayer)
/*   80 */       if (this.field_70170_p.field_72995_K) {
/*   82 */         if (!SMCoreModContainer.proxy.isOtherPlayer((EntityPlayer)this)) {
/*   85 */           gravity.keepGravityDirTick--;
/*   86 */           if (gravity.keepGravityDirTick < 0)
/*   87 */             gravity.keepGravityDirTick = 0; 
/*   90 */           gravity.temporatyZeroGTick--;
/*   91 */           if (gravity.temporatyZeroGTick < 0)
/*   92 */             gravity.temporatyZeroGTick = 0; 
/*   95 */           boolean sendGravityStatePacket = false;
/*   96 */           if (this.field_70154_o != null) {
/*   99 */             gravity.changeGravityDirection(GravityDirection.upTOdown_YN);
/*  100 */             gravity.turnRate = 1.0F;
/*  102 */             changeGravityToNext(gravity);
/*  103 */             sendGravityStatePacket = true;
/*      */           } else {
/*  106 */             gravity.prevTurnRate = gravity.turnRate;
/*  107 */             if (gravity.turnRate < 1.0F) {
/*  109 */               gravity.turnRate += gravity.turnSpeed;
/*  110 */               gravity.turnSpeed *= 1.7F;
/*  113 */               if (gravity.turnRate >= 1.0F) {
/*  114 */                 changeGravityToNext(gravity);
/*  115 */                 sendGravityStatePacket = true;
/*      */               } 
/*      */             } 
/*  120 */             if (!isGravityZero)
/*  122 */               gravity.updateGravityDirectionState((Entity)this); 
/*      */           } 
/*  127 */           if (sendGravityStatePacket || ++this.gravityUpdateTick % CoreConfig.gravityUpdateFreq == 0) {
/*  128 */             SMCorePacketSender.sendGravityStatePacketToServer((Entity)this, gravity.gravityDirection);
/*  129 */             this.gravityUpdateTick = 0;
/*      */           } 
/*  133 */           gravity.normalGravityEffectRedistTick--;
/*  134 */           if (gravity.normalGravityEffectRedistTick < 0)
/*  135 */             gravity.normalGravityEffectRedistTick = 0; 
/*      */         } 
/*      */       } else {
/*  140 */         if (!this.hasInitPrivate)
/*  141 */           init(); 
/*  146 */         gravity.attractUpdateTickCount++;
/*  148 */         if (this.field_70154_o != null) {
/*  150 */           gravity.loseAttractedBy();
/*  152 */           gravity.gravityDirection = GravityDirection.upTOdown_YN;
/*      */         } else {
/*  155 */           gravity.updateAtractedStateAndGravityZero((EntityPlayerMP)this);
/*      */         } 
/*  159 */         gravity.validateGravity((EntityPlayer)this, isGravityZero);
/*  160 */         if (gravity.illegalGStateTickCount > CoreConfig.illegalGStateTickToCheck) {
/*  161 */           if (!func_70608_bn()) {
/*  163 */             gravity.unsynchronizedWarnCount++;
/*      */             try {
/*  166 */               SMCoreModContainer.proxy.warnOrKickIllegalGravity(this, gravity);
/*      */             } catch (Exception ex) {
/*  167 */               ex.printStackTrace();
/*      */             } 
/*      */           } 
/*  170 */           gravity.illegalGStateTickCount = 0;
/*      */         } 
/*  174 */         this.redistInOpaqueBlockDamageTick--;
/*  175 */         if (this.redistInOpaqueBlockDamageTick < 0)
/*  176 */           this.redistInOpaqueBlockDamageTick = 0; 
/*  180 */         gravity.acceptExceptionalGravityTick--;
/*  181 */         if (gravity.acceptExceptionalGravityTick < 0)
/*  182 */           gravity.acceptExceptionalGravityTick = 0; 
/*      */       }  
/*  187 */     super.func_70030_z();
/*  190 */     if (this instanceof EntityPlayer && 
/*  191 */       !this.field_70170_p.field_72995_K && !this.field_70128_L)
/*  193 */       if (this.field_70163_u < -128.0D)
/*  194 */         func_70076_C();  
/*  200 */     gravity.changeGravityImmidiate = false;
/*      */   }
/*      */   
/*      */   private void changeGravityToNext(ExtendedPropertyGravity gravity) {
/*  207 */     gravity.gravityDirection = gravity.gravityDirectionNext;
/*  208 */     this.field_70177_z += gravity.onChangeTurnYaw;
/*  209 */     this.field_70126_B += gravity.onChangeTurnYaw;
/*  210 */     this.field_70761_aq += gravity.onChangeTurnYaw;
/*  211 */     this.field_70760_ar += gravity.onChangeTurnYaw;
/*  212 */     this.field_70143_R = 0.0F;
/*  214 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*      */   }
/*      */   
/*      */   public void func_71038_i() {
/*  219 */     super.func_71038_i();
/*  222 */     if (!func_70093_af() && ExtendedPropertyGravity.isEntityZeroGravity((Entity)this) && this instanceof EntityPlayer && func_70694_bm() == null) {
/*  224 */       Vec3 look = func_70040_Z();
/*  225 */       double speed = 0.02D;
/*  226 */       double min = -0.25D;
/*  227 */       double max = 0.25D;
/*  228 */       if ((look.field_72450_a > 0.0D && min <= this.field_70159_w) || (look.field_72450_a < 0.0D && max >= this.field_70159_w))
/*  230 */         this.field_70159_w -= look.field_72450_a * speed; 
/*  232 */       if ((look.field_72448_b > 0.0D && min <= this.field_70181_x) || (look.field_72448_b < 0.0D && max >= this.field_70181_x))
/*  234 */         this.field_70181_x -= look.field_72448_b * speed; 
/*  236 */       if ((look.field_72449_c > 0.0D && min <= this.field_70179_y) || (look.field_72449_c < 0.0D && max >= this.field_70179_y))
/*  238 */         this.field_70179_y -= look.field_72449_c * speed; 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void func_70064_a(double par1, boolean par3) {
/*  246 */     if (ExtendedPropertyGravity.isAttracted((Entity)this))
/*      */       return; 
/*  248 */     if (ExtendedPropertyGravity.isEntityZeroGravity((Entity)this))
/*      */       return; 
/*  250 */     super.func_70064_a(par1, par3);
/*      */   }
/*      */   
/*      */   public boolean func_70094_T() {
/*  258 */     if (this.redistInOpaqueBlockDamageTick > 0)
/*  258 */       return false; 
/*  260 */     ExtendedPropertyGravity gravity = ExtendedPropertyGravity.getExtendedPropertyGravity((Entity)this);
/*  261 */     if (gravity.gravityDirection != GravityDirection.upTOdown_YN) {
/*      */       double entityPosX, entityPosY, entityPosZ;
/*  266 */       switch (ExtendedPropertyGravity.getGravityDirection((Entity)this)) {
/*      */         case southTOnorth_ZN:
/*  268 */           entityPosX = this.field_70121_D.field_72340_a + (this.field_70121_D.field_72336_d - this.field_70121_D.field_72340_a) / 2.0D;
/*  269 */           entityPosY = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) / 2.0D;
/*  270 */           entityPosZ = this.field_70121_D.field_72339_c + (double)func_70047_e();
/*      */           break;
/*      */         case northTOsouth_ZP:
/*  273 */           entityPosX = this.field_70121_D.field_72340_a + (this.field_70121_D.field_72336_d - this.field_70121_D.field_72340_a) / 2.0D;
/*  274 */           entityPosY = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) / 2.0D;
/*  275 */           entityPosZ = this.field_70121_D.field_72334_f - (double)func_70047_e();
/*      */           break;
/*      */         case westTOeast_XP:
/*  278 */           entityPosX = this.field_70121_D.field_72336_d - (double)func_70047_e();
/*  279 */           entityPosY = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) / 2.0D;
/*  280 */           entityPosZ = this.field_70121_D.field_72339_c + (this.field_70121_D.field_72334_f - this.field_70121_D.field_72339_c) / 2.0D;
/*      */           break;
/*      */         case eastTOwest_XN:
/*  283 */           entityPosX = this.field_70121_D.field_72340_a + (double)func_70047_e();
/*  284 */           entityPosY = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) / 2.0D;
/*  285 */           entityPosZ = this.field_70121_D.field_72339_c + (this.field_70121_D.field_72334_f - this.field_70121_D.field_72339_c) / 2.0D;
/*      */           break;
/*      */         case downTOup_YP:
/*  288 */           entityPosX = this.field_70121_D.field_72340_a + (this.field_70121_D.field_72336_d - this.field_70121_D.field_72340_a) / 2.0D;
/*  289 */           entityPosY = this.field_70121_D.field_72337_e - (double)func_70047_e();
/*  290 */           entityPosZ = this.field_70121_D.field_72339_c + (this.field_70121_D.field_72334_f - this.field_70121_D.field_72339_c) / 2.0D;
/*      */           break;
/*      */         default:
/*  294 */           entityPosX = this.field_70121_D.field_72340_a + (this.field_70121_D.field_72336_d - this.field_70121_D.field_72340_a) / 2.0D;
/*  295 */           entityPosY = this.field_70121_D.field_72338_b + (double)func_70047_e();
/*  296 */           entityPosZ = this.field_70121_D.field_72339_c + (this.field_70121_D.field_72334_f - this.field_70121_D.field_72339_c) / 2.0D;
/*      */           break;
/*      */       } 
/*  300 */       if (this.field_70170_p.func_147439_a(MathHelper.func_76128_c(entityPosX), MathHelper.func_76128_c(entityPosY), MathHelper.func_76128_c(entityPosZ)).func_149721_r())
/*  302 */         return true; 
/*      */     } else {
/*  306 */       for (int i = 0; i < 8; i++) {
/*  308 */         float addX = ((float)((i >> 0) % 2) - 0.5F) * this.field_70130_N * 0.8F;
/*  309 */         float addY = ((float)((i >> 1) % 2) - 0.5F) * 0.1F;
/*  310 */         float addZ = ((float)((i >> 2) % 2) - 0.5F) * this.field_70130_N * 0.8F;
/*  311 */         int x = MathHelper.func_76128_c(this.field_70165_t + (double)addX);
/*  312 */         int y = MathHelper.func_76128_c(this.field_70163_u + (double)func_70047_e() + (double)addY);
/*  313 */         int z = MathHelper.func_76128_c(this.field_70161_v + (double)addZ);
/*  315 */         if (this.field_70170_p.func_147439_a(x, y, z).func_149721_r())
/*  317 */           return true; 
/*      */       } 
/*      */     } 
/*  321 */     return false;
/*      */   }
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public Vec3 func_70666_h(float par1) {
/*  331 */     if (par1 == 1.0F)
/*  334 */       return TransformUtils.fixEyePositionByGravityClient((Entity)this, VecUtils.createVec3(this.field_70165_t, this.field_70163_u, this.field_70161_v)); 
/*  340 */     double d0 = this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)par1;
/*  341 */     double d1 = this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)par1;
/*  342 */     double d2 = this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)par1;
/*  344 */     return TransformUtils.fixEyePositionByGravityClient((Entity)this, VecUtils.createVec3(d0, d1, d2));
/*      */   }
/*      */   
/*      */   public Vec3 func_70676_i(float par1) {
/*      */     float pitch, yaw;
/*  359 */     if (par1 == 1.0F) {
/*  361 */       pitch = this.field_70125_A;
/*  362 */       yaw = this.field_70177_z;
/*      */     } else {
/*  364 */       pitch = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * par1;
/*  365 */       yaw = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * par1;
/*      */     } 
/*  367 */     ExtendedPropertyGravity gravity = ExtendedPropertyGravity.getExtendedPropertyGravity((Entity)this);
/*  368 */     if (gravity == null)
/*  369 */       return super.func_70676_i(par1); 
/*  371 */     return gravity.getGravityFixedLook(pitch, yaw);
/*      */   }
/*      */   
/*      */   protected void func_70664_aZ() {
/*  404 */     switch (ExtendedPropertyGravity.getGravityDirection((Entity)this)) {
/*      */       case southTOnorth_ZN:
/*  406 */         this.field_70179_y = 0.41999998688697815D;
/*  408 */         if (func_70644_a(Potion.field_76430_j))
/*  410 */           this.field_70179_y += (double)((float)(func_70660_b(Potion.field_76430_j).func_76458_c() + 1) * 0.1F); 
/*  413 */         if (func_70051_ag()) {
/*  415 */           float f = this.field_70177_z * 0.017453292F;
/*  416 */           this.field_70159_w -= (double)(MathHelper.func_76126_a(f) * 0.2F);
/*  417 */           this.field_70181_x -= (double)(MathHelper.func_76134_b(f) * 0.2F);
/*      */         } 
/*      */         break;
/*      */       case northTOsouth_ZP:
/*  421 */         this.field_70179_y = -0.41999998688697815D;
/*  423 */         if (func_70644_a(Potion.field_76430_j))
/*  425 */           this.field_70179_y -= (double)((float)(func_70660_b(Potion.field_76430_j).func_76458_c() + 1) * 0.1F); 
/*  428 */         if (func_70051_ag()) {
/*  430 */           float f = this.field_70177_z * 0.017453292F;
/*  431 */           this.field_70159_w -= (double)(MathHelper.func_76126_a(f) * 0.2F);
/*  432 */           this.field_70181_x += (double)(MathHelper.func_76134_b(f) * 0.2F);
/*      */         } 
/*      */         break;
/*      */       case westTOeast_XP:
/*  436 */         this.field_70159_w = -0.41999998688697815D;
/*  438 */         if (func_70644_a(Potion.field_76430_j))
/*  440 */           this.field_70159_w -= (double)((float)(func_70660_b(Potion.field_76430_j).func_76458_c() + 1) * 0.1F); 
/*  443 */         if (func_70051_ag()) {
/*  445 */           float f = this.field_70177_z * 0.017453292F;
/*  446 */           this.field_70181_x -= (double)(MathHelper.func_76126_a(f) * 0.2F);
/*  447 */           this.field_70179_y += (double)(MathHelper.func_76134_b(f) * 0.2F);
/*      */         } 
/*      */         break;
/*      */       case eastTOwest_XN:
/*  451 */         this.field_70159_w = 0.41999998688697815D;
/*  453 */         if (func_70644_a(Potion.field_76430_j))
/*  455 */           this.field_70159_w += (double)((float)(func_70660_b(Potion.field_76430_j).func_76458_c() + 1) * 0.1F); 
/*  458 */         if (func_70051_ag()) {
/*  460 */           float f = this.field_70177_z * 0.017453292F;
/*  461 */           this.field_70181_x += (double)(MathHelper.func_76126_a(f) * 0.2F);
/*  462 */           this.field_70179_y += (double)(MathHelper.func_76134_b(f) * 0.2F);
/*      */         } 
/*      */         break;
/*      */       case downTOup_YP:
/*  466 */         this.field_70181_x = -0.41999998688697815D;
/*  468 */         if (func_70644_a(Potion.field_76430_j))
/*  470 */           this.field_70181_x -= (double)((float)(func_70660_b(Potion.field_76430_j).func_76458_c() + 1) * 0.1F); 
/*  473 */         if (func_70051_ag()) {
/*  475 */           float f = this.field_70177_z * 0.017453292F;
/*  476 */           this.field_70159_w -= (double)(MathHelper.func_76126_a(f) * 0.2F);
/*  477 */           this.field_70179_y -= (double)(MathHelper.func_76134_b(f) * 0.2F);
/*      */         } 
/*      */         break;
/*      */       default:
/*  481 */         this.field_70181_x = 0.41999998688697815D;
/*  483 */         if (func_70644_a(Potion.field_76430_j))
/*  485 */           this.field_70181_x += (double)((float)(func_70660_b(Potion.field_76430_j).func_76458_c() + 1) * 0.1F); 
/*  488 */         if (func_70051_ag()) {
/*  490 */           float f = this.field_70177_z * 0.017453292F;
/*  491 */           this.field_70159_w -= (double)(MathHelper.func_76126_a(f) * 0.2F);
/*  492 */           this.field_70179_y += (double)(MathHelper.func_76134_b(f) * 0.2F);
/*      */         } 
/*      */         break;
/*      */     } 
/*  496 */     this.field_70160_al = true;
/*  497 */     ForgeHooks.onLivingJump(this);
/*      */   }
/*      */   
/*      */   public void func_70612_e(float moveStrafing, float moveForward) {
/*  522 */     ExtendedPropertyGravity gravity = ExtendedPropertyGravity.getExtendedPropertyGravity((Entity)this);
/*  523 */     boolean isGravityZero = gravity.isZeroGravity(this.field_70170_p.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider);
/*  524 */     boolean isAbnormalGravity = (gravity.gravityDirection != GravityDirection.upTOdown_YN);
/*  525 */     boolean isNormalGravity = !isAbnormalGravity;
/*  526 */     if (isNormalGravity && !isGravityZero)
/*  528 */       if (!(this instanceof EntityPlayer) || !((EntityPlayer)this).field_71075_bZ.field_75100_b || this.field_70154_o != null) {
/*  534 */         super.func_70612_e(moveStrafing, moveForward);
/*      */         return;
/*      */       }  
/*  540 */     if (this.field_70703_bu)
/*  542 */       if (func_70090_H() || func_70058_J())
/*  543 */         switch (gravity.gravityDirection) {
/*      */           case southTOnorth_ZN:
/*  545 */             this.field_70181_x -= 0.03999999910593033D;
/*  546 */             this.field_70179_y += 0.03999999910593033D;
/*      */             break;
/*      */           case northTOsouth_ZP:
/*  549 */             this.field_70181_x -= 0.03999999910593033D;
/*  550 */             this.field_70179_y -= 0.03999999910593033D;
/*      */             break;
/*      */           case westTOeast_XP:
/*  553 */             this.field_70181_x -= 0.03999999910593033D;
/*  554 */             this.field_70159_w -= 0.03999999910593033D;
/*      */             break;
/*      */           case eastTOwest_XN:
/*  557 */             this.field_70181_x -= 0.03999999910593033D;
/*  558 */             this.field_70159_w += 0.03999999910593033D;
/*      */             break;
/*      */           case downTOup_YP:
/*  562 */             this.field_70181_x -= 0.03999999910593033D;
/*  563 */             this.field_70181_x -= 0.03999999910593033D;
/*      */             break;
/*      */         }   
/*  571 */     double savedX = this.field_70159_w;
/*  572 */     double savedY = this.field_70181_x;
/*  573 */     double savedZ = this.field_70179_y;
/*  577 */     if (isGravityZero && (
/*  578 */       !(this instanceof EntityPlayer) || !((EntityPlayer)this).field_71075_bZ.field_75100_b)) {
/*  580 */       moveStrafing = 0.0F;
/*  581 */       moveForward = 0.0F;
/*      */     } 
/*  585 */     if (func_70090_H() && (!(this instanceof EntityPlayer) || !((EntityPlayer)this).field_71075_bZ.field_75100_b)) {
/*  587 */       double d = this.field_70163_u;
/*  588 */       func_70060_a(moveStrafing, moveForward, func_70650_aV() ? 0.04F : 0.02F);
/*  589 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*  590 */       this.field_70159_w *= 0.800000011920929D;
/*  591 */       this.field_70181_x *= 0.800000011920929D;
/*  592 */       this.field_70179_y *= 0.800000011920929D;
/*  595 */       switch (gravity.gravityDirection) {
/*      */         case southTOnorth_ZN:
/*  597 */           d = this.field_70161_v;
/*  598 */           this.field_70179_y -= 0.02D;
/*  600 */           if (this.field_70123_F && func_70038_c(this.field_70159_w, this.field_70181_x, this.field_70179_y + 0.6000000238418579D - this.field_70161_v + d))
/*  602 */             this.field_70179_y = 0.30000001192092896D; 
/*      */           break;
/*      */         case northTOsouth_ZP:
/*  606 */           d = this.field_70161_v;
/*  607 */           this.field_70179_y += 0.02D;
/*  609 */           if (this.field_70123_F && func_70038_c(this.field_70159_w, this.field_70181_x, this.field_70179_y - 0.6000000238418579D - this.field_70161_v + d))
/*  611 */             this.field_70179_y = -0.30000001192092896D; 
/*      */           break;
/*      */         case westTOeast_XP:
/*  615 */           d = this.field_70165_t;
/*  616 */           this.field_70159_w += 0.02D;
/*  618 */           if (this.field_70123_F && func_70038_c(this.field_70159_w - 0.6000000238418579D - this.field_70165_t + d, this.field_70181_x, this.field_70179_y))
/*  620 */             this.field_70159_w = -0.30000001192092896D; 
/*      */           break;
/*      */         case eastTOwest_XN:
/*  624 */           d = this.field_70165_t;
/*  625 */           this.field_70159_w -= 0.02D;
/*  627 */           if (this.field_70123_F && func_70038_c(this.field_70159_w + 0.6000000238418579D - this.field_70165_t + d, this.field_70181_x, this.field_70179_y))
/*  629 */             this.field_70159_w = 0.30000001192092896D; 
/*      */           break;
/*      */         case downTOup_YP:
/*  633 */           this.field_70181_x += 0.02D;
/*  635 */           if (this.field_70123_F && func_70038_c(this.field_70159_w, this.field_70181_x - 0.6000000238418579D - this.field_70163_u + d, this.field_70179_y))
/*  637 */             this.field_70181_x = -0.30000001192092896D; 
/*      */           break;
/*      */         default:
/*  641 */           this.field_70181_x -= 0.02D;
/*  643 */           if (this.field_70123_F && func_70038_c(this.field_70159_w, this.field_70181_x + 0.6000000238418579D - this.field_70163_u + d, this.field_70179_y))
/*  645 */             this.field_70181_x = 0.30000001192092896D; 
/*      */           break;
/*      */       } 
/*  657 */     } else if (func_70058_J() && (!(this instanceof EntityPlayer) || !((EntityPlayer)this).field_71075_bZ.field_75100_b)) {
/*  659 */       double d = this.field_70163_u;
/*  660 */       func_70060_a(moveStrafing, moveForward, 0.02F);
/*  661 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*  662 */       this.field_70159_w *= 0.5D;
/*  663 */       this.field_70181_x *= 0.5D;
/*  664 */       this.field_70179_y *= 0.5D;
/*  666 */       switch (gravity.gravityDirection) {
/*      */         case southTOnorth_ZN:
/*  668 */           d = this.field_70161_v;
/*  669 */           this.field_70179_y -= 0.02D;
/*  671 */           if (this.field_70123_F && func_70038_c(this.field_70159_w, this.field_70181_x, this.field_70179_y + 0.6000000238418579D - this.field_70161_v + d))
/*  673 */             this.field_70179_y = 0.30000001192092896D; 
/*      */           break;
/*      */         case northTOsouth_ZP:
/*  677 */           d = this.field_70161_v;
/*  678 */           this.field_70179_y += 0.02D;
/*  680 */           if (this.field_70123_F && func_70038_c(this.field_70159_w, this.field_70181_x, this.field_70179_y - 0.6000000238418579D - this.field_70161_v + d))
/*  682 */             this.field_70179_y = -0.30000001192092896D; 
/*      */           break;
/*      */         case westTOeast_XP:
/*  686 */           d = this.field_70165_t;
/*  687 */           this.field_70159_w += 0.02D;
/*  689 */           if (this.field_70123_F && func_70038_c(this.field_70159_w - 0.6000000238418579D - this.field_70165_t + d, this.field_70181_x, this.field_70179_y))
/*  691 */             this.field_70159_w = -0.30000001192092896D; 
/*      */           break;
/*      */         case eastTOwest_XN:
/*  695 */           d = this.field_70165_t;
/*  696 */           this.field_70159_w -= 0.02D;
/*  698 */           if (this.field_70123_F && func_70038_c(this.field_70159_w + 0.6000000238418579D - this.field_70165_t + d, this.field_70181_x, this.field_70179_y))
/*  700 */             this.field_70159_w = 0.30000001192092896D; 
/*      */           break;
/*      */         case downTOup_YP:
/*  704 */           this.field_70181_x += 0.02D;
/*  706 */           if (this.field_70123_F && func_70038_c(this.field_70159_w, this.field_70181_x - 0.6000000238418579D - this.field_70163_u + d, this.field_70179_y))
/*  708 */             this.field_70181_x = -0.30000001192092896D; 
/*      */           break;
/*      */         default:
/*  712 */           this.field_70181_x -= 0.02D;
/*  714 */           if (this.field_70123_F && func_70038_c(this.field_70159_w, this.field_70181_x + 0.6000000238418579D - this.field_70163_u + d, this.field_70179_y))
/*  716 */             this.field_70181_x = 0.30000001192092896D; 
/*      */           break;
/*      */       } 
/*      */     } else {
/*      */       int blockOnX, blockOnY, blockOnZ;
/*      */       float f4;
/*  733 */       double roatCenterX = this.field_70165_t;
/*  734 */       double roatCenterY = this.field_70163_u - (double)this.field_70129_M + (double)(this.field_70130_N / 2.0F);
/*  735 */       double roatCenterZ = this.field_70161_v;
/*  736 */       switch (gravity.gravityDirection) {
/*      */         case southTOnorth_ZN:
/*  738 */           blockOnX = MathHelper.func_76128_c(roatCenterX);
/*  739 */           blockOnY = MathHelper.func_76128_c(roatCenterY);
/*  740 */           blockOnZ = MathHelper.func_76128_c(roatCenterZ - 1.0D);
/*      */           break;
/*      */         case northTOsouth_ZP:
/*  743 */           blockOnX = MathHelper.func_76128_c(roatCenterX);
/*  744 */           blockOnY = MathHelper.func_76128_c(roatCenterY);
/*  745 */           blockOnZ = MathHelper.func_76128_c(roatCenterZ + 1.0D);
/*      */           break;
/*      */         case westTOeast_XP:
/*  748 */           blockOnX = MathHelper.func_76128_c(roatCenterX + 1.0D);
/*  749 */           blockOnY = MathHelper.func_76128_c(roatCenterY);
/*  750 */           blockOnZ = MathHelper.func_76128_c(roatCenterZ);
/*      */           break;
/*      */         case eastTOwest_XN:
/*  753 */           blockOnX = MathHelper.func_76128_c(roatCenterX - 1.0D);
/*  754 */           blockOnY = MathHelper.func_76128_c(roatCenterY);
/*  755 */           blockOnZ = MathHelper.func_76128_c(roatCenterZ);
/*      */           break;
/*      */         case downTOup_YP:
/*  758 */           blockOnX = MathHelper.func_76128_c(roatCenterX);
/*  759 */           blockOnY = MathHelper.func_76128_c(roatCenterY + 1.5D);
/*  760 */           blockOnZ = MathHelper.func_76128_c(roatCenterZ);
/*      */           break;
/*      */         default:
/*  763 */           blockOnX = MathHelper.func_76128_c(this.field_70165_t);
/*  764 */           blockOnY = MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1;
/*  765 */           blockOnZ = MathHelper.func_76128_c(this.field_70161_v);
/*      */           break;
/*      */       } 
/*  768 */       Block blockOn = this.field_70170_p.func_147439_a(blockOnX, blockOnY, blockOnZ);
/*  771 */       float horizontalSpeed = 0.91F;
/*  773 */       if (this.field_70122_E) {
/*  775 */         horizontalSpeed = 0.54600006F;
/*  777 */         Block i = blockOn;
/*  779 */         if (i != null)
/*  781 */           horizontalSpeed = i.field_149765_K * 0.91F; 
/*      */       } 
/*  785 */       float f3 = 0.16277136F / (horizontalSpeed * horizontalSpeed * horizontalSpeed);
/*  788 */       if (this.field_70122_E) {
/*  790 */         f4 = func_70689_ay() * f3;
/*      */       } else {
/*  794 */         f4 = this.field_70747_aH;
/*      */       } 
/*  797 */       func_70060_a(moveStrafing, moveForward, f4);
/*  799 */       horizontalSpeed = 0.91F;
/*  801 */       if (this.field_70122_E) {
/*  803 */         horizontalSpeed = 0.54600006F;
/*  805 */         Block j = blockOn;
/*  806 */         if (j != null)
/*  808 */           horizontalSpeed = j.field_149765_K * 0.91F; 
/*      */       } 
/*  812 */       if (!isGravityZero && 
/*  813 */         func_70617_f_()) {
/*  816 */         double dLimit = 0.15000000596046448D;
/*  817 */         this.field_70143_R = 0.0F;
/*  819 */         if (gravity.gravityDirection != GravityDirection.eastTOwest_XN) {
/*  820 */           if (this.field_70159_w < -dLimit)
/*  822 */             this.field_70159_w = -dLimit; 
/*  824 */         } else if (gravity.gravityDirection != GravityDirection.westTOeast_XP && 
/*  825 */           this.field_70159_w > dLimit) {
/*  827 */           this.field_70159_w = dLimit;
/*      */         } 
/*  831 */         if (gravity.gravityDirection != GravityDirection.southTOnorth_ZN) {
/*  832 */           if (this.field_70179_y < -dLimit)
/*  834 */             this.field_70179_y = -dLimit; 
/*  836 */         } else if (gravity.gravityDirection != GravityDirection.northTOsouth_ZP && 
/*  837 */           this.field_70179_y > dLimit) {
/*  839 */           this.field_70179_y = dLimit;
/*      */         } 
/*  843 */         if (gravity.gravityDirection != GravityDirection.upTOdown_YN) {
/*  844 */           if (this.field_70181_x > dLimit)
/*  846 */             this.field_70181_x = dLimit; 
/*  848 */         } else if (gravity.gravityDirection != GravityDirection.downTOup_YP && 
/*  849 */           this.field_70181_x < -dLimit) {
/*  851 */           this.field_70181_x = -dLimit;
/*      */         } 
/*  855 */         boolean flag = (func_70093_af() && this instanceof EntityPlayer);
/*  857 */         switch (gravity.gravityDirection) {
/*      */           case southTOnorth_ZN:
/*  859 */             if (flag && this.field_70179_y < 0.0D)
/*  861 */               this.field_70179_y = 0.0D; 
/*      */             break;
/*      */           case northTOsouth_ZP:
/*  865 */             if (flag && this.field_70179_y > 0.0D)
/*  867 */               this.field_70179_y = 0.0D; 
/*      */             break;
/*      */           case westTOeast_XP:
/*  871 */             if (flag && this.field_70159_w > 0.0D)
/*  873 */               this.field_70159_w = 0.0D; 
/*      */             break;
/*      */           case eastTOwest_XN:
/*  877 */             if (flag && this.field_70159_w < 0.0D)
/*  879 */               this.field_70159_w = 0.0D; 
/*      */             break;
/*      */           case downTOup_YP:
/*  883 */             if (flag && this.field_70181_x > 0.0D)
/*  885 */               this.field_70181_x = 0.0D; 
/*      */             break;
/*      */           default:
/*  889 */             if (flag && this.field_70181_x < 0.0D)
/*  891 */               this.field_70181_x = 0.0D; 
/*      */             break;
/*      */         } 
/*      */       } 
/*  949 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*  951 */       if (!isGravityZero) {
/*  952 */         if (func_70617_f_())
/*  954 */           switch (ExtendedPropertyGravity.getGravityDirection((Entity)this)) {
/*      */             case southTOnorth_ZN:
/*  956 */               if (this.collidedGravityHorizontally)
/*  957 */                 this.field_70179_y = 0.2D; 
/*      */               break;
/*      */             case northTOsouth_ZP:
/*  961 */               if (this.collidedGravityHorizontally)
/*  962 */                 this.field_70179_y = -0.2D; 
/*      */               break;
/*      */             case westTOeast_XP:
/*  966 */               if (this.collidedGravityHorizontally)
/*  967 */                 this.field_70159_w = -0.2D; 
/*      */               break;
/*      */             case eastTOwest_XN:
/*  971 */               if (this.collidedGravityHorizontally)
/*  972 */                 this.field_70159_w = 0.2D; 
/*      */               break;
/*      */             case downTOup_YP:
/*  976 */               if (this.field_70123_F)
/*  977 */                 this.field_70181_x = -0.2D; 
/*      */               break;
/*      */             default:
/*  981 */               if (this.field_70123_F)
/*  982 */                 this.field_70181_x = 0.2D; 
/*      */               break;
/*      */           }  
/*  993 */         if (this.field_70170_p.field_72995_K && (!this.field_70170_p.func_72899_e((int)this.field_70165_t, 0, (int)this.field_70161_v) || !(this.field_70170_p.func_72938_d((int)this.field_70165_t, (int)this.field_70161_v)).field_76636_d)) {
/*  996 */           if (this.field_70163_u > 0.0D) {
/*  999 */             switch (ExtendedPropertyGravity.getGravityDirection((Entity)this)) {
/*      */               case southTOnorth_ZN:
/* 1001 */                 this.field_70179_y -= 0.1D;
/*      */                 break;
/*      */               case northTOsouth_ZP:
/* 1004 */                 this.field_70179_y += 0.1D;
/*      */                 break;
/*      */               case westTOeast_XP:
/* 1007 */                 this.field_70159_w += 0.1D;
/*      */                 break;
/*      */               case eastTOwest_XN:
/* 1010 */                 this.field_70159_w -= 0.1D;
/*      */                 break;
/*      */               case downTOup_YP:
/* 1013 */                 this.field_70181_x += 0.1D;
/*      */                 break;
/*      */               default:
/* 1016 */                 this.field_70181_x -= 0.1D;
/*      */                 break;
/*      */             } 
/*      */           } else {
/* 1027 */             this.field_70181_x = 0.0D;
/*      */           } 
/*      */         } else {
/* 1033 */           switch (ExtendedPropertyGravity.getGravityDirection((Entity)this)) {
/*      */             case southTOnorth_ZN:
/* 1035 */               this.field_70179_y -= 0.08D;
/*      */               break;
/*      */             case northTOsouth_ZP:
/* 1038 */               this.field_70179_y += 0.08D;
/*      */               break;
/*      */             case westTOeast_XP:
/* 1041 */               this.field_70159_w += 0.08D;
/*      */               break;
/*      */             case eastTOwest_XN:
/* 1044 */               this.field_70159_w -= 0.08D;
/*      */               break;
/*      */             case downTOup_YP:
/* 1047 */               this.field_70181_x += 0.08D;
/*      */               break;
/*      */             default:
/* 1050 */               this.field_70181_x -= 0.08D;
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1060 */       if (!isGravityZero || (this instanceof EntityPlayer && ((EntityPlayer)this).field_71075_bZ.field_75100_b))
/* 1062 */         switch (ExtendedPropertyGravity.getGravityDirection((Entity)this)) {
/*      */           case southTOnorth_ZN:
/*      */           case northTOsouth_ZP:
/* 1065 */             this.field_70181_x *= (double)horizontalSpeed;
/* 1066 */             this.field_70159_w *= (double)horizontalSpeed;
/* 1067 */             this.field_70179_y *= isGravityZero ? 0.8D : 0.9800000190734863D;
/*      */             break;
/*      */           case westTOeast_XP:
/*      */           case eastTOwest_XN:
/* 1071 */             this.field_70181_x *= (double)horizontalSpeed;
/* 1072 */             this.field_70159_w *= isGravityZero ? 0.8D : 0.9800000190734863D;
/* 1073 */             this.field_70179_y *= (double)horizontalSpeed;
/*      */             break;
/*      */           default:
/* 1077 */             this.field_70181_x *= isGravityZero ? 0.8D : 0.9800000190734863D;
/* 1078 */             this.field_70159_w *= (double)horizontalSpeed;
/* 1079 */             this.field_70179_y *= (double)horizontalSpeed;
/*      */             break;
/*      */         }  
/*      */     } 
/* 1089 */     this.field_70722_aY = this.field_70721_aZ;
/* 1090 */     double d0 = this.field_70165_t - this.field_70169_q;
/* 1091 */     double d1 = this.field_70161_v - this.field_70166_s;
/* 1093 */     double d2 = this.field_70163_u - this.field_70167_r;
/* 1094 */     float f6 = MathHelper.func_76133_a(d0 * d0 + d1 * d1 + d2 * d2) * 4.0F;
/* 1098 */     if (f6 > 1.0F)
/* 1100 */       f6 = 1.0F; 
/* 1103 */     this.field_70721_aZ += (f6 - this.field_70721_aZ) * 0.4F;
/* 1104 */     this.field_70754_ba += this.field_70721_aZ;
/* 1107 */     if (!isGravityZero && 
/* 1108 */       this instanceof EntityPlayer && ((EntityPlayer)this).field_71075_bZ.field_75100_b && this.field_70154_o == null)
/* 1111 */       switch (ExtendedPropertyGravity.getGravityDirection((Entity)this)) {
/*      */         case southTOnorth_ZN:
/*      */         case northTOsouth_ZP:
/* 1114 */           this.field_70179_y = savedZ * 0.6D;
/*      */           break;
/*      */         case westTOeast_XP:
/*      */         case eastTOwest_XN:
/* 1118 */           this.field_70159_w = savedX * 0.6D;
/*      */           break;
/*      */         default:
/* 1121 */           this.field_70181_x = savedY * 0.6D;
/*      */           break;
/*      */       }  
/*      */   }
/*      */   
/*      */   public void func_70091_d(double argXMove, double argYMove, double argZMove) {
/* 1133 */     List bbList = thListHold.get();
/* 1134 */     if (bbList == null)
/* 1134 */       thListHold.set(bbList = new ArrayList()); 
/* 1137 */     ExtendedPropertyGravity gravity = ExtendedPropertyGravity.getExtendedPropertyGravity((Entity)this);
/* 1138 */     GravityDirection gDir = gravity.gravityDirection;
/* 1139 */     boolean isAbnormalGravity = (gDir != GravityDirection.upTOdown_YN);
/* 1140 */     boolean isNormalGravity = !isAbnormalGravity;
/* 1143 */     if (this.field_70145_X) {
/* 1145 */       this.field_70121_D.func_72317_d(argXMove, argYMove, argZMove);
/* 1147 */       switch (ExtendedPropertyGravity.getGravityDirection((Entity)this)) {
/*      */         case northTOsouth_ZP:
/* 1149 */           this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
/* 1150 */           this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M;
/* 1151 */           this.field_70161_v = this.field_70121_D.field_72334_f - (double)(this.field_70130_N / 2.0F);
/*      */           break;
/*      */         case southTOnorth_ZN:
/* 1154 */           this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
/* 1155 */           this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M;
/* 1156 */           this.field_70161_v = this.field_70121_D.field_72339_c + (double)(this.field_70130_N / 2.0F);
/*      */           break;
/*      */         case westTOeast_XP:
/* 1159 */           this.field_70165_t = this.field_70121_D.field_72336_d - (double)(this.field_70130_N / 2.0F);
/* 1160 */           this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M;
/* 1161 */           this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
/*      */           break;
/*      */         case eastTOwest_XN:
/* 1164 */           this.field_70165_t = this.field_70121_D.field_72340_a + (double)(this.field_70130_N / 2.0F);
/* 1165 */           this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M;
/* 1166 */           this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
/*      */           break;
/*      */         case downTOup_YP:
/* 1169 */           this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
/* 1170 */           this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M;
/* 1171 */           this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
/*      */           break;
/*      */         default:
/* 1174 */           this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
/* 1175 */           this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M - (double)this.field_70139_V;
/* 1176 */           this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
/*      */           break;
/*      */       } 
/*      */     } else {
/* 1186 */       this.field_70170_p.field_72984_F.func_76320_a("move");
/* 1187 */       this.field_70139_V *= 0.4F;
/* 1188 */       double savedPosX = this.field_70165_t;
/* 1189 */       double savedPosY = this.field_70163_u;
/* 1190 */       double savedPosZ = this.field_70161_v;
/* 1192 */       if (this.field_70134_J) {
/* 1194 */         this.field_70134_J = false;
/* 1195 */         argXMove *= 0.25D;
/* 1196 */         argYMove *= 0.05000000074505806D;
/* 1197 */         argZMove *= 0.25D;
/* 1198 */         this.field_70159_w = 0.0D;
/* 1199 */         this.field_70181_x = 0.0D;
/* 1200 */         this.field_70179_y = 0.0D;
/*      */       } 
/* 1203 */       double savedXMove = argXMove;
/* 1204 */       double savedYMove = argYMove;
/* 1205 */       double savedZMove = argZMove;
/* 1206 */       AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
/* 1207 */       boolean isSneakingPlayer = (this.field_70122_E && func_70093_af() && this instanceof EntityPlayer);
/* 1209 */       if (isSneakingPlayer) {
/* 1212 */         double aBit = 0.05D;
/* 1213 */         double sub = -1.0D;
/* 1214 */         switch (gDir) {
/*      */           case northTOsouth_ZP:
/* 1216 */             sub = 1.0D;
/*      */           case southTOnorth_ZN:
/* 1218 */             for (; argXMove != 0.0D && getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72325_c(argXMove, 0.0D, sub), gDir).isEmpty(); savedXMove = argXMove) {
/* 1220 */               if (-aBit <= argXMove && argXMove < aBit) {
/* 1220 */                 argXMove = 0.0D;
/* 1221 */               } else if (argXMove > 0.0D) {
/* 1221 */                 argXMove -= aBit;
/*      */               } else {
/* 1222 */                 argXMove += aBit;
/*      */               } 
/*      */             } 
/* 1224 */             for (; argYMove != 0.0D && getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72325_c(0.0D, argYMove, sub), gDir).isEmpty(); savedYMove = argYMove) {
/* 1226 */               if (-aBit <= argYMove && argYMove < aBit) {
/* 1226 */                 argYMove = 0.0D;
/* 1227 */               } else if (argYMove > 0.0D) {
/* 1227 */                 argYMove -= aBit;
/*      */               } else {
/* 1228 */                 argYMove += aBit;
/*      */               } 
/*      */             } 
/* 1230 */             while (argXMove != 0.0D && argYMove != 0.0D && getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72325_c(argXMove, argYMove, sub), gDir).isEmpty()) {
/* 1232 */               if (argXMove < aBit && argXMove >= -aBit) {
/* 1232 */                 argXMove = 0.0D;
/* 1233 */               } else if (argXMove > 0.0D) {
/* 1233 */                 argXMove -= aBit;
/*      */               } else {
/* 1234 */                 argXMove += aBit;
/*      */               } 
/* 1236 */               if (argYMove < aBit && argYMove >= -aBit) {
/* 1236 */                 argYMove = 0.0D;
/* 1237 */               } else if (argYMove > 0.0D) {
/* 1237 */                 argYMove -= aBit;
/*      */               } else {
/* 1238 */                 argYMove += aBit;
/*      */               } 
/* 1240 */               savedXMove = argXMove;
/* 1241 */               savedYMove = argYMove;
/*      */             } 
/*      */             break;
/*      */           case westTOeast_XP:
/* 1245 */             sub = 1.0D;
/*      */           case eastTOwest_XN:
/* 1247 */             for (; argYMove != 0.0D && getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72325_c(sub, argYMove, 0.0D), gDir).isEmpty(); savedYMove = argYMove) {
/* 1249 */               if (-aBit <= argYMove && argYMove < aBit) {
/* 1249 */                 argYMove = 0.0D;
/* 1250 */               } else if (argYMove > 0.0D) {
/* 1250 */                 argYMove -= aBit;
/*      */               } else {
/* 1251 */                 argYMove += aBit;
/*      */               } 
/*      */             } 
/* 1253 */             for (; argZMove != 0.0D && getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72325_c(sub, 0.0D, argZMove), gDir).isEmpty(); savedZMove = argZMove) {
/* 1255 */               if (-aBit <= argZMove && argZMove < aBit) {
/* 1255 */                 argZMove = 0.0D;
/* 1256 */               } else if (argZMove > 0.0D) {
/* 1256 */                 argZMove -= aBit;
/*      */               } else {
/* 1257 */                 argZMove += aBit;
/*      */               } 
/*      */             } 
/* 1259 */             while (argZMove != 0.0D && argYMove != 0.0D && getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72325_c(sub, argYMove, argZMove), gDir).isEmpty()) {
/* 1261 */               if (argZMove < aBit && argZMove >= -aBit) {
/* 1261 */                 argZMove = 0.0D;
/* 1262 */               } else if (argZMove > 0.0D) {
/* 1262 */                 argZMove -= aBit;
/*      */               } else {
/* 1263 */                 argZMove += aBit;
/*      */               } 
/* 1265 */               if (argYMove < aBit && argYMove >= -aBit) {
/* 1265 */                 argYMove = 0.0D;
/* 1266 */               } else if (argYMove > 0.0D) {
/* 1266 */                 argYMove -= aBit;
/*      */               } else {
/* 1267 */                 argYMove += aBit;
/*      */               } 
/* 1269 */               savedZMove = argZMove;
/* 1270 */               savedYMove = argYMove;
/*      */             } 
/*      */             break;
/*      */           case downTOup_YP:
/* 1274 */             sub = 1.0D;
/*      */           default:
/* 1276 */             for (; argXMove != 0.0D && getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72325_c(argXMove, sub, 0.0D), gDir).isEmpty(); savedXMove = argXMove) {
/* 1278 */               if (-aBit <= argXMove && argXMove < aBit) {
/* 1278 */                 argXMove = 0.0D;
/* 1279 */               } else if (argXMove > 0.0D) {
/* 1279 */                 argXMove -= aBit;
/*      */               } else {
/* 1280 */                 argXMove += aBit;
/*      */               } 
/*      */             } 
/* 1282 */             for (; argZMove != 0.0D && getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72325_c(0.0D, sub, argZMove), gDir).isEmpty(); savedZMove = argZMove) {
/* 1284 */               if (-aBit <= argZMove && argZMove < aBit) {
/* 1284 */                 argZMove = 0.0D;
/* 1285 */               } else if (argZMove > 0.0D) {
/* 1285 */                 argZMove -= aBit;
/*      */               } else {
/* 1286 */                 argZMove += aBit;
/*      */               } 
/*      */             } 
/* 1288 */             while (argXMove != 0.0D && argZMove != 0.0D && getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72325_c(argXMove, sub, argZMove), gDir).isEmpty()) {
/* 1290 */               if (argXMove < aBit && argXMove >= -aBit) {
/* 1290 */                 argXMove = 0.0D;
/* 1291 */               } else if (argXMove > 0.0D) {
/* 1291 */                 argXMove -= aBit;
/*      */               } else {
/* 1292 */                 argXMove += aBit;
/*      */               } 
/* 1294 */               if (argZMove < aBit && argZMove >= -aBit) {
/* 1294 */                 argZMove = 0.0D;
/* 1295 */               } else if (argZMove > 0.0D) {
/* 1295 */                 argZMove -= aBit;
/*      */               } else {
/* 1296 */                 argZMove += aBit;
/*      */               } 
/* 1298 */               savedXMove = argXMove;
/* 1299 */               savedZMove = argZMove;
/*      */             } 
/*      */             break;
/*      */         } 
/*      */       } 
/* 1370 */       List<AxisAlignedBB> list = getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72321_a(argXMove, argYMove, argZMove), gDir);
/* 1372 */       for (int i = 0; i < list.size(); i++)
/* 1374 */         argYMove = list.get(i).func_72323_b(this.field_70121_D, argYMove); 
/* 1377 */       this.field_70121_D.func_72317_d(0.0D, argYMove, 0.0D);
/* 1379 */       if (!this.field_70135_K && savedYMove != argYMove) {
/* 1381 */         argZMove = 0.0D;
/* 1382 */         argYMove = 0.0D;
/* 1383 */         argXMove = 0.0D;
/*      */       } 
/* 1386 */       boolean flag1 = (this.field_70122_E || (savedYMove != argYMove && savedYMove < 0.0D));
/* 1389 */       for (int k = 0; k < list.size(); k++)
/* 1391 */         argXMove = list.get(k).func_72316_a(this.field_70121_D, argXMove); 
/* 1394 */       this.field_70121_D.func_72317_d(argXMove, 0.0D, 0.0D);
/* 1396 */       if (!this.field_70135_K && savedXMove != argXMove) {
/* 1398 */         argZMove = 0.0D;
/* 1399 */         argYMove = 0.0D;
/* 1400 */         argXMove = 0.0D;
/*      */       } 
/* 1403 */       for (int j = 0; j < list.size(); j++)
/* 1405 */         argZMove = list.get(j).func_72322_c(this.field_70121_D, argZMove); 
/* 1408 */       this.field_70121_D.func_72317_d(0.0D, 0.0D, argZMove);
/* 1410 */       if (!this.field_70135_K && savedZMove != argZMove) {
/* 1412 */         argZMove = 0.0D;
/* 1413 */         argYMove = 0.0D;
/* 1414 */         argXMove = 0.0D;
/*      */       } 
/* 1423 */       this.collidedGravityHorizontally = false;
/* 1424 */       switch (gDir) {
/*      */         case southTOnorth_ZN:
/*      */         case northTOsouth_ZP:
/* 1427 */           this.collidedGravityHorizontally = (savedXMove != argXMove || savedYMove != argYMove);
/*      */           break;
/*      */         case westTOeast_XP:
/*      */         case eastTOwest_XN:
/* 1431 */           this.collidedGravityHorizontally = (savedZMove != argZMove || savedYMove != argYMove);
/*      */           break;
/*      */         default:
/* 1435 */           this.collidedGravityHorizontally = (savedXMove != argXMove || savedZMove != argZMove);
/*      */           break;
/*      */       } 
/* 1438 */       if (this.field_70138_W > 0.0F && flag1 && (isSneakingPlayer || this.field_70139_V < 0.05F) && this.collidedGravityHorizontally) {
/*      */         int m;
/* 1442 */         double d3 = argXMove;
/* 1443 */         double d1 = argYMove;
/* 1444 */         double d2 = argZMove;
/* 1445 */         AxisAlignedBB axisalignedbb1 = this.field_70121_D.func_72329_c();
/* 1446 */         this.field_70121_D.func_72328_c(axisalignedbb);
/* 1448 */         switch (gDir) {
/*      */           case northTOsouth_ZP:
/* 1450 */             argXMove = savedXMove;
/* 1451 */             argYMove = savedYMove;
/* 1452 */             argZMove = -((double)this.field_70138_W);
/* 1453 */             list = getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72321_a(savedXMove, savedYMove, argZMove), gDir);
/* 1454 */             for (m = 0; m < list.size(); m++)
/* 1456 */               argZMove = list.get(m).func_72322_c(this.field_70121_D, argZMove); 
/* 1459 */             this.field_70121_D.func_72317_d(0.0D, 0.0D, argZMove);
/* 1461 */             if (!this.field_70135_K && savedZMove != argZMove) {
/* 1463 */               argZMove = 0.0D;
/* 1464 */               argYMove = 0.0D;
/* 1465 */               argXMove = 0.0D;
/*      */             } 
/* 1467 */             for (m = 0; m < list.size(); m++)
/* 1469 */               argYMove = list.get(m).func_72323_b(this.field_70121_D, argYMove); 
/* 1472 */             this.field_70121_D.func_72317_d(0.0D, argYMove, 0.0D);
/* 1474 */             if (!this.field_70135_K && savedYMove != argYMove) {
/* 1476 */               argZMove = 0.0D;
/* 1477 */               argYMove = 0.0D;
/* 1478 */               argXMove = 0.0D;
/*      */             } 
/* 1481 */             for (m = 0; m < list.size(); m++)
/* 1483 */               argXMove = list.get(m).func_72316_a(this.field_70121_D, argXMove); 
/* 1486 */             this.field_70121_D.func_72317_d(argXMove, 0.0D, 0.0D);
/* 1488 */             if (!this.field_70135_K && savedXMove != argXMove) {
/* 1490 */               argZMove = 0.0D;
/* 1491 */               argYMove = 0.0D;
/* 1492 */               argXMove = 0.0D;
/*      */             } 
/* 1494 */             if (!this.field_70135_K && savedZMove != argZMove) {
/* 1496 */               argZMove = 0.0D;
/* 1497 */               argYMove = 0.0D;
/* 1498 */               argXMove = 0.0D;
/*      */             } else {
/* 1502 */               argZMove = (double)this.field_70138_W;
/* 1504 */               for (int n = 0; n < list.size(); n++)
/* 1506 */                 argZMove = list.get(n).func_72322_c(this.field_70121_D, argZMove); 
/* 1508 */               this.field_70121_D.func_72317_d(0.0D, 0.0D, argZMove);
/*      */             } 
/* 1511 */             if (d3 * d3 + d1 * d1 >= argXMove * argXMove + argYMove * argYMove) {
/* 1513 */               argXMove = d3;
/* 1514 */               argYMove = d1;
/* 1515 */               argZMove = d2;
/* 1516 */               this.field_70121_D.func_72328_c(axisalignedbb1);
/*      */             } 
/*      */             break;
/*      */           case southTOnorth_ZN:
/* 1520 */             argXMove = savedXMove;
/* 1521 */             argYMove = savedYMove;
/* 1522 */             argZMove = (double)this.field_70138_W;
/* 1523 */             list = getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72321_a(savedXMove, savedYMove, argZMove), gDir);
/* 1524 */             for (m = 0; m < list.size(); m++)
/* 1526 */               argZMove = list.get(m).func_72322_c(this.field_70121_D, argZMove); 
/* 1529 */             this.field_70121_D.func_72317_d(0.0D, 0.0D, argZMove);
/* 1531 */             if (!this.field_70135_K && savedZMove != argZMove) {
/* 1533 */               argZMove = 0.0D;
/* 1534 */               argYMove = 0.0D;
/* 1535 */               argXMove = 0.0D;
/*      */             } 
/* 1537 */             for (m = 0; m < list.size(); m++)
/* 1539 */               argYMove = list.get(m).func_72323_b(this.field_70121_D, argYMove); 
/* 1542 */             this.field_70121_D.func_72317_d(0.0D, argYMove, 0.0D);
/* 1544 */             if (!this.field_70135_K && savedYMove != argYMove) {
/* 1546 */               argZMove = 0.0D;
/* 1547 */               argYMove = 0.0D;
/* 1548 */               argXMove = 0.0D;
/*      */             } 
/* 1551 */             for (m = 0; m < list.size(); m++)
/* 1553 */               argXMove = list.get(m).func_72316_a(this.field_70121_D, argXMove); 
/* 1556 */             this.field_70121_D.func_72317_d(argXMove, 0.0D, 0.0D);
/* 1558 */             if (!this.field_70135_K && savedXMove != argXMove) {
/* 1560 */               argZMove = 0.0D;
/* 1561 */               argYMove = 0.0D;
/* 1562 */               argXMove = 0.0D;
/*      */             } 
/* 1564 */             if (!this.field_70135_K && savedZMove != argZMove) {
/* 1566 */               argZMove = 0.0D;
/* 1567 */               argYMove = 0.0D;
/* 1568 */               argXMove = 0.0D;
/*      */             } else {
/* 1572 */               argZMove = (double)-this.field_70138_W;
/* 1574 */               for (int n = 0; n < list.size(); n++)
/* 1576 */                 argZMove = list.get(n).func_72322_c(this.field_70121_D, argZMove); 
/* 1579 */               this.field_70121_D.func_72317_d(0.0D, 0.0D, argZMove);
/*      */             } 
/* 1582 */             if (d3 * d3 + d1 * d1 >= argXMove * argXMove + argYMove * argYMove) {
/* 1584 */               argXMove = d3;
/* 1585 */               argYMove = d1;
/* 1586 */               argZMove = d2;
/* 1587 */               this.field_70121_D.func_72328_c(axisalignedbb1);
/*      */             } 
/*      */             break;
/*      */           case westTOeast_XP:
/* 1591 */             argXMove = -((double)this.field_70138_W);
/* 1592 */             argYMove = savedYMove;
/* 1593 */             argZMove = savedZMove;
/* 1594 */             list = getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72321_a(argXMove, savedYMove, savedZMove), gDir);
/* 1595 */             for (m = 0; m < list.size(); m++)
/* 1597 */               argXMove = list.get(m).func_72316_a(this.field_70121_D, argXMove); 
/* 1600 */             this.field_70121_D.func_72317_d(argXMove, 0.0D, 0.0D);
/* 1602 */             if (!this.field_70135_K && savedXMove != argXMove) {
/* 1604 */               argZMove = 0.0D;
/* 1605 */               argYMove = 0.0D;
/* 1606 */               argXMove = 0.0D;
/*      */             } 
/* 1609 */             for (m = 0; m < list.size(); m++)
/* 1611 */               argYMove = list.get(m).func_72323_b(this.field_70121_D, argYMove); 
/* 1614 */             this.field_70121_D.func_72317_d(0.0D, argYMove, 0.0D);
/* 1616 */             if (!this.field_70135_K && savedYMove != argYMove) {
/* 1618 */               argZMove = 0.0D;
/* 1619 */               argYMove = 0.0D;
/* 1620 */               argXMove = 0.0D;
/*      */             } 
/* 1623 */             for (m = 0; m < list.size(); m++)
/* 1625 */               argZMove = list.get(m).func_72322_c(this.field_70121_D, argZMove); 
/* 1628 */             this.field_70121_D.func_72317_d(0.0D, 0.0D, argZMove);
/* 1630 */             if (!this.field_70135_K && savedZMove != argZMove) {
/* 1632 */               argZMove = 0.0D;
/* 1633 */               argYMove = 0.0D;
/* 1634 */               argXMove = 0.0D;
/*      */             } 
/* 1636 */             if (!this.field_70135_K && savedXMove != savedXMove) {
/* 1638 */               argZMove = 0.0D;
/* 1639 */               argYMove = 0.0D;
/* 1640 */               argXMove = 0.0D;
/*      */             } else {
/* 1644 */               argXMove = (double)this.field_70138_W;
/* 1646 */               for (int n = 0; n < list.size(); n++)
/* 1648 */                 argXMove = list.get(n).func_72316_a(this.field_70121_D, argXMove); 
/* 1650 */               this.field_70121_D.func_72317_d(argXMove, 0.0D, 0.0D);
/*      */             } 
/* 1653 */             if (d1 * d1 + d2 * d2 >= argYMove * argYMove + argZMove * argZMove) {
/* 1655 */               argXMove = d3;
/* 1656 */               argYMove = d1;
/* 1657 */               argZMove = d2;
/* 1658 */               this.field_70121_D.func_72328_c(axisalignedbb1);
/*      */             } 
/*      */             break;
/*      */           case eastTOwest_XN:
/* 1662 */             argXMove = (double)this.field_70138_W;
/* 1663 */             argYMove = savedYMove;
/* 1664 */             argZMove = savedZMove;
/* 1665 */             list = getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72321_a(argXMove, savedYMove, savedZMove), gDir);
/* 1667 */             for (m = 0; m < list.size(); m++)
/* 1669 */               argXMove = list.get(m).func_72316_a(this.field_70121_D, argXMove); 
/* 1672 */             this.field_70121_D.func_72317_d(argXMove, 0.0D, 0.0D);
/* 1674 */             if (!this.field_70135_K && savedXMove != argXMove) {
/* 1676 */               argZMove = 0.0D;
/* 1677 */               argYMove = 0.0D;
/* 1678 */               argXMove = 0.0D;
/*      */             } 
/* 1680 */             for (m = 0; m < list.size(); m++)
/* 1682 */               argYMove = list.get(m).func_72323_b(this.field_70121_D, argYMove); 
/* 1685 */             this.field_70121_D.func_72317_d(0.0D, argYMove, 0.0D);
/* 1687 */             if (!this.field_70135_K && savedYMove != argYMove) {
/* 1689 */               argZMove = 0.0D;
/* 1690 */               argYMove = 0.0D;
/* 1691 */               argXMove = 0.0D;
/*      */             } 
/* 1693 */             for (m = 0; m < list.size(); m++)
/* 1695 */               argZMove = list.get(m).func_72322_c(this.field_70121_D, argZMove); 
/* 1698 */             this.field_70121_D.func_72317_d(0.0D, 0.0D, argZMove);
/* 1700 */             if (!this.field_70135_K && savedZMove != argZMove) {
/* 1702 */               argZMove = 0.0D;
/* 1703 */               argYMove = 0.0D;
/* 1704 */               argXMove = 0.0D;
/*      */             } 
/* 1706 */             if (!this.field_70135_K && savedXMove != savedXMove) {
/* 1708 */               argZMove = 0.0D;
/* 1709 */               argYMove = 0.0D;
/* 1710 */               argXMove = 0.0D;
/*      */             } else {
/* 1714 */               argXMove = (double)-this.field_70138_W;
/* 1715 */               for (int n = 0; n < list.size(); n++)
/* 1717 */                 argXMove = list.get(n).func_72316_a(this.field_70121_D, argXMove); 
/* 1719 */               this.field_70121_D.func_72317_d(argXMove, 0.0D, 0.0D);
/*      */             } 
/* 1722 */             if (d1 * d1 + d2 * d2 >= argYMove * argYMove + argZMove * argZMove) {
/* 1724 */               argXMove = d3;
/* 1725 */               argYMove = d1;
/* 1726 */               argZMove = d2;
/* 1727 */               this.field_70121_D.func_72328_c(axisalignedbb1);
/*      */             } 
/*      */             break;
/*      */           case downTOup_YP:
/* 1731 */             argXMove = savedXMove;
/* 1732 */             argYMove = -((double)this.field_70138_W);
/* 1733 */             argZMove = savedZMove;
/* 1734 */             list = getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72321_a(savedXMove, argYMove, savedZMove), gDir);
/* 1735 */             for (m = 0; m < list.size(); m++)
/* 1737 */               argYMove = list.get(m).func_72323_b(this.field_70121_D, argYMove); 
/* 1740 */             this.field_70121_D.func_72317_d(0.0D, argYMove, 0.0D);
/* 1742 */             if (!this.field_70135_K && savedYMove != argYMove) {
/* 1744 */               argZMove = 0.0D;
/* 1745 */               argYMove = 0.0D;
/* 1746 */               argXMove = 0.0D;
/*      */             } 
/* 1749 */             for (m = 0; m < list.size(); m++)
/* 1751 */               argXMove = list.get(m).func_72316_a(this.field_70121_D, argXMove); 
/* 1754 */             this.field_70121_D.func_72317_d(argXMove, 0.0D, 0.0D);
/* 1756 */             if (!this.field_70135_K && savedXMove != argXMove) {
/* 1758 */               argZMove = 0.0D;
/* 1759 */               argYMove = 0.0D;
/* 1760 */               argXMove = 0.0D;
/*      */             } 
/* 1763 */             for (m = 0; m < list.size(); m++)
/* 1765 */               argZMove = list.get(m).func_72322_c(this.field_70121_D, argZMove); 
/* 1768 */             this.field_70121_D.func_72317_d(0.0D, 0.0D, argZMove);
/* 1770 */             if (!this.field_70135_K && savedZMove != argZMove) {
/* 1772 */               argZMove = 0.0D;
/* 1773 */               argYMove = 0.0D;
/* 1774 */               argXMove = 0.0D;
/*      */             } 
/* 1776 */             if (!this.field_70135_K && savedYMove != argYMove) {
/* 1778 */               argZMove = 0.0D;
/* 1779 */               argYMove = 0.0D;
/* 1780 */               argXMove = 0.0D;
/*      */             } else {
/* 1784 */               argYMove = (double)this.field_70138_W;
/* 1786 */               for (int n = 0; n < list.size(); n++)
/* 1788 */                 argYMove = list.get(n).func_72323_b(this.field_70121_D, argYMove); 
/* 1791 */               this.field_70121_D.func_72317_d(0.0D, argYMove, 0.0D);
/*      */             } 
/* 1794 */             if (d3 * d3 + d2 * d2 >= argXMove * argXMove + argZMove * argZMove) {
/* 1796 */               argXMove = d3;
/* 1797 */               argYMove = d1;
/* 1798 */               argZMove = d2;
/* 1799 */               this.field_70121_D.func_72328_c(axisalignedbb1);
/*      */             } 
/*      */             break;
/*      */           default:
/* 1803 */             argXMove = savedXMove;
/* 1804 */             argYMove = (double)this.field_70138_W;
/* 1805 */             argZMove = savedZMove;
/* 1806 */             list = getCollidingBoundingBoxesPrivate(bbList, this.field_70170_p, (Entity)this, this.field_70121_D.func_72321_a(savedXMove, argYMove, savedZMove), gDir);
/* 1807 */             for (m = 0; m < list.size(); m++)
/* 1809 */               argYMove = list.get(m).func_72323_b(this.field_70121_D, argYMove); 
/* 1812 */             this.field_70121_D.func_72317_d(0.0D, argYMove, 0.0D);
/* 1814 */             if (!this.field_70135_K && savedYMove != argYMove) {
/* 1816 */               argZMove = 0.0D;
/* 1817 */               argYMove = 0.0D;
/* 1818 */               argXMove = 0.0D;
/*      */             } 
/* 1821 */             for (m = 0; m < list.size(); m++)
/* 1823 */               argXMove = list.get(m).func_72316_a(this.field_70121_D, argXMove); 
/* 1826 */             this.field_70121_D.func_72317_d(argXMove, 0.0D, 0.0D);
/* 1828 */             if (!this.field_70135_K && savedXMove != argXMove) {
/* 1830 */               argZMove = 0.0D;
/* 1831 */               argYMove = 0.0D;
/* 1832 */               argXMove = 0.0D;
/*      */             } 
/* 1835 */             for (m = 0; m < list.size(); m++)
/* 1837 */               argZMove = list.get(m).func_72322_c(this.field_70121_D, argZMove); 
/* 1840 */             this.field_70121_D.func_72317_d(0.0D, 0.0D, argZMove);
/* 1842 */             if (!this.field_70135_K && savedZMove != argZMove) {
/* 1844 */               argZMove = 0.0D;
/* 1845 */               argYMove = 0.0D;
/* 1846 */               argXMove = 0.0D;
/*      */             } 
/* 1848 */             if (!this.field_70135_K && savedYMove != argYMove) {
/* 1850 */               argZMove = 0.0D;
/* 1851 */               argYMove = 0.0D;
/* 1852 */               argXMove = 0.0D;
/*      */             } else {
/* 1856 */               argYMove = (double)-this.field_70138_W;
/* 1858 */               for (int n = 0; n < list.size(); n++)
/* 1860 */                 argYMove = list.get(n).func_72323_b(this.field_70121_D, argYMove); 
/* 1863 */               this.field_70121_D.func_72317_d(0.0D, argYMove, 0.0D);
/*      */             } 
/* 1866 */             if (d3 * d3 + d2 * d2 >= argXMove * argXMove + argZMove * argZMove) {
/* 1868 */               argXMove = d3;
/* 1869 */               argYMove = d1;
/* 1870 */               argZMove = d2;
/* 1871 */               this.field_70121_D.func_72328_c(axisalignedbb1);
/*      */             } 
/*      */             break;
/*      */         } 
/*      */       } 
/* 1951 */       this.field_70170_p.field_72984_F.func_76319_b();
/* 1952 */       this.field_70170_p.field_72984_F.func_76320_a("rest");
/* 1954 */       switch (gDir) {
/*      */         case northTOsouth_ZP:
/* 1956 */           this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
/* 1957 */           this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M;
/* 1958 */           this.field_70161_v = this.field_70121_D.field_72334_f - (double)(this.field_70130_N / 2.0F);
/*      */           break;
/*      */         case southTOnorth_ZN:
/* 1961 */           this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
/* 1962 */           this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M;
/* 1963 */           this.field_70161_v = this.field_70121_D.field_72339_c + (double)(this.field_70130_N / 2.0F);
/*      */           break;
/*      */         case westTOeast_XP:
/* 1966 */           this.field_70165_t = this.field_70121_D.field_72336_d - (double)(this.field_70130_N / 2.0F);
/* 1967 */           this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M;
/* 1968 */           this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
/*      */           break;
/*      */         case eastTOwest_XN:
/* 1971 */           this.field_70165_t = this.field_70121_D.field_72340_a + (double)(this.field_70130_N / 2.0F);
/* 1972 */           this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M;
/* 1973 */           this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
/*      */           break;
/*      */         case downTOup_YP:
/* 1976 */           this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
/* 1977 */           this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M;
/* 1978 */           this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
/*      */           break;
/*      */         default:
/* 1981 */           this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
/* 1982 */           this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M - (double)this.field_70139_V;
/* 1983 */           this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
/*      */           break;
/*      */       } 
/* 1990 */       this.field_70123_F = (savedXMove != argXMove || savedZMove != argZMove);
/* 1991 */       this.field_70124_G = (savedYMove != argYMove);
/* 1992 */       this.field_70132_H = (this.field_70123_F || this.field_70124_G);
/* 1995 */       switch (gDir) {
/*      */         case southTOnorth_ZN:
/* 1997 */           this.field_70122_E = (savedZMove != argZMove && savedZMove < 0.0D);
/* 1998 */           func_70064_a(argZMove, this.field_70122_E);
/*      */           break;
/*      */         case northTOsouth_ZP:
/* 2001 */           this.field_70122_E = (savedZMove != argZMove && savedZMove > 0.0D);
/* 2002 */           func_70064_a(-argZMove, this.field_70122_E);
/*      */           break;
/*      */         case westTOeast_XP:
/* 2005 */           this.field_70122_E = (savedXMove != argXMove && savedXMove > 0.0D);
/* 2006 */           func_70064_a(-argXMove, this.field_70122_E);
/*      */           break;
/*      */         case eastTOwest_XN:
/* 2009 */           this.field_70122_E = (savedXMove != argXMove && savedXMove < 0.0D);
/* 2010 */           func_70064_a(argXMove, this.field_70122_E);
/*      */           break;
/*      */         case downTOup_YP:
/* 2013 */           this.field_70122_E = (savedYMove != argYMove && savedYMove > 0.0D);
/* 2014 */           func_70064_a(-argYMove, this.field_70122_E);
/*      */           break;
/*      */         default:
/* 2017 */           this.field_70122_E = (savedYMove != argYMove && savedYMove < 0.0D);
/* 2018 */           func_70064_a(argYMove, this.field_70122_E);
/*      */           break;
/*      */       } 
/* 2028 */       if (savedXMove != argXMove)
/* 2030 */         this.field_70159_w = 0.0D; 
/* 2033 */       if (savedYMove != argYMove)
/* 2035 */         this.field_70181_x = 0.0D; 
/* 2038 */       if (savedZMove != argZMove)
/* 2040 */         this.field_70179_y = 0.0D; 
/* 2043 */       double mvdXFrmPsNow = this.field_70165_t - savedPosX;
/* 2044 */       double mvdYFrmPsNow = this.field_70163_u - savedPosY;
/* 2045 */       double mvdZFrmPsNow = this.field_70161_v - savedPosZ;
/* 2047 */       if (func_70041_e_() && !isSneakingPlayer && this.field_70154_o == null) {
/*      */         int blockOnX, blockOnY, blockOnZ;
/*      */         Block blockOn;
/* 2054 */         if (isAbnormalGravity) {
/* 2056 */           double roatCenterX = this.field_70165_t;
/* 2057 */           double roatCenterY = this.field_70163_u - (double)this.field_70129_M + (double)(this.field_70130_N / 2.0F);
/* 2058 */           double roatCenterZ = this.field_70161_v;
/* 2059 */           switch (gDir) {
/*      */             case southTOnorth_ZN:
/* 2061 */               blockOnX = MathHelper.func_76128_c(roatCenterX);
/* 2062 */               blockOnY = MathHelper.func_76128_c(roatCenterY);
/* 2063 */               blockOnZ = MathHelper.func_76128_c(roatCenterZ - 1.0D);
/*      */               break;
/*      */             case northTOsouth_ZP:
/* 2066 */               blockOnX = MathHelper.func_76128_c(roatCenterX);
/* 2067 */               blockOnY = MathHelper.func_76128_c(roatCenterY);
/* 2068 */               blockOnZ = MathHelper.func_76128_c(roatCenterZ + 1.0D);
/*      */               break;
/*      */             case westTOeast_XP:
/* 2071 */               blockOnX = MathHelper.func_76128_c(roatCenterX + 1.0D);
/* 2072 */               blockOnY = MathHelper.func_76128_c(roatCenterY);
/* 2073 */               blockOnZ = MathHelper.func_76128_c(roatCenterZ);
/*      */               break;
/*      */             case eastTOwest_XN:
/* 2076 */               blockOnX = MathHelper.func_76128_c(roatCenterX - 1.0D);
/* 2077 */               blockOnY = MathHelper.func_76128_c(roatCenterY);
/* 2078 */               blockOnZ = MathHelper.func_76128_c(roatCenterZ);
/*      */               break;
/*      */             case downTOup_YP:
/* 2081 */               blockOnX = MathHelper.func_76128_c(roatCenterX);
/* 2082 */               blockOnY = MathHelper.func_76128_c(roatCenterY + 1.5D);
/* 2083 */               blockOnZ = MathHelper.func_76128_c(roatCenterZ);
/*      */               break;
/*      */             default:
/*      */               return;
/*      */           } 
/* 2089 */           blockOn = this.field_70170_p.func_147439_a(blockOnX, blockOnY, blockOnZ);
/*      */         } else {
/* 2092 */           blockOnX = MathHelper.func_76128_c(this.field_70165_t);
/* 2093 */           blockOnY = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D - (double)this.field_70129_M);
/* 2094 */           blockOnZ = MathHelper.func_76128_c(this.field_70161_v);
/* 2095 */           blockOn = this.field_70170_p.func_147439_a(blockOnX, blockOnY, blockOnZ);
/* 2097 */           if (blockOn == Blocks.field_150350_a) {
/* 2099 */             int k1 = this.field_70170_p.func_147439_a(blockOnX, blockOnY - 1, blockOnZ).func_149645_b();
/* 2101 */             if (k1 == 11 || k1 == 32 || k1 == 21)
/* 2103 */               blockOn = this.field_70170_p.func_147439_a(blockOnX, blockOnY - 1, blockOnZ); 
/*      */           } 
/*      */         } 
/* 2123 */         if (blockOn != Blocks.field_150468_ap && (isNormalGravity || gDir == GravityDirection.downTOup_YP))
/* 2126 */           mvdYFrmPsNow = 0.0D; 
/* 2130 */         this.field_70140_Q = (float)((double)this.field_70140_Q + (double)MathHelper.func_76133_a(mvdXFrmPsNow * mvdXFrmPsNow + mvdYFrmPsNow * mvdYFrmPsNow + mvdZFrmPsNow * mvdZFrmPsNow) * 0.6D);
/* 2136 */         this.field_82151_R = (float)((double)this.field_82151_R + (double)MathHelper.func_76133_a(mvdXFrmPsNow * mvdXFrmPsNow + mvdYFrmPsNow * mvdYFrmPsNow + mvdZFrmPsNow * mvdZFrmPsNow) * 0.6D);
/* 2139 */         if (this.field_82151_R > (float)this.nextStepDistancePrivate && blockOn != null && blockOn != Blocks.field_150350_a) {
/* 2141 */           this.nextStepDistancePrivate = (int)this.field_82151_R + 1;
/* 2143 */           if (func_70090_H()) {
/* 2145 */             float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w * 0.20000000298023224D + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y * 0.20000000298023224D) * 0.35F;
/* 2147 */             if (f > 1.0F)
/* 2149 */               f = 1.0F; 
/* 2152 */             func_85030_a("liquid.swim", f, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F);
/*      */           } 
/* 2155 */           func_145780_a(blockOnX, blockOnY, blockOnZ, blockOn);
/* 2156 */           blockOn.func_149724_b(this.field_70170_p, blockOnX, blockOnY, blockOnZ, (Entity)this);
/*      */         } 
/*      */       } 
/*      */       try {
/* 2163 */         func_145775_I();
/* 2165 */       } catch (Throwable throwable) {
/* 2167 */         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Checking entity tile collision");
/* 2168 */         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity being checked for collision");
/* 2169 */         func_85029_a(crashreportcategory);
/* 2170 */         throw new ReportedException(crashreport);
/*      */       } 
/* 2173 */       boolean flag2 = func_70026_G();
/* 2177 */       if (SMCoreReflectionHelper.field_fire == null)
/* 2178 */         SMCoreReflectionHelper.initFiledAccessFire(); 
/*      */       try {
/* 2181 */         int fireVal = SMCoreReflectionHelper.field_fire.getInt(this);
/* 2183 */         if (this.field_70170_p.func_147470_e(this.field_70121_D.func_72331_e(0.001D, 0.001D, 0.001D))) {
/* 2185 */           func_70081_e(1);
/* 2187 */           if (!flag2) {
/* 2190 */             SMCoreReflectionHelper.field_fire.setInt(this, ++fireVal);
/* 2192 */             if (fireVal == 0)
/* 2194 */               func_70015_d(8); 
/*      */           } 
/* 2198 */         } else if (fireVal <= 0) {
/* 2201 */           SMCoreReflectionHelper.field_fire.setInt(this, fireVal = -this.field_70174_ab);
/*      */         } 
/* 2204 */         if (flag2 && fireVal > 0) {
/* 2206 */           func_85030_a("random.fizz", 0.7F, 1.6F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F);
/* 2208 */           SMCoreReflectionHelper.field_fire.setInt(this, fireVal = -this.field_70174_ab);
/*      */         } 
/* 2210 */       } catch (IllegalAccessException elgaEx) {
/* 2211 */         elgaEx.printStackTrace();
/*      */       } 
/* 2238 */       this.field_70170_p.field_72984_F.func_76319_b();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean func_70617_f_() {
/*      */     int x, y, z;
/* 2245 */     double widthHalf = (double)(this.field_70130_N / 2.0F);
/* 2246 */     double padding = 0.01D;
/* 2247 */     AxisAlignedBB bb = this.field_70121_D;
/* 2248 */     switch (ExtendedPropertyGravity.getGravityDirection((Entity)this)) {
/*      */       case southTOnorth_ZN:
/* 2250 */         x = MathHelper.func_76128_c(bb.field_72340_a + widthHalf);
/* 2251 */         y = MathHelper.func_76128_c(bb.field_72338_b + widthHalf);
/* 2252 */         z = MathHelper.func_76128_c(bb.field_72339_c + padding);
/*      */         break;
/*      */       case northTOsouth_ZP:
/* 2255 */         x = MathHelper.func_76128_c(bb.field_72340_a + widthHalf);
/* 2256 */         y = MathHelper.func_76128_c(bb.field_72338_b + widthHalf);
/* 2257 */         z = MathHelper.func_76128_c(bb.field_72334_f - padding);
/*      */         break;
/*      */       case westTOeast_XP:
/* 2260 */         x = MathHelper.func_76128_c(bb.field_72336_d - padding);
/* 2261 */         y = MathHelper.func_76128_c(bb.field_72338_b + widthHalf);
/* 2262 */         z = MathHelper.func_76128_c(bb.field_72339_c + widthHalf);
/*      */         break;
/*      */       case eastTOwest_XN:
/* 2265 */         x = MathHelper.func_76128_c(bb.field_72340_a + padding);
/* 2266 */         y = MathHelper.func_76128_c(bb.field_72338_b + widthHalf);
/* 2267 */         z = MathHelper.func_76128_c(bb.field_72339_c + widthHalf);
/*      */         break;
/*      */       case downTOup_YP:
/* 2270 */         x = MathHelper.func_76128_c(this.field_70165_t);
/* 2271 */         y = MathHelper.func_76128_c(bb.field_72337_e - padding);
/* 2272 */         z = MathHelper.func_76128_c(this.field_70161_v);
/*      */         break;
/*      */       default:
/* 2275 */         x = MathHelper.func_76128_c(this.field_70165_t);
/* 2276 */         y = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 2277 */         z = MathHelper.func_76128_c(this.field_70161_v);
/* 2278 */         block = this.field_70170_p.func_147439_a(x, y, z);
/* 2279 */         return ForgeHooks.isLivingOnLadder(block, this.field_70170_p, x, y, z, this);
/*      */     } 
/* 2281 */     Block block = this.field_70170_p.func_147439_a(x, y, z);
/* 2282 */     return (block != null && block.isLadder((IBlockAccess)this.field_70170_p, x, y, z, this));
/*      */   }
/*      */   
/*      */   public void func_70060_a(float par1, float par2, float par3) {
/* 2307 */     float f3 = par1 * par1 + par2 * par2;
/* 2309 */     if (f3 >= 1.0E-4F) {
/*      */       float yawFront, yawSholder;
/* 2311 */       f3 = MathHelper.func_76129_c(f3);
/* 2313 */       if (f3 < 1.0F)
/* 2315 */         f3 = 1.0F; 
/* 2318 */       f3 = par3 / f3;
/* 2319 */       par1 *= f3;
/* 2320 */       par2 *= f3;
/* 2325 */       switch (ExtendedPropertyGravity.getGravityDirection((Entity)this)) {
/*      */         case southTOnorth_ZN:
/* 2327 */           par1 = -par1;
/* 2328 */           yawFront = MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F);
/* 2329 */           yawSholder = -MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F);
/* 2330 */           this.field_70159_w += (double)(par1 * yawSholder - par2 * yawFront);
/* 2331 */           this.field_70181_x += (double)(par2 * yawSholder + par1 * yawFront);
/*      */           break;
/*      */         case northTOsouth_ZP:
/* 2334 */           yawFront = MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F);
/* 2335 */           yawSholder = MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F);
/* 2336 */           this.field_70159_w += (double)(par1 * yawSholder - par2 * yawFront);
/* 2337 */           this.field_70181_x += (double)(par2 * yawSholder + par1 * yawFront);
/*      */           break;
/*      */         case westTOeast_XP:
/* 2340 */           yawFront = MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F);
/* 2341 */           yawSholder = MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F);
/* 2342 */           this.field_70181_x += (double)(par1 * yawSholder - par2 * yawFront);
/* 2343 */           this.field_70179_y += (double)(par2 * yawSholder + par1 * yawFront);
/*      */           break;
/*      */         case eastTOwest_XN:
/* 2346 */           par1 = -par1;
/* 2347 */           yawFront = -MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F);
/* 2348 */           yawSholder = MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F);
/* 2349 */           this.field_70181_x += (double)(par1 * yawSholder - par2 * yawFront);
/* 2350 */           this.field_70179_y += (double)(par2 * yawSholder + par1 * yawFront);
/*      */           break;
/*      */         case downTOup_YP:
/* 2353 */           par1 = -par1;
/* 2354 */           yawFront = MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F);
/* 2355 */           yawSholder = -MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F);
/* 2356 */           this.field_70159_w += (double)(par1 * yawSholder - par2 * yawFront);
/* 2357 */           this.field_70179_y += (double)(par2 * yawSholder + par1 * yawFront);
/*      */           break;
/*      */         default:
/* 2360 */           yawFront = MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F);
/* 2361 */           yawSholder = MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F);
/* 2362 */           this.field_70159_w += (double)(par1 * yawSholder - par2 * yawFront);
/* 2363 */           this.field_70179_y += (double)(par2 * yawSholder + par1 * yawFront);
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void func_70107_b(double argX, double argY, double argZ) {
/* 2378 */     this.field_70165_t = argX;
/* 2379 */     this.field_70163_u = argY;
/* 2380 */     this.field_70161_v = argZ;
/* 2381 */     float f = this.field_70130_N / 2.0F;
/* 2382 */     float f1 = this.field_70131_O;
/* 2384 */     this.field_70121_D.func_72324_b(argX - (double)f, argY - (double)this.field_70129_M + (double)this.field_70139_V, argZ - (double)f, argX + (double)f, argY - (double)this.field_70129_M + (double)this.field_70139_V + (double)f1, argZ + (double)f);
/*      */     try {
/* 2391 */       if (this.field_70170_p.field_72995_K) {
/* 2392 */         fixPositonAndBBClient(argX, argY, argZ);
/*      */       } else {
/* 2394 */         fixPositonAndBBServer(argX, argY, argZ);
/*      */       } 
/* 2411 */     } catch (Exception ex) {}
/*      */   }
/*      */   
/*      */   public void fixPositonAndBBClient(double argX, double argY, double argZ) {
/* 2417 */     double height = (double)this.field_70131_O;
/* 2418 */     double widthHalf = (double)(this.field_70130_N / 2.0F);
/* 2420 */     GravityDirection gDir = ExtendedPropertyGravity.getGravityDirection((Entity)this);
/* 2421 */     switch (gDir) {
/*      */       case upTOdown_YN:
/*      */         break;
/*      */       case downTOup_YP:
/* 2425 */         if (SMCoreModContainer.proxy.isOtherPlayer((EntityPlayer)this))
/*      */           return; 
/*      */       default:
/* 2431 */         gDir.rotateAABBAt(this.field_70121_D, this.field_70121_D.field_72340_a + widthHalf, this.field_70121_D.field_72338_b + widthHalf, this.field_70121_D.field_72339_c + widthHalf);
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void fixPositonAndBBServer(double argX, double argY, double par5) {
/* 2441 */     double height = (double)this.field_70131_O;
/* 2442 */     double widthHalf = (double)(this.field_70130_N / 2.0F);
/* 2444 */     GravityDirection gDir = ExtendedPropertyGravity.getGravityDirection((Entity)this);
/* 2445 */     switch (gDir) {
/*      */       case upTOdown_YN:
/*      */         break;
/*      */       case downTOup_YP:
/* 2449 */         this.field_70121_D.field_72338_b = argY;
/* 2450 */         this.field_70121_D.field_72337_e = argY + height;
/*      */         break;
/*      */       default:
/* 2453 */         gDir.rotateAABBAt(this.field_70121_D, this.field_70121_D.field_72340_a + widthHalf, this.field_70121_D.field_72338_b + widthHalf, this.field_70121_D.field_72339_c + widthHalf);
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected float func_110146_f(float par1, float par2) {
/* 2463 */     float f2 = MathHelper.func_76142_g(par1 - this.field_70761_aq);
/* 2464 */     this.field_70761_aq += f2 * 0.3F;
/* 2465 */     float f3 = MathHelper.func_76142_g(this.field_70177_z - this.field_70761_aq);
/* 2466 */     boolean flag = (f3 < -90.0F || f3 >= 90.0F);
/* 2468 */     if (f3 < -75.0F)
/* 2470 */       f3 = -75.0F; 
/* 2473 */     if (f3 >= 75.0F)
/* 2475 */       f3 = 75.0F; 
/* 2477 */     if (ExtendedPropertyGravity.isEntityAbnormalGravity((Entity)this)) {
/* 2478 */       this.field_70761_aq = this.field_70177_z;
/*      */     } else {
/* 2480 */       this.field_70761_aq = this.field_70177_z - f3;
/* 2482 */       if (f3 * f3 > 2500.0F)
/* 2484 */         this.field_70761_aq += f3 * 0.2F; 
/*      */     } 
/* 2488 */     if (flag)
/* 2490 */       par2 *= -1.0F; 
/* 2493 */     return par2;
/*      */   }
/*      */   
/*      */   public void setSizeProxy(float par1, float par2) {
/* 2496 */     func_70105_a(par1, par2);
/*      */   }
/*      */   
/*      */   private final List getCollidingBoundingBoxesPrivate(List bbList, World world, Entity p_72945_1_, AxisAlignedBB p_72945_2_, GravityDirection gDir) {
/* 2505 */     bbList.clear();
/* 2506 */     int xMin = MathHelper.func_76128_c(p_72945_2_.field_72340_a);
/* 2507 */     int xMax = MathHelper.func_76128_c(p_72945_2_.field_72336_d + 1.0D);
/* 2508 */     int yMin = MathHelper.func_76128_c(p_72945_2_.field_72338_b);
/* 2509 */     int yMax = MathHelper.func_76128_c(p_72945_2_.field_72337_e + 1.0D);
/* 2510 */     int zMin = MathHelper.func_76128_c(p_72945_2_.field_72339_c);
/* 2511 */     int zMax = MathHelper.func_76128_c(p_72945_2_.field_72334_f + 1.0D);
/* 2513 */     xMin += (gDir.collideCheckExpandX < 0) ? gDir.collideCheckExpandX : 0;
/* 2514 */     yMin += (gDir.collideCheckExpandY < 0) ? gDir.collideCheckExpandY : 0;
/* 2515 */     zMin += (gDir.collideCheckExpandZ < 0) ? gDir.collideCheckExpandZ : 0;
/* 2517 */     xMax += (gDir.collideCheckExpandX > 0) ? gDir.collideCheckExpandX : 0;
/* 2518 */     yMax += (gDir.collideCheckExpandY > 0) ? gDir.collideCheckExpandY : 0;
/* 2519 */     zMax += (gDir.collideCheckExpandZ > 0) ? gDir.collideCheckExpandZ : 0;
/* 2522 */     for (int k1 = xMin; k1 < xMax; k1++) {
/* 2524 */       for (int l1 = zMin; l1 < zMax; l1++) {
/* 2526 */         if (world.func_72899_e(k1, 64, l1))
/* 2528 */           for (int i2 = yMin; i2 < yMax; i2++) {
/*      */             Block block;
/* 2532 */             if (k1 >= -30000000 && k1 < 30000000 && l1 >= -30000000 && l1 < 30000000) {
/* 2534 */               block = world.func_147439_a(k1, i2, l1);
/*      */             } else {
/* 2538 */               block = Blocks.field_150348_b;
/*      */             } 
/* 2541 */             block.func_149743_a(world, k1, i2, l1, p_72945_2_, bbList, p_72945_1_);
/*      */           }  
/*      */       } 
/*      */     } 
/* 2547 */     double d0 = 0.25D;
/* 2548 */     List<Entity> list = world.func_72839_b(p_72945_1_, p_72945_2_.func_72314_b(d0, d0, d0));
/* 2550 */     for (int j2 = 0; j2 < list.size(); j2++) {
/* 2552 */       AxisAlignedBB axisalignedbb1 = list.get(j2).func_70046_E();
/* 2554 */       if (axisalignedbb1 != null && axisalignedbb1.func_72326_a(p_72945_2_))
/* 2556 */         bbList.add(axisalignedbb1); 
/* 2559 */       axisalignedbb1 = p_72945_1_.func_70114_g(list.get(j2));
/* 2561 */       if (axisalignedbb1 != null && axisalignedbb1.func_72326_a(p_72945_2_))
/* 2563 */         bbList.add(axisalignedbb1); 
/*      */     } 
/* 2567 */     return bbList;
/*      */   }
/*      */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */