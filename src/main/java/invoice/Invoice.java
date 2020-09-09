package invoice;

import java.util.Calendar;

public class Invoice
{
	private double amount;
	private Calendar date;

	public Invoice()
	{
		this.amount = 0;
		this.date = Calendar.getInstance();
	}

	public double getAmount()
	{
		return amount;
	}

	public Calendar getDate()
	{
		return date;
	}

	public void addAmount(double toAdd)
	{
		this.amount += toAdd;
	}
}
