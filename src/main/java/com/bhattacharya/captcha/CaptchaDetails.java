package com.bhattacharya.captcha;

import java.io.Serializable;

import nl.captcha.Captcha;

public class CaptchaDetails implements Serializable {

    private static final long serialVersionUID = 8372386434886698719L;

    private final String answer;
    private final Captcha captcha;

    public CaptchaDetails(String answer, Captcha captcha) {
        this.answer = answer;
        this.captcha = captcha;
    }

    public String getAnswer() {
        return answer;
    }

    public Captcha getCaptcha() {
        return captcha;
    }

}
