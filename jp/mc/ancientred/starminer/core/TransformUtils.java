/*    */ package jp.mc.ancientred.starminer.core;
/*    */ 
/*    */ import jp.mc.ancientred.starminer.core.entity.ExtendedPropertyGravity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ public class TransformUtils {
/*    */   public static Vec3 fixEyePositionByGravityClient(Entity entity, Vec3 vec3) {
/* 10 */     ExtendedPropertyGravity gravity = ExtendedPropertyGravity.getExtendedPropertyGravity(entity);
/* 11 */     float fixHeight = entity.field_70129_M - entity.field_70130_N / 2.0F;
/* 12 */     vec3.field_72450_a -= (double)(fixHeight * gravity.gravityDirection.shiftEyeX);
/* 13 */     vec3.field_72448_b -= (double)(fixHeight * gravity.gravityDirection.shiftEyeY);
/* 14 */     vec3.field_72449_c -= (double)(fixHeight * gravity.gravityDirection.shiftEyeZ);
/* 15 */     return vec3;
/*    */   }
/*    */   
/*    */   public static Vec3 fixEyePositionByGravityServer(EntityPlayer entity, Vec3 vec3) {
/* 18 */     ExtendedPropertyGravity gravity = ExtendedPropertyGravity.getExtendedPropertyGravity((Entity)entity);
/* 19 */     float fixHeight = entity.func_70047_e() - entity.field_70130_N / 2.0F;
/* 20 */     vec3.field_72450_a -= (double)(fixHeight * gravity.gravityDirection.shiftEyeX);
/* 21 */     vec3.field_72448_b -= (double)(fixHeight * gravity.gravityDirection.shiftEyeY);
/* 22 */     vec3.field_72449_c -= (double)(fixHeight * gravity.gravityDirection.shiftEyeZ);
/* 23 */     return vec3;
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */