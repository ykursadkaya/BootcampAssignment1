package invoice;

import exceptions.CompanyHasNoInvoiceException;
import exceptions.CompanyIsBlacklistedException;
import exceptions.CompanyNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class InvoiceManager
{
	private static InvoiceManager singleton = null;
	private HashMap<Long, ArrayList<Invoice>> companyInvoices;
	private HashSet<Long> blackList;

	public InvoiceManager()
	{
		this.companyInvoices = new HashMap<Long, ArrayList<Invoice>>();
		this.blackList = new HashSet<Long>();
	}

	public static InvoiceManager getInstance()
	{
		if (Objects.isNull(singleton))
		{
			singleton = new InvoiceManager();
		}
		return singleton;
	}

	public void addInvoiceToCompany(Long id, Invoice invoice)
	{
		companyInvoices.putIfAbsent(id, new ArrayList<Invoice>());
		ArrayList<Invoice> invoices = getInvoices(id);
		invoices.add(invoice);
	}

	public ArrayList<Invoice> getInvoices(Long id) throws CompanyNotFoundException
	{
		ArrayList<Invoice> invoices = companyInvoices.get(id);
		if (Objects.nonNull(invoices))
		{
			return invoices;
		}
		throw new CompanyNotFoundException("");
	}

	public Invoice getLastInvoice(Long id) throws CompanyHasNoInvoiceException
	{
		ArrayList<Invoice> invoices = getInvoices(id);
		if (invoices.size() > 0)
		{
			return invoices.get(invoices.size() -1);
		}
		throw new CompanyHasNoInvoiceException("");
	}

	public void addToBlacklist(Long id) throws CompanyNotFoundException
	{
		if (companyInvoices.containsKey(id))
		{
			blackList.add(id);
		}
		else
		{
			throw new CompanyNotFoundException("");
		}
	}

	public void removeFromBlacklist(Long id) throws CompanyNotFoundException
	{
		if (companyInvoices.containsKey(id) && blackList.contains(id))
		{
			blackList.remove(id);
		}
		else
		{
			throw new CompanyNotFoundException("");
		}
	}

	public boolean isBlacklisted(Long id)
	{
		if (blackList.contains(id))
		{
			throw new CompanyIsBlacklistedException("");
		}
		return false;
	}

	public void payLastInvoice(Long id) throws CompanyHasNoInvoiceException
	{
		ArrayList<Invoice> invoices = getInvoices(id);
		if (invoices.size() > 0)
		{
			invoices.remove(invoices.size() - 1);
		}
		else
		{
			throw new CompanyHasNoInvoiceException("");
		}
	}

	public void payAll(Long id) throws CompanyHasNoInvoiceException
	{
		ArrayList<Invoice> invoices = getInvoices(id);
		if (invoices.size() > 0)
		{
			invoices.clear();
		}
		else
		{
			throw new CompanyHasNoInvoiceException("");
		}
	}
}
