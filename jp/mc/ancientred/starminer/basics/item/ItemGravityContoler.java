/*    */ package jp.mc.ancientred.starminer.basics.item;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemGravityContoler extends Item {
/*    */   public static final String G_REVERSE_NBT_TAG = "stmn.g_reverse";
/*    */   
/* 15 */   private IIcon[] itemIconPrivate = new IIcon[2];
/*    */   
/*    */   public ItemGravityContoler() {
/* 18 */     func_111206_d("dummy");
/* 19 */     func_77625_d(1);
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
/*    */     boolean gcon;
/* 27 */     NBTTagCompound nbttagcompound = itemStack.func_77978_p();
/* 29 */     if (nbttagcompound == null) {
/* 31 */       nbttagcompound = new NBTTagCompound();
/* 32 */       itemStack.func_77982_d(nbttagcompound);
/* 33 */       gcon = false;
/*    */     } else {
/* 35 */       gcon = nbttagcompound.func_74767_n("stmn.g_reverse");
/*    */     } 
/* 38 */     nbttagcompound.func_74757_a("stmn.g_reverse", !gcon);
/* 40 */     return itemStack;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon getIcon(ItemStack itemStack, int pass) {
/* 45 */     if (itemStack.func_77942_o()) {
/* 46 */       NBTTagCompound tag = itemStack.func_77978_p();
/* 47 */       boolean gcon = tag.func_74767_n("stmn.g_reverse");
/* 48 */       return gcon ? this.itemIconPrivate[1] : this.itemIconPrivate[0];
/*    */     } 
/* 50 */     return this.itemIconPrivate[0];
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 56 */     return this.itemIconPrivate[1];
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77650_f(ItemStack itemStack) {
/* 62 */     if (itemStack.func_77942_o()) {
/* 63 */       NBTTagCompound tag = itemStack.func_77978_p();
/* 64 */       boolean gcon = tag.func_74767_n("stmn.g_reverse");
/* 65 */       return gcon ? this.itemIconPrivate[1] : this.itemIconPrivate[0];
/*    */     } 
/* 67 */     return this.itemIconPrivate[0];
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister par1IconRegister) {
/* 73 */     this.itemIconPrivate[0] = par1IconRegister.func_94245_a("starminer:gcontroler_a");
/* 74 */     this.itemIconPrivate[1] = par1IconRegister.func_94245_a("starminer:gcontroler_b");
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */