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
/*     */ import net.minecraft.item.ItemFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.EnumPlantType;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ 
/*     */ public class ItemSeedFoodGravitized extends ItemFood implements IPlantable {
/*     */   private Block cropBlock;
/*     */   
/*     */   public ItemSeedFoodGravitized(int par2, float par3, Block cropblock) {
/*  27 */     super(par2, par3, false);
/*  28 */     this.cropBlock = cropblock;
/*     */   }
/*     */   
/*     */   public ItemSeedFoodGravitized(Block cropblock) {
/*  32 */     this(getHealAmountForBlock(cropblock), getSaturationModifierForBlock(cropblock), cropblock);
/*     */   }
/*     */   
/*     */   private static int getHealAmountForBlock(Block block) {
/*  35 */     if (block == SMModContainer.CarrotGravitizedBlock)
/*  36 */       return 4; 
/*  38 */     if (block == SMModContainer.PotatoGravitizedBlock)
/*  39 */       return 1; 
/*  41 */     return 1;
/*     */   }
/*     */   
/*     */   private static float getSaturationModifierForBlock(Block block) {
/*  44 */     if (block == SMModContainer.CarrotGravitizedBlock)
/*  45 */       return 0.6F; 
/*  47 */     if (block == SMModContainer.PotatoGravitizedBlock)
/*  48 */       return 0.3F; 
/*  50 */     return 1.0F;
/*     */   }
/*     */   
/*     */   public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
/*  55 */     return EnumPlantType.Crop;
/*     */   }
/*     */   
/*     */   public Block getPlant(IBlockAccess world, int x, int y, int z) {
/*  61 */     return this.cropBlock;
/*     */   }
/*     */   
/*     */   public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
/*  67 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
/*  73 */     int meta = func_77647_b(par1ItemStack.func_77960_j());
/*  74 */     if (this.cropBlock instanceof IGravitizedPlants && 
/*  75 */       !((IGravitizedPlants)this.cropBlock).allowPlantOn(par3World.func_147439_a(x, y, z), meta))
/*  76 */       return false; 
/*  87 */     int dir = 0;
/*  88 */     int tgtX = x;
/*  89 */     int tgtY = y;
/*  90 */     int tgtZ = z;
/*  91 */     int gAirX = x;
/*  92 */     int gAirY = y;
/*  93 */     int gAirZ = z;
/*  94 */     switch (side) {
/*     */       case 0:
/*  96 */         tgtY--;
/*  97 */         dir = 1;
/*  98 */         gAirY -= 2;
/*     */         break;
/*     */       case 1:
/* 101 */         dir = 0;
/* 102 */         tgtY++;
/* 103 */         gAirY += 2;
/*     */         break;
/*     */       case 2:
/* 106 */         dir = 5;
/* 107 */         tgtZ--;
/* 108 */         gAirZ -= 2;
/*     */         break;
/*     */       case 3:
/* 111 */         dir = 4;
/* 112 */         tgtZ++;
/* 113 */         gAirZ += 2;
/*     */         break;
/*     */       case 4:
/* 116 */         dir = 3;
/* 117 */         tgtX--;
/* 118 */         gAirX -= 2;
/*     */         break;
/*     */       case 5:
/* 121 */         dir = 2;
/* 122 */         tgtX++;
/* 123 */         gAirX += 2;
/*     */         break;
/*     */       default:
/* 126 */         return false;
/*     */     } 
/* 129 */     if (canEditAndAir(par1ItemStack, par2EntityPlayer, par3World, tgtX, tgtY, tgtZ, side) && canEditAndAir(par1ItemStack, par2EntityPlayer, par3World, gAirX, gAirY, gAirZ, side)) {
/* 131 */       par3World.func_147465_d(tgtX, tgtY, tgtZ, this.cropBlock, meta, 3);
/* 132 */       DirectionConst.setDummyAirBlockWithMeta(par3World, gAirX, gAirY, gAirZ, dir + 1, 3);
/* 133 */       par1ItemStack.field_77994_a--;
/* 134 */       return true;
/*     */     } 
/* 136 */     return false;
/*     */   }
/*     */   
/*     */   public static final boolean canEditAndAir(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int side) {
/* 140 */     return (par2EntityPlayer.func_82247_a(x, y, z, side, par1ItemStack) && par3World.func_147437_c(x, y, z));
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v() {
/* 148 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getRenderPasses(int metadata) {
/* 153 */     return 2;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getIcon(ItemStack stack, int pass) {
/* 158 */     if (pass == 1)
/* 159 */       return ((ItemStarContoler)SMModContainer.StarControlerItem).starMarkIcon; 
/* 161 */     return super.getIcon(stack, pass);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */