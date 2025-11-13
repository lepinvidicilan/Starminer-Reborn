/*    */ package jp.mc.ancientred.starminer.core;
/*    */ 
/*    */ import jp.mc.ancientred.starminer.core.common.VecUtils;
/*    */ import jp.mc.ancientred.starminer.core.entity.ExtendedPropertyGravity;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.network.NetHandlerPlayServer;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.chunk.Chunk;
/*    */ 
/*    */ public class TransformServerHelper {
/*    */   public static boolean jumpOverKickIllegalStance() {
/* 21 */     return CoreConfig.skipIllegalStanceCheck;
/*    */   }
/*    */   
/*    */   public static boolean jumpOverKickFloatTooLong(NetHandlerPlayServer handler) {
/* 24 */     if (handler.field_147369_b == null)
/* 24 */       return false; 
/* 25 */     if (handler.field_147369_b.field_70170_p == null)
/* 25 */       return false; 
/* 27 */     if (handler.field_147369_b.field_70170_p.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider)
/* 29 */       return true; 
/* 32 */     if (ExtendedPropertyGravity.isEntityAbnormalGravity((Entity)handler.field_147369_b))
/* 34 */       return true; 
/* 37 */     return false;
/*    */   }
/*    */   
/*    */   public static double pullGravityYInGravity(Entity entity) {
/* 40 */     if (entity.field_70170_p.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider)
/* 41 */       return 0.0D; 
/* 43 */     return 0.03999999910593033D;
/*    */   }
/*    */   
/*    */   public static MovingObjectPosition getMovingObjectPositionFromPlayerByGravity(World par1World, EntityPlayer par2EntityPlayer, boolean par3) {
/*    */     double d3;
/* 47 */     float f = 1.0F;
/* 48 */     float f1 = par2EntityPlayer.field_70127_C + (par2EntityPlayer.field_70125_A - par2EntityPlayer.field_70127_C) * f;
/* 49 */     float f2 = par2EntityPlayer.field_70126_B + (par2EntityPlayer.field_70177_z - par2EntityPlayer.field_70126_B) * f;
/* 50 */     double d0 = par2EntityPlayer.field_70169_q + (par2EntityPlayer.field_70165_t - par2EntityPlayer.field_70169_q) * (double)f;
/* 51 */     double d1 = par2EntityPlayer.field_70167_r + (par2EntityPlayer.field_70163_u - par2EntityPlayer.field_70167_r) * (double)f + (double)(par1World.field_72995_K ? (par2EntityPlayer.func_70047_e() - par2EntityPlayer.getDefaultEyeHeight()) : par2EntityPlayer.func_70047_e());
/* 52 */     double d2 = par2EntityPlayer.field_70166_s + (par2EntityPlayer.field_70161_v - par2EntityPlayer.field_70166_s) * (double)f;
/* 54 */     Vec3 vec3 = VecUtils.createVec3(d0, d1, d2);
/* 57 */     if (par2EntityPlayer instanceof EntityPlayerMP) {
/* 59 */       d3 = ((EntityPlayerMP)par2EntityPlayer).field_71134_c.getBlockReachDistance();
/* 60 */       vec3 = TransformUtils.fixEyePositionByGravityServer(par2EntityPlayer, vec3);
/*    */     } else {
/* 62 */       d3 = 5.0D;
/* 63 */       vec3 = TransformUtils.fixEyePositionByGravityClient((Entity)par2EntityPlayer, vec3);
/*    */     } 
/* 66 */     Vec3 vecLook = par2EntityPlayer.func_70676_i(1.0F);
/* 67 */     Vec3 vec31 = vec3.func_72441_c(vecLook.field_72450_a * d3, vecLook.field_72448_b * d3, vecLook.field_72449_c * d3);
/* 68 */     return par1World.func_147447_a(vec3, vec31, par3, !par3, false);
/*    */   }
/*    */   
/*    */   public static boolean blockLiquidOnBlockAddedHook(Block block, World par1World, int par2, int par3, int par4) {
/* 72 */     if (par1World.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider && 
/* 73 */       block != null) {
/* 74 */       if (block.func_149688_o() == Material.field_151586_h) {
/* 75 */         Chunk chunk = par1World.func_72964_e(par2 >> 4, par4 >> 4);
/* 76 */         chunk.func_150807_a(par2 & 0xF, par3, par4 & 0xF, Blocks.field_150403_cj, 0);
/* 78 */         return true;
/*    */       } 
/* 80 */       if (block.func_149688_o() == Material.field_151587_i) {
/* 81 */         Chunk chunk = par1World.func_72964_e(par2 >> 4, par4 >> 4);
/* 82 */         chunk.func_150807_a(par2 & 0xF, par3, par4 & 0xF, Blocks.field_150343_Z, 0);
/* 84 */         return true;
/*    */       } 
/*    */     } 
/* 88 */     return false;
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */