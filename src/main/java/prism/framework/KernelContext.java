/*
 * Copyright (c) 2014 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package prism.framework;

/**
 * A container that holds prism's core kernel class.
 *
 * This will typically be created by and stored or implemented by your
 * Application class (recommended).
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
public interface KernelContext
{
    /**
     * Get the instance of the Prism kernel. This should NOT be created every
     * time, but should be held onto for the life of the application.
     */
    public PrismKernel getKernel();
}
