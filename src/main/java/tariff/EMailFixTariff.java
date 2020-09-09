package tariff;

import notification.EMail;

public class EMailFixTariff extends Tariff
{
	public static final int QUOTA = 1000;
	public static final double PRICE = 10.0;

	public EMailFixTariff()
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
	public String getType()
	{
		return EMail.class.getSimpleName();
	}

	@Override
	public boolean canSend()
	{
		return count < QUOTA;
	}
}
