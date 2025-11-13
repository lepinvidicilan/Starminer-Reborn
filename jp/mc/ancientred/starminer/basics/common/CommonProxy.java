/*    */ package jp.mc.ancientred.starminer.basics.common;
/*    */ 
/*    */ import cpw.mods.fml.common.network.IGuiHandler;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraft.world.chunk.IChunkProvider;
/*    */ import net.minecraftforge.event.world.WorldEvent;
/*    */ 
/*    */ public class CommonProxy implements IGuiHandler {
/*    */   public World getClientWorld() {
/* 16 */     return null;
/*    */   }
/*    */   
/*    */   public void registerNetworkHandler() {}
/*    */   
/*    */   public void registerRenderHelper() {}
/*    */   
/*    */   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
/* 20 */     return null;
/*    */   }
/*    */   
/*    */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
/* 22 */     return null;
/*    */   }
/*    */   
/*    */   public void handleWorldLoadEvent(WorldEvent.Load event) {}
/*    */   
/*    */   public void doWakeupAll(WorldServer worldServer) {}
/*    */   
/*    */   public void canselLightGapUpdate(IChunkProvider chunkProviderClient) {}
/*    */   
/*    */   public void showGrappleGunGuideMessage() {}
/*    */   
/*    */   public void setRemainingHighlightTicksOFF() {}
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */