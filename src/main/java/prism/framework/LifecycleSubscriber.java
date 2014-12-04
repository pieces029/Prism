/*
 * Copyright (c) 2014 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package prism.framework;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * An implementation of Android's activity lifecycle callbacks that will hook
 * into the prism hooks automatically for activities.
 *
 * If you are using this subscriber in your application you should NOT be
 * calling the static facades in `PrismFacade` This will do the equivalent
 * automatically for every activity in your application.
 *
 * Currently, there is no equivalent to this class for Fragments, so you will
 * have to use the `PrismFacade` class in those cases.
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
public class LifecycleSubscriber implements Application.ActivityLifecycleCallbacks
{
    final private PrismKernel kernel;

    @SuppressWarnings("unused")
    public LifecycleSubscriber(KernelContext context)
    {
        this(context.getKernel());
    }

    public LifecycleSubscriber(PrismKernel kernel)
    {
        this.kernel = kernel;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.kernel.bootstrap(activity);
    }

    @Override public void onActivityStarted(Activity activity) {}
    @Override public void onActivityResumed(Activity activity) {}
    @Override public void onActivityPaused(Activity activity) {}
    @Override public void onActivityStopped(Activity activity) {}
    @Override public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {}
    @Override public void onActivityDestroyed(Activity activity) {}
}
