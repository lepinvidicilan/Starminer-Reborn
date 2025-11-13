/*    */ package jp.mc.ancientred.starminer.basics.dimention;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.Teleporter;
/*    */ import net.minecraft.world.WorldServer;
/*    */ 
/*    */ public class TeleporterForSpace extends Teleporter {
/*    */   public TeleporterForSpace(WorldServer par1WorldServer) {
/* 15 */     super(par1WorldServer);
/*    */   }
/*    */   
/*    */   public void func_77185_a(Entity par1Entity, double par2, double par4, double par6, float par8) {
/* 23 */     par1Entity.func_70012_b(par1Entity.field_70165_t, par1Entity.field_70163_u, par1Entity.field_70161_v, par1Entity.field_70177_z, par1Entity.field_70125_A);
/*    */   }
/*    */   
/*    */   public void func_85189_a(long par1) {}
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */