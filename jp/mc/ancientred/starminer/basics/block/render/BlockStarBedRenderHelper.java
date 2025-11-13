/*      */ package jp.mc.ancientred.starminer.basics.block.render;
/*      */ 
/*      */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*      */ import jp.mc.ancientred.starminer.basics.block.bed.BlockStarBedBody;
/*      */ import jp.mc.ancientred.starminer.basics.block.bed.BlockStarBedHead;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.client.renderer.RenderBlocks;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.util.IIcon;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ 
/*      */ public class BlockStarBedRenderHelper implements ISimpleBlockRenderingHandler {
/*      */   public static final int RENDER_TYPE = 4341808;
/*      */   
/*      */   public int getRenderId() {
/*   21 */     return 4341808;
/*      */   }
/*      */   
/*      */   public void renderInventoryBlock(Block par1Block, int metadata, int modelID, RenderBlocks renderer) {}
/*      */   
/*      */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/*      */     boolean isBedHead;
/*      */     int gravDir, connDir;
/*      */     IIcon topIcon, endIcon, sideIcon;
/*      */     double end;
/*   36 */     IIcon starIcon = null;
/*   37 */     int sideTopCnt = 1;
/*   38 */     if (block instanceof BlockStarBedBody) {
/*   39 */       isBedHead = false;
/*   40 */       gravDir = ((BlockStarBedBody)block).getGravityDirection(world, x, y, z);
/*   41 */       connDir = ((BlockStarBedBody)block).getConnectionDirection(world, x, y, z);
/*   42 */       if (gravDir == -1)
/*   42 */         return true; 
/*   43 */       topIcon = ((BlockStarBedBody)block).getBedTopIcon();
/*   44 */       endIcon = ((BlockStarBedBody)block).getBedEndIcon();
/*   45 */       sideIcon = ((BlockStarBedBody)block).getBedSideIcon();
/*   46 */       starIcon = ((BlockStarBedBody)block).getBedStarIcon();
/*   47 */       sideTopCnt = 2;
/*   48 */     } else if (block instanceof BlockStarBedHead) {
/*   49 */       isBedHead = true;
/*   50 */       gravDir = ((BlockStarBedHead)block).getGravityDirection(world, x, y, z);
/*   51 */       connDir = ((BlockStarBedHead)block).getConnectionDirection(world, x, y, z);
/*   52 */       if (connDir == -1)
/*   52 */         return true; 
/*   53 */       topIcon = ((BlockStarBedHead)block).getBedTopIcon();
/*   54 */       endIcon = ((BlockStarBedHead)block).getBedEndIcon();
/*   55 */       sideIcon = ((BlockStarBedHead)block).getBedSideIcon();
/*      */     } else {
/*   57 */       return true;
/*      */     } 
/*   59 */     Tessellator tessellator = Tessellator.field_78398_a;
/*   60 */     double bedHeight = 0.5625D;
/*   61 */     double bedHeightRev = 1.0D - bedHeight;
/*   63 */     float color = 0.5F;
/*   64 */     float colorTopR = 1.0F;
/*   65 */     float colorTopG = 0.8F;
/*   66 */     float colorTopB = 0.6F;
/*   78 */     int brightness = block.func_149677_c(world, x, y, z);
/*   79 */     tessellator.func_78380_c(brightness);
/*   80 */     tessellator.func_78386_a(color, color, color);
/*   81 */     IIcon icon = Blocks.field_150344_f.func_149733_h(0);
/*   82 */     if (renderer.func_147744_b())
/*   82 */       icon = renderer.field_147840_d; 
/*   83 */     double minU = (double)icon.func_94209_e();
/*   84 */     double maxU = (double)icon.func_94212_f();
/*   85 */     double minV = (double)icon.func_94206_g();
/*   86 */     double maxV = (double)icon.func_94210_h();
/*   88 */     switch (gravDir) {
/*      */       case 3:
/*   90 */         x_2 = (double)x + 1.0D - 0.1875D;
/*   91 */         y_1 = (double)y + 0.0D;
/*   92 */         y_2 = (double)y + 1.0D;
/*   93 */         z_1 = (double)z + 0.0D;
/*   94 */         z_2 = (double)z + 1.0D;
/*   95 */         tessellator.func_78374_a(x_2, y_2, z_2, minU, maxV);
/*   96 */         tessellator.func_78374_a(x_2, y_1, z_2, minU, minV);
/*   97 */         tessellator.func_78374_a(x_2, y_1, z_1, maxU, minV);
/*   98 */         tessellator.func_78374_a(x_2, y_2, z_1, maxU, maxV);
/*      */         break;
/*      */       case 2:
/*  101 */         x_1 = (double)x + 0.0D + 0.1875D;
/*  102 */         y_1 = (double)y + 0.0D;
/*  103 */         y_2 = (double)y + 1.0D;
/*  104 */         z_1 = (double)z + 0.0D;
/*  105 */         z_2 = (double)z + 1.0D;
/*  106 */         tessellator.func_78374_a(x_1, y_2, z_1, minU, maxV);
/*  107 */         tessellator.func_78374_a(x_1, y_1, z_1, minU, minV);
/*  108 */         tessellator.func_78374_a(x_1, y_1, z_2, maxU, minV);
/*  109 */         tessellator.func_78374_a(x_1, y_2, z_2, maxU, maxV);
/*      */         break;
/*      */       case 5:
/*  112 */         x_1 = (double)x + 0.0D;
/*  113 */         x_2 = (double)x + 1.0D;
/*  114 */         y_1 = (double)y + 0.0D;
/*  115 */         y_2 = (double)y + 1.0D;
/*  116 */         z_2 = (double)z + 1.0D - 0.1875D;
/*  117 */         tessellator.func_78374_a(x_2, y_1, z_2, minU, maxV);
/*  118 */         tessellator.func_78374_a(x_2, y_2, z_2, minU, minV);
/*  119 */         tessellator.func_78374_a(x_1, y_2, z_2, maxU, minV);
/*  120 */         tessellator.func_78374_a(x_1, y_1, z_2, maxU, maxV);
/*      */         break;
/*      */       case 4:
/*  123 */         x_1 = (double)x + 0.0D;
/*  124 */         x_2 = (double)x + 1.0D;
/*  125 */         y_1 = (double)y + 0.0D;
/*  126 */         y_2 = (double)y + 1.0D;
/*  127 */         z_1 = (double)z + 0.0D + 0.1875D;
/*  128 */         tessellator.func_78374_a(x_1, y_1, z_1, minU, maxV);
/*  129 */         tessellator.func_78374_a(x_1, y_2, z_1, minU, minV);
/*  130 */         tessellator.func_78374_a(x_2, y_2, z_1, maxU, minV);
/*  131 */         tessellator.func_78374_a(x_2, y_1, z_1, maxU, maxV);
/*      */         break;
/*      */       case 1:
/*  134 */         x_1 = (double)x + 0.0D;
/*  135 */         x_2 = (double)x + 1.0D;
/*  136 */         y_2 = (double)y + 1.0D - 0.1875D;
/*  137 */         z_1 = (double)z + 0.0D;
/*  138 */         z_2 = (double)z + 1.0D;
/*  139 */         tessellator.func_78374_a(x_2, y_2, z_2, minU, maxV);
/*  140 */         tessellator.func_78374_a(x_2, y_2, z_1, minU, minV);
/*  141 */         tessellator.func_78374_a(x_1, y_2, z_1, maxU, minV);
/*  142 */         tessellator.func_78374_a(x_1, y_2, z_2, maxU, maxV);
/*      */         break;
/*      */       case 0:
/*  145 */         x_1 = (double)x + 0.0D;
/*  146 */         x_2 = (double)x + 1.0D;
/*  147 */         y_1 = (double)y + 0.0D + 0.1875D;
/*  148 */         z_1 = (double)z + 0.0D;
/*  149 */         z_2 = (double)z + 1.0D;
/*  150 */         tessellator.func_78374_a(x_1, y_1, z_2, minU, maxV);
/*  151 */         tessellator.func_78374_a(x_1, y_1, z_1, minU, minV);
/*  152 */         tessellator.func_78374_a(x_2, y_1, z_1, maxU, minV);
/*  153 */         tessellator.func_78374_a(x_2, y_1, z_2, maxU, maxV);
/*      */         break;
/*      */     } 
/*  159 */     tessellator.func_78386_a(colorTopR, colorTopR, colorTopR);
/*  160 */     icon = topIcon;
/*  161 */     if (renderer.func_147744_b())
/*  161 */       icon = renderer.field_147840_d; 
/*  162 */     for (int i = 0; i < sideTopCnt; i++) {
/*  163 */       if (i == 1 && starIcon != null) {
/*  164 */         icon = starIcon;
/*  165 */         bedHeight += 0.02D;
/*  166 */         bedHeightRev = 1.0D - bedHeight;
/*      */       } 
/*  169 */       minU = (double)icon.func_94209_e();
/*  170 */       maxU = (double)icon.func_94212_f();
/*  171 */       minV = (double)icon.func_94206_g();
/*  172 */       maxV = (double)icon.func_94210_h();
/*  174 */       double d1 = -1.0D;
/*  175 */       double d2 = minU;
/*  176 */       double d3 = maxU;
/*  177 */       double d4 = minV;
/*  178 */       double d5 = minV;
/*  179 */       double d6 = minU;
/*  180 */       double d7 = maxU;
/*  181 */       double d8 = maxV;
/*  182 */       double d9 = maxV;
/*  186 */       switch (gravDir) {
/*      */         case 3:
/*  188 */           switch (connDir) {
/*      */             case 1:
/*  191 */               d1 = 3.0D;
/*      */               break;
/*      */             case 5:
/*  198 */               d1 = 0.0D;
/*      */               break;
/*      */             case 4:
/*  202 */               d1 = 2.0D;
/*      */               break;
/*      */           } 
/*  206 */           if (d1 == 0.0D) {
/*  206 */             d3 = minU;
/*  206 */             d4 = maxV;
/*  206 */             d6 = maxU;
/*  206 */             d9 = minV;
/*  207 */           } else if (d1 == 2.0D) {
/*  207 */             d2 = maxU;
/*  207 */             d5 = maxV;
/*  207 */             d7 = minU;
/*  207 */             d8 = minV;
/*  208 */           } else if (d1 == 3.0D) {
/*  208 */             d2 = maxU;
/*  208 */             d5 = maxV;
/*  208 */             d7 = minU;
/*  208 */             d8 = minV;
/*  208 */             d3 = minU;
/*  208 */             d4 = maxV;
/*  208 */             d6 = maxU;
/*  208 */             d9 = minV;
/*      */           } 
/*  210 */           x_2 = (double)x + bedHeightRev;
/*  211 */           y_1 = (double)y + 0.0D;
/*  212 */           y_2 = (double)y + 1.0D;
/*  213 */           z_1 = (double)z + 0.0D;
/*  214 */           z_2 = (double)z + 1.0D;
/*  215 */           tessellator.func_78374_a(x_2, y_1, z_1, d6, d8);
/*  216 */           tessellator.func_78374_a(x_2, y_1, z_2, d2, d4);
/*  217 */           tessellator.func_78374_a(x_2, y_2, z_2, d3, d5);
/*  218 */           tessellator.func_78374_a(x_2, y_2, z_1, d7, d9);
/*      */           break;
/*      */         case 2:
/*  221 */           switch (connDir) {
/*      */             case 0:
/*  227 */               d1 = 3.0D;
/*      */               break;
/*      */             case 5:
/*  231 */               d1 = 0.0D;
/*      */               break;
/*      */             case 4:
/*  235 */               d1 = 2.0D;
/*      */               break;
/*      */           } 
/*  239 */           if (d1 == 0.0D) {
/*  239 */             d3 = minU;
/*  239 */             d4 = maxV;
/*  239 */             d6 = maxU;
/*  239 */             d9 = minV;
/*  240 */           } else if (d1 == 2.0D) {
/*  240 */             d2 = maxU;
/*  240 */             d5 = maxV;
/*  240 */             d7 = minU;
/*  240 */             d8 = minV;
/*  241 */           } else if (d1 == 3.0D) {
/*  241 */             d2 = maxU;
/*  241 */             d5 = maxV;
/*  241 */             d7 = minU;
/*  241 */             d8 = minV;
/*  241 */             d3 = minU;
/*  241 */             d4 = maxV;
/*  241 */             d6 = maxU;
/*  241 */             d9 = minV;
/*      */           } 
/*  243 */           x_2 = (double)x + bedHeight;
/*  244 */           y_1 = (double)y + 0.0D;
/*  245 */           y_2 = (double)y + 1.0D;
/*  246 */           z_1 = (double)z + 0.0D;
/*  247 */           z_2 = (double)z + 1.0D;
/*  248 */           tessellator.func_78374_a(x_2, y_2, z_1, d6, d8);
/*  249 */           tessellator.func_78374_a(x_2, y_2, z_2, d2, d4);
/*  250 */           tessellator.func_78374_a(x_2, y_1, z_2, d3, d5);
/*  251 */           tessellator.func_78374_a(x_2, y_1, z_1, d7, d9);
/*      */           break;
/*      */         case 5:
/*  254 */           switch (connDir) {
/*      */             case 3:
/*  257 */               d1 = 2.0D;
/*      */               break;
/*      */             case 2:
/*  261 */               d1 = 0.0D;
/*      */               break;
/*      */             case 1:
/*  265 */               d1 = 3.0D;
/*      */               break;
/*      */           } 
/*  272 */           if (d1 == 0.0D) {
/*  272 */             d3 = minU;
/*  272 */             d4 = maxV;
/*  272 */             d6 = maxU;
/*  272 */             d9 = minV;
/*  273 */           } else if (d1 == 2.0D) {
/*  273 */             d2 = maxU;
/*  273 */             d5 = maxV;
/*  273 */             d7 = minU;
/*  273 */             d8 = minV;
/*  274 */           } else if (d1 == 3.0D) {
/*  274 */             d2 = maxU;
/*  274 */             d5 = maxV;
/*  274 */             d7 = minU;
/*  274 */             d8 = minV;
/*  274 */             d3 = minU;
/*  274 */             d4 = maxV;
/*  274 */             d6 = maxU;
/*  274 */             d9 = minV;
/*      */           } 
/*  276 */           x_1 = (double)x + 0.0D;
/*  277 */           x_2 = (double)x + 1.0D;
/*  278 */           y_1 = (double)y + 0.0D;
/*  279 */           y_2 = (double)y + 1.0D;
/*  280 */           z_2 = (double)z + bedHeightRev;
/*  281 */           tessellator.func_78374_a(x_2, y_1, z_2, d6, d8);
/*  282 */           tessellator.func_78374_a(x_1, y_1, z_2, d2, d4);
/*  283 */           tessellator.func_78374_a(x_1, y_2, z_2, d3, d5);
/*  284 */           tessellator.func_78374_a(x_2, y_2, z_2, d7, d9);
/*      */           break;
/*      */         case 4:
/*  287 */           switch (connDir) {
/*      */             case 3:
/*  290 */               d1 = 2.0D;
/*      */               break;
/*      */             case 2:
/*  294 */               d1 = 0.0D;
/*      */               break;
/*      */             case 0:
/*  301 */               d1 = 3.0D;
/*      */               break;
/*      */           } 
/*  305 */           if (d1 == 0.0D) {
/*  305 */             d3 = minU;
/*  305 */             d4 = maxV;
/*  305 */             d6 = maxU;
/*  305 */             d9 = minV;
/*  306 */           } else if (d1 == 2.0D) {
/*  306 */             d2 = maxU;
/*  306 */             d5 = maxV;
/*  306 */             d7 = minU;
/*  306 */             d8 = minV;
/*  307 */           } else if (d1 == 3.0D) {
/*  307 */             d2 = maxU;
/*  307 */             d5 = maxV;
/*  307 */             d7 = minU;
/*  307 */             d8 = minV;
/*  307 */             d3 = minU;
/*  307 */             d4 = maxV;
/*  307 */             d6 = maxU;
/*  307 */             d9 = minV;
/*      */           } 
/*  309 */           x_1 = (double)x + 0.0D;
/*  310 */           x_2 = (double)x + 1.0D;
/*  311 */           y_1 = (double)y + 0.0D;
/*  312 */           y_2 = (double)y + 1.0D;
/*  313 */           z_2 = (double)z + bedHeight;
/*  314 */           tessellator.func_78374_a(x_2, y_2, z_2, d6, d8);
/*  315 */           tessellator.func_78374_a(x_1, y_2, z_2, d2, d4);
/*  316 */           tessellator.func_78374_a(x_1, y_1, z_2, d3, d5);
/*  317 */           tessellator.func_78374_a(x_2, y_1, z_2, d7, d9);
/*      */           break;
/*      */         case 1:
/*  320 */           switch (connDir) {
/*      */             case 3:
/*  323 */               d1 = 2.0D;
/*      */               break;
/*      */             case 2:
/*  327 */               d1 = 0.0D;
/*      */               break;
/*      */             case 4:
/*  334 */               d1 = 3.0D;
/*      */               break;
/*      */           } 
/*  338 */           if (d1 == 0.0D) {
/*  338 */             d3 = minU;
/*  338 */             d4 = maxV;
/*  338 */             d6 = maxU;
/*  338 */             d9 = minV;
/*  339 */           } else if (d1 == 2.0D) {
/*  339 */             d2 = maxU;
/*  339 */             d5 = maxV;
/*  339 */             d7 = minU;
/*  339 */             d8 = minV;
/*  340 */           } else if (d1 == 3.0D) {
/*  340 */             d2 = maxU;
/*  340 */             d5 = maxV;
/*  340 */             d7 = minU;
/*  340 */             d8 = minV;
/*  340 */             d3 = minU;
/*  340 */             d4 = maxV;
/*  340 */             d6 = maxU;
/*  340 */             d9 = minV;
/*      */           } 
/*  342 */           x_1 = (double)x + 0.0D;
/*  343 */           x_2 = (double)x + 1.0D;
/*  344 */           y_2 = (double)y + bedHeightRev;
/*  345 */           z_1 = (double)z + 0.0D;
/*  346 */           z_2 = (double)z + 1.0D;
/*  347 */           tessellator.func_78374_a(x_2, y_2, z_2, d6, d8);
/*  348 */           tessellator.func_78374_a(x_1, y_2, z_2, d2, d4);
/*  349 */           tessellator.func_78374_a(x_1, y_2, z_1, d3, d5);
/*  350 */           tessellator.func_78374_a(x_2, y_2, z_1, d7, d9);
/*      */           break;
/*      */         case 0:
/*  354 */           switch (connDir) {
/*      */             case 3:
/*  357 */               d1 = 2.0D;
/*      */               break;
/*      */             case 2:
/*  361 */               d1 = 0.0D;
/*      */               break;
/*      */             case 5:
/*  365 */               d1 = 3.0D;
/*      */               break;
/*      */           } 
/*  372 */           if (d1 == 0.0D) {
/*  372 */             d3 = minU;
/*  372 */             d4 = maxV;
/*  372 */             d6 = maxU;
/*  372 */             d9 = minV;
/*  373 */           } else if (d1 == 2.0D) {
/*  373 */             d2 = maxU;
/*  373 */             d5 = maxV;
/*  373 */             d7 = minU;
/*  373 */             d8 = minV;
/*  374 */           } else if (d1 == 3.0D) {
/*  374 */             d2 = maxU;
/*  374 */             d5 = maxV;
/*  374 */             d7 = minU;
/*  374 */             d8 = minV;
/*  374 */             d3 = minU;
/*  374 */             d4 = maxV;
/*  374 */             d6 = maxU;
/*  374 */             d9 = minV;
/*      */           } 
/*  376 */           x_1 = (double)x + 0.0D;
/*  377 */           x_2 = (double)x + 1.0D;
/*  378 */           y_2 = (double)y + bedHeight;
/*  379 */           z_1 = (double)z + 0.0D;
/*  380 */           z_2 = (double)z + 1.0D;
/*  381 */           tessellator.func_78374_a(x_2, y_2, z_1, d6, d8);
/*  382 */           tessellator.func_78374_a(x_1, y_2, z_1, d2, d4);
/*  383 */           tessellator.func_78374_a(x_1, y_2, z_2, d3, d5);
/*  384 */           tessellator.func_78374_a(x_2, y_2, z_2, d7, d9);
/*      */           break;
/*      */       } 
/*      */     } 
/*  390 */     bedHeight = 0.5625D;
/*  391 */     bedHeightRev = 1.0D - bedHeight;
/*  394 */     icon = endIcon;
/*  395 */     minU = (double)icon.func_94209_e();
/*  396 */     maxU = (double)icon.func_94212_f();
/*  397 */     minV = (double)icon.func_94206_g();
/*  398 */     maxV = (double)icon.func_94210_h();
/*  399 */     double roatU1 = minU;
/*  400 */     double roatU2 = maxU;
/*  401 */     double roatV1 = minV;
/*  402 */     double roatV2 = minV;
/*  403 */     double roatU3 = minU;
/*  404 */     double roatU4 = maxU;
/*  405 */     double roatV3 = maxV;
/*  406 */     double roatV4 = maxV;
/*  407 */     tessellator.func_78386_a(colorTopG, colorTopG, colorTopG);
/*  411 */     double roat = -1.0D;
/*  413 */     double x_1 = (double)x + 0.0D;
/*  414 */     double x_2 = (double)x + 1.0D;
/*  415 */     double y_1 = (double)y + 0.0D;
/*  416 */     double y_2 = (double)y + 1.0D;
/*  417 */     double z_1 = (double)z + 0.0D;
/*  418 */     double z_2 = (double)z + 1.0D;
/*  420 */     switch (connDir) {
/*      */       case 3:
/*  423 */         end = x_2;
/*  424 */         switch (gravDir) {
/*      */           case 5:
/*  427 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  428 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  429 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  430 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  432 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  433 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  434 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  435 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  437 */             if (isBedHead) {
/*  438 */               double swp = y_1;
/*  439 */               y_1 = y_2;
/*  440 */               y_2 = swp;
/*  442 */               end = x_1;
/*      */             } 
/*  445 */             roat = 2.0D;
/*      */             break;
/*      */           case 4:
/*  449 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  450 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  451 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  452 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  454 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  455 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  456 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  457 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  459 */             if (isBedHead) {
/*  460 */               double swp = y_1;
/*  461 */               y_1 = y_2;
/*  462 */               y_2 = swp;
/*  464 */               end = x_1;
/*      */             } 
/*  467 */             roat = 0.0D;
/*      */             break;
/*      */           case 1:
/*  471 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  472 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  473 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  474 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  476 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  477 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  478 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  479 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  481 */             if (isBedHead) {
/*  482 */               double swp = z_1;
/*  483 */               z_1 = z_2;
/*  484 */               z_2 = swp;
/*  486 */               end = x_1;
/*      */             } 
/*  489 */             roat = 3.0D;
/*      */             break;
/*      */           case 0:
/*  493 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  494 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  495 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  496 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  498 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  499 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  500 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  501 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  503 */             if (isBedHead) {
/*  504 */               double swp = z_1;
/*  505 */               z_1 = z_2;
/*  506 */               z_2 = swp;
/*  508 */               end = x_1;
/*      */             } 
/*      */             break;
/*      */         } 
/*  514 */         if (roat == 0.0D) {
/*  514 */           roatU2 = minU;
/*  514 */           roatV1 = maxV;
/*  514 */           roatU3 = maxU;
/*  514 */           roatV4 = minV;
/*  515 */         } else if (roat == 2.0D) {
/*  515 */           roatU1 = maxU;
/*  515 */           roatV2 = maxV;
/*  515 */           roatU4 = minU;
/*  515 */           roatV3 = minV;
/*  516 */         } else if (roat == 3.0D) {
/*  516 */           roatU1 = maxU;
/*  516 */           roatV2 = maxV;
/*  516 */           roatU4 = minU;
/*  516 */           roatV3 = minV;
/*  516 */           roatU2 = minU;
/*  516 */           roatV1 = maxV;
/*  516 */           roatU3 = maxU;
/*  516 */           roatV4 = minV;
/*      */         } 
/*  518 */         tessellator.func_78374_a(end, y_1, z_1, roatU3, roatV3);
/*  519 */         tessellator.func_78374_a(end, y_2, z_1, roatU1, roatV1);
/*  520 */         tessellator.func_78374_a(end, y_2, z_2, roatU2, roatV2);
/*  521 */         tessellator.func_78374_a(end, y_1, z_2, roatU4, roatV4);
/*      */         break;
/*      */       case 2:
/*  526 */         end = x_1;
/*  527 */         switch (gravDir) {
/*      */           case 5:
/*  530 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  531 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  532 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  533 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  535 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  536 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  537 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  538 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  540 */             if (isBedHead) {
/*  541 */               double swp = y_1;
/*  542 */               y_1 = y_2;
/*  543 */               y_2 = swp;
/*  545 */               end = x_2;
/*      */             } 
/*  548 */             roat = 0.0D;
/*      */             break;
/*      */           case 4:
/*  552 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  553 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  554 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  555 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  557 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  558 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  559 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  560 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  562 */             if (isBedHead) {
/*  563 */               double swp = y_1;
/*  564 */               y_1 = y_2;
/*  565 */               y_2 = swp;
/*  567 */               end = x_2;
/*      */             } 
/*  570 */             roat = 2.0D;
/*      */             break;
/*      */           case 1:
/*  573 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  574 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  575 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  576 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  578 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  579 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  580 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  581 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  584 */             if (isBedHead) {
/*  585 */               double swp = z_1;
/*  586 */               z_1 = z_2;
/*  587 */               z_2 = swp;
/*  589 */               end = x_2;
/*      */             } 
/*  591 */             roat = 3.0D;
/*      */             break;
/*      */           case 0:
/*  595 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  596 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  597 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  598 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  600 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  601 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  602 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  603 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  605 */             if (isBedHead) {
/*  606 */               double swp = z_1;
/*  607 */               z_1 = z_2;
/*  608 */               z_2 = swp;
/*  610 */               end = x_2;
/*      */             } 
/*      */             break;
/*      */         } 
/*  616 */         if (roat == 0.0D) {
/*  616 */           roatU2 = minU;
/*  616 */           roatV1 = maxV;
/*  616 */           roatU3 = maxU;
/*  616 */           roatV4 = minV;
/*  617 */         } else if (roat == 2.0D) {
/*  617 */           roatU1 = maxU;
/*  617 */           roatV2 = maxV;
/*  617 */           roatU4 = minU;
/*  617 */           roatV3 = minV;
/*  618 */         } else if (roat == 3.0D) {
/*  618 */           roatU1 = maxU;
/*  618 */           roatV2 = maxV;
/*  618 */           roatU4 = minU;
/*  618 */           roatV3 = minV;
/*  618 */           roatU2 = minU;
/*  618 */           roatV1 = maxV;
/*  618 */           roatU3 = maxU;
/*  618 */           roatV4 = minV;
/*      */         } 
/*  620 */         tessellator.func_78374_a(end, y_1, z_2, roatU3, roatV3);
/*  621 */         tessellator.func_78374_a(end, y_2, z_2, roatU1, roatV1);
/*  622 */         tessellator.func_78374_a(end, y_2, z_1, roatU2, roatV2);
/*  623 */         tessellator.func_78374_a(end, y_1, z_1, roatU4, roatV4);
/*      */         break;
/*      */       case 5:
/*  627 */         end = z_2;
/*  628 */         switch (gravDir) {
/*      */           case 3:
/*  631 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  632 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  633 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  634 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  636 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  637 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  638 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  639 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  641 */             if (isBedHead) {
/*  642 */               double swp = y_1;
/*  643 */               y_1 = y_2;
/*  644 */               y_2 = swp;
/*  646 */               end = z_1;
/*      */             } 
/*  649 */             roat = 0.0D;
/*      */             break;
/*      */           case 2:
/*  653 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  654 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  655 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  656 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  658 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  659 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  660 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  661 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  663 */             if (isBedHead) {
/*  664 */               double swp = y_1;
/*  665 */               y_1 = y_2;
/*  666 */               y_2 = swp;
/*  668 */               end = z_1;
/*      */             } 
/*  671 */             roat = 2.0D;
/*      */             break;
/*      */           case 1:
/*  675 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  676 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  677 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  678 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  680 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  681 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  682 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  683 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  686 */             if (isBedHead) {
/*  687 */               double swp = x_1;
/*  688 */               x_1 = x_2;
/*  689 */               x_2 = swp;
/*  691 */               end = z_1;
/*      */             } 
/*  693 */             roat = 3.0D;
/*      */             break;
/*      */           case 0:
/*  697 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  698 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  699 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  700 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  702 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  703 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  704 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  705 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  707 */             if (isBedHead) {
/*  708 */               double swp = x_1;
/*  709 */               x_1 = x_2;
/*  710 */               x_2 = swp;
/*  712 */               end = z_1;
/*      */             } 
/*      */             break;
/*      */         } 
/*  718 */         if (roat == 0.0D) {
/*  718 */           roatU2 = minU;
/*  718 */           roatV1 = maxV;
/*  718 */           roatU3 = maxU;
/*  718 */           roatV4 = minV;
/*  719 */         } else if (roat == 2.0D) {
/*  719 */           roatU1 = maxU;
/*  719 */           roatV2 = maxV;
/*  719 */           roatU4 = minU;
/*  719 */           roatV3 = minV;
/*  720 */         } else if (roat == 3.0D) {
/*  720 */           roatU1 = maxU;
/*  720 */           roatV2 = maxV;
/*  720 */           roatU4 = minU;
/*  720 */           roatV3 = minV;
/*  720 */           roatU2 = minU;
/*  720 */           roatV1 = maxV;
/*  720 */           roatU3 = maxU;
/*  720 */           roatV4 = minV;
/*      */         } 
/*  722 */         tessellator.func_78374_a(x_2, y_1, end, roatU3, roatV3);
/*  723 */         tessellator.func_78374_a(x_2, y_2, end, roatU1, roatV1);
/*  724 */         tessellator.func_78374_a(x_1, y_2, end, roatU2, roatV2);
/*  725 */         tessellator.func_78374_a(x_1, y_1, end, roatU4, roatV4);
/*      */         break;
/*      */       case 4:
/*  730 */         end = z_1;
/*  731 */         switch (gravDir) {
/*      */           case 3:
/*  734 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  735 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  736 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  737 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  739 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  740 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  741 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  742 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  744 */             if (isBedHead) {
/*  745 */               double swp = y_1;
/*  746 */               y_1 = y_2;
/*  747 */               y_2 = swp;
/*  749 */               end = z_2;
/*      */             } 
/*  752 */             roat = 2.0D;
/*      */             break;
/*      */           case 2:
/*  756 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  757 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  758 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  759 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  761 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  762 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  763 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  764 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  766 */             if (isBedHead) {
/*  767 */               double swp = y_1;
/*  768 */               y_1 = y_2;
/*  769 */               y_2 = swp;
/*  771 */               end = z_2;
/*      */             } 
/*  774 */             roat = 0.0D;
/*      */             break;
/*      */           case 1:
/*  778 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  779 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  780 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  781 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  783 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  784 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  785 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  786 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  789 */             if (isBedHead) {
/*  790 */               double swp = x_1;
/*  791 */               x_1 = x_2;
/*  792 */               x_2 = swp;
/*  794 */               end = z_2;
/*      */             } 
/*  796 */             roat = 3.0D;
/*      */             break;
/*      */           case 0:
/*  800 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  801 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  802 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  803 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  805 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  806 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  807 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  808 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  810 */             if (isBedHead) {
/*  811 */               double swp = x_1;
/*  812 */               x_1 = x_2;
/*  813 */               x_2 = swp;
/*  815 */               end = z_2;
/*      */             } 
/*      */             break;
/*      */         } 
/*  821 */         if (roat == 0.0D) {
/*  821 */           roatU2 = minU;
/*  821 */           roatV1 = maxV;
/*  821 */           roatU3 = maxU;
/*  821 */           roatV4 = minV;
/*  822 */         } else if (roat == 2.0D) {
/*  822 */           roatU1 = maxU;
/*  822 */           roatV2 = maxV;
/*  822 */           roatU4 = minU;
/*  822 */           roatV3 = minV;
/*  823 */         } else if (roat == 3.0D) {
/*  823 */           roatU1 = maxU;
/*  823 */           roatV2 = maxV;
/*  823 */           roatU4 = minU;
/*  823 */           roatV3 = minV;
/*  823 */           roatU2 = minU;
/*  823 */           roatV1 = maxV;
/*  823 */           roatU3 = maxU;
/*  823 */           roatV4 = minV;
/*      */         } 
/*  825 */         tessellator.func_78374_a(x_1, y_1, end, roatU3, roatV3);
/*  826 */         tessellator.func_78374_a(x_1, y_2, end, roatU1, roatV1);
/*  827 */         tessellator.func_78374_a(x_2, y_2, end, roatU2, roatV2);
/*  828 */         tessellator.func_78374_a(x_2, y_1, end, roatU4, roatV4);
/*      */         break;
/*      */       case 1:
/*  833 */         end = y_2;
/*  834 */         switch (gravDir) {
/*      */           case 3:
/*  837 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  838 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  839 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  840 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  842 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  843 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  844 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  845 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  847 */             if (isBedHead) {
/*  848 */               double swp = z_1;
/*  849 */               z_1 = z_2;
/*  850 */               z_2 = swp;
/*  852 */               end = y_1;
/*      */             } 
/*  855 */             roat = 2.0D;
/*      */             break;
/*      */           case 2:
/*  859 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  860 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  861 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  862 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  864 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  865 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  866 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  867 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  869 */             if (isBedHead) {
/*  870 */               double swp = z_1;
/*  871 */               z_1 = z_2;
/*  872 */               z_2 = swp;
/*  874 */               end = y_1;
/*      */             } 
/*  877 */             roat = 0.0D;
/*      */             break;
/*      */           case 5:
/*  881 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  882 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  883 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  884 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  886 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  887 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  888 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  889 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  892 */             if (isBedHead) {
/*  893 */               double swp = x_1;
/*  894 */               x_1 = x_2;
/*  895 */               x_2 = swp;
/*  897 */               end = y_1;
/*      */             } 
/*  899 */             roat = 3.0D;
/*      */             break;
/*      */           case 4:
/*  903 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  904 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  905 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  906 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  908 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  909 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  910 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  911 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  913 */             if (isBedHead) {
/*  914 */               double swp = x_1;
/*  915 */               x_1 = x_2;
/*  916 */               x_2 = swp;
/*  918 */               end = y_1;
/*      */             } 
/*      */             break;
/*      */         } 
/*  924 */         if (roat == 0.0D) {
/*  924 */           roatU2 = minU;
/*  924 */           roatV1 = maxV;
/*  924 */           roatU3 = maxU;
/*  924 */           roatV4 = minV;
/*  925 */         } else if (roat == 2.0D) {
/*  925 */           roatU1 = maxU;
/*  925 */           roatV2 = maxV;
/*  925 */           roatU4 = minU;
/*  925 */           roatV3 = minV;
/*  926 */         } else if (roat == 3.0D) {
/*  926 */           roatU1 = maxU;
/*  926 */           roatV2 = maxV;
/*  926 */           roatU4 = minU;
/*  926 */           roatV3 = minV;
/*  926 */           roatU2 = minU;
/*  926 */           roatV1 = maxV;
/*  926 */           roatU3 = maxU;
/*  926 */           roatV4 = minV;
/*      */         } 
/*  928 */         tessellator.func_78374_a(x_1, end, z_1, roatU3, roatV3);
/*  929 */         tessellator.func_78374_a(x_1, end, z_2, roatU1, roatV1);
/*  930 */         tessellator.func_78374_a(x_2, end, z_2, roatU2, roatV2);
/*  931 */         tessellator.func_78374_a(x_2, end, z_1, roatU4, roatV4);
/*      */         break;
/*      */       case 0:
/*  936 */         end = y_1;
/*  937 */         switch (gravDir) {
/*      */           case 3:
/*  940 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  941 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  942 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  943 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  945 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  946 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  947 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  948 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  950 */             if (isBedHead) {
/*  951 */               double swp = z_1;
/*  952 */               z_1 = z_2;
/*  953 */               z_2 = swp;
/*  955 */               end = y_2;
/*      */             } 
/*  958 */             roat = 0.0D;
/*      */             break;
/*      */           case 2:
/*  962 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  963 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  964 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  965 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  967 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  968 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  969 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  970 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  972 */             if (isBedHead) {
/*  973 */               double swp = z_1;
/*  974 */               z_1 = z_2;
/*  975 */               z_2 = swp;
/*  977 */               end = y_2;
/*      */             } 
/*  980 */             roat = 2.0D;
/*      */             break;
/*      */           case 5:
/*  984 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  985 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  986 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  987 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  989 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/*  990 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/*  991 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/*  992 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/*  995 */             if (isBedHead) {
/*  996 */               double swp = x_1;
/*  997 */               x_1 = x_2;
/*  998 */               x_2 = swp;
/* 1000 */               end = y_2;
/*      */             } 
/* 1002 */             roat = 3.0D;
/*      */             break;
/*      */           case 4:
/* 1006 */             tessellator.func_78374_a(x_1, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/* 1007 */             tessellator.func_78374_a(x_1, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/* 1008 */             tessellator.func_78374_a(x_1, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/* 1009 */             tessellator.func_78374_a(x_1, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/* 1011 */             tessellator.func_78374_a(x_2, y_2, z_1, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94210_h());
/* 1012 */             tessellator.func_78374_a(x_2, y_2, z_2, (double)sideIcon.func_94212_f(), (double)sideIcon.func_94206_g());
/* 1013 */             tessellator.func_78374_a(x_2, y_1, z_2, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94206_g());
/* 1014 */             tessellator.func_78374_a(x_2, y_1, z_1, (double)sideIcon.func_94209_e(), (double)sideIcon.func_94210_h());
/* 1016 */             if (isBedHead) {
/* 1017 */               double swp = x_1;
/* 1018 */               x_1 = x_2;
/* 1019 */               x_2 = swp;
/* 1021 */               end = y_2;
/*      */             } 
/*      */             break;
/*      */         } 
/* 1027 */         if (roat == 0.0D) {
/* 1027 */           roatU2 = minU;
/* 1027 */           roatV1 = maxV;
/* 1027 */           roatU3 = maxU;
/* 1027 */           roatV4 = minV;
/* 1028 */         } else if (roat == 2.0D) {
/* 1028 */           roatU1 = maxU;
/* 1028 */           roatV2 = maxV;
/* 1028 */           roatU4 = minU;
/* 1028 */           roatV3 = minV;
/* 1029 */         } else if (roat == 3.0D) {
/* 1029 */           roatU1 = maxU;
/* 1029 */           roatV2 = maxV;
/* 1029 */           roatU4 = minU;
/* 1029 */           roatV3 = minV;
/* 1029 */           roatU2 = minU;
/* 1029 */           roatV1 = maxV;
/* 1029 */           roatU3 = maxU;
/* 1029 */           roatV4 = minV;
/*      */         } 
/* 1031 */         tessellator.func_78374_a(x_2, end, z_1, roatU3, roatV3);
/* 1032 */         tessellator.func_78374_a(x_2, end, z_2, roatU1, roatV1);
/* 1033 */         tessellator.func_78374_a(x_1, end, z_2, roatU2, roatV2);
/* 1034 */         tessellator.func_78374_a(x_1, end, z_1, roatU4, roatV4);
/*      */         break;
/*      */     } 
/* 1040 */     renderer.field_147842_e = false;
/* 1041 */     return true;
/*      */   }
/*      */   
/*      */   public boolean shouldRender3DInInventory(int modelId) {
/* 1045 */     return false;
/*      */   }
/*      */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */