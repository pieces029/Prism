package prism.framework;

final class Preconditions {
    private Preconditions() {
        /* No Instances */
    }

    static <T> T checkNotNull(T reference, String errorMessage)
    {
        if (null == reference) {
            throw new NullPointerException(errorMessage);
        }
        return reference;
    }
}
