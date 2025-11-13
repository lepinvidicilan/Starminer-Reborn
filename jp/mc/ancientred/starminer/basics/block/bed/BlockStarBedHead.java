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
/*     */ 
/*     */ public class BlockStarBedHead extends Block {
/*     */   public static final double HEIGHT_D = 0.5625D;
/*     */   
/*     */   public static final float HEIGHT_F = 0.5625F;
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
/*     */   public BlockStarBedHead() {
/*  40 */     super(Material.field_151580_n);
/*  41 */     func_149711_c(0.2F);
/*  42 */     func_149649_H();
/*     */   }
/*     */   
/*     */   public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player) {
/*  47 */     return true;
/*     */   }
/*     */   
/*     */   public int getGravityDirection(IBlockAccess world, int x, int y, int z) {
/*  50 */     int meta = world.func_72805_g(x, y, z);
/*  51 */     return meta & 0x7;
/*     */   }
/*     */   
/*     */   public int getConnectionDirection(IBlockAccess world, int x, int y, int z) {
/*  55 */     int[] bodyRel = DirectionConst.getBlockBedHeadNeighborBody(world, x, y, z);
/*  56 */     if (bodyRel != null && world.func_147439_a(x + bodyRel[0], y + bodyRel[1], z + bodyRel[2]) == SMModContainer.StarBedBodyBlock) {
/*  58 */       int meta = world.func_72805_g(x + bodyRel[0], y + bodyRel[1], z + bodyRel[2]);
/*  59 */       return meta & 0x7;
/*     */     } 
/*  61 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
/*  67 */     if (par1World.field_72995_K)
/*  69 */       return true; 
/*  74 */     int meta = par1World.func_72805_g(par2, par3, par4);
/*  76 */     if (par1World.field_73011_w.func_76567_e() && par1World.func_72807_a(par2, par4) != BiomeGenBase.field_76778_j) {
/*  78 */       if (isBedOccupiedHead(meta)) {
/*  80 */         EntityPlayer entityplayer1 = null;
/*  81 */         Iterator<EntityPlayer> iterator = par1World.field_73010_i.iterator();
/*  83 */         while (iterator.hasNext()) {
/*  85 */           EntityPlayer entityplayer2 = iterator.next();
/*  87 */           if (entityplayer2.func_70608_bn()) {
/*  89 */             ChunkCoordinates chunkcoordinates = entityplayer2.field_71081_bT;
/*  91 */             if (chunkcoordinates.field_71574_a == par2 && chunkcoordinates.field_71572_b == par3 && chunkcoordinates.field_71573_c == par4)
/*  93 */               entityplayer1 = entityplayer2; 
/*     */           } 
/*     */         } 
/*  98 */         if (entityplayer1 != null) {
/* 101 */           par5EntityPlayer.func_146105_b(new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
/* 102 */           return true;
/*     */         } 
/* 105 */         setBedOccupiedHead((IBlockAccess)par1World, par2, par3, par4, false);
/*     */       } 
/* 108 */       EntityPlayer.EnumStatus enumstatus = par5EntityPlayer.func_71018_a(par2, par3, par4);
/* 109 */       SMReflectionHelper.ignoreHasMovedFlg(par5EntityPlayer);
/* 111 */       if (enumstatus == EntityPlayer.EnumStatus.OK) {
/* 113 */         setBedOccupiedHead((IBlockAccess)par1World, par2, par3, par4, true);
/* 114 */         return true;
/*     */       } 
/* 118 */       if (enumstatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW) {
/* 121 */         par5EntityPlayer.func_146105_b(new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
/* 123 */       } else if (enumstatus == EntityPlayer.EnumStatus.NOT_SAFE) {
/* 126 */         par5EntityPlayer.func_146105_b(new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
/*     */       } 
/* 129 */       return true;
/*     */     } 
/* 134 */     double d0 = (double)par2 + 0.5D;
/* 135 */     double d1 = (double)par3 + 0.5D;
/* 136 */     double d2 = (double)par4 + 0.5D;
/* 137 */     par1World.func_147468_f(par2, par3, par4);
/* 138 */     par1World.func_72885_a(null, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), 8.0F, true, true);
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c() {
/* 146 */     return false;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
/* 152 */     double lw = 0.5625D;
/* 153 */     double hi = 0.4375D;
/* 154 */     int dir = getGravityDirection((IBlockAccess)par1World, par2, par3, par4);
/* 155 */     switch (dir) {
/*     */       case 1:
/* 157 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + hi, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 0:
/* 164 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + lw, (double)par4 + 1.0D);
/*     */       case 3:
/* 171 */         return AxisAlignedBB.func_72330_a((double)par2 + hi, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 2:
/* 178 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + lw, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 5:
/* 185 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + hi, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */       case 4:
/* 192 */         return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + lw);
/*     */     } 
/* 199 */     return AxisAlignedBB.func_72330_a((double)par2 + 0.0D, (double)par3 + 0.0D, (double)par4 + 0.0D, (double)par2 + 1.0D, (double)par3 + 1.0D, (double)par4 + 1.0D);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
/* 211 */     return func_149668_a(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   public void func_149719_a(IBlockAccess par1IWorld, int par2, int par3, int par4) {
/* 217 */     int dir = getGravityDirection(par1IWorld, par2, par3, par4);
/* 218 */     float lw = 0.5625F;
/* 219 */     float hi = 0.4375F;
/* 220 */     switch (dir) {
/*     */       case 1:
/* 222 */         func_149676_a(0.0F, hi, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         break;
/*     */       case 0:
/* 225 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, lw, 1.0F);
/*     */         break;
/*     */       case 3:
/* 228 */         func_149676_a(hi, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         break;
/*     */       case 2:
/* 231 */         func_149676_a(0.0F, 0.0F, 0.0F, lw, 1.0F, 1.0F);
/*     */         break;
/*     */       case 5:
/* 234 */         func_149676_a(0.0F, 0.0F, hi, 1.0F, 1.0F, 1.0F);
/*     */         break;
/*     */       case 4:
/* 237 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, lw);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block parBlock) {
/* 245 */     int[] bodyRel = DirectionConst.getBlockBedHeadNeighborBody((IBlockAccess)par1World, par2, par3, par4);
/* 246 */     if (bodyRel == null || par1World.func_147439_a(par2 + bodyRel[0], par3 + bodyRel[1], par4 + bodyRel[2]) != SMModContainer.StarBedBodyBlock)
/* 248 */       par1World.func_147468_f(par2, par3, par4); 
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3) {
/* 255 */     return SMModContainer.StarBedItem;
/*     */   }
/*     */   
/*     */   public ChunkCoordinates getBedSpawnPosition(IBlockAccess world, int x, int y, int z, EntityPlayer player) {
/* 261 */     return BlockStarBedBody.getNearestEmptyChunkCoordinates(world, x, y, z, 0);
/*     */   }
/*     */   
/*     */   public int func_149656_h() {
/* 267 */     return 1;
/*     */   }
/*     */   
/*     */   public static boolean isBedOccupiedHead(int par0) {
/* 272 */     return ((par0 & 0x8) != 0);
/*     */   }
/*     */   
/*     */   public static void setBedOccupiedHead(IBlockAccess world, int par1, int par2, int par3, boolean doSet) {
/* 277 */     if (world instanceof World) {
/* 278 */       int meta = world.func_72805_g(par1, par2, par3);
/* 279 */       meta &= 0x7;
/* 280 */       if (doSet)
/* 282 */         meta |= 0x8; 
/* 284 */       ((World)world).func_72921_c(par1, par2, par3, meta, 4);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setBedOccupied(IBlockAccess world, int x, int y, int z, EntityPlayer player, boolean occupied) {
/* 293 */     if (world instanceof World)
/* 294 */       setBedOccupiedHead(world, x, y, z, occupied); 
/*     */   }
/*     */   
/*     */   public static void setPositionForStarBedSleepingPlayer(EntityPlayer player, int par1, int par2, int par3, int gravDir, int connDir) {
/* 305 */     double move = 0.3D;
/* 306 */     double setPad = 1.3D;
/* 307 */     double padX = 0.0D;
/* 308 */     double padY = 0.0D;
/* 309 */     double padZ = 0.0D;
/* 311 */     switch (connDir) {
/*     */       case 3:
/* 313 */         padX = setPad;
/*     */         break;
/*     */       case 2:
/* 316 */         padX = -setPad;
/*     */         break;
/*     */       case 5:
/* 319 */         padZ = setPad;
/*     */         break;
/*     */       case 4:
/* 322 */         padZ = -setPad;
/*     */         break;
/*     */       case 1:
/* 325 */         padY = setPad;
/*     */         break;
/*     */       case 0:
/* 328 */         padY = -setPad;
/*     */         break;
/*     */     } 
/* 332 */     switch (gravDir) {
/*     */       case 3:
/* 334 */         player.func_70080_a(padX + (double)par1 + 0.5D - move, padY + (double)par2 + 0.5D, padZ + (double)par3 + 0.5D, 0.0F, 0.0F);
/*     */         break;
/*     */       case 2:
/* 337 */         player.func_70080_a(padX + (double)par1 + 0.5D + move, padY + (double)par2 + 0.5D, padZ + (double)par3 + 0.5D, 0.0F, 0.0F);
/*     */         break;
/*     */       case 5:
/* 340 */         player.func_70080_a(padX + (double)par1 + 0.5D, padY + (double)par2 + 0.5D, padZ + (double)par3 + 0.5D - move, 0.0F, 0.0F);
/*     */         break;
/*     */       case 4:
/* 343 */         player.func_70080_a(padX + (double)par1 + 0.5D, padY + (double)par2 + 0.5D, padZ + (double)par3 + 0.5D + move, 0.0F, 0.0F);
/*     */         break;
/*     */       case 1:
/* 346 */         player.func_70080_a(padX + (double)par1 + 0.5D, padY + (double)par2 + 0.5D - move, padZ + (double)par3 + 0.5D, 0.0F, 0.0F);
/*     */         break;
/*     */       case 0:
/* 349 */         player.func_70080_a(padX + (double)par1 + 0.5D, padY + (double)par2 + 0.5D + move, padZ + (double)par3 + 0.5D, 0.0F, 0.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Item func_149694_d(World par1World, int par2, int par3, int par4) {
/* 360 */     return SMModContainer.StarBedItem;
/*     */   }
/*     */   
/*     */   public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
/* 365 */     if (par6EntityPlayer.field_71075_bZ.field_75098_d) {
/* 367 */       int[] bodyRel = DirectionConst.getBlockBedHeadNeighborBody((IBlockAccess)par1World, par2, par3, par4);
/* 368 */       if (bodyRel == null || par1World.func_147439_a(par2 + bodyRel[0], par3 + bodyRel[1], par4 + bodyRel[2]) != SMModContainer.StarBedBodyBlock)
/* 370 */         par1World.func_147468_f(par2, par3, par4); 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149645_b() {
/* 378 */     return 4341808;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149686_d() {
/* 384 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getBedTopIcon() {
/* 390 */     return this.bedTopIcon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getBedEndIcon() {
/* 396 */     return this.bedEndIcon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getBedSideIcon() {
/* 402 */     return this.bedSideIcon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister) {
/* 408 */     this.bedTopIcon = par1IconRegister.func_94245_a("bed_head_top");
/* 409 */     this.bedEndIcon = par1IconRegister.func_94245_a("bed_head_end");
/* 410 */     this.bedSideIcon = par1IconRegister.func_94245_a("bed_head_side");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/* 416 */     return this.bedTopIcon;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */