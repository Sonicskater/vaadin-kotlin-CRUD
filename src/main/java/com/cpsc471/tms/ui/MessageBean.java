package com.cpsc471.tms.ui;

import java.time.LocalTime;
import org.springframework.stereotype.Service;
@Deprecated
@Service
public class MessageBean {

    public String getMessage() {
        return "Button was clicked at " + LocalTime.now();
    }
}
