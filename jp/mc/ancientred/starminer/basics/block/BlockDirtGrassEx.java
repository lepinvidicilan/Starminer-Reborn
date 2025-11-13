/*    */ package jp.mc.ancientred.starminer.basics.block;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.ColorizerGrass;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ 
/*    */ public class BlockDirtGrassEx extends Block {
/*    */   public BlockDirtGrassEx() {
/* 24 */     super(Material.field_151578_c);
/* 25 */     func_149711_c(0.5F);
/* 26 */     func_149672_a(Block.field_149767_g);
/*    */   }
/*    */   
/*    */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
/* 31 */     return true;
/*    */   }
/*    */   
/*    */   public boolean func_149662_c() {
/* 35 */     return true;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public boolean func_149686_d() {
/* 41 */     return false;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister par1IconRegister) {}
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int side, int meta) {
/* 49 */     if ((meta & 0x8) == 0)
/* 50 */       return Blocks.field_150349_c.func_149691_a(1, 0); 
/* 52 */     return Blocks.field_150458_ak.func_149691_a(1, 1);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_149645_b() {
/* 58 */     return 4341802;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_149635_D() {
/* 64 */     double d0 = 0.5D;
/* 65 */     double d1 = 1.0D;
/* 66 */     return ColorizerGrass.func_77480_a(d0, d1);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_149741_i(int par1) {
/* 73 */     return func_149635_D();
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */