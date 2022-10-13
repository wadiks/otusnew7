package ru.otus.spring.Insurance;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.spring.modelPolis.PolicyOrder;
import ru.otus.spring.modelPolis.Polis;

/**
 * ИСЖ - инвестиционное страхование жизни
 * Исж - это инвестиции в ценные бумаги. Акции, валюты, фонды
 * ----------------------------------------------------------------
 * КСЖ - кредитное страхование жизни
 * -----------------------------------------------------------------
 * НСЖ - накопительное страхование жизни.
 * Нсж - это как вклад только без процента и страховка по здоровью
 */

@Service
public class InsuranceService {
    private static final String[] typePolicyIsj = {"Валюта", "Акции", "Фонды"};

    public Polis signingAnd(PolicyOrder policyOrder) throws InterruptedException {

        var pol = new Polis() {{
            setName(policyOrder.getPolicyType());

            switch (policyOrder.getPolicyType()) {
                case "ИСЖ":
                    setType(typePolicyIsj[RandomUtils.nextInt(0, typePolicyIsj.length)]);
                    break;
                case "КСЖ":
                    setType(String.format("Кредит под ставку  %s годовых.", RandomUtils.nextInt(0, 5) + "%"));
                    break;
                case "НСЖ":
                    setType("Вклад внесен. ");
                    break;
            }
        }};
        return pol;
    }
}
