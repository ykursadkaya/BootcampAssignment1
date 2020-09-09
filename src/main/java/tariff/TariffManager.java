package tariff;

import exceptions.CompanyHasNoTariffException;
import exceptions.CompanyIsBlacklistedException;
import exceptions.CompanyNotFoundException;
import invoice.Invoice;
import invoice.InvoiceManager;

import java.util.HashMap;
import java.util.Objects;

public class TariffManager
{
	private static TariffManager singleton = null;
	private HashMap<Long, HashMap<String, Tariff>> companyTariffs;

	public TariffManager()
	{
		this.companyTariffs = new HashMap<Long, HashMap<String, Tariff>>();
	}

	public static TariffManager getInstance()
	{
		if (Objects.isNull(singleton))
		{
			singleton = new TariffManager();
		}
		return singleton;
	}

//	public boolean hasCompany(Long id)
//	{
//		return companyTariffs.containsKey(id);
//	}

	public void addTariffToCompany(Long id, Tariff tariff)
	{
		InvoiceManager invoiceManager = InvoiceManager.getInstance();
		try
		{
			if (!invoiceManager.isBlacklisted(id))
			{
				companyTariffs.putIfAbsent(id, new HashMap<String, Tariff>());
				HashMap<String, Tariff> companyTariffMap = getTariffMap(id);
				companyTariffMap.put(tariff.getType(), tariff);
				Invoice invoice = invoiceManager.getLastInvoice(id);
				invoice.addAmount(tariff.getPRICE());
				// older tariff didnt checked
			}
		}
		catch (CompanyIsBlacklistedException e)
		{
			e.printStackTrace();
		}
	}

	public Tariff getTariff(Long id, String type) throws CompanyHasNoTariffException
	{
		HashMap<String, Tariff> companyTariffMap = getTariffMap(id);
		Tariff tariff = companyTariffMap.get(type);
		if (Objects.nonNull(tariff))
		{
			return tariff;
		}
		throw new CompanyHasNoTariffException("");
	}

	public HashMap<String, Tariff> getTariffMap(Long id) throws CompanyNotFoundException
	{
		HashMap<String, Tariff> companyTariffMap = companyTariffs.get(id);
		if (Objects.nonNull(companyTariffMap))
		{
			return companyTariffMap;
		}
		throw new CompanyNotFoundException("");
	}
}
