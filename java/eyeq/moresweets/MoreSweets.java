package eyeq.moresweets;

import eyeq.util.client.model.UModelCreator;
import eyeq.util.client.model.UModelLoader;
import eyeq.util.client.model.gson.ItemmodelJsonFactory;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.creativetab.UCreativeTab;
import eyeq.util.item.IItemUsePotion;
import eyeq.util.item.UItemFood;
import eyeq.util.oredict.CategoryTypes;
import eyeq.util.oredict.UOreDictionary;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.io.File;

import static eyeq.moresweets.MoreSweets.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
@Mod.EventBusSubscriber
public class MoreSweets {
    public static final String MOD_ID = "eyeq_moresweets";

    @Mod.Instance(MOD_ID)
    public static MoreSweets instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    public static Item miracleGummi;
    public static final CreativeTabs TAB_MORE_SWEETS = new UCreativeTab("MoreSweets", () -> new ItemStack(miracleGummi));

    public static Item leavesGummi;
    public static Item appleGummi;
    public static Item grapeGummi;
    public static Item peachGummi;
    public static Item pineappleGummi;
    public static Item persimmonGummi;
    public static Item pearGummi;
    public static Item mixGummi;

    public static Item chocolatePie;
    public static Item fishPie;
    public static Item meatPie;
    public static Item applePie;
    public static Item carrotPie;
    public static Item pumpkinStew;

    public static Item chocolate;
    public static Item iceCandy;
    public static Item appleBaked;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        addRecipes();
        if(event.getSide().isServer()) {
            return;
        }
        renderItemModels();
        createFiles();
    }

    @SubscribeEvent
    protected static void registerItems(RegistryEvent.Register<Item> event) {
        leavesGummi = new UItemFood(1, 0.3F, false).setPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0), 1.0F).setUnlocalizedName("leavesGummi").setCreativeTab(TAB_MORE_SWEETS);
        appleGummi = new UItemFood(4, 0.5F, false).setPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 2), 1.0F).setAlwaysEdible().setUnlocalizedName("appleGummi").setCreativeTab(TAB_MORE_SWEETS);
        grapeGummi = new UItemFood(4, 0.8F, false).setPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1), 1.0F).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 20, 0), 1.0F).setAlwaysEdible().setUnlocalizedName("grapeGummi").setCreativeTab(TAB_MORE_SWEETS);
        peachGummi = new UItemFood(4, 0.5F, false).setClearPotionType(IItemUsePotion.ClearPotionType.CLEAR_BAD).setPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1), 1.0F).setAlwaysEdible().setUnlocalizedName("peachGummi").setCreativeTab(TAB_MORE_SWEETS);
        pineappleGummi = new UItemFood(4, 0.5F, false).setPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 1), 1.0F).setAlwaysEdible().setUnlocalizedName("pineappleGummi").setCreativeTab(TAB_MORE_SWEETS);
        persimmonGummi = new UItemFood(6, 0.4F, false).setFire(-1, 1.0F).setPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1), 1.0F).setAlwaysEdible().setUnlocalizedName("persimmonGummi").setCreativeTab(TAB_MORE_SWEETS);
        pearGummi = new UItemFood(4, 0.5F, false).setPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1), 1.0F).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 400, 0), 1.0F).setAlwaysEdible().setUnlocalizedName("pearGummi").setCreativeTab(TAB_MORE_SWEETS);
        mixGummi = new UItemFood(4, 0.5F, false).setClearPotionType(IItemUsePotion.ClearPotionType.CLEAR_BAD).setPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 4), 1.0F).setAlwaysEdible().setUnlocalizedName("mixGummi").setCreativeTab(TAB_MORE_SWEETS);
        miracleGummi = new UItemFood(4, 0.5F, false).setClearPotionType(IItemUsePotion.ClearPotionType.CLEAR_BAD).setPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 9), 1.0F)
                .addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1200, 0), 1.0F).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 3600, 0), 1.0F)
                .addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 3600, 0), 1.0F).addPotionEffect(new PotionEffect(MobEffects.HASTE, 3600, 0), 1.0F).addPotionEffect(new PotionEffect(MobEffects.SPEED, 3600, 0), 1.0F)
                .setAlwaysEdible().setUnlocalizedName("miracleGummi").setCreativeTab(TAB_MORE_SWEETS);

        chocolatePie = new ItemFood(8, 0.7F, false).setPotionEffect(new PotionEffect(MobEffects.HASTE, 3600, 0), 0.5F).setUnlocalizedName("chocolatePie").setCreativeTab(TAB_MORE_SWEETS);
        fishPie = new ItemFood(8, 1.0F, false).setPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 2400, 0), 0.5F).setUnlocalizedName("fishPie").setCreativeTab(TAB_MORE_SWEETS);
        meatPie = new ItemFood(10, 1.3F, false).setPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2400, 0), 0.5F).setUnlocalizedName("meatPie").setCreativeTab(TAB_MORE_SWEETS);
        applePie = new ItemFood(6, 0.8F, false).setPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 2400, 0), 0.5F).setUnlocalizedName("applePie").setCreativeTab(TAB_MORE_SWEETS);
        carrotPie = new ItemFood(8, 0.8F, false).setPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1800, 0), 0.5F).setUnlocalizedName("carrotPie").setCreativeTab(TAB_MORE_SWEETS);
        pumpkinStew = new ItemSoup(6).setUnlocalizedName("pumpkinStew").setCreativeTab(TAB_MORE_SWEETS);

        chocolate = new ItemFood(4, 1.2F, false).setUnlocalizedName("chocolate").setCreativeTab(TAB_MORE_SWEETS);
        iceCandy = new ItemFood(2, 0.3F, false).setPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1800, 0), 0.5F).setUnlocalizedName("iceCandy").setCreativeTab(TAB_MORE_SWEETS);
        appleBaked = new ItemFood(4, 0.3F, false).setUnlocalizedName("appleBaked").setCreativeTab(TAB_MORE_SWEETS);

        GameRegistry.register(leavesGummi, resource.createResourceLocation("gummi_leaves"));
        GameRegistry.register(appleGummi, resource.createResourceLocation("gummi_apple"));
        GameRegistry.register(grapeGummi, resource.createResourceLocation("gummi_grape"));
        GameRegistry.register(peachGummi, resource.createResourceLocation("gummi_peach"));
        GameRegistry.register(pineappleGummi, resource.createResourceLocation("gummi_pineapple"));
        GameRegistry.register(persimmonGummi, resource.createResourceLocation("gummi_persimmon"));
        GameRegistry.register(pearGummi, resource.createResourceLocation("gummi_pear"));
        GameRegistry.register(mixGummi, resource.createResourceLocation("gummi_mix"));
        GameRegistry.register(miracleGummi, resource.createResourceLocation("gummi_miracle"));

        GameRegistry.register(chocolatePie, resource.createResourceLocation("chocolate_pie"));
        GameRegistry.register(fishPie, resource.createResourceLocation("fish_pie"));
        GameRegistry.register(meatPie, resource.createResourceLocation("meat_pie"));
        GameRegistry.register(applePie, resource.createResourceLocation("apple_pie"));
        GameRegistry.register(carrotPie, resource.createResourceLocation("carrot_pie"));
        GameRegistry.register(pumpkinStew, resource.createResourceLocation("pumpkin_stew"));

        GameRegistry.register(chocolate, resource.createResourceLocation("chocolate"));
        GameRegistry.register(iceCandy, resource.createResourceLocation("ice_candy"));
        GameRegistry.register(appleBaked, resource.createResourceLocation("apple_baked"));

        UOreDictionary.registerOre(CategoryTypes.SWEET, "leavesGummi", leavesGummi);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "appleGummi", appleGummi);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "grapeGummi", grapeGummi);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "peachGummi", peachGummi);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "pineappleGummi", pineappleGummi);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "persimmonGummi", persimmonGummi);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "pearGummi", pearGummi);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "mixGummi", mixGummi);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "miracleGummi", miracleGummi);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "chocolatePie", chocolatePie);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "fishPie", fishPie);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "meatPie", meatPie);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "applePie", applePie);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "carrotPie", carrotPie);
        UOreDictionary.registerOre(CategoryTypes.COOKED, "pumpkinStew", pumpkinStew);
        OreDictionary.registerOre("chocolate", chocolate);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "chocolate", chocolate);
        UOreDictionary.registerOre(CategoryTypes.SWEET, "iceCandy", iceCandy);
        UOreDictionary.registerOre(CategoryTypes.COOKED, "appleBaked", appleBaked);
    }

    public static void addRecipeGummi(Item output, String input) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(output),
                Items.SUGAR, UOreDictionary.OREDICT_SLIME_BALL, input));
    }

    public static void addRecipeMixGummi(Item output, String input) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(output),
                Items.SUGAR, UOreDictionary.OREDICT_SLIME_BALL, input, input, input, input));
    }

    public static void addRecipePie(Item output, String input) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output,
                Items.SUGAR, UOreDictionary.OREDICT_EGG, input));
    }

    public static void addRecipes() {
        addRecipeGummi(leavesGummi, UOreDictionary.OREDICT_LEAVES);
        addRecipeGummi(appleGummi, CategoryTypes.PREFIX_FRUIT.getDictionaryName("apple"));
        addRecipeGummi(grapeGummi, CategoryTypes.PREFIX_FRUIT.getDictionaryName("grape"));
        addRecipeGummi(peachGummi, CategoryTypes.PREFIX_FRUIT.getDictionaryName("peach"));
        addRecipeGummi(pineappleGummi, CategoryTypes.PREFIX_FRUIT.getDictionaryName("pineapple"));
        addRecipeGummi(persimmonGummi, CategoryTypes.PREFIX_FRUIT.getDictionaryName("persimmon"));
        addRecipeGummi(pearGummi, CategoryTypes.PREFIX_FRUIT.getDictionaryName("pear"));
        addRecipeMixGummi(mixGummi, UOreDictionary.OREDICT_FRUIT);
        addRecipeMixGummi(miracleGummi, UOreDictionary.OREDICT_GOLDEN_FOOD);

        addRecipePie(chocolatePie, "chocolate");
        addRecipePie(fishPie, UOreDictionary.OREDICT_COOKED_FISH);
        addRecipePie(meatPie, UOreDictionary.OREDICT_COOKED_MEAT);
        addRecipePie(applePie, CategoryTypes.PREFIX_FRUIT.getDictionaryName("apple"));
        addRecipePie(carrotPie, UOreDictionary.OREDICT_CARROT);
        GameRegistry.addRecipe(new ShapelessOreRecipe(pumpkinStew,
                Items.BOWL, CategoryTypes.PREFIX_VEGETABLE.getDictionaryName("pumpkin")));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(chocolate),
                Items.SUGAR, Items.SUGAR, CategoryTypes.PREFIX_GRAIN.getDictionaryName("cocoa")));
        GameRegistry.addShapelessRecipe(new ItemStack(iceCandy, 4),
                Items.SUGAR, Blocks.ICE);

        GameRegistry.addSmelting(Items.APPLE, new ItemStack(appleBaked), 0.1F);
    }

    @SideOnly(Side.CLIENT)
    public static void renderItemModels() {
        UModelLoader.setCustomModelResourceLocation(leavesGummi);
        UModelLoader.setCustomModelResourceLocation(appleGummi);
        UModelLoader.setCustomModelResourceLocation(grapeGummi);
        UModelLoader.setCustomModelResourceLocation(peachGummi);
        UModelLoader.setCustomModelResourceLocation(pineappleGummi);
        UModelLoader.setCustomModelResourceLocation(persimmonGummi);
        UModelLoader.setCustomModelResourceLocation(pearGummi);
        UModelLoader.setCustomModelResourceLocation(mixGummi);
        UModelLoader.setCustomModelResourceLocation(miracleGummi);

        UModelLoader.setCustomModelResourceLocation(chocolatePie);
        UModelLoader.setCustomModelResourceLocation(fishPie);
        UModelLoader.setCustomModelResourceLocation(meatPie);
        UModelLoader.setCustomModelResourceLocation(applePie);
        UModelLoader.setCustomModelResourceLocation(carrotPie);
        UModelLoader.setCustomModelResourceLocation(pumpkinStew);

        UModelLoader.setCustomModelResourceLocation(chocolate);
        UModelLoader.setCustomModelResourceLocation(iceCandy);
        UModelLoader.setCustomModelResourceLocation(appleBaked);
    }

    public static void createFiles() {
        File project = new File("../1.11.2-MoreSweets");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, TAB_MORE_SWEETS, "MoreSweets");
        language.register(LanguageResourceManager.JA_JP, TAB_MORE_SWEETS, "お菓子");

        language.register(LanguageResourceManager.EN_US, leavesGummi, "Leaves Gummi");
        language.register(LanguageResourceManager.JA_JP, leavesGummi, "ハッパグミ");
        language.register(LanguageResourceManager.EN_US, appleGummi, "Apple Gummi");
        language.register(LanguageResourceManager.JA_JP, appleGummi, "リンゴグミ");
        language.register(LanguageResourceManager.EN_US, grapeGummi, "Grape Gummi");
        language.register(LanguageResourceManager.JA_JP, grapeGummi, "ブドウグミ");
        language.register(LanguageResourceManager.EN_US, peachGummi, "Peach Gummi");
        language.register(LanguageResourceManager.JA_JP, peachGummi, "モモグミ");
        language.register(LanguageResourceManager.EN_US, pineappleGummi, "Pineapple Gummi");
        language.register(LanguageResourceManager.JA_JP, pineappleGummi, "パイナップルグミ");
        language.register(LanguageResourceManager.EN_US, persimmonGummi, "Persimmon Gummi");
        language.register(LanguageResourceManager.JA_JP, persimmonGummi, "カキグミ");
        language.register(LanguageResourceManager.EN_US, pearGummi, "Pear Gummi");
        language.register(LanguageResourceManager.JA_JP, pearGummi, "ナシグミ");
        language.register(LanguageResourceManager.EN_US, mixGummi, "Mix Gummi");
        language.register(LanguageResourceManager.JA_JP, mixGummi, "ミックスグミ");
        language.register(LanguageResourceManager.EN_US, miracleGummi, "Miracle Gummi");
        language.register(LanguageResourceManager.JA_JP, miracleGummi, "ミラクルグミ");

        language.register(LanguageResourceManager.EN_US, chocolatePie, "Chocolate Pie");
        language.register(LanguageResourceManager.JA_JP, chocolatePie, "チョコレートパイ");
        language.register(LanguageResourceManager.EN_US, fishPie, "Fish Pot Pie");
        language.register(LanguageResourceManager.JA_JP, fishPie, "魚のパイ包み焼き");
        language.register(LanguageResourceManager.EN_US, meatPie, "Meat Pie");
        language.register(LanguageResourceManager.JA_JP, meatPie, "ミートパイ");
        language.register(LanguageResourceManager.EN_US, applePie, "Apple Pie");
        language.register(LanguageResourceManager.JA_JP, applePie, "アップルパイ");
        language.register(LanguageResourceManager.EN_US, carrotPie, "Carrot Pie");
        language.register(LanguageResourceManager.JA_JP, carrotPie, "キャロットパイ");
        language.register(LanguageResourceManager.EN_US, pumpkinStew, "Pumpkin Stew");
        language.register(LanguageResourceManager.JA_JP, pumpkinStew, "カボチャシチュー");

        language.register(LanguageResourceManager.EN_US, chocolate, "Chocolate");
        language.register(LanguageResourceManager.JA_JP, chocolate, "チョコレート");
        language.register(LanguageResourceManager.EN_US, iceCandy, "Ice Candy");
        language.register(LanguageResourceManager.JA_JP, iceCandy, "アイスキャンディ");
        language.register(LanguageResourceManager.EN_US, appleBaked, "Baked Apple");
        language.register(LanguageResourceManager.JA_JP, appleBaked, "焼きリンゴ");

        ULanguageCreator.createLanguage(project, MOD_ID, language);

        UModelCreator.createItemJson(project, leavesGummi, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, appleGummi, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, grapeGummi, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, peachGummi, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, pineappleGummi, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, persimmonGummi, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, pearGummi, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, mixGummi, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, miracleGummi, ItemmodelJsonFactory.ItemmodelParent.GENERATED);

        UModelCreator.createItemJson(project, chocolatePie, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, fishPie, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, meatPie, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, applePie, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, carrotPie, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, pumpkinStew, ItemmodelJsonFactory.ItemmodelParent.GENERATED);

        UModelCreator.createItemJson(project, chocolate, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, iceCandy, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
        UModelCreator.createItemJson(project, appleBaked, ItemmodelJsonFactory.ItemmodelParent.GENERATED);
    }
}
