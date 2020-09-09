package tariff;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class EMailFixTariffTest
{

	@Test
	void canSend()
	{
		EMailFixTariff tariff = new EMailFixTariff();

		assertThat(tariff.canSend()).isTrue();

		tariff.setCount(tariff.getQUOTA());

		assertThat(tariff.canSend()).isFalse();
	}
}