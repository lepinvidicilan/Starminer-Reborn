/*    */ package jp.mc.ancientred.starminer.basics;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*    */ import java.lang.reflect.Field;
/*    */ import net.minecraft.client.gui.GuiIngame;
/*    */ import net.minecraft.client.renderer.RenderGlobal;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ 
/*    */ public class SMReflectionHelperClient {
/* 15 */   public static final String[] FIELD_NAME_chunkListing = new String[] { "chunkListing", "field_73237_c" };
/*    */   
/* 16 */   public static final String[] FIELD_NAME_isGapLightingUpdated = new String[] { "isGapLightingUpdated", "field_76650_s" };
/*    */   
/* 17 */   public static final String[] FIELD_NAME_queuedLightChecks = new String[] { "queuedLightChecks", "field_76649_t" };
/*    */   
/*    */   public static Field field_starGLCallList;
/*    */   
/*    */   public static Field field_remainingHighlightTicks;
/*    */   
/*    */   public static Field field_tesselator_instance;
/*    */   
/*    */   public static int getStarGLCallList(RenderGlobal renderGlobal) {
/*    */     try {
/* 24 */       if (field_starGLCallList == null)
/* 25 */         field_starGLCallList = ReflectionHelper.findField(RenderGlobal.class, new String[] { "starGLCallList", "field_72772_v" }); 
/* 27 */       return field_starGLCallList.getInt(renderGlobal);
/* 28 */     } catch (Exception ex) {
/* 29 */       ex.printStackTrace();
/* 31 */       return -1;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void setRemainingHighlightTicks(GuiIngame ingameGUI, int value) {
/*    */     try {
/* 39 */       if (field_remainingHighlightTicks == null)
/* 40 */         field_remainingHighlightTicks = ReflectionHelper.findField(GuiIngame.class, new String[] { "remainingHighlightTicks", "field_92017_k" }); 
/* 42 */       field_remainingHighlightTicks.setInt(ingameGUI, value);
/* 43 */     } catch (Exception ex) {
/* 44 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void setWrappedTesselator(Tessellator instance) {
/*    */     try {
/* 53 */       if (field_tesselator_instance == null) {
/* 54 */         field_tesselator_instance = ReflectionHelper.findField(Tessellator.class, new String[] { "instance", "field_78398_a" });
/* 55 */         Field modifiersField = Field.class.getDeclaredField("modifiers");
/* 56 */         modifiersField.setAccessible(true);
/* 57 */         modifiersField.setInt(field_tesselator_instance, field_tesselator_instance.getModifiers() & 0xFFFFFFEF);
/*    */       } 
/* 60 */       field_tesselator_instance.set(null, instance);
/* 61 */     } catch (Exception ex) {
/* 62 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */