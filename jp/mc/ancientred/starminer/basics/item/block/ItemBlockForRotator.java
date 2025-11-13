/*     */ package jp.mc.ancientred.starminer.basics.item.block;
/*     */ 
/*     */ import jp.mc.ancientred.starminer.api.Gravity;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.basics.Config;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.SMReflectionHelper;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockRotator;
/*     */ import jp.mc.ancientred.starminer.basics.dummy.DummyRotatedWorld;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityBlockRotator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDoor;
/*     */ import net.minecraft.block.BlockDoublePlant;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemReed;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemBlockForRotator extends ItemBlock {
/*     */   public ItemBlockForRotator(Block block) {
/*  35 */     super(block);
/*     */   }
/*     */   
/*     */   public boolean func_77648_a(ItemStack itemStack, EntityPlayer player, World world, int parX, int parY, int parZ, int side, float hitX, float hitY, float hitZ) {
/*  40 */     Block currentBlock = world.func_147439_a(parX, parY, parZ);
/*  42 */     if (currentBlock == Blocks.field_150431_aC && (world.func_72805_g(parX, parY, parZ) & 0x7) < 1) {
/*  44 */       side = 1;
/*  46 */     } else if (currentBlock != Blocks.field_150395_bd && currentBlock != Blocks.field_150329_H && currentBlock != Blocks.field_150330_I && !currentBlock.isReplaceable((IBlockAccess)world, parX, parY, parZ)) {
/*  48 */       if (side == 0)
/*  50 */         parY--; 
/*  53 */       if (side == 1)
/*  55 */         parY++; 
/*  58 */       if (side == 2)
/*  60 */         parZ--; 
/*  63 */       if (side == 3)
/*  65 */         parZ++; 
/*  68 */       if (side == 4)
/*  70 */         parX--; 
/*  73 */       if (side == 5)
/*  75 */         parX++; 
/*     */     } 
/*  79 */     if (itemStack.field_77994_a == 0)
/*  81 */       return false; 
/*  83 */     if (!player.func_82247_a(parX, parY, parZ, side, itemStack))
/*  85 */       return false; 
/*  87 */     if (parY == 255 && this.field_150939_a.func_149688_o().func_76220_a())
/*  89 */       return false; 
/*  91 */     if (world.func_147472_a(this.field_150939_a, parX, parY, parZ, false, side, (Entity)player, itemStack)) {
/*  93 */       GravityDirection gDir = Gravity.getGravityDirection((Entity)player);
/*  95 */       ItemStack[] mainInv = player.field_71071_by.field_70462_a;
/*  97 */       Block blockToInclude = null;
/*  98 */       ItemStack targetStack = null;
/*     */       int slotNum;
/* 100 */       for (slotNum = 0; slotNum < 9; slotNum++) {
/*     */         Item item;
/* 101 */         if (mainInv[slotNum] != null && (item = mainInv[slotNum].func_77973_b()) != null) {
/* 102 */           blockToInclude = convertItemToBlock(item);
/* 104 */           if (blockToInclude != null && blockToInclude != Blocks.field_150350_a && !blockToInclude.hasTileEntity(0) && !(blockToInclude instanceof net.minecraft.block.ITileEntityProvider) && !(blockToInclude instanceof BlockRotator) && (!Config.enableFakeRotatorOnlyVannilaBlock || isVannilaBlock(blockToInclude))) {
/* 108 */             targetStack = mainInv[slotNum];
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 114 */       if (targetStack == null)
/* 114 */         return false; 
/* 116 */       int itemMeta = targetStack.func_77960_j();
/* 119 */       int convSide = gDir.forgeSideRot[side];
/* 121 */       GravityDirection dirOpposite = GravityDirection.turnWayForNormal(gDir);
/* 122 */       float[] conv = new float[3];
/* 123 */       conv = dirOpposite.rotateXYZAt(conv, hitX, hitY, hitZ, 0.5F, 0.5F, 0.5F);
/* 127 */       World dummy = DummyRotatedWorld.get().wrapp(world, gDir, parX, parY, parZ);
/* 128 */       int meta = blockToInclude.func_149660_a(dummy, parX, parY, parZ, convSide, conv[0], conv[1], conv[2], itemMeta);
/* 131 */       Vec3 playerLookVec = player.func_70040_Z();
/* 132 */       dirOpposite.rotateVec3(playerLookVec);
/* 133 */       float rotationPitchSaved = player.field_70125_A;
/* 134 */       float rotationYawSaved = player.field_70177_z;
/*     */       try {
/* 136 */         double pitch = -Math.asin(playerLookVec.field_72448_b) * 57.29577951308232D;
/* 137 */         double yaw = -90.0D + Math.atan2(playerLookVec.field_72449_c, playerLookVec.field_72450_a) * 180.0D / Math.PI;
/* 138 */         player.field_70125_A = (float)pitch;
/* 139 */         player.field_70177_z = (float)yaw;
/* 141 */         Block blockRotToSet = SMModContainer.BlockRotatorBlock;
/* 150 */         if (placeBlockAtEx(gDir, blockToInclude, blockRotToSet, itemStack, targetStack, player, world, dummy, parX, parY, parZ, side, hitX, hitY, hitZ, meta)) {
/* 152 */           world.func_72908_a((double)((float)parX + 0.5F), (double)((float)parY + 0.5F), (double)((float)parZ + 0.5F), this.field_150939_a.field_149762_H.func_150496_b(), (this.field_150939_a.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.field_150939_a.field_149762_H.func_150494_d() * 0.8F);
/* 153 */           itemStack.field_77994_a--;
/* 155 */           if (!player.field_71075_bZ.field_75098_d && --targetStack.field_77994_a == 0)
/* 157 */             player.field_71071_by.func_70299_a(slotNum, null); 
/*     */         } 
/*     */       } catch (Exception ex) {
/* 160 */         ex.printStackTrace();
/*     */       } finally {
/* 161 */         player.field_70125_A = rotationPitchSaved;
/* 162 */         player.field_70177_z = rotationYawSaved;
/*     */       } 
/* 165 */       return true;
/*     */     } 
/* 169 */     return false;
/*     */   }
/*     */   
/*     */   private boolean placeBlockAtEx(GravityDirection gDir, Block blockToInclude, Block blockRotToSet, ItemStack stack, ItemStack targetStack, EntityPlayer player, World world, World dummy, int parX, int parY, int parZ, int side, float hitX, float hitY, float hitZ, int metadata) {
/* 175 */     if (blockToInclude instanceof BlockDoor)
/* 176 */       return placeDoorAtEx(gDir, (BlockDoor)blockToInclude, blockRotToSet, stack, targetStack, player, world, dummy, parX, parY, parZ, side, hitX, hitY, hitZ, metadata); 
/* 178 */     if (blockToInclude instanceof BlockDoublePlant)
/* 179 */       return placeBlockDoublePlantAtEx(gDir, (BlockDoublePlant)blockToInclude, blockRotToSet, stack, targetStack, player, world, dummy, parX, parY, parZ, side, hitX, hitY, hitZ, metadata); 
/* 183 */     if (!world.func_147465_d(parX, parY, parZ, blockRotToSet, metadata, 3))
/* 186 */       return false; 
/* 189 */     if (world.func_147439_a(parX, parY, parZ) == blockRotToSet) {
/* 192 */       blockToInclude.func_149689_a(dummy, parX, parY, parZ, (EntityLivingBase)player, stack);
/* 193 */       blockToInclude.func_149714_e(dummy, parX, parY, parZ, metadata);
/*     */     } 
/* 197 */     TileEntityBlockRotator te = BlockRotator.getTileEntityBlockRotator((IBlockAccess)world, parX, parY, parZ);
/* 198 */     te.setStoredBlock(blockToInclude);
/* 199 */     te.setStoredItem(targetStack.func_77973_b());
/* 200 */     te.setItemMetadata(targetStack.func_77960_j());
/* 202 */     te.setGravityDirection(gDir);
/* 203 */     te.isSubBlock = false;
/* 204 */     te.relatedBlockX = parX;
/* 205 */     te.relatedBlockY = parY;
/* 206 */     te.relatedBlockZ = parZ;
/* 208 */     return true;
/*     */   }
/*     */   
/*     */   private boolean placeBlockDoublePlantAtEx(GravityDirection gDir, BlockDoublePlant blockToInclude, Block blockRotToSet, ItemStack stack, ItemStack targetStack, EntityPlayer player, World world, World dummy, int parX, int parY, int parZ, int side, float hitX, float hitY, float hitZ, int metadata) {
/* 213 */     int l = ((MathHelper.func_76128_c((double)(player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3) + 2) % 4;
/* 216 */     int[] rotatedSubPos = ((DummyRotatedWorld)dummy).rotateOnCurrentState(parX, parY + 1, parZ);
/* 217 */     if (!world.func_147437_c(rotatedSubPos[0], rotatedSubPos[1], rotatedSubPos[2]))
/* 219 */       return false; 
/* 223 */     world.func_147465_d(parX, parY, parZ, blockRotToSet, metadata, 3);
/* 224 */     world.func_147465_d(rotatedSubPos[0], rotatedSubPos[1], rotatedSubPos[2], blockRotToSet, 0x8 | l, 2);
/* 227 */     TileEntityBlockRotator te = BlockRotator.getTileEntityBlockRotator((IBlockAccess)world, parX, parY, parZ);
/* 228 */     te.setStoredBlock((Block)blockToInclude);
/* 229 */     te.setStoredItem(targetStack.func_77973_b());
/* 230 */     te.setItemMetadata(targetStack.func_77960_j());
/* 232 */     te.setGravityDirection(gDir);
/* 233 */     te.isSubBlock = false;
/* 234 */     te.relatedBlockX = rotatedSubPos[0];
/* 235 */     te.relatedBlockY = rotatedSubPos[1];
/* 236 */     te.relatedBlockZ = rotatedSubPos[2];
/* 239 */     te = BlockRotator.getTileEntityBlockRotator((IBlockAccess)world, rotatedSubPos[0], rotatedSubPos[1], rotatedSubPos[2]);
/* 240 */     te.setStoredBlock((Block)blockToInclude);
/* 241 */     te.setStoredItem(targetStack.func_77973_b());
/* 242 */     te.setItemMetadata(targetStack.func_77960_j());
/* 244 */     te.setGravityDirection(gDir);
/* 245 */     te.isSubBlock = true;
/* 246 */     te.relatedBlockX = parX;
/* 247 */     te.relatedBlockY = parY;
/* 248 */     te.relatedBlockZ = parZ;
/* 250 */     return true;
/*     */   }
/*     */   
/*     */   private boolean placeDoorAtEx(GravityDirection gDir, BlockDoor blockToInclude, Block blockRotToSet, ItemStack stack, ItemStack targetStack, EntityPlayer player, World world, World dummy, int parX, int parY, int parZ, int side, float hitX, float hitY, float hitZ, int metadata) {
/* 256 */     int direction4Way = MathHelper.func_76128_c((double)((player.field_70177_z + 180.0F) * 4.0F / 360.0F) - 0.5D) & 0x3;
/* 257 */     byte fixX = 0;
/* 258 */     byte fixZ = 0;
/* 260 */     if (direction4Way == 0)
/* 262 */       fixZ = 1; 
/* 265 */     if (direction4Way == 1)
/* 267 */       fixX = -1; 
/* 270 */     if (direction4Way == 2)
/* 272 */       fixZ = -1; 
/* 275 */     if (direction4Way == 3)
/* 277 */       fixX = 1; 
/* 280 */     int i1 = (dummy.func_147439_a(parX - fixX, parY, parZ - fixZ).func_149721_r() ? 1 : 0) + (dummy.func_147439_a(parX - fixX, parY + 1, parZ - fixZ).func_149721_r() ? 1 : 0);
/* 281 */     int j1 = (dummy.func_147439_a(parX + fixX, parY, parZ + fixZ).func_149721_r() ? 1 : 0) + (dummy.func_147439_a(parX + fixX, parY + 1, parZ + fixZ).func_149721_r() ? 1 : 0);
/* 282 */     boolean flag = (dummy.func_147439_a(parX - fixX, parY, parZ - fixZ) == blockToInclude || dummy.func_147439_a(parX - fixX, parY + 1, parZ - fixZ) == blockToInclude);
/* 283 */     boolean flag1 = (dummy.func_147439_a(parX + fixX, parY, parZ + fixZ) == blockToInclude || dummy.func_147439_a(parX + fixX, parY + 1, parZ + fixZ) == blockToInclude);
/* 284 */     boolean isFlipped = false;
/* 286 */     if (flag && !flag1) {
/* 288 */       isFlipped = true;
/* 290 */     } else if (j1 > i1) {
/* 292 */       isFlipped = true;
/*     */     } 
/* 296 */     int[] rotatedHeadPos = ((DummyRotatedWorld)dummy).rotateOnCurrentState(parX, parY + 1, parZ);
/* 297 */     if (!world.func_147437_c(rotatedHeadPos[0], rotatedHeadPos[1], rotatedHeadPos[2]))
/* 299 */       return false; 
/* 303 */     world.func_147465_d(parX, parY, parZ, blockRotToSet, direction4Way, 2);
/* 304 */     world.func_147465_d(rotatedHeadPos[0], rotatedHeadPos[1], rotatedHeadPos[2], blockRotToSet, 0x8 | (isFlipped ? 1 : 0), 2);
/* 307 */     TileEntityBlockRotator te = BlockRotator.getTileEntityBlockRotator((IBlockAccess)world, parX, parY, parZ);
/* 308 */     te.setStoredBlock((Block)blockToInclude);
/* 309 */     te.setStoredItem(targetStack.func_77973_b());
/* 310 */     te.setItemMetadata(targetStack.func_77960_j());
/* 313 */     te.setGravityDirection(gDir);
/* 314 */     te.isSubBlock = false;
/* 315 */     te.relatedBlockX = rotatedHeadPos[0];
/* 316 */     te.relatedBlockY = rotatedHeadPos[1];
/* 317 */     te.relatedBlockZ = rotatedHeadPos[2];
/* 320 */     te = BlockRotator.getTileEntityBlockRotator((IBlockAccess)world, rotatedHeadPos[0], rotatedHeadPos[1], rotatedHeadPos[2]);
/* 321 */     te.setStoredBlock((Block)blockToInclude);
/* 322 */     te.setStoredItem(targetStack.func_77973_b());
/* 323 */     te.setItemMetadata(targetStack.func_77960_j());
/* 325 */     te.setGravityDirection(gDir);
/* 326 */     te.isSubBlock = true;
/* 327 */     te.relatedBlockX = parX;
/* 328 */     te.relatedBlockY = parY;
/* 329 */     te.relatedBlockZ = parZ;
/* 332 */     world.func_147459_d(parX, parY, parZ, blockRotToSet);
/* 333 */     world.func_147459_d(rotatedHeadPos[0], rotatedHeadPos[1], rotatedHeadPos[2], blockRotToSet);
/* 335 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isVannilaBlock(Block block) {
/* 339 */     return block.getClass().getName().startsWith("net.minecraft");
/*     */   }
/*     */   
/*     */   private Block convertItemToBlock(Item item) {
/* 343 */     Block block = null;
/* 344 */     if (item instanceof ItemReed) {
/* 346 */       block = SMReflectionHelper.getField_150935_a((ItemReed)item);
/* 347 */     } else if (item instanceof ItemBlock) {
/* 349 */       block = Block.func_149634_a(item);
/* 350 */     } else if (item instanceof net.minecraft.item.ItemDoor) {
/* 352 */       if (item == Items.field_151135_aq) {
/* 353 */         block = Blocks.field_150466_ao;
/* 354 */       } else if (item == Items.field_151139_aw) {
/* 355 */         block = Blocks.field_150454_av;
/*     */       } 
/*     */     } 
/* 358 */     return block;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */