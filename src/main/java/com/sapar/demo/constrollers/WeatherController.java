package com.sapar.demo.constrollers;

import org.apache.catalina.util.ToStringUtil;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Entity;

import java.io.IOException;

@Controller
public class WeatherController {
    public String apiKey = "030f2fd6441b4276ad190154211806";

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public String weather() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        try {
            HttpGet request = new HttpGet("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=Almaty&aqi=no");
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            response.close();
            httpClient.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
}
