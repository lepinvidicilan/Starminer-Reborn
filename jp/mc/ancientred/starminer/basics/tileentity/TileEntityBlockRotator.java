/*     */ package jp.mc.ancientred.starminer.basics.tileentity;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ public class TileEntityBlockRotator extends TileEntity {
/*  32 */   private Block storedBlock = Blocks.field_150350_a;
/*     */   
/*  33 */   private Item storedItem = Items.field_151034_e;
/*     */   
/*  34 */   private GravityDirection gravityDirection = GravityDirection.upTOdown_YN;
/*     */   
/*  35 */   public boolean isSubBlock = false;
/*     */   
/*     */   private int itemMetadata;
/*     */   
/*     */   public int relatedBlockX;
/*     */   
/*     */   public int relatedBlockY;
/*     */   
/*     */   public int relatedBlockZ;
/*     */   
/*     */   public void setStoredBlock(Block block) {
/*  40 */     this.storedBlock = block;
/*     */   }
/*     */   
/*     */   public void setStoredItem(Item item) {
/*  45 */     this.storedItem = item;
/*     */   }
/*     */   
/*     */   public void setItemMetadata(int meta) {
/*  50 */     this.itemMetadata = meta;
/*     */   }
/*     */   
/*     */   public Block getStoredBlock() {
/*  55 */     return this.storedBlock;
/*     */   }
/*     */   
/*     */   public Item getStoredItem() {
/*  60 */     return this.storedItem;
/*     */   }
/*     */   
/*     */   public int getItemMetadata() {
/*  65 */     return this.itemMetadata;
/*     */   }
/*     */   
/*     */   public void setGravityDirection(GravityDirection gDir) {
/*  70 */     this.gravityDirection = gDir;
/*     */   }
/*     */   
/*     */   public GravityDirection getGravityDirection() {
/*  75 */     return this.gravityDirection;
/*     */   }
/*     */   
/*     */   public boolean hasRelated() {
/*  79 */     return (this.relatedBlockX != this.field_145851_c || this.relatedBlockY != this.field_145848_d || this.relatedBlockZ != this.field_145849_e);
/*     */   }
/*     */   
/*     */   public boolean canUpdate() {
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  90 */     return AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)(this.field_145848_d - 1), (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 2), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 2));
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound p_145839_1_) {
/*  95 */     super.func_145839_a(p_145839_1_);
/*  96 */     this.storedBlock = Block.func_149729_e(p_145839_1_.func_74762_e("blockId"));
/*  97 */     this.storedItem = Item.func_150899_d(p_145839_1_.func_74762_e("itemId"));
/*  98 */     this.itemMetadata = p_145839_1_.func_74762_e("itemMetadata");
/*  99 */     this.gravityDirection = GravityDirection.values()[p_145839_1_.func_74762_e("gDir")];
/* 101 */     this.relatedBlockX = p_145839_1_.func_74762_e("relatedBlockX");
/* 102 */     this.relatedBlockY = p_145839_1_.func_74762_e("relatedBlockY");
/* 103 */     this.relatedBlockZ = p_145839_1_.func_74762_e("relatedBlockZ");
/* 104 */     this.isSubBlock = p_145839_1_.func_74767_n("isSubBlock");
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound p_145841_1_) {
/* 109 */     super.func_145841_b(p_145841_1_);
/* 110 */     p_145841_1_.func_74768_a("blockId", Block.func_149682_b(this.storedBlock));
/* 111 */     p_145841_1_.func_74768_a("itemId", Item.func_150891_b(this.storedItem));
/* 112 */     p_145841_1_.func_74768_a("itemMetadata", this.itemMetadata);
/* 113 */     p_145841_1_.func_74768_a("gDir", this.gravityDirection.ordinal());
/* 115 */     p_145841_1_.func_74768_a("relatedBlockX", this.relatedBlockX);
/* 116 */     p_145841_1_.func_74768_a("relatedBlockY", this.relatedBlockY);
/* 117 */     p_145841_1_.func_74768_a("relatedBlockZ", this.relatedBlockZ);
/* 118 */     p_145841_1_.func_74757_a("isSubBlock", this.isSubBlock);
/*     */   }
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/* 122 */     NBTTagCompound tag = pkt.func_148857_g();
/* 128 */     NBTTagCompound tagOld = new NBTTagCompound();
/* 129 */     func_145841_b(tagOld);
/* 131 */     func_145839_a(tag);
/* 133 */     if (this.storedBlock != null && this.storedBlock.func_149750_m() > 1)
/* 134 */       this.field_145850_b.func_147451_t(this.field_145851_c, this.field_145848_d, this.field_145849_e); 
/* 136 */     if (!tagOld.equals(tag))
/* 137 */       this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e); 
/*     */   }
/*     */   
/*     */   public Packet func_145844_m() {
/* 142 */     NBTTagCompound tag = new NBTTagCompound();
/* 143 */     func_145841_b(tag);
/* 144 */     return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145847_g, tag);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */