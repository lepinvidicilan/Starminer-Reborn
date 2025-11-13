/*     */ package jp.mc.ancientred.starminer.basics.block;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import jp.mc.ancientred.starminer.api.Gravity;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityChestEx;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryLargeChest;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockChestEx extends BlockContainer {
/*  37 */   private final Random rand = new Random();
/*     */   
/*     */   public final int chestType;
/*     */   
/*     */   private static final int CHESTMOUTHFACING_NORTH = 2;
/*     */   
/*     */   private static final int CHESTMOUTHFACING_EAST = 5;
/*     */   
/*     */   private static final int CHESTMOUTHFACING_SOUTH = 3;
/*     */   
/*     */   private static final int CHESTMOUTHFACING_WEST = 4;
/*     */   
/*     */   public BlockChestEx(int type) {
/*  42 */     super(Material.field_151575_d);
/*  43 */     this.chestType = type;
/*  44 */     func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c() {
/*  53 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d() {
/*  61 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b() {
/*  69 */     return 22;
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int parX, int parY, int parZ) {
/*  77 */     TileEntityChestEx te = getTileEntityChestEx(world, parX, parY, parZ);
/*  78 */     GravityDirection gDir = te.getGravityDirection();
/*  79 */     GravityDirection dirOpposite = GravityDirection.turnWayForNormal(gDir);
/*  81 */     AxisAlignedBB aabb = null;
/*  82 */     if (te.hasRelated()) {
/*  83 */       dirOpposite.rotateXYZAt(te.conv, te.relatedBlockX, te.relatedBlockY, te.relatedBlockZ, parX, parY, parZ);
/*  85 */       if (conpareXYZ(te.conv, parX, parY, parZ - 1)) {
/*  87 */         aabb = AxisAlignedBB.func_72330_a(0.0625D, 0.0D, 0.0D, 0.9375D, 0.875D, 0.9375D);
/*  90 */       } else if (conpareXYZ(te.conv, parX, parY, parZ + 1)) {
/*  92 */         aabb = AxisAlignedBB.func_72330_a(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 1.0D);
/*  95 */       } else if (conpareXYZ(te.conv, parX - 1, parY, parZ)) {
/*  97 */         aabb = AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
/* 100 */       } else if (conpareXYZ(te.conv, parX + 1, parY, parZ)) {
/* 102 */         aabb = AxisAlignedBB.func_72330_a(0.0625D, 0.0D, 0.0625D, 1.0D, 0.875D, 0.9375D);
/*     */       } 
/*     */     } else {
/* 108 */       aabb = AxisAlignedBB.func_72330_a(0.0625D, 0.0D, 0.0625D, 1.0D, 0.875D, 0.9375D);
/*     */     } 
/* 111 */     if (aabb != null)
/* 112 */       gDir.rotateAABBAt(aabb, parX, parY, parZ); 
/*     */   }
/*     */   
/*     */   public void func_149689_a(World world, int parX, int parY, int parZ, EntityLivingBase entityLivingBase, ItemStack itemStack) {
/* 125 */     GravityDirection gDir = Gravity.getGravityDirection((Entity)entityLivingBase);
/* 126 */     int[] conv = new int[3];
/* 130 */     gDir.rotateXYZAt(conv, parX, parY, parZ - 1, parX, parY, parZ);
/* 131 */     Block blockNorth = world.func_147439_a(conv[0], conv[1], conv[2]);
/* 132 */     boolean canConbBlockNorth = canConbineWith(gDir, (IBlockAccess)world, blockNorth, conv[0], conv[1], conv[2], parX, parY, parZ);
/* 135 */     gDir.rotateXYZAt(conv, parX, parY, parZ + 1, parX, parY, parZ);
/* 136 */     Block blockSouth = world.func_147439_a(conv[0], conv[1], conv[2]);
/* 137 */     boolean canConbBlockSouth = canConbineWith(gDir, (IBlockAccess)world, blockSouth, conv[0], conv[1], conv[2], parX, parY, parZ);
/* 140 */     gDir.rotateXYZAt(conv, parX - 1, parY, parZ, parX, parY, parZ);
/* 141 */     Block blockWest = world.func_147439_a(conv[0], conv[1], conv[2]);
/* 142 */     boolean canConbBlockWest = canConbineWith(gDir, (IBlockAccess)world, blockWest, conv[0], conv[1], conv[2], parX, parY, parZ);
/* 145 */     gDir.rotateXYZAt(conv, parX + 1, parY, parZ, parX, parY, parZ);
/* 146 */     Block blockEast = world.func_147439_a(conv[0], conv[1], conv[2]);
/* 147 */     boolean canConbBlockEast = canConbineWith(gDir, (IBlockAccess)world, blockEast, conv[0], conv[1], conv[2], parX, parY, parZ);
/* 150 */     int direction4 = getGravityFixedDirectionFromEntity(entityLivingBase);
/* 152 */     byte meta = 0;
/* 153 */     if (direction4 == 0)
/* 155 */       meta = 2; 
/* 158 */     if (direction4 == 1)
/* 160 */       meta = 5; 
/* 163 */     if (direction4 == 2)
/* 165 */       meta = 3; 
/* 168 */     if (direction4 == 3)
/* 170 */       meta = 4; 
/* 173 */     if (!canConbBlockNorth && !canConbBlockSouth && !canConbBlockWest && !canConbBlockEast) {
/* 176 */       setRelatedWithThis((IBlockAccess)world, parX, parY, parZ, parX, parY, parZ, gDir, false);
/* 177 */       world.func_72921_c(parX, parY, parZ, meta, 3);
/*     */     } else {
/* 182 */       if (canConbBlockNorth) {
/* 185 */         if (meta == 2 || meta == 3)
/* 187 */           if (blockWest == Blocks.field_150350_a) {
/* 188 */             meta = 4;
/*     */           } else {
/* 190 */             meta = 5;
/*     */           }  
/* 193 */         gDir.rotateXYZAt(conv, parX, parY, parZ - 1, parX, parY, parZ);
/* 195 */       } else if (canConbBlockSouth) {
/* 198 */         if (meta == 2 || meta == 3)
/* 200 */           if (blockEast == Blocks.field_150350_a) {
/* 201 */             meta = 5;
/*     */           } else {
/* 203 */             meta = 4;
/*     */           }  
/* 206 */         gDir.rotateXYZAt(conv, parX, parY, parZ + 1, parX, parY, parZ);
/* 208 */       } else if (canConbBlockWest) {
/* 211 */         if (meta == 5 || meta == 4)
/* 213 */           if (blockNorth == Blocks.field_150350_a) {
/* 214 */             meta = 2;
/*     */           } else {
/* 216 */             meta = 3;
/*     */           }  
/* 219 */         gDir.rotateXYZAt(conv, parX - 1, parY, parZ, parX, parY, parZ);
/*     */       } else {
/* 224 */         if (meta == 5 || meta == 4)
/* 226 */           if (blockSouth == Blocks.field_150350_a) {
/* 227 */             meta = 3;
/*     */           } else {
/* 229 */             meta = 2;
/*     */           }  
/* 232 */         gDir.rotateXYZAt(conv, parX + 1, parY, parZ, parX, parY, parZ);
/*     */       } 
/* 236 */       setRelatedWithThis((IBlockAccess)world, conv[0], conv[1], conv[2], parX, parY, parZ, gDir, false);
/* 237 */       setRelatedWithThis((IBlockAccess)world, parX, parY, parZ, conv[0], conv[1], conv[2], gDir, true);
/* 239 */       world.func_72921_c(conv[0], conv[1], conv[2], meta, 3);
/* 240 */       world.func_72921_c(parX, parY, parZ, meta, 3);
/*     */     } 
/* 244 */     if (itemStack.func_82837_s())
/* 246 */       ((TileEntityChestEx)world.func_147438_o(parX, parY, parZ)).func_145976_a(itemStack.func_82833_r()); 
/*     */   }
/*     */   
/*     */   private void setRelatedWithThis(IBlockAccess world, int targetX, int targetY, int targetZ, int thisX, int thisY, int thisZ, GravityDirection gDir, boolean isSubBlock) {
/* 250 */     TileEntityChestEx te = getTileEntityChestEx(world, targetX, targetY, targetZ);
/* 251 */     te.relatedBlockX = thisX;
/* 252 */     te.relatedBlockY = thisY;
/* 253 */     te.relatedBlockZ = thisZ;
/* 254 */     te.isSubBlock = isSubBlock;
/* 255 */     te.setGravityDirection(gDir);
/*     */   }
/*     */   
/*     */   public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
/* 262 */     return true;
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int parX, int parY, int parZ, Block block) {
/* 271 */     TileEntityChestEx te = getTileEntityChestEx((IBlockAccess)world, parX, parY, parZ);
/* 272 */     if (te != null) {
/* 274 */       Block relatedBlock = world.func_147439_a(te.relatedBlockX, te.relatedBlockY, te.relatedBlockZ);
/* 275 */       if (relatedBlock == null || relatedBlock == Blocks.field_150350_a) {
/* 276 */         te.relatedBlockX = parX;
/* 277 */         te.relatedBlockY = parY;
/* 278 */         te.relatedBlockZ = parZ;
/* 279 */         te.isSubBlock = false;
/* 281 */         world.func_147471_g(parX, parY, parZ);
/*     */       } else {
/* 283 */         TileEntityChestEx relatedTe = getTileEntityChestEx((IBlockAccess)world, te.relatedBlockX, te.relatedBlockY, te.relatedBlockZ);
/* 284 */         if (relatedTe == null || relatedTe.relatedBlockX != parX || relatedTe.relatedBlockY != parY || relatedTe.relatedBlockZ != parZ) {
/* 285 */           te.relatedBlockX = parX;
/* 286 */           te.relatedBlockY = parY;
/* 287 */           te.relatedBlockZ = parZ;
/* 288 */           te.isSubBlock = false;
/* 290 */           world.func_147471_g(parX, parY, parZ);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_149749_a(World world, int parX, int parY, int parZ, Block block, int meta) {
/* 298 */     TileEntityChestEx tileentitychest = (TileEntityChestEx)world.func_147438_o(parX, parY, parZ);
/* 300 */     if (tileentitychest != null) {
/* 302 */       for (int i1 = 0; i1 < tileentitychest.func_70302_i_(); i1++) {
/* 304 */         ItemStack itemstack = tileentitychest.func_70301_a(i1);
/* 306 */         if (itemstack != null) {
/*     */           EntityItem entityitem;
/* 308 */           float f = this.rand.nextFloat() * 0.8F + 0.1F;
/* 309 */           float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
/* 312 */           for (float f2 = this.rand.nextFloat() * 0.8F + 0.1F; itemstack.field_77994_a > 0; world.func_72838_d((Entity)entityitem)) {
/* 314 */             int j1 = this.rand.nextInt(21) + 10;
/* 316 */             if (j1 > itemstack.field_77994_a)
/* 318 */               j1 = itemstack.field_77994_a; 
/* 321 */             itemstack.field_77994_a -= j1;
/* 322 */             entityitem = new EntityItem(world, (double)((float)parX + f), (double)((float)parY + f1), (double)((float)parZ + f2), new ItemStack(itemstack.func_77973_b(), j1, itemstack.func_77960_j()));
/* 323 */             float f3 = 0.05F;
/* 324 */             entityitem.field_70159_w = (double)((float)this.rand.nextGaussian() * f3);
/* 325 */             entityitem.field_70181_x = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
/* 326 */             entityitem.field_70179_y = (double)((float)this.rand.nextGaussian() * f3);
/* 328 */             if (itemstack.func_77942_o())
/* 330 */               entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b()); 
/*     */           } 
/*     */         } 
/*     */       } 
/* 336 */       world.func_147453_f(parX, parY, parZ, block);
/*     */     } 
/* 339 */     super.func_149749_a(world, parX, parY, parZ, block, meta);
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World world, int parX, int parY, int parZ, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
/* 347 */     if (world.field_72995_K)
/* 349 */       return true; 
/* 353 */     IInventory iinventory = conInventory(world, parX, parY, parZ);
/* 355 */     if (iinventory != null)
/* 357 */       entityPlayer.func_71007_a(iinventory); 
/* 360 */     return true;
/*     */   }
/*     */   
/*     */   public IInventory conInventory(World world, int parX, int parY, int parZ) {
/* 366 */     TileEntityChestEx te = getTileEntityChestEx((IBlockAccess)world, parX, parY, parZ);
/* 367 */     if (te.hasRelated() && world.func_147439_a(te.relatedBlockX, te.relatedBlockY, te.relatedBlockZ) == this) {
/* 368 */       if (te.isSubBlock)
/* 369 */         return new InventoryLargeChest("container.chestDouble", (IInventory)world.func_147438_o(parX, parY, parZ), (IInventory)world.func_147438_o(te.relatedBlockX, te.relatedBlockY, te.relatedBlockZ)); 
/* 371 */       return new InventoryLargeChest("container.chestDouble", (IInventory)world.func_147438_o(te.relatedBlockX, te.relatedBlockY, te.relatedBlockZ), (IInventory)world.func_147438_o(parX, parY, parZ));
/*     */     } 
/* 374 */     return (IInventory)world.func_147438_o(parX, parY, parZ);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
/* 383 */     TileEntityChestEx tileentitychest = new TileEntityChestEx();
/* 384 */     return (TileEntity)tileentitychest;
/*     */   }
/*     */   
/*     */   public boolean func_149744_f() {
/* 392 */     return (this.chestType == 1);
/*     */   }
/*     */   
/*     */   public int func_149709_b(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_) {
/* 397 */     if (!func_149744_f())
/* 399 */       return 0; 
/* 403 */     int i1 = ((TileEntityChestEx)p_149709_1_.func_147438_o(p_149709_2_, p_149709_3_, p_149709_4_)).numPlayersUsing;
/* 404 */     return MathHelper.func_76125_a(i1, 0, 15);
/*     */   }
/*     */   
/*     */   public int func_149748_c(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_) {
/* 410 */     return (p_149748_5_ == 1) ? func_149709_b(p_149748_1_, p_149748_2_, p_149748_3_, p_149748_4_, p_149748_5_) : 0;
/*     */   }
/*     */   
/*     */   private static boolean func_149953_o(World p_149953_0_, int p_149953_1_, int p_149953_2_, int p_149953_3_) {
/*     */     EntityOcelot entityocelot;
/* 415 */     Iterator<Entity> iterator = p_149953_0_.func_72872_a(EntityOcelot.class, AxisAlignedBB.func_72330_a((double)p_149953_1_, (double)(p_149953_2_ + 1), (double)p_149953_3_, (double)(p_149953_1_ + 1), (double)(p_149953_2_ + 2), (double)(p_149953_3_ + 1))).iterator();
/*     */     do {
/* 420 */       if (!iterator.hasNext())
/* 422 */         return false; 
/* 425 */       Entity entity = iterator.next();
/* 426 */       entityocelot = (EntityOcelot)entity;
/* 428 */     } while (!entityocelot.func_70906_o());
/* 430 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_149740_M() {
/* 439 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149736_g(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_) {
/* 448 */     return Container.func_94526_b(conInventory(p_149736_1_, p_149736_2_, p_149736_3_, p_149736_4_));
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister p_149651_1_) {
/* 454 */     this.field_149761_L = p_149651_1_.func_94245_a("planks_oak");
/*     */   }
/*     */   
/*     */   private TileEntityChestEx getTileEntityChestEx(IBlockAccess world, int parX, int parY, int parZ) {
/* 460 */     TileEntity te = world.func_147438_o(parX, parY, parZ);
/* 461 */     if (te != null && te instanceof TileEntityChestEx)
/* 462 */       return (TileEntityChestEx)te; 
/* 464 */     return null;
/*     */   }
/*     */   
/*     */   private GravityDirection getGDirection(IBlockAccess world, int parX, int parY, int parZ) {
/* 468 */     TileEntity te = world.func_147438_o(parX, parY, parZ);
/* 469 */     if (te != null && te instanceof TileEntityChestEx)
/* 470 */       return ((TileEntityChestEx)te).getGravityDirection(); 
/* 472 */     return GravityDirection.upTOdown_YN;
/*     */   }
/*     */   
/*     */   private boolean conpareXYZ(int[] conv, int parX, int parY, int parZ) {
/* 476 */     return (conv[0] == parX && conv[1] == parY && conv[2] == parZ);
/*     */   }
/*     */   
/*     */   private int getGravityFixedDirectionFromEntity(EntityLivingBase entityLivingBase) {
/* 480 */     GravityDirection gDir = Gravity.getGravityDirection((Entity)entityLivingBase);
/* 481 */     Vec3 playerLookVec = entityLivingBase.func_70040_Z();
/* 482 */     GravityDirection dirOpposite = GravityDirection.turnWayForNormal(gDir);
/* 483 */     dirOpposite.rotateVec3(playerLookVec);
/* 484 */     double pitch = -Math.asin(playerLookVec.field_72448_b) * 57.29577951308232D;
/* 485 */     double yaw = -90.0D + Math.atan2(playerLookVec.field_72449_c, playerLookVec.field_72450_a) * 180.0D / Math.PI;
/* 487 */     return MathHelper.func_76128_c(yaw * 4.0D / 360.0D + 0.5D) & 0x3;
/*     */   }
/*     */   
/*     */   public boolean canConbineWith(GravityDirection gDir, IBlockAccess world, Block block, int targetX, int targetY, int targetZ, int thisX, int thisY, int thisZ) {
/* 491 */     if (block != this)
/* 491 */       return false; 
/* 492 */     TileEntityChestEx te = getTileEntityChestEx(world, targetX, targetY, targetZ);
/* 493 */     if (te == null || te.hasRelated() || te.getGravityDirection() != gDir)
/* 493 */       return false; 
/* 494 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isConbinedFrom(GravityDirection gDir, IBlockAccess world, Block block, int targetX, int targetY, int targetZ, int thisX, int thisY, int thisZ) {
/* 498 */     if (block != this)
/* 498 */       return false; 
/* 499 */     TileEntityChestEx te = getTileEntityChestEx(world, targetX, targetY, targetZ);
/* 500 */     if (te != null && te.hasRelated() && te.getGravityDirection() == gDir)
/* 501 */       return (te.relatedBlockX == thisX && te.relatedBlockY == thisY && te.relatedBlockZ == thisZ); 
/* 503 */     return false;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */