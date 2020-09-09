package tariff;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SMSFixTariffTest
{

	@Test
	void canSend()
	{
		SMSFixTariff tariff = new SMSFixTariff();

		assertThat(tariff.canSend()).isTrue();

		tariff.setCount(tariff.getQUOTA());

		assertThat(tariff.canSend()).isFalse();
	}
}