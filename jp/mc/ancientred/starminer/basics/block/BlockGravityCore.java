/*     */ package jp.mc.ancientred.starminer.basics.block;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityGravityGenerator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.ITileEntityProvider;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockGravityCore extends BlockContainer implements ITileEntityProvider {
/*  28 */   private final Random random = new Random();
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon normalIcon;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon itemIcon;
/*     */   
/*     */   public BlockGravityCore() {
/*  36 */     super(Material.field_151576_e);
/*  37 */     func_149711_c(1.0F);
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World par1World, int metadata) {
/*  41 */     return new TileEntityGravityGenerator();
/*     */   }
/*     */   
/*     */   public boolean func_149686_d() {
/*  45 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c() {
/*  49 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b() {
/*  53 */     return 4341803;
/*     */   }
/*     */   
/*     */   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
/*  58 */     if (!par1World.field_72995_K)
/*  60 */       par5EntityPlayer.openGui(SMModContainer.instance, SMModContainer.guiStarCoreId, par1World, par2, par3, par4); 
/*  62 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister) {
/*  67 */     this.normalIcon = par1IconRegister.func_94245_a("starminer:g_core");
/*  68 */     this.itemIcon = par1IconRegister.func_94245_a("starminer:g_coreItem");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/*  73 */     return this.normalIcon;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getCoreItemIcon() {
/*  78 */     return this.itemIcon;
/*     */   }
/*     */   
/*     */   public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
/*  83 */     TileEntityGravityGenerator tileentitychest = (TileEntityGravityGenerator)par1World.func_147438_o(par2, par3, par4);
/*  85 */     if (tileentitychest != null) {
/*  87 */       for (int j1 = 0; j1 < tileentitychest.func_70302_i_(); j1++) {
/*  89 */         ItemStack itemstack = tileentitychest.func_70301_a(j1);
/*  91 */         if (itemstack != null) {
/*     */           EntityItem entityitem;
/*  93 */           float f = this.random.nextFloat() * 0.8F + 0.1F;
/*  94 */           float f1 = this.random.nextFloat() * 0.8F + 0.1F;
/*  97 */           for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.field_77994_a > 0; par1World.func_72838_d((Entity)entityitem)) {
/*  99 */             int k1 = this.random.nextInt(21) + 10;
/* 101 */             if (k1 > itemstack.field_77994_a)
/* 103 */               k1 = itemstack.field_77994_a; 
/* 106 */             itemstack.field_77994_a -= k1;
/* 107 */             entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
/* 108 */             float f3 = 0.05F;
/* 109 */             entityitem.field_70159_w = (double)((float)this.random.nextGaussian() * f3);
/* 110 */             entityitem.field_70181_x = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
/* 111 */             entityitem.field_70179_y = (double)((float)this.random.nextGaussian() * f3);
/* 113 */             if (itemstack.func_77942_o())
/* 115 */               entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b()); 
/*     */           } 
/*     */         } 
/*     */       } 
/* 121 */       par1World.func_147453_f(par2, par3, par4, par5);
/*     */     } 
/* 124 */     super.func_149749_a(par1World, par2, par3, par4, par5, par6);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */