import company.Company;
import invoice.Invoice;
import invoice.InvoiceManager;
import notification.SMS;
import tariff.EMailFixTariff;
import tariff.TariffManager;

import java.util.ArrayList;

public class Main
{
	public static void main(String[] args)
	{
		Company company = new Company(1L, "YKK", "55555", "ykk@ykk.com", "EN");
		InvoiceManager invoiceManager = InvoiceManager.getInstance();
		TariffManager tariffManager = TariffManager.getInstance();

		invoiceManager.addInvoiceToCompany(company.getId(), new Invoice());
//		invoiceManager.addToBlacklist(company.getId());
		tariffManager.addTariffToCompany(company.getId(), new EMailFixTariff());
		tariffManager.addTariffToCompany(company.getId(), new tariff.SMSFixTariff());
		ArrayList<String> recipientList = new ArrayList<String>();
		recipientList.add("a");
		recipientList.add("b");
		invoiceManager.addToBlacklist(company.getId());
		SMS sms = new SMS(company, recipientList, "Content");
		sms.sendAll();
	}
}
