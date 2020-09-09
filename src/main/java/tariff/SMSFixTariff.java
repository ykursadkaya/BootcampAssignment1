package tariff;

import notification.SMS;

public class SMSFixTariff extends Tariff
{
	public static final int QUOTA = 1000;
	public static final double PRICE = 20.0;

	public SMSFixTariff()
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
		return SMS.class.getSimpleName();
	}

	@Override
	public boolean canSend()
	{
		return count < QUOTA;
	}
}
