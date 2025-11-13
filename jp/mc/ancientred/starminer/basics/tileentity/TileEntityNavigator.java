/*     */ package jp.mc.ancientred.starminer.basics.tileentity;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import jp.mc.ancientred.starminer.basics.block.BlockNavigator;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ public class TileEntityNavigator extends TileEntity {
/*     */   private static final String LX_NBT_KEY = "stmn.lx";
/*     */   
/*     */   private static final String LY_NBT_KEY = "stmn.ly";
/*     */   
/*     */   private static final String LZ_NBT_KEY = "stmn.lz";
/*     */   
/*     */   private static final String ACT_NBT_KEY = "stmn.actvc";
/*     */   
/*  27 */   public int activeTickCount = 0;
/*     */   
/*  29 */   public float lookX = 0.0F;
/*     */   
/*  30 */   public float lookY = 0.0F;
/*     */   
/*  31 */   public float lookZ = 0.0F;
/*     */   
/*     */   public void activate() {
/*  37 */     this.activeTickCount = 100;
/*     */   }
/*     */   
/*     */   public boolean isActive() {
/*  43 */     return (this.activeTickCount > 0);
/*     */   }
/*     */   
/*     */   public void func_145845_h() {
/*  48 */     if (!this.field_145850_b.field_72995_K) {
/*  49 */       if (this.activeTickCount > 0) {
/*  51 */         updateAngleByNearPlayer();
/*  54 */         this.activeTickCount--;
/*  55 */         if (this.activeTickCount <= 0 || this.field_145850_b.func_82737_E() % 10L == 0L)
/*  57 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e); 
/*     */       } 
/*  61 */     } else if (this.activeTickCount > 0) {
/*  63 */       this.activeTickCount--;
/*  66 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateAngleByNearPlayer() {
/*  74 */     AxisAlignedBB axisalignedbb = AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(2.0D, 2.0D, 2.0D);
/*  80 */     axisalignedbb.field_72337_e = (double)this.field_145850_b.func_72800_K();
/*  81 */     List list = this.field_145850_b.func_72872_a(EntityPlayer.class, axisalignedbb);
/*  82 */     Iterator<EntityPlayer> iterator = list.iterator();
/*  84 */     while (iterator.hasNext()) {
/*  85 */       EntityPlayer entityPlayer = iterator.next();
/*  88 */       if (entityPlayer.func_70608_bn())
/*     */         continue; 
/*  91 */       if (!BlockNavigator.doesPlayerHasTorchOnHand(entityPlayer))
/*     */         continue; 
/*  93 */       Vec3 vec3 = entityPlayer.func_70676_i(1.0F);
/*  94 */       this.lookX = (float)vec3.field_72450_a;
/*  95 */       this.lookY = (float)vec3.field_72448_b;
/*  96 */       this.lookZ = (float)vec3.field_72449_c;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
/* 102 */     super.func_145839_a(par1NBTTagCompound);
/* 103 */     this.lookX = par1NBTTagCompound.func_74760_g("stmn.lx");
/* 104 */     this.lookY = par1NBTTagCompound.func_74760_g("stmn.ly");
/* 105 */     this.lookZ = par1NBTTagCompound.func_74760_g("stmn.lz");
/* 106 */     this.activeTickCount = par1NBTTagCompound.func_74762_e("stmn.actvc");
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
/* 112 */     super.func_145841_b(par1NBTTagCompound);
/* 113 */     par1NBTTagCompound.func_74776_a("stmn.lx", this.lookX);
/* 114 */     par1NBTTagCompound.func_74776_a("stmn.ly", this.lookY);
/* 115 */     par1NBTTagCompound.func_74776_a("stmn.lz", this.lookZ);
/* 116 */     par1NBTTagCompound.func_74768_a("stmn.actvc", this.activeTickCount);
/*     */   }
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/* 120 */     NBTTagCompound tag = pkt.func_148857_g();
/* 121 */     func_145839_a(tag);
/*     */   }
/*     */   
/*     */   public Packet func_145844_m() {
/* 125 */     NBTTagCompound tag = new NBTTagCompound();
/* 126 */     func_145841_b(tag);
/* 127 */     return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145847_g, tag);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */