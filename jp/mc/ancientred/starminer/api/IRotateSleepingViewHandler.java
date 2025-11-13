/*    */ package jp.mc.ancientred.starminer.api;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public interface IRotateSleepingViewHandler {
/* 12 */   public static final ArrayList<IRotateSleepingViewHandler> handlerList = new ArrayList<IRotateSleepingViewHandler>();
/*    */   
/*    */   boolean rotateSleepingFPView();
/*    */   
/*    */   boolean rotateTPPlayerSleeping(EntityPlayer paramEntityPlayer);
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */