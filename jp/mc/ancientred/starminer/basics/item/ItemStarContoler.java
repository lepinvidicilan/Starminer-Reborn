/*    */ package jp.mc.ancientred.starminer.basics.item;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.util.StatCollector;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemStarContoler extends Item {
/*    */   public IIcon starMarkIcon;
/*    */   
/*    */   public static final double DISTANCE_MAX = 64.0D;
/*    */   
/*    */   public static final String TARGETX_NBT_TAG = "stmn.tX";
/*    */   
/*    */   public static final String TARGETY_NBT_TAG = "stmn.tY";
/*    */   
/*    */   public static final String TARGETZ_NBT_TAG = "stmn.tZ";
/*    */   
/*    */   public ItemStarContoler() {
/* 28 */     func_111206_d("starminer:scontroler");
/* 29 */     func_77625_d(1);
/*    */   }
/*    */   
/*    */   public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 33 */     TileEntity te = world.func_147438_o(x, y, z);
/* 34 */     if (!world.field_72995_K && 
/* 35 */       te != null && te instanceof jp.mc.ancientred.starminer.basics.tileentity.TileEntityGravityGenerator) {
/* 36 */       NBTTagCompound nbttagcompound = itemStack.func_77978_p();
/* 37 */       if (nbttagcompound == null) {
/* 39 */         nbttagcompound = new NBTTagCompound();
/* 40 */         itemStack.func_77982_d(nbttagcompound);
/*    */       } 
/* 43 */       if (!nbttagcompound.func_74764_b("stmn.tX")) {
/* 44 */         nbttagcompound.func_74768_a("stmn.tX", x);
/* 45 */         nbttagcompound.func_74768_a("stmn.tY", y);
/* 46 */         nbttagcompound.func_74768_a("stmn.tZ", z);
/*    */       } 
/* 48 */       return true;
/*    */     } 
/* 51 */     return super.onItemUseFirst(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
/*    */   }
/*    */   
/*    */   public void func_77624_a(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
/* 56 */     NBTTagCompound nbttagcompound = itemStack.func_77978_p();
/* 57 */     if (nbttagcompound != null && nbttagcompound.func_74764_b("stmn.tX")) {
/* 59 */       list.add(StatCollector.func_74837_a("starInfo.sconInfoON", new Object[] { nbttagcompound.func_74762_e("stmn.tX"), nbttagcompound.func_74762_e("stmn.tY"), nbttagcompound.func_74762_e("stmn.tZ") }));
/*    */     } else {
/* 64 */       list.add(StatCollector.func_74838_a("starInfo.sconInfoOFF"));
/*    */     } 
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
/* 72 */     if (!world.field_72995_K) {
/* 73 */       NBTTagCompound nbttagcompound = itemStack.func_77978_p();
/* 74 */       if (nbttagcompound != null)
/* 76 */         if (nbttagcompound.func_74764_b("stmn.tX")) {
/* 77 */           int targetX = nbttagcompound.func_74762_e("stmn.tX");
/* 78 */           int targetY = nbttagcompound.func_74762_e("stmn.tY");
/* 79 */           int targetZ = nbttagcompound.func_74762_e("stmn.tZ");
/* 80 */           if (player.func_70011_f((double)targetX + 0.5D, (double)targetY + 0.5D, (double)targetZ + 0.5D) <= 64.0D)
/* 81 */             player.openGui(SMModContainer.instance, SMModContainer.guiStarCoreId, world, targetX, targetY, targetZ); 
/*    */         }  
/*    */     } 
/* 87 */     return itemStack;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_94581_a(IIconRegister par1IconRegister) {
/* 93 */     this.starMarkIcon = par1IconRegister.func_94245_a("starminer:starmark");
/* 94 */     super.func_94581_a(par1IconRegister);
/*    */   }
/*    */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */