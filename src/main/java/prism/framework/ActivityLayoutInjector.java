/*
 * Copyright (c) 2014 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package prism.framework;

import android.app.Activity;

/**
 * Injects the Content View for the activity using the `@Layout` Annotation.
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
final class ActivityLayoutInjector
{
    /**
     * Injects the content view into an activity based on the Layout annotation.
     *
     * If the activity does not contain a layout annotation, this method will
     * do nothing.
     *
     * @see prism.framework.Layout
     * @param activity The activity to set the content view on
     */
    public void injectContentView(Activity activity)
    {
        Layout annotation = activity.getClass().getAnnotation(Layout.class);

        if (null == annotation) {
            return;
        }

        activity.setContentView(annotation.value());
    }
}
