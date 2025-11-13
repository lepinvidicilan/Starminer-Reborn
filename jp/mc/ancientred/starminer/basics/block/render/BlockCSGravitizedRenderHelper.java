/*     */ package jp.mc.ancientred.starminer.basics.block.render;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import jp.mc.ancientred.starminer.basics.block.DirectionConst;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.EntityRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ public class BlockCSGravitizedRenderHelper implements ISimpleBlockRenderingHandler {
/*     */   public static final int RENDER_TYPE = 4341801;
/*     */   
/*     */   public int getRenderId() {
/*  20 */     return 4341801;
/*     */   }
/*     */   
/*     */   public void renderInventoryBlock(Block par1Block, int metadata, int modelID, RenderBlocks renderer) {}
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/*  38 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  39 */     tessellator.func_78380_c(block.func_149677_c(world, x, y, z));
/*  40 */     float f = 1.0F;
/*  41 */     int l = block.func_149720_d(world, x, y, z);
/*  42 */     float f1 = (float)(l >> 16 & 0xFF) / 255.0F;
/*  43 */     float f2 = (float)(l >> 8 & 0xFF) / 255.0F;
/*  44 */     float f3 = (float)(l & 0xFF) / 255.0F;
/*  46 */     if (EntityRenderer.field_78517_a) {
/*  48 */       float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
/*  49 */       float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
/*  50 */       float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
/*  51 */       f1 = f4;
/*  52 */       f2 = f5;
/*  53 */       f3 = f6;
/*     */     } 
/*  56 */     tessellator.func_78386_a(f * f1, f * f2, f * f3);
/*  57 */     double renX = (double)x;
/*  58 */     double renY = (double)y;
/*  59 */     double renZ = (double)z;
/*  61 */     if (block == Blocks.field_150329_H) {
/*  63 */       long i1 = (long)(x * 3129871) ^ (long)x * 116129781L ^ (long)y;
/*  64 */       i1 = i1 * i1 * 42317861L + i1 * 11L;
/*  65 */       renX += ((double)((float)(i1 >> 16L & 0xFL) / 15.0F) - 0.5D) * 0.5D;
/*  66 */       renY += ((double)((float)(i1 >> 20L & 0xFL) / 15.0F) - 1.0D) * 0.2D;
/*  67 */       renZ += ((double)((float)(i1 >> 24L & 0xFL) / 15.0F) - 0.5D) * 0.5D;
/*     */     } 
/*  69 */     int meta = world.func_72805_g(x, y, z);
/*  71 */     IIcon icon = renderer.func_147787_a(block, 0, meta);
/*  73 */     if (renderer.func_147744_b())
/*  75 */       icon = renderer.field_147840_d; 
/*  77 */     int renderType = 0;
/*  78 */     double verTop = renY + 1.0D;
/*  79 */     double verBot = renY + 0.0D;
/*  80 */     int dir = DirectionConst.getPlantGravityDirection(world, x, y, z);
/*  81 */     switch (dir) {
/*     */       case 1:
/*  83 */         renderType = 0;
/*  84 */         verTop = renY + 0.0D;
/*  85 */         verBot = renY + 1.0D;
/*     */         break;
/*     */       case 0:
/*  88 */         renderType = 0;
/*  89 */         verTop = renY + 1.0D;
/*  90 */         verBot = renY + 0.0D;
/*     */         break;
/*     */       case 3:
/*  93 */         renderType = 1;
/*  94 */         verTop = renX + 0.0D;
/*  95 */         verBot = renX + 1.0D;
/*     */         break;
/*     */       case 2:
/*  98 */         renderType = 1;
/*  99 */         verTop = renX + 1.0D;
/* 100 */         verBot = renX + 0.0D;
/*     */         break;
/*     */       case 5:
/* 103 */         renderType = 2;
/* 104 */         verTop = renZ + 0.0D;
/* 105 */         verBot = renZ + 1.0D;
/*     */         break;
/*     */       case 4:
/* 108 */         renderType = 2;
/* 109 */         verTop = renZ + 1.0D;
/* 110 */         verBot = renZ + 0.0D;
/*     */         break;
/*     */     } 
/* 113 */     double fix = 0.45D;
/* 114 */     double minU = (double)icon.func_94209_e();
/* 115 */     double minV = (double)icon.func_94206_g();
/* 116 */     double maxU = (double)icon.func_94212_f();
/* 117 */     double maxV = (double)icon.func_94210_h();
/* 119 */     if (renderType == 0) {
/* 120 */       double xMin = renX + 0.5D - fix;
/* 121 */       double xMax = renX + 0.5D + fix;
/* 122 */       double zMin = renZ + 0.5D - fix;
/* 123 */       double zMax = renZ + 0.5D + fix;
/* 124 */       tessellator.func_78374_a(xMin, verTop, zMin, minU, minV);
/* 125 */       tessellator.func_78374_a(xMin, verBot, zMin, minU, maxV);
/* 126 */       tessellator.func_78374_a(xMax, verBot, zMax, maxU, maxV);
/* 127 */       tessellator.func_78374_a(xMax, verTop, zMax, maxU, minV);
/* 128 */       tessellator.func_78374_a(xMax, verTop, zMax, minU, minV);
/* 129 */       tessellator.func_78374_a(xMax, verBot, zMax, minU, maxV);
/* 130 */       tessellator.func_78374_a(xMin, verBot, zMin, maxU, maxV);
/* 131 */       tessellator.func_78374_a(xMin, verTop, zMin, maxU, minV);
/* 132 */       tessellator.func_78374_a(xMin, verTop, zMax, minU, minV);
/* 133 */       tessellator.func_78374_a(xMin, verBot, zMax, minU, maxV);
/* 134 */       tessellator.func_78374_a(xMax, verBot, zMin, maxU, maxV);
/* 135 */       tessellator.func_78374_a(xMax, verTop, zMin, maxU, minV);
/* 136 */       tessellator.func_78374_a(xMax, verTop, zMin, minU, minV);
/* 137 */       tessellator.func_78374_a(xMax, verBot, zMin, minU, maxV);
/* 138 */       tessellator.func_78374_a(xMin, verBot, zMax, maxU, maxV);
/* 139 */       tessellator.func_78374_a(xMin, verTop, zMax, maxU, minV);
/*     */     } 
/* 141 */     if (renderType == 1) {
/* 142 */       double zMin = renZ + 0.5D - fix;
/* 143 */       double zMax = renZ + 0.5D + fix;
/* 144 */       double yMin = renY + 0.5D - fix;
/* 145 */       double yMax = renY + 0.5D + fix;
/* 146 */       tessellator.func_78374_a(verTop, yMin, zMin, minU, minV);
/* 147 */       tessellator.func_78374_a(verBot, yMin, zMin, minU, maxV);
/* 148 */       tessellator.func_78374_a(verBot, yMax, zMax, maxU, maxV);
/* 149 */       tessellator.func_78374_a(verTop, yMax, zMax, maxU, minV);
/* 150 */       tessellator.func_78374_a(verTop, yMax, zMax, minU, minV);
/* 151 */       tessellator.func_78374_a(verBot, yMax, zMax, minU, maxV);
/* 152 */       tessellator.func_78374_a(verBot, yMin, zMin, maxU, maxV);
/* 153 */       tessellator.func_78374_a(verTop, yMin, zMin, maxU, minV);
/* 154 */       tessellator.func_78374_a(verTop, yMax, zMin, minU, minV);
/* 155 */       tessellator.func_78374_a(verBot, yMax, zMin, minU, maxV);
/* 156 */       tessellator.func_78374_a(verBot, yMin, zMax, maxU, maxV);
/* 157 */       tessellator.func_78374_a(verTop, yMin, zMax, maxU, minV);
/* 158 */       tessellator.func_78374_a(verTop, yMin, zMax, minU, minV);
/* 159 */       tessellator.func_78374_a(verBot, yMin, zMax, minU, maxV);
/* 160 */       tessellator.func_78374_a(verBot, yMax, zMin, maxU, maxV);
/* 161 */       tessellator.func_78374_a(verTop, yMax, zMin, maxU, minV);
/*     */     } 
/* 163 */     if (renderType == 2) {
/* 164 */       double yMin = renY + 0.5D - fix;
/* 165 */       double yMax = renY + 0.5D + fix;
/* 166 */       double xMin = renX + 0.5D - fix;
/* 167 */       double xMax = renX + 0.5D + fix;
/* 168 */       tessellator.func_78374_a(xMin, yMin, verTop, minU, minV);
/* 169 */       tessellator.func_78374_a(xMin, yMin, verBot, minU, maxV);
/* 170 */       tessellator.func_78374_a(xMax, yMax, verBot, maxU, maxV);
/* 171 */       tessellator.func_78374_a(xMax, yMax, verTop, maxU, minV);
/* 172 */       tessellator.func_78374_a(xMax, yMax, verTop, minU, minV);
/* 173 */       tessellator.func_78374_a(xMax, yMax, verBot, minU, maxV);
/* 174 */       tessellator.func_78374_a(xMin, yMin, verBot, maxU, maxV);
/* 175 */       tessellator.func_78374_a(xMin, yMin, verTop, maxU, minV);
/* 176 */       tessellator.func_78374_a(xMax, yMin, verTop, minU, minV);
/* 177 */       tessellator.func_78374_a(xMax, yMin, verBot, minU, maxV);
/* 178 */       tessellator.func_78374_a(xMin, yMax, verBot, maxU, maxV);
/* 179 */       tessellator.func_78374_a(xMin, yMax, verTop, maxU, minV);
/* 180 */       tessellator.func_78374_a(xMin, yMax, verTop, minU, minV);
/* 181 */       tessellator.func_78374_a(xMin, yMax, verBot, minU, maxV);
/* 182 */       tessellator.func_78374_a(xMax, yMin, verBot, maxU, maxV);
/* 183 */       tessellator.func_78374_a(xMax, yMin, verTop, maxU, minV);
/*     */     } 
/* 186 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 190 */     return false;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */