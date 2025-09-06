package com.internintelligence.gmailapplication.model.request;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GmailRequest {
    private String to;
    private String subject;
    private String body;
}
