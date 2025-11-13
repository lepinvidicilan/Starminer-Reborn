/*     */ package jp.mc.ancientred.starminer.basics.item.block;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.block.DirectionConst;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.BlockCropsGravitized;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.BlockFlowerGravitized;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.BlockSaplingGravitized;
/*     */ import jp.mc.ancientred.starminer.basics.block.gravitized.IGravitizedPlants;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemMultiTexture;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemMultiTextureTileForG extends ItemMultiTexture {
/*     */   public ItemMultiTextureTileForG(Block par1Block, BlockSaplingGravitized par2Block, String[] namelist) {
/*  25 */     super(par1Block, (Block)par2Block, namelist);
/*     */   }
/*     */   
/*     */   public ItemMultiTextureTileForG(Block par1Block, BlockFlowerGravitized par2Block, String[] namelist) {
/*  28 */     super(par1Block, (Block)par2Block, namelist);
/*     */   }
/*     */   
/*     */   public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
/*  34 */     Block thisBlockId = this.field_150941_b;
/*  35 */     int meta = func_77647_b(par1ItemStack.func_77960_j());
/*  36 */     if (thisBlockId instanceof IGravitizedPlants && 
/*  37 */       !((IGravitizedPlants)thisBlockId).allowPlantOn(par3World.func_147439_a(x, y, z), meta))
/*  38 */       return false; 
/*  49 */     int dir = 0;
/*  50 */     int tgtX = x;
/*  51 */     int tgtY = y;
/*  52 */     int tgtZ = z;
/*  53 */     int gAirX = x;
/*  54 */     int gAirY = y;
/*  55 */     int gAirZ = z;
/*  56 */     switch (side) {
/*     */       case 0:
/*  58 */         tgtY--;
/*  59 */         dir = 1;
/*  60 */         gAirY -= 2;
/*     */         break;
/*     */       case 1:
/*  63 */         dir = 0;
/*  64 */         tgtY++;
/*  65 */         gAirY += 2;
/*     */         break;
/*     */       case 2:
/*  68 */         dir = 5;
/*  69 */         tgtZ--;
/*  70 */         gAirZ -= 2;
/*     */         break;
/*     */       case 3:
/*  73 */         dir = 4;
/*  74 */         tgtZ++;
/*  75 */         gAirZ += 2;
/*     */         break;
/*     */       case 4:
/*  78 */         dir = 3;
/*  79 */         tgtX--;
/*  80 */         gAirX -= 2;
/*     */         break;
/*     */       case 5:
/*  83 */         dir = 2;
/*  84 */         tgtX++;
/*  85 */         gAirX += 2;
/*     */         break;
/*     */       default:
/*  88 */         return false;
/*     */     } 
/*  91 */     if (canEditAndAir(par1ItemStack, par2EntityPlayer, par3World, tgtX, tgtY, tgtZ, side) && canEditAndAir(par1ItemStack, par2EntityPlayer, par3World, gAirX, gAirY, gAirZ, side)) {
/*  93 */       par3World.func_147465_d(tgtX, tgtY, tgtZ, thisBlockId, meta, 3);
/*  94 */       DirectionConst.setDummyAirBlockWithMeta(par3World, gAirX, gAirY, gAirZ, dir + 1, 3);
/*  95 */       par1ItemStack.field_77994_a--;
/*  96 */       return true;
/*     */     } 
/*  98 */     return false;
/*     */   }
/*     */   
/*     */   public static final boolean canEditAndAir(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int side) {
/* 101 */     return (par2EntityPlayer.func_82247_a(x, y, z, side, par1ItemStack) && par3World.func_147437_c(x, y, z));
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v() {
/* 108 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getRenderPasses(int metadata) {
/* 113 */     return 2;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getIcon(ItemStack stack, int pass) {
/* 118 */     if (pass == 1)
/* 120 */       return ((BlockCropsGravitized)SMModContainer.CropGravitizedBlock).starMarkIcon; 
/* 122 */     return super.getIcon(stack, pass);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */