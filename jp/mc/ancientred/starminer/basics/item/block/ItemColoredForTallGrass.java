/*     */ package jp.mc.ancientred.starminer.basics.item.block;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.block.DirectionConst;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.BlockCropsGravitized;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.IGravitizedPlants;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemColoredForTallGrass extends ItemBlock {
/*     */   private final Block theBlock;
/*     */   
/*  20 */   private String[] blockNames = new String[] { "shrub", "grass", "fern" };
/*     */   
/*     */   public ItemColoredForTallGrass(Block block) {
/*  24 */     super(block);
/*  25 */     this.theBlock = block;
/*  27 */     func_77656_e(0);
/*  28 */     func_77627_a(true);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack par1ItemStack, int par2) {
/*  36 */     return this.theBlock.func_149741_i(par1ItemStack.func_77960_j());
/*     */   }
/*     */   
/*     */   public int func_77647_b(int par1) {
/*  44 */     return par1;
/*     */   }
/*     */   
/*     */   public ItemColoredForTallGrass setBlockNames(String[] par1ArrayOfStr) {
/*  52 */     this.blockNames = par1ArrayOfStr;
/*  53 */     return this;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int par1) {
/*  59 */     return this.theBlock.func_149691_a(0, par1);
/*     */   }
/*     */   
/*     */   public String func_77667_c(ItemStack par1ItemStack) {
/*  68 */     if (this.blockNames == null)
/*  70 */       return super.func_77667_c(par1ItemStack); 
/*  74 */     int i = par1ItemStack.func_77960_j();
/*  75 */     return (i >= 0 && i < this.blockNames.length) ? (super.func_77667_c(par1ItemStack) + "." + this.blockNames[i]) : super.func_77667_c(par1ItemStack);
/*     */   }
/*     */   
/*     */   public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
/*  85 */     int meta = func_77647_b(par1ItemStack.func_77960_j());
/*  86 */     if (this.theBlock instanceof IGravitizedPlants && 
/*  87 */       !((IGravitizedPlants)this.theBlock).allowPlantOn(par3World.func_147439_a(x, y, z), meta))
/*  88 */       return false; 
/*  99 */     int dir = 0;
/* 100 */     int tgtX = x;
/* 101 */     int tgtY = y;
/* 102 */     int tgtZ = z;
/* 103 */     int gAirX = x;
/* 104 */     int gAirY = y;
/* 105 */     int gAirZ = z;
/* 106 */     switch (side) {
/*     */       case 0:
/* 108 */         tgtY--;
/* 109 */         dir = 1;
/* 110 */         gAirY -= 2;
/*     */         break;
/*     */       case 1:
/* 113 */         dir = 0;
/* 114 */         tgtY++;
/* 115 */         gAirY += 2;
/*     */         break;
/*     */       case 2:
/* 118 */         dir = 5;
/* 119 */         tgtZ--;
/* 120 */         gAirZ -= 2;
/*     */         break;
/*     */       case 3:
/* 123 */         dir = 4;
/* 124 */         tgtZ++;
/* 125 */         gAirZ += 2;
/*     */         break;
/*     */       case 4:
/* 128 */         dir = 3;
/* 129 */         tgtX--;
/* 130 */         gAirX -= 2;
/*     */         break;
/*     */       case 5:
/* 133 */         dir = 2;
/* 134 */         tgtX++;
/* 135 */         gAirX += 2;
/*     */         break;
/*     */       default:
/* 138 */         return false;
/*     */     } 
/* 141 */     if (canEditAndAir(par1ItemStack, par2EntityPlayer, par3World, tgtX, tgtY, tgtZ, side) && canEditAndAir(par1ItemStack, par2EntityPlayer, par3World, gAirX, gAirY, gAirZ, side)) {
/* 143 */       par3World.func_147465_d(tgtX, tgtY, tgtZ, this.theBlock, meta, 3);
/* 144 */       DirectionConst.setDummyAirBlockWithMeta(par3World, gAirX, gAirY, gAirZ, dir + 1, 3);
/* 145 */       par1ItemStack.field_77994_a--;
/* 146 */       return true;
/*     */     } 
/* 148 */     return false;
/*     */   }
/*     */   
/*     */   public static final boolean canEditAndAir(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int side) {
/* 151 */     return (par2EntityPlayer.func_82247_a(x, y, z, side, par1ItemStack) && par3World.func_147437_c(x, y, z));
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v() {
/* 158 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getRenderPasses(int metadata) {
/* 163 */     return 2;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getIcon(ItemStack stack, int pass) {
/* 168 */     if (pass == 1)
/* 170 */       return ((BlockCropsGravitized)SMModContainer.CropGravitizedBlock).starMarkIcon; 
/* 172 */     return super.getIcon(stack, pass);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */