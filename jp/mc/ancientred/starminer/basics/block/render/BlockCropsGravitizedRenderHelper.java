/*     */ package jp.mc.ancientred.starminer.basics.block.render;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import jp.mc.ancientred.starminer.basics.block.DirectionConst;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ public class BlockCropsGravitizedRenderHelper implements ISimpleBlockRenderingHandler {
/*     */   public static final int RENDER_TYPE = 4341800;
/*     */   
/*     */   public int getRenderId() {
/*  18 */     return 4341800;
/*     */   }
/*     */   
/*     */   public void renderInventoryBlock(Block par1Block, int metadata, int modelID, RenderBlocks renderer) {}
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/*  27 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  28 */     tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
/*  29 */     tessellator.func_78386_a(1.0F, 1.0F, 1.0F);
/*  31 */     int dir = DirectionConst.getPlantGravityDirection(world, x, y, z);
/*  33 */     int meta = renderer.field_147845_a.func_72805_g(x, y, z);
/*  34 */     IIcon icon = renderer.func_147787_a(block, 0, meta);
/*  35 */     if (renderer.func_147744_b())
/*  37 */       icon = renderer.field_147840_d; 
/*  39 */     double renX = (double)x;
/*  40 */     double renY = (double)y;
/*  41 */     double renZ = (double)z;
/*  42 */     double verTop = renY + 1.0D - 0.0625D;
/*  43 */     double verBot = renY + 0.0D - 0.0625D;
/*  44 */     int renderType = 0;
/*  45 */     switch (dir) {
/*     */       case 1:
/*  47 */         renderType = 0;
/*  48 */         verTop = renY + 0.0D + 0.0625D;
/*  49 */         verBot = renY + 1.0D + 0.0625D;
/*     */         break;
/*     */       case 0:
/*  52 */         renderType = 0;
/*  53 */         verTop = renY + 1.0D - 0.0625D;
/*  54 */         verBot = renY + 0.0D - 0.0625D;
/*     */         break;
/*     */       case 3:
/*  57 */         renderType = 1;
/*  58 */         verTop = renX + 0.0D + 0.0625D;
/*  59 */         verBot = renX + 1.0D + 0.0625D;
/*     */         break;
/*     */       case 2:
/*  62 */         renderType = 1;
/*  63 */         verTop = renX + 1.0D - 0.0625D;
/*  64 */         verBot = renX + 0.0D - 0.0625D;
/*     */         break;
/*     */       case 5:
/*  67 */         renderType = 2;
/*  68 */         verTop = renZ + 0.0D + 0.0625D;
/*  69 */         verBot = renZ + 1.0D + 0.0625D;
/*     */         break;
/*     */       case 4:
/*  72 */         renderType = 2;
/*  73 */         verTop = renZ + 1.0D - 0.0625D;
/*  74 */         verBot = renZ + 0.0D - 0.0625D;
/*     */         break;
/*     */     } 
/*  78 */     double minU = (double)icon.func_94209_e();
/*  79 */     double minV = (double)icon.func_94206_g();
/*  80 */     double maxU = (double)icon.func_94212_f();
/*  81 */     double maxV = (double)icon.func_94210_h();
/*  84 */     if (renderType == 0) {
/*  85 */       double xMin = renX + 0.5D - 0.25D;
/*  86 */       double xMax = renX + 0.5D + 0.25D;
/*  87 */       double zMin = renZ + 0.5D - 0.5D;
/*  88 */       double zMax = renZ + 0.5D + 0.5D;
/*  90 */       tessellator.func_78374_a(xMin, verTop, zMin, minU, minV);
/*  91 */       tessellator.func_78374_a(xMin, verBot, zMin, minU, maxV);
/*  92 */       tessellator.func_78374_a(xMin, verBot, zMax, maxU, maxV);
/*  93 */       tessellator.func_78374_a(xMin, verTop, zMax, maxU, minV);
/*  95 */       tessellator.func_78374_a(xMin, verTop, zMax, minU, minV);
/*  96 */       tessellator.func_78374_a(xMin, verBot, zMax, minU, maxV);
/*  97 */       tessellator.func_78374_a(xMin, verBot, zMin, maxU, maxV);
/*  98 */       tessellator.func_78374_a(xMin, verTop, zMin, maxU, minV);
/* 100 */       tessellator.func_78374_a(xMax, verTop, zMax, minU, minV);
/* 101 */       tessellator.func_78374_a(xMax, verBot, zMax, minU, maxV);
/* 102 */       tessellator.func_78374_a(xMax, verBot, zMin, maxU, maxV);
/* 103 */       tessellator.func_78374_a(xMax, verTop, zMin, maxU, minV);
/* 105 */       tessellator.func_78374_a(xMax, verTop, zMin, minU, minV);
/* 106 */       tessellator.func_78374_a(xMax, verBot, zMin, minU, maxV);
/* 107 */       tessellator.func_78374_a(xMax, verBot, zMax, maxU, maxV);
/* 108 */       tessellator.func_78374_a(xMax, verTop, zMax, maxU, minV);
/* 109 */       xMin = renX + 0.5D - 0.5D;
/* 110 */       xMax = renX + 0.5D + 0.5D;
/* 111 */       zMin = renZ + 0.5D - 0.25D;
/* 112 */       zMax = renZ + 0.5D + 0.25D;
/* 113 */       tessellator.func_78374_a(xMin, verTop, zMin, minU, minV);
/* 114 */       tessellator.func_78374_a(xMin, verBot, zMin, minU, maxV);
/* 115 */       tessellator.func_78374_a(xMax, verBot, zMin, maxU, maxV);
/* 116 */       tessellator.func_78374_a(xMax, verTop, zMin, maxU, minV);
/* 118 */       tessellator.func_78374_a(xMax, verTop, zMin, minU, minV);
/* 119 */       tessellator.func_78374_a(xMax, verBot, zMin, minU, maxV);
/* 120 */       tessellator.func_78374_a(xMin, verBot, zMin, maxU, maxV);
/* 121 */       tessellator.func_78374_a(xMin, verTop, zMin, maxU, minV);
/* 123 */       tessellator.func_78374_a(xMax, verTop, zMax, minU, minV);
/* 124 */       tessellator.func_78374_a(xMax, verBot, zMax, minU, maxV);
/* 125 */       tessellator.func_78374_a(xMin, verBot, zMax, maxU, maxV);
/* 126 */       tessellator.func_78374_a(xMin, verTop, zMax, maxU, minV);
/* 128 */       tessellator.func_78374_a(xMin, verTop, zMax, minU, minV);
/* 129 */       tessellator.func_78374_a(xMin, verBot, zMax, minU, maxV);
/* 130 */       tessellator.func_78374_a(xMax, verBot, zMax, maxU, maxV);
/* 131 */       tessellator.func_78374_a(xMax, verTop, zMax, maxU, minV);
/* 132 */     } else if (renderType == 1) {
/* 133 */       double zMin = renZ + 0.5D - 0.25D;
/* 134 */       double zMax = renZ + 0.5D + 0.25D;
/* 135 */       double yMin = renY + 0.5D - 0.5D;
/* 136 */       double yMax = renY + 0.5D + 0.5D;
/* 138 */       tessellator.func_78374_a(verTop, yMin, zMin, minU, minV);
/* 139 */       tessellator.func_78374_a(verBot, yMin, zMin, minU, maxV);
/* 140 */       tessellator.func_78374_a(verBot, yMax, zMin, maxU, maxV);
/* 141 */       tessellator.func_78374_a(verTop, yMax, zMin, maxU, minV);
/* 143 */       tessellator.func_78374_a(verTop, yMax, zMin, minU, minV);
/* 144 */       tessellator.func_78374_a(verBot, yMax, zMin, minU, maxV);
/* 145 */       tessellator.func_78374_a(verBot, yMin, zMin, maxU, maxV);
/* 146 */       tessellator.func_78374_a(verTop, yMin, zMin, maxU, minV);
/* 148 */       tessellator.func_78374_a(verTop, yMax, zMax, minU, minV);
/* 149 */       tessellator.func_78374_a(verBot, yMax, zMax, minU, maxV);
/* 150 */       tessellator.func_78374_a(verBot, yMin, zMax, maxU, maxV);
/* 151 */       tessellator.func_78374_a(verTop, yMin, zMax, maxU, minV);
/* 153 */       tessellator.func_78374_a(verTop, yMin, zMax, minU, minV);
/* 154 */       tessellator.func_78374_a(verBot, yMin, zMax, minU, maxV);
/* 155 */       tessellator.func_78374_a(verBot, yMax, zMax, maxU, maxV);
/* 156 */       tessellator.func_78374_a(verTop, yMax, zMax, maxU, minV);
/* 158 */       zMin = renZ + 0.5D - 0.5D;
/* 159 */       zMax = renZ + 0.5D + 0.5D;
/* 160 */       yMin = renY + 0.5D - 0.25D;
/* 161 */       yMax = renY + 0.5D + 0.25D;
/* 162 */       tessellator.func_78374_a(verTop, yMin, zMin, minU, minV);
/* 163 */       tessellator.func_78374_a(verBot, yMin, zMin, minU, maxV);
/* 164 */       tessellator.func_78374_a(verBot, yMin, zMax, maxU, maxV);
/* 165 */       tessellator.func_78374_a(verTop, yMin, zMax, maxU, minV);
/* 167 */       tessellator.func_78374_a(verTop, yMin, zMax, minU, minV);
/* 168 */       tessellator.func_78374_a(verBot, yMin, zMax, minU, maxV);
/* 169 */       tessellator.func_78374_a(verBot, yMin, zMin, maxU, maxV);
/* 170 */       tessellator.func_78374_a(verTop, yMin, zMin, maxU, minV);
/* 172 */       tessellator.func_78374_a(verTop, yMax, zMax, minU, minV);
/* 173 */       tessellator.func_78374_a(verBot, yMax, zMax, minU, maxV);
/* 174 */       tessellator.func_78374_a(verBot, yMax, zMin, maxU, maxV);
/* 175 */       tessellator.func_78374_a(verTop, yMax, zMin, maxU, minV);
/* 177 */       tessellator.func_78374_a(verTop, yMax, zMin, minU, minV);
/* 178 */       tessellator.func_78374_a(verBot, yMax, zMin, minU, maxV);
/* 179 */       tessellator.func_78374_a(verBot, yMax, zMax, maxU, maxV);
/* 180 */       tessellator.func_78374_a(verTop, yMax, zMax, maxU, minV);
/* 181 */     } else if (renderType == 2) {
/* 182 */       double yMin = renY + 0.5D - 0.25D;
/* 183 */       double yMax = renY + 0.5D + 0.25D;
/* 184 */       double xMin = renX + 0.5D - 0.5D;
/* 185 */       double xMax = renX + 0.5D + 0.5D;
/* 187 */       tessellator.func_78374_a(xMin, yMin, verTop, minU, minV);
/* 188 */       tessellator.func_78374_a(xMin, yMin, verBot, minU, maxV);
/* 189 */       tessellator.func_78374_a(xMax, yMin, verBot, maxU, maxV);
/* 190 */       tessellator.func_78374_a(xMax, yMin, verTop, maxU, minV);
/* 192 */       tessellator.func_78374_a(xMax, yMin, verTop, minU, minV);
/* 193 */       tessellator.func_78374_a(xMax, yMin, verBot, minU, maxV);
/* 194 */       tessellator.func_78374_a(xMin, yMin, verBot, maxU, maxV);
/* 195 */       tessellator.func_78374_a(xMin, yMin, verTop, maxU, minV);
/* 197 */       tessellator.func_78374_a(xMax, yMax, verTop, minU, minV);
/* 198 */       tessellator.func_78374_a(xMax, yMax, verBot, minU, maxV);
/* 199 */       tessellator.func_78374_a(xMin, yMax, verBot, maxU, maxV);
/* 200 */       tessellator.func_78374_a(xMin, yMax, verTop, maxU, minV);
/* 202 */       tessellator.func_78374_a(xMin, yMax, verTop, minU, minV);
/* 203 */       tessellator.func_78374_a(xMin, yMax, verBot, minU, maxV);
/* 204 */       tessellator.func_78374_a(xMax, yMax, verBot, maxU, maxV);
/* 205 */       tessellator.func_78374_a(xMax, yMax, verTop, maxU, minV);
/* 207 */       yMin = renY + 0.5D - 0.5D;
/* 208 */       yMax = renY + 0.5D + 0.5D;
/* 209 */       xMin = renX + 0.5D - 0.25D;
/* 210 */       xMax = renX + 0.5D + 0.25D;
/* 211 */       tessellator.func_78374_a(xMin, yMin, verTop, minU, minV);
/* 212 */       tessellator.func_78374_a(xMin, yMin, verBot, minU, maxV);
/* 213 */       tessellator.func_78374_a(xMin, yMax, verBot, maxU, maxV);
/* 214 */       tessellator.func_78374_a(xMin, yMax, verTop, maxU, minV);
/* 216 */       tessellator.func_78374_a(xMin, yMax, verTop, minU, minV);
/* 217 */       tessellator.func_78374_a(xMin, yMax, verBot, minU, maxV);
/* 218 */       tessellator.func_78374_a(xMin, yMin, verBot, maxU, maxV);
/* 219 */       tessellator.func_78374_a(xMin, yMin, verTop, maxU, minV);
/* 221 */       tessellator.func_78374_a(xMax, yMax, verTop, minU, minV);
/* 222 */       tessellator.func_78374_a(xMax, yMax, verBot, minU, maxV);
/* 223 */       tessellator.func_78374_a(xMax, yMin, verBot, maxU, maxV);
/* 224 */       tessellator.func_78374_a(xMax, yMin, verTop, maxU, minV);
/* 226 */       tessellator.func_78374_a(xMax, yMin, verTop, minU, minV);
/* 227 */       tessellator.func_78374_a(xMax, yMin, verBot, minU, maxV);
/* 228 */       tessellator.func_78374_a(xMax, yMax, verBot, maxU, maxV);
/* 229 */       tessellator.func_78374_a(xMax, yMax, verTop, maxU, minV);
/*     */     } 
/* 231 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 236 */     return false;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */