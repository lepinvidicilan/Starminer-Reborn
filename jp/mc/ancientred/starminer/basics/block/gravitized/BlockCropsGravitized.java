/*     */ package jp.mc.ancientred.starminer.basics.block.gravitized;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import net.minecraft.block.IGrowable;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockCropsGravitized extends BlockBushGravitized implements IGrowable {
/*     */   public IIcon starMarkIcon;
/*     */   
/*     */   public static final double HEIGHT_D = 0.25D;
/*     */   
/*     */   public static final float HEIGHT_F = 0.25F;
/*     */   
/*     */   public BlockCropsGravitized() {
/*  30 */     super(0.5D, 0.25D);
/*     */   }
/*     */   
/*     */   public int func_149645_b() {
/*  35 */     return 4341800;
/*     */   }
/*     */   
/*     */   public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  40 */     func_149855_e(par1World, par2, par3, par4);
/*  42 */     if (par1World.func_72957_l(par2, par3, par4) >= 7) {
/*  44 */       int l = par1World.func_72805_g(par2, par3, par4);
/*  46 */       if (l < 7) {
/*  48 */         float f = 3.0F;
/*  50 */         if (par5Random.nextInt((int)(25.0F / f) + 1) == 0) {
/*  52 */           l++;
/*  53 */           par1World.func_72921_c(par2, par3, par4, l, 2);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_149690_a(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
/*  61 */     super.func_149690_a(par1World, par2, par3, par4, par5, par6, 0);
/*     */   }
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/*  67 */     ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);
/*  69 */     if (metadata >= 7)
/*  71 */       for (int n = 0; n < 3 + fortune; n++) {
/*  73 */         if (world.field_73012_v.nextInt(15) <= metadata)
/*  75 */           ret.add(new ItemStack(func_149866_i(), 1, 0)); 
/*     */       }  
/*  80 */     return ret;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3) {
/*  85 */     return (par1 == 7) ? func_149865_P() : func_149866_i();
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random par1Random) {
/*  90 */     return 1;
/*     */   }
/*     */   
/*     */   public void func_149863_m(World par1World, int par2, int par3, int par4) {
/*  95 */     int l = par1World.func_72805_g(par2, par3, par4) + MathHelper.func_76136_a(par1World.field_73012_v, 2, 5);
/*  97 */     if (l > 7)
/*  99 */       l = 7; 
/* 102 */     par1World.func_72921_c(par2, par3, par4, l, 2);
/*     */   }
/*     */   
/*     */   protected Item func_149866_i() {
/* 107 */     return SMModContainer.SeedGravizedItem;
/*     */   }
/*     */   
/*     */   protected Item func_149865_P() {
/* 112 */     return Items.field_151015_O;
/*     */   }
/*     */   
/*     */   public boolean func_149851_a(World parWorld, int parX, int parY, int parZ, boolean p_149851_5_) {
/* 119 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_149852_a(World parWorld, Random parRand, int parX, int parY, int parZ) {
/* 124 */     return (parWorld.func_72805_g(parX, parY, parZ) != 7);
/*     */   }
/*     */   
/*     */   public void func_149853_b(World parWorld, Random parRand, int parX, int parY, int parZ) {
/* 128 */     func_149863_m(parWorld, parX, parY, parZ);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Item func_149694_d(World par1World, int par2, int par3, int par4) {
/* 137 */     return func_149866_i();
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/* 143 */     return Blocks.field_150464_aj.func_149691_a(par1, par2);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister) {
/* 149 */     this.starMarkIcon = par1IconRegister.func_94245_a("starminer:starmark");
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */