package com.train.system.service;

import java.io.IOException;
import java.util.List;

import com.train.system.entity.TrainEntity;

public interface TrainService {

    public List<TrainEntity> getTrains(String apiKey, String trainId) throws IOException;

}
