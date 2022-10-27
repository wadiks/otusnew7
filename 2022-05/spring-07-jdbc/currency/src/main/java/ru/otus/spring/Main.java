package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);
     //   getEmployeeDemo2();
     //   PersonDao dao = context.getBean(PersonDao.class);



        Console.main(args);
    }

   public void zapros (){
       // http://cbr.ru/scripts/XML_daily.asp?date_req=26.10.2022
       // TODO Auto-generated method stub
       MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
       Map map = new HashMap<String, String>();
       map.put("Content-Type", "application/json");

       headers.setAll(map);

       Map req_payload = new HashMap();
       req_payload.put("date_req", "26.10.2022");

       HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
       String url = "http://cbr.ru/scripts/XML_daily.asp";

       ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);
      // ResponseEntity<?> response = new RestTemplate().getForEntity(url, request, String.class);
       // ServiceResponse entityResponse = (ServiceResponse) response.getBody();
       System.out.println(response);


   }


    public static void getEmployeeDemo2() {
       // http://cbr.ru/scripts/XML_daily.asp?date_req=26.10.2022
        String url = "http://cbr.ru/scripts/XML_daily.asp?date_req=22.08.2022";


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        System.out.println("Status Code: " + responseEntity.getStatusCode());
        System.out.println("response==============" +responseEntity.getBody());
    }



}
