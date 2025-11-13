/*    */ package jp.mc.ancientred.starminer.basics.item.render;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.ItemRenderer;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.client.renderer.texture.TextureUtil;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.IItemRenderer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class RenderEquippedItemGrappleGun implements IItemRenderer {
/* 19 */   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
/*    */   
/*    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
/* 22 */     return (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON);
/*    */   }
/*    */   
/*    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
/* 27 */     return true;
/*    */   }
/*    */   
/*    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack itemStack, Object... data) {
/* 32 */     int renderPass = 0;
/* 33 */     if (data == null || data[1] == null || !(data[1] instanceof EntityLivingBase))
/*    */       return; 
/* 34 */     EntityLivingBase entity = (EntityLivingBase)data[1];
/* 36 */     TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
/* 37 */     IIcon iicon = entity.func_70620_b(itemStack, renderPass);
/* 38 */     if (iicon == null)
/*    */       return; 
/* 40 */     texturemanager.func_110577_a(texturemanager.func_130087_a(itemStack.func_94608_d()));
/* 42 */     TextureUtil.func_152777_a(false, false, 1.0F);
/* 43 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 44 */     float f = iicon.func_94209_e();
/* 45 */     float f1 = iicon.func_94212_f();
/* 46 */     float f2 = iicon.func_94206_g();
/* 47 */     float f3 = iicon.func_94210_h();
/* 48 */     float f4 = 0.0F;
/* 49 */     float f5 = -0.5F;
/* 50 */     GL11.glEnable(32826);
/* 51 */     GL11.glTranslatef(-f4, -f5, 0.0F);
/* 52 */     float f6 = 1.5F;
/* 53 */     GL11.glScalef(f6, f6, f6);
/* 54 */     GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
/* 55 */     GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
/* 56 */     GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
/* 57 */     ItemRenderer.func_78439_a(tessellator, f1, f2, f, f3, iicon.func_94211_a(), iicon.func_94216_b(), 0.0625F);
/* 59 */     if (itemStack.hasEffect(renderPass)) {
/* 62 */       GL11.glDepthFunc(514);
/* 63 */       GL11.glDisable(2896);
/* 64 */       texturemanager.func_110577_a(RES_ITEM_GLINT);
/* 65 */       GL11.glEnable(3042);
/* 66 */       OpenGlHelper.func_148821_a(768, 1, 1, 0);
/* 67 */       float f7 = 0.76F;
/* 68 */       GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
/* 69 */       GL11.glMatrixMode(5890);
/* 70 */       GL11.glPushMatrix();
/* 71 */       float f8 = 0.125F;
/* 72 */       GL11.glScalef(f8, f8, f8);
/* 73 */       float f9 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0F * 8.0F;
/* 74 */       GL11.glTranslatef(f9, 0.0F, 0.0F);
/* 75 */       GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
/* 76 */       ItemRenderer.func_78439_a(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
/* 77 */       GL11.glPopMatrix();
/* 78 */       GL11.glPushMatrix();
/* 79 */       GL11.glScalef(f8, f8, f8);
/* 80 */       f9 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0F * 8.0F;
/* 81 */       GL11.glTranslatef(-f9, 0.0F, 0.0F);
/* 82 */       GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/* 83 */       ItemRenderer.func_78439_a(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
/* 84 */       GL11.glPopMatrix();
/* 85 */       GL11.glMatrixMode(5888);
/* 86 */       GL11.glDisable(3042);
/* 87 */       GL11.glEnable(2896);
/* 88 */       GL11.glDepthFunc(515);
/*    */     } 
/* 91 */     GL11.glDisable(32826);
/* 92 */     texturemanager.func_110577_a(texturemanager.func_130087_a(itemStack.func_94608_d()));
/* 93 */     TextureUtil.func_147945_b();
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */