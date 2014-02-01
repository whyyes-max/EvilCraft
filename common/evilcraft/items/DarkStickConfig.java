package evilcraft.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import evilcraft.Reference;
import evilcraft.api.config.ItemConfig;

public class DarkStickConfig extends ItemConfig {
    
    public static DarkStickConfig _instance;

    public DarkStickConfig() {
        super(
            Reference.ITEM_DARKSTICK,
            "Dark Stick",
            "darkStick",
            null,
            DarkStick.class
        );
    }
    
    @Override
    public String getOreDictionaryId() {
        return Reference.DICT_WOODSTICK;
    }
    
}
