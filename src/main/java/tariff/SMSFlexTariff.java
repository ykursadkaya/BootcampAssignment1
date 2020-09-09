package tariff;

import notification.SMS;

public class SMSFlexTariff extends Tariff implements FlexTariff
{
	public static final int QUOTA = 2000;
	public static final double PRICE = 30.0;

	public SMSFlexTariff()
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
		return 0.10;
	}

	@Override
	public String getType()
	{
		return SMS.class.getSimpleName();
	}

	@Override
	public boolean canSend()
	{
		return true;
	}
}
