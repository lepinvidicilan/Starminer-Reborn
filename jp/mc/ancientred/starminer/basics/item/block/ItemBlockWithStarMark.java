/*    */ package jp.mc.ancientred.starminer.basics.item.block;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*    */ import jp.mc.ancientred.starminer.basics.item.ItemStarContoler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ public class ItemBlockWithStarMark extends ItemBlock {
/*    */   public ItemBlockWithStarMark(Block p_i45328_1_) {
/* 15 */     super(p_i45328_1_);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public boolean func_77623_v() {
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int getRenderPasses(int metadata) {
/* 29 */     return 2;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon getIcon(ItemStack stack, int pass) {
/* 34 */     if (pass == 1)
/* 35 */       return ((ItemStarContoler)SMModContainer.StarControlerItem).starMarkIcon; 
/* 37 */     return super.getIcon(stack, pass);
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */