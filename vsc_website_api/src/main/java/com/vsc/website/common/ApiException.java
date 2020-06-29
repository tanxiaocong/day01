package com.vsc.website.common;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 267105423560454711L;
    private MessageCode code;
    private Integer language=Constant.CHINESE;
    private String messageKey;
    private String[] argv = null;

    public ApiException(MessageCode code,Integer language) {
        this.code = code;
        this.messageKey = code.name();
        if (language != null)
            this.language = language;
    }

    public ApiException(MessageCode code,Integer language, String... argv) {
        this.code = code;
        this.messageKey = code.name();
        this.argv = argv;
        if (language != null)
            this.language = language;
    }

    public MessageCode getCode() {
        return code;
    }

    public void setCode(MessageCode code) {
        this.code = code;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String[] getArgv() {
        return argv;
    }

    public void setArgv(String[] argv) {
        this.argv = argv;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    @Override
    public String getMessage() {
        return Util.getMessage(messageKey,language, argv);
    }
}
