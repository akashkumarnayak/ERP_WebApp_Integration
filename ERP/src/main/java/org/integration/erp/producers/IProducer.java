package org.integration.erp.producers;

public interface IProducer<T> {
    public void sendMessage(String topic, T message);
}
