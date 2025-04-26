package org.ai.aicopilotforapi.agent.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author think
 * @version 1.0
 * @description:
 * @date 2025/4/24 21:31
 */
@Data
@Accessors(chain = true)
public class GenerateCodeReq {
    private String entityName;
    private String fields;
}
