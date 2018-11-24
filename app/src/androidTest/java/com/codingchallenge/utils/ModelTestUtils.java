package com.codingchallenge.utils;

import android.support.annotation.NonNull;

import com.codingchallenge.data.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anil Gudigar on 11/12/18.
 */
public class ModelTestUtils {
    // =======================================================================================
    // Sample Message creator
    // =======================================================================================

    public static Message sampleMessage(Long id) {
        return sampleMessage(id, "Test message");
    }

    public static Message sampleMessage(Long id, String subject) {
        Message message = new Message(subject);
        message.setId(id);
        return message;
    }

    // =======================================================================================
    // Sample Message List creator
    // =======================================================================================

    public static List<Message> sampleMessageList(int size, @NonNull Long id) {
        return sampleIssueList(0, size, id);
    }

    public static List<Message> sampleIssueList(int startIndex, int size, @NonNull Long id) {
        List<Message> list = new ArrayList<>();
        for (int i = startIndex; i < startIndex + size; i++) {
            list.add(sampleMessage(id));
        }
        return list;
    }
}
