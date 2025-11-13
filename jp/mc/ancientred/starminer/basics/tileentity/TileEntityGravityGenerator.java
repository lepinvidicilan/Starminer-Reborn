/*     */ package jp.mc.ancientred.starminer.basics.tileentity;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import jp.mc.ancientred.starminer.api.Gravity;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.api.IAttractableTileEntity;
/*     */ import jp.mc.ancientred.starminer.basics.Config;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.packet.SMPacketSender;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ public class TileEntityGravityGenerator extends TileEntity implements IInventory, IAttractableTileEntity {
/*     */   private static final String GRAVITY_NBT_KEY = "stmn.gravRange";
/*     */   
/*     */   private static final String STARRAD_NBT_KEY = "stmn.starRad";
/*     */   
/*     */   private static final String TYPE_NBT_KEY = "stmn.type";
/*     */   
/*     */   private static final String WORKX_NBT_KEY = "stmn.wkX";
/*     */   
/*     */   private static final String WORKY_NBT_KEY = "stmn.wkY";
/*     */   
/*     */   private static final String WORKZ_NBT_KEY = "stmn.wkZ";
/*     */   
/*     */   public static final int GTYPE_SPHERE = 0;
/*     */   
/*     */   public static final int GTYPE_SQUARE = 1;
/*     */   
/*     */   public static final int GTYPE_XCYLINDER = 2;
/*     */   
/*     */   public static final int GTYPE_YCYLINDER = 3;
/*     */   
/*     */   public static final int GTYPE_ZCYLINDER = 4;
/*     */   
/*     */   public static final int TYPE_NUM = 5;
/*     */   
/*  51 */   public double gravityRange = 0.0D;
/*     */   
/*  53 */   public double starRad = 0.0D;
/*     */   
/*  55 */   public int type = 0;
/*     */   
/*  57 */   public short workStateX = 0;
/*     */   
/*  58 */   public short workStateY = 0;
/*     */   
/*  59 */   public short workStateZ = 0;
/*     */   
/*  60 */   public boolean workFast = false;
/*     */   
/*     */   public void func_145845_h() {
/*  66 */     if (!this.field_145850_b.field_72995_K) {
/*  67 */       long totalTime = this.field_145850_b.func_82737_E();
/*  68 */       if (totalTime % Config.attractCheckTick == 0L)
/*  69 */         addEffectsToPlayersOnServer(); 
/*  71 */       long workSpeed = this.workFast ? 5L : 40L;
/*  72 */       if (totalTime % workSpeed == 0L)
/*  73 */         doTerraformWork(); 
/*  75 */       if (totalTime % 100L == 0L)
/*  76 */         doGravitizeWork(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addEffectsToPlayersOnServer() {
/*  84 */     double centerX = (double)this.field_145851_c + 0.5D;
/*  85 */     double centerY = (double)this.field_145848_d + 0.5D;
/*  86 */     double centerZ = (double)this.field_145849_e + 0.5D;
/*  88 */     AxisAlignedBB axisalignedbb = AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(this.gravityRange, this.gravityRange, this.gravityRange);
/*  94 */     List list = this.field_145850_b.func_72872_a(EntityPlayer.class, axisalignedbb);
/*  95 */     Iterator<EntityPlayer> iterator = list.iterator();
/*  97 */     while (iterator.hasNext()) {
/*  98 */       EntityPlayer entityPlayer = iterator.next();
/* 101 */       if (entityPlayer.func_70608_bn())
/*     */         continue; 
/* 104 */       Gravity gravity = Gravity.getGravityProp((Entity)entityPlayer);
/* 106 */       if (gravity == null)
/*     */         continue; 
/* 109 */       if (inGravityRange((Entity)entityPlayer, gravity.gravityDirection, centerX, centerY, centerZ, this.gravityRange, this.type)) {
/* 117 */         if (!gravity.isAttracted()) {
/* 119 */           gravity.attractUpdateTickCount = 1;
/* 120 */           gravity.setAttractedBy(this);
/*     */           continue;
/*     */         } 
/* 121 */         if (gravity.isAttractedBy(this) && gravity.attractUpdateTickCount % 60 == 0)
/* 125 */           gravity.setAttractedBy(this); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean inGravityRange(Entity entity, GravityDirection gDirection, double attractCenterX, double attractCenterY, double attractCenterZ, double gravityRange, int type) {
/*     */     double playerPosY;
/* 144 */     switch (gDirection) {
/*     */       case downTOup_YP:
/* 146 */         playerPosY = entity.field_70163_u + (double)entity.field_70131_O - (double)(entity.field_70130_N / 2.0F);
/*     */         break;
/*     */       default:
/* 154 */         playerPosY = entity.field_70163_u - (double)(entity.field_70130_N / 2.0F);
/*     */         break;
/*     */     } 
/* 157 */     switch (type) {
/*     */       case 1:
/* 159 */         return checkAttractedRangeSquare(attractCenterX, attractCenterY, attractCenterZ, entity.field_70165_t, playerPosY, entity.field_70161_v, gravityRange);
/*     */       case 2:
/* 167 */         return checkAttractedRangeXbaseCylinder(attractCenterX, attractCenterY, attractCenterZ, entity.field_70165_t, playerPosY, entity.field_70161_v, gravityRange);
/*     */       case 3:
/* 175 */         return checkAttractedRangeYbaseCylinder(attractCenterX, attractCenterY, attractCenterZ, entity.field_70165_t, playerPosY, entity.field_70161_v, gravityRange);
/*     */       case 4:
/* 183 */         return checkAttractedRangeZbaseCylinder(attractCenterX, attractCenterY, attractCenterZ, entity.field_70165_t, playerPosY, entity.field_70161_v, gravityRange);
/*     */     } 
/* 191 */     return checkAttractedRangeSphere(attractCenterX, attractCenterY, attractCenterZ, entity.field_70165_t, playerPosY, entity.field_70161_v, gravityRange);
/*     */   }
/*     */   
/*     */   private static boolean checkAttractedRangeSphere(double centerX, double centerY, double centerZ, double playerX, double playerY, double playerZ, double argAttarctedRange) {
/* 206 */     double xRel = centerX - playerX;
/* 207 */     double zRel = centerZ - playerZ;
/* 208 */     double yRel = centerY - playerY;
/* 210 */     return (Math.sqrt(xRel * xRel + yRel * yRel + zRel * zRel) <= argAttarctedRange);
/*     */   }
/*     */   
/*     */   private static boolean checkAttractedRangeSquare(double centerX, double centerY, double centerZ, double playerX, double playerY, double playerZ, double argAttarctedRange) {
/* 216 */     return (playerX <= centerX + argAttarctedRange && playerX >= centerX - argAttarctedRange && playerY <= centerY + argAttarctedRange && playerY >= centerY - argAttarctedRange && playerZ <= centerZ + argAttarctedRange && playerZ >= centerZ - argAttarctedRange);
/*     */   }
/*     */   
/*     */   private static boolean checkAttractedRangeXbaseCylinder(double centerX, double centerY, double centerZ, double playerX, double playerY, double playerZ, double argAttarctedRange) {
/* 227 */     double yRel = centerY - playerY;
/* 228 */     double zRel = centerZ - playerZ;
/* 230 */     return (playerX <= centerX + argAttarctedRange && playerX >= centerX - argAttarctedRange && Math.sqrt(yRel * yRel + zRel * zRel) <= argAttarctedRange);
/*     */   }
/*     */   
/*     */   private static boolean checkAttractedRangeYbaseCylinder(double centerX, double centerY, double centerZ, double playerX, double playerY, double playerZ, double argAttarctedRange) {
/* 238 */     double xRel = centerX - playerX;
/* 239 */     double zRel = centerZ - playerZ;
/* 241 */     return (playerY <= centerY + argAttarctedRange && playerY >= centerY - argAttarctedRange && Math.sqrt(xRel * xRel + zRel * zRel) <= argAttarctedRange);
/*     */   }
/*     */   
/*     */   private static boolean checkAttractedRangeZbaseCylinder(double centerX, double centerY, double centerZ, double playerX, double playerY, double playerZ, double argAttarctedRange) {
/* 249 */     double xRel = centerX - playerX;
/* 250 */     double yRel = centerY - playerY;
/* 252 */     return (playerZ <= centerZ + argAttarctedRange && playerZ >= centerZ - argAttarctedRange && Math.sqrt(xRel * xRel + yRel * yRel) <= argAttarctedRange);
/*     */   }
/*     */   
/*     */   public boolean isStillInAttractedState(Entity entity) {
/* 261 */     return inGravityRange(entity, Gravity.getGravityDirection(entity), (double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.5D, (double)this.field_145849_e + 0.5D, this.gravityRange, this.type);
/*     */   }
/*     */   
/*     */   public GravityDirection getCurrentGravity(Entity entity) {
/*     */     GravityDirection gravityDirNew;
/* 272 */     double centerX = (double)this.field_145851_c + 0.5D;
/* 273 */     double centerY = (double)this.field_145848_d + 0.5D;
/* 274 */     double centerZ = (double)this.field_145849_e + 0.5D;
/* 275 */     double xRel = centerX - entity.field_70165_t;
/* 276 */     double zRel = centerZ - entity.field_70161_v;
/* 277 */     double yRel = centerY - (entity.field_70163_u - (double)(entity.field_70129_M - entity.field_70130_N / 2.0F));
/* 281 */     boolean reverse = isGravityReverse(entity, false);
/* 283 */     switch (this.type) {
/*     */       case 2:
/* 285 */         if (Math.abs(zRel) > Math.abs(yRel)) {
/* 287 */           if (zRel > 0.0D) {
/* 288 */             gravityDirNew = reverse ? GravityDirection.northTOsouth_ZP : GravityDirection.southTOnorth_ZN;
/*     */             break;
/*     */           } 
/* 290 */           gravityDirNew = reverse ? GravityDirection.southTOnorth_ZN : GravityDirection.northTOsouth_ZP;
/*     */           break;
/*     */         } 
/* 294 */         if (yRel > 0.0D) {
/* 295 */           gravityDirNew = reverse ? GravityDirection.downTOup_YP : GravityDirection.upTOdown_YN;
/*     */           break;
/*     */         } 
/* 297 */         gravityDirNew = reverse ? GravityDirection.upTOdown_YN : GravityDirection.downTOup_YP;
/*     */         break;
/*     */       case 3:
/* 302 */         if (Math.abs(xRel) > Math.abs(zRel)) {
/* 304 */           if (xRel > 0.0D) {
/* 305 */             gravityDirNew = reverse ? GravityDirection.westTOeast_XP : GravityDirection.eastTOwest_XN;
/*     */             break;
/*     */           } 
/* 307 */           gravityDirNew = reverse ? GravityDirection.eastTOwest_XN : GravityDirection.westTOeast_XP;
/*     */           break;
/*     */         } 
/* 311 */         if (zRel > 0.0D) {
/* 312 */           gravityDirNew = reverse ? GravityDirection.northTOsouth_ZP : GravityDirection.southTOnorth_ZN;
/*     */           break;
/*     */         } 
/* 314 */         gravityDirNew = reverse ? GravityDirection.southTOnorth_ZN : GravityDirection.northTOsouth_ZP;
/*     */         break;
/*     */       case 4:
/* 319 */         if (Math.abs(xRel) > Math.abs(yRel)) {
/* 321 */           if (xRel > 0.0D) {
/* 322 */             gravityDirNew = reverse ? GravityDirection.westTOeast_XP : GravityDirection.eastTOwest_XN;
/*     */             break;
/*     */           } 
/* 324 */           gravityDirNew = reverse ? GravityDirection.eastTOwest_XN : GravityDirection.westTOeast_XP;
/*     */           break;
/*     */         } 
/* 328 */         if (yRel > 0.0D) {
/* 329 */           gravityDirNew = reverse ? GravityDirection.downTOup_YP : GravityDirection.upTOdown_YN;
/*     */           break;
/*     */         } 
/* 331 */         gravityDirNew = reverse ? GravityDirection.upTOdown_YN : GravityDirection.downTOup_YP;
/*     */         break;
/*     */       default:
/* 337 */         if (Math.abs(xRel) > Math.abs(zRel) && Math.abs(xRel) > Math.abs(yRel)) {
/* 339 */           if (xRel > 0.0D) {
/* 340 */             gravityDirNew = !reverse ? GravityDirection.westTOeast_XP : GravityDirection.eastTOwest_XN;
/*     */             break;
/*     */           } 
/* 342 */           gravityDirNew = !reverse ? GravityDirection.eastTOwest_XN : GravityDirection.westTOeast_XP;
/*     */           break;
/*     */         } 
/* 344 */         if (Math.abs(zRel) >= Math.abs(xRel) && Math.abs(zRel) > Math.abs(yRel)) {
/* 346 */           if (zRel > 0.0D) {
/* 347 */             gravityDirNew = !reverse ? GravityDirection.northTOsouth_ZP : GravityDirection.southTOnorth_ZN;
/*     */             break;
/*     */           } 
/* 349 */           gravityDirNew = !reverse ? GravityDirection.southTOnorth_ZN : GravityDirection.northTOsouth_ZP;
/*     */           break;
/*     */         } 
/* 353 */         if (yRel > 0.0D) {
/* 354 */           gravityDirNew = !reverse ? GravityDirection.downTOup_YP : GravityDirection.upTOdown_YN;
/*     */           break;
/*     */         } 
/* 356 */         gravityDirNew = !reverse ? GravityDirection.upTOdown_YN : GravityDirection.downTOup_YP;
/*     */         break;
/*     */     } 
/* 360 */     return gravityDirNew;
/*     */   }
/*     */   
/*     */   public static boolean isGravityReverse(Entity entity, boolean setReverseOff) {
/* 367 */     if (entity instanceof EntityPlayer) {
/* 368 */       EntityPlayer player = (EntityPlayer)entity;
/* 370 */       for (int i = 0; i < 9; i++) {
/* 371 */         ItemStack itemStack = player.field_71071_by.field_70462_a[i];
/* 372 */         if (itemStack != null && itemStack.func_77973_b() == SMModContainer.GravityControllerItem) {
/* 373 */           if (itemStack.func_77942_o()) {
/* 374 */             NBTTagCompound tag = itemStack.func_77978_p();
/* 375 */             boolean gcon = tag.func_74767_n("stmn.g_reverse");
/* 376 */             if (gcon && setReverseOff)
/* 377 */               tag.func_74757_a("stmn.g_reverse", false); 
/* 379 */             return gcon;
/*     */           } 
/* 381 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 386 */     return false;
/*     */   }
/*     */   
/*     */   private void doTerraformWork() {
/* 395 */     int itemStackIndexToUse = getItemStackForTerraform();
/* 398 */     if (itemStackIndexToUse == -1) {
/* 399 */       this.workFast = false;
/*     */       return;
/*     */     } 
/* 404 */     if (this.type == 1) {
/* 405 */       this.workFast = false;
/*     */       return;
/*     */     } 
/* 410 */     if (this.workStateY < -((int)this.starRad) || this.workStateY + this.field_145848_d < 0) {
/* 412 */       this.workFast = false;
/*     */       return;
/*     */     } 
/* 417 */     this.workFast = true;
/* 420 */     if (calclulateNextWorkSpot())
/* 424 */       putBlock(itemStackIndexToUse); 
/*     */   }
/*     */   
/*     */   private void putBlock(int itemStackIndexToUse) {
/* 431 */     this.field_145850_b.func_147465_d(this.field_145851_c + this.workStateX, this.field_145848_d + this.workStateY, this.field_145849_e + this.workStateZ, Block.func_149634_a(this.gCoreItemStacks[itemStackIndexToUse].func_77973_b()), this.gCoreItemStacks[itemStackIndexToUse].func_77960_j(), 3);
/* 439 */     if (--(this.gCoreItemStacks[itemStackIndexToUse]).field_77994_a <= 0)
/* 440 */       this.gCoreItemStacks[itemStackIndexToUse] = null; 
/*     */   }
/*     */   
/*     */   public void resetWorkState() {
/* 447 */     int radPlsOne = (int)this.starRad + 1;
/* 448 */     this.workStateX = (short)-radPlsOne;
/* 449 */     this.workStateZ = (short)-radPlsOne;
/* 450 */     this.workStateY = (short)radPlsOne;
/* 451 */     if (this.workStateY + this.field_145848_d > 255)
/* 452 */       this.workStateY = (short)(255 - this.field_145848_d); 
/*     */   }
/*     */   
/*     */   private boolean calclulateNextWorkSpot() {
/* 459 */     int intRad = (int)this.starRad;
/* 461 */     int radPlsOne = intRad + 1;
/* 462 */     int radSub1 = intRad - 1;
/* 463 */     double radPow = (double)(intRad * intRad);
/* 464 */     double radSub1Pow = (double)(radSub1 * radSub1);
/* 469 */     int lookedCount = 0;
/* 472 */     this.workStateX = (short)-radPlsOne;
/* 474 */     this.workStateZ = (short)-radPlsOne;
/* 475 */     while ((this.workStateX = (short)(this.workStateX + 1)) <= radPlsOne || (this.workStateZ = (short)(this.workStateZ + 1)) <= radPlsOne || ((this.workStateY = (short)(this.workStateY - 1)) >= -radPlsOne && this.workStateY + this.field_145848_d >= 0)) {
/* 480 */       if (this.workStateX != 0 || this.workStateY != 0 || this.workStateZ != 0) {
/*     */         double distancePow, distanceX, distanceY, distanceZ;
/* 481 */         switch (this.type) {
/*     */           case 2:
/* 483 */             distanceY = (double)this.workStateY;
/* 484 */             distanceZ = (double)this.workStateZ;
/* 486 */             distancePow = distanceY * distanceY + distanceZ * distanceZ;
/* 487 */             if (distancePow < radPow && 
/* 488 */               distancePow >= radSub1Pow)
/* 489 */               return true; 
/*     */             break;
/*     */           case 3:
/* 494 */             distanceX = (double)this.workStateX;
/* 495 */             distanceZ = (double)this.workStateZ;
/* 497 */             distancePow = distanceX * distanceX + distanceZ * distanceZ;
/* 498 */             if (distancePow < radPow && 
/* 499 */               distancePow >= radSub1Pow)
/* 500 */               return true; 
/*     */             break;
/*     */           case 4:
/* 505 */             distanceX = (double)this.workStateX;
/* 506 */             distanceY = (double)this.workStateY;
/* 508 */             distancePow = distanceX * distanceX + distanceY * distanceY;
/* 509 */             if (distancePow < radPow && 
/* 510 */               distancePow >= radSub1Pow)
/* 511 */               return true; 
/*     */             break;
/*     */           default:
/* 516 */             distanceX = (double)this.workStateX;
/* 517 */             distanceY = (double)this.workStateY;
/* 518 */             distanceZ = (double)this.workStateZ;
/* 520 */             distancePow = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
/* 521 */             if (distancePow < radPow && 
/* 522 */               distancePow >= radSub1Pow)
/* 523 */               return true; 
/*     */             break;
/*     */         } 
/*     */       } 
/* 529 */       if (++lookedCount > 50)
/* 529 */         return false; 
/*     */     } 
/* 532 */     return false;
/*     */   }
/*     */   
/*     */   private int getItemStackForTerraform() {
/* 538 */     for (int i = 0; i < 27; i++) {
/* 539 */       if (this.gCoreItemStacks[i] != null) {
/* 540 */         Item item = this.gCoreItemStacks[i].func_77973_b();
/* 541 */         if (item != null) {
/* 542 */           Block block = Block.func_149634_a(item);
/* 543 */           if (block != null && block != Blocks.field_150350_a && AllowedBlockDictionary.isAllowed(block))
/* 546 */             if (!(this.field_145850_b.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider) || block != Blocks.field_150432_aD)
/* 551 */               return i;  
/*     */         } 
/*     */       } 
/*     */     } 
/* 556 */     return -1;
/*     */   }
/*     */   
/*     */   private void doGravitizeWork() {
/* 562 */     if (this.field_145850_b.field_73012_v.nextInt(24) == 0) {
/* 563 */       ItemStack itemStack = this.gCoreItemStacks[this.gCoreItemStacks.length - 1];
/* 564 */       if (itemStack != null) {
/* 565 */         Item newItem = null;
/* 566 */         Item item = itemStack.func_77973_b();
/* 567 */         if (item == Items.field_151014_N)
/* 568 */           newItem = SMModContainer.SeedGravizedItem; 
/* 570 */         if (item == Items.field_151172_bF)
/* 571 */           newItem = SMModContainer.CarrotGravizedItem; 
/* 573 */         if (item == Items.field_151174_bG)
/* 574 */           newItem = SMModContainer.PotatoGravizedItem; 
/* 576 */         Block block = Block.func_149634_a(item);
/* 577 */         if (block != null) {
/* 578 */           if (block == Blocks.field_150345_g)
/* 579 */             newItem = Item.func_150898_a(SMModContainer.SaplingGravitizedBlock); 
/* 581 */           if (block == Blocks.field_150327_N)
/* 582 */             newItem = Item.func_150898_a(SMModContainer.PlantYelGravitizedBlock); 
/* 584 */           if (block == Blocks.field_150328_O)
/* 585 */             newItem = Item.func_150898_a(SMModContainer.PlantRedGravitizedBlock); 
/*     */         } 
/* 588 */         if (newItem != null)
/* 589 */           this.gCoreItemStacks[this.gCoreItemStacks.length - 1] = new ItemStack(newItem, itemStack.field_77994_a, itemStack.func_77960_j()); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
/* 599 */     super.func_145839_a(par1NBTTagCompound);
/* 600 */     this.gravityRange = par1NBTTagCompound.func_74769_h("stmn.gravRange");
/* 601 */     this.type = par1NBTTagCompound.func_74762_e("stmn.type");
/* 602 */     if (par1NBTTagCompound.func_74764_b("stmn.starRad")) {
/* 603 */       this.starRad = par1NBTTagCompound.func_74769_h("stmn.starRad");
/*     */     } else {
/* 605 */       this.starRad = this.gravityRange - 6.0D;
/*     */     } 
/* 607 */     if (this.starRad < 0.0D)
/* 607 */       this.starRad = 0.0D; 
/* 608 */     if (par1NBTTagCompound.func_74764_b("stmn.wkX")) {
/* 609 */       this.workStateX = par1NBTTagCompound.func_74765_d("stmn.wkX");
/* 610 */       this.workStateY = par1NBTTagCompound.func_74765_d("stmn.wkY");
/* 611 */       this.workStateZ = par1NBTTagCompound.func_74765_d("stmn.wkZ");
/*     */     } else {
/* 613 */       resetWorkState();
/*     */     } 
/* 617 */     if (par1NBTTagCompound.func_74764_b("Items")) {
/* 618 */       NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("Items", 10);
/* 619 */       for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/* 621 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 622 */         byte b0 = nbttagcompound1.func_74771_c("Slot");
/* 624 */         if (b0 >= 0 && b0 < this.gCoreItemStacks.length)
/* 626 */           this.gCoreItemStacks[b0] = ItemStack.func_77949_a(nbttagcompound1); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
/* 636 */     super.func_145841_b(par1NBTTagCompound);
/* 637 */     par1NBTTagCompound.func_74780_a("stmn.gravRange", this.gravityRange);
/* 638 */     par1NBTTagCompound.func_74768_a("stmn.type", this.type);
/* 639 */     par1NBTTagCompound.func_74780_a("stmn.starRad", this.starRad);
/* 640 */     par1NBTTagCompound.func_74777_a("stmn.wkX", this.workStateX);
/* 641 */     par1NBTTagCompound.func_74777_a("stmn.wkY", this.workStateY);
/* 642 */     par1NBTTagCompound.func_74777_a("stmn.wkZ", this.workStateZ);
/* 644 */     NBTTagList nbttaglist = new NBTTagList();
/* 645 */     for (int i = 0; i < this.gCoreItemStacks.length; i++) {
/* 647 */       if (this.gCoreItemStacks[i] != null) {
/* 649 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 650 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 651 */         this.gCoreItemStacks[i].func_77955_b(nbttagcompound1);
/* 652 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 655 */     par1NBTTagCompound.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */   
/*     */   public Packet func_145844_m() {
/* 662 */     return SMPacketSender.createTEGcoreDescriptionPacket(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.gravityRange, this.starRad, this.type);
/*     */   }
/*     */   
/*     */   public void fixInValidRange() {
/* 674 */     if (this.gravityRange < 0.0D)
/* 675 */       this.gravityRange = 0.0D; 
/* 677 */     if (this.gravityRange > (double)Config.maxGravityRad)
/* 678 */       this.gravityRange = (double)Config.maxGravityRad; 
/* 681 */     if (this.starRad < 0.0D)
/* 682 */       this.starRad = 0.0D; 
/* 684 */     if (this.starRad > (double)Config.maxStarRad)
/* 685 */       this.starRad = (double)Config.maxStarRad; 
/*     */   }
/*     */   
/* 691 */   private ItemStack[] gCoreItemStacks = new ItemStack[28];
/*     */   
/*     */   public int func_70302_i_() {
/* 695 */     return this.gCoreItemStacks.length;
/*     */   }
/*     */   
/*     */   public ItemStack func_70301_a(int par1) {
/* 699 */     return this.gCoreItemStacks[par1];
/*     */   }
/*     */   
/*     */   public ItemStack func_70298_a(int par1, int par2) {
/* 703 */     if (this.gCoreItemStacks[par1] != null) {
/* 707 */       if ((this.gCoreItemStacks[par1]).field_77994_a <= par2) {
/* 709 */         ItemStack itemStack = this.gCoreItemStacks[par1];
/* 710 */         this.gCoreItemStacks[par1] = null;
/* 711 */         return itemStack;
/*     */       } 
/* 715 */       ItemStack itemstack = this.gCoreItemStacks[par1].func_77979_a(par2);
/* 717 */       if ((this.gCoreItemStacks[par1]).field_77994_a == 0)
/* 719 */         this.gCoreItemStacks[par1] = null; 
/* 722 */       return itemstack;
/*     */     } 
/* 727 */     return null;
/*     */   }
/*     */   
/*     */   public ItemStack func_70304_b(int par1) {
/* 732 */     if (this.gCoreItemStacks[par1] != null) {
/* 734 */       ItemStack itemstack = this.gCoreItemStacks[par1];
/* 735 */       this.gCoreItemStacks[par1] = null;
/* 736 */       return itemstack;
/*     */     } 
/* 740 */     return null;
/*     */   }
/*     */   
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack) {
/* 745 */     this.gCoreItemStacks[par1] = par2ItemStack;
/* 747 */     if (par2ItemStack != null && par2ItemStack.field_77994_a > func_70297_j_())
/* 749 */       par2ItemStack.field_77994_a = func_70297_j_(); 
/*     */   }
/*     */   
/*     */   public String func_145825_b() {
/* 755 */     return "";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_() {
/* 759 */     return true;
/*     */   }
/*     */   
/*     */   public int func_70297_j_() {
/* 763 */     return 64;
/*     */   }
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
/* 767 */     return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : ((par1EntityPlayer.func_70011_f((double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.5D, (double)this.field_145849_e + 0.5D) < 64.0D));
/*     */   }
/*     */   
/*     */   public void func_70295_k_() {}
/*     */   
/*     */   public void func_70305_f() {}
/*     */   
/*     */   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
/* 781 */     return true;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */