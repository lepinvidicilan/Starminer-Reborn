/*     */ package jp.mc.ancientred.starminer.basics;
/*     */ 
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.Mod;
/*     */ import cpw.mods.fml.common.Mod.EventHandler;
/*     */ import cpw.mods.fml.common.Mod.Instance;
/*     */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*     */ import cpw.mods.fml.common.network.FMLEventChannel;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.registry.EntityRegistry;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import java.io.File;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockAirEx;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockChestEx;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockDirtGrassEx;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockGravityCore;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockGravityWall;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockInnerCore;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockManBazooka;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockNavigator;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockOuterCore;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockRotator;
/*     */ import jp.mc.ancientred.starminer.basics.block.bed.BlockStarBedBody;
/*     */ import jp.mc.ancientred.starminer.basics.block.bed.BlockStarBedHead;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.BlockCarrotGravitized;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.BlockCropsGravitized;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.BlockFlowerGravitized;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.BlockPotatoGravitized;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.BlockSaplingGravitized;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.BlockTallGrassGravitized;
/*     */ import jp.mc.ancientred.starminer.basics.common.CommonForgeEventHandler;
/*     */ import jp.mc.ancientred.starminer.basics.common.CommonProxy;
/*     */ import jp.mc.ancientred.starminer.basics.common.CommonProxyClient;
/*     */ import jp.mc.ancientred.starminer.basics.common.CommonProxyServer;
/*     */ import jp.mc.ancientred.starminer.basics.dimention.WorldProviderSpace;
/*     */ import jp.mc.ancientred.starminer.basics.entity.EntityFallingBlockEx;
/*     */ import jp.mc.ancientred.starminer.basics.entity.EntityGProjectile;
/*     */ import jp.mc.ancientred.starminer.basics.entity.EntityStarSquid;
/*     */ import jp.mc.ancientred.starminer.basics.item.ItemBlockGun;
/*     */ import jp.mc.ancientred.starminer.basics.item.ItemGArrow;
/*     */ import jp.mc.ancientred.starminer.basics.item.ItemGHook;
/*     */ import jp.mc.ancientred.starminer.basics.item.ItemGrappleGun;
/*     */ import jp.mc.ancientred.starminer.basics.item.ItemGravityContoler;
/*     */ import jp.mc.ancientred.starminer.basics.item.ItemSolarWindFan;
/*     */ import jp.mc.ancientred.starminer.basics.item.ItemStarBed;
/*     */ import jp.mc.ancientred.starminer.basics.item.ItemStarContoler;
/*     */ import jp.mc.ancientred.starminer.basics.item.ItemStardust;
/*     */ import jp.mc.ancientred.starminer.basics.item.block.ItemBlockForGWall;
/*     */ import jp.mc.ancientred.starminer.basics.item.block.ItemBlockForRotator;
/*     */ import jp.mc.ancientred.starminer.basics.item.block.ItemBlockWithStarMark;
/*     */ import jp.mc.ancientred.starminer.basics.item.block.ItemColoredForTallGrass;
/*     */ import jp.mc.ancientred.starminer.basics.item.block.ItemMultiTextureTileForG;
/*     */ import jp.mc.ancientred.starminer.basics.item.crop.ItemSeedFoodGravitized;
/*     */ import jp.mc.ancientred.starminer.basics.item.crop.ItemSeedGravitized;
/*     */ import jp.mc.ancientred.starminer.basics.structure.ComponentStar;
/*     */ import jp.mc.ancientred.starminer.basics.structure.MapGenStar;
/*     */ import jp.mc.ancientred.starminer.basics.structure.StructureStarStart;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityBlockRotator;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityChestEx;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityGravityGenerator;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityNavigator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.block.BlockSapling;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.gen.structure.MapGenStructureIO;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ 
/*     */ @Mod(modid = "modJ_StarMiner", name = "StarMiner", version = "0.9.7", useMetadata = true)
/*     */ public class SMModContainer {
/*     */   @Mod.Instance("modJ_StarMiner")
/*     */   public static SMModContainer instance;
/*     */   
/*     */   public static CommonProxy proxy;
/*     */   
/*     */   public static FMLEventChannel channel;
/*     */   
/*     */   public static final String networkChannelName = "Starminer";
/*     */   
/*     */   public static final int PACKET_TYPE_GCORE_GUIACT = 10;
/*     */   
/*     */   public static final int PACKET_TYPE_SKYMAP = 12;
/*     */   
/*     */   public static final int PACKET_TYPE_TEUPD_GCORE = 14;
/*     */   
/*     */   public static final int PACKET_TYPE_TEUPD_NAVIG = 16;
/*     */   
/*     */   public static final int PACKET_TYPE_DIMENTION_RESPAWN = 18;
/*     */   
/*     */   public static final int PACKET_TYPE_RESPAWN_PLAYER_LOOKPOS = 20;
/*     */   
/*  97 */   public static MapGenStar starGenerator = new MapGenStar();
/*     */   
/* 107 */   public static int GeostationaryOrbitDimentionId = 88;
/*     */   
/*     */   public static Item GravityControllerItem;
/*     */   
/*     */   public static Item StarControlerItem;
/*     */   
/*     */   public static Item SolarWindFanItem;
/*     */   
/*     */   public static Item StarBedItem;
/*     */   
/*     */   public static Item StardustItem;
/*     */   
/*     */   public static Item GrappleGunItem;
/*     */   
/*     */   public static Item BlockGunItem;
/*     */   
/*     */   public static Item GArrowItem;
/*     */   
/*     */   public static Item GHookItem;
/*     */   
/*     */   public static Item SeedGravizedItem;
/*     */   
/*     */   public static Item CarrotGravizedItem;
/*     */   
/*     */   public static Item PotatoGravizedItem;
/*     */   
/*     */   public static Block GravityCoreBlock;
/*     */   
/*     */   public static Block InnerCoreBlock;
/*     */   
/*     */   public static Block OuterCoreBlock;
/*     */   
/*     */   public static Block ManBazookaBlock;
/*     */   
/*     */   public static Block NavigatorBlock;
/*     */   
/*     */   public static Block GravityWallBlock;
/*     */   
/*     */   public static Block AirExBlock;
/*     */   
/*     */   public static Block DirtGrassExBlock;
/*     */   
/*     */   public static Block StarBedBodyBlock;
/*     */   
/*     */   public static Block StarBedHeadBlock;
/*     */   
/*     */   public static Block BlockRotatorBlock;
/*     */   
/*     */   public static Block BlockChestEx;
/*     */   
/*     */   public static Block CropGravitizedBlock;
/*     */   
/*     */   public static Block PlantYelGravitizedBlock;
/*     */   
/*     */   public static Block PlantRedGravitizedBlock;
/*     */   
/*     */   public static Block SaplingGravitizedBlock;
/*     */   
/*     */   public static Block CarrotGravitizedBlock;
/*     */   
/*     */   public static Block PotatoGravitizedBlock;
/*     */   
/*     */   public static Block TallGrassGravitizedBlock;
/*     */   
/* 175 */   public static int guiStarCoreId = 1243;
/*     */   
/* 177 */   public static CreativeTabs starMinerTab = new CreativeTabs(CreativeTabs.getNextID(), "StarMiner") {
/*     */       public Item func_78016_d() {
/* 180 */         return Item.func_150898_a(SMModContainer.GravityCoreBlock);
/*     */       }
/*     */     };
/*     */   
/*     */   @Mod.EventHandler
/*     */   public void preInit(FMLPreInitializationEvent event) {
/* 187 */     proxy = FMLCommonHandler.instance().getSide().isClient() ? new CommonProxyClient() : new CommonProxyServer();
/*     */     try {
/* 190 */       Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "Starminer.cfg"));
/* 191 */       config.load();
/* 194 */       config.getCategory("basics_server_side_property");
/* 195 */       config.addCustomCategoryComment("basics_server_side_property", "Affects server side only.");
/* 197 */       Config.bazookaStartSpeed = config.get("basics_server_side_property", "bazookaStartSpeed", 3.3D).getDouble(3.3D);
/* 198 */       Config.generateStars = config.get("basics_server_side_property", "generateStars", true).getBoolean(true);
/* 199 */       Config.ticketFreeForTeleport = config.get("basics_server_side_property", "ticketFreeForTeleport", false).getBoolean(false);
/* 200 */       Config.attractCheckTick = (long)config.get("basics_server_side_property", "attractCheckTick", 8).getInt(8);
/* 201 */       Config.enableFakeRotatorOnlyVannilaBlock = config.get("basics_server_side_property", "enableFakeRotatorOnlyVannilaBlock", true).getBoolean(true);
/* 202 */       if (Config.attractCheckTick == 0L)
/* 202 */         Config.attractCheckTick = 1L; 
/* 205 */       Config.maxStarRad = (long)config.get("basics_server_side_property", "maxStarRad", 48).getInt(48);
/* 206 */       Config.maxGravityRad = (long)config.get("basics_server_side_property", "maxGravityRad", 54).getInt(54);
/* 209 */       GeostationaryOrbitDimentionId = config.get("basics_server_side_property", "GSODimentionId_byte", 88).getInt(88);
/* 212 */       config.getCategory("basics_client_side_property");
/* 213 */       config.addCustomCategoryComment("basics_client_side_property", "Affects client side only.");
/* 214 */       Config.naviLaserLength = config.get("basics_client_side_property", "naviLaserLength", 7).getInt(7);
/* 216 */       if (config.hasChanged())
/* 218 */         config.save(); 
/*     */     } catch (Exception ex) {
/* 220 */       ex.printStackTrace();
/*     */     } 
/* 224 */     MapGenStructureIO.func_143034_b(StructureStarStart.class, "StarminerStarStart");
/* 225 */     MapGenStructureIO.func_143031_a(ComponentStar.class, "StarminerStar");
/* 228 */     GameRegistry.registerBlock(GravityCoreBlock = new BlockGravityCore().func_149658_d("starminer:g_core").func_149663_c("gravitycore"), "gravitycore");
/* 229 */     GameRegistry.registerBlock(InnerCoreBlock = new BlockInnerCore().func_149658_d("starminer:inner_core").func_149663_c("innercore"), "innercore");
/* 230 */     GameRegistry.registerBlock(OuterCoreBlock = new BlockOuterCore().func_149658_d("starminer:outer_core").func_149663_c("outercore"), "outercore");
/* 231 */     GameRegistry.registerBlock(ManBazookaBlock = new BlockManBazooka().func_149658_d("starminer:dokan").func_149663_c("manbazooka"), "manbazooka");
/* 232 */     GameRegistry.registerBlock(NavigatorBlock = new BlockNavigator().func_149658_d("starminer:g_core").func_149663_c("navigator"), "navigator");
/* 233 */     GameRegistry.registerBlock(GravityWallBlock = new BlockGravityWall().func_149658_d("starminer:g_wall").func_149663_c("gravitywall"), ItemBlockForGWall.class, "gravitywall");
/* 234 */     GameRegistry.registerBlock(AirExBlock = new BlockAirEx().func_149663_c("airex"), "airex");
/* 235 */     GameRegistry.registerBlock(DirtGrassExBlock = new BlockDirtGrassEx().func_149663_c("dirtgrassex"), "dirtgrassex");
/* 236 */     GameRegistry.registerBlock(BlockRotatorBlock = new BlockRotator().func_149663_c("blockrotator"), ItemBlockForRotator.class, "blockrotator");
/* 238 */     GameRegistry.registerBlock(BlockChestEx = new BlockChestEx(0).func_149663_c("chestgravitized"), ItemBlockWithStarMark.class, "chestgravitized");
/* 241 */     GameRegistry.registerBlock(StarBedBodyBlock = new BlockStarBedBody().func_149663_c("starbedbody"), "starbedbody");
/* 242 */     GameRegistry.registerBlock(StarBedHeadBlock = new BlockStarBedHead().func_149663_c("starbedhead"), "starbedhead");
/* 245 */     GameRegistry.registerItem(GravityControllerItem = new ItemGravityContoler().func_77655_b("gravitycontroller"), "gravitycontroller");
/* 246 */     GameRegistry.registerItem(StarControlerItem = new ItemStarContoler().func_77655_b("starcontroller"), "starcontroller");
/* 247 */     GameRegistry.registerItem(SolarWindFanItem = new ItemSolarWindFan().func_77655_b("solarwindFan"), "solarwindFan");
/* 248 */     GameRegistry.registerItem(StarBedItem = new ItemStarBed().func_77655_b("starbed"), "starbed");
/* 250 */     GameRegistry.registerItem(StardustItem = new ItemStardust().func_77655_b("starcore_dust"), "starcore_dust");
/* 251 */     GameRegistry.registerItem(GrappleGunItem = new ItemGrappleGun().func_77655_b("g_rapplegun"), "g_rapplegun");
/* 252 */     GameRegistry.registerItem(GArrowItem = new ItemGArrow().func_77655_b("g_arrow"), "g_arrow");
/* 253 */     GameRegistry.registerItem(GHookItem = new ItemGHook().func_77655_b("g_hook"), "g_hook");
/* 255 */     GameRegistry.registerItem(BlockGunItem = new ItemBlockGun().func_77655_b("blockgun"), "blockgun");
/* 260 */     GameRegistry.registerBlock(CropGravitizedBlock = new BlockCropsGravitized().func_149663_c("cropgravitized"), "cropgravitized");
/* 261 */     GameRegistry.registerBlock(CarrotGravitizedBlock = new BlockCarrotGravitized().func_149663_c("carrotgravitized"), "carrotgravitized");
/* 262 */     GameRegistry.registerBlock(PotatoGravitizedBlock = new BlockPotatoGravitized().func_149663_c("potatogravitized"), "potatogravitized");
/* 264 */     GameRegistry.registerBlock(SaplingGravitizedBlock = new BlockSaplingGravitized().func_149663_c("saplinggravitized"), ItemMultiTextureTileForG.class, "saplinggravitized", new Object[] { SaplingGravitizedBlock, BlockSapling.field_149882_a });
/* 265 */     GameRegistry.registerBlock(PlantYelGravitizedBlock = new BlockFlowerGravitized(0).func_149663_c("yflowergravitized"), ItemMultiTextureTileForG.class, "yflowergravitized", new Object[] { PlantYelGravitizedBlock, BlockFlower.field_149858_b });
/* 266 */     GameRegistry.registerBlock(PlantRedGravitizedBlock = new BlockFlowerGravitized(1).func_149663_c("rflowergravitized"), ItemMultiTextureTileForG.class, "rflowergravitized", new Object[] { PlantRedGravitizedBlock, BlockFlower.field_149859_a });
/* 267 */     GameRegistry.registerBlock(TallGrassGravitizedBlock = new BlockTallGrassGravitized().func_149663_c("tallgrassgravitized"), ItemColoredForTallGrass.class, "tallgrassgravitized");
/* 270 */     GameRegistry.registerItem(SeedGravizedItem = new ItemSeedGravitized(CropGravitizedBlock, Blocks.field_150458_ak).func_77655_b("seedsgravitized").func_111206_d("seeds_wheat"), "seedsgravitized");
/* 271 */     GameRegistry.registerItem(CarrotGravizedItem = new ItemSeedFoodGravitized(CarrotGravitizedBlock).func_77655_b("carrotgravitizeditem").func_111206_d("carrot"), "carrotgravitizeditem");
/* 272 */     GameRegistry.registerItem(PotatoGravizedItem = new ItemSeedFoodGravitized(PotatoGravitizedBlock).func_77655_b("potatogravitizeditem").func_111206_d("potato"), "potatogravitizeditem");
/* 275 */     setMyCreativeTabs();
/* 278 */     NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
/* 281 */     DimensionManager.registerProviderType(GeostationaryOrbitDimentionId, WorldProviderSpace.class, true);
/* 282 */     DimensionManager.registerDimension(GeostationaryOrbitDimentionId, GeostationaryOrbitDimentionId);
/*     */   }
/*     */   
/*     */   @Mod.EventHandler
/*     */   public void init(FMLInitializationEvent event) {
/* 288 */     Object handler = new CommonForgeEventHandler();
/* 289 */     MinecraftForge.EVENT_BUS.register(handler);
/* 290 */     FMLCommonHandler.instance().bus().register(handler);
/* 293 */     channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("Starminer");
/* 294 */     proxy.registerNetworkHandler();
/* 297 */     GameRegistry.registerTileEntity(TileEntityGravityGenerator.class, "TileEntityGravityCore");
/* 298 */     GameRegistry.registerTileEntity(TileEntityNavigator.class, "TileEntityNavigator");
/* 299 */     GameRegistry.registerTileEntity(TileEntityBlockRotator.class, "TileEntityBlockRotator");
/* 300 */     GameRegistry.registerTileEntity(TileEntityChestEx.class, "TileEntityChestEx");
/* 303 */     EntityRegistry.registerModEntity(EntityFallingBlockEx.class, "FallingBlockEx", 564, this, 30, 1, true);
/* 304 */     EntityRegistry.registerModEntity(EntityStarSquid.class, "StarSquid", 565, this, 80, 1, true);
/* 305 */     EntityRegistry.registerModEntity(EntityGProjectile.class, "GProjectile", 566, this, 80, 1, true);
/* 308 */     addRecipie();
/* 311 */     proxy.registerRenderHelper();
/*     */   }
/*     */   
/*     */   private void addRecipie() {
/* 318 */     GameRegistry.addRecipe(new ItemStack(OuterCoreBlock), new Object[] { "XZX", "ZYZ", "XZX", 'X', Blocks.field_150322_A, 'Y', Items.field_151129_at, 'Z', Blocks.field_150346_d });
/* 320 */     GameRegistry.addRecipe(new ItemStack(OuterCoreBlock), new Object[] { "XXX", "XXX", "XXX", 'X', StardustItem });
/* 322 */     GameRegistry.addRecipe(new ItemStack(InnerCoreBlock), new Object[] { "ZXZ", "XYX", "ZXZ", 'X', Blocks.field_150343_Z, 'Y', Blocks.field_150339_S, 'Z', Blocks.field_150359_w });
/* 324 */     GameRegistry.addRecipe(new ItemStack(GravityCoreBlock), new Object[] { "XXX", "XYX", "XXX", 'X', OuterCoreBlock, 'Y', InnerCoreBlock });
/* 326 */     GameRegistry.addRecipe(new ItemStack(GravityWallBlock), new Object[] { "XXX", 'X', StardustItem });
/* 328 */     GameRegistry.addRecipe(new ItemStack(GravityControllerItem), new Object[] { "X  ", "YZZ", "X Z", 'X', OuterCoreBlock, 'Y', InnerCoreBlock, 'Z', Items.field_151055_y });
/* 330 */     GameRegistry.addRecipe(new ItemStack(StarControlerItem), new Object[] { "XYX", "XXX", " Z ", 'X', Items.field_151042_j, 'Y', InnerCoreBlock, 'Z', GravityControllerItem });
/* 332 */     GameRegistry.addRecipe(new ItemStack(SolarWindFanItem), new Object[] { 
/* 332 */           "XXY", "XZY", "YYW", 'X', OuterCoreBlock, 'Y', new ItemStack(Items.field_151016_H, 1, 4), 'Z', InnerCoreBlock, 'W', 
/* 332 */           Items.field_151055_y });
/* 334 */     GameRegistry.addRecipe(new ItemStack(StarBedItem), new Object[] { " X ", "YYY", 'X', Items.field_151104_aV, 'Y', GravityWallBlock });
/* 336 */     GameRegistry.addRecipe(new ItemStack(BlockChestEx), new Object[] { " X ", "YYY", 'X', Blocks.field_150486_ae, 'Y', GravityWallBlock });
/* 338 */     GameRegistry.addShapelessRecipe(new ItemStack(StardustItem, 9), new Object[] { new ItemStack(OuterCoreBlock, 1) });
/* 341 */     GameRegistry.addShapelessRecipe(new ItemStack(BlockRotatorBlock, 1), new Object[] { new ItemStack(Blocks.field_150359_w, 1), new ItemStack(StardustItem, 1) });
/* 344 */     GameRegistry.addRecipe(new ItemStack(BlockGunItem, 1, 0), new Object[] { " XX", "XXY", "  Z", 'X', Blocks.field_150347_e, 'Y', StardustItem, 'Z', GravityControllerItem });
/* 345 */     GameRegistry.addShapelessRecipe(new ItemStack(BlockGunItem, 1, 1), new Object[] { new ItemStack(BlockGunItem, 1, 0) });
/* 346 */     GameRegistry.addShapelessRecipe(new ItemStack(BlockGunItem, 1, 2), new Object[] { new ItemStack(BlockGunItem, 1, 1) });
/* 347 */     GameRegistry.addShapelessRecipe(new ItemStack(BlockGunItem, 1, 3), new Object[] { new ItemStack(BlockGunItem, 1, 2) });
/* 348 */     GameRegistry.addShapelessRecipe(new ItemStack(BlockGunItem, 1, 0), new Object[] { new ItemStack(BlockGunItem, 1, 3) });
/* 351 */     GameRegistry.addRecipe(new ItemStack(GrappleGunItem), new Object[] { " XX", "XXY", "  Z", 'X', Blocks.field_150343_Z, 'Y', GravityCoreBlock, 'Z', GravityControllerItem });
/* 353 */     GameRegistry.addShapelessRecipe(new ItemStack(GArrowItem, 1), new Object[] { new ItemStack(Items.field_151032_g, 1), new ItemStack(StardustItem, 1) });
/* 355 */     GameRegistry.addShapelessRecipe(new ItemStack(GHookItem, 1), new Object[] { new ItemStack(GArrowItem, 1), new ItemStack(Items.field_151007_F, 1) });
/* 357 */     GameRegistry.addRecipe(new ItemStack(NavigatorBlock, 2), new Object[] { "X", "Y", 'X', OuterCoreBlock, 'Y', Blocks.field_150478_aa });
/* 358 */     GameRegistry.addRecipe(new ItemStack(NavigatorBlock, 2), new Object[] { "Y", "X", 'X', OuterCoreBlock, 'Y', Blocks.field_150478_aa });
/* 360 */     GameRegistry.addRecipe(new ItemStack(ManBazookaBlock), new Object[] { 
/* 360 */           "XTX", "XZX", "YWY", 'X', Blocks.field_150463_bK, 'T', Blocks.field_150344_f, 'Z', Items.field_151016_H, 'Y', 
/* 360 */           Blocks.field_150347_e, 'W', Blocks.field_150460_al });
/* 362 */     GameRegistry.addRecipe(new ItemStack(ManBazookaBlock), new Object[] { 
/* 362 */           "XTX", "XZX", "YWY", 'X', Blocks.field_150463_bK, 'T', Blocks.field_150344_f, 'Z', InnerCoreBlock, 'Y', 
/* 362 */           Blocks.field_150347_e, 'W', Blocks.field_150460_al });
/* 365 */     GameRegistry.addShapelessRecipe(new ItemStack(DirtGrassExBlock, 1), new Object[] { new ItemStack(Blocks.field_150346_d, 1), new ItemStack(StardustItem, 1) });
/* 367 */     GameRegistry.addShapelessRecipe(new ItemStack(SeedGravizedItem, 1), new Object[] { new ItemStack(Items.field_151014_N, 1), new ItemStack(StardustItem, 1) });
/* 369 */     GameRegistry.addShapelessRecipe(new ItemStack(CarrotGravizedItem, 1), new Object[] { new ItemStack(Items.field_151172_bF, 1), new ItemStack(StardustItem, 1) });
/* 371 */     GameRegistry.addShapelessRecipe(new ItemStack(PotatoGravizedItem, 1), new Object[] { new ItemStack(Items.field_151174_bG, 1), new ItemStack(StardustItem, 1) });
/* 373 */     for (int k = 0; k < 1; k++) {
/* 374 */       GameRegistry.addShapelessRecipe(new ItemStack(PlantYelGravitizedBlock, 1, k), new Object[] { new ItemStack((Block)Blocks.field_150327_N, 1, k), new ItemStack(StardustItem, 1) });
/*     */     } 
/* 377 */     for (int j = 0; j < 9; j++) {
/* 378 */       GameRegistry.addShapelessRecipe(new ItemStack(PlantRedGravitizedBlock, 1, j), new Object[] { new ItemStack((Block)Blocks.field_150328_O, 1, j), new ItemStack(StardustItem, 1) });
/*     */     } 
/* 381 */     for (int i = 0; i < 6; i++) {
/* 382 */       GameRegistry.addShapelessRecipe(new ItemStack(SaplingGravitizedBlock, 1, i), new Object[] { new ItemStack(Blocks.field_150345_g, 1, i), new ItemStack(StardustItem, 1) });
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setMyCreativeTabs() {
/* 390 */     GravityControllerItem.func_77637_a(starMinerTab);
/* 391 */     StarControlerItem.func_77637_a(starMinerTab);
/* 392 */     SolarWindFanItem.func_77637_a(starMinerTab);
/* 393 */     StarBedItem.func_77637_a(starMinerTab);
/* 394 */     StardustItem.func_77637_a(starMinerTab);
/* 395 */     GrappleGunItem.func_77637_a(starMinerTab);
/* 396 */     BlockGunItem.func_77637_a(starMinerTab);
/* 397 */     GArrowItem.func_77637_a(starMinerTab);
/* 398 */     GHookItem.func_77637_a(starMinerTab);
/* 401 */     GravityCoreBlock.func_149647_a(starMinerTab);
/* 402 */     InnerCoreBlock.func_149647_a(starMinerTab);
/* 403 */     OuterCoreBlock.func_149647_a(starMinerTab);
/* 404 */     ManBazookaBlock.func_149647_a(starMinerTab);
/* 405 */     NavigatorBlock.func_149647_a(starMinerTab);
/* 406 */     GravityWallBlock.func_149647_a(starMinerTab);
/* 407 */     DirtGrassExBlock.func_149647_a(starMinerTab);
/* 408 */     BlockRotatorBlock.func_149647_a(starMinerTab);
/* 409 */     BlockChestEx.func_149647_a(starMinerTab);
/* 413 */     SeedGravizedItem.func_77637_a(starMinerTab);
/* 414 */     CarrotGravizedItem.func_77637_a(starMinerTab);
/* 415 */     PotatoGravizedItem.func_77637_a(starMinerTab);
/* 418 */     SaplingGravitizedBlock.func_149647_a(starMinerTab);
/* 419 */     PlantYelGravitizedBlock.func_149647_a(starMinerTab);
/* 420 */     PlantRedGravitizedBlock.func_149647_a(starMinerTab);
/* 421 */     TallGrassGravitizedBlock.func_149647_a(starMinerTab);
/* 424 */     CropGravitizedBlock.func_149647_a(null);
/* 425 */     CarrotGravitizedBlock.func_149647_a(null);
/* 426 */     PotatoGravitizedBlock.func_149647_a(null);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */