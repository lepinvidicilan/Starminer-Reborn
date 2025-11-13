/*    */ package jp.mc.ancientred.starminer.basics.block;
/*    */ 
/*    */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityNavigator;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.ITileEntityProvider;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockNavigator extends Block implements ITileEntityProvider {
/*    */   public BlockNavigator() {
/* 22 */     super(Material.field_151576_e);
/* 23 */     func_149711_c(1.0F);
/* 24 */     func_149752_b(1.0F);
/* 25 */     func_149715_a(1.0F);
/* 27 */     this.field_149759_B = 0.1875D;
/* 28 */     this.field_149760_C = 0.1875D;
/* 29 */     this.field_149754_D = 0.1875D;
/* 30 */     this.field_149755_E = 0.8125D;
/* 31 */     this.field_149756_F = 0.8125D;
/* 32 */     this.field_149757_G = 0.8125D;
/*    */   }
/*    */   
/*    */   public TileEntity func_149915_a(World par1World, int metadata) {
/* 36 */     return new TileEntityNavigator();
/*    */   }
/*    */   
/*    */   public boolean isOn(int blockMetadata) {
/* 39 */     return ((blockMetadata & 0x1) != 0);
/*    */   }
/*    */   
/*    */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5Block) {
/* 48 */     int meta = par1World.func_72805_g(par2, par3, par4);
/* 49 */     boolean isPowerOn = ((meta & 0x1) != 0);
/* 50 */     boolean isHavingPower = par1World.func_72864_z(par2, par3, par4);
/* 52 */     if (isPowerOn != isHavingPower) {
/* 53 */       par1World.func_72921_c(par2, par3, par4, isHavingPower ? 1 : 0, 2);
/* 54 */       par1World.func_147458_c(par2, par3, par4, par2, par3, par4);
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
/* 60 */     if (!par1World.field_72995_K) {
/* 62 */       if (!doesPlayerHasTorchOnHand(par5EntityPlayer))
/* 62 */         return false; 
/* 65 */       TileEntity te = par1World.func_147438_o(par2, par3, par4);
/* 66 */       if (!(te instanceof TileEntityNavigator))
/* 66 */         return false; 
/* 67 */       TileEntityNavigator teNavi = (TileEntityNavigator)te;
/* 68 */       teNavi.activate();
/* 69 */       par1World.func_147471_g(par2, par3, par4);
/*    */     } 
/* 71 */     return true;
/*    */   }
/*    */   
/*    */   public boolean func_149686_d() {
/* 77 */     return false;
/*    */   }
/*    */   
/*    */   public boolean func_149662_c() {
/* 81 */     return false;
/*    */   }
/*    */   
/*    */   public int func_149645_b() {
/* 85 */     return 4341807;
/*    */   }
/*    */   
/*    */   public static final boolean doesPlayerHasTorchOnHand(EntityPlayer parEntityPlayer) {
/* 88 */     ItemStack itemNow = parEntityPlayer.func_71045_bC();
/* 89 */     return (itemNow != null && itemNow.func_77973_b() == Item.func_150898_a(Blocks.field_150478_aa));
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */