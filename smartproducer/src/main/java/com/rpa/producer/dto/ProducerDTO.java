package com.rpa.producer.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 20:17
 * @description: TODO
 * @version: 1.0
 */
@Data
public class ProducerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String verify;
    private String data;
}
