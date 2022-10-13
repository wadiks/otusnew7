package ru.otus.spring.Insurance;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.modelPolis.PolicyOrder;
import ru.otus.spring.modelPolis.Polis;

import java.util.List;

@MessagingGateway
public interface InsuranceCompany {

    @Gateway(requestChannel = "policyOrderChannel", replyChannel = "policyChannel")
    List<Polis> process(List<PolicyOrder> policyOrders);
}
