/*    */ package jp.mc.ancientred.starminer.basics.item;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ public class ItemGHook extends Item {
/*    */   public ItemGHook() {
/* 16 */     func_111206_d("arrow");
/* 17 */     func_77625_d(64);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public boolean func_77623_v() {
/* 23 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int getRenderPasses(int metadata) {
/* 28 */     return 3;
/*    */   }
/*    */   
/*    */   public int func_82790_a(ItemStack stack, int pass) {
/* 32 */     if (pass == 0)
/* 33 */       return 6728362; 
/* 35 */     return super.func_82790_a(stack, pass);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon getIcon(ItemStack stack, int pass) {
/* 41 */     if (pass == 0)
/* 43 */       return Items.field_151058_ca.getIcon(stack, pass); 
/* 45 */     if (pass == 2)
/* 47 */       return ((ItemStarContoler)SMModContainer.StarControlerItem).starMarkIcon; 
/* 49 */     return super.getIcon(stack, pass);
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */