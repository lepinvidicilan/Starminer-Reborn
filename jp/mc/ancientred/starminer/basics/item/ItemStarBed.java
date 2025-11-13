/*     */ package jp.mc.ancientred.starminer.basics.item;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import jp.mc.ancientred.starminer.api.Gravity;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.block.DirectionConst;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class ItemStarBed extends Item {
/*     */   public ItemStarBed() {
/*  21 */     func_111206_d("bed");
/*  22 */     func_77625_d(1);
/*     */   }
/*     */   
/*     */   public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
/*  28 */     if (par3World.field_72995_K)
/*  30 */       return true; 
/*  41 */     Gravity gravity = Gravity.getGravityProp((Entity)par2EntityPlayer);
/*  42 */     Vec3 lookVec = par2EntityPlayer.func_70676_i(1.0F);
/*  45 */     int gravityDir = -1;
/*  48 */     int connectDir = -1;
/*  50 */     int[] bodyPlace = { 0, 0, 0 };
/*  51 */     int[] surfaceCheck = { 0, 0, 0 };
/*  53 */     ForgeDirection surfaceCheckDir = ForgeDirection.UNKNOWN;
/*  55 */     boolean skipCascade = false;
/*  58 */     switch (gravity.gravityDirection) {
/*     */       case northTOsouth_ZP:
/*  61 */         if (par7 != 2)
/*  61 */           return false; 
/*  62 */         bodyPlace[2] = -1;
/*  65 */         surfaceCheck[2] = 1;
/*  66 */         surfaceCheckDir = ForgeDirection.NORTH;
/*  69 */         gravityDir = 5;
/*  72 */         skipCascade = true;
/*     */       case southTOnorth_ZN:
/*  75 */         if (!skipCascade) {
/*  77 */           if (par7 != 3)
/*  77 */             return false; 
/*  78 */           bodyPlace[2] = 1;
/*  81 */           surfaceCheck[2] = -1;
/*  82 */           surfaceCheckDir = ForgeDirection.SOUTH;
/*  85 */           gravityDir = 4;
/*     */         } 
/*  89 */         if (Math.abs(lookVec.field_72450_a) > Math.abs(lookVec.field_72448_b)) {
/*  90 */           if (lookVec.field_72450_a > 0.0D) {
/*  92 */             connectDir = 2;
/*     */           } else {
/*  95 */             connectDir = 3;
/*     */           } 
/*     */           break;
/*     */         } 
/*  98 */         if (lookVec.field_72448_b > 0.0D) {
/* 100 */           connectDir = 0;
/*     */         } else {
/* 103 */           connectDir = 1;
/*     */         } 
/*     */         break;
/*     */       case westTOeast_XP:
/* 109 */         if (par7 != 4)
/* 109 */           return false; 
/* 110 */         bodyPlace[0] = -1;
/* 113 */         surfaceCheck[0] = 1;
/* 114 */         surfaceCheckDir = ForgeDirection.WEST;
/* 117 */         gravityDir = 3;
/* 120 */         skipCascade = true;
/*     */       case eastTOwest_XN:
/* 123 */         if (!skipCascade) {
/* 125 */           if (par7 != 5)
/* 125 */             return false; 
/* 126 */           bodyPlace[0] = 1;
/* 129 */           surfaceCheck[0] = -1;
/* 130 */           surfaceCheckDir = ForgeDirection.EAST;
/* 133 */           gravityDir = 2;
/*     */         } 
/* 137 */         if (Math.abs(lookVec.field_72449_c) > Math.abs(lookVec.field_72448_b)) {
/* 138 */           if (lookVec.field_72449_c > 0.0D) {
/* 140 */             connectDir = 4;
/*     */           } else {
/* 143 */             connectDir = 5;
/*     */           } 
/*     */           break;
/*     */         } 
/* 146 */         if (lookVec.field_72448_b > 0.0D) {
/* 148 */           connectDir = 0;
/*     */         } else {
/* 151 */           connectDir = 1;
/*     */         } 
/*     */         break;
/*     */       case downTOup_YP:
/* 157 */         if (par7 != 0)
/* 157 */           return false; 
/* 158 */         bodyPlace[1] = -1;
/* 161 */         surfaceCheck[1] = 1;
/* 162 */         surfaceCheckDir = ForgeDirection.DOWN;
/* 165 */         gravityDir = 1;
/* 168 */         skipCascade = true;
/*     */       case upTOdown_YN:
/* 171 */         if (!skipCascade) {
/* 173 */           if (par7 != 1)
/* 173 */             return false; 
/* 174 */           bodyPlace[1] = 1;
/* 177 */           surfaceCheck[1] = -1;
/* 178 */           surfaceCheckDir = ForgeDirection.UP;
/* 181 */           gravityDir = 0;
/*     */         } 
/* 185 */         if (Math.abs(lookVec.field_72449_c) > Math.abs(lookVec.field_72450_a)) {
/* 186 */           if (lookVec.field_72449_c > 0.0D) {
/* 188 */             connectDir = 4;
/*     */           } else {
/* 191 */             connectDir = 5;
/*     */           } 
/*     */           break;
/*     */         } 
/* 194 */         if (lookVec.field_72450_a > 0.0D) {
/* 196 */           connectDir = 2;
/*     */         } else {
/* 199 */           connectDir = 3;
/*     */         } 
/*     */         break;
/*     */     } 
/* 206 */     if (connectDir == -1)
/* 206 */       return false; 
/* 209 */     int[] neighbourForHead = DirectionConst.CHECKNEIGHBOR_LIST[connectDir];
/* 211 */     int bodyX = par4 + bodyPlace[0];
/* 212 */     int bodyY = par5 + bodyPlace[1];
/* 213 */     int bodyZ = par6 + bodyPlace[2];
/* 215 */     int headX = bodyX + neighbourForHead[0];
/* 216 */     int headY = bodyY + neighbourForHead[1];
/* 217 */     int headZ = bodyZ + neighbourForHead[2];
/* 219 */     if (par2EntityPlayer.func_82247_a(bodyX, bodyY, bodyZ, par7, par1ItemStack) && par2EntityPlayer.func_82247_a(headX, headY, headZ, par7, par1ItemStack)) {
/* 222 */       if (par3World.func_147437_c(bodyX, bodyY, bodyZ) && par3World.func_147437_c(headX, headY, headZ) && par3World.isSideSolid(bodyX + surfaceCheck[0], bodyY + surfaceCheck[1], bodyZ + surfaceCheck[2], surfaceCheckDir) && par3World.isSideSolid(headX + surfaceCheck[0], headY + surfaceCheck[1], headZ + surfaceCheck[2], surfaceCheckDir)) {
/* 228 */         par3World.func_147465_d(bodyX, bodyY, bodyZ, SMModContainer.StarBedBodyBlock, connectDir, 3);
/* 230 */         if (par3World.func_147439_a(bodyX, bodyY, bodyZ) == SMModContainer.StarBedBodyBlock)
/* 232 */           par3World.func_147465_d(headX, headY, headZ, SMModContainer.StarBedHeadBlock, gravityDir, 3); 
/* 235 */         par1ItemStack.field_77994_a--;
/* 236 */         return true;
/*     */       } 
/* 240 */       return false;
/*     */     } 
/* 245 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v() {
/* 253 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getRenderPasses(int metadata) {
/* 258 */     return 2;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getIcon(ItemStack stack, int pass) {
/* 263 */     if (pass == 1)
/* 265 */       return ((ItemStarContoler)SMModContainer.StarControlerItem).starMarkIcon; 
/* 267 */     return super.getIcon(stack, pass);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */