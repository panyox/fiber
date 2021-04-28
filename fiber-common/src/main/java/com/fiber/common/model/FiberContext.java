package com.fiber.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author panyox
 */
@Data
@NoArgsConstructor
public class FiberContext implements Serializable {

    private String rpcType;

    private String path;

    private String method;

    private String sign;

    private String authorization;

    private Long startTime;

}
