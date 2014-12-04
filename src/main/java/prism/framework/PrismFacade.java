/*
 * Copyright (c) 2014 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package prism.framework;

import android.app.Activity;
import android.app.Application;

/**
 * A series of convenience facades for bootstrapping prism where no lifecycle
 * callbacks are available.
 *
 * This is intended to be used in lifecycle methods of fragments as a quick
 * and dirty way to bootstrap the framework.
 * This can be used on an activity, but it is recommended to use the
 * `LifecycleSubscriber` instead.
 *
 * @see prism.framework.LifecycleSubscriber
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
@SuppressWarnings("unused")
final public class PrismFacade
{
    private PrismFacade() {}

    /**
     * Bootstrap the framework on an activity.
     *
     * If your application is implementing the `KernelContext` itself you may
     * use `bootstrap(Activity)` for simpler access.
     */
    public static void bootstrap(KernelContext context, Activity activity)
    {
        PrismKernel kernel = context.getKernel();
        kernel.bootstrap(activity);
    }

    /**
     * Bootstrap the framework on an activity.
     *
     * To use this your application MUST implement `KernelContext`
     */
    public static void bootstrap(Activity activity)
    {
        Application kernelApplication = activity.getApplication();
        KernelContext context = getContextFromApplication(kernelApplication);

        bootstrap(context, activity);
    }

    /**
     * Bootstrap the framework on a fragment.
     *
     * This will get the attached activity and the application from that.
     * This CANNOT be called until `onActivityCreated` is called and should
     * probably be done in the override of `onActivityCreated`.
     *
     * To use this your application MUST implement `KernelContext`
     */
    public static void bootstrap(android.app.Fragment fragment)
    {
        PrismKernel kernel = getKernelFromActivity(fragment.getActivity());

        kernel.bootstrap(fragment);
    }

    /**
     *
     * Bootstrap the framework on a support fragment.
     *
     * This will get the attached activity and the application from that.
     * This CANNOT be called until `onActivityCreated` is called and should
     * probably be done in the override of `onActivityCreated`.
     *
     * To use this your application MUST implement `KernelContext`
     */
    public static void bootstrap(android.support.v4.app.Fragment fragment)
    {
        PrismKernel kernel = getKernelFromActivity(fragment.getActivity());

        kernel.bootstrap(fragment);
    }

    /**
     * Get the kernel implemented by an application for a given activity.
     *
     * @throws java.lang.IllegalArgumentException if the application does not
     *     implement KernelContext.
     */
    private static PrismKernel getKernelFromActivity(Activity activity)
    {
        Application kernelApplication = activity.getApplication();
        KernelContext context = getContextFromApplication(kernelApplication);
        return context.getKernel();
    }

    /**
     * Get the kernel context implemented by an application.
     *
     * @throws java.lang.IllegalArgumentException if the application does not
     *     implement KernelContext.
     */
    private static KernelContext getContextFromApplication(Application application)
    {
        if (false == application instanceof KernelContext) {
            throw new IllegalArgumentException(
                "Your application must implement KernelContext in order to use the activity facade. " +
                    "If you are using a different container for the kernel you may specify it manually as a parameter " +
                    "see: `PrismFacade#bootstrap(KernelContext, Activity)`"
            );
        }

        return (KernelContext) application;
    }
}
