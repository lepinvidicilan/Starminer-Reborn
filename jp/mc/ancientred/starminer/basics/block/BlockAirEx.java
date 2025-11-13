/*    */ package jp.mc.ancientred.starminer.basics.block;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ public class BlockAirEx extends Block {
/*    */   public BlockAirEx() {
/* 18 */     super(Material.field_151579_a);
/* 19 */     func_149676_a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public boolean func_149662_c() {
/* 22 */     return false;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int par1, int par2) {
/* 27 */     return Blocks.field_150359_w.func_149691_a(par1, par2);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister par1IconRegister) {}
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */