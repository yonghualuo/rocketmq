/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.client.consumer.store;

import java.util.Map;
import java.util.Set;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * Offset store interface
 *
 * 主要分为本地文件类型和Broker代存的类型两种。
 * 对 @see DefaultMQPushConsumer来说, 默认是CLUSTERING模式, 使用的是RemotBrokerOffsetStore结构。广播模式时，消费进度保存在消费端。
 */
public interface OffsetStore {
    /**
     * Load
     * 从消息进度存储文件加载消息进度到内存
     */
    void load() throws MQClientException;

    /**
     * Update the offset,store it in memory
     * 更新内存中的消息消费进度
     * @param increaseOnly true表示offset必须大于内存中当前的消费偏移量才更新。
     */
    void updateOffset(final MessageQueue mq, final long offset, final boolean increaseOnly);

    /**
     * 读取消息消费进度
     *
     * Get offset from local storage
     * @param type READ_FROM_MEMORY：从内存中；READ_FROM_STORE：从磁盘中；MEMORY_FIRST_THEN_STORE；先从内存读取，再从磁盘。
     * @return The fetched offset
     */
    long readOffset(final MessageQueue mq, final ReadOffsetType type);

    /**
     * 持久化执行消息队列进度到磁盘。
     *
     * Persist all offsets,may be in local storage or remote name server
     */
    void persistAll(final Set<MessageQueue> mqs);

    /**
     * Persist the offset,may be in local storage or remote name server
     */
    void persist(final MessageQueue mq);

    /**
     * 将消息队列的消费进度从内存中移除。
     *
     * Remove offset
     */
    void removeOffset(MessageQueue mq);

    /**
     * 克隆该主题下所有消息队列的消息消费进度。
     * @return The cloned offset table of given topic
     */
    Map<MessageQueue, Long> cloneOffsetTable(String topic);

    /**
     * 更新存储在Broker端的消息消费进度，使用集群模式。
     * @param mq
     * @param offset
     * @param isOneway
     */
    void updateConsumeOffsetToBroker(MessageQueue mq, long offset, boolean isOneway) throws RemotingException,
        MQBrokerException, InterruptedException, MQClientException;
}
