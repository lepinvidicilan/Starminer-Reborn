/*     */ package jp.mc.ancientred.starminer.basics.gui;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import jp.mc.ancientred.starminer.basics.packet.SMPacketSender;
/*     */ import jp.mc.ancientred.starminer.basics.tileentity.TileEntityGravityGenerator;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.network.NetHandlerPlayClient;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class GuiStarCore extends GuiContainer {
/*  18 */   private static final ResourceLocation resource = new ResourceLocation("starminer:textures/gui/GuiStarCore.png");
/*     */   
/*     */   public static final String REMOTE = "REMOTE";
/*     */   
/*     */   public static final int ButtonAddGrvOneID = 1;
/*     */   
/*     */   public static final int ButtonSubGrvOneID = 2;
/*     */   
/*     */   public static final int ButtonAddGrvTenID = 3;
/*     */   
/*     */   public static final int ButtonSubGrvTenID = 4;
/*     */   
/*     */   public static final int ButtonAddRadOneID = 5;
/*     */   
/*     */   public static final int ButtonSubRadOneID = 6;
/*     */   
/*     */   public static final int ButtonAddRadTenID = 7;
/*     */   
/*     */   public static final int ButtonSubRadTenID = 8;
/*     */   
/*     */   public static final int ButtonToggleTypeID = 9;
/*     */   
/*     */   private GuiButton ButtonAddGrvOne;
/*     */   
/*     */   private GuiButton ButtonSubGrvOne;
/*     */   
/*     */   private GuiButton ButtonAddGrvTen;
/*     */   
/*     */   private GuiButton ButtonSubGrvTen;
/*     */   
/*     */   private GuiButton ButtonAddRadOne;
/*     */   
/*     */   private GuiButton ButtonSubRadOne;
/*     */   
/*     */   private GuiButton ButtonAddRadTen;
/*     */   
/*     */   private GuiButton ButtonSubRadTen;
/*     */   
/*     */   private GuiButton ButtonToggleType;
/*     */   
/*     */   private int inventoryRows;
/*     */   
/*     */   private WeakReference<EntityPlayer> player;
/*     */   
/*     */   TileEntityGravityGenerator par3InvStarCore;
/*     */   
/*  44 */   private static final String[] TYPENAMES = new String[] { "starInfo.type.sph", "starInfo.type.cub", "starInfo.type.xcyl", "starInfo.type.ycyl", "starInfo.type.zcyl" };
/*     */   
/*     */   public GuiStarCore(EntityPlayer par1Player, TileEntityGravityGenerator par3InvStarCore) {
/*  48 */     super(new ContainerStarCore(par1Player, par3InvStarCore));
/*  49 */     this.par3InvStarCore = par3InvStarCore;
/*  50 */     this.player = new WeakReference<EntityPlayer>(par1Player);
/*  51 */     this.field_146291_p = false;
/*  52 */     short short1 = 222;
/*  53 */     int i = short1 - 108;
/*  54 */     this.inventoryRows = par3InvStarCore.func_70302_i_() / 9;
/*  55 */     this.field_147000_g = 222;
/*     */   }
/*     */   
/*     */   public void func_146281_b() {
/*  61 */     super.func_146281_b();
/*     */   }
/*     */   
/*     */   public void func_73866_w_() {
/*  67 */     super.func_73866_w_();
/*  68 */     int xLeft = (this.field_146294_l - this.field_146999_f) / 2;
/*  69 */     int yTop = (this.field_146295_m - this.field_147000_g) / 2;
/*  70 */     int grvButtonXLeft = xLeft + 8;
/*  71 */     int grvButtonWidth = 17;
/*  72 */     int grvButtonHeight = 20;
/*  73 */     int grvButtonShiftH = grvButtonWidth + 1;
/*  74 */     int grvButtonFirstRowY = yTop + 8;
/*  75 */     int grvButtonSecondRowY = yTop + 55 - grvButtonHeight;
/*  76 */     this.field_146292_n.add(this.ButtonSubRadTen = new GuiButton(8, grvButtonXLeft, grvButtonFirstRowY, grvButtonWidth, grvButtonHeight, "-5"));
/*  77 */     this.field_146292_n.add(this.ButtonSubRadOne = new GuiButton(6, grvButtonXLeft + grvButtonShiftH, grvButtonFirstRowY, grvButtonWidth, grvButtonHeight, "-1"));
/*  78 */     this.field_146292_n.add(this.ButtonSubGrvTen = new GuiButton(4, grvButtonXLeft, grvButtonSecondRowY, grvButtonWidth, grvButtonHeight, "-5"));
/*  79 */     this.field_146292_n.add(this.ButtonSubGrvOne = new GuiButton(2, grvButtonXLeft + grvButtonShiftH, grvButtonSecondRowY, grvButtonWidth, grvButtonHeight, "-1"));
/*  81 */     grvButtonXLeft = xLeft + 97;
/*  82 */     this.field_146292_n.add(this.ButtonAddRadOne = new GuiButton(5, grvButtonXLeft, grvButtonFirstRowY, grvButtonWidth, grvButtonHeight, "+1"));
/*  83 */     this.field_146292_n.add(this.ButtonAddRadTen = new GuiButton(7, grvButtonXLeft + grvButtonShiftH, grvButtonFirstRowY, grvButtonWidth, grvButtonHeight, "+5"));
/*  84 */     this.field_146292_n.add(this.ButtonAddGrvOne = new GuiButton(1, grvButtonXLeft, grvButtonSecondRowY, grvButtonWidth, grvButtonHeight, "+1"));
/*  85 */     this.field_146292_n.add(this.ButtonAddGrvTen = new GuiButton(3, grvButtonXLeft + grvButtonShiftH, grvButtonSecondRowY, grvButtonWidth, grvButtonHeight, "+5"));
/*  87 */     this.field_146292_n.add(this.ButtonToggleType = new GuiButton(9, xLeft + 135, grvButtonFirstRowY, 30, grvButtonHeight, StatCollector.func_74838_a(TYPENAMES[this.par3InvStarCore.type])));
/*     */   }
/*     */   
/*     */   public void func_73876_c() {
/*  93 */     super.func_73876_c();
/*  94 */     int xLeft = (this.field_146294_l - this.field_146999_f) / 2;
/*  95 */     int yTop = (this.field_146295_m - this.field_147000_g) / 2;
/*  97 */     this.ButtonToggleType.field_146126_j = StatCollector.func_74838_a(TYPENAMES[this.par3InvStarCore.type]);
/*     */   }
/*     */   
/*     */   public void func_146979_b(int par1, int par2) {
/* 103 */     super.func_146979_b(par1, par2);
/* 105 */     this.field_146289_q.func_78276_b(StatCollector.func_74838_a("container.inventory"), 8, this.field_147000_g - 94, 4210752);
/* 106 */     this.field_146289_q.func_78276_b(StatCollector.func_74838_a("starInfo.inventory"), 8, this.field_147000_g - 162, 4210752);
/* 108 */     int centerX = 70;
/* 109 */     String string = StatCollector.func_74838_a("starInfo.titleSSize");
/* 110 */     int centered = centerX - this.field_146289_q.func_78256_a(string) / 2;
/* 111 */     this.field_146289_q.func_78276_b(string, centered, this.field_147000_g - 212, 15658734);
/* 113 */     string = StatCollector.func_74837_a("starInfo.gSizeInfo", new Object[] { (int)this.par3InvStarCore.starRad });
/* 114 */     centered = centerX - this.field_146289_q.func_78256_a(string) / 2;
/* 115 */     this.field_146289_q.func_78276_b(string, centered, this.field_147000_g - 202, 15658734);
/* 117 */     string = StatCollector.func_74838_a("starInfo.titleGrange");
/* 118 */     centered = centerX - this.field_146289_q.func_78256_a(string) / 2;
/* 119 */     this.field_146289_q.func_78276_b(string, centered, this.field_147000_g - 187, 15658734);
/* 121 */     string = StatCollector.func_74837_a("starInfo.gRangeInfo", new Object[] { (int)this.par3InvStarCore.gravityRange });
/* 122 */     centered = centerX - this.field_146289_q.func_78256_a(string) / 2;
/* 123 */     this.field_146289_q.func_78276_b(string, centered, this.field_147000_g - 177, 15658734);
/*     */   }
/*     */   
/*     */   protected void func_146976_a(float par1, int par2, int par3) {
/* 132 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 133 */     this.field_146297_k.func_110434_K().func_110577_a(resource);
/* 134 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 135 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/* 136 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */   }
/*     */   
/*     */   protected void func_146284_a(GuiButton par1GuiButton) {
/*     */     EntityPlayer player;
/* 143 */     if ((player = this.player.get()) != null) {
/* 144 */       NetHandlerPlayClient nethandlerplayclient = this.field_146297_k.func_147114_u();
/* 145 */       nethandlerplayclient.func_147297_a(SMPacketSender.createGUIActPacket(par1GuiButton.field_146127_k));
/*     */     } 
/*     */   }
/*     */ }


/*
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.2.33
 */