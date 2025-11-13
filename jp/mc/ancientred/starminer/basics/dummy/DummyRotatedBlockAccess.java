/*     */ package jp.mc.ancientred.starminer.basics.dummy;
/*     */ 
/*     */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityBlockRotator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class DummyRotatedBlockAccess implements IBlockAccess {
/*     */   private IBlockAccess wrappedAccess;
/*     */   
/*     */   private GravityDirection gdir;
/*     */   
/*     */   private int centerX;
/*     */   
/*     */   private int centerY;
/*     */   
/*     */   private int centerZ;
/*     */   
/*  21 */   private int[] conv = new int[3];
/*     */   
/*     */   public static final DummyRotatedBlockAccess get() {
/*  28 */     return thHoldThis.get();
/*     */   }
/*     */   
/*  31 */   private static ThreadLocal<DummyRotatedBlockAccess> thHoldThis = new ThreadLocal<DummyRotatedBlockAccess>() {
/*     */       protected DummyRotatedBlockAccess initialValue() {
/*  33 */         return new DummyRotatedBlockAccess();
/*     */       }
/*     */     };
/*     */   
/*     */   public IBlockAccess wrapp(IBlockAccess world, GravityDirection gdir, int centerX, int centerY, int centerZ) {
/*  38 */     this.centerX = centerX;
/*  39 */     this.centerY = centerY;
/*  40 */     this.centerZ = centerZ;
/*  41 */     this.gdir = gdir;
/*  42 */     this.wrappedAccess = world;
/*  43 */     return this;
/*     */   }
/*     */   
/*     */   public Block func_147439_a(int parX, int parY, int parZ) {
/*  48 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  49 */     parX = rotated[0];
/*  49 */     parY = rotated[1];
/*  49 */     parZ = rotated[2];
/*  51 */     TileEntityBlockRotator te = getTileEntityBlockRotator(this.wrappedAccess, parX, parY, parZ);
/*  52 */     if (te != null) {
/*  53 */       Block storedBlock = te.getStoredBlock();
/*  54 */       if (storedBlock != null)
/*  54 */         return storedBlock; 
/*     */     } 
/*  56 */     return this.wrappedAccess.func_147439_a(parX, parY, parZ);
/*     */   }
/*     */   
/*     */   public TileEntity func_147438_o(int parX, int parY, int parZ) {
/*  61 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  62 */     parX = rotated[0];
/*  62 */     parY = rotated[1];
/*  62 */     parZ = rotated[2];
/*  64 */     return this.wrappedAccess.func_147438_o(parX, parY, parZ);
/*     */   }
/*     */   
/*     */   public int func_72802_i(int parX, int parY, int parZ, int p_72802_4_) {
/*  69 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  70 */     parX = rotated[0];
/*  70 */     parY = rotated[1];
/*  70 */     parZ = rotated[2];
/*  72 */     return this.wrappedAccess.func_72802_i(parX, parY, parZ, p_72802_4_);
/*     */   }
/*     */   
/*     */   public int func_72805_g(int parX, int parY, int parZ) {
/*  77 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  78 */     parX = rotated[0];
/*  78 */     parY = rotated[1];
/*  78 */     parZ = rotated[2];
/*  80 */     return this.wrappedAccess.func_72805_g(parX, parY, parZ);
/*     */   }
/*     */   
/*     */   public int func_72879_k(int parX, int parY, int parZ, int direction) {
/*  85 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  86 */     parX = rotated[0];
/*  86 */     parY = rotated[1];
/*  86 */     parZ = rotated[2];
/*  88 */     return this.wrappedAccess.func_72879_k(parX, parY, parZ, direction);
/*     */   }
/*     */   
/*     */   public boolean func_147437_c(int parX, int parY, int parZ) {
/*  93 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  94 */     parX = rotated[0];
/*  94 */     parY = rotated[1];
/*  94 */     parZ = rotated[2];
/*  96 */     return this.wrappedAccess.func_147437_c(parX, parY, parZ);
/*     */   }
/*     */   
/*     */   public BiomeGenBase func_72807_a(int p_72807_1_, int p_72807_2_) {
/* 101 */     return this.wrappedAccess.func_72807_a(p_72807_1_, p_72807_2_);
/*     */   }
/*     */   
/*     */   public int func_72800_K() {
/* 106 */     return this.wrappedAccess.func_72800_K();
/*     */   }
/*     */   
/*     */   public boolean func_72806_N() {
/* 111 */     return this.wrappedAccess.func_72806_N();
/*     */   }
/*     */   
/*     */   public boolean isSideSolid(int parX, int parY, int parZ, ForgeDirection side, boolean _default) {
/* 116 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/* 117 */     parX = rotated[0];
/* 117 */     parY = rotated[1];
/* 117 */     parZ = rotated[2];
/* 119 */     return this.wrappedAccess.isSideSolid(parX, parY, parZ, side, _default);
/*     */   }
/*     */   
/*     */   private TileEntityBlockRotator getTileEntityBlockRotator(IBlockAccess world, int par2, int par3, int par4) {
/* 122 */     TileEntity te = this.wrappedAccess.func_147438_o(par2, par3, par4);
/* 123 */     if (te instanceof TileEntityBlockRotator)
/* 124 */       return (TileEntityBlockRotator)te; 
/* 126 */     return null;
/*     */   }
/*     */   
/*     */   private DummyRotatedBlockAccess() {}
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */