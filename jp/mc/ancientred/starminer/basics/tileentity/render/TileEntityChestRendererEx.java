/*     */ package jp.mc.ancientred.starminer.basics.tileentity.render;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Calendar;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityChestEx;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.model.ModelChest;
/*     */ import net.minecraft.client.model.ModelLargeChest;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class TileEntityChestRendererEx extends TileEntitySpecialRenderer {
/*  27 */   private static final ResourceLocation field_147507_b = new ResourceLocation("textures/entity/chest/trapped_double.png");
/*     */   
/*  28 */   private static final ResourceLocation field_147508_c = new ResourceLocation("textures/entity/chest/christmas_double.png");
/*     */   
/*  29 */   private static final ResourceLocation field_147505_d = new ResourceLocation("textures/entity/chest/normal_double.png");
/*     */   
/*  30 */   private static final ResourceLocation field_147506_e = new ResourceLocation("textures/entity/chest/trapped.png");
/*     */   
/*  31 */   private static final ResourceLocation field_147503_f = new ResourceLocation("textures/entity/chest/christmas.png");
/*     */   
/*  32 */   private static final ResourceLocation field_147504_g = new ResourceLocation("textures/entity/chest/normal.png");
/*     */   
/*  33 */   private ModelChest modelChest = new ModelChest();
/*     */   
/*  34 */   private ModelChest modelLargeChest = new ModelLargeChest();
/*     */   
/*     */   private boolean field_147509_j;
/*     */   
/*     */   private static final String __OBFID = "CL_00000965";
/*     */   
/*     */   public TileEntityChestRendererEx() {
/*  40 */     Calendar calendar = Calendar.getInstance();
/*  42 */     if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26)
/*  44 */       this.field_147509_j = true; 
/*     */   }
/*     */   
/*     */   public void renderTileEntityAt(TileEntityChestEx tileEntityChestEx, double posX, double posY, double posZ, float partialTick) {
/*     */     int meta;
/*     */     ModelChest modelchest;
/*  50 */     GravityDirection gDir = tileEntityChestEx.getGravityDirection();
/*  53 */     if (!tileEntityChestEx.func_145830_o()) {
/*  55 */       meta = 0;
/*     */     } else {
/*  59 */       Block block = tileEntityChestEx.func_145838_q();
/*  60 */       meta = tileEntityChestEx.func_145832_p();
/*  62 */       if (block instanceof jp.mc.ancientred.starminer.basics.block.BlockChestEx && meta == 0)
/*  64 */         meta = tileEntityChestEx.func_145832_p(); 
/*     */     } 
/*  68 */     int adjacentChestInt = tileEntityChestEx.getAdjacentChestTo();
/*  70 */     if (adjacentChestInt == TileEntityChestEx.IS_adjacentChestZNeg)
/*     */       return; 
/*  74 */     if (adjacentChestInt == TileEntityChestEx.IS_adjacentChestXNeg)
/*     */       return; 
/*  81 */     if (adjacentChestInt == TileEntityChestEx.IS_adjacentChestNone) {
/*  84 */       modelchest = this.modelChest;
/*  86 */       if (tileEntityChestEx.func_145980_j() == 1) {
/*  89 */         func_147499_a(field_147506_e);
/*  91 */       } else if (this.field_147509_j) {
/*  93 */         func_147499_a(field_147503_f);
/*     */       } else {
/*  97 */         func_147499_a(field_147504_g);
/*     */       } 
/*     */     } else {
/* 103 */       modelchest = this.modelLargeChest;
/* 105 */       if (tileEntityChestEx.func_145980_j() == 1) {
/* 108 */         func_147499_a(field_147507_b);
/* 110 */       } else if (this.field_147509_j) {
/* 112 */         func_147499_a(field_147508_c);
/*     */       } else {
/* 116 */         func_147499_a(field_147505_d);
/*     */       } 
/*     */     } 
/* 120 */     GL11.glPushMatrix();
/* 121 */     GL11.glEnable(32826);
/* 122 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 123 */     GL11.glTranslatef((float)posX, (float)posY + 1.0F, (float)posZ + 1.0F);
/* 124 */     GL11.glScalef(1.0F, -1.0F, -1.0F);
/* 125 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/* 126 */     short rotateAroundY = 0;
/* 128 */     GL11.glRotatef(-180.0F * gDir.rotX, 1.0F, 0.0F, 0.0F);
/* 129 */     GL11.glRotatef(180.0F * gDir.rotZ, 0.0F, 0.0F, 1.0F);
/* 131 */     if (meta == 2)
/* 133 */       rotateAroundY = 180; 
/* 136 */     if (meta == 3)
/* 138 */       rotateAroundY = 0; 
/* 141 */     if (meta == 4)
/* 143 */       rotateAroundY = 90; 
/* 146 */     if (meta == 5)
/* 148 */       rotateAroundY = -90; 
/* 151 */     if (meta == 2 && adjacentChestInt == TileEntityChestEx.IS_adjacentChestXPos)
/* 153 */       GL11.glTranslatef(1.0F, 0.0F, 0.0F); 
/* 156 */     if (meta == 5 && adjacentChestInt == TileEntityChestEx.IS_adjacentChestZPos)
/* 158 */       GL11.glTranslatef(0.0F, 0.0F, -1.0F); 
/* 161 */     GL11.glRotatef((float)rotateAroundY, 0.0F, 1.0F, 0.0F);
/* 163 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 164 */     float f1 = tileEntityChestEx.prevLidAngle + (tileEntityChestEx.lidAngle - tileEntityChestEx.prevLidAngle) * partialTick;
/* 188 */     f1 = 1.0F - f1;
/* 189 */     f1 = 1.0F - f1 * f1 * f1;
/* 190 */     modelchest.field_78234_a.field_78795_f = -(f1 * 3.1415927F / 2.0F);
/* 191 */     modelchest.func_78231_a();
/* 192 */     GL11.glDisable(32826);
/* 193 */     GL11.glPopMatrix();
/* 194 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public void func_147500_a(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {
/* 199 */     renderTileEntityAt((TileEntityChestEx)p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */