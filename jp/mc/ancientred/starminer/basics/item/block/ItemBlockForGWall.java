/*    */ package jp.mc.ancientred.starminer.basics.item.block;
/*    */ 
/*    */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemBlockForGWall extends ItemBlock {
/*    */   public ItemBlockForGWall(Block block) {
/* 14 */     super(block);
/*    */   }
/*    */   
/*    */   public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
/* 23 */     Block block = par3World.func_147439_a(par4, par5, par6);
/* 24 */     if (block == SMModContainer.GravityWallBlock)
/* 25 */       return false; 
/* 27 */     return super.func_77648_a(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */