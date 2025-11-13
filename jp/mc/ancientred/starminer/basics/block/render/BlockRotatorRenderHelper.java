/*     */ package jp.mc.ancientred.starminer.basics.block.render;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.basics.SMReflectionHelperClient;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockRotator;
/*     */ import jp.mc.ancientred.starminer.basics.dummy.DummyRotatedBlockAccess;
/*     */ import jp.mc.ancientred.starminer.basics.dummy.TesselatorWrapper;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityBlockRotator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDoublePlant;
/*     */ import net.minecraft.client.renderer.EntityRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraftforge.client.ForgeHooksClient;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class BlockRotatorRenderHelper implements ISimpleBlockRenderingHandler {
/*     */   public static final int RENDER_TYPE = 398378466;
/*     */   
/*     */   private DummyRotatedBlockAccess wrappedBlockAccess;
/*     */   
/*     */   private TesselatorWrapper wrappedTesselator;
/*     */   
/*     */   public int getRenderId() {
/*  35 */     return 398378466;
/*     */   }
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/*  39 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  40 */     block.func_149683_g();
/*  41 */     renderer.func_147775_a(block);
/*  42 */     GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  43 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  44 */     tessellator.func_78382_b();
/*  45 */     tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
/*  46 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 0, metadata));
/*  47 */     tessellator.func_78381_a();
/*  48 */     tessellator.func_78382_b();
/*  49 */     tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/*  50 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 1, metadata));
/*  51 */     tessellator.func_78381_a();
/*  52 */     tessellator.func_78382_b();
/*  53 */     tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*  54 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 2, metadata));
/*  55 */     tessellator.func_78381_a();
/*  56 */     tessellator.func_78382_b();
/*  57 */     tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/*  58 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 3, metadata));
/*  59 */     tessellator.func_78381_a();
/*  60 */     tessellator.func_78382_b();
/*  61 */     tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
/*  62 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 4, metadata));
/*  63 */     tessellator.func_78381_a();
/*  64 */     tessellator.func_78382_b();
/*  65 */     tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
/*  66 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 5, metadata));
/*  67 */     tessellator.func_78381_a();
/*  68 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int parX, int parY, int parZ, Block block, int modelId, RenderBlocks renderer) {
/*  78 */     Tessellator tessellatorOrg = Tessellator.field_78398_a;
/*  79 */     IBlockAccess blockAccessOrg = renderer.field_147845_a;
/*  81 */     block;
/*  81 */     TileEntityBlockRotator tileEntity = BlockRotator.getTileEntityBlockRotator(world, parX, parY, parZ);
/*  83 */     Block storedBlock = tileEntity.getStoredBlock();
/*  84 */     GravityDirection gDir = tileEntity.getGravityDirection();
/*  87 */     if (storedBlock == null || storedBlock == Blocks.field_150350_a)
/*  88 */       return false; 
/*  92 */     if (storedBlock.func_149701_w() != ForgeHooksClient.getWorldRenderPass())
/*  93 */       return false; 
/*     */     try {
/*  98 */       if (this.wrappedBlockAccess == null)
/*  98 */         this.wrappedBlockAccess = DummyRotatedBlockAccess.get(); 
/*  99 */       if (this.wrappedTesselator == null)
/*  99 */         this.wrappedTesselator = new TesselatorWrapper(); 
/* 102 */       if (renderer.field_147845_a != this.wrappedBlockAccess)
/* 103 */         renderer.field_147845_a = this.wrappedBlockAccess.wrapp(blockAccessOrg, gDir, tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e); 
/* 106 */       if (Tessellator.field_78398_a != this.wrappedTesselator)
/* 107 */         SMReflectionHelperClient.setWrappedTesselator(this.wrappedTesselator.wrap(tessellatorOrg, gDir, (double)parX + 0.5D, (double)parY + 0.5D, (double)parZ + 0.5D)); 
/* 111 */       if (storedBlock instanceof BlockDoublePlant) {
/* 113 */         renderBlockDoublePlant((BlockDoublePlant)storedBlock, tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e, renderer);
/*     */       } else {
/* 115 */         renderer.func_147769_a(storedBlock, tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
/*     */       } 
/*     */     } finally {
/* 120 */       renderer.field_147845_a = blockAccessOrg;
/* 121 */       SMReflectionHelperClient.setWrappedTesselator(tessellatorOrg);
/*     */     } 
/* 124 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 129 */     return true;
/*     */   }
/*     */   
/*     */   public boolean renderBlockDoublePlant(BlockDoublePlant blockDoublePlant, int parX, int parY, int parZ, RenderBlocks renderer) {
/*     */     int k1;
/* 135 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 136 */     tessellator.func_78380_c(blockDoublePlant.func_149677_c(renderer.field_147845_a, parX, parY, parZ));
/* 137 */     int l = blockDoublePlant.func_149720_d(renderer.field_147845_a, parX, parY, parZ);
/* 138 */     float f = (float)(l >> 16 & 0xFF) / 255.0F;
/* 139 */     float f1 = (float)(l >> 8 & 0xFF) / 255.0F;
/* 140 */     float f2 = (float)(l & 0xFF) / 255.0F;
/* 142 */     if (EntityRenderer.field_78517_a) {
/* 144 */       float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
/* 145 */       float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
/* 146 */       float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
/* 147 */       f = f3;
/* 148 */       f1 = f4;
/* 149 */       f2 = f5;
/*     */     } 
/* 152 */     tessellator.func_78386_a(f, f1, f2);
/* 154 */     double d19 = (double)parX;
/* 155 */     double d0 = (double)parY;
/* 156 */     double d1 = (double)parZ;
/* 158 */     int i1 = renderer.field_147845_a.func_72805_g(parX, parY, parZ);
/* 159 */     boolean flag = false;
/* 160 */     boolean flag1 = BlockDoublePlant.func_149887_c(i1);
/* 163 */     if (flag1) {
/* 165 */       if (renderer.field_147845_a.func_147439_a(parX, parY - 1, parZ) != blockDoublePlant)
/* 167 */         return false; 
/* 170 */       k1 = BlockDoublePlant.func_149890_d(renderer.field_147845_a.func_72805_g(parX, parY - 1, parZ));
/*     */     } else {
/* 174 */       k1 = BlockDoublePlant.func_149890_d(i1);
/*     */     } 
/* 177 */     IIcon iicon = blockDoublePlant.func_149888_a(flag1, k1);
/* 178 */     renderer.func_147765_a(iicon, d19, d0, d1, 1.0F);
/* 180 */     if (flag1 && k1 == 0) {
/* 182 */       IIcon iicon1 = blockDoublePlant.field_149891_b[0];
/* 183 */       double d2 = Math.cos(0.8D) * Math.PI * 0.1D;
/* 184 */       double d3 = Math.cos(d2);
/* 185 */       double d4 = Math.sin(d2);
/* 186 */       double d5 = (double)iicon1.func_94209_e();
/* 187 */       double d6 = (double)iicon1.func_94206_g();
/* 188 */       double d7 = (double)iicon1.func_94212_f();
/* 189 */       double d8 = (double)iicon1.func_94210_h();
/* 190 */       double d9 = 0.3D;
/* 191 */       double d10 = -0.05D;
/* 192 */       double d11 = 0.5D + 0.3D * d3 - 0.5D * d4;
/* 193 */       double d12 = 0.5D + 0.5D * d3 + 0.3D * d4;
/* 194 */       double d13 = 0.5D + 0.3D * d3 + 0.5D * d4;
/* 195 */       double d14 = 0.5D + -0.5D * d3 + 0.3D * d4;
/* 196 */       double d15 = 0.5D + -0.05D * d3 + 0.5D * d4;
/* 197 */       double d16 = 0.5D + -0.5D * d3 + -0.05D * d4;
/* 198 */       double d17 = 0.5D + -0.05D * d3 - 0.5D * d4;
/* 199 */       double d18 = 0.5D + 0.5D * d3 + -0.05D * d4;
/* 200 */       tessellator.func_78374_a(d19 + d15, d0 + 1.0D, d1 + d16, d5, d8);
/* 201 */       tessellator.func_78374_a(d19 + d17, d0 + 1.0D, d1 + d18, d7, d8);
/* 202 */       tessellator.func_78374_a(d19 + d11, d0 + 0.0D, d1 + d12, d7, d6);
/* 203 */       tessellator.func_78374_a(d19 + d13, d0 + 0.0D, d1 + d14, d5, d6);
/* 204 */       IIcon iicon2 = blockDoublePlant.field_149891_b[1];
/* 205 */       d5 = (double)iicon2.func_94209_e();
/* 206 */       d6 = (double)iicon2.func_94206_g();
/* 207 */       d7 = (double)iicon2.func_94212_f();
/* 208 */       d8 = (double)iicon2.func_94210_h();
/* 209 */       tessellator.func_78374_a(d19 + d17, d0 + 1.0D, d1 + d18, d5, d8);
/* 210 */       tessellator.func_78374_a(d19 + d15, d0 + 1.0D, d1 + d16, d7, d8);
/* 211 */       tessellator.func_78374_a(d19 + d13, d0 + 0.0D, d1 + d14, d7, d6);
/* 212 */       tessellator.func_78374_a(d19 + d11, d0 + 0.0D, d1 + d12, d5, d6);
/*     */     } 
/* 215 */     return true;
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */