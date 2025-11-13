/*     */ package jp.mc.ancientred.starminer.basics.tileentity;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockChestEx;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.ContainerChest;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryLargeChest;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ public class TileEntityChestEx extends TileEntity implements IInventory {
/*  25 */   private ItemStack[] chestContents = new ItemStack[36];
/*     */   
/*     */   public float lidAngle;
/*     */   
/*     */   public float prevLidAngle;
/*     */   
/*     */   public int numPlayersUsing;
/*     */   
/*     */   private int ticksSinceSync;
/*     */   
/*     */   private int cachedChestType;
/*     */   
/*     */   private String customName;
/*     */   
/*     */   private static final String __OBFID = "CL_00000346";
/*     */   
/*  39 */   public static int IS_adjacentChestZNeg = 0;
/*     */   
/*  40 */   public static int IS_adjacentChestXPos = 1;
/*     */   
/*  41 */   public static int IS_adjacentChestXNeg = 2;
/*     */   
/*  42 */   public static int IS_adjacentChestZPos = 3;
/*     */   
/*  43 */   public static int IS_adjacentChestNone = 4;
/*     */   
/*     */   public int getAdjacentChestTo() {
/*  46 */     GravityDirection direOpp = GravityDirection.turnWayForNormal(this.gravityDirection);
/*  47 */     direOpp.rotateXYZAt(this.conv, this.relatedBlockX, this.relatedBlockY, this.relatedBlockZ, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  48 */     int xDiff = this.conv[0] - this.field_145851_c;
/*  49 */     int yDiff = this.conv[1] - this.field_145848_d;
/*  50 */     int zDiff = this.conv[2] - this.field_145849_e;
/*  52 */     if (xDiff < 0)
/*  53 */       return IS_adjacentChestXNeg; 
/*  54 */     if (xDiff > 0)
/*  55 */       return IS_adjacentChestXPos; 
/*  56 */     if (zDiff < 0)
/*  57 */       return IS_adjacentChestZNeg; 
/*  58 */     if (zDiff > 0)
/*  59 */       return IS_adjacentChestZPos; 
/*  61 */     return IS_adjacentChestNone;
/*     */   }
/*     */   
/*     */   public TileEntityChestEx() {
/*  67 */     this.cachedChestType = -1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public TileEntityChestEx(int p_i2350_1_) {
/*  73 */     this.cachedChestType = p_i2350_1_;
/*     */   }
/*     */   
/*     */   public int func_70302_i_() {
/*  81 */     return 27;
/*     */   }
/*     */   
/*     */   public ItemStack func_70301_a(int p_70301_1_) {
/*  89 */     return this.chestContents[p_70301_1_];
/*     */   }
/*     */   
/*     */   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
/*  98 */     if (this.chestContents[p_70298_1_] != null) {
/* 102 */       if ((this.chestContents[p_70298_1_]).field_77994_a <= p_70298_2_) {
/* 104 */         ItemStack itemStack = this.chestContents[p_70298_1_];
/* 105 */         this.chestContents[p_70298_1_] = null;
/* 106 */         func_70296_d();
/* 107 */         return itemStack;
/*     */       } 
/* 111 */       ItemStack itemstack = this.chestContents[p_70298_1_].func_77979_a(p_70298_2_);
/* 113 */       if ((this.chestContents[p_70298_1_]).field_77994_a == 0)
/* 115 */         this.chestContents[p_70298_1_] = null; 
/* 118 */       func_70296_d();
/* 119 */       return itemstack;
/*     */     } 
/* 124 */     return null;
/*     */   }
/*     */   
/*     */   public ItemStack func_70304_b(int p_70304_1_) {
/* 134 */     if (this.chestContents[p_70304_1_] != null) {
/* 136 */       ItemStack itemstack = this.chestContents[p_70304_1_];
/* 137 */       this.chestContents[p_70304_1_] = null;
/* 138 */       return itemstack;
/*     */     } 
/* 142 */     return null;
/*     */   }
/*     */   
/*     */   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
/* 151 */     this.chestContents[p_70299_1_] = p_70299_2_;
/* 153 */     if (p_70299_2_ != null && p_70299_2_.field_77994_a > func_70297_j_())
/* 155 */       p_70299_2_.field_77994_a = func_70297_j_(); 
/* 158 */     func_70296_d();
/*     */   }
/*     */   
/*     */   public String func_145825_b() {
/* 166 */     return func_145818_k_() ? this.customName : "container.chest";
/*     */   }
/*     */   
/*     */   public boolean func_145818_k_() {
/* 174 */     return (this.customName != null && this.customName.length() > 0);
/*     */   }
/*     */   
/*     */   public void func_145976_a(String p_145976_1_) {
/* 179 */     this.customName = p_145976_1_;
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound p_145839_1_) {
/* 184 */     super.func_145839_a(p_145839_1_);
/* 185 */     NBTTagList nbttaglist = p_145839_1_.func_150295_c("Items", 10);
/* 186 */     this.chestContents = new ItemStack[func_70302_i_()];
/* 188 */     if (p_145839_1_.func_150297_b("CustomName", 8))
/* 190 */       this.customName = p_145839_1_.func_74779_i("CustomName"); 
/* 193 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/* 195 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 196 */       int j = nbttagcompound1.func_74771_c("Slot") & 0xFF;
/* 198 */       if (j >= 0 && j < this.chestContents.length)
/* 200 */         this.chestContents[j] = ItemStack.func_77949_a(nbttagcompound1); 
/*     */     } 
/* 203 */     readOriginalNBTData(p_145839_1_);
/*     */   }
/*     */   
/*     */   public void readOriginalNBTData(NBTTagCompound p_145839_1_) {
/* 206 */     this.gravityDirection = GravityDirection.values()[p_145839_1_.func_74762_e("gDir")];
/* 207 */     this.relatedBlockX = p_145839_1_.func_74762_e("relatedBlockX");
/* 208 */     this.relatedBlockY = p_145839_1_.func_74762_e("relatedBlockY");
/* 209 */     this.relatedBlockZ = p_145839_1_.func_74762_e("relatedBlockZ");
/* 210 */     this.isSubBlock = p_145839_1_.func_74767_n("isSubBlock");
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound p_145841_1_) {
/* 215 */     super.func_145841_b(p_145841_1_);
/* 216 */     NBTTagList nbttaglist = new NBTTagList();
/* 218 */     for (int i = 0; i < this.chestContents.length; i++) {
/* 220 */       if (this.chestContents[i] != null) {
/* 222 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 223 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 224 */         this.chestContents[i].func_77955_b(nbttagcompound1);
/* 225 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 229 */     p_145841_1_.func_74782_a("Items", (NBTBase)nbttaglist);
/* 231 */     if (func_145818_k_())
/* 233 */       p_145841_1_.func_74778_a("CustomName", this.customName); 
/* 236 */     writeOriginalNBTData(p_145841_1_);
/*     */   }
/*     */   
/*     */   public void writeOriginalNBTData(NBTTagCompound p_145841_1_) {
/* 239 */     p_145841_1_.func_74768_a("gDir", this.gravityDirection.ordinal());
/* 240 */     p_145841_1_.func_74768_a("relatedBlockX", this.relatedBlockX);
/* 241 */     p_145841_1_.func_74768_a("relatedBlockY", this.relatedBlockY);
/* 242 */     p_145841_1_.func_74768_a("relatedBlockZ", this.relatedBlockZ);
/* 243 */     p_145841_1_.func_74757_a("isSubBlock", this.isSubBlock);
/*     */   }
/*     */   
/*     */   public int func_70297_j_() {
/* 251 */     return 64;
/*     */   }
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer p_70300_1_) {
/* 259 */     return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : ((p_70300_1_.func_70092_e((double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.5D, (double)this.field_145849_e + 0.5D) <= 64.0D));
/*     */   }
/*     */   
/*     */   public void func_145845_h() {
/* 264 */     super.func_145845_h();
/* 266 */     int adjacentChestInt = getAdjacentChestTo();
/* 267 */     boolean dontPlaySound = (adjacentChestInt == IS_adjacentChestZNeg || adjacentChestInt == IS_adjacentChestXNeg);
/* 269 */     this.ticksSinceSync++;
/* 272 */     if (!this.field_145850_b.field_72995_K && this.numPlayersUsing != 0 && (this.ticksSinceSync + this.field_145851_c + this.field_145848_d + this.field_145849_e) % 200 == 0) {
/* 274 */       this.numPlayersUsing = 0;
/* 275 */       float f1 = 5.0F;
/* 276 */       List list = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)((float)this.field_145851_c - f1), (double)((float)this.field_145848_d - f1), (double)((float)this.field_145849_e - f1), (double)((float)(this.field_145851_c + 1) + f1), (double)((float)(this.field_145848_d + 1) + f1), (double)((float)(this.field_145849_e + 1) + f1)));
/* 277 */       Iterator<EntityPlayer> iterator = list.iterator();
/* 279 */       while (iterator.hasNext()) {
/* 281 */         EntityPlayer entityplayer = iterator.next();
/* 283 */         if (entityplayer.field_71070_bA instanceof ContainerChest) {
/* 285 */           IInventory iinventory = ((ContainerChest)entityplayer.field_71070_bA).func_85151_d();
/* 287 */           if (iinventory == this || (iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).func_90010_a(this)))
/* 289 */             this.numPlayersUsing++; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 295 */     this.prevLidAngle = this.lidAngle;
/* 296 */     float f = 0.1F;
/* 299 */     if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F && !dontPlaySound)
/* 301 */       this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.5D, (double)this.field_145849_e + 0.5D, "random.chestopen", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F); 
/* 304 */     if ((this.numPlayersUsing == 0 && this.lidAngle > 0.0F) || (this.numPlayersUsing > 0 && this.lidAngle < 1.0F)) {
/* 306 */       float f1 = this.lidAngle;
/* 308 */       if (this.numPlayersUsing > 0) {
/* 310 */         this.lidAngle += f;
/*     */       } else {
/* 314 */         this.lidAngle -= f;
/*     */       } 
/* 317 */       if (this.lidAngle > 1.0F)
/* 319 */         this.lidAngle = 1.0F; 
/* 322 */       float f2 = 0.5F;
/* 324 */       if (this.lidAngle < f2 && f1 >= f2 && !dontPlaySound)
/* 326 */         this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.5D, (double)this.field_145849_e + 0.5D, "random.chestclosed", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F); 
/* 329 */       if (this.lidAngle < 0.0F)
/* 331 */         this.lidAngle = 0.0F; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean func_145842_c(int p_145842_1_, int p_145842_2_) {
/* 341 */     if (p_145842_1_ == 1) {
/* 343 */       this.numPlayersUsing = p_145842_2_;
/* 344 */       return true;
/*     */     } 
/* 348 */     return super.func_145842_c(p_145842_1_, p_145842_2_);
/*     */   }
/*     */   
/*     */   public void func_70295_k_() {
/* 354 */     if (this.numPlayersUsing < 0)
/* 356 */       this.numPlayersUsing = 0; 
/* 359 */     this.numPlayersUsing++;
/* 360 */     this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q(), 1, this.numPlayersUsing);
/* 361 */     this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q());
/* 362 */     this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, func_145838_q());
/*     */   }
/*     */   
/*     */   public void func_70305_f() {
/* 367 */     if (func_145838_q() instanceof BlockChestEx) {
/* 369 */       this.numPlayersUsing--;
/* 370 */       this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q(), 1, this.numPlayersUsing);
/* 371 */       this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q());
/* 372 */       this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, func_145838_q());
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
/* 381 */     return true;
/*     */   }
/*     */   
/*     */   public int func_145980_j() {
/* 386 */     if (this.cachedChestType == -1) {
/* 388 */       if (this.field_145850_b == null || !(func_145838_q() instanceof BlockChestEx))
/* 390 */         return 0; 
/* 393 */       this.cachedChestType = ((BlockChestEx)func_145838_q()).chestType;
/*     */     } 
/* 396 */     return this.cachedChestType;
/*     */   }
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/* 400 */     NBTTagCompound tag = pkt.func_148857_g();
/* 401 */     readOriginalNBTData(tag);
/*     */   }
/*     */   
/*     */   public Packet func_145844_m() {
/* 405 */     NBTTagCompound tag = new NBTTagCompound();
/* 406 */     writeOriginalNBTData(tag);
/* 407 */     return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145847_g, tag);
/*     */   }
/*     */   
/* 411 */   public boolean isSubBlock = false;
/*     */   
/*     */   public int relatedBlockX;
/*     */   
/*     */   public int relatedBlockY;
/*     */   
/*     */   public int relatedBlockZ;
/*     */   
/* 415 */   private GravityDirection gravityDirection = GravityDirection.upTOdown_YN;
/*     */   
/* 416 */   public int[] conv = new int[3];
/*     */   
/*     */   public GravityDirection getGravityDirection() {
/* 419 */     return this.gravityDirection;
/*     */   }
/*     */   
/*     */   public void setGravityDirection(GravityDirection gDir) {
/* 423 */     this.gravityDirection = gDir;
/*     */   }
/*     */   
/*     */   public boolean hasRelated() {
/* 427 */     return (this.relatedBlockX != this.field_145851_c || this.relatedBlockY != this.field_145848_d || this.relatedBlockZ != this.field_145849_e);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */