/*    */ package jp.mc.ancientred.starminer.core.obfuscar;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.Method;
/*    */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.network.NetHandlerPlayServer;
/*    */ 
/*    */ public class SMCoreReflectionHelper {
/*    */   public static Method method_rotateCorpsePublic;
/*    */   
/* 21 */   public static float[] method_rotateCorpsePublic_args = new float[3];
/*    */   
/*    */   public static Field field_fire;
/*    */   
/*    */   public static Field field_hasMoved;
/*    */   
/*    */   public static final void initMethodAccessRotateCorpsePublic() {
/*    */     try {
/* 25 */       method_rotateCorpsePublic = RendererLivingEntity.class.getDeclaredMethod("rotateCorpsePublic", EntityLivingBase.class, float[].class);
/* 26 */     } catch (Exception ex) {
/* 27 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static final void initFiledAccessFire() {
/*    */     try {
/* 37 */       field_fire = ReflectionHelper.findField(Entity.class, new String[] { "fire", "field_70151_c" });
/* 38 */     } catch (Exception ex) {
/* 39 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void ignoreHasMovedFlg(EntityPlayer par5EntityPlayer) {
/* 48 */     if (par5EntityPlayer instanceof EntityPlayerMP)
/*    */       try {
/* 50 */         if (field_hasMoved == null)
/* 51 */           field_hasMoved = ReflectionHelper.findField(NetHandlerPlayServer.class, new String[] { "hasMoved", "field_147380_r" }); 
/* 53 */         field_hasMoved.setBoolean(((EntityPlayerMP)par5EntityPlayer).field_71135_a, true);
/* 54 */       } catch (Exception ex) {
/* 55 */         ex.printStackTrace();
/*    */       }  
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */