/*    */ package jp.mc.ancientred.starminer.basics.item;
/*    */ 
/*    */ import jp.mc.ancientred.starminer.api.Gravity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemSolarWindFan extends Item {
/*    */   private static final String STMN_INSTANTFLAG = "stmn;instantflag";
/*    */   
/*    */   public ItemSolarWindFan() {
/* 17 */     func_111206_d("starminer:uchiwa");
/* 18 */     func_77656_e(500);
/* 19 */     func_77625_d(1);
/*    */   }
/*    */   
/*    */   public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack itemStack) {
/* 23 */     if (!entityLiving.func_70093_af() && Gravity.isEntityZeroGravity((Entity)entityLiving)) {
/* 24 */       Vec3 look = entityLiving.func_70040_Z();
/* 26 */       NBTTagCompound nbttagcompound = itemStack.func_77978_p();
/* 27 */       if (nbttagcompound == null)
/* 28 */         itemStack.func_77982_d(nbttagcompound = new NBTTagCompound()); 
/* 31 */       if (nbttagcompound.func_74767_n("stmn;instantflag")) {
/* 32 */         double div = 3.0D;
/* 33 */         entityLiving.field_70159_w -= entityLiving.field_70159_w / div;
/* 34 */         if (Math.abs(entityLiving.field_70159_w) < 0.03D)
/* 35 */           entityLiving.field_70159_w = 0.0D; 
/* 37 */         entityLiving.field_70181_x -= entityLiving.field_70181_x / div;
/* 38 */         if (Math.abs(entityLiving.field_70181_x) < 0.03D)
/* 39 */           entityLiving.field_70181_x = 0.0D; 
/* 41 */         entityLiving.field_70179_y -= entityLiving.field_70179_y / div;
/* 42 */         if (Math.abs(entityLiving.field_70179_y) < 0.03D)
/* 43 */           entityLiving.field_70179_y = 0.0D; 
/*    */       } else {
/* 46 */         double speed = 0.06D;
/* 47 */         double min = -1.25D;
/* 48 */         double max = 1.25D;
/* 49 */         if ((look.field_72450_a > 0.0D && max >= entityLiving.field_70159_w) || (look.field_72450_a < 0.0D && min <= entityLiving.field_70159_w))
/* 51 */           entityLiving.field_70159_w += look.field_72450_a * speed; 
/* 53 */         if ((look.field_72448_b > 0.0D && max >= entityLiving.field_70181_x) || (look.field_72448_b < 0.0D && min <= entityLiving.field_70181_x))
/* 55 */           entityLiving.field_70181_x += look.field_72448_b * speed; 
/* 57 */         if ((look.field_72449_c > 0.0D && max >= entityLiving.field_70179_y) || (look.field_72449_c < 0.0D && min <= entityLiving.field_70179_y))
/* 59 */           entityLiving.field_70179_y += look.field_72449_c * speed; 
/*    */       } 
/* 63 */       itemStack.func_77972_a(1, entityLiving);
/*    */     } 
/* 66 */     return false;
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
/* 70 */     NBTTagCompound nbttagcompound = itemStack.func_77978_p();
/* 71 */     if (nbttagcompound == null)
/* 72 */       itemStack.func_77982_d(nbttagcompound = new NBTTagCompound()); 
/* 75 */     nbttagcompound.func_74757_a("stmn;instantflag", true);
/* 76 */     player.func_71038_i();
/* 77 */     nbttagcompound.func_74757_a("stmn;instantflag", false);
/* 78 */     nbttagcompound.func_82580_o("stmn;instantflag");
/* 80 */     return itemStack;
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */