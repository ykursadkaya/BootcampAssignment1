package tariff;

import exceptions.CompanyNotFoundException;
import invoice.Invoice;
import invoice.InvoiceManager;
import notification.SMS;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class TariffManagerTest
{

	@Test
	void getInstance()
	{
		TariffManager tariffManager1 = TariffManager.getInstance();
		TariffManager tariffManager2 = TariffManager.getInstance();

		assertThat(tariffManager1).isNotNull();
		assertThat(tariffManager1).isEqualTo(tariffManager2);
	}

	@Test
	void getTariff()
	{
		InvoiceManager.getInstance().addInvoiceToCompany(1L, new Invoice());
		TariffManager tariffManager = TariffManager.getInstance();
		Throwable throwable = catchThrowable(() -> tariffManager.getTariff(1L, "SMS"));

		assertThat(throwable).isNotNull();
		assertThat(throwable).isInstanceOf(CompanyNotFoundException.class);

		SMSFixTariff tariffToAdd = new SMSFixTariff();
		tariffManager.addTariffToCompany(1L, tariffToAdd);
		Tariff tariffReturn = tariffManager.getTariff(1L, tariffToAdd.getType());

		assertThat(tariffReturn).isNotNull();
		assertThat(tariffReturn).isInstanceOf(SMSFixTariff.class);
		assertThat(tariffReturn).isEqualTo(tariffToAdd);
	}

	@Test
	void getTariffMap()
	{
		InvoiceManager.getInstance().addInvoiceToCompany(1L, new Invoice());
		TariffManager tariffManager = TariffManager.getInstance();
		Throwable throwable = catchThrowable(() -> tariffManager.getTariff(1L, "SMS"));

		assertThat(throwable).isNotNull();
		assertThat(throwable).isInstanceOf(CompanyNotFoundException.class);

		SMSFixTariff tariffToAdd = new SMSFixTariff();
		tariffManager.addTariffToCompany(1L, tariffToAdd);
		HashMap<String, Tariff> companyTariffMap = tariffManager.getTariffMap(1L);

		assertThat(companyTariffMap).isNotNull();
		assertThat(companyTariffMap).isInstanceOf(HashMap.class);
		assertThat(companyTariffMap).containsKey(tariffToAdd.getType());
		assertThat(companyTariffMap.get(tariffToAdd.getType())).isEqualTo(tariffToAdd);
	}

	@Test
	void addTariffToCompany()
	{
		InvoiceManager invoiceManager = InvoiceManager.getInstance();
		Invoice invoice = new Invoice();
		invoiceManager.addInvoiceToCompany(1L, invoice);
		SMSFixTariff tariff = new SMSFixTariff();
		TariffManager.getInstance().addTariffToCompany(1L, tariff);

		assertThat(invoice.getAmount()).isEqualTo(tariff.getPRICE());
	}
}