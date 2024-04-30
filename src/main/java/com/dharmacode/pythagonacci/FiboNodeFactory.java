package com.dharmacode.pythagonacci;

public class FiboNodeFactory {

    private static final Long[] DEFAULT_ROOT_NODE_VALUES = new Long[]{1L, 1L, 2L, 3L};
    private static final Integer DEFAULT_MAX_DEPTH = 5;

    private final Integer maxDepth;
    private final Long[] rootNodeValues;

    private FiboNodeFactory(Integer maxDepth, Long[] rootNodeValues) {
        this.maxDepth = maxDepth != null ? maxDepth : DEFAULT_MAX_DEPTH;
        this.rootNodeValues = rootNodeValues != null ? rootNodeValues : DEFAULT_ROOT_NODE_VALUES;
    }

    private FiboNode createRootNode() {
        return new FiboNode(maxDepth, rootNodeValues);
    }

    public static FiboNode create() {
        return new FiboNodeFactory(DEFAULT_MAX_DEPTH, DEFAULT_ROOT_NODE_VALUES).createRootNode();
    }

    public static FiboNode create(Integer maxDepth) {
        return new FiboNodeFactory(maxDepth, DEFAULT_ROOT_NODE_VALUES).createRootNode();
    }

    public static FiboNode create(Long[] rootNodeValues) {
        return new FiboNodeFactory(DEFAULT_MAX_DEPTH, rootNodeValues).createRootNode();
    }

    public static FiboNode create(Integer maxDepth, Long[] values) {
        return new FiboNodeFactory(maxDepth, values).createRootNode();
    }
}
