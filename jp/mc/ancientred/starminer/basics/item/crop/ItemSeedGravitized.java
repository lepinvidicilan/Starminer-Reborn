/*     */ package jp.mc.ancientred.starminer.basics.item.crop;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.block.DirectionConst;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.IGravitizedPlants;
/*     */ import jp.mc.ancientred.starminer.basics.item.ItemStarContoler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemSeeds;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemSeedGravitized extends ItemSeeds {
/*     */   private Block blockTypePrivate;
/*     */   
/*     */   public ItemSeedGravitized(Block par1Block, Block par2Block) {
/*  20 */     super(par1Block, par2Block);
/*  21 */     this.blockTypePrivate = par1Block;
/*     */   }
/*     */   
/*     */   public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
/*  28 */     int meta = func_77647_b(par1ItemStack.func_77960_j());
/*  29 */     if (this.blockTypePrivate instanceof IGravitizedPlants && 
/*  30 */       !((IGravitizedPlants)this.blockTypePrivate).allowPlantOn(par3World.func_147439_a(x, y, z), meta))
/*  31 */       return false; 
/*  42 */     int dir = 0;
/*  43 */     int tgtX = x;
/*  44 */     int tgtY = y;
/*  45 */     int tgtZ = z;
/*  46 */     int gAirX = x;
/*  47 */     int gAirY = y;
/*  48 */     int gAirZ = z;
/*  49 */     switch (side) {
/*     */       case 0:
/*  51 */         tgtY--;
/*  52 */         dir = 1;
/*  53 */         gAirY -= 2;
/*     */         break;
/*     */       case 1:
/*  56 */         dir = 0;
/*  57 */         tgtY++;
/*  58 */         gAirY += 2;
/*     */         break;
/*     */       case 2:
/*  61 */         dir = 5;
/*  62 */         tgtZ--;
/*  63 */         gAirZ -= 2;
/*     */         break;
/*     */       case 3:
/*  66 */         dir = 4;
/*  67 */         tgtZ++;
/*  68 */         gAirZ += 2;
/*     */         break;
/*     */       case 4:
/*  71 */         dir = 3;
/*  72 */         tgtX--;
/*  73 */         gAirX -= 2;
/*     */         break;
/*     */       case 5:
/*  76 */         dir = 2;
/*  77 */         tgtX++;
/*  78 */         gAirX += 2;
/*     */         break;
/*     */       default:
/*  81 */         return false;
/*     */     } 
/*  84 */     if (canEditAndAir(par1ItemStack, par2EntityPlayer, par3World, tgtX, tgtY, tgtZ, side) && canEditAndAir(par1ItemStack, par2EntityPlayer, par3World, gAirX, gAirY, gAirZ, side)) {
/*  86 */       par3World.func_147465_d(tgtX, tgtY, tgtZ, this.blockTypePrivate, meta, 3);
/*  87 */       DirectionConst.setDummyAirBlockWithMeta(par3World, gAirX, gAirY, gAirZ, dir + 1, 3);
/*  88 */       par1ItemStack.field_77994_a--;
/*  89 */       return true;
/*     */     } 
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public static final boolean canEditAndAir(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int side) {
/*  94 */     return (par2EntityPlayer.func_82247_a(x, y, z, side, par1ItemStack) && par3World.func_147437_c(x, y, z));
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v() {
/* 102 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getRenderPasses(int metadata) {
/* 107 */     return 2;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getIcon(ItemStack stack, int pass) {
/* 112 */     if (pass == 1)
/* 113 */       return ((ItemStarContoler)SMModContainer.StarControlerItem).starMarkIcon; 
/* 115 */     return super.getIcon(stack, pass);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */