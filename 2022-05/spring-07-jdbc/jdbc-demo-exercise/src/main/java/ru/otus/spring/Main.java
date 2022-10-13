package ru.otus.spring;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import ru.otus.spring.Insurance.InsuranceCompany;
import ru.otus.spring.modelPolis.PolicyOrder;
import ru.otus.spring.modelPolis.Polis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
public class Main {
    /**
     * ИСЖ - инвестиционное страхование жизни
     * Исж - это инвестиции в ценные бумаги. Акции, валюты, фонды
     * ----------------------------------------------------------------
     * КСЖ - кредитное страхование жизни
     * -----------------------------------------------------------------
     * НСЖ - накопительное страхование жизни.
     * Нсж - это как вклад только без процента и страховка по здоровью
     */
    private static InsuranceCompany insuranceCompany = null;

    private static final String[] namePolicy = {"ИСЖ", "КСЖ", "НСЖ"};

    public Main(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    @Bean
    public QueueChannel policyOrderChannel() {
        return MessageChannels.queue(3).get();
    }

    @Bean
    public PublishSubscribeChannel policyChannel() {
        return MessageChannels.publishSubscribe().get();
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class);
        ForkJoinPool pool = ForkJoinPool.commonPool();  // пул потоков
        while (true) {
            Thread.sleep(2000);
            pool.execute(() -> {
                List<PolicyOrder> policyOrders = generateOrderItems();
                List<Polis> polis = insuranceCompany.process(policyOrders);
                print(polis);
            });
        }
    }

    @Bean
    public IntegrationFlow insurance() {
        var channel = IntegrationFlows.from("policyOrderChannel")
                .split()
                .handle("insuranceService", "signingAnd")
                .aggregate()
                .channel("policyChannel")
                .get();
        return channel;
    }

    private static List<PolicyOrder> generateOrderItems() {
        final Random prize = new Random();
        List<PolicyOrder> items = new ArrayList<>();
        var order = newOrder();
        System.out.println("Уважаемый клиент вы заказали полис  = " + order.getPolicyType());
        items.add(order);
        if (prize.nextBoolean()) {
            var prizePolicy = new PolicyOrder(namePolicy[RandomUtils.nextInt(0, namePolicy.length)]);
            System.out.println("Уважаемый клиент вы выйграли дополнительный полис  = " + prizePolicy.getPolicyType());
            items.add(prizePolicy);
        }
        return items;
    }

    private static PolicyOrder newOrder() {
        return new PolicyOrder(namePolicy[RandomUtils.nextInt(0, namePolicy.length)]);

    }

    private static void print(List<Polis> polis) {
        System.out.println("Уважаемый клиент все готово ");
        polis.forEach(p -> System.out.format("Ваш заказанный полис %s  условия  %s", p.getName(), p.getType()));
    }
}
