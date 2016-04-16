package com.didekin.trash.dominio;

/**
 * User: pedro@didekin
 * Date: 05/04/16
 * Time: 16:38
 */

public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
