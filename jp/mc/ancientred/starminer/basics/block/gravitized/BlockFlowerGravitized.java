/*    */ package jp.mc.ancientred.starminer.basics.block.gravitized;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ public class BlockFlowerGravitized extends BlockBushGravitized {
/*    */   private int flowerType;
/*    */   
/*    */   public BlockFlowerGravitized(int flowerType) {
/* 20 */     this.flowerType = flowerType;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int par1, int par2) {
/* 26 */     if (this.flowerType == 0)
/* 27 */       return Blocks.field_150327_N.func_149691_a(par1, par2); 
/* 29 */     return Blocks.field_150328_O.func_149691_a(par1, par2);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister p_149651_1_) {}
/*    */   
/*    */   public int func_149692_a(int par1) {
/* 40 */     return par1;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149666_a(Item parItem, CreativeTabs parTabs, List parList) {
/* 49 */     int subItemCount = (this.flowerType == 0) ? 1 : 9;
/* 50 */     for (int i = 0; i < subItemCount; i++)
/* 51 */       parList.add(new ItemStack(parItem, 1, i)); 
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */