/*    */ package jp.mc.ancientred.starminer.basics.common;
/*    */ 
/*    */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*    */ import jp.mc.ancientred.starminer.basics.dimention.MapFromSky;
/*    */ import jp.mc.ancientred.starminer.basics.gui.ContainerStarCore;
/*    */ import jp.mc.ancientred.starminer.basics.packet.SMPacketHandlerServer;
/*    */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityGravityGenerator;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.launchwrapper.LogWrapper;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraft.world.chunk.IChunkProvider;
/*    */ import net.minecraftforge.event.world.WorldEvent;
/*    */ import org.apache.logging.log4j.Level;
/*    */ 
/*    */ public class CommonProxyServer extends CommonProxy {
/*    */   public World getClientWorld() {
/* 27 */     return null;
/*    */   }
/*    */   
/*    */   public void registerNetworkHandler() {
/* 31 */     SMModContainer.channel.register(new SMPacketHandlerServer());
/*    */   }
/*    */   
/*    */   public void registerRenderHelper() {}
/*    */   
/*    */   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
/* 37 */     if (ID == SMModContainer.guiStarCoreId) {
/* 38 */       TileEntity te = world.func_147438_o(x, y, z);
/* 39 */       if (te != null && te instanceof TileEntityGravityGenerator)
/* 40 */         return new ContainerStarCore(player, (TileEntityGravityGenerator)te); 
/*    */     } 
/* 43 */     return null;
/*    */   }
/*    */   
/*    */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
/* 47 */     return null;
/*    */   }
/*    */   
/*    */   public void handleWorldLoadEvent(WorldEvent.Load event) {
/* 52 */     if (event.world.field_73011_w.field_76574_g == 0 && event.world.field_73011_w instanceof net.minecraft.world.WorldProviderSurface) {
/* 53 */       LogWrapper.log(Level.INFO, "[Starminer]Creating dimention 0(surface) ground texture at server....", new Object[0]);
/* 54 */       MapFromSky.createMapDataFromSky(event.world);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void doWakeupAll(WorldServer worldServer) {}
/*    */   
/*    */   public void canselLightGapUpdate(IChunkProvider chunkProvider_Client) {}
/*    */   
/*    */   public void showGrappleGunGuideMessage() {}
/*    */   
/*    */   public void setRemainingHighlightTicksOFF() {}
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */