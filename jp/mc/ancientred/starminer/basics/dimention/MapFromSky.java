/*     */ package jp.mc.ancientred.starminer.basics.dimention;
/*     */ 
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.storage.MapData;
/*     */ 
/*     */ public class MapFromSky {
/*  11 */   public static boolean hasSkyMapImageData = false;
/*     */   
/*     */   public static MapData mapDataFromSky;
/*     */   
/*     */   public static byte[] skyMapclientData;
/*     */   
/*  14 */   public static boolean doRecompileSkyMapList = false;
/*     */   
/*     */   public static void createMapDataFromSky(World world) {
/*  16 */     mapDataFromSky = new MapData("fromskymap");
/*  17 */     createMapFromBiomeCache(world, mapDataFromSky);
/*     */   }
/*     */   
/*     */   public static void createMapFromBiomeCache(World par1World, MapData par3MapData) /* Patched from JD-Core V0 */{
    short short1 = 128;
    short short2 = 128;
    int i = 128;
    int j = 0;
    int k = 0;
    int l = MathHelper.func_76128_c(0.0D - j) / i + short1 / 2;
    int i1 = MathHelper.func_76128_c(0.0D - k) / i + short2 / 2;
    int j1 = 128 / i;
    if (par1World.field_73011_w.field_76576_e) {
      j1 /= 2;
    }
    for (int k1 = l - j1 - 1 - 62; k1 < l + j1 + 63; k1++)
    {
      int l1 = 255;
      int i2 = 0;
      double d0 = 0.0D;
      for (int j2 = i1 - j1 - 1 - 62; j2 < i1 + j1 + 63; j2++)
      {
        int k2 = k1 - l;
        int l2 = j2 - i1;
        boolean flag = k2 * k2 + l2 * l2 > (j1 - 2) * (j1 - 2);
        int i3 = (j / i + k1 - short1 / 2) * i;
        int j3 = (k / i + j2 - short2 / 2) * i;
        
        BiomeGenBase base = par1World.field_73011_w.field_76578_c.func_76935_a(i3, j3);
        
        int col = MapColor.field_151662_n.field_76290_q;
        if (base == BiomeGenBase.field_76787_r) {
          col = MapColor.field_151658_d.field_76290_q;
        }
        if (base == BiomeGenBase.field_150576_N) {
          col = MapColor.field_151665_m.field_76290_q;
        }
        if (base == BiomeGenBase.field_76769_d) {
          col = MapColor.field_151658_d.field_76290_q;
        }
        if (base == BiomeGenBase.field_76786_s) {
          col = MapColor.field_151658_d.field_76290_q;
        }
        if (base == BiomeGenBase.field_76770_e) {
          col = MapColor.field_151665_m.field_76290_q;
        }
        if (base == BiomeGenBase.field_76783_v) {
          col = MapColor.field_151665_m.field_76290_q;
        }
        if (base == BiomeGenBase.field_150580_W) {
          col = MapColor.field_151665_m.field_76290_q;
        }
        if (base == BiomeGenBase.field_76767_f) {
          col = MapColor.field_151663_o.field_76290_q;
        }
        if (base == BiomeGenBase.field_76785_t) {
          col = MapColor.field_151663_o.field_76290_q;
        }
        if (base == BiomeGenBase.field_76776_l) {
          col = MapColor.field_151657_g.field_76290_q;
        }
        if (base == BiomeGenBase.field_76777_m) {
          col = MapColor.field_151657_g.field_76290_q;
        }
        if (base == BiomeGenBase.field_76775_o) {
          col = MapColor.field_151666_j.field_76290_q;
        }
        if (base == BiomeGenBase.field_76774_n) {
          col = MapColor.field_151666_j.field_76290_q;
        }
        if (base == BiomeGenBase.field_76782_w) {
          col = MapColor.field_151669_i.field_76290_q;
        }
        if (base == BiomeGenBase.field_76792_x) {
          col = MapColor.field_151669_i.field_76290_q;
        }
        if (base == BiomeGenBase.field_150574_L) {
          col = MapColor.field_151669_i.field_76290_q;
        }
        if (base == BiomeGenBase.field_76789_p) {
          col = MapColor.field_151664_l.field_76290_q;
        }
        if (base == BiomeGenBase.field_76788_q) {
          col = MapColor.field_151664_l.field_76290_q;
        }
        if (base == BiomeGenBase.field_150577_O) {
          col = MapColor.field_151666_j.field_76290_q;
        }
        if (base == BiomeGenBase.field_150584_S) {
          col = MapColor.field_151666_j.field_76290_q;
        }
        if (base == BiomeGenBase.field_150579_T) {
          col = MapColor.field_151666_j.field_76290_q;
        }
        if (base == BiomeGenBase.field_150578_U) {
          col = MapColor.field_151666_j.field_76290_q;
        }
        if (base == BiomeGenBase.field_150581_V) {
          col = MapColor.field_151666_j.field_76290_q;
        }
        if (base == BiomeGenBase.field_150583_P) {
          col = MapColor.field_151663_o.field_76290_q;
        }
        if (base == BiomeGenBase.field_150582_Q) {
          col = MapColor.field_151663_o.field_76290_q;
        }
        if (base == BiomeGenBase.field_150585_R) {
          col = MapColor.field_151663_o.field_76290_q;
        }
        if (base == BiomeGenBase.field_150588_X) {
          col = MapColor.field_151661_c.field_76290_q;
        }
        if (base == BiomeGenBase.field_150587_Y) {
          col = MapColor.field_151661_c.field_76290_q;
        }
        if (base == BiomeGenBase.field_150589_Z) {
          col = MapColor.field_151656_f.field_76290_q;
        }
        if (base == BiomeGenBase.field_150608_ab) {
          col = MapColor.field_151656_f.field_76290_q;
        }
        if (base == BiomeGenBase.field_150607_aa) {
          col = MapColor.field_151656_f.field_76290_q;
        }
        if (base == BiomeGenBase.field_76771_b) {
          col = MapColor.field_151662_n.field_76290_q;
        }
        if (base == BiomeGenBase.field_150575_M) {
          col = MapColor.field_151662_n.field_76290_q;
        }
        if (base == BiomeGenBase.field_76772_c) {
          col = MapColor.field_151661_c.field_76290_q;
        }
        if (base == BiomeGenBase.field_76781_i) {
          col = MapColor.field_151662_n.field_76290_q;
        }
        if (base != BiomeGenBase.field_76778_j || 
          base != BiomeGenBase.field_76779_k || 
          base == BiomeGenBase.field_76780_h) {
          col = MapColor.field_151667_k.field_76290_q;
        }
        if (base == BiomeGenBase.field_76768_g) {
          col = MapColor.field_151666_j.field_76290_q;
        }
        if (base == BiomeGenBase.field_76784_u) {
          col = MapColor.field_151666_j.field_76290_q;
        }
        par3MapData.field_76198_e[(k1 + j2 * short1)] = ((byte)(col * 4 + 1));
      }
    }
  }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */