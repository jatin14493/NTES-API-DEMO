package com.train.system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainEntity {

    private Integer halt;
    private String scharr;
    private Integer day;
    private String schdep;
    private Integer distance;
    private String no;
    private Station station;

}
