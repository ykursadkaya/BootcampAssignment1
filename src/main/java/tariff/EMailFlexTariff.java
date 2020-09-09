package tariff;

import notification.EMail;

public class EMailFlexTariff extends Tariff implements FlexTariff
{
	public static final int QUOTA = 2000;
	public static final double PRICE = 7.5;

	public EMailFlexTariff()
	{
		super(0);
	}

	public int getQUOTA()
	{
		return QUOTA;
	}

	public double getPRICE()
	{
		return PRICE;
	}

	@Override
	public double unitPrice()
	{
		return 0.03;
	}

	@Override
	public String getType()
	{
		return EMail.class.getSimpleName();
	}

	@Override
	public boolean canSend()
	{
		return true;
	}
}
