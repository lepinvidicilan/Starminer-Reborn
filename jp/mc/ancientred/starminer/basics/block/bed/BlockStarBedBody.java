/*     */ package jp.mc.ancientred.starminer.basics.block.bed;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.SMReflectionHelper;
/*     */ import jp.mc.ancientred.starminer.basics.block.DirectionConst;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BlockStarBedBody extends Block {
/*     */   public static final double HEIGHT_D = 0.5625D;
/*     */   
/*     */   public static final float HEIGHT_F = 0.5625F;
/*     */   
/*     */   public BlockStarBedBody() {
/*  41 */     super(Material.field_151580_n);
/*  42 */     func_149711_c(0.2F);
/*  43 */     func_149649_H();
/*     */   }
/*     */   
/*     */   public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player) {
/*  48 */     return true;
/*     */   }
/*     */   
/*     */   public int getGravityDirection(IBlockAccess world, int x, int y, int z) {
/*  51 */     int meta = world.func_72805_g(x, y, z);
/*  52 */     int[] headRel = DirectionConst.CHECKNEIGHBOR_LIST[meta & 0x7];
/*     */     Block block;
/*  54 */     if ((block = world.func_147439_a(x + headRel[0], y + headRel[1], z + headRel[2])) == SMModContainer.StarBedHeadBlock)
/*  55 */       return ((BlockStarBedHead)block).getGravityDirection(world, x + headRel[0], y + headRel[1], z + headRel[2]); 
/*  57 */     return -1;
/*     */   }
/*     */   
/*     */   public int getConnectionDirection(IBlockAccess world, int x, int y, int z) {
/*  61 */     int meta = world.func_72805_g(x, y, z);
/*  62 */     return meta & 0x7;
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
/*  67 */     if (par1World.field_72995_K)
/*  69 */       return true; 
/*  74 */     int meta = par1World.func_72805_g(par2, par3, par4);
/*  75 */     int[] headRel = DirectionConst.CHECKNEIGHBOR_LIST[meta & 0x7];
/*  77 */     par2 += headRel[0];
/*  78 */     par3 += headRel[1];
/*  79 */     par4 += headRel[2];
/*  81 */     if (par1World.func_147439_a(par2, par3, par4) != SMModContainer.StarBedHeadBlock)
/*  84 */       return true; 
/*  87 */     meta = par1World.func_72805_g(par2, par3, par4);
/*  89 */     if (par1World.field_73011_w.func_76567_e() && par1World.func_72807_a(par2, par4) != BiomeGenBase.field_76778_j) {
/*  91 */       if (BlockStarBedHead.isBedOccupiedHead(meta)) {
/*  93 */         EntityPlayer entityplayer1 = null;
/*  94 */         Iterator<EntityPlayer> iterator = par1World.field_73010_i.iterator();
/*  96 */         while (iterator.hasNext()) {
/*  98 */           EntityPlayer entityplayer2 = iterator.next();
/* 100 */           if (entityplayer2.func_70608_bn()) {
/* 102 */             ChunkCoordinates chunkcoordinates = entityplayer2.field_71081_bT;
/* 104 */             if (chunkcoordinates.field_71574_a == par2 && chunkcoordinates.field_71572_b == par3 && chunkcoordinates.field_71573_c == par4)
/* 106 */               entityplayer1 = entityplayer2; 
/*     */           } 
/*     */         } 
/* 111 */         if (entityplayer1 != null) {
/* 114 */           par5EntityPlayer.func_146105_b(new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
/* 115 */           return true;
/*     */         } 
/* 118 */         BlockStarBedHead.setBedOccupiedHead((IBlockAccess)par1World, par2, par3, par4, false);
/*     */       } 
/* 121 */       EntityPlayer.EnumStatus enumstatus = par5EntityPlayer.func_71018_a(par2, par3, par4);
/* 122 */       SMReflectionHelper.ignoreHasMovedFlg(par5EntityPlayer);
/* 124 */       if (enumstatus == EntityPlayer.EnumStatus.OK) {
/* 126 */         BlockStarBedHead.setBedOccupiedHead((IBlockAccess)par1World, par2, par3, par4, true);
/* 127 */         return true;
/*     */       } 
/* 131 */       if (enumstatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW) {
/* 134 */         par5EntityPlayer.func_146105_b(new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
/* 136 */       } else if (enumstatus == EntityPlayer.EnumStatus.NOT_SAFE) {
/* 139 */         par5EntityPlayer.func_146105_b(new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
/*     */       } 
/* 142 */       return true;
/*     */     } 
/* 147 */     double d0 = (double)par2 + 0.5D;
/* 148 */     double d1 = (double)par3 + 0.5D;
/* 149 */     double d2 = (double)par4 + 0.5D;
/* 150 */     par1World.func_147468_f(par2, par3, par4);
/* 151 */     par1World.func_72885_a(null, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), 8.0F, true, true);
/* 152 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c() {
/* 159 */     return false;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
/* 165 */     double lw = 0.5625D;
/* 166 */     double hi = 0.4375D;
/* 167 */     int dir = getGravityDirection((IBlockAccess)par1World, par2, par3, par4);
/* 168 */     switch (dir) {
/*     */       case 1:
/* 170 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + hi, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 0:
/* 177 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + lw, (double)par4 + 1.0D);
/*     */       case 3:
/* 184 */         return AxisAlignedBB.func_72330_a((double)par2 + hi, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 2:
/* 191 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + lw, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 5:
/* 198 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + hi, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 4:
/* 205 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + lw);
/*     */     } 
/* 212 */     return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
/* 224 */     return func_149668_a(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess par1IWorld, int par2, int par3, int par4) {
/* 231 */     int dir = getGravityDirection(par1IWorld, par2, par3, par4);
/* 232 */     float lw = 0.5625F;
/* 233 */     float hi = 0.4375F;
/* 234 */     switch (dir) {
/*     */       case 1:
/* 236 */         func_149676_a(0.0F, hi, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         break;
/*     */       case 0:
/* 239 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, lw, 1.0F);
/*     */         break;
/*     */       case 3:
/* 242 */         func_149676_a(hi, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         break;
/*     */       case 2:
/* 245 */         func_149676_a(0.0F, 0.0F, 0.0F, lw, 1.0F, 1.0F);
/*     */         break;
/*     */       case 5:
/* 248 */         func_149676_a(0.0F, 0.0F, hi, 1.0F, 1.0F, 1.0F);
/*     */         break;
/*     */       case 4:
/* 251 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, lw);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block parBlock) {
/* 259 */     int meta = par1World.func_72805_g(par2, par3, par4);
/* 260 */     int[] headRel = DirectionConst.CHECKNEIGHBOR_LIST[meta & 0x7];
/* 262 */     if (par1World.func_147439_a(par2 + headRel[0], par3 + headRel[1], par4 + headRel[2]) != SMModContainer.StarBedHeadBlock)
/* 264 */       par1World.func_147468_f(par2, par3, par4); 
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3) {
/* 271 */     return SMModContainer.StarBedItem;
/*     */   }
/*     */   
/*     */   public ChunkCoordinates getBedSpawnPosition(IBlockAccess world, int x, int y, int z, EntityPlayer player) {
/* 277 */     return getNearestEmptyChunkCoordinates(world, x, y, z, 0);
/*     */   }
/*     */   
/* 279 */   private static final int[] CHECK_HEIGHT_LIST = new int[] { 0, -1, 1, 2 };
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon bedTopIcon;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon bedEndIcon;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon bedSideIcon;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon bedStarIcon;
/*     */   
/*     */   public static ChunkCoordinates getNearestEmptyChunkCoordinates(IBlockAccess world, int parX, int parY, int parZ, int par4) {
/* 285 */     Block block = world.func_147439_a(parX, parY, parZ);
/* 286 */     if (block != SMModContainer.StarBedHeadBlock)
/* 287 */       return null; 
/* 289 */     int gravDir = ((BlockStarBedHead)block).getGravityDirection(world, parX, parY, parZ);
/* 291 */     for (int i = 0; i < CHECK_HEIGHT_LIST.length; i++) {
/*     */       int range;
/* 292 */       int height = CHECK_HEIGHT_LIST[i];
/* 293 */       switch (gravDir) {
/*     */         case 3:
/* 295 */           for (range = 1; range <= 3; range++) {
/* 296 */             for (int z = -range; z <= range; z++) {
/* 297 */               for (int y = -range; y <= range; y++) {
/* 298 */                 if (world.isSideSolid(parX + 1 - height, parY + y, parZ + z, ForgeDirection.WEST, false) && isAirBlockOrGravityWall(world, parX - height, parY + y, parZ + z) && world.func_147437_c(parX - 1 - height, parY + y, parZ + z))
/* 300 */                   return new ChunkCoordinates(parX - 1 - height, parY + y, parZ + z); 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 2:
/* 307 */           for (range = 1; range <= 3; range++) {
/* 308 */             for (int z = -range; z <= range; z++) {
/* 309 */               for (int y = -range; y <= range; y++) {
/* 310 */                 if (world.isSideSolid(parX - 1 + height, parY + y, parZ + z, ForgeDirection.EAST, false) && isAirBlockOrGravityWall(world, parX + height, parY + y, parZ + z) && world.func_147437_c(parX + 1 + height, parY + y, parZ + z))
/* 312 */                   return new ChunkCoordinates(parX + 1 + height, parY + y, parZ + z); 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 5:
/* 319 */           for (range = 1; range <= 3; range++) {
/* 320 */             for (int x = -range; x <= range; x++) {
/* 321 */               for (int y = -range; y <= range; y++) {
/* 322 */                 if (world.isSideSolid(parX + x, parY + y, parZ + 1 - height, ForgeDirection.NORTH, false) && isAirBlockOrGravityWall(world, parX + x, parY + y, parZ - height) && world.func_147437_c(parX + x, parY + y, parZ - 1 - height))
/* 324 */                   return new ChunkCoordinates(parX + x, parY + y, parZ - 1 - height); 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 4:
/* 331 */           for (range = 1; range <= 3; range++) {
/* 332 */             for (int x = -range; x <= range; x++) {
/* 333 */               for (int y = -range; y <= range; y++) {
/* 334 */                 if (world.isSideSolid(parX + x, parY + y, parZ - 1 + height, ForgeDirection.SOUTH, false) && isAirBlockOrGravityWall(world, parX + x, parY + y, parZ + height) && world.func_147437_c(parX + x, parY + y, parZ + 1 + height))
/* 336 */                   return new ChunkCoordinates(parX + x, parY + y, parZ + 1 + height); 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 1:
/* 343 */           for (range = 1; range <= 3; range++) {
/* 344 */             for (int x = -range; x <= range; x++) {
/* 345 */               for (int z = -range; z <= range; z++) {
/* 346 */                 if (world.isSideSolid(parX + x, parY + 1 - height, parZ + z, ForgeDirection.DOWN, false) && isAirBlockOrGravityWall(world, parX + x, parY - height, parZ + z) && world.func_147437_c(parX + x, parY - 1 - height, parZ + z))
/* 348 */                   return new ChunkCoordinates(parX + x, parY - 1 - height, parZ + z); 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 0:
/* 355 */           for (range = 1; range <= 3; range++) {
/* 356 */             for (int x = -range; x <= range; x++) {
/* 357 */               for (int z = -range; z <= range; z++) {
/* 358 */                 if (world.isSideSolid(parX + x, parY - 1 + height, parZ + z, ForgeDirection.UP, false) && isAirBlockOrGravityWall(world, parX + x, parY + height, parZ + z) && world.func_147437_c(parX + x, parY + 1 + height, parZ + z))
/* 360 */                   return new ChunkCoordinates(parX + x, parY + 1 + height, parZ + z); 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     } 
/* 369 */     return null;
/*     */   }
/*     */   
/*     */   public static final boolean isAirBlockOrGravityWall(IBlockAccess world, int parX, int parY, int parZ) {
/* 372 */     return (world.func_147437_c(parX, parY, parZ) || world.func_147439_a(parX, parY, parZ) == SMModContainer.GravityWallBlock);
/*     */   }
/*     */   
/*     */   public int func_149656_h() {
/* 377 */     return 1;
/*     */   }
/*     */   
/*     */   public void setBedOccupied(IBlockAccess world, int x, int y, int z, EntityPlayer player, boolean occupied) {
/* 385 */     int meta = world.func_72805_g(x, y, z);
/* 386 */     int[] headRel = DirectionConst.CHECKNEIGHBOR_LIST[meta & 0x7];
/*     */     Block block;
/* 388 */     if ((block = world.func_147439_a(x + headRel[0], y + headRel[1], z + headRel[2])) == SMModContainer.StarBedHeadBlock)
/* 389 */       BlockStarBedHead.setBedOccupiedHead(world, x + headRel[0], y + headRel[1], z + headRel[2], occupied); 
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Item func_149694_d(World par1World, int par2, int par3, int par4) {
/* 399 */     return SMModContainer.StarBedItem;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149645_b() {
/* 405 */     return 4341808;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149686_d() {
/* 411 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getBedTopIcon() {
/* 417 */     return this.bedTopIcon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getBedEndIcon() {
/* 423 */     return this.bedEndIcon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getBedSideIcon() {
/* 429 */     return this.bedSideIcon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getBedStarIcon() {
/* 435 */     return this.bedStarIcon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister) {
/* 441 */     this.bedTopIcon = par1IconRegister.func_94245_a("bed_feet_top");
/* 442 */     this.bedEndIcon = par1IconRegister.func_94245_a("bed_feet_end");
/* 443 */     this.bedSideIcon = par1IconRegister.func_94245_a("bed_feet_side");
/* 444 */     this.bedStarIcon = par1IconRegister.func_94245_a("starminer:bedstar");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/* 450 */     return this.bedTopIcon;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */