/*    */ package jp.mc.ancientred.starminer.core;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
/*    */ import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
/*    */ import java.io.File;
/*    */ import java.util.Map;
/*    */ 
/*    */ @IFMLLoadingPlugin.MCVersion("1.7.10")
/*    */ public class SMPlugin implements IFMLLoadingPlugin {
/* 12 */   public static boolean RUNTIME_DEOBF = false;
/*    */   
/*    */   public static File forgeLocation;
/*    */   
/*    */   public String getAccessTransformerClass() {
/* 17 */     return "jp.mc.ancientred.starminer.core.SMAccessTransformer";
/*    */   }
/*    */   
/*    */   public String[] getASMTransformerClass() {
/* 23 */     return new String[] { "jp.mc.ancientred.starminer.core.SMTransformer" };
/*    */   }
/*    */   
/*    */   public String getModContainerClass() {
/* 29 */     return "jp.mc.ancientred.starminer.core.SMCoreModContainer";
/*    */   }
/*    */   
/*    */   public String getSetupClass() {
/* 35 */     return null;
/*    */   }
/*    */   
/*    */   public void injectData(Map<String, Object> data) {
/* 41 */     RUNTIME_DEOBF = (Boolean)data.get("runtimeDeobfuscationEnabled");
/* 42 */     forgeLocation = (File)data.get("coremodLocation");
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */