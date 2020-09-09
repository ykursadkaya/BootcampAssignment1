package invoice;

import exceptions.CompanyHasNoInvoiceException;
import exceptions.CompanyIsBlacklistedException;
import exceptions.CompanyNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class InvoiceManagerTest
{

	@Test
	void getInstance()
	{
		InvoiceManager invoiceManager1 = InvoiceManager.getInstance();
		InvoiceManager invoiceManager2 = InvoiceManager.getInstance();

		assertThat(invoiceManager1).isNotNull();
		assertThat(invoiceManager1).isEqualTo(invoiceManager2);
	}

//	@Test
//	void addInvoiceToCompany()
//	{
//
//	}

	@Test
	void getInvoices()
	{
		InvoiceManager invoiceManager = InvoiceManager.getInstance();
		Throwable throwable = catchThrowable(() -> invoiceManager.getInvoices(1L));

		assertThat(throwable).isNotNull();
		assertThat(throwable).isInstanceOf(CompanyNotFoundException.class);

		invoiceManager.addInvoiceToCompany(1L, new Invoice());
		ArrayList<Invoice> invoices = invoiceManager.getInvoices(1L);

		assertThat(invoices).isNotNull();
		assertThat(invoices).hasSize(1);
	}

	@Test
	void getLastInvoice()
	{
		InvoiceManager invoiceManager = InvoiceManager.getInstance();
		invoiceManager.addInvoiceToCompany(1L, new Invoice());
		ArrayList<Invoice> invoices = invoiceManager.getInvoices(1L);
		invoices.clear();
		Throwable throwable = catchThrowable(() -> invoiceManager.getLastInvoice(1L));

		assertThat(throwable).isNotNull();
		assertThat(throwable).isInstanceOf(CompanyHasNoInvoiceException.class);

		Invoice invoiceToAdd = new Invoice();
		invoiceManager.addInvoiceToCompany(1L, invoiceToAdd);
		Invoice invoiceReturn = invoiceManager.getLastInvoice(1L);

		assertThat(invoiceReturn).isNotNull();
		assertThat(invoiceReturn).isEqualTo(invoiceToAdd);
	}

	@Test
	void addToBlacklist()
	{
		InvoiceManager invoiceManager = InvoiceManager.getInstance();
		Throwable throwable = catchThrowable(() -> invoiceManager.addToBlacklist(1L));

		assertThat(throwable).isNotNull();
		assertThat(throwable).isInstanceOf(CompanyNotFoundException.class);

		invoiceManager.addInvoiceToCompany(1L, new Invoice());

		assertThat(invoiceManager.isBlacklisted(1L)).isFalse();

		invoiceManager.addToBlacklist(1L);

		throwable = catchThrowable(() -> invoiceManager.isBlacklisted(1L));

		assertThat(throwable).isNotNull();
		assertThat(throwable).isInstanceOf(CompanyIsBlacklistedException.class);
	}

//	@Test
//	void isBlacklisted()
//	{
//	}

	@Test
	void payLastInvoice()
	{
		InvoiceManager invoiceManager = InvoiceManager.getInstance();
		Throwable throwable = catchThrowable(() -> invoiceManager.payLastInvoice(1L));

		assertThat(throwable).isNotNull();
		assertThat(throwable).isInstanceOf(CompanyNotFoundException.class);

		invoiceManager.addInvoiceToCompany(1L, new Invoice());
		ArrayList<Invoice> invoices = invoiceManager.getInvoices(1L);
		invoices.clear();
		throwable = catchThrowable(() -> invoiceManager.payLastInvoice(1L));

		assertThat(throwable).isNotNull();
		assertThat(throwable).isInstanceOf(CompanyHasNoInvoiceException.class);

		invoiceManager.addInvoiceToCompany(1L, new Invoice());
		invoiceManager.addInvoiceToCompany(1L, new Invoice());
		invoiceManager.payLastInvoice(1L);

		assertThat(invoices).isNotNull();
		assertThat(invoices).hasSize(1);
	}

	@Test
	void payAll()
	{
		InvoiceManager invoiceManager = InvoiceManager.getInstance();
		Throwable throwable = catchThrowable(() -> invoiceManager.payLastInvoice(1L));

		assertThat(throwable).isNotNull();
		assertThat(throwable).isInstanceOf(CompanyNotFoundException.class);

		invoiceManager.addInvoiceToCompany(1L, new Invoice());
		ArrayList<Invoice> invoices = invoiceManager.getInvoices(1L);
		invoices.clear();
		throwable = catchThrowable(() -> invoiceManager.payLastInvoice(1L));

		assertThat(throwable).isNotNull();
		assertThat(throwable).isInstanceOf(CompanyHasNoInvoiceException.class);

		invoiceManager.addInvoiceToCompany(1L, new Invoice());
		invoiceManager.addInvoiceToCompany(1L, new Invoice());
		invoiceManager.payAll(1L);

		assertThat(invoices).isNotNull();
		assertThat(invoices).hasSize(0);
	}

	@Test
	void removeFromBlacklist()
	{
		InvoiceManager invoiceManager = InvoiceManager.getInstance();
		Throwable throwable = catchThrowable(() -> invoiceManager.removeFromBlacklist(1L));

		assertThat(throwable).isNotNull();
		assertThat(throwable).isInstanceOf(CompanyNotFoundException.class);

		invoiceManager.addInvoiceToCompany(1L, new Invoice());
		invoiceManager.addToBlacklist(1L);
		invoiceManager.removeFromBlacklist(1L);

		assertThat(invoiceManager.isBlacklisted(1L)).isFalse();
	}
}