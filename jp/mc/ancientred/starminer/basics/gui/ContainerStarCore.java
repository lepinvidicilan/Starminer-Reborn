/*     */ package jp.mc.ancientred.starminer.basics.gui;
/*     */ 
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityGravityGenerator;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ContainerStarCore extends Container {
/*     */   public TileEntityGravityGenerator tileEntityGrav;
/*     */   
/*     */   public EntityPlayer entityplayer;
/*     */   
/*     */   private World world;
/*     */   
/*     */   private int posX;
/*     */   
/*     */   private int posY;
/*     */   
/*     */   private int posZ;
/*     */   
/*     */   public ContainerStarCore(EntityPlayer par1Player, TileEntityGravityGenerator par2IInventoryStarCore) {
/*  25 */     this.entityplayer = par1Player;
/*  26 */     InventoryPlayer par1InventoryPlayer = par1Player.field_71071_by;
/*  27 */     this.tileEntityGrav = par2IInventoryStarCore;
/*  28 */     this.world = par2IInventoryStarCore.func_145831_w();
/*  29 */     this.posX = par2IInventoryStarCore.field_145851_c;
/*  30 */     this.posY = par2IInventoryStarCore.field_145848_d;
/*  31 */     this.posZ = par2IInventoryStarCore.field_145849_e;
/*  33 */     int i = 37;
/*  37 */     for (int m = 0; m < 3; m++) {
/*  39 */       for (int n = 0; n < 9; n++)
/*  41 */         func_75146_a(new Slot((IInventory)par2IInventoryStarCore, n + m * 9, 8 + n * 18, 72 + m * 18)); 
/*     */     } 
/*  45 */     func_75146_a(new Slot((IInventory)par2IInventoryStarCore, 27, 142, 36));
/*  48 */     for (int k = 0; k < 3; k++) {
/*  50 */       for (int n = 0; n < 9; n++)
/*  52 */         func_75146_a(new Slot((IInventory)par1InventoryPlayer, n + k * 9 + 9, 8 + n * 18, 103 + k * 18 + i)); 
/*     */     } 
/*  56 */     for (int j = 0; j < 9; j++)
/*  58 */       func_75146_a(new Slot((IInventory)par1InventoryPlayer, j, 8 + j * 18, 161 + i)); 
/*     */   }
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
/*  65 */     if (this.world.func_147439_a(this.posX, this.posY, this.posZ) != SMModContainer.GravityCoreBlock)
/*  67 */       return false; 
/*  69 */     return (par1EntityPlayer.func_70011_f((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) < 64.0D);
/*     */   }
/*     */   
/*     */   public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
/*  74 */     return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
/*     */   }
/*     */   
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer) {
/*  80 */     super.func_75134_a(par1EntityPlayer);
/*     */   }
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
/*  86 */     ItemStack itemstack = null;
/*  87 */     Slot slot = this.field_75151_b.get(par2);
/*  89 */     if (slot != null && slot.func_75216_d()) {
/*  91 */       ItemStack itemstack1 = slot.func_75211_c();
/*  92 */       itemstack = itemstack1.func_77946_l();
/*  93 */       if (par2 < 28) {
/*  95 */         if (!func_75135_a(itemstack1, 54, this.field_75151_b.size(), true))
/*  97 */           return null; 
/* 100 */       } else if (!func_75135_a(itemstack1, 0, 27, true)) {
/* 102 */         return null;
/*     */       } 
/* 105 */       if (itemstack1.field_77994_a == 0) {
/* 107 */         slot.func_75215_d(null);
/*     */       } else {
/* 111 */         slot.func_75218_e();
/*     */       } 
/*     */     } 
/* 115 */     return itemstack;
/*     */   }
/*     */   
/*     */   public void receiveButtonAction(int ID) {
/* 118 */     if (!this.world.field_72995_K) {
/* 120 */       TileEntity te = this.world.func_147438_o(this.posX, this.posY, this.posZ);
/* 121 */       if (te == null)
/*     */         return; 
/* 122 */       if (this.tileEntityGrav == null)
/*     */         return; 
/* 123 */       if (te != this.tileEntityGrav)
/*     */         return; 
/* 124 */       if (!(te instanceof TileEntityGravityGenerator))
/*     */         return; 
/* 126 */       boolean resetWorkState = false;
/* 127 */       switch (ID) {
/*     */         case 1:
/* 129 */           this.tileEntityGrav.gravityRange++;
/*     */           break;
/*     */         case 2:
/* 132 */           this.tileEntityGrav.gravityRange--;
/*     */           break;
/*     */         case 3:
/* 135 */           this.tileEntityGrav.gravityRange += 5.0D;
/*     */           break;
/*     */         case 4:
/* 138 */           this.tileEntityGrav.gravityRange -= 5.0D;
/*     */           break;
/*     */         case 5:
/* 141 */           this.tileEntityGrav.starRad++;
/* 142 */           resetWorkState = true;
/*     */           break;
/*     */         case 6:
/* 145 */           this.tileEntityGrav.starRad--;
/* 146 */           resetWorkState = true;
/*     */           break;
/*     */         case 7:
/* 149 */           this.tileEntityGrav.starRad += 5.0D;
/* 150 */           resetWorkState = true;
/*     */           break;
/*     */         case 8:
/* 153 */           this.tileEntityGrav.starRad -= 5.0D;
/* 154 */           resetWorkState = true;
/*     */           break;
/*     */         case 9:
/* 157 */           this.tileEntityGrav.type++;
/* 158 */           this.tileEntityGrav.type %= 5;
/* 159 */           resetWorkState = true;
/*     */           break;
/*     */       } 
/* 163 */       this.tileEntityGrav.fixInValidRange();
/* 165 */       if (resetWorkState)
/* 167 */         this.tileEntityGrav.resetWorkState(); 
/* 170 */       this.world.func_147471_g(this.posX, this.posY, this.posZ);
/*     */     } 
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */