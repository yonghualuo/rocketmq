package org.apache.rocketmq.broker.client;

import org.apache.rocketmq.common.constant.LoggerName;
import org.apache.rocketmq.logging.InternalLogger;
import org.apache.rocketmq.logging.InternalLoggerFactory;

import java.util.Set;

/**
 * @author luoyonghua
 * @since 2020-06-09 20:01
 */
public class ConsumerEnvInfo {
    private static final InternalLogger log = InternalLoggerFactory.getLogger(LoggerName.BROKER_LOGGER_NAME);

    // consumer所在env
    private final String envLabel;
    // 订阅时维护的所有env标识集
    private Set<String> consumeEnvLabels;

    public ConsumerEnvInfo(String envLabel, Set<String> consumeEnvLabels) {
        this.envLabel = envLabel;
        this.consumeEnvLabels = consumeEnvLabels;
    }

    public String getEnvLabel() {
        return envLabel;
    }

    public Set<String> getConsumeEnvLabels() {
        return consumeEnvLabels;
    }

    public void setConsumeEnvLabels(Set<String> consumeEnvLabels) {
        this.consumeEnvLabels = consumeEnvLabels;
    }
}
