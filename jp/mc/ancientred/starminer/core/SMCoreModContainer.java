/*     */ package jp.mc.ancientred.starminer.core;
/*     */ 
/*     */ import com.google.common.eventbus.EventBus;
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import cpw.mods.fml.common.DummyModContainer;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.LoadController;
/*     */ import cpw.mods.fml.common.Mod.Instance;
/*     */ import cpw.mods.fml.common.ModMetadata;
/*     */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.PlayerEvent;
/*     */ import cpw.mods.fml.common.network.FMLEventChannel;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import java.io.File;
/*     */ import java.util.Arrays;
/*     */ import jp.mc.ancientred.starminer.core.common.CoreProxy;
/*     */ import jp.mc.ancientred.starminer.core.common.CoreProxyClient;
/*     */ import jp.mc.ancientred.starminer.core.common.CoreProxyServer;
/*     */ import jp.mc.ancientred.starminer.core.entity.ExtendedPropertyGravity;
/*     */ import jp.mc.ancientred.starminer.core.packet.SMCorePacketSender;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ 
/*     */ public class SMCoreModContainer extends DummyModContainer {
/*     */   @cpw.mods.fml.common.Mod.Instance("modJ_StarMinerCore")
/*     */   public static SMCoreModContainer instance;
/*     */   
/*     */   public static CoreProxy proxy;
/*     */   
/*     */   public static final String coreNetworkChannelName = "StarminerCore";
/*     */   
/*     */   public static FMLEventChannel coreChannel;
/*     */   
/*     */   public static final int PACKET_TYPE_ATTRACT = 0;
/*     */   
/*     */   public static final int PACKET_TYPE_GCHANGE = 1;
/*     */   
/*     */   public SMCoreModContainer() {
/*  48 */     super(new ModMetadata());
/*  49 */     ModMetadata meta = getMetadata();
/*  51 */     meta.modId = "modJ_StarMinerCore";
/*  52 */     meta.name = "StarMinerCore";
/*  53 */     meta.version = "0.9.7";
/*  54 */     meta.authorList = Arrays.<String>asList("ARUBE(oANCIENTREDo)");
/*  55 */     meta.description = "It's for StarMinerMOD.";
/*  56 */     meta.url = "";
/*  57 */     meta.credits = "";
/*  58 */     meta.parent = "modJ_StarMiner";
/*  59 */     setEnabledState(true);
/*     */   }
/*     */   
/*     */   public boolean registerBus(EventBus bus, LoadController controller) {
/*  63 */     bus.register(this);
/*  64 */     return true;
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void preInit(FMLPreInitializationEvent event) {
/*  69 */     proxy = FMLCommonHandler.instance().getSide().isClient() ? new CoreProxyClient() : new CoreProxyServer();
/*     */     try {
/*  71 */       Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "Starminer.cfg"));
/*  72 */       config.load();
/*  75 */       config.getCategory("core_server_side_property");
/*  76 */       config.addCustomCategoryComment("core_server_side_property", "Affects server side only.");
/*  77 */       CoreConfig.skipIllegalStanceCheck = config.get("core_server_side_property", "skipIllegalStanceCheck", true).getBoolean(true);
/*  78 */       CoreConfig.illegalGStateTickToCheck = config.get("core_server_side_property", "unsynchronizedTickToCheck", 100).getInt(100);
/*  79 */       CoreConfig.showUnsynchronizedWarning = config.get("core_server_side_property", "showUnsynchronizedWarning", true).getBoolean(true);
/*  80 */       CoreConfig.unsynchronizedWarnToKick = config.get("core_server_side_property", "unsynchronizedWarnToKick", 0).getInt(0);
/*  82 */       if (config.hasChanged())
/*  84 */         config.save(); 
/*     */     } catch (Exception ex) {
/*  86 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void init(FMLInitializationEvent event) {
/*  92 */     Object handler = new SMCommonEventHandler();
/*  93 */     MinecraftForge.EVENT_BUS.register(handler);
/*  94 */     FMLCommonHandler.instance().bus().register(handler);
/*  97 */     coreChannel = NetworkRegistry.INSTANCE.newEventDrivenChannel("StarminerCore");
/*  98 */     proxy.registerNetworkHandler();
/*     */   }
/*     */   
/*     */   public class SMCommonEventHandler {
/*     */     @SubscribeEvent
/*     */     public void registerExtendedProperty(EntityEvent.EntityConstructing event) {
/* 104 */       event.entity.registerExtendedProperties("starminer.Gravity", new ExtendedPropertyGravity());
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public void handlePlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
/*     */       try {
/* 109 */         if (event.player instanceof EntityPlayerMP) {
/* 111 */           ExtendedPropertyGravity gProp = ExtendedPropertyGravity.getExtendedPropertyGravity((Entity)event.player);
/* 112 */           if (gProp != null && gProp.isAttracted)
/* 114 */             SMCorePacketSender.sendAttractedChangePacketToPlayer((EntityPlayerMP)event.player, true, true, gProp.attractedPosX, gProp.attractedPosY, gProp.attractedPosZ); 
/*     */         } 
/* 122 */       } catch (Exception ex) {
/* 123 */         ex.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */