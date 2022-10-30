package com.github.vladimirantin.core.utils;

public class Try {

    public static <T> T then(Wrapper<T> w) {
        return then(w, null);
    }
    public static <T> T then(Wrapper<T> w, Wrapper<T> defaultFunc, T defaultValue) {
        return then(w, defaultFunc, defaultValue, (e) -> {}, (e) -> {});
    }
    public static void then(VoidWrapper w) {
        then(w, null);
    }
    public static void then(VoidWrapper w, Object defaultValue) {
        then(() -> {
            w.codeThatThrows();
            return null;
        }, defaultValue);
    }
    public static <T> T then(Wrapper<T> w, T defaultValue) {
        return then(w, () -> null, defaultValue, (e) -> {}, (e) -> {});
    }

    public static <T> T thenCatch(Wrapper<T> w, Catch onException) {
        return thenCatch(w, onException, (e) -> {});
    }
    public static <T> T thenCatch(Wrapper<T> w, Catch onException, Catch onDefaultException) {
        return then(w, () -> null, null, onException, onDefaultException);
    }
    public static void thenCatch(VoidWrapper w, Catch onException) {
        thenCatch(w, onException, (e) ->{});
    }
    public static void thenCatch(VoidWrapper w, Catch onException, Catch onDefaultException) {
        then(() -> {
            w.codeThatThrows();
            return null;
        }, () -> null, null, onException, onDefaultException);
    }

    public static <T> T then(Wrapper<T> w, Wrapper<T> defaultFunc, T defaultValue, Catch onException, Catch onDefaultException ) {
        try {
            T value = w.codeThatThrows();
            if (value != null) {
                return value;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                onException.exception(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            T value = defaultFunc.codeThatThrows();
            if (value != null) {
                return value;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                onDefaultException.exception(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return defaultValue;
    }

    public interface Wrapper<T> {
        T codeThatThrows() throws Exception;
    }

    public interface VoidWrapper {
        void codeThatThrows() throws Exception;
    }

    public interface Catch {
        void exception(Exception e) throws Exception;
    }

}
