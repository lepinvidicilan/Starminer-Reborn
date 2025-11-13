/*    */ package jp.mc.ancientred.starminer.basics.item;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ public class ItemGArrow extends Item {
/*    */   public ItemGArrow() {
/* 15 */     func_111206_d("arrow");
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
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon getIcon(ItemStack stack, int pass) {
/* 32 */     if (pass == 1)
/* 34 */       return ((ItemStarContoler)SMModContainer.StarControlerItem).starMarkIcon; 
/* 36 */     return super.getIcon(stack, pass);
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */