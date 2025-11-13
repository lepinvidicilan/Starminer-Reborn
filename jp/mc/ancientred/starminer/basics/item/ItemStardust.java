/*    */ package jp.mc.ancientred.starminer.basics.item;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ public class ItemStardust extends Item {
/*    */   public ItemStardust() {
/* 15 */     func_111206_d("glowstone_dust");
/* 16 */     func_77625_d(64);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public boolean func_77623_v() {
/* 22 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int getRenderPasses(int metadata) {
/* 27 */     return 2;
/*    */   }
/*    */   
/*    */   public int func_82790_a(ItemStack stack, int pass) {
/* 31 */     if (pass == 0)
/* 32 */       return 16746751; 
/* 34 */     return super.func_82790_a(stack, pass);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon getIcon(ItemStack stack, int pass) {
/* 40 */     if (pass == 1)
/* 42 */       return ((ItemStarContoler)SMModContainer.StarControlerItem).starMarkIcon; 
/* 44 */     return super.getIcon(stack, pass);
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */