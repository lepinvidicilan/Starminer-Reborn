/*     */ package jp.mc.ancientred.starminer.basics.common;
/*     */ 
/*     */ import com.google.common.collect.BiMap;
/*     */ import com.google.common.collect.HashBiMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class SMRotationHelper {
/*     */   private enum BlockType {
/*  64 */     LOG, DISPENSER, BED, RAIL, RAIL_POWERED, RAIL_ASCENDING, RAIL_CORNER, TORCH, STAIR, CHEST, SIGNPOST, DOOR, LEVER, BUTTON, REDSTONE_REPEATER, TRAPDOOR, MUSHROOM_CAP, MUSHROOM_CAP_CORNER, MUSHROOM_CAP_SIDE, VINE, SKULL, ANVIL;
/*     */   }
/*     */   
/*  88 */   private static final ForgeDirection[] UP_DOWN_AXES = new ForgeDirection[] { ForgeDirection.UP, ForgeDirection.DOWN };
/*     */   
/*  89 */   private static final Map<BlockType, BiMap<Integer, ForgeDirection>> MAPPINGS = new HashMap<BlockType, BiMap<Integer, ForgeDirection>>();
/*     */   
/*     */   public static ForgeDirection[] getValidVanillaBlockRotations(Block block) {
/*  93 */     return (block instanceof net.minecraft.block.BlockBed || block instanceof net.minecraft.block.BlockPumpkin || block instanceof net.minecraft.block.BlockFenceGate || block instanceof net.minecraft.block.BlockEndPortalFrame || block instanceof net.minecraft.block.BlockTripWireHook || block instanceof net.minecraft.block.BlockCocoa || block instanceof net.minecraft.block.BlockRailPowered || block instanceof net.minecraft.block.BlockRailDetector || block instanceof net.minecraft.block.BlockStairs || block instanceof net.minecraft.block.BlockChest || block instanceof net.minecraft.block.BlockEnderChest || block instanceof net.minecraft.block.BlockFurnace || block instanceof net.minecraft.block.BlockLadder || block == Blocks.field_150444_as || block == Blocks.field_150472_an || block instanceof net.minecraft.block.BlockDoor || block instanceof net.minecraft.block.BlockRail || block instanceof net.minecraft.block.BlockButton || block instanceof net.minecraft.block.BlockRedstoneRepeater || block instanceof net.minecraft.block.BlockRedstoneComparator || block instanceof net.minecraft.block.BlockTrapDoor || block instanceof net.minecraft.block.BlockHugeMushroom || block instanceof net.minecraft.block.BlockVine || block instanceof net.minecraft.block.BlockSkull || block instanceof net.minecraft.block.BlockAnvil) ? UP_DOWN_AXES : ForgeDirection.VALID_DIRECTIONS;
/*     */   }
/*     */   
/*     */   public static int rotateVanillaBlock(Block block, int orgMeta, ForgeDirection axis) {
/* 122 */     if (axis == ForgeDirection.UP || axis == ForgeDirection.DOWN) {
/* 124 */       if (block instanceof net.minecraft.block.BlockBed || block instanceof net.minecraft.block.BlockPumpkin || block instanceof net.minecraft.block.BlockFenceGate || block instanceof net.minecraft.block.BlockEndPortalFrame || block instanceof net.minecraft.block.BlockTripWireHook || block instanceof net.minecraft.block.BlockCocoa)
/* 126 */         return rotateBlock(orgMeta, axis, 3, BlockType.BED); 
/* 128 */       if (block instanceof net.minecraft.block.BlockRail)
/* 130 */         return rotateBlock(orgMeta, axis, 15, BlockType.RAIL); 
/* 132 */       if (block instanceof net.minecraft.block.BlockRailPowered || block instanceof net.minecraft.block.BlockRailDetector)
/* 134 */         return rotateBlock(orgMeta, axis, 7, BlockType.RAIL_POWERED); 
/* 136 */       if (block instanceof net.minecraft.block.BlockStairs)
/* 138 */         return rotateBlock(orgMeta, axis, 3, BlockType.STAIR); 
/* 140 */       if (block instanceof net.minecraft.block.BlockChest || block instanceof net.minecraft.block.BlockEnderChest || block instanceof net.minecraft.block.BlockFurnace || block instanceof net.minecraft.block.BlockLadder || block == Blocks.field_150444_as)
/* 142 */         return rotateBlock(orgMeta, axis, 7, BlockType.CHEST); 
/* 144 */       if (block == Blocks.field_150472_an)
/* 146 */         return rotateBlock(orgMeta, axis, 15, BlockType.SIGNPOST); 
/* 148 */       if (block instanceof net.minecraft.block.BlockDoor)
/* 150 */         return rotateBlock(orgMeta, axis, 3, BlockType.DOOR); 
/* 152 */       if (block instanceof net.minecraft.block.BlockButton)
/* 154 */         return rotateBlock(orgMeta, axis, 7, BlockType.BUTTON); 
/* 156 */       if (block instanceof net.minecraft.block.BlockRedstoneRepeater || block instanceof net.minecraft.block.BlockRedstoneComparator)
/* 158 */         return rotateBlock(orgMeta, axis, 3, BlockType.REDSTONE_REPEATER); 
/* 160 */       if (block instanceof net.minecraft.block.BlockTrapDoor)
/* 162 */         return rotateBlock(orgMeta, axis, 3, BlockType.TRAPDOOR); 
/* 164 */       if (block instanceof net.minecraft.block.BlockHugeMushroom)
/* 166 */         return rotateBlock(orgMeta, axis, 15, BlockType.MUSHROOM_CAP); 
/* 168 */       if (block instanceof net.minecraft.block.BlockVine)
/* 170 */         return rotateBlock(orgMeta, axis, 15, BlockType.VINE); 
/* 172 */       if (block instanceof net.minecraft.block.BlockSkull)
/* 174 */         return rotateBlock(orgMeta, axis, 7, BlockType.SKULL); 
/* 176 */       if (block instanceof net.minecraft.block.BlockAnvil)
/* 178 */         return rotateBlock(orgMeta, axis, 1, BlockType.ANVIL); 
/*     */     } 
/* 182 */     if (block instanceof net.minecraft.block.BlockLog)
/* 184 */       return rotateBlock(orgMeta, axis, 12, BlockType.LOG); 
/* 186 */     if (block instanceof net.minecraft.block.BlockDispenser || block instanceof net.minecraft.block.BlockPistonBase || block instanceof net.minecraft.block.BlockPistonExtension || block instanceof net.minecraft.block.BlockHopper)
/* 188 */       return rotateBlock(orgMeta, axis, 7, BlockType.DISPENSER); 
/* 190 */     if (block instanceof net.minecraft.block.BlockTorch)
/* 192 */       return rotateBlock(orgMeta, axis, 15, BlockType.TORCH); 
/* 194 */     if (block instanceof net.minecraft.block.BlockLever)
/* 196 */       return rotateBlock(orgMeta, axis, 7, BlockType.LEVER); 
/* 199 */     return -1;
/*     */   }
/*     */   
/*     */   private static int rotateBlock(int orgMeta, ForgeDirection axis, int mask, BlockType blockType) {
/* 204 */     int rotMeta = orgMeta;
/* 205 */     if (blockType == BlockType.DOOR && (rotMeta & 0x8) == 8)
/* 207 */       return -1; 
/* 209 */     int masked = rotMeta & (mask ^ 0xFFFFFFFF);
/* 210 */     int meta = rotateMetadata(axis, blockType, rotMeta & mask);
/* 211 */     if (meta == -1)
/* 213 */       return -1; 
/* 216 */     return meta & mask | masked;
/*     */   }
/*     */   
/*     */   private static int rotateMetadata(ForgeDirection axis, BlockType blockType, int meta) {
/* 221 */     if (blockType == BlockType.RAIL || blockType == BlockType.RAIL_POWERED) {
/* 223 */       if (meta == 0 || meta == 1)
/* 225 */         return (meta ^ 0xFFFFFFFF) & 0x1; 
/* 227 */       if (meta >= 2 && meta <= 5)
/* 229 */         blockType = BlockType.RAIL_ASCENDING; 
/* 231 */       if (meta >= 6 && meta <= 9 && blockType == BlockType.RAIL)
/* 233 */         blockType = BlockType.RAIL_CORNER; 
/*     */     } 
/* 236 */     if (blockType == BlockType.SIGNPOST)
/* 238 */       return (axis == ForgeDirection.UP) ? ((meta + 4) % 16) : ((meta + 12) % 16); 
/* 240 */     if (blockType == BlockType.LEVER && (axis == ForgeDirection.UP || axis == ForgeDirection.DOWN))
/* 242 */       switch (meta) {
/*     */         case 5:
/* 245 */           return 6;
/*     */         case 6:
/* 247 */           return 5;
/*     */         case 7:
/* 249 */           return 0;
/*     */         case 0:
/* 251 */           return 7;
/*     */       }  
/* 254 */     if (blockType == BlockType.MUSHROOM_CAP)
/* 256 */       if (meta % 2 == 0) {
/* 258 */         blockType = BlockType.MUSHROOM_CAP_SIDE;
/*     */       } else {
/* 262 */         blockType = BlockType.MUSHROOM_CAP_CORNER;
/*     */       }  
/* 265 */     if (blockType == BlockType.VINE)
/* 267 */       return meta << 1 | (meta & 0x8) >> 3; 
/* 270 */     ForgeDirection orientation = metadataToDirection(blockType, meta);
/* 271 */     ForgeDirection rotated = orientation.getRotation(axis);
/* 272 */     return directionToMetadata(blockType, rotated);
/*     */   }
/*     */   
/*     */   private static ForgeDirection metadataToDirection(BlockType blockType, int meta) {
/* 277 */     if (blockType == BlockType.LEVER)
/* 279 */       if (meta == 6) {
/* 281 */         meta = 5;
/* 283 */       } else if (meta == 0) {
/* 285 */         meta = 7;
/*     */       }  
/* 289 */     if (MAPPINGS.containsKey(blockType)) {
/* 291 */       BiMap<Integer, ForgeDirection> biMap = MAPPINGS.get(blockType);
/* 292 */       if (biMap.containsKey(Integer.valueOf(meta)))
/* 294 */         return (ForgeDirection)biMap.get(Integer.valueOf(meta)); 
/*     */     } 
/* 298 */     if (blockType == BlockType.TORCH)
/* 300 */       return ForgeDirection.getOrientation(6 - meta); 
/* 302 */     if (blockType == BlockType.STAIR)
/* 304 */       return ForgeDirection.getOrientation(5 - meta); 
/* 306 */     if (blockType == BlockType.CHEST || blockType == BlockType.DISPENSER || blockType == BlockType.SKULL)
/* 308 */       return ForgeDirection.getOrientation(meta); 
/* 310 */     if (blockType == BlockType.BUTTON)
/* 312 */       return ForgeDirection.getOrientation(6 - meta); 
/* 314 */     if (blockType == BlockType.TRAPDOOR)
/* 316 */       return ForgeDirection.getOrientation(meta + 2).getOpposite(); 
/* 319 */     return ForgeDirection.UNKNOWN;
/*     */   }
/*     */   
/*     */   private static int directionToMetadata(BlockType blockType, ForgeDirection direction) {
/* 324 */     if ((blockType == BlockType.LOG || blockType == BlockType.ANVIL) && direction.offsetX + direction.offsetY + direction.offsetZ < 0)
/* 326 */       direction = direction.getOpposite(); 
/* 329 */     if (MAPPINGS.containsKey(blockType)) {
/* 331 */       BiMap<ForgeDirection, Integer> biMap = MAPPINGS.get(blockType).inverse();
/* 332 */       if (biMap.containsKey(direction))
/* 334 */         return (Integer)biMap.get(direction); 
/*     */     } 
/* 338 */     if (blockType == BlockType.TORCH)
/* 340 */       if (direction.ordinal() >= 1)
/* 342 */         return 6 - direction.ordinal();  
/* 345 */     if (blockType == BlockType.STAIR)
/* 347 */       return 5 - direction.ordinal(); 
/* 349 */     if (blockType == BlockType.CHEST || blockType == BlockType.DISPENSER || blockType == BlockType.SKULL)
/* 351 */       return direction.ordinal(); 
/* 353 */     if (blockType == BlockType.BUTTON)
/* 355 */       if (direction.ordinal() >= 2)
/* 357 */         return 6 - direction.ordinal();  
/* 360 */     if (blockType == BlockType.TRAPDOOR)
/* 362 */       return direction.getOpposite().ordinal() - 2; 
/* 365 */     return -1;
/*     */   }
/*     */   
/*     */   static {
/* 372 */     HashBiMap hashBiMap = HashBiMap.create(3);
/* 373 */     hashBiMap.put(Integer.valueOf(0), ForgeDirection.UP);
/* 374 */     hashBiMap.put(Integer.valueOf(4), ForgeDirection.EAST);
/* 375 */     hashBiMap.put(Integer.valueOf(8), ForgeDirection.SOUTH);
/* 376 */     MAPPINGS.put(BlockType.LOG, hashBiMap);
/* 378 */     hashBiMap = HashBiMap.create(4);
/* 379 */     hashBiMap.put(Integer.valueOf(0), ForgeDirection.SOUTH);
/* 380 */     hashBiMap.put(Integer.valueOf(1), ForgeDirection.WEST);
/* 381 */     hashBiMap.put(Integer.valueOf(2), ForgeDirection.NORTH);
/* 382 */     hashBiMap.put(Integer.valueOf(3), ForgeDirection.EAST);
/* 383 */     MAPPINGS.put(BlockType.BED, hashBiMap);
/* 385 */     hashBiMap = HashBiMap.create(4);
/* 386 */     hashBiMap.put(Integer.valueOf(2), ForgeDirection.EAST);
/* 387 */     hashBiMap.put(Integer.valueOf(3), ForgeDirection.WEST);
/* 388 */     hashBiMap.put(Integer.valueOf(4), ForgeDirection.NORTH);
/* 389 */     hashBiMap.put(Integer.valueOf(5), ForgeDirection.SOUTH);
/* 390 */     MAPPINGS.put(BlockType.RAIL_ASCENDING, hashBiMap);
/* 392 */     hashBiMap = HashBiMap.create(4);
/* 393 */     hashBiMap.put(Integer.valueOf(6), ForgeDirection.WEST);
/* 394 */     hashBiMap.put(Integer.valueOf(7), ForgeDirection.NORTH);
/* 395 */     hashBiMap.put(Integer.valueOf(8), ForgeDirection.EAST);
/* 396 */     hashBiMap.put(Integer.valueOf(9), ForgeDirection.SOUTH);
/* 397 */     MAPPINGS.put(BlockType.RAIL_CORNER, hashBiMap);
/* 399 */     hashBiMap = HashBiMap.create(6);
/* 400 */     hashBiMap.put(Integer.valueOf(1), ForgeDirection.EAST);
/* 401 */     hashBiMap.put(Integer.valueOf(2), ForgeDirection.WEST);
/* 402 */     hashBiMap.put(Integer.valueOf(3), ForgeDirection.SOUTH);
/* 403 */     hashBiMap.put(Integer.valueOf(4), ForgeDirection.NORTH);
/* 404 */     hashBiMap.put(Integer.valueOf(5), ForgeDirection.UP);
/* 405 */     hashBiMap.put(Integer.valueOf(7), ForgeDirection.DOWN);
/* 406 */     MAPPINGS.put(BlockType.LEVER, hashBiMap);
/* 408 */     hashBiMap = HashBiMap.create(4);
/* 409 */     hashBiMap.put(Integer.valueOf(0), ForgeDirection.WEST);
/* 410 */     hashBiMap.put(Integer.valueOf(1), ForgeDirection.NORTH);
/* 411 */     hashBiMap.put(Integer.valueOf(2), ForgeDirection.EAST);
/* 412 */     hashBiMap.put(Integer.valueOf(3), ForgeDirection.SOUTH);
/* 413 */     MAPPINGS.put(BlockType.DOOR, hashBiMap);
/* 415 */     hashBiMap = HashBiMap.create(4);
/* 416 */     hashBiMap.put(Integer.valueOf(0), ForgeDirection.NORTH);
/* 417 */     hashBiMap.put(Integer.valueOf(1), ForgeDirection.EAST);
/* 418 */     hashBiMap.put(Integer.valueOf(2), ForgeDirection.SOUTH);
/* 419 */     hashBiMap.put(Integer.valueOf(3), ForgeDirection.WEST);
/* 420 */     MAPPINGS.put(BlockType.REDSTONE_REPEATER, hashBiMap);
/* 422 */     hashBiMap = HashBiMap.create(4);
/* 423 */     hashBiMap.put(Integer.valueOf(1), ForgeDirection.EAST);
/* 424 */     hashBiMap.put(Integer.valueOf(3), ForgeDirection.SOUTH);
/* 425 */     hashBiMap.put(Integer.valueOf(7), ForgeDirection.NORTH);
/* 426 */     hashBiMap.put(Integer.valueOf(9), ForgeDirection.WEST);
/* 427 */     MAPPINGS.put(BlockType.MUSHROOM_CAP_CORNER, hashBiMap);
/* 429 */     hashBiMap = HashBiMap.create(4);
/* 430 */     hashBiMap.put(Integer.valueOf(2), ForgeDirection.NORTH);
/* 431 */     hashBiMap.put(Integer.valueOf(4), ForgeDirection.WEST);
/* 432 */     hashBiMap.put(Integer.valueOf(6), ForgeDirection.EAST);
/* 433 */     hashBiMap.put(Integer.valueOf(8), ForgeDirection.SOUTH);
/* 434 */     MAPPINGS.put(BlockType.MUSHROOM_CAP_SIDE, hashBiMap);
/* 436 */     hashBiMap = HashBiMap.create(2);
/* 437 */     hashBiMap.put(Integer.valueOf(0), ForgeDirection.SOUTH);
/* 438 */     hashBiMap.put(Integer.valueOf(1), ForgeDirection.EAST);
/* 439 */     MAPPINGS.put(BlockType.ANVIL, hashBiMap);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */