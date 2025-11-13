/*     */ package jp.mc.ancientred.starminer.basics.block.gravitized;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.ColorizerFoliage;
/*     */ import net.minecraft.world.ColorizerGrass;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IShearable;
/*     */ 
/*     */ public class BlockTallGrassGravitized extends BlockBushGravitized implements IShearable {
/*     */   public BlockTallGrassGravitized() {
/*  32 */     super(Material.field_151582_l, 0.4D, 0.8D);
/*     */   }
/*     */   
/*     */   public int func_149679_a(int par1, Random par2Random) {
/*  38 */     return 1 + par2Random.nextInt(par1 * 2 + 1);
/*     */   }
/*     */   
/*     */   public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
/*  44 */     super.func_149636_a(par1World, par2EntityPlayer, par3, par4, par5, par6);
/*     */   }
/*     */   
/*     */   public boolean allowPlantOn(Block block, int meta) {
/*  48 */     if (meta == 1)
/*  48 */       return (block == Blocks.field_150433_aE || block == Blocks.field_150435_aG || super.allowPlantOn(block, meta)); 
/*  49 */     if (meta == 2)
/*  49 */       return (block != Blocks.field_150350_a); 
/*  50 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149643_k(World par1World, int par2, int par3, int par4) {
/*  55 */     return par1World.func_72805_g(par2, par3, par4);
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
/*  60 */     ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
/*  61 */     if (world.field_73012_v.nextInt(8) != 0)
/*  63 */       return ret; 
/*  66 */     if (world.field_73012_v.nextInt(5) == 0)
/*  68 */       ret.add(new ItemStack(SMModContainer.SeedGravizedItem)); 
/*  70 */     return ret;
/*     */   }
/*     */   
/*     */   public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
/*  76 */     return true;
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
/*  82 */     ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
/*  83 */     ret.add(new ItemStack((Block)this, 1, world.func_72805_g(x, y, z)));
/*  84 */     return ret;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/*  93 */     return Blocks.field_150329_H.func_149691_a(par1, par2);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister) {}
/*     */   
/*     */   public int idDropped(int par1, Random par2Random, int par3) {
/* 104 */     return -1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
/* 110 */     par3List.add(new ItemStack(par1, 1, 1));
/* 111 */     par3List.add(new ItemStack(par1, 1, 2));
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149720_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 117 */     int l = par1IBlockAccess.func_72805_g(par2, par3, par4);
/* 118 */     return (l == 0) ? 16777215 : par1IBlockAccess.func_72807_a(par2, par4).func_150558_b(par2, par3, par4);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149635_D() {
/* 124 */     double d0 = 0.5D;
/* 125 */     double d1 = 1.0D;
/* 126 */     return ColorizerGrass.func_77480_a(d0, d1);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149741_i(int par1) {
/* 132 */     return (par1 == 0) ? 16777215 : ColorizerFoliage.func_77468_c();
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */