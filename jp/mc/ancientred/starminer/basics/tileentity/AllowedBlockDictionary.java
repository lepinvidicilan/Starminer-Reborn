/*     */ package jp.mc.ancientred.starminer.basics.tileentity;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import jp.mc.ancientred.starminer.basics.SMModContainer;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ 
/*     */ public class AllowedBlockDictionary {
/*     */   public static final boolean isAllowed(Block block) {
/*  14 */     if (block == null)
/*  14 */       return false; 
/*  15 */     return placableBlockMap.containsKey(block);
/*     */   }
/*     */   
/*  17 */   public static final HashMap<Block, Object> placableBlockMap = new HashMap<Block, Object>();
/*     */   
/*  18 */   public static final Object ALLOWED = new Object();
/*     */   
/*     */   static {
/*  21 */     placableBlockMap.put(SMModContainer.OuterCoreBlock, ALLOWED);
/*  22 */     placableBlockMap.put(SMModContainer.InnerCoreBlock, ALLOWED);
/*  23 */     placableBlockMap.put(SMModContainer.DirtGrassExBlock, ALLOWED);
/*  27 */     placableBlockMap.put(Blocks.field_150348_b, ALLOWED);
/*  29 */     placableBlockMap.put(Blocks.field_150346_d, ALLOWED);
/*  30 */     placableBlockMap.put(Blocks.field_150347_e, ALLOWED);
/*  31 */     placableBlockMap.put(Blocks.field_150344_f, ALLOWED);
/*  33 */     placableBlockMap.put(Blocks.field_150357_h, ALLOWED);
/*  38 */     placableBlockMap.put(Blocks.field_150354_m, ALLOWED);
/*  39 */     placableBlockMap.put(Blocks.field_150351_n, ALLOWED);
/*  40 */     placableBlockMap.put(Blocks.field_150352_o, ALLOWED);
/*  41 */     placableBlockMap.put(Blocks.field_150366_p, ALLOWED);
/*  42 */     placableBlockMap.put(Blocks.field_150365_q, ALLOWED);
/*  43 */     placableBlockMap.put(Blocks.field_150364_r, ALLOWED);
/*  44 */     placableBlockMap.put(Blocks.field_150363_s, ALLOWED);
/*  45 */     placableBlockMap.put(Blocks.field_150362_t, ALLOWED);
/*  46 */     placableBlockMap.put(Blocks.field_150361_u, ALLOWED);
/*  47 */     placableBlockMap.put(Blocks.field_150360_v, ALLOWED);
/*  48 */     placableBlockMap.put(Blocks.field_150359_w, ALLOWED);
/*  49 */     placableBlockMap.put(Blocks.field_150369_x, ALLOWED);
/*  50 */     placableBlockMap.put(Blocks.field_150368_y, ALLOWED);
/*  52 */     placableBlockMap.put(Blocks.field_150322_A, ALLOWED);
/*  58 */     placableBlockMap.put(Blocks.field_150321_G, ALLOWED);
/*  63 */     placableBlockMap.put(Blocks.field_150325_L, ALLOWED);
/*  69 */     placableBlockMap.put(Blocks.field_150340_R, ALLOWED);
/*  70 */     placableBlockMap.put(Blocks.field_150339_S, ALLOWED);
/*  73 */     placableBlockMap.put(Blocks.field_150336_V, ALLOWED);
/*  74 */     placableBlockMap.put(Blocks.field_150335_W, ALLOWED);
/*  75 */     placableBlockMap.put(Blocks.field_150342_X, ALLOWED);
/*  76 */     placableBlockMap.put(Blocks.field_150341_Y, ALLOWED);
/*  77 */     placableBlockMap.put(Blocks.field_150343_Z, ALLOWED);
/*  84 */     placableBlockMap.put(Blocks.field_150482_ag, ALLOWED);
/*  85 */     placableBlockMap.put(Blocks.field_150484_ah, ALLOWED);
/* 101 */     placableBlockMap.put(Blocks.field_150450_ax, ALLOWED);
/* 106 */     placableBlockMap.put(Blocks.field_150431_aC, ALLOWED);
/* 107 */     placableBlockMap.put(Blocks.field_150432_aD, ALLOWED);
/* 110 */     placableBlockMap.put(Blocks.field_150435_aG, ALLOWED);
/* 114 */     placableBlockMap.put(Blocks.field_150423_aK, ALLOWED);
/* 115 */     placableBlockMap.put(Blocks.field_150424_aL, ALLOWED);
/* 116 */     placableBlockMap.put(Blocks.field_150425_aM, ALLOWED);
/* 117 */     placableBlockMap.put(Blocks.field_150426_aN, ALLOWED);
/* 119 */     placableBlockMap.put(Blocks.field_150428_aP, ALLOWED);
/* 120 */     placableBlockMap.put(Blocks.field_150414_aQ, ALLOWED);
/* 125 */     placableBlockMap.put(Blocks.field_150417_aV, ALLOWED);
/* 130 */     placableBlockMap.put(Blocks.field_150440_ba, ALLOWED);
/* 137 */     placableBlockMap.put(Blocks.field_150391_bh, ALLOWED);
/* 139 */     placableBlockMap.put(Blocks.field_150385_bj, ALLOWED);
/* 148 */     placableBlockMap.put(Blocks.field_150377_bs, ALLOWED);
/* 156 */     placableBlockMap.put(Blocks.field_150412_bA, ALLOWED);
/* 160 */     placableBlockMap.put(Blocks.field_150475_bE, ALLOWED);
/* 172 */     placableBlockMap.put(Blocks.field_150467_bQ, ALLOWED);
/* 179 */     placableBlockMap.put(Blocks.field_150451_bX, ALLOWED);
/* 180 */     placableBlockMap.put(Blocks.field_150449_bY, ALLOWED);
/* 182 */     placableBlockMap.put(Blocks.field_150371_ca, ALLOWED);
/* 186 */     placableBlockMap.put(Blocks.field_150406_ce, ALLOWED);
/* 187 */     placableBlockMap.put(Blocks.field_150407_cf, ALLOWED);
/* 189 */     placableBlockMap.put(Blocks.field_150405_ch, ALLOWED);
/* 190 */     placableBlockMap.put(Blocks.field_150402_ci, ALLOWED);
/* 191 */     placableBlockMap.put(Blocks.field_150403_cj, ALLOWED);
/* 195 */     placableBlockMap.put(Blocks.field_150399_cn, ALLOWED);
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */