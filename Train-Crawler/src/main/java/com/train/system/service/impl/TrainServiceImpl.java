package com.train.system.service.impl;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.train.system.entity.TrainEntity;
import com.train.system.service.TrainService;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static String baseURI = "https://api.railwayapi.com/v2/route/train/";

    @Override
    public List<TrainEntity> getTrains(String apiKey, String trainId) throws IOException {
        List<TrainEntity> list = new ArrayList<TrainEntity>();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String uri = MessageFormat.format("{0}{1}/apikey/{2}/", baseURI, trainId, apiKey);

        ResponseEntity<JsonNode> result = restTemplate.exchange(uri, HttpMethod.GET, null, JsonNode.class);
        return prepareList(list, result.getBody());

    }

    private List<TrainEntity> prepareList(List<TrainEntity> list, JsonNode body) throws IOException {

        final JsonNode arrNode = objectMapper.readTree(body.toString()).get("route");
        if (arrNode.isArray()) {
            for (final JsonNode objNode : arrNode) {

                TypeReference<TrainEntity> toValueTypeRef = new TypeReference<TrainEntity>() {
                };
                list.add(objectMapper.convertValue(objNode, toValueTypeRef));
            }
        }
        return list;
    }

}
