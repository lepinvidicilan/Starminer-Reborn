/*     */ package jp.mc.ancientred.starminer.basics.block;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.mc.ancientred.starminer.api.Gravity;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.SMReflectionHelper;
/*     */ import jp.mc.ancientred.starminer.basics.dummy.DummyRotatedBlockAccess;
/*     */ import jp.mc.ancientred.starminer.basics.dummy.DummyRotatedWorld;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityBlockRotator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.ITileEntityProvider;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemReed;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockRotator extends Block implements ITileEntityProvider {
/*     */   public static final float EXPAND_BND_SIZE = 0.01F;
/*     */   
/*     */   private static final double EXPAND_AABB = 10.0D;
/*     */   
/*     */   public boolean rotateBlockSelfFlg;
/*     */   
/*     */   public BlockRotator() {
/*  44 */     super(Material.field_151592_s);
/*  45 */     func_149711_c(1.0F);
/*  46 */     func_149752_b(1.0F);
/*  47 */     func_149715_a(1.0F);
/*  48 */     func_149672_a(field_149778_k);
/*  49 */     func_149658_d("starminer:rotator_yellow");
/*  51 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister) {
/*  57 */     this.field_149761_L = par1IconRegister.func_94245_a(func_149641_N());
/*     */   }
/*     */   
/*     */   public boolean canRenderInPass(int pass) {
/*  62 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149701_w() {
/*  66 */     return 1;
/*     */   }
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
/*  71 */     return new ItemStack(SMModContainer.BlockRotatorBlock);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World par1World, int metadata) {
/*  76 */     return new TileEntityBlockRotator();
/*     */   }
/*     */   
/*     */   public void func_149689_a(World world, int parX, int parY, int parZ, EntityLivingBase parPlacer, ItemStack itemStack) {
/*  81 */     if (parPlacer != null && parPlacer instanceof EntityPlayer) {
/*  82 */       Gravity gravity = Gravity.getGravityProp((Entity)parPlacer);
/*  83 */       TileEntityBlockRotator te = getTileEntityBlockRotator((IBlockAccess)world, parX, parY, parZ);
/*  84 */       if (te != null)
/*  85 */         te.setGravityDirection(gravity.gravityDirection); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_149695_a(World world, int parX, int parY, int parZ, Block par5Block) {
/*  92 */     TileEntityBlockRotator te = getTileEntityBlockRotator((IBlockAccess)world, parX, parY, parZ);
/*  93 */     if (te != null && 
/*  94 */       te.hasRelated()) {
/*  95 */       Block relatedBlock = world.func_147439_a(te.relatedBlockX, te.relatedBlockY, te.relatedBlockZ);
/*  96 */       if (relatedBlock instanceof BlockRotator) {
/*  97 */         TileEntityBlockRotator relatedTe = getTileEntityBlockRotator((IBlockAccess)world, te.relatedBlockX, te.relatedBlockY, te.relatedBlockZ);
/*  98 */         if (relatedTe != null && relatedTe.relatedBlockX == parX && relatedTe.relatedBlockY == parY && relatedTe.relatedBlockZ == parZ)
/*     */           return; 
/*     */       } 
/* 105 */       world.func_147449_b(parX, parY, parZ, Blocks.field_150350_a);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_149749_a(World world, int parX, int parY, int parZ, Block block, int meta) {
/* 123 */     if (!world.field_72995_K && world.func_82736_K().func_82766_b("doTileDrops") && !world.restoringBlockSnapshots) {
/* 124 */       TileEntityBlockRotator te = getTileEntityBlockRotator((IBlockAccess)world, parX, parY, parZ);
/* 125 */       if (te != null && !te.isSubBlock) {
/* 126 */         Item storedItem = te.getStoredItem();
/* 127 */         if (storedItem != null && storedItem != Items.field_151105_aU) {
/* 128 */           int initialBlockMeta = te.getItemMetadata();
/* 129 */           ItemStack itemStack = new ItemStack(storedItem, 1, initialBlockMeta);
/* 130 */           if (this.captureDrops.get()) {
/* 132 */             this.capturedDrops.get().add(itemStack);
/*     */             return;
/*     */           } 
/* 135 */           float f = 0.7F;
/* 136 */           double d0 = (double)(world.field_73012_v.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
/* 137 */           double d1 = (double)(world.field_73012_v.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
/* 138 */           double d2 = (double)(world.field_73012_v.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
/* 139 */           EntityItem entityitem = new EntityItem(world, (double)parX + d0, (double)parY + d1, (double)parZ + d2, itemStack);
/* 140 */           entityitem.field_145804_b = 10;
/* 141 */           world.func_72838_d((Entity)entityitem);
/*     */         } 
/*     */       } 
/*     */     } 
/* 145 */     super.func_149749_a(world, parX, parY, parZ, block, meta);
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World world, int parX, int parY, int parZ, EntityPlayer par5EntityPlayer, int side, float par7, float par8, float par9) {
/* 150 */     TileEntityBlockRotator te = getTileEntityBlockRotator((IBlockAccess)world, parX, parY, parZ);
/* 151 */     if (te == null)
/* 151 */       return true; 
/*     */     try {
/* 154 */       Block block = te.getStoredBlock();
/* 156 */       if (block != null && block != Blocks.field_150350_a) {
/*     */         World toPass;
/* 159 */         if (block instanceof net.minecraft.block.BlockDoor) {
/* 160 */           DummyRotatedWorld dummyWorld = DummyRotatedWorld.get();
/* 161 */           toPass = (world == dummyWorld) ? world : dummyWorld.wrapp(world, te.getGravityDirection(), parX, parY, parZ);
/*     */         } else {
/* 163 */           toPass = world;
/*     */         } 
/* 165 */         return block.func_149727_a(toPass, parX, parY, parZ, par5EntityPlayer, side, par7, par8, par9);
/*     */       } 
/*     */     } catch (Exception ex) {
/* 167 */       ex.printStackTrace();
/*     */     } 
/* 170 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isVannilaBlock(Block block) {
/* 174 */     return block.getClass().getName().startsWith("net.minecraft");
/*     */   }
/*     */   
/*     */   public int getLightValue(IBlockAccess world, int parX, int parY, int parZ) {
/* 179 */     TileEntityBlockRotator te = getTileEntityBlockRotator(world, parX, parY, parZ);
/* 180 */     if (te != null) {
/* 181 */       Block block = te.getStoredBlock();
/* 182 */       if (block != null) {
/* 184 */         DummyRotatedBlockAccess dummyBlockAccess = DummyRotatedBlockAccess.get();
/* 185 */         IBlockAccess toPass = (world == dummyBlockAccess) ? world : dummyBlockAccess.wrapp(world, te.getGravityDirection(), parX, parY, parZ);
/* 186 */         return block.getLightValue(toPass, parX, parY, parZ);
/*     */       } 
/*     */     } 
/* 189 */     return this.field_149784_t;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d() {
/* 193 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c() {
/* 197 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b() {
/* 201 */     return 398378466;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess world, int parX, int parY, int parZ, int side) {
/* 209 */     if (this.rotateBlockSelfFlg)
/* 210 */       return (side != 0 && side != 1); 
/* 213 */     TileEntityBlockRotator te = getTileEntityBlockRotator(world, parX, parY, parZ);
/* 214 */     if (te != null) {
/* 215 */       Block block = te.getStoredBlock();
/* 216 */       if (block != null && block != Blocks.field_150350_a) {
/* 218 */         DummyRotatedBlockAccess dummyBlockAccess = DummyRotatedBlockAccess.get();
/* 219 */         IBlockAccess toPass = (world == dummyBlockAccess) ? world : dummyBlockAccess.wrapp(world, te.getGravityDirection(), parX, parY, parZ);
/* 220 */         return block.func_149646_a(toPass, parX, parY, parZ, side);
/*     */       } 
/*     */     } 
/* 223 */     return super.func_149646_a(world, parX, parY, parZ, side);
/*     */   }
/*     */   
/*     */   public void func_149743_a(World world, int parX, int parY, int parZ, AxisAlignedBB aabb, List aabbList, Entity entity) {
/* 229 */     TileEntityBlockRotator te = getTileEntityBlockRotator((IBlockAccess)world, parX, parY, parZ);
/* 230 */     if (te != null) {
/* 231 */       Block block = te.getStoredBlock();
/* 232 */       if (block != null) {
/* 233 */         ArrayList<AxisAlignedBB> dummyList = thHoldDummyList.get();
/* 234 */         dummyList.clear();
/* 237 */         aabb.field_72340_a -= 10.0D;
/* 237 */         aabb.field_72336_d += 10.0D;
/* 238 */         aabb.field_72338_b -= 10.0D;
/* 238 */         aabb.field_72337_e += 10.0D;
/* 239 */         aabb.field_72339_c -= 10.0D;
/* 239 */         aabb.field_72334_f += 10.0D;
/* 242 */         DummyRotatedWorld dummyWorld = DummyRotatedWorld.get();
/* 243 */         World toPass = (world == dummyWorld) ? world : dummyWorld.wrapp(world, te.getGravityDirection(), parX, parY, parZ);
/* 244 */         block.func_149743_a(toPass, parX, parY, parZ, aabb, dummyList, entity);
/* 247 */         aabb.field_72340_a += 10.0D;
/* 247 */         aabb.field_72336_d -= 10.0D;
/* 248 */         aabb.field_72338_b += 10.0D;
/* 248 */         aabb.field_72337_e -= 10.0D;
/* 249 */         aabb.field_72339_c += 10.0D;
/* 249 */         aabb.field_72334_f -= 10.0D;
/* 251 */         for (int i = 0; i < dummyList.size(); i++) {
/* 252 */           AxisAlignedBB axisalignedbb1 = dummyList.get(i);
/* 253 */           if (axisalignedbb1 != null) {
/* 254 */             axisalignedbb1 = te.getGravityDirection().rotateAABBAt(axisalignedbb1, parX, parY, parZ);
/* 255 */             if (aabb.func_72326_a(axisalignedbb1))
/* 257 */               aabbList.add(axisalignedbb1); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/* 268 */     TileEntityBlockRotator te = getTileEntityBlockRotator((IBlockAccess)world, x, y, z);
/* 269 */     if (te != null) {
/* 270 */       Block block = te.getStoredBlock();
/* 271 */       if (block != null) {
/* 272 */         AxisAlignedBB storedBlockAABB = block.func_149668_a(world, x, y, z);
/* 273 */         if (storedBlockAABB == null)
/* 273 */           return null; 
/* 275 */         return te.getGravityDirection().rotateAABBAt(storedBlockAABB, x, y, z);
/*     */       } 
/*     */     } 
/* 278 */     return AxisAlignedBB.func_72330_a((double)x + this.field_149759_B, (double)y + this.field_149760_C, (double)z + this.field_149754_D, (double)x + this.field_149755_E, (double)y + this.field_149756_F, (double)z + this.field_149757_G);
/*     */   }
/*     */   
/*     */   public boolean isLadder(IBlockAccess world, int parX, int parY, int parZ, EntityLivingBase entity) {
/* 284 */     TileEntityBlockRotator te = getTileEntityBlockRotator(world, parX, parY, parZ);
/* 285 */     if (te != null) {
/* 286 */       Block block = te.getStoredBlock();
/* 287 */       if (block != null) {
/* 289 */         DummyRotatedBlockAccess dummyBlockAccess = DummyRotatedBlockAccess.get();
/* 290 */         IBlockAccess toPass = (world == dummyBlockAccess) ? world : dummyBlockAccess.wrapp(world, te.getGravityDirection(), parX, parY, parZ);
/* 291 */         return block.isLadder(toPass, parX, parY, parZ, entity);
/*     */       } 
/*     */     } 
/* 294 */     return false;
/*     */   }
/*     */   
/*     */   public static TileEntityBlockRotator getTileEntityBlockRotator(IBlockAccess world, int par2, int par3, int par4) {
/* 300 */     TileEntity te = world.func_147438_o(par2, par3, par4);
/* 301 */     if (te instanceof TileEntityBlockRotator)
/* 302 */       return (TileEntityBlockRotator)te; 
/* 304 */     return null;
/*     */   }
/*     */   
/*     */   private Block convertItemToBlock(Item item) {
/* 308 */     Block block = null;
/* 309 */     if (item instanceof ItemReed) {
/* 311 */       block = SMReflectionHelper.getField_150935_a((ItemReed)item);
/* 312 */     } else if (item instanceof net.minecraft.item.ItemBlock) {
/* 314 */       block = Block.func_149634_a(item);
/*     */     } 
/* 316 */     return block;
/*     */   }
/*     */   
/* 320 */   private static ThreadLocal<ArrayList> thHoldDummyList = new ThreadLocal<ArrayList>() {
/*     */       protected ArrayList initialValue() {
/* 322 */         return new ArrayList();
/*     */       }
/*     */     };
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */