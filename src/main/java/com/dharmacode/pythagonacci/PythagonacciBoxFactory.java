package com.dharmacode.pythagonacci;

public class PythagonacciBoxFactory {

    private static final Long[] DEFAULT_ROOT_BOX_VALUES = new Long[]{1L, 1L, 2L, 3L};
    private static final Integer DEFAULT_MAX_DEPTH = 5;

    private final Integer maxDepth;
    private final Long[] rootBoxValues;

    private PythagonacciBoxFactory(Integer maxDepth, Long[] rootBoxValues) {
        this.maxDepth = maxDepth != null ? maxDepth : DEFAULT_MAX_DEPTH;
        this.rootBoxValues = rootBoxValues != null ? rootBoxValues : DEFAULT_ROOT_BOX_VALUES;
    }

    private PythagonacciBox createRootBox() {
        return new PythagonacciBox(maxDepth, rootBoxValues);
    }

    public static PythagonacciBox create() {
        return new PythagonacciBoxFactory(DEFAULT_MAX_DEPTH, DEFAULT_ROOT_BOX_VALUES).createRootBox();
    }

    public static PythagonacciBox create(Integer maxDepth) {
        return new PythagonacciBoxFactory(maxDepth, DEFAULT_ROOT_BOX_VALUES).createRootBox();
    }

    public static PythagonacciBox create(Long[] rootBoxValues) {
        return new PythagonacciBoxFactory(DEFAULT_MAX_DEPTH, rootBoxValues).createRootBox();
    }

    public static PythagonacciBox create(Integer maxDepth, Long[] values) {
        return new PythagonacciBoxFactory(maxDepth, values).createRootBox();
    }
}
