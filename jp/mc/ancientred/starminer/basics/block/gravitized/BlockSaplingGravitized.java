/*     */ package jp.mc.ancientred.starminer.basics.block.gravitized;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import jp.mc.ancientred.starminer.basics.block.DirectionConst;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.IGrowable;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraftforge.event.terraingen.TerrainGen;
/*     */ 
/*     */ public class BlockSaplingGravitized extends BlockBushGravitized implements IGrowable {
/*     */   public BlockSaplingGravitized() {
/*  43 */     super(0.4D, 0.8D);
/*  44 */     func_149711_c(0.0F);
/*  45 */     func_149672_a(field_149779_h);
/*     */   }
/*     */   
/*     */   public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  50 */     if (!par1World.field_72995_K) {
/*  52 */       super.func_149674_a(par1World, par2, par3, par4, par5Random);
/*  54 */       if (par1World.func_72957_l(par2, par3, par4) >= 7 && par5Random.nextInt(7) == 0)
/*  56 */         func_149879_c(par1World, par2, par3, par4, par5Random); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_149879_c(World par1World, int par2, int par3, int par4, Random par5Random) {
/*  63 */     int l = par1World.func_72805_g(par2, par3, par4);
/*  65 */     if ((l & 0x8) == 0) {
/*  67 */       par1World.func_72921_c(par2, par3, par4, l | 0x8, 4);
/*     */     } else {
/*  71 */       func_149878_d(par1World, par2, par3, par4, par5Random);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_149878_d(World parWorld, int parX, int parY, int parZ, Random parRand) {
/*  76 */     if (!TerrainGen.saplingGrowTree(parWorld, parRand, parX, parY, parZ))
/*     */       return; 
/*     */     int dir;
/*  79 */     if ((dir = DirectionConst.getPlantGravityDirection((IBlockAccess)parWorld, parX, parY, parZ)) == -1)
/*     */       return; 
/*  83 */     int meta = parWorld.func_72805_g(parX, parY, parZ) & 0x7;
/*  84 */     Object treeGen = null;
/*  85 */     int xRel = 0;
/*  86 */     int zRel = 0;
/*  87 */     boolean willGrowHugeTree = false;
/*  90 */     int multipOrAddTreeHight = (parWorld.field_73011_w instanceof jp.mc.ancientred.starminer.api.IZeroGravityWorldProvider) ? 2 : 1;
/*  92 */     switch (meta) {
/*     */       default:
/*  96 */         treeGen = new WorldGenTreesG(true, 4 * multipOrAddTreeHight, 0, 0, false, dir);
/*     */         break;
/*     */       case 1:
/* 114 */         if (!willGrowHugeTree) {
/* 116 */           zRel = 0;
/* 117 */           xRel = 0;
/* 118 */           treeGen = new WorldGenTaiga2G(true, 6 + multipOrAddTreeHight, dir);
/*     */         } 
/*     */         break;
/*     */       case 2:
/* 123 */         treeGen = new WorldGenForestG(true, 5 + multipOrAddTreeHight, dir);
/*     */         break;
/*     */       case 3:
/* 142 */         if (!willGrowHugeTree) {
/* 144 */           zRel = 0;
/* 145 */           xRel = 0;
/* 146 */           treeGen = new WorldGenTreesG(true, 4 * multipOrAddTreeHight + parRand.nextInt(7), 3, 3, false, dir);
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 151 */         treeGen = new WorldGenSavannaTreeG(true, 5 + multipOrAddTreeHight, dir);
/*     */         break;
/*     */       case 5:
/* 171 */         if (!willGrowHugeTree) {
/* 174 */           zRel = 0;
/* 175 */           xRel = 0;
/* 176 */           treeGen = new WorldGenTreesG(true, 4 * multipOrAddTreeHight + parRand.nextInt(7), 1, 1, false, dir, true);
/*     */         } 
/*     */         break;
/*     */     } 
/* 180 */     Block block = Blocks.field_150350_a;
/* 182 */     if (willGrowHugeTree) {
/* 184 */       parWorld.func_147465_d(parX + xRel, parY, parZ + zRel, block, 0, 4);
/* 185 */       parWorld.func_147465_d(parX + xRel + 1, parY, parZ + zRel, block, 0, 4);
/* 186 */       parWorld.func_147465_d(parX + xRel, parY, parZ + zRel + 1, block, 0, 4);
/* 187 */       parWorld.func_147465_d(parX + xRel + 1, parY, parZ + zRel + 1, block, 0, 4);
/*     */     } else {
/* 191 */       parWorld.func_147465_d(parX, parY, parZ, block, 0, 4);
/*     */     } 
/* 194 */     if (!((WorldGenerator)treeGen).func_76484_a(parWorld, parRand, parX + xRel, parY, parZ + zRel))
/* 196 */       if (willGrowHugeTree) {
/* 198 */         parWorld.func_147465_d(parX + xRel, parY, parZ + zRel, (Block)this, meta, 4);
/* 199 */         parWorld.func_147465_d(parX + xRel + 1, parY, parZ + zRel, (Block)this, meta, 4);
/* 200 */         parWorld.func_147465_d(parX + xRel, parY, parZ + zRel + 1, (Block)this, meta, 4);
/* 201 */         parWorld.func_147465_d(parX + xRel + 1, parY, parZ + zRel + 1, (Block)this, meta, 4);
/*     */       } else {
/* 205 */         parWorld.func_147465_d(parX, parY, parZ, (Block)this, meta, 4);
/*     */       }  
/*     */   }
/*     */   
/*     */   public boolean func_149880_a(World parWorld, int parX, int parY, int parZ, int parMeta) {
/* 211 */     return (parWorld.func_147439_a(parX, parY, parZ) == this && (parWorld.func_72805_g(parX, parY, parZ) & 0x7) == parMeta);
/*     */   }
/*     */   
/*     */   public int func_149692_a(int p_149692_1_) {
/* 216 */     return MathHelper.func_76125_a(p_149692_1_ & 0x7, 0, 5);
/*     */   }
/*     */   
/*     */   public boolean func_149851_a(World parWorld, int parX, int parY, int parZ, boolean p_149851_5_) {
/* 223 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_149852_a(World parWorld, Random parRand, int parX, int parY, int parZ) {
/* 228 */     return ((double)parWorld.field_73012_v.nextFloat() < 0.45D);
/*     */   }
/*     */   
/*     */   public void func_149853_b(World parWorld, Random parRand, int parX, int parY, int parZ) {
/* 232 */     func_149879_c(parWorld, parX, parY, parZ, parRand);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
/* 241 */     p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
/* 242 */     p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
/* 243 */     p_149666_3_.add(new ItemStack(p_149666_1_, 1, 2));
/* 244 */     p_149666_3_.add(new ItemStack(p_149666_1_, 1, 3));
/* 245 */     p_149666_3_.add(new ItemStack(p_149666_1_, 1, 4));
/* 246 */     p_149666_3_.add(new ItemStack(p_149666_1_, 1, 5));
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/* 259 */     return Blocks.field_150345_g.func_149691_a(par1, par2);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister) {}
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */