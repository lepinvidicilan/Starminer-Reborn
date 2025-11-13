/*     */ package jp.mc.ancientred.starminer.basics.common;
/*     */ 
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import cpw.mods.fml.client.registry.ClientRegistry;
/*     */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import jp.mc.ancientred.starminer.api.IRotateSleepingViewHandler;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.SMReflectionHelper;
/*     */ import jp.mc.ancientred.starminer.basics.SMReflectionHelperClient;
/*     */ import jp.mc.ancientred.starminer.basics.block.render.BlockCSGravitizedRenderHelper;
/*     */ import jp.mc.ancientred.starminer.basics.block.render.BlockCropsGravitizedRenderHelper;
/*     */ import jp.mc.ancientred.starminer.basics.block.render.BlockDirtExRenderHelper;
/*     */ import jp.mc.ancientred.starminer.basics.block.render.BlockGravityCoreRenderHelper;
/*     */ import jp.mc.ancientred.starminer.basics.block.render.BlockGravityWallRenderHelper;
/*     */ import jp.mc.ancientred.starminer.basics.block.render.BlockManBazookaRenderHelper;
/*     */ import jp.mc.ancientred.starminer.basics.block.render.BlockNavigatorRenderHelper;
/*     */ import jp.mc.ancientred.starminer.basics.block.render.BlockRotatorRenderHelper;
/*     */ import jp.mc.ancientred.starminer.basics.block.render.BlockStarBedRenderHelper;
/*     */ import jp.mc.ancientred.starminer.basics.dimention.MapFromSky;
/*     */ import jp.mc.ancientred.starminer.basics.dimention.WorldProviderSpace;
/*     */ import jp.mc.ancientred.starminer.basics.dimention.renderer.SpaceCloudRenderHandler;
/*     */ import jp.mc.ancientred.starminer.basics.dimention.renderer.SpaceSkyRenderHanlder;
/*     */ import jp.mc.ancientred.starminer.basics.entity.EntityFallingBlockEx;
/*     */ import jp.mc.ancientred.starminer.basics.entity.EntityGProjectile;
/*     */ import jp.mc.ancientred.starminer.basics.entity.EntityStarSquid;
/*     */ import jp.mc.ancientred.starminer.basics.entity.RenderFallingBlockEx;
/*     */ import jp.mc.ancientred.starminer.basics.entity.RenderGProjectile;
/*     */ import jp.mc.ancientred.starminer.basics.entity.RenderStarSquid;
/*     */ import jp.mc.ancientred.starminer.basics.gui.ContainerStarCore;
/*     */ import jp.mc.ancientred.starminer.basics.gui.GuiStarCore;
/*     */ import jp.mc.ancientred.starminer.basics.item.render.RenderEquippedItemGrappleGun;
/*     */ import jp.mc.ancientred.starminer.basics.packet.SMPacketHandlerClient;
/*     */ import jp.mc.ancientred.starminer.basics.packet.SMPacketHandlerServer;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityBlockRotator;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityChestEx;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityGravityGenerator;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.render.TileEnityRenderBlockRotator;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.render.TileEntityChestRendererEx;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelSquid;
/*     */ import net.minecraft.client.multiplayer.ChunkProviderClient;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.launchwrapper.LogWrapper;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import org.apache.logging.log4j.Level;
/*     */ 
/*     */ public class CommonProxyClient extends CommonProxy {
/*     */   private static Field field_chunkListing;
/*     */   
/*     */   private static Field field_isGapLightingUpdated;
/*     */   
/*     */   private static Field field_queuedLightChecks;
/*     */   
/*     */   public World getClientWorld() {
/*  71 */     return (World)(FMLClientHandler.instance().getClient()).field_71441_e;
/*     */   }
/*     */   
/*     */   public void registerNetworkHandler() {
/*  75 */     SMModContainer.channel.register(new SMPacketHandlerClient());
/*  76 */     SMModContainer.channel.register(new SMPacketHandlerServer());
/*     */   }
/*     */   
/*     */   public void registerRenderHelper() {
/*  79 */     RenderingRegistry.registerEntityRenderingHandler(EntityStarSquid.class, new RenderStarSquid(new ModelSquid(), 0.7F));
/*  80 */     RenderingRegistry.registerEntityRenderingHandler(EntityGProjectile.class, new RenderGProjectile());
/*  81 */     RenderingRegistry.registerEntityRenderingHandler(EntityFallingBlockEx.class, new RenderFallingBlockEx());
/*  82 */     RenderingRegistry.registerBlockHandler(new BlockGravityCoreRenderHelper());
/*  83 */     RenderingRegistry.registerBlockHandler(new BlockGravityWallRenderHelper());
/*  84 */     RenderingRegistry.registerBlockHandler(new BlockManBazookaRenderHelper());
/*  85 */     RenderingRegistry.registerBlockHandler(new BlockNavigatorRenderHelper());
/*  86 */     RenderingRegistry.registerBlockHandler(new BlockCropsGravitizedRenderHelper());
/*  87 */     RenderingRegistry.registerBlockHandler(new BlockCSGravitizedRenderHelper());
/*  88 */     RenderingRegistry.registerBlockHandler(new BlockDirtExRenderHelper());
/*  89 */     RenderingRegistry.registerBlockHandler(new BlockStarBedRenderHelper());
/*  91 */     RenderingRegistry.registerBlockHandler(new BlockRotatorRenderHelper());
/*  93 */     ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockRotator.class, new TileEnityRenderBlockRotator());
/*  94 */     ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChestEx.class, new TileEntityChestRendererEx());
/*  96 */     MinecraftForgeClient.registerItemRenderer(SMModContainer.GrappleGunItem, new RenderEquippedItemGrappleGun());
/*  97 */     MinecraftForgeClient.registerItemRenderer(SMModContainer.BlockGunItem, new RenderEquippedItemGrappleGun());
/* 100 */     IRotateSleepingViewHandler.handlerList.add(new SleepingViewHandler());
/*     */   }
/*     */   
/*     */   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
/* 104 */     if (ID == SMModContainer.guiStarCoreId) {
/* 105 */       TileEntity te = world.func_147438_o(x, y, z);
/* 106 */       if (te != null && te instanceof TileEntityGravityGenerator)
/* 107 */         return new ContainerStarCore(player, (TileEntityGravityGenerator)te); 
/*     */     } 
/* 110 */     return null;
/*     */   }
/*     */   
/*     */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
/* 114 */     if (ID == SMModContainer.guiStarCoreId) {
/* 115 */       TileEntity te = world.func_147438_o(x, y, z);
/* 116 */       if (te != null && te instanceof TileEntityGravityGenerator)
/* 117 */         return new GuiStarCore(player, (TileEntityGravityGenerator)te); 
/*     */     } 
/* 120 */     return null;
/*     */   }
/*     */   
/*     */   public void handleWorldLoadEvent(WorldEvent.Load event) {
/* 124 */     if (event.world.field_73011_w instanceof WorldProviderSpace) {
/* 125 */       ((WorldProviderSpace)event.world.field_73011_w).setSkyRenderer(new SpaceSkyRenderHanlder());
/* 126 */       ((WorldProviderSpace)event.world.field_73011_w).setCloudRenderer(new SpaceCloudRenderHandler());
/*     */     } 
/* 129 */     if (!event.world.field_72995_K && 
/* 130 */       event.world.field_73011_w.field_76574_g == 0 && event.world.field_73011_w instanceof net.minecraft.world.WorldProviderSurface) {
/* 131 */       LogWrapper.log(Level.INFO, "[Starminer]Creating dimention 0(surface) ground texture at server....", new Object[0]);
/* 132 */       MapFromSky.createMapDataFromSky(event.world);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void doWakeupAll(WorldServer worldServer) {
/* 138 */     if (!worldServer.field_72995_K && 
/* 139 */       worldServer.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider && 
/* 140 */       worldServer.func_73056_e()) {
/* 142 */       if (worldServer.func_82736_K().func_82766_b("doDaylightCycle")) {
/* 144 */         WorldServer serverOverWorld = DimensionManager.getWorld(0);
/* 145 */         long i = serverOverWorld.func_72820_D() + 24000L;
/* 146 */         serverOverWorld.func_72877_b(i - i % 24000L);
/*     */       } 
/* 149 */       SMReflectionHelper.setallPlayersSleeping(worldServer, false);
/* 150 */       Iterator<EntityPlayer> iterator = worldServer.field_73010_i.iterator();
/* 152 */       while (iterator.hasNext()) {
/* 154 */         EntityPlayer entityplayer = iterator.next();
/* 156 */         if (entityplayer.func_70608_bn())
/* 158 */           entityplayer.func_70999_a(false, false, true); 
/*     */       } 
/* 161 */       worldServer.field_73011_w.resetRainAndThunder();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void canselLightGapUpdate(IChunkProvider chunkProvider_Client) {
/*     */     try {
/* 172 */       if (field_chunkListing == null)
/* 173 */         field_chunkListing = ReflectionHelper.findField(ChunkProviderClient.class, new String[] { SMReflectionHelperClient.FIELD_NAME_chunkListing[0], SMReflectionHelperClient.FIELD_NAME_chunkListing[1] }); 
/* 175 */       if (field_isGapLightingUpdated == null)
/* 176 */         field_isGapLightingUpdated = ReflectionHelper.findField(Chunk.class, new String[] { SMReflectionHelperClient.FIELD_NAME_isGapLightingUpdated[0], SMReflectionHelperClient.FIELD_NAME_isGapLightingUpdated[1] }); 
/* 178 */       if (field_queuedLightChecks == null)
/* 179 */         field_queuedLightChecks = ReflectionHelper.findField(Chunk.class, new String[] { SMReflectionHelperClient.FIELD_NAME_queuedLightChecks[0], SMReflectionHelperClient.FIELD_NAME_queuedLightChecks[1] }); 
/* 181 */       Iterator<Chunk> iterator = ((List<?>)field_chunkListing.get(chunkProvider_Client)).iterator();
/* 182 */       while (iterator.hasNext()) {
/* 184 */         Chunk chunk = iterator.next();
/* 185 */         field_isGapLightingUpdated.setBoolean(chunk, false);
/* 186 */         field_queuedLightChecks.setInt(chunk, 4096);
/*     */       } 
/* 188 */     } catch (Exception ex) {
/* 189 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void showGrappleGunGuideMessage() {
/* 194 */     GameSettings gamesettings = (Minecraft.func_71410_x()).field_71474_y;
/* 195 */     (Minecraft.func_71410_x()).field_71456_v.func_110326_a(I18n.func_135052_a("item.g_rapplegun.keyguide", new Object[] { GameSettings.func_74298_c(gamesettings.field_74311_E.func_151463_i()) }), false);
/*     */   }
/*     */   
/*     */   public void setRemainingHighlightTicksOFF() {
/* 199 */     SMReflectionHelperClient.setRemainingHighlightTicks((Minecraft.func_71410_x()).field_71456_v, 0);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */