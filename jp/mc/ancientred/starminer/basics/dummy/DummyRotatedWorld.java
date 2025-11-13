/*      */ package jp.mc.ancientred.starminer.basics.dummy;
/*      */ 
/*      */ import com.google.common.collect.ImmutableSetMultimap;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collection;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import jp.mc.ancientred.starminer.api.GravityDirection;
/*      */ import jp.mc.ancientred.starminer.basics.SMReflectionHelper;
/*      */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityBlockRotator;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.command.IEntitySelector;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EnumCreatureType;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.pathfinding.PathEntity;
/*      */ import net.minecraft.scoreboard.Scoreboard;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.ChunkCoordinates;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.ChunkCoordIntPair;
/*      */ import net.minecraft.world.ChunkPosition;
/*      */ import net.minecraft.world.EnumSkyBlock;
/*      */ import net.minecraft.world.Explosion;
/*      */ import net.minecraft.world.GameRules;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ import net.minecraft.world.IWorldAccess;
/*      */ import net.minecraft.world.MinecraftException;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldSavedData;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import net.minecraft.world.biome.WorldChunkManager;
/*      */ import net.minecraft.world.chunk.Chunk;
/*      */ import net.minecraft.world.chunk.IChunkProvider;
/*      */ import net.minecraft.world.storage.ISaveHandler;
/*      */ import net.minecraft.world.storage.WorldInfo;
/*      */ import net.minecraftforge.common.ForgeChunkManager;
/*      */ import net.minecraftforge.common.util.ForgeDirection;
/*      */ 
/*      */ public class DummyRotatedWorld extends World {
/*      */   private World wrapped;
/*      */   
/*      */   private GravityDirection gdir;
/*      */   
/*      */   private int centerX;
/*      */   
/*      */   private int centerY;
/*      */   
/*      */   private int centerZ;
/*      */   
/*   59 */   private int[] conv = new int[3];
/*      */   
/*      */   private DummyRotatedWorld() {
/*   62 */     super(null, null, null, null, null);
/*      */   }
/*      */   
/*      */   public static final DummyRotatedWorld get() {
/*   68 */     return thHoldThis.get();
/*      */   }
/*      */   
/*   71 */   private static ThreadLocal<DummyRotatedWorld> thHoldThis = new ThreadLocal<DummyRotatedWorld>() {
/*      */       protected DummyRotatedWorld initialValue() {
/*   73 */         return new DummyRotatedWorld();
/*      */       }
/*      */     };
/*      */   
/*      */   public World wrapp(World world, GravityDirection gdir, int centerX, int centerY, int centerZ) {
/*   80 */     this.centerX = centerX;
/*   81 */     this.centerY = centerY;
/*   82 */     this.centerZ = centerZ;
/*   83 */     this.gdir = gdir;
/*   84 */     this.wrapped = world;
/*   87 */     this.field_72999_e = world.field_72999_e;
/*   88 */     this.field_72996_f = world.field_72996_f;
/*   89 */     this.field_147482_g = world.field_147482_g;
/*   90 */     this.field_73010_i = world.field_73010_i;
/*   91 */     this.field_73007_j = world.field_73007_j;
/*   92 */     this.field_73008_k = world.field_73008_k;
/*   93 */     this.field_73003_n = world.field_73003_n;
/*   94 */     this.field_73004_o = world.field_73004_o;
/*   95 */     this.field_73018_p = world.field_73018_p;
/*   96 */     this.field_73017_q = world.field_73017_q;
/*   97 */     this.field_73016_r = world.field_73016_r;
/*   98 */     this.field_73013_u = world.field_73013_u;
/*   99 */     this.field_73012_v = world.field_73012_v;
/*  100 */     this.field_72987_B = world.field_72987_B;
/*  101 */     this.field_72988_C = world.field_72988_C;
/*  102 */     this.field_72982_D = world.field_72982_D;
/*  103 */     this.field_72995_K = world.field_72995_K;
/*  104 */     this.restoringBlockSnapshots = world.restoringBlockSnapshots;
/*  105 */     this.captureBlockSnapshots = world.captureBlockSnapshots;
/*  106 */     this.capturedBlockSnapshots = world.capturedBlockSnapshots;
/*  108 */     SMReflectionHelper.setWrappedWorldFinalField(this, world.field_73011_w, world.field_72984_F);
/*  110 */     return this;
/*      */   }
/*      */   
/*      */   private TileEntityBlockRotator getTileEntityBlockRotator(IBlockAccess world, int par2, int par3, int par4) {
/*  114 */     TileEntity te = this.wrapped.func_147438_o(par2, par3, par4);
/*  115 */     if (te instanceof TileEntityBlockRotator)
/*  116 */       return (TileEntityBlockRotator)te; 
/*  118 */     return null;
/*      */   }
/*      */   
/*      */   public int[] rotateOnCurrentState(int parX, int parY, int parZ) {
/*  122 */     return this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*      */   }
/*      */   
/*      */   public BiomeGenBase func_72807_a(int p_72807_1_, int p_72807_2_) {
/*  128 */     return this.wrapped.func_72807_a(p_72807_1_, p_72807_2_);
/*      */   }
/*      */   
/*      */   public BiomeGenBase getBiomeGenForCoordsBody(int p_72807_1_, int p_72807_2_) {
/*  132 */     return this.wrapped.getBiomeGenForCoordsBody(p_72807_1_, p_72807_2_);
/*      */   }
/*      */   
/*      */   public WorldChunkManager func_72959_q() {
/*  136 */     return this.wrapped.func_72959_q();
/*      */   }
/*      */   
/*      */   public void func_72974_f() {
/*  140 */     this.wrapped.func_72974_f();
/*      */   }
/*      */   
/*      */   public boolean func_72873_a(int p_72873_1_, int p_72873_2_, int p_72873_3_, int p_72873_4_) {
/*  144 */     return this.wrapped.func_72873_a(p_72873_1_, p_72873_2_, p_72873_3_, p_72873_4_);
/*      */   }
/*      */   
/*      */   public boolean func_72904_c(int p_72904_1_, int p_72904_2_, int p_72904_3_, int p_72904_4_, int p_72904_5_, int p_72904_6_) {
/*  148 */     return this.wrapped.func_72904_c(p_72904_1_, p_72904_2_, p_72904_3_, p_72904_4_, p_72904_5_, p_72904_6_);
/*      */   }
/*      */   
/*      */   public Chunk func_72938_d(int p_72938_1_, int p_72938_2_) {
/*  152 */     return this.wrapped.func_72938_d(p_72938_1_, p_72938_2_);
/*      */   }
/*      */   
/*      */   public Chunk func_72964_e(int p_72964_1_, int p_72964_2_) {
/*  156 */     return this.wrapped.func_72964_e(p_72964_1_, p_72964_2_);
/*      */   }
/*      */   
/*      */   public void func_147479_m(int p_147479_1_, int p_147479_2_, int p_147479_3_) {
/*  161 */     this.wrapped.func_147479_m(p_147479_1_, p_147479_2_, p_147479_3_);
/*      */   }
/*      */   
/*      */   public boolean func_72935_r() {
/*  165 */     return this.wrapped.func_72935_r();
/*      */   }
/*      */   
/*      */   public MovingObjectPosition func_72933_a(Vec3 p_72933_1_, Vec3 p_72933_2_) {
/*  169 */     return this.wrapped.func_72933_a(p_72933_1_, p_72933_2_);
/*      */   }
/*      */   
/*      */   public MovingObjectPosition func_72901_a(Vec3 p_72901_1_, Vec3 p_72901_2_, boolean p_72901_3_) {
/*  173 */     return this.wrapped.func_72901_a(p_72901_1_, p_72901_2_, p_72901_3_);
/*      */   }
/*      */   
/*      */   public MovingObjectPosition func_147447_a(Vec3 p_147447_1_, Vec3 p_147447_2_, boolean p_147447_3_, boolean p_147447_4_, boolean p_147447_5_) {
/*  177 */     return this.wrapped.func_147447_a(p_147447_1_, p_147447_2_, p_147447_3_, p_147447_4_, p_147447_5_);
/*      */   }
/*      */   
/*      */   public void func_72956_a(Entity p_72956_1_, String p_72956_2_, float p_72956_3_, float p_72956_4_) {
/*  181 */     this.wrapped.func_72956_a(p_72956_1_, p_72956_2_, p_72956_3_, p_72956_4_);
/*      */   }
/*      */   
/*      */   public void func_85173_a(EntityPlayer p_85173_1_, String p_85173_2_, float p_85173_3_, float p_85173_4_) {
/*  185 */     this.wrapped.func_85173_a(p_85173_1_, p_85173_2_, p_85173_3_, p_85173_4_);
/*      */   }
/*      */   
/*      */   public void func_72908_a(double p_72908_1_, double p_72908_3_, double p_72908_5_, String p_72908_7_, float p_72908_8_, float p_72908_9_) {
/*  189 */     this.wrapped.func_72908_a(p_72908_1_, p_72908_3_, p_72908_5_, p_72908_7_, p_72908_8_, p_72908_9_);
/*      */   }
/*      */   
/*      */   public void func_72980_b(double p_72980_1_, double p_72980_3_, double p_72980_5_, String p_72980_7_, float p_72980_8_, float p_72980_9_, boolean p_72980_10_) {
/*  193 */     this.wrapped.func_72980_b(p_72980_1_, p_72980_3_, p_72980_5_, p_72980_7_, p_72980_8_, p_72980_9_, p_72980_10_);
/*      */   }
/*      */   
/*      */   public void func_72934_a(String p_72934_1_, int p_72934_2_, int p_72934_3_, int p_72934_4_) {
/*  197 */     this.wrapped.func_72934_a(p_72934_1_, p_72934_2_, p_72934_3_, p_72934_4_);
/*      */   }
/*      */   
/*      */   public void func_72869_a(String p_72869_1_, double p_72869_2_, double p_72869_4_, double p_72869_6_, double p_72869_8_, double p_72869_10_, double p_72869_12_) {
/*  201 */     this.wrapped.func_72869_a(p_72869_1_, p_72869_2_, p_72869_4_, p_72869_6_, p_72869_8_, p_72869_10_, p_72869_12_);
/*      */   }
/*      */   
/*      */   public void func_147446_b(int p_147446_1_, int p_147446_2_, int p_147446_3_, Block p_147446_4_, int p_147446_5_, int p_147446_6_) {
/*  206 */     this.wrapped.func_147446_b(p_147446_1_, p_147446_2_, p_147446_3_, p_147446_4_, p_147446_5_, p_147446_6_);
/*      */   }
/*      */   
/*      */   public boolean func_72942_c(Entity p_72942_1_) {
/*  211 */     return this.wrapped.func_72942_c(p_72942_1_);
/*      */   }
/*      */   
/*      */   public boolean func_72838_d(Entity p_72838_1_) {
/*  215 */     return this.wrapped.func_72838_d(p_72838_1_);
/*      */   }
/*      */   
/*      */   public void func_72923_a(Entity p_72923_1_) {
/*  219 */     this.wrapped.func_72923_a(p_72923_1_);
/*      */   }
/*      */   
/*      */   public void func_72847_b(Entity p_72847_1_) {
/*  223 */     this.wrapped.func_72847_b(p_72847_1_);
/*      */   }
/*      */   
/*      */   public void func_72900_e(Entity p_72900_1_) {
/*  227 */     this.wrapped.func_72900_e(p_72900_1_);
/*      */   }
/*      */   
/*      */   public void func_72973_f(Entity p_72973_1_) {
/*  231 */     this.wrapped.func_72973_f(p_72973_1_);
/*      */   }
/*      */   
/*      */   public void func_72954_a(IWorldAccess p_72954_1_) {
/*  235 */     this.wrapped.func_72954_a(p_72954_1_);
/*      */   }
/*      */   
/*      */   public List func_72945_a(Entity p_72945_1_, AxisAlignedBB p_72945_2_) {
/*  239 */     return this.wrapped.func_72945_a(p_72945_1_, p_72945_2_);
/*      */   }
/*      */   
/*      */   public List func_147461_a(AxisAlignedBB p_147461_1_) {
/*  243 */     return this.wrapped.func_147461_a(p_147461_1_);
/*      */   }
/*      */   
/*      */   public int func_72967_a(float p_72967_1_) {
/*  247 */     return this.wrapped.func_72967_a(p_72967_1_);
/*      */   }
/*      */   
/*      */   public float getSunBrightnessFactor(float p_72967_1_) {
/*  251 */     return this.wrapped.getSunBrightnessFactor(p_72967_1_);
/*      */   }
/*      */   
/*      */   public void func_72848_b(IWorldAccess p_72848_1_) {
/*  255 */     this.wrapped.func_72848_b(p_72848_1_);
/*      */   }
/*      */   
/*      */   public float func_72971_b(float p_72971_1_) {
/*  259 */     return this.wrapped.func_72971_b(p_72971_1_);
/*      */   }
/*      */   
/*      */   public float getSunBrightnessBody(float p_72971_1_) {
/*  263 */     return this.wrapped.getSunBrightnessBody(p_72971_1_);
/*      */   }
/*      */   
/*      */   public Vec3 func_72833_a(Entity p_72833_1_, float p_72833_2_) {
/*  267 */     return this.wrapped.func_72833_a(p_72833_1_, p_72833_2_);
/*      */   }
/*      */   
/*      */   public Vec3 getSkyColorBody(Entity p_72833_1_, float p_72833_2_) {
/*  271 */     return this.wrapped.getSkyColorBody(p_72833_1_, p_72833_2_);
/*      */   }
/*      */   
/*      */   public float func_72826_c(float p_72826_1_) {
/*  275 */     return this.wrapped.func_72826_c(p_72826_1_);
/*      */   }
/*      */   
/*      */   public int func_72853_d() {
/*  279 */     return this.wrapped.func_72853_d();
/*      */   }
/*      */   
/*      */   public float func_130001_d() {
/*  283 */     return this.wrapped.func_130001_d();
/*      */   }
/*      */   
/*      */   public float getCurrentMoonPhaseFactorBody() {
/*  287 */     return this.wrapped.getCurrentMoonPhaseFactorBody();
/*      */   }
/*      */   
/*      */   public float func_72929_e(float p_72929_1_) {
/*  291 */     return this.wrapped.func_72929_e(p_72929_1_);
/*      */   }
/*      */   
/*      */   public Vec3 func_72824_f(float p_72824_1_) {
/*  295 */     return this.wrapped.func_72824_f(p_72824_1_);
/*      */   }
/*      */   
/*      */   public Vec3 drawCloudsBody(float p_72824_1_) {
/*  299 */     return this.wrapped.drawCloudsBody(p_72824_1_);
/*      */   }
/*      */   
/*      */   public Vec3 func_72948_g(float p_72948_1_) {
/*  303 */     return this.wrapped.func_72948_g(p_72948_1_);
/*      */   }
/*      */   
/*      */   public int func_72874_g(int p_72874_1_, int p_72874_2_) {
/*  307 */     return this.wrapped.func_72874_g(p_72874_1_, p_72874_2_);
/*      */   }
/*      */   
/*      */   public int func_72825_h(int p_72825_1_, int p_72825_2_) {
/*  311 */     return this.wrapped.func_72825_h(p_72825_1_, p_72825_2_);
/*      */   }
/*      */   
/*      */   public float func_72880_h(float p_72880_1_) {
/*  315 */     return this.wrapped.func_72880_h(p_72880_1_);
/*      */   }
/*      */   
/*      */   public float getStarBrightnessBody(float par1) {
/*  319 */     return this.wrapped.getStarBrightnessBody(par1);
/*      */   }
/*      */   
/*      */   public void func_72939_s() {
/*  323 */     this.wrapped.func_72939_s();
/*      */   }
/*      */   
/*      */   public void func_147448_a(Collection p_147448_1_) {
/*  327 */     this.wrapped.func_147448_a(p_147448_1_);
/*      */   }
/*      */   
/*      */   public void func_72870_g(Entity p_72870_1_) {
/*  331 */     this.wrapped.func_72870_g(p_72870_1_);
/*      */   }
/*      */   
/*      */   public void func_72866_a(Entity p_72866_1_, boolean p_72866_2_) {
/*  335 */     this.wrapped.func_72866_a(p_72866_1_, p_72866_2_);
/*      */   }
/*      */   
/*      */   public boolean func_72855_b(AxisAlignedBB p_72855_1_) {
/*  339 */     return this.wrapped.func_72855_b(p_72855_1_);
/*      */   }
/*      */   
/*      */   public boolean func_72917_a(AxisAlignedBB p_72917_1_, Entity p_72917_2_) {
/*  343 */     return this.wrapped.func_72917_a(p_72917_1_, p_72917_2_);
/*      */   }
/*      */   
/*      */   public boolean func_72829_c(AxisAlignedBB p_72829_1_) {
/*  347 */     return this.wrapped.func_72829_c(p_72829_1_);
/*      */   }
/*      */   
/*      */   public boolean func_72953_d(AxisAlignedBB p_72953_1_) {
/*  351 */     return this.wrapped.func_72953_d(p_72953_1_);
/*      */   }
/*      */   
/*      */   public boolean func_147470_e(AxisAlignedBB p_147470_1_) {
/*  355 */     return this.wrapped.func_147470_e(p_147470_1_);
/*      */   }
/*      */   
/*      */   public boolean func_72918_a(AxisAlignedBB p_72918_1_, Material p_72918_2_, Entity p_72918_3_) {
/*  359 */     return this.wrapped.func_72918_a(p_72918_1_, p_72918_2_, p_72918_3_);
/*      */   }
/*      */   
/*      */   public boolean func_72875_a(AxisAlignedBB p_72875_1_, Material p_72875_2_) {
/*  363 */     return this.wrapped.func_72875_a(p_72875_1_, p_72875_2_);
/*      */   }
/*      */   
/*      */   public boolean func_72830_b(AxisAlignedBB p_72830_1_, Material p_72830_2_) {
/*  367 */     return this.wrapped.func_72830_b(p_72830_1_, p_72830_2_);
/*      */   }
/*      */   
/*      */   public Explosion func_72876_a(Entity p_72876_1_, double p_72876_2_, double p_72876_4_, double p_72876_6_, float p_72876_8_, boolean p_72876_9_) {
/*  371 */     return this.wrapped.func_72876_a(p_72876_1_, p_72876_2_, p_72876_4_, p_72876_6_, p_72876_8_, p_72876_9_);
/*      */   }
/*      */   
/*      */   public Explosion func_72885_a(Entity p_72885_1_, double p_72885_2_, double p_72885_4_, double p_72885_6_, float p_72885_8_, boolean p_72885_9_, boolean p_72885_10_) {
/*  376 */     return this.wrapped.func_72885_a(p_72885_1_, p_72885_2_, p_72885_4_, p_72885_6_, p_72885_8_, p_72885_9_, p_72885_10_);
/*      */   }
/*      */   
/*      */   public float func_72842_a(Vec3 p_72842_1_, AxisAlignedBB p_72842_2_) {
/*  380 */     return this.wrapped.func_72842_a(p_72842_1_, p_72842_2_);
/*      */   }
/*      */   
/*      */   public boolean func_72886_a(EntityPlayer p_72886_1_, int p_72886_2_, int p_72886_3_, int p_72886_4_, int p_72886_5_) {
/*  384 */     return this.wrapped.func_72886_a(p_72886_1_, p_72886_2_, p_72886_3_, p_72886_4_, p_72886_5_);
/*      */   }
/*      */   
/*      */   public String func_72981_t() {
/*  388 */     return this.wrapped.func_72981_t();
/*      */   }
/*      */   
/*      */   public String func_72827_u() {
/*  392 */     return this.wrapped.func_72827_u();
/*      */   }
/*      */   
/*      */   public void func_147455_a(int p_147455_1_, int p_147455_2_, int p_147455_3_, TileEntity p_147455_4_) {
/*  397 */     this.wrapped.func_147455_a(p_147455_1_, p_147455_2_, p_147455_3_, p_147455_4_);
/*      */   }
/*      */   
/*      */   public void func_147475_p(int p_147475_1_, int p_147475_2_, int p_147475_3_) {
/*  402 */     this.wrapped.func_147475_p(p_147475_1_, p_147475_2_, p_147475_3_);
/*      */   }
/*      */   
/*      */   public void func_147457_a(TileEntity p_147457_1_) {
/*  406 */     this.wrapped.func_147457_a(p_147457_1_);
/*      */   }
/*      */   
/*      */   public boolean func_147469_q(int p_147469_1_, int p_147469_2_, int p_147469_3_) {
/*  410 */     return this.wrapped.func_147469_q(p_147469_1_, p_147469_2_, p_147469_3_);
/*      */   }
/*      */   
/*      */   public void func_72966_v() {
/*  414 */     this.wrapped.func_72966_v();
/*      */   }
/*      */   
/*      */   public void func_72891_a(boolean p_72891_1_, boolean p_72891_2_) {
/*  418 */     this.wrapped.func_72891_a(p_72891_1_, p_72891_2_);
/*      */   }
/*      */   
/*      */   public void func_72835_b() {
/*  422 */     this.wrapped.func_72835_b();
/*      */   }
/*      */   
/*      */   public void calculateInitialWeatherBody() {
/*  426 */     this.wrapped.calculateInitialWeatherBody();
/*      */   }
/*      */   
/*      */   public void updateWeatherBody() {
/*  430 */     this.wrapped.updateWeatherBody();
/*      */   }
/*      */   
/*      */   public boolean func_147451_t(int p_147451_1_, int p_147451_2_, int p_147451_3_) {
/*  434 */     return this.wrapped.func_147451_t(p_147451_1_, p_147451_2_, p_147451_3_);
/*      */   }
/*      */   
/*      */   public boolean func_147463_c(EnumSkyBlock p_147463_1_, int p_147463_2_, int p_147463_3_, int p_147463_4_) {
/*  438 */     return this.wrapped.func_147463_c(p_147463_1_, p_147463_2_, p_147463_3_, p_147463_4_);
/*      */   }
/*      */   
/*      */   public boolean func_72955_a(boolean p_72955_1_) {
/*  442 */     return this.wrapped.func_72955_a(p_72955_1_);
/*      */   }
/*      */   
/*      */   public List func_72920_a(Chunk p_72920_1_, boolean p_72920_2_) {
/*  446 */     return this.wrapped.func_72920_a(p_72920_1_, p_72920_2_);
/*      */   }
/*      */   
/*      */   public List func_72839_b(Entity p_72839_1_, AxisAlignedBB p_72839_2_) {
/*  451 */     return this.wrapped.func_72839_b(p_72839_1_, p_72839_2_);
/*      */   }
/*      */   
/*      */   public List func_94576_a(Entity p_94576_1_, AxisAlignedBB p_94576_2_, IEntitySelector p_94576_3_) {
/*  455 */     return this.wrapped.func_94576_a(p_94576_1_, p_94576_2_, p_94576_3_);
/*      */   }
/*      */   
/*      */   public List func_72872_a(Class p_72872_1_, AxisAlignedBB p_72872_2_) {
/*  460 */     return this.wrapped.func_72872_a(p_72872_1_, p_72872_2_);
/*      */   }
/*      */   
/*      */   public List func_82733_a(Class p_82733_1_, AxisAlignedBB p_82733_2_, IEntitySelector p_82733_3_) {
/*  464 */     return this.wrapped.func_82733_a(p_82733_1_, p_82733_2_, p_82733_3_);
/*      */   }
/*      */   
/*      */   public Entity func_72857_a(Class p_72857_1_, AxisAlignedBB p_72857_2_, Entity p_72857_3_) {
/*  468 */     return this.wrapped.func_72857_a(p_72857_1_, p_72857_2_, p_72857_3_);
/*      */   }
/*      */   
/*      */   public List func_72910_y() {
/*  472 */     return this.wrapped.func_72910_y();
/*      */   }
/*      */   
/*      */   public void func_147476_b(int p_147476_1_, int p_147476_2_, int p_147476_3_, TileEntity p_147476_4_) {
/*  476 */     this.wrapped.func_147476_b(p_147476_1_, p_147476_2_, p_147476_3_, p_147476_4_);
/*      */   }
/*      */   
/*      */   public int func_72907_a(Class p_72907_1_) {
/*  480 */     return this.wrapped.func_72907_a(p_72907_1_);
/*      */   }
/*      */   
/*      */   public void func_72868_a(List p_72868_1_) {
/*  484 */     this.wrapped.func_72868_a(p_72868_1_);
/*      */   }
/*      */   
/*      */   public void func_72828_b(List p_72828_1_) {
/*  488 */     this.wrapped.func_72828_b(p_72828_1_);
/*      */   }
/*      */   
/*      */   public boolean func_147472_a(Block p_147472_1_, int p_147472_2_, int p_147472_3_, int p_147472_4_, boolean p_147472_5_, int p_147472_6_, Entity p_147472_7_, ItemStack p_147472_8_) {
/*  492 */     return this.wrapped.func_147472_a(p_147472_1_, p_147472_2_, p_147472_3_, p_147472_4_, p_147472_5_, p_147472_6_, p_147472_7_, p_147472_8_);
/*      */   }
/*      */   
/*      */   public PathEntity func_72865_a(Entity p_72865_1_, Entity p_72865_2_, float p_72865_3_, boolean p_72865_4_, boolean p_72865_5_, boolean p_72865_6_, boolean p_72865_7_) {
/*  496 */     return this.wrapped.func_72865_a(p_72865_1_, p_72865_2_, p_72865_3_, p_72865_4_, p_72865_5_, p_72865_6_, p_72865_7_);
/*      */   }
/*      */   
/*      */   public PathEntity func_72844_a(Entity p_72844_1_, int p_72844_2_, int p_72844_3_, int p_72844_4_, float p_72844_5_, boolean p_72844_6_, boolean p_72844_7_, boolean p_72844_8_, boolean p_72844_9_) {
/*  500 */     return this.wrapped.func_72844_a(p_72844_1_, p_72844_2_, p_72844_3_, p_72844_4_, p_72844_5_, p_72844_6_, p_72844_7_, p_72844_8_, p_72844_9_);
/*      */   }
/*      */   
/*      */   public EntityPlayer func_72890_a(Entity p_72890_1_, double p_72890_2_) {
/*  505 */     return this.wrapped.func_72890_a(p_72890_1_, p_72890_2_);
/*      */   }
/*      */   
/*      */   public EntityPlayer func_72977_a(double p_72977_1_, double p_72977_3_, double p_72977_5_, double p_72977_7_) {
/*  509 */     return this.wrapped.func_72977_a(p_72977_1_, p_72977_3_, p_72977_5_, p_72977_7_);
/*      */   }
/*      */   
/*      */   public EntityPlayer func_72856_b(Entity p_72856_1_, double p_72856_2_) {
/*  513 */     return this.wrapped.func_72856_b(p_72856_1_, p_72856_2_);
/*      */   }
/*      */   
/*      */   public EntityPlayer func_72846_b(double p_72846_1_, double p_72846_3_, double p_72846_5_, double p_72846_7_) {
/*  517 */     return this.wrapped.func_72846_b(p_72846_1_, p_72846_3_, p_72846_5_, p_72846_7_);
/*      */   }
/*      */   
/*      */   public EntityPlayer func_72924_a(String p_72924_1_) {
/*  521 */     return this.wrapped.func_72924_a(p_72924_1_);
/*      */   }
/*      */   
/*      */   public EntityPlayer func_152378_a(UUID p_152378_1_) {
/*  525 */     return this.wrapped.func_152378_a(p_152378_1_);
/*      */   }
/*      */   
/*      */   public void func_72882_A() {
/*  529 */     this.wrapped.func_72882_A();
/*      */   }
/*      */   
/*      */   public void func_72906_B() throws MinecraftException {
/*  533 */     this.wrapped.func_72906_B();
/*      */   }
/*      */   
/*      */   public void func_82738_a(long p_82738_1_) {
/*  537 */     this.wrapped.func_82738_a(p_82738_1_);
/*      */   }
/*      */   
/*      */   public long func_72905_C() {
/*  541 */     return this.wrapped.func_72905_C();
/*      */   }
/*      */   
/*      */   public long func_82737_E() {
/*  545 */     return this.wrapped.func_82737_E();
/*      */   }
/*      */   
/*      */   public long func_72820_D() {
/*  549 */     return this.wrapped.func_72820_D();
/*      */   }
/*      */   
/*      */   public void func_72877_b(long p_72877_1_) {
/*  553 */     this.wrapped.func_72877_b(p_72877_1_);
/*      */   }
/*      */   
/*      */   public ChunkCoordinates func_72861_E() {
/*  557 */     return this.wrapped.func_72861_E();
/*      */   }
/*      */   
/*      */   public void func_72950_A(int p_72950_1_, int p_72950_2_, int p_72950_3_) {
/*  561 */     this.wrapped.func_72950_A(p_72950_1_, p_72950_2_, p_72950_3_);
/*      */   }
/*      */   
/*      */   public void func_72897_h(Entity p_72897_1_) {
/*  565 */     this.wrapped.func_72897_h(p_72897_1_);
/*      */   }
/*      */   
/*      */   public void func_72960_a(Entity p_72960_1_, byte p_72960_2_) {
/*  569 */     this.wrapped.func_72960_a(p_72960_1_, p_72960_2_);
/*      */   }
/*      */   
/*      */   public IChunkProvider func_72863_F() {
/*  573 */     return this.wrapped.func_72863_F();
/*      */   }
/*      */   
/*      */   public void func_147452_c(int p_147452_1_, int p_147452_2_, int p_147452_3_, Block p_147452_4_, int p_147452_5_, int p_147452_6_) {
/*  577 */     this.wrapped.func_147452_c(p_147452_1_, p_147452_2_, p_147452_3_, p_147452_4_, p_147452_5_, p_147452_6_);
/*      */   }
/*      */   
/*      */   public ISaveHandler func_72860_G() {
/*  581 */     return this.wrapped.func_72860_G();
/*      */   }
/*      */   
/*      */   public WorldInfo func_72912_H() {
/*  585 */     return this.wrapped.func_72912_H();
/*      */   }
/*      */   
/*      */   public GameRules func_82736_K() {
/*  589 */     return this.wrapped.func_82736_K();
/*      */   }
/*      */   
/*      */   public void func_72854_c() {
/*  593 */     this.wrapped.func_72854_c();
/*      */   }
/*      */   
/*      */   public float func_72819_i(float p_72819_1_) {
/*  597 */     return this.wrapped.func_72819_i(p_72819_1_);
/*      */   }
/*      */   
/*      */   public void func_147442_i(float p_147442_1_) {
/*  601 */     this.wrapped.func_147442_i(p_147442_1_);
/*      */   }
/*      */   
/*      */   public float func_72867_j(float p_72867_1_) {
/*  605 */     return this.wrapped.func_72867_j(p_72867_1_);
/*      */   }
/*      */   
/*      */   public void func_72894_k(float p_72894_1_) {
/*  609 */     this.wrapped.func_72894_k(p_72894_1_);
/*      */   }
/*      */   
/*      */   public boolean func_72911_I() {
/*  613 */     return this.wrapped.func_72911_I();
/*      */   }
/*      */   
/*      */   public boolean func_72896_J() {
/*  617 */     return this.wrapped.func_72896_J();
/*      */   }
/*      */   
/*      */   public void func_72823_a(String p_72823_1_, WorldSavedData p_72823_2_) {
/*  621 */     this.wrapped.func_72823_a(p_72823_1_, p_72823_2_);
/*      */   }
/*      */   
/*      */   public WorldSavedData func_72943_a(Class p_72943_1_, String p_72943_2_) {
/*  625 */     return this.wrapped.func_72943_a(p_72943_1_, p_72943_2_);
/*      */   }
/*      */   
/*      */   public int func_72841_b(String p_72841_1_) {
/*  629 */     return this.wrapped.func_72841_b(p_72841_1_);
/*      */   }
/*      */   
/*      */   public void func_82739_e(int p_82739_1_, int p_82739_2_, int p_82739_3_, int p_82739_4_, int p_82739_5_) {
/*  633 */     this.wrapped.func_82739_e(p_82739_1_, p_82739_2_, p_82739_3_, p_82739_4_, p_82739_5_);
/*      */   }
/*      */   
/*      */   public void func_72926_e(int p_72926_1_, int p_72926_2_, int p_72926_3_, int p_72926_4_, int p_72926_5_) {
/*  637 */     this.wrapped.func_72926_e(p_72926_1_, p_72926_2_, p_72926_3_, p_72926_4_, p_72926_5_);
/*      */   }
/*      */   
/*      */   public void func_72889_a(EntityPlayer p_72889_1_, int p_72889_2_, int p_72889_3_, int p_72889_4_, int p_72889_5_, int p_72889_6_) {
/*  641 */     this.wrapped.func_72889_a(p_72889_1_, p_72889_2_, p_72889_3_, p_72889_4_, p_72889_5_, p_72889_6_);
/*      */   }
/*      */   
/*      */   public int func_72800_K() {
/*  645 */     return this.wrapped.func_72800_K();
/*      */   }
/*      */   
/*      */   public int func_72940_L() {
/*  649 */     return this.wrapped.func_72940_L();
/*      */   }
/*      */   
/*      */   public Random func_72843_D(int p_72843_1_, int p_72843_2_, int p_72843_3_) {
/*  653 */     return this.wrapped.func_72843_D(p_72843_1_, p_72843_2_, p_72843_3_);
/*      */   }
/*      */   
/*      */   public ChunkPosition func_147440_b(String p_147440_1_, int p_147440_2_, int p_147440_3_, int p_147440_4_) {
/*  657 */     return this.wrapped.func_147440_b(p_147440_1_, p_147440_2_, p_147440_3_, p_147440_4_);
/*      */   }
/*      */   
/*      */   public boolean func_72806_N() {
/*  661 */     return this.wrapped.func_72806_N();
/*      */   }
/*      */   
/*      */   public double func_72919_O() {
/*  665 */     return this.wrapped.func_72919_O();
/*      */   }
/*      */   
/*      */   public CrashReportCategory func_72914_a(CrashReport p_72914_1_) {
/*  669 */     return this.wrapped.func_72914_a(p_72914_1_);
/*      */   }
/*      */   
/*      */   public void func_147443_d(int p_147443_1_, int p_147443_2_, int p_147443_3_, int p_147443_4_, int p_147443_5_) {
/*  673 */     this.wrapped.func_147443_d(p_147443_1_, p_147443_2_, p_147443_3_, p_147443_4_, p_147443_5_);
/*      */   }
/*      */   
/*      */   public Calendar func_83015_S() {
/*  677 */     return this.wrapped.func_83015_S();
/*      */   }
/*      */   
/*      */   public void func_92088_a(double p_92088_1_, double p_92088_3_, double p_92088_5_, double p_92088_7_, double p_92088_9_, double p_92088_11_, NBTTagCompound p_92088_13_) {
/*  681 */     this.wrapped.func_92088_a(p_92088_1_, p_92088_3_, p_92088_5_, p_92088_7_, p_92088_9_, p_92088_11_, p_92088_13_);
/*      */   }
/*      */   
/*      */   public Scoreboard func_96441_U() {
/*  685 */     return this.wrapped.func_96441_U();
/*      */   }
/*      */   
/*      */   public void func_147453_f(int p_147453_1_, int p_147453_2_, int p_147453_3_, Block p_147453_4_) {
/*  690 */     this.wrapped.func_147453_f(p_147453_1_, p_147453_2_, p_147453_3_, p_147453_4_);
/*      */   }
/*      */   
/*      */   public float func_147462_b(double p_147462_1_, double p_147462_3_, double p_147462_5_) {
/*  695 */     return this.wrapped.func_147462_b(p_147462_1_, p_147462_3_, p_147462_5_);
/*      */   }
/*      */   
/*      */   public float func_147473_B(int p_147473_1_, int p_147473_2_, int p_147473_3_) {
/*  699 */     return this.wrapped.func_147473_B(p_147473_1_, p_147473_2_, p_147473_3_);
/*      */   }
/*      */   
/*      */   public void func_147450_X() {
/*  703 */     this.wrapped.func_147450_X();
/*      */   }
/*      */   
/*      */   public void addTileEntity(TileEntity entity) {
/*  707 */     this.wrapped.addTileEntity(entity);
/*      */   }
/*      */   
/*      */   public ImmutableSetMultimap<ChunkCoordIntPair, ForgeChunkManager.Ticket> getPersistentChunks() {
/*  711 */     return this.wrapped.getPersistentChunks();
/*      */   }
/*      */   
/*      */   public int countEntities(EnumCreatureType type, boolean forSpawnCount) {
/*  716 */     return this.wrapped.countEntities(type, forSpawnCount);
/*      */   }
/*      */   
/*      */   protected IChunkProvider func_72970_h() {
/*  720 */     return null;
/*      */   }
/*      */   
/*      */   protected int func_152379_p() {
/*  724 */     return 0;
/*      */   }
/*      */   
/*      */   public Entity func_73045_a(int p_73045_1_) {
/*  728 */     return this.wrapped.func_73045_a(p_73045_1_);
/*      */   }
/*      */   
/*      */   public boolean func_147465_d(int parX, int parY, int parZ, Block p_147465_4_, int p_147465_5_, int p_147465_6_) {
/*  735 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  736 */     parX = rotated[0];
/*  736 */     parY = rotated[1];
/*  736 */     parZ = rotated[2];
/*  738 */     return this.wrapped.func_147465_d(parX, parY, parZ, p_147465_4_, p_147465_5_, p_147465_6_);
/*      */   }
/*      */   
/*      */   public void markAndNotifyBlock(int parX, int parY, int parZ, Chunk chunk, Block oldBlock, Block newBlock, int flag) {
/*  742 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  743 */     parX = rotated[0];
/*  743 */     parY = rotated[1];
/*  743 */     parZ = rotated[2];
/*  745 */     this.wrapped.markAndNotifyBlock(parX, parY, parZ, chunk, oldBlock, newBlock, flag);
/*      */   }
/*      */   
/*      */   public boolean func_72921_c(int parX, int parY, int parZ, int p_72921_4_, int p_72921_5_) {
/*  749 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  750 */     parX = rotated[0];
/*  750 */     parY = rotated[1];
/*  750 */     parZ = rotated[2];
/*  752 */     return this.wrapped.func_72921_c(parX, parY, parZ, p_72921_4_, p_72921_5_);
/*      */   }
/*      */   
/*      */   public boolean func_147468_f(int parX, int parY, int parZ) {
/*  756 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  757 */     parX = rotated[0];
/*  757 */     parY = rotated[1];
/*  757 */     parZ = rotated[2];
/*  759 */     return this.wrapped.func_147468_f(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public boolean func_147480_a(int parX, int parY, int parZ, boolean p_147480_4_) {
/*  763 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  764 */     parX = rotated[0];
/*  764 */     parY = rotated[1];
/*  764 */     parZ = rotated[2];
/*  766 */     return this.wrapped.func_147480_a(parX, parY, parZ, p_147480_4_);
/*      */   }
/*      */   
/*      */   public boolean func_147449_b(int parX, int parY, int parZ, Block p_147449_4_) {
/*  770 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  771 */     parX = rotated[0];
/*  771 */     parY = rotated[1];
/*  771 */     parZ = rotated[2];
/*  773 */     return this.wrapped.func_147449_b(parX, parY, parZ, p_147449_4_);
/*      */   }
/*      */   
/*      */   public void func_147471_g(int parX, int parY, int parZ) {
/*  777 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  778 */     parX = rotated[0];
/*  778 */     parY = rotated[1];
/*  778 */     parZ = rotated[2];
/*  780 */     this.wrapped.func_147471_g(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public void func_147444_c(int parX, int parY, int parZ, Block p_147444_4_) {
/*  784 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  785 */     parX = rotated[0];
/*  785 */     parY = rotated[1];
/*  785 */     parZ = rotated[2];
/*  787 */     this.wrapped.func_147444_c(parX, parY, parZ, p_147444_4_);
/*      */   }
/*      */   
/*      */   public void func_72975_g(int parX, int parZ, int parYMin, int parYMax) {
/*  791 */     this.wrapped.func_72975_g(parX, parZ, parYMin, parYMax);
/*      */   }
/*      */   
/*      */   public void func_147458_c(int parXMin, int parYMin, int parZMin, int parXMax, int parYMax, int parZMax) {
/*  796 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parXMin, parYMin, parZMin, this.centerX, this.centerY, this.centerZ);
/*  797 */     parXMin = rotated[0];
/*  797 */     parYMin = rotated[1];
/*  797 */     parZMin = rotated[2];
/*  799 */     rotated = this.gdir.rotateXYZAt(this.conv, parXMax, parYMax, parZMax, this.centerX, this.centerY, this.centerZ);
/*  800 */     parXMax = rotated[0];
/*  800 */     parYMax = rotated[1];
/*  800 */     parZMax = rotated[2];
/*  802 */     this.wrapped.func_147458_c(parXMin, parYMin, parZMin, parXMax, parYMax, parZMax);
/*      */   }
/*      */   
/*      */   public void func_147459_d(int parX, int parY, int parZ, Block p_147459_4_) {
/*  806 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  807 */     parX = rotated[0];
/*  807 */     parY = rotated[1];
/*  807 */     parZ = rotated[2];
/*  809 */     this.wrapped.func_147459_d(parX, parY, parZ, p_147459_4_);
/*      */   }
/*      */   
/*      */   public void func_147441_b(int parX, int parY, int parZ, Block p_147441_4_, int p_147441_5_) {
/*  813 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  814 */     parX = rotated[0];
/*  814 */     parY = rotated[1];
/*  814 */     parZ = rotated[2];
/*  816 */     this.wrapped.func_147441_b(parX, parY, parZ, p_147441_4_, p_147441_5_);
/*      */   }
/*      */   
/*      */   public void func_147460_e(int parX, int parY, int parZ, Block p_147460_4_) {
/*  820 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  821 */     parX = rotated[0];
/*  821 */     parY = rotated[1];
/*  821 */     parZ = rotated[2];
/*  823 */     this.wrapped.func_147460_e(parX, parY, parZ, p_147460_4_);
/*      */   }
/*      */   
/*      */   public void func_72915_b(EnumSkyBlock p_72915_1_, int parX, int parY, int parZ, int p_72915_5_) {
/*  827 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  828 */     parX = rotated[0];
/*  828 */     parY = rotated[1];
/*  828 */     parZ = rotated[2];
/*  830 */     this.wrapped.func_72915_b(p_72915_1_, parX, parY, parZ, p_72915_5_);
/*      */   }
/*      */   
/*      */   public void func_147464_a(int parX, int parY, int parZ, Block block, int tickRate) {
/*  835 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  836 */     parX = rotated[0];
/*  836 */     parY = rotated[1];
/*  836 */     parZ = rotated[2];
/*  838 */     this.wrapped.func_147464_a(parX, parY, parZ, block, tickRate);
/*      */   }
/*      */   
/*      */   public void func_147454_a(int parX, int parY, int parZ, Block block, int tickRate, int priority) {
/*  842 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  843 */     parX = rotated[0];
/*  843 */     parY = rotated[1];
/*  843 */     parZ = rotated[2];
/*  845 */     this.wrapped.func_147454_a(parX, parY, parZ, block, tickRate, priority);
/*      */   }
/*      */   
/*      */   public Block func_147439_a(int parX, int parY, int parZ) {
/*  852 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  853 */     parX = rotated[0];
/*  853 */     parY = rotated[1];
/*  853 */     parZ = rotated[2];
/*  855 */     TileEntityBlockRotator te = getTileEntityBlockRotator((IBlockAccess)this.wrapped, parX, parY, parZ);
/*  856 */     if (te != null) {
/*  857 */       Block storedBlock = te.getStoredBlock();
/*  858 */       if (storedBlock != null)
/*  858 */         return storedBlock; 
/*      */     } 
/*  860 */     return this.wrapped.func_147439_a(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public TileEntity func_147438_o(int parX, int parY, int parZ) {
/*  864 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  865 */     parX = rotated[0];
/*  865 */     parY = rotated[1];
/*  865 */     parZ = rotated[2];
/*  867 */     return this.wrapped.func_147438_o(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public boolean func_147437_c(int parX, int parY, int parZ) {
/*  871 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  872 */     parX = rotated[0];
/*  872 */     parY = rotated[1];
/*  872 */     parZ = rotated[2];
/*  874 */     return this.wrapped.func_147437_c(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public boolean func_72899_e(int parX, int parY, int parZ) {
/*  878 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  879 */     parX = rotated[0];
/*  879 */     parY = rotated[1];
/*  879 */     parZ = rotated[2];
/*  881 */     return this.wrapped.func_72899_e(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public int func_72805_g(int parX, int parY, int parZ) {
/*  885 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  886 */     parX = rotated[0];
/*  886 */     parY = rotated[1];
/*  886 */     parZ = rotated[2];
/*  888 */     return this.wrapped.func_72805_g(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public int getBlockLightOpacity(int parX, int parY, int parZ) {
/*  892 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  893 */     parX = rotated[0];
/*  893 */     parY = rotated[1];
/*  893 */     parZ = rotated[2];
/*  895 */     return this.wrapped.getBlockLightOpacity(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public boolean isSideSolid(int parX, int parY, int parZ, ForgeDirection side) {
/*  899 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  900 */     parX = rotated[0];
/*  900 */     parY = rotated[1];
/*  900 */     parZ = rotated[2];
/*  907 */     side = ForgeDirection.values()[this.gdir.forgeSideRot[side.ordinal()]];
/*  909 */     return this.wrapped.isSideSolid(parX, parY, parZ, side);
/*      */   }
/*      */   
/*      */   public boolean isSideSolid(int parX, int parY, int parZ, ForgeDirection side, boolean _default) {
/*  913 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  914 */     parX = rotated[0];
/*  914 */     parY = rotated[1];
/*  914 */     parZ = rotated[2];
/*  921 */     side = ForgeDirection.values()[this.gdir.forgeSideRot[side.ordinal()]];
/*  923 */     return this.wrapped.isSideSolid(parX, parY, parZ, side, _default);
/*      */   }
/*      */   
/*      */   public boolean func_72884_u(int parX, int parY, int parZ) {
/*  927 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  928 */     parX = rotated[0];
/*  928 */     parY = rotated[1];
/*  928 */     parZ = rotated[2];
/*  930 */     return this.wrapped.func_72884_u(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public boolean func_72850_v(int parX, int parY, int parZ) {
/*  934 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  935 */     parX = rotated[0];
/*  935 */     parY = rotated[1];
/*  935 */     parZ = rotated[2];
/*  937 */     return this.wrapped.func_72850_v(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public boolean func_72834_c(int parX, int parY, int parZ, boolean p_72834_4_) {
/*  941 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  942 */     parX = rotated[0];
/*  942 */     parY = rotated[1];
/*  942 */     parZ = rotated[2];
/*  944 */     return this.wrapped.func_72834_c(parX, parY, parZ, p_72834_4_);
/*      */   }
/*      */   
/*      */   public boolean canBlockFreezeBody(int parX, int parY, int parZ, boolean p_72834_4_) {
/*  948 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  949 */     parX = rotated[0];
/*  949 */     parY = rotated[1];
/*  949 */     parZ = rotated[2];
/*  951 */     return this.wrapped.canBlockFreezeBody(parX, parY, parZ, p_72834_4_);
/*      */   }
/*      */   
/*      */   public boolean func_147478_e(int parX, int parY, int parZ, boolean p_147478_4_) {
/*  955 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  956 */     parX = rotated[0];
/*  956 */     parY = rotated[1];
/*  956 */     parZ = rotated[2];
/*  958 */     return this.wrapped.func_147478_e(parX, parY, parZ, p_147478_4_);
/*      */   }
/*      */   
/*      */   public boolean canSnowAtBody(int parX, int parY, int parZ, boolean p_147478_4_) {
/*  962 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  963 */     parX = rotated[0];
/*  963 */     parY = rotated[1];
/*  963 */     parZ = rotated[2];
/*  965 */     return this.wrapped.canSnowAtBody(parX, parY, parZ, p_147478_4_);
/*      */   }
/*      */   
/*      */   public boolean func_72951_B(int parX, int parY, int parZ) {
/*  969 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  970 */     parX = rotated[0];
/*  970 */     parY = rotated[1];
/*  970 */     parZ = rotated[2];
/*  972 */     return this.wrapped.func_72951_B(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public boolean func_72958_C(int parX, int parY, int parZ) {
/*  976 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  977 */     parX = rotated[0];
/*  977 */     parY = rotated[1];
/*  977 */     parZ = rotated[2];
/*  979 */     return this.wrapped.func_72958_C(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public boolean func_72962_a(EntityPlayer p_72962_1_, int parX, int parY, int parZ) {
/*  983 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  984 */     parX = rotated[0];
/*  984 */     parY = rotated[1];
/*  984 */     parZ = rotated[2];
/*  986 */     return this.wrapped.func_72962_a(p_72962_1_, parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public boolean canMineBlockBody(EntityPlayer par1EntityPlayer, int parX, int parY, int parZ) {
/*  990 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  991 */     parX = rotated[0];
/*  991 */     parY = rotated[1];
/*  991 */     parZ = rotated[2];
/*  993 */     return this.wrapped.canMineBlockBody(par1EntityPlayer, parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public boolean func_147477_a(int parX, int parY, int parZ, Block p_147477_4_) {
/*  998 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/*  999 */     parX = rotated[0];
/*  999 */     parY = rotated[1];
/*  999 */     parZ = rotated[2];
/* 1001 */     return this.wrapped.func_147477_a(parX, parY, parZ, p_147477_4_);
/*      */   }
/*      */   
/*      */   public boolean func_72937_j(int parX, int parY, int parZ) {
/* 1005 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/* 1006 */     parX = rotated[0];
/* 1006 */     parY = rotated[1];
/* 1006 */     parZ = rotated[2];
/* 1008 */     return this.wrapped.func_72937_j(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public int func_72883_k(int parX, int parY, int parZ) {
/* 1012 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/* 1013 */     parX = rotated[0];
/* 1013 */     parY = rotated[1];
/* 1013 */     parZ = rotated[2];
/* 1015 */     return this.wrapped.func_72883_k(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public int func_72957_l(int parX, int parY, int parZ) {
/* 1019 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/* 1020 */     parX = rotated[0];
/* 1020 */     parY = rotated[1];
/* 1020 */     parZ = rotated[2];
/* 1022 */     return this.wrapped.func_72957_l(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public int func_72849_a(int parX, int parY, int parZ, boolean p_72849_4_) {
/* 1026 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/* 1027 */     parX = rotated[0];
/* 1027 */     parY = rotated[1];
/* 1027 */     parZ = rotated[2];
/* 1029 */     return this.wrapped.func_72849_a(parX, parY, parZ, p_72849_4_);
/*      */   }
/*      */   
/*      */   public int func_72925_a(EnumSkyBlock p_72925_1_, int parX, int parY, int parZ) {
/* 1033 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/* 1034 */     parX = rotated[0];
/* 1034 */     parY = rotated[1];
/* 1034 */     parZ = rotated[2];
/* 1036 */     return this.wrapped.func_72925_a(p_72925_1_, parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public int func_72972_b(EnumSkyBlock p_72972_1_, int parX, int parY, int parZ) {
/* 1040 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/* 1041 */     parX = rotated[0];
/* 1041 */     parY = rotated[1];
/* 1041 */     parZ = rotated[2];
/* 1043 */     return this.wrapped.func_72972_b(p_72972_1_, parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public int func_72802_i(int parX, int parY, int parZ, int p_72802_4_) {
/* 1047 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/* 1048 */     parX = rotated[0];
/* 1048 */     parY = rotated[1];
/* 1048 */     parZ = rotated[2];
/* 1050 */     return this.wrapped.func_72802_i(parX, parY, parZ, p_72802_4_);
/*      */   }
/*      */   
/*      */   public float func_72801_o(int parX, int parY, int parZ) {
/* 1054 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/* 1055 */     parX = rotated[0];
/* 1055 */     parY = rotated[1];
/* 1055 */     parZ = rotated[2];
/* 1057 */     return this.wrapped.func_72801_o(parX, parY, parZ);
/*      */   }
/*      */   
/*      */   public boolean func_147445_c(int parX, int parY, int parZ, boolean p_147445_4_) {
/* 1061 */     int[] rotated = this.gdir.rotateXYZAt(this.conv, parX, parY, parZ, this.centerX, this.centerY, this.centerZ);
/* 1062 */     parX = rotated[0];
/* 1062 */     parY = rotated[1];
/* 1062 */     parZ = rotated[2];
/* 1064 */     return this.wrapped.func_147445_c(parX, parY, parZ, p_147445_4_);
/*      */   }
/*      */   
/*      */   public Block func_147474_b(int parX, int parZ) {
/* 1071 */     return this.wrapped.func_147474_b(parX, parZ);
/*      */   }
/*      */   
/*      */   public int func_72976_f(int parX, int parZ) {
/* 1075 */     return this.wrapped.func_72976_f(parX, parZ);
/*      */   }
/*      */   
/*      */   public int func_82734_g(int parX, int parZ) {
/* 1079 */     return this.wrapped.func_82734_g(parX, parZ);
/*      */   }
/*      */   
/*      */   public int func_72879_k(int p_72879_1_, int p_72879_2_, int p_72879_3_, int p_72879_4_) {
/* 1086 */     return this.wrapped.func_72879_k(p_72879_1_, p_72879_2_, p_72879_3_, p_72879_4_);
/*      */   }
/*      */   
/*      */   public int func_94577_B(int p_94577_1_, int p_94577_2_, int p_94577_3_) {
/* 1090 */     return this.wrapped.func_94577_B(p_94577_1_, p_94577_2_, p_94577_3_);
/*      */   }
/*      */   
/*      */   public boolean func_94574_k(int p_94574_1_, int p_94574_2_, int p_94574_3_, int p_94574_4_) {
/* 1094 */     return this.wrapped.func_94574_k(p_94574_1_, p_94574_2_, p_94574_3_, p_94574_4_);
/*      */   }
/*      */   
/*      */   public int func_72878_l(int p_72878_1_, int p_72878_2_, int p_72878_3_, int p_72878_4_) {
/* 1098 */     return this.wrapped.func_72878_l(p_72878_1_, p_72878_2_, p_72878_3_, p_72878_4_);
/*      */   }
/*      */   
/*      */   public boolean func_72864_z(int p_72864_1_, int p_72864_2_, int p_72864_3_) {
/* 1102 */     return this.wrapped.func_72864_z(p_72864_1_, p_72864_2_, p_72864_3_);
/*      */   }
/*      */   
/*      */   public int func_94572_D(int p_94572_1_, int p_94572_2_, int p_94572_3_) {
/* 1107 */     return this.wrapped.func_94572_D(p_94572_1_, p_94572_2_, p_94572_3_);
/*      */   }
/*      */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */