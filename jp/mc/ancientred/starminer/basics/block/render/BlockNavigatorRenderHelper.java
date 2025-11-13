/*     */ package jp.mc.ancientred.starminer.basics.block.render;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import jp.mc.ancientred.starminer.basics.Config;
/*     */ import jp.mc.ancientred.starminer.basics.common.VecUtils;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityNavigator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class BlockNavigatorRenderHelper implements ISimpleBlockRenderingHandler {
/*     */   public static final int RENDER_TYPE = 4341807;
/*     */   
/*     */   public int getRenderId() {
/*  26 */     return 4341807;
/*     */   }
/*     */   
/*     */   public void renderInventoryBlock(Block par1Block, int metadata, int modelID, RenderBlocks renderer) {
/*  30 */     IIcon beaconIcon = Blocks.field_150461_bJ.func_149691_a(0, 0);
/*  31 */     GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  32 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  33 */     renderer.func_147782_a(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
/*  34 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  35 */     tessellator.func_78382_b();
/*  36 */     tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
/*  37 */     renderer.func_147768_a(par1Block, 0.0D, 0.0D, 0.0D, beaconIcon);
/*  38 */     tessellator.func_78381_a();
/*  39 */     tessellator.func_78382_b();
/*  40 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/*  41 */     renderer.func_147806_b(par1Block, 0.0D, 0.0D, 0.0D, beaconIcon);
/*  42 */     tessellator.func_78381_a();
/*  43 */     tessellator.func_78382_b();
/*  44 */     tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*  45 */     renderer.func_147761_c(par1Block, 0.0D, 0.0D, 0.0D, beaconIcon);
/*  46 */     tessellator.func_78381_a();
/*  47 */     tessellator.func_78382_b();
/*  48 */     tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/*  49 */     renderer.func_147734_d(par1Block, 0.0D, 0.0D, 0.0D, beaconIcon);
/*  50 */     tessellator.func_78381_a();
/*  51 */     tessellator.func_78382_b();
/*  52 */     tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
/*  53 */     renderer.func_147798_e(par1Block, 0.0D, 0.0D, 0.0D, beaconIcon);
/*  54 */     tessellator.func_78381_a();
/*  55 */     tessellator.func_78382_b();
/*  56 */     tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
/*  57 */     renderer.func_147764_f(par1Block, 0.0D, 0.0D, 0.0D, beaconIcon);
/*  58 */     tessellator.func_78381_a();
/*  59 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */   
/*  61 */   private static Vec3[] vec = new Vec3[60];
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/*  64 */     Tessellator tes = Tessellator.field_78398_a;
/*  65 */     boolean isOn = ((world.func_72805_g(x, y, z) & 0x1) != 0);
/*  67 */     renderer.field_147837_f = true;
/*  68 */     renderer.func_147757_a(renderer.func_147745_b((Block)Blocks.field_150461_bJ));
/*  69 */     renderer.func_147782_a(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
/*  70 */     if (isOn) {
/*  71 */       renderer.func_147784_q(block, x, y, z);
/*     */     } else {
/*  73 */       renderer.func_147736_d(block, x, y, z, 0.6F, 0.9F, 0.6F);
/*     */     } 
/*  75 */     renderer.field_147837_f = false;
/*  76 */     renderer.func_147771_a();
/*  78 */     if (!isOn)
/*  78 */       return true; 
/*  80 */     TileEntity te = world.func_147438_o(x, y, z);
/*  81 */     if (!(te instanceof TileEntityNavigator))
/*  81 */       return true; 
/*  83 */     TileEntityNavigator teNavi = (TileEntityNavigator)te;
/*  85 */     Vec3 directionVec = VecUtils.createVec3((double)teNavi.lookX, (double)teNavi.lookY, (double)teNavi.lookZ);
/*  86 */     Vec3 centerVec = VecUtils.createVec3((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D);
/*  90 */     if (teNavi.isActive()) {
/*  91 */       tes.func_78386_a(1.0F, 1.0F, 0.0F);
/*  92 */       directionVec.field_72450_a *= 100.0D;
/*  93 */       directionVec.field_72448_b *= 100.0D;
/*  94 */       directionVec.field_72449_c *= 100.0D;
/*     */     } else {
/*  96 */       directionVec.field_72450_a *= (double)Config.naviLaserLength;
/*  97 */       directionVec.field_72448_b *= (double)Config.naviLaserLength;
/*  98 */       directionVec.field_72449_c *= (double)Config.naviLaserLength;
/*     */     } 
/* 101 */     directionVec.field_72450_a += centerVec.field_72450_a;
/* 102 */     directionVec.field_72448_b += centerVec.field_72448_b;
/* 103 */     directionVec.field_72449_c += centerVec.field_72449_c;
/* 105 */     double w = 0.05D;
/* 106 */     tes.func_78377_a(centerVec.field_72450_a + w, centerVec.field_72448_b, centerVec.field_72449_c);
/* 107 */     tes.func_78377_a(directionVec.field_72450_a + w, directionVec.field_72448_b, directionVec.field_72449_c);
/* 108 */     tes.func_78377_a(directionVec.field_72450_a - w, directionVec.field_72448_b, directionVec.field_72449_c);
/* 109 */     tes.func_78377_a(centerVec.field_72450_a - w, centerVec.field_72448_b, centerVec.field_72449_c);
/* 111 */     tes.func_78377_a(centerVec.field_72450_a - w, centerVec.field_72448_b, centerVec.field_72449_c);
/* 112 */     tes.func_78377_a(directionVec.field_72450_a - w, directionVec.field_72448_b, directionVec.field_72449_c);
/* 113 */     tes.func_78377_a(directionVec.field_72450_a + w, directionVec.field_72448_b, directionVec.field_72449_c);
/* 114 */     tes.func_78377_a(centerVec.field_72450_a + w, centerVec.field_72448_b, centerVec.field_72449_c);
/* 116 */     tes.func_78377_a(centerVec.field_72450_a, centerVec.field_72448_b, centerVec.field_72449_c + w);
/* 117 */     tes.func_78377_a(directionVec.field_72450_a, directionVec.field_72448_b, directionVec.field_72449_c + w);
/* 118 */     tes.func_78377_a(directionVec.field_72450_a, directionVec.field_72448_b, directionVec.field_72449_c - w);
/* 119 */     tes.func_78377_a(centerVec.field_72450_a, centerVec.field_72448_b, centerVec.field_72449_c - w);
/* 121 */     tes.func_78377_a(centerVec.field_72450_a, centerVec.field_72448_b, centerVec.field_72449_c - w);
/* 122 */     tes.func_78377_a(directionVec.field_72450_a, directionVec.field_72448_b, directionVec.field_72449_c - w);
/* 123 */     tes.func_78377_a(directionVec.field_72450_a, directionVec.field_72448_b, directionVec.field_72449_c + w);
/* 124 */     tes.func_78377_a(centerVec.field_72450_a, centerVec.field_72448_b, centerVec.field_72449_c + w);
/* 126 */     tes.func_78377_a(centerVec.field_72450_a, centerVec.field_72448_b + w, centerVec.field_72449_c);
/* 127 */     tes.func_78377_a(directionVec.field_72450_a, directionVec.field_72448_b + w, directionVec.field_72449_c + w);
/* 128 */     tes.func_78377_a(directionVec.field_72450_a, directionVec.field_72448_b - w, directionVec.field_72449_c - w);
/* 129 */     tes.func_78377_a(centerVec.field_72450_a, centerVec.field_72448_b - w, centerVec.field_72449_c - w);
/* 131 */     tes.func_78377_a(centerVec.field_72450_a, centerVec.field_72448_b - w, centerVec.field_72449_c);
/* 132 */     tes.func_78377_a(directionVec.field_72450_a, directionVec.field_72448_b - w, directionVec.field_72449_c);
/* 133 */     tes.func_78377_a(directionVec.field_72450_a, directionVec.field_72448_b + w, directionVec.field_72449_c);
/* 134 */     tes.func_78377_a(centerVec.field_72450_a, centerVec.field_72448_b + w, centerVec.field_72449_c);
/* 136 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 141 */     return true;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */