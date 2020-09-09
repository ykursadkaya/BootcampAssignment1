package tariff;

public abstract class Tariff
{
	protected int count;

	public Tariff(int count)
	{
		this.count = count;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public void incrementCount()
	{
		this.count += 1;
	}

	public abstract String getType();

	public abstract boolean canSend();

	public abstract int getQUOTA();

	public abstract double getPRICE();
}
