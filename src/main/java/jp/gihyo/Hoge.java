package jp.gihyo;

import java.util.Collection;

public class Hoge {

    private Hoge() {
        System.out.println("Hoge Constructor");
    }

    public Fuga getFuga() {
        return null;
    }

    public String getString() {
        return "HOGE";
    }

    public Object getObject() {
        return new Object();
    }

    public Collection<?> getCollection() {
        return null;
    }

    public int getInt() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return "HOGE";
    }

    public String method() {
        return "hoge";
    }

    public String method(String parameter) {
        return parameter;
    }
}
