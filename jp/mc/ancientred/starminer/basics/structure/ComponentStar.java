/*     */ package jp.mc.ancientred.starminer.basics.structure;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.WorldGenForestG;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.WorldGenSavannaTreeG;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.WorldGenTaiga2G;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.WorldGenTreesG;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityGravityGenerator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ import net.minecraft.world.gen.structure.StructureComponent;
/*     */ 
/*     */ public class ComponentStar extends StructureComponent {
/*     */   private int starRad;
/*     */   
/*     */   private int ringRad;
/*     */   
/*     */   private int centerY;
/*     */   
/*     */   private int starSeed;
/*     */   
/*  34 */   private Random rand = new Random();
/*     */   
/*  37 */   private WorldGenForestG wordGenForestG = new WorldGenForestG(false);
/*     */   
/*  38 */   private WorldGenSavannaTreeG worldGenSavannaTreeG = new WorldGenSavannaTreeG(false);
/*     */   
/*  39 */   private WorldGenTreesG worldGenTreesG = new WorldGenTreesG(false);
/*     */   
/*  40 */   private WorldGenTaiga2G worldGenTaiga2G = new WorldGenTaiga2G(false);
/*     */   
/*     */   private class BlockMeta {
/*     */     public Block block;
/*     */     
/*     */     public int meta;
/*     */     
/*     */     public BlockMeta(Block block, int meta) {
/*  47 */       this.block = block;
/*  47 */       this.meta = meta;
/*     */     }
/*     */   }
/*     */   
/*     */   private class TreeSet {
/*     */     public int meta;
/*     */     
/*     */     public boolean asNewTrees;
/*     */     
/*     */     public TreeSet(int meta, boolean asNewTrees) {
/*  54 */       this.meta = meta;
/*  54 */       this.asNewTrees = asNewTrees;
/*     */     }
/*     */   }
/*     */   
/*  57 */   private BlockMeta GRASSSET_GRASS = new BlockMeta(SMModContainer.TallGrassGravitizedBlock, 1);
/*     */   
/*  58 */   private BlockMeta GRASSSET_FERN = new BlockMeta(SMModContainer.TallGrassGravitizedBlock, 2);
/*     */   
/*  60 */   private BlockMeta GRASSSET_PLANT_DANDELION = new BlockMeta(SMModContainer.PlantYelGravitizedBlock, 0);
/*     */   
/*  61 */   private BlockMeta GRASSSET_PLANT_POPPY = new BlockMeta(SMModContainer.PlantRedGravitizedBlock, 0);
/*     */   
/*  62 */   private BlockMeta GRASSSET_PLANT_BLUEORCHID = new BlockMeta(SMModContainer.PlantRedGravitizedBlock, 1);
/*     */   
/*  63 */   private BlockMeta GRASSSET_PLANT_ALLIUM = new BlockMeta(SMModContainer.PlantRedGravitizedBlock, 2);
/*     */   
/*  64 */   private BlockMeta GRASSSET_PLANT_HOUSTONIA = new BlockMeta(SMModContainer.PlantRedGravitizedBlock, 3);
/*     */   
/*  65 */   private BlockMeta GRASSSET_PLANT_TULIPRED = new BlockMeta(SMModContainer.PlantRedGravitizedBlock, 4);
/*     */   
/*  66 */   private BlockMeta GRASSSET_PLANT_TULIPORANGE = new BlockMeta(SMModContainer.PlantRedGravitizedBlock, 5);
/*     */   
/*  67 */   private BlockMeta GRASSSET_PLANT_TULIPWHITE = new BlockMeta(SMModContainer.PlantRedGravitizedBlock, 6);
/*     */   
/*  68 */   private BlockMeta GRASSSET_PLANT_TULIPPINK = new BlockMeta(SMModContainer.PlantRedGravitizedBlock, 7);
/*     */   
/*  69 */   private BlockMeta GRASSSET_PLANT_OXEYEDAISY = new BlockMeta(SMModContainer.PlantRedGravitizedBlock, 8);
/*     */   
/*  71 */   private TreeSet TREESET_OAK = new TreeSet(0, false);
/*     */   
/*  72 */   private TreeSet TREESET_SPRUCE = new TreeSet(1, false);
/*     */   
/*  73 */   private TreeSet TREESET_BIRCH = new TreeSet(2, false);
/*     */   
/*  74 */   private TreeSet TREESET_JUNGLE = new TreeSet(3, false);
/*     */   
/*  75 */   private TreeSet TREESET_ACACIA = new TreeSet(0, true);
/*     */   
/*  76 */   private TreeSet TREESET_ROOFED_OAK = new TreeSet(1, true);
/*     */   
/*  84 */   private BlockMeta GRASSSET_GROWSTONE = new BlockMeta(Blocks.field_150426_aN, 0);
/*     */   
/*  85 */   private BlockMeta[][] GRASSSET_LST = new BlockMeta[][] { new BlockMeta[] { this.GRASSSET_GRASS, this.GRASSSET_GRASS, this.GRASSSET_GRASS, this.GRASSSET_GRASS, this.GRASSSET_GRASS, this.GRASSSET_PLANT_DANDELION, this.GRASSSET_PLANT_POPPY }, new BlockMeta[] { 
/*  85 */         this.GRASSSET_GRASS, this.GRASSSET_GRASS, this.GRASSSET_GRASS, this.GRASSSET_GRASS, this.GRASSSET_GRASS, this.GRASSSET_PLANT_DANDELION, this.GRASSSET_PLANT_POPPY, this.GRASSSET_PLANT_BLUEORCHID, this.GRASSSET_PLANT_ALLIUM, this.GRASSSET_PLANT_HOUSTONIA, 
/*  85 */         this.GRASSSET_PLANT_TULIPRED, this.GRASSSET_PLANT_TULIPORANGE, this.GRASSSET_PLANT_TULIPWHITE, this.GRASSSET_PLANT_TULIPPINK, this.GRASSSET_PLANT_OXEYEDAISY }, new BlockMeta[] { this.GRASSSET_FERN }, new BlockMeta[] { this.GRASSSET_GRASS } };
/*     */   
/* 101 */   private TreeSet[][] TREESET_LST = new TreeSet[][] { new TreeSet[] { this.TREESET_OAK }, new TreeSet[] { this.TREESET_SPRUCE }, new TreeSet[] { this.TREESET_BIRCH }, new TreeSet[] { this.TREESET_JUNGLE }, new TreeSet[] { this.TREESET_ACACIA }, new TreeSet[] { this.TREESET_ROOFED_OAK }, new TreeSet[] { this.TREESET_OAK, this.TREESET_SPRUCE, this.TREESET_BIRCH, this.TREESET_JUNGLE, this.TREESET_ACACIA, this.TREESET_ROOFED_OAK } };
/*     */   
/*     */   private enum StarBiomeType {
/* 113 */     grassStar(SMModContainer.DirtGrassExBlock, 5, 50, 0, 0, 0),
/* 114 */     grassStarRich(SMModContainer.DirtGrassExBlock, 2, 40, 1, 0, 0),
/* 115 */     forestStar(SMModContainer.DirtGrassExBlock, 3, 15, 0, 20, 4),
/* 116 */     stoneStar(Blocks.field_150348_b, 1, 10, 2, 0, 0),
/* 117 */     mossyStar(Blocks.field_150341_Y, 1, 10, 2, 4, 3),
/* 118 */     snowStar(Blocks.field_150433_aE, 1, 20, 3, 4, 2),
/* 119 */     iceStar(Blocks.field_150403_cj, 1, 0, 0, 0, 0),
/* 120 */     spongeStar(Blocks.field_150360_v, 1, 0, 0, 0, 0),
/* 121 */     clayStar(Blocks.field_150435_aG, 1, 10, 3, 10, 0),
/* 122 */     netherStar(Blocks.field_150424_aL, 1, 0, 0, 0, 0),
/* 123 */     sandStoneStar(Blocks.field_150322_A, 1, 0, 0, 0, 0);
/*     */     
/*     */     public Block sufBlock;
/*     */     
/*     */     public int frequency;
/*     */     
/*     */     public int grassRate;
/*     */     
/*     */     public int grassSet;
/*     */     
/*     */     public int treeRate;
/*     */     
/*     */     public int treeSet;
/*     */     
/*     */     StarBiomeType(Block argSurfBlock, int argFrequency, int argGrassRate, int argGrassSet, int argTreeRate, int argTreeSet) {
/* 131 */       this.sufBlock = argSurfBlock;
/* 132 */       this.frequency = argFrequency;
/* 133 */       this.grassRate = argGrassRate;
/* 134 */       this.grassSet = argGrassSet;
/* 135 */       this.treeRate = argTreeRate;
/* 136 */       this.treeSet = argTreeSet;
/*     */     }
/*     */     
/*     */     public static StarBiomeType[] makeList(StarBiomeType[] gen) {
/* 139 */       ArrayList<StarBiomeType> list = new ArrayList<StarBiomeType>();
/* 140 */       for (StarBiomeType elem : values()) {
/* 141 */         for (int i = 0; i < elem.frequency; i++)
/* 142 */           list.add(elem); 
/*     */       } 
/* 145 */       return list.<StarBiomeType>toArray(gen);
/*     */     }
/*     */   }
/*     */   
/* 148 */   private static StarBiomeType[] STAR_BIOMES = new StarBiomeType[0];
/*     */   
/*     */   static {
/* 150 */     STAR_BIOMES = StarBiomeType.makeList(STAR_BIOMES);
/*     */   }
/*     */   
/* 154 */   public static Block[] BEDROCK_BLOCKS = new Block[] { Blocks.field_150346_d, Blocks.field_150322_A, Blocks.field_150347_e, Blocks.field_150348_b, Blocks.field_150424_aL, Blocks.field_150360_v, Blocks.field_150435_aG };
/*     */   
/* 163 */   public static Block[] ORE_BLOCKS = new Block[] { Blocks.field_150426_aN, Blocks.field_150365_q, Blocks.field_150366_p, Blocks.field_150450_ax, Blocks.field_150426_aN, Blocks.field_150369_x };
/*     */   
/* 170 */   public static Block[] ORE_BLOCKS_RARE = new Block[] { Blocks.field_150352_o, Blocks.field_150412_bA, Blocks.field_150482_ag, Blocks.field_150339_S, Blocks.field_150368_y };
/*     */   
/* 176 */   public static Block[] ORE_BLOCKS_S_RARE = new Block[] { Blocks.field_150340_R, Blocks.field_150484_ah, Blocks.field_150475_bE };
/*     */   
/*     */   public ComponentStar(int par1, Random par2Random, int par3, int par4, World world) {
/* 187 */     super(par1);
/* 189 */     this.starSeed = par2Random.nextInt();
/* 190 */     if (world.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider) {
/* 191 */       if (par2Random.nextInt(5) == 1) {
/* 193 */         this.starRad = 24 + par2Random.nextInt(12);
/* 194 */         this.ringRad = getExpandBoundRad();
/* 195 */         this.centerY = 77 + par2Random.nextInt(50);
/*     */       } else {
/* 198 */         this.starRad = 24 + par2Random.nextInt(30);
/* 199 */         this.ringRad = 0;
/* 200 */         this.centerY = 57 + par2Random.nextInt(70);
/*     */       } 
/*     */     } else {
/* 204 */       this.starRad = 10 + par2Random.nextInt(12);
/* 205 */       this.ringRad = 0;
/* 206 */       this.centerY = 100 + par2Random.nextInt(80);
/*     */     } 
/* 208 */     int bound = this.starRad + 2 + getExpandBoundRad();
/* 209 */     this.field_74887_e = new StructureBoundingBox(par3 - bound, this.centerY - bound, par4 - bound, par3 + bound, this.centerY + bound, par4 + bound);
/*     */   }
/*     */   
/*     */   public void func_74861_a(StructureComponent par1StructureComponent, List par2List, Random par3Random) {}
/*     */   
/*     */   private int getExpandBoundRad() {
/* 222 */     return (int)((float)this.starRad * 1.2F);
/*     */   }
/*     */   
/*     */   public boolean func_74875_a(World par1World, Random par2Random, StructureBoundingBox chunkBoundingBox) {
/* 226 */     this.rand.setSeed((long)(this.starSeed + this.field_74887_e.field_78897_a + this.field_74887_e.field_78896_c));
/* 227 */     int cx = this.starRad + 2 + getExpandBoundRad() + this.field_74887_e.field_78897_a;
/* 228 */     int cy = this.centerY;
/* 229 */     int cz = this.starRad + 2 + getExpandBoundRad() + this.field_74887_e.field_78896_c;
/* 231 */     if (!(par1World.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider)) {
/* 233 */       Block surfaceBlockA = BEDROCK_BLOCKS[this.rand.nextInt(BEDROCK_BLOCKS.length)];
/* 235 */       int rad = this.starRad - 1;
/* 236 */       int xMin = chunkBoundingBox.field_78897_a;
/* 237 */       int xMax = chunkBoundingBox.field_78893_d;
/* 238 */       int yMin = cy - rad;
/* 239 */       int yMax = cy + rad;
/* 240 */       int zMin = chunkBoundingBox.field_78896_c;
/* 241 */       int zMax = chunkBoundingBox.field_78892_f;
/* 242 */       double radPow = (double)(rad * rad);
/* 243 */       double radPls1Pow = (double)((rad + 1) * (rad + 1));
/* 244 */       double radSub3Pow = (double)((rad - 3) * (rad - 3));
/* 245 */       double radSub4Pow = (double)((rad - 4) * (rad - 4));
/* 249 */       for (int x = xMin; x <= xMax; x++) {
/* 250 */         double xFar = (double)(x - cx);
/* 251 */         xFar *= xFar;
/* 252 */         if (xFar < radPow)
/* 254 */           for (int y = yMin; y <= yMax; y++) {
/* 255 */             if (y >= 0 && 255 >= y) {
/* 256 */               double yFar = (double)(y - cy);
/* 257 */               yFar *= yFar;
/* 258 */               double xyFar = xFar + yFar;
/* 259 */               if (xyFar < radPow)
/* 261 */                 for (int z = zMin; z <= zMax; z++) {
/* 262 */                   double zFar = (double)(z - cz);
/* 263 */                   zFar *= zFar;
/* 264 */                   double distancePow = xyFar + zFar;
/* 265 */                   if (distancePow < radPow)
/* 266 */                     if (distancePow > radSub3Pow) {
/* 268 */                       par1World.func_147465_d(x, y, z, surfaceBlockA, 0, 2);
/* 269 */                     } else if (distancePow > radSub4Pow) {
/* 271 */                       int randInt = this.rand.nextInt(700);
/* 272 */                       if (randInt < 1) {
/* 274 */                         par1World.func_147465_d(x, y, z, ORE_BLOCKS_S_RARE[this.rand.nextInt(ORE_BLOCKS_S_RARE.length)], 0, 2);
/* 275 */                       } else if (randInt < 8) {
/* 277 */                         par1World.func_147465_d(x, y, z, ORE_BLOCKS_RARE[this.rand.nextInt(ORE_BLOCKS_RARE.length)], 0, 2);
/* 278 */                       } else if (randInt < 80) {
/* 280 */                         par1World.func_147465_d(x, y, z, ORE_BLOCKS[this.rand.nextInt(ORE_BLOCKS.length)], 0, 2);
/*     */                       } else {
/* 283 */                         par1World.func_147465_d(x, y, z, surfaceBlockA, 0, 2);
/*     */                       } 
/* 285 */                     } else if (distancePow < 6.25D) {
/* 287 */                       par1World.func_147465_d(x, y, z, SMModContainer.InnerCoreBlock, 0, 2);
/* 288 */                     } else if (distancePow < 16.0D) {
/* 290 */                       par1World.func_147465_d(x, y, z, SMModContainer.OuterCoreBlock, 0, 2);
/*     */                     }  
/*     */                 }  
/*     */             } 
/*     */           }  
/*     */       } 
/*     */     } 
/* 299 */     if (chunkBoundingBox.func_78890_b(cx, cy, cz)) {
/* 300 */       func_151550_a(par1World, SMModContainer.GravityCoreBlock, 0, cx, cy, cz, chunkBoundingBox);
/* 301 */       TileEntityGravityGenerator tileEntityGravity = (TileEntityGravityGenerator)par1World.func_147438_o(cx, cy, cz);
/* 302 */       tileEntityGravity.starRad = (double)this.starRad;
/* 303 */       tileEntityGravity.gravityRange = (double)(this.starRad + 8);
/* 304 */       tileEntityGravity.resetWorkState();
/*     */     } 
/* 307 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox chunkBoundingBox, Block[] blocksData, byte[] blockMetas) {
/* 314 */     this.rand.setSeed((long)(this.starSeed + this.field_74887_e.field_78897_a + this.field_74887_e.field_78896_c));
/* 317 */     boolean makeDimples = this.rand.nextBoolean();
/* 319 */     StarBiomeType starBiome = STAR_BIOMES[this.rand.nextInt(STAR_BIOMES.length)];
/* 320 */     Block surfaceBlockB = BEDROCK_BLOCKS[this.rand.nextInt(BEDROCK_BLOCKS.length)];
/* 321 */     Block surfaceBlockA = starBiome.sufBlock;
/* 324 */     int cx = this.starRad + 2 + getExpandBoundRad() + this.field_74887_e.field_78897_a;
/* 325 */     int cy = this.centerY;
/* 326 */     int cz = this.starRad + 2 + getExpandBoundRad() + this.field_74887_e.field_78896_c;
/* 327 */     int rad = this.starRad;
/* 328 */     int xMin = chunkBoundingBox.field_78897_a;
/* 329 */     int xMax = chunkBoundingBox.field_78893_d;
/* 330 */     int yMin = cy - (rad + 2);
/* 331 */     int yMax = cy + rad + 2;
/* 332 */     int zMin = chunkBoundingBox.field_78896_c;
/* 333 */     int zMax = chunkBoundingBox.field_78892_f;
/* 334 */     double radPow = (double)(rad * rad);
/* 335 */     double radPls1Pow = (double)((rad + 1) * (rad + 1));
/* 336 */     double radPls3Pow = (double)((rad + 2) * (rad + 2));
/* 337 */     double radSub1Pow = (double)((rad - 1) * (rad - 1));
/* 338 */     double radSub3Pow = (double)((rad - 3) * (rad - 3));
/* 339 */     double radSub4Pow = (double)((rad - 4) * (rad - 4));
/* 344 */     boolean plantGrass = (starBiome.grassRate > 0);
/* 350 */     for (int x = xMin; x <= xMax; x++) {
/* 351 */       double xFar = (double)(x - cx);
/* 352 */       boolean xComp = (xFar < 0.0D);
/* 353 */       xFar *= xFar;
/* 355 */       if (xFar <= radPls3Pow)
/* 357 */         for (int y = yMin; y <= yMax; y++) {
/* 358 */           if (y >= 0 && 255 >= y) {
/* 359 */             double yFar = (double)(y - cy);
/* 360 */             boolean yComp = (yFar < 0.0D);
/* 361 */             yFar *= yFar;
/* 363 */             double xyFar = xFar + yFar;
/* 364 */             if (xyFar <= radPls3Pow)
/* 366 */               for (int z = zMin; z <= zMax; z++) {
/* 367 */                 double zFar = (double)(z - cz);
/* 368 */                 boolean zComp = (zFar < 0.0D);
/* 369 */                 zFar *= zFar;
/* 371 */                 double distancePow = xyFar + zFar;
/* 373 */                 if (distancePow < radPow) {
/* 375 */                   int targetIdIndex = y << 8 | z - zMin << 4 | x - xMin;
/* 378 */                   blocksData[targetIdIndex] = Blocks.field_150350_a;
/* 379 */                   blockMetas[targetIdIndex] = 0;
/* 381 */                   if (distancePow > radSub3Pow) {
/* 382 */                     if (distancePow >= radSub1Pow) {
/* 384 */                       blocksData[targetIdIndex] = surfaceBlockA;
/* 385 */                       blockMetas[targetIdIndex] = 0;
/*     */                     } else {
/* 388 */                       blocksData[targetIdIndex] = surfaceBlockB;
/* 389 */                       blockMetas[targetIdIndex] = 0;
/*     */                     } 
/* 391 */                   } else if (distancePow > radSub4Pow) {
/* 393 */                     int randInt = this.rand.nextInt(700);
/* 394 */                     if (randInt < 1) {
/* 396 */                       blocksData[targetIdIndex] = ORE_BLOCKS_S_RARE[this.rand.nextInt(ORE_BLOCKS_S_RARE.length)];
/* 397 */                       blockMetas[targetIdIndex] = 0;
/* 398 */                     } else if (randInt < 8) {
/* 400 */                       blocksData[targetIdIndex] = ORE_BLOCKS_RARE[this.rand.nextInt(ORE_BLOCKS_RARE.length)];
/* 401 */                       blockMetas[targetIdIndex] = 0;
/* 402 */                     } else if (randInt < 80) {
/* 404 */                       blocksData[targetIdIndex] = ORE_BLOCKS[this.rand.nextInt(ORE_BLOCKS.length)];
/* 405 */                       blockMetas[targetIdIndex] = 0;
/*     */                     } else {
/* 408 */                       blocksData[targetIdIndex] = surfaceBlockB;
/* 409 */                       blockMetas[targetIdIndex] = 0;
/*     */                     } 
/* 411 */                   } else if (distancePow < 6.25D) {
/* 413 */                     blocksData[targetIdIndex] = SMModContainer.InnerCoreBlock;
/* 414 */                     blockMetas[targetIdIndex] = 0;
/* 415 */                   } else if (distancePow < 16.0D) {
/* 417 */                     blocksData[targetIdIndex] = SMModContainer.OuterCoreBlock;
/* 418 */                     blockMetas[targetIdIndex] = 0;
/*     */                   } 
/* 420 */                 } else if (distancePow < radPls1Pow) {
/* 422 */                   if (plantGrass && this.rand.nextInt(100) <= starBiome.grassRate)
/* 424 */                     if (Math.abs(xFar - zFar) > radPow * 0.1D || yFar > radPow * 0.5D)
/* 427 */                       if (Math.abs(xFar - yFar) > radPow * 0.1D || zFar > radPow * 0.5D)
/* 430 */                         if (Math.abs(yFar - zFar) > radPow * 0.1D || xFar > radPow * 0.5D) {
/* 435 */                           if (xFar > zFar && xFar > yFar) {
/*     */                             double fixedFar;
/* 437 */                             if (xComp) {
/* 439 */                               fixedFar = (double)(x + 1 - cx);
/*     */                             } else {
/* 442 */                               fixedFar = (double)(x - 1 - cx);
/*     */                             } 
/* 444 */                             fixedFar *= fixedFar;
/* 445 */                             distancePow = fixedFar + yFar + zFar;
/* 446 */                           } else if (zFar >= xFar && zFar > yFar) {
/*     */                             double fixedFar;
/* 448 */                             if (zComp) {
/* 450 */                               fixedFar = (double)(z + 1 - cz);
/*     */                             } else {
/* 453 */                               fixedFar = (double)(z - 1 - cz);
/*     */                             } 
/* 455 */                             fixedFar *= fixedFar;
/* 456 */                             distancePow = xFar + yFar + fixedFar;
/*     */                           } else {
/*     */                             double fixedFar;
/* 459 */                             if (yComp) {
/* 461 */                               fixedFar = (double)(y + 1 - cy);
/*     */                             } else {
/* 464 */                               fixedFar = (double)(y - 1 - cy);
/*     */                             } 
/* 466 */                             fixedFar *= fixedFar;
/* 467 */                             distancePow = xFar + fixedFar + zFar;
/*     */                           } 
/* 469 */                           if (distancePow < radPow || distancePow >= radPls1Pow) {
/* 471 */                             int targetIdIndex = y << 8 | z - zMin << 4 | x - xMin;
/* 473 */                             BlockMeta[] grassSet = this.GRASSSET_LST[starBiome.grassSet];
/* 474 */                             BlockMeta grass = grassSet[this.rand.nextInt(grassSet.length)];
/* 475 */                             blocksData[targetIdIndex] = grass.block;
/* 476 */                             blockMetas[targetIdIndex] = (byte)grass.meta;
/*     */                           } 
/*     */                         }    
/* 479 */                 } else if (distancePow <= radPls3Pow) {
/* 480 */                   int targetIdIndex = y << 8 | z - zMin << 4 | x - xMin;
/* 481 */                   if (blocksData[targetIdIndex] == null) {
/*     */                     int meta;
/* 482 */                     if (xFar > zFar && xFar > yFar) {
/* 484 */                       if (xComp) {
/* 486 */                         meta = 3;
/*     */                       } else {
/* 489 */                         meta = 2;
/*     */                       } 
/* 491 */                     } else if (zFar >= xFar && zFar > yFar) {
/* 493 */                       if (zComp) {
/* 495 */                         meta = 5;
/*     */                       } else {
/* 498 */                         meta = 4;
/*     */                       } 
/* 502 */                     } else if (yComp) {
/* 504 */                       meta = 1;
/*     */                     } else {
/* 507 */                       meta = 0;
/*     */                     } 
/* 511 */                     blocksData[targetIdIndex] = Blocks.field_150350_a;
/* 512 */                     blockMetas[targetIdIndex] = (byte)(meta + 1);
/*     */                   } 
/*     */                 } 
/*     */               }  
/*     */           } 
/*     */         }  
/*     */     } 
/* 519 */     if (this.ringRad != 0)
/* 520 */       makeRing(par1World, par2Random, chunkBoundingBox, blocksData, blockMetas); 
/* 523 */     if (starBiome.treeRate > 0) {
/* 525 */       makeTrees(par1World, par2Random, chunkBoundingBox, blocksData, blockMetas, starBiome);
/* 526 */     } else if (makeDimples) {
/* 528 */       makeDimples(par1World, par2Random, chunkBoundingBox, blocksData, blockMetas, surfaceBlockA);
/*     */     } 
/* 531 */     return true;
/*     */   }
/*     */   
/*     */   private void makeRing(World par1World, Random par2Random, StructureBoundingBox chunkBoundingBox, Block[] blocksData, byte[] blockMetas) {
/* 538 */     this.rand.setSeed((long)this.starSeed);
/* 540 */     int ringRoatX = this.rand.nextInt(90);
/* 541 */     int ringRoatZ = this.rand.nextInt(90);
/* 543 */     int rad = this.starRad + getExpandBoundRad();
/* 544 */     int wa = (int)((float)this.starRad * 0.7F);
/* 545 */     int cx = this.starRad + 2 + getExpandBoundRad() + this.field_74887_e.field_78897_a;
/* 546 */     int cy = this.centerY;
/* 547 */     int cz = this.starRad + 2 + getExpandBoundRad() + this.field_74887_e.field_78896_c;
/* 549 */     int prevPosX = 0, posX = 0;
/* 550 */     int prevPosY = 0, posY = 0;
/* 551 */     int prevPosZ = 0, posZ = 0;
/* 558 */     byte blockColorToUse = 0;
/* 560 */     for (int colorCnt = 0, distance = rad - wa; distance <= rad; distance++, colorCnt++) {
/* 561 */       if ((colorCnt & 0x1) == 0)
/* 561 */         blockColorToUse = (byte)this.rand.nextInt(16); 
/* 562 */       for (int angle = 0; angle < 360; angle++) {
/* 563 */         double dXYZ0 = Math.cos((double)angle * Math.PI / 180.0D) * (double)distance;
/* 564 */         double dXYZ1 = 0.0D;
/* 565 */         double dXYZ2 = Math.sin((double)angle * Math.PI / 180.0D) * (double)distance;
/* 568 */         float f1 = MathHelper.func_76134_b((float)ringRoatX);
/* 569 */         float f2 = MathHelper.func_76126_a((float)ringRoatX);
/* 570 */         double d0 = dXYZ0;
/* 571 */         double d1 = dXYZ1 * (double)f1 + dXYZ2 * (double)f2;
/* 572 */         double d2 = dXYZ2 * (double)f1 - dXYZ1 * (double)f2;
/* 573 */         dXYZ0 = d0;
/* 574 */         dXYZ1 = d1;
/* 575 */         dXYZ2 = d2;
/* 578 */         f1 = MathHelper.func_76134_b((float)ringRoatZ);
/* 579 */         f2 = MathHelper.func_76126_a((float)ringRoatZ);
/* 580 */         d0 = dXYZ0 * (double)f1 + dXYZ1 * (double)f2;
/* 581 */         d1 = dXYZ1 * (double)f1 - dXYZ0 * (double)f2;
/* 582 */         d2 = dXYZ2;
/* 583 */         dXYZ0 = d0;
/* 584 */         dXYZ1 = d1;
/* 585 */         dXYZ2 = d2;
/* 587 */         posX = cx + (int)dXYZ0;
/* 588 */         posY = cy + (int)dXYZ1;
/* 589 */         posZ = cz + (int)dXYZ2;
/* 591 */         if ((prevPosX != posX || prevPosY != posY || prevPosZ != posZ) && 
/* 592 */           posY >= 0 && posY <= 255 && posX >= chunkBoundingBox.field_78897_a && posX <= chunkBoundingBox.field_78893_d && posZ >= chunkBoundingBox.field_78896_c && posZ <= chunkBoundingBox.field_78892_f) {
/* 597 */           int targetIdIndex = posY << 8 | posZ - chunkBoundingBox.field_78896_c << 4 | posX - chunkBoundingBox.field_78897_a;
/* 598 */           blocksData[targetIdIndex] = (Block)Blocks.field_150399_cn;
/* 599 */           blockMetas[targetIdIndex] = blockColorToUse;
/*     */         } 
/* 602 */         prevPosX = posX;
/* 603 */         prevPosY = posY;
/* 604 */         prevPosZ = posZ;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void makeTrees(World par1World, Random par2Random, StructureBoundingBox chunkBoundingBox, Block[] blocksData, byte[] blockMetas, StarBiomeType starBiome) {
/* 613 */     this.rand.setSeed((long)this.starSeed);
/* 614 */     TreeSet[] treeSets = this.TREESET_LST[this.rand.nextInt(this.TREESET_LST.length)];
/* 615 */     int cx = this.starRad + 2 + getExpandBoundRad() + this.field_74887_e.field_78897_a;
/* 616 */     int cy = this.centerY;
/* 617 */     int cz = this.starRad + 2 + getExpandBoundRad() + this.field_74887_e.field_78896_c;
/* 619 */     int posX = 0;
/* 620 */     int posY = 0;
/* 621 */     int posZ = 0;
/* 626 */     byte blockColorToUse = 0;
/* 627 */     int workDistance = this.starRad;
/* 637 */     this.wordGenForestG.setChunkProviderSupportData(chunkBoundingBox, blocksData, blockMetas);
/* 638 */     this.worldGenSavannaTreeG.setChunkProviderSupportData(chunkBoundingBox, blocksData, blockMetas);
/* 639 */     this.worldGenTreesG.setChunkProviderSupportData(chunkBoundingBox, blocksData, blockMetas);
/* 640 */     this.worldGenTaiga2G.setChunkProviderSupportData(chunkBoundingBox, blocksData, blockMetas);
/* 642 */     int genCount = (int)(12.566370614359172D * (double)this.starRad * (double)this.starRad * (double)starBiome.treeRate / 1000.0D) / 2;
/* 643 */     genCount += this.rand.nextInt(genCount);
/* 647 */     for (int i = 0; i < genCount; i++) {
/*     */       int meta;
/* 648 */       int angle = this.rand.nextInt(360);
/* 649 */       int ringRoatX = this.rand.nextInt(90);
/* 650 */       int ringRoatZ = this.rand.nextInt(90);
/* 651 */       TreeSet treeSet = treeSets[this.rand.nextInt(treeSets.length)];
/* 653 */       double dXYZ0 = Math.cos((double)angle * Math.PI / 180.0D) * (double)workDistance;
/* 654 */       double dXYZ1 = 0.0D;
/* 655 */       double dXYZ2 = Math.sin((double)angle * Math.PI / 180.0D) * (double)workDistance;
/* 658 */       float f1 = MathHelper.func_76134_b((float)ringRoatX);
/* 659 */       float f2 = MathHelper.func_76126_a((float)ringRoatX);
/* 660 */       double d0 = dXYZ0;
/* 661 */       double d1 = dXYZ1 * (double)f1 + dXYZ2 * (double)f2;
/* 662 */       double d2 = dXYZ2 * (double)f1 - dXYZ1 * (double)f2;
/* 663 */       dXYZ0 = d0;
/* 664 */       dXYZ1 = d1;
/* 665 */       dXYZ2 = d2;
/* 668 */       f1 = MathHelper.func_76134_b((float)ringRoatZ);
/* 669 */       f2 = MathHelper.func_76126_a((float)ringRoatZ);
/* 670 */       d0 = dXYZ0 * (double)f1 + dXYZ1 * (double)f2;
/* 671 */       d1 = dXYZ1 * (double)f1 - dXYZ0 * (double)f2;
/* 672 */       d2 = dXYZ2;
/* 673 */       dXYZ0 = d0;
/* 674 */       dXYZ1 = d1;
/* 675 */       dXYZ2 = d2;
/* 677 */       posX = cx + (int)dXYZ0;
/* 678 */       posY = cy + (int)dXYZ1;
/* 679 */       posZ = cz + (int)dXYZ2;
/* 681 */       int targetIdIndex = posY << 8 | posZ - chunkBoundingBox.field_78896_c << 4 | posX - chunkBoundingBox.field_78897_a;
/* 683 */       double xFar = (double)(posX - cx);
/* 684 */       boolean xComp = (xFar < 0.0D);
/* 685 */       xFar *= xFar;
/* 687 */       double yFar = (double)(posY - cy);
/* 688 */       boolean yComp = (yFar < 0.0D);
/* 689 */       yFar *= yFar;
/* 691 */       double zFar = (double)(posZ - cz);
/* 692 */       boolean zComp = (zFar < 0.0D);
/* 693 */       zFar *= zFar;
/* 695 */       if (xFar > zFar && xFar > yFar) {
/* 697 */         if (xComp) {
/* 699 */           meta = 3;
/*     */         } else {
/* 702 */           meta = 2;
/*     */         } 
/* 704 */       } else if (zFar >= xFar && zFar > yFar) {
/* 706 */         if (zComp) {
/* 708 */           meta = 5;
/*     */         } else {
/* 711 */           meta = 4;
/*     */         } 
/* 715 */       } else if (yComp) {
/* 717 */         meta = 1;
/*     */       } else {
/* 720 */         meta = 0;
/*     */       } 
/* 724 */       int minTreeHeight = 4 + this.rand.nextInt(6);
/* 725 */       if (treeSet == this.TREESET_OAK) {
/* 726 */         this.worldGenTreesG.metaWood = 0;
/* 727 */         this.worldGenTreesG.metaLeaves = 0;
/* 728 */         this.worldGenTreesG.setAsNewTreeSet(false);
/* 730 */         this.worldGenTreesG.minTreeHeight = minTreeHeight;
/* 731 */         this.worldGenTreesG.dir = meta;
/* 732 */         this.worldGenTreesG.func_76484_a(null, this.rand, posX, posY, posZ);
/*     */       } 
/* 734 */       if (treeSet == this.TREESET_SPRUCE) {
/* 735 */         this.worldGenTaiga2G.minTreeHeight = minTreeHeight;
/* 736 */         this.worldGenTaiga2G.dir = meta;
/* 737 */         this.worldGenTaiga2G.func_76484_a(null, this.rand, posX, posY, posZ);
/*     */       } 
/* 739 */       if (treeSet == this.TREESET_BIRCH) {
/* 740 */         this.wordGenForestG.minTreeHeight = minTreeHeight;
/* 741 */         this.wordGenForestG.dir = meta;
/* 742 */         this.wordGenForestG.func_76484_a(null, this.rand, posX, posY, posZ);
/*     */       } 
/* 744 */       if (treeSet == this.TREESET_JUNGLE) {
/* 745 */         this.worldGenTreesG.metaWood = 3;
/* 746 */         this.worldGenTreesG.metaLeaves = 3;
/* 747 */         this.worldGenTreesG.setAsNewTreeSet(false);
/* 749 */         this.worldGenTreesG.minTreeHeight = minTreeHeight;
/* 750 */         this.worldGenTreesG.dir = meta;
/* 751 */         this.worldGenTreesG.func_76484_a(null, this.rand, posX, posY, posZ);
/*     */       } 
/* 753 */       if (treeSet == this.TREESET_ACACIA) {
/* 754 */         this.worldGenSavannaTreeG.minTreeHeight = minTreeHeight;
/* 755 */         this.worldGenSavannaTreeG.dir = meta;
/* 756 */         this.worldGenSavannaTreeG.func_76484_a(null, this.rand, posX, posY, posZ);
/*     */       } 
/* 758 */       if (treeSet == this.TREESET_ROOFED_OAK) {
/* 759 */         this.worldGenTreesG.metaWood = 1;
/* 760 */         this.worldGenTreesG.metaLeaves = 1;
/* 761 */         this.worldGenTreesG.setAsNewTreeSet(true);
/* 763 */         this.worldGenTreesG.minTreeHeight = minTreeHeight;
/* 764 */         this.worldGenTreesG.dir = meta;
/* 765 */         this.worldGenTreesG.func_76484_a(null, this.rand, posX, posY, posZ);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void makeDimples(World par1World, Random par2Random, StructureBoundingBox chunkBoundingBox, Block[] blocksData, byte[] blockMetas, Block surfaceBlock) {
/* 773 */     this.rand.setSeed((long)this.starSeed);
/* 775 */     int cx = this.starRad + 2 + getExpandBoundRad() + this.field_74887_e.field_78897_a;
/* 776 */     int cy = this.centerY;
/* 777 */     int cz = this.starRad + 2 + getExpandBoundRad() + this.field_74887_e.field_78896_c;
/* 779 */     int dimpleCPosX = 0, posX = 0;
/* 780 */     int dimpleCPosY = 0, posY = 0;
/* 781 */     int dimpleCPosZ = 0, posZ = 0;
/* 786 */     byte blockColorToUse = 0;
/* 787 */     int workDistance = this.starRad + 4;
/* 795 */     double starRadPow = (double)(this.starRad * this.starRad);
/* 796 */     double starRadSub1Pow = (double)((this.starRad - 1) * (this.starRad - 1));
/* 797 */     double starRadPls2Pow = (double)((this.starRad + 2) * (this.starRad + 2));
/* 802 */     for (int i = 0; i < 1; i++) {
/* 803 */       int r = this.rand.nextInt(360);
/* 804 */       int ringRoatX = this.rand.nextInt(90);
/* 805 */       int ringRoatZ = this.rand.nextInt(90);
/* 806 */       int dimpleRad = this.starRad / 4 + this.rand.nextInt(this.starRad / 4);
/* 807 */       double dimpleRadPow = (double)(dimpleRad * dimpleRad);
/* 808 */       double dimpleRadSub1Pow = (double)((dimpleRad - 2) * (dimpleRad - 2));
/* 810 */       double dXYZ0 = Math.cos((double)r * Math.PI / 180.0D) * (double)workDistance;
/* 811 */       double dXYZ1 = 0.0D;
/* 812 */       double dXYZ2 = Math.sin((double)r * Math.PI / 180.0D) * (double)workDistance;
/* 815 */       float f1 = MathHelper.func_76134_b((float)ringRoatX);
/* 816 */       float f2 = MathHelper.func_76126_a((float)ringRoatX);
/* 817 */       double d0 = dXYZ0;
/* 818 */       double d1 = dXYZ1 * (double)f1 + dXYZ2 * (double)f2;
/* 819 */       double d2 = dXYZ2 * (double)f1 - dXYZ1 * (double)f2;
/* 820 */       dXYZ0 = d0;
/* 821 */       dXYZ1 = d1;
/* 822 */       dXYZ2 = d2;
/* 825 */       f1 = MathHelper.func_76134_b((float)ringRoatZ);
/* 826 */       f2 = MathHelper.func_76126_a((float)ringRoatZ);
/* 827 */       d0 = dXYZ0 * (double)f1 + dXYZ1 * (double)f2;
/* 828 */       d1 = dXYZ1 * (double)f1 - dXYZ0 * (double)f2;
/* 829 */       d2 = dXYZ2;
/* 830 */       dXYZ0 = d0;
/* 831 */       dXYZ1 = d1;
/* 832 */       dXYZ2 = d2;
/* 834 */       dimpleCPosX = cx + (int)dXYZ0;
/* 835 */       dimpleCPosY = cy + (int)dXYZ1;
/* 836 */       dimpleCPosZ = cz + (int)dXYZ2;
/* 838 */       int targetIdIndex = dimpleCPosY << 8 | dimpleCPosZ - chunkBoundingBox.field_78896_c << 4 | dimpleCPosX - chunkBoundingBox.field_78897_a;
/* 840 */       for (int j = dimpleCPosX - dimpleRad; j <= dimpleCPosX + dimpleRad; j++) {
/* 842 */         double xFarFromDimpleC = (double)(j - dimpleCPosX);
/* 843 */         xFarFromDimpleC *= xFarFromDimpleC;
/* 846 */         double xFarFromStarC = (double)(j - cx);
/* 847 */         xFarFromStarC *= xFarFromStarC;
/* 849 */         if (xFarFromDimpleC <= dimpleRadPow && xFarFromStarC <= starRadPls2Pow)
/* 851 */           for (int k = dimpleCPosY - dimpleRad; k <= dimpleCPosY + dimpleRad; k++) {
/* 852 */             if (k >= 0 && 255 >= k) {
/* 854 */               double yFarFromDimpleC = (double)(k - dimpleCPosY);
/* 855 */               yFarFromDimpleC *= yFarFromDimpleC;
/* 856 */               double xyFarFromDimpleC = xFarFromDimpleC + yFarFromDimpleC;
/* 859 */               double yFarFromStarC = (double)(k - cy);
/* 860 */               yFarFromStarC *= yFarFromStarC;
/* 861 */               double xyFarFromStarC = xFarFromStarC + yFarFromStarC;
/* 863 */               if (xyFarFromDimpleC <= dimpleRadPow && xyFarFromStarC <= starRadPls2Pow)
/* 865 */                 for (int m = dimpleCPosZ - dimpleRad; m <= dimpleCPosZ + dimpleRad; m++) {
/* 867 */                   double zFarFromDimpleC = (double)(m - dimpleCPosZ);
/* 868 */                   zFarFromDimpleC *= zFarFromDimpleC;
/* 869 */                   double xyzFarFromDimpleC = zFarFromDimpleC + xyFarFromDimpleC;
/* 872 */                   double zFarFromStarC = (double)(m - cz);
/* 873 */                   zFarFromStarC *= zFarFromStarC;
/* 874 */                   double xyzFarFromStarC = zFarFromStarC + xyFarFromStarC;
/* 876 */                   if (xyzFarFromDimpleC <= dimpleRadPow && xyzFarFromStarC <= starRadPls2Pow)
/* 878 */                     if (xyzFarFromDimpleC >= dimpleRadSub1Pow) {
/* 880 */                       if (xyzFarFromStarC <= starRadPow)
/* 882 */                         if (j >= chunkBoundingBox.field_78897_a && j <= chunkBoundingBox.field_78893_d && m >= chunkBoundingBox.field_78896_c && m <= chunkBoundingBox.field_78892_f) {
/* 886 */                           targetIdIndex = k << 8 | m - chunkBoundingBox.field_78896_c << 4 | j - chunkBoundingBox.field_78897_a;
/* 887 */                           blocksData[targetIdIndex] = surfaceBlock;
/* 888 */                           blockMetas[targetIdIndex] = 0;
/*     */                         }  
/* 890 */                     } else if (xyzFarFromDimpleC < dimpleRadSub1Pow && 
/* 891 */                       j >= chunkBoundingBox.field_78897_a && j <= chunkBoundingBox.field_78893_d && m >= chunkBoundingBox.field_78896_c && m <= chunkBoundingBox.field_78892_f) {
/* 895 */                       targetIdIndex = k << 8 | m - chunkBoundingBox.field_78896_c << 4 | j - chunkBoundingBox.field_78897_a;
/* 904 */                       blocksData[targetIdIndex] = Blocks.field_150350_a;
/*     */                     }  
/*     */                 }  
/*     */             } 
/*     */           }  
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
/* 917 */     par1NBTTagCompound.func_74768_a("starRad", this.starRad);
/* 918 */     par1NBTTagCompound.func_74768_a("ringRad", this.ringRad);
/* 919 */     par1NBTTagCompound.func_74768_a("centerY", this.centerY);
/* 920 */     par1NBTTagCompound.func_74768_a("starSeed", this.starSeed);
/*     */   }
/*     */   
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
/* 926 */     this.starRad = par1NBTTagCompound.func_74762_e("starRad");
/* 927 */     this.ringRad = par1NBTTagCompound.func_74762_e("ringRad");
/* 928 */     this.centerY = par1NBTTagCompound.func_74762_e("centerY");
/* 929 */     this.starSeed = par1NBTTagCompound.func_74762_e("starSeed");
/*     */   }
/*     */   
/*     */   public ComponentStar() {}
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */