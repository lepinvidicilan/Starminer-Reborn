/*    */ package jp.mc.ancientred.starminer.basics.block.gravitized;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ public class BlockPotatoGravitized extends BlockCropsGravitized {
/*    */   protected Item func_149866_i() {
/* 28 */     return SMModContainer.PotatoGravizedItem;
/*    */   }
/*    */   
/*    */   protected Item func_149865_P() {
/* 33 */     return SMModContainer.PotatoGravizedItem;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int par1, int par2) {
/* 56 */     return Blocks.field_150469_bN.func_149691_a(par1, par2);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister par1IconRegister) {}
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */