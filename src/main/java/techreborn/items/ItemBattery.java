package techreborn.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.api.power.IEnergyItemInfo;
import reborncore.common.powerSystem.PoweredItem;
import techreborn.client.TechRebornCreativeTab;

public class ItemBattery extends ItemTextureBase implements IEnergyItemInfo
{

	String name = "null";
	int maxEnergy = 0;
	int maxTransfer = 0;
	int tier = 0;
	
	public ItemBattery(String name, int maxEnergy, int maxTransfer, int tier)
	{
		super();
		setMaxStackSize(1);
		setMaxDamage(1);
		setUnlocalizedName("techreborn."+name);
		this.name=name;
		this.maxEnergy=maxEnergy;
		this.maxTransfer=maxTransfer;
		this.tier=tier;
		setCreativeTab(TechRebornCreativeTab.instance);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List itemList)
	{
		ItemStack itemStack = new ItemStack(this, 1);
		itemList.add(itemStack);

		ItemStack charged = new ItemStack(this, 1);
		PoweredItem.setEnergy(getMaxPower(charged), charged);
		itemList.add(charged);
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		double charge = (PoweredItem.getEnergy(stack) / getMaxPower(stack));
		return 1 - charge;

	}

	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		return true;
	}

	@Override
	public double getMaxPower(ItemStack stack)
	{
		return maxEnergy;
	}

	@Override
	public boolean canAcceptEnergy(ItemStack stack)
	{
		return true;
	}

	@Override
	public boolean canProvideEnergy(ItemStack stack)
	{
		return true;
	}

	@Override
	public double getMaxTransfer(ItemStack stack)
	{
		return maxTransfer;
	}

	@Override
	public int getStackTier(ItemStack stack)
	{
		return tier;
	}

	@Override
	public String getTextureName(int damage)
	{
		return "techreborn:items/tool/"+name;
	}

	@Override
	public int getMaxMeta()
	{
		return 1;
	}
}
