package com.abc12366.message.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.model.BusinessMessage;
import com.abc12366.message.model.UserMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 消息控制器
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-02-20 4:25 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/queue", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class MessageController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Kafka 用户消息生产者
     *
     * @param data UserMessage
     */
    @PostMapping(path = "/user")
    public ResponseEntity sendUserMessage(@Valid @RequestBody UserMessage data) {
        LOGGER.info("{}", data);

//        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("user_message_topic", data);
//        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                LOGGER.info("Failed user_message_topic: " + data);
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, Object> result) {
//                LOGGER.info("Success user_message_topic: " + result.getProducerRecord().value());
//            }
//        });
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * Kafka 业务消息生产者
     *
     * @param data BusinessMessage
     */
    @PostMapping(path = "/business")
    public ResponseEntity sendBusinessMessage(@Valid @RequestBody BusinessMessage data) {
        LOGGER.info("{}", data);

//        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("business_message_topic", data);
//        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                LOGGER.info("Failed business_message_topic: " + data);
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, Object> result) {
//                LOGGER.info("Success business_message_topic: " + result.getProducerRecord().value());
//            }
//        });
        return ResponseEntity.ok(Utils.kv());
    }
}
