/*
 * Copyright (c) 2014 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package prism.framework;

import android.app.Activity;
import dagger.ObjectGraph;

import java.util.Map;

/**
 * A container that holds Dagger's object graphs.
 *
 * This is the root "configuration" for the Dependency Injection system.
 * Typically this will be created in the activity and stored or may be
 * implemented directly by the activity.
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
public interface GraphContext
{
    /**
     * Get the set of modules to be injected into all activities.
     *
     * These modules will be re-drawn on every new activity.
     *
     * @param activity The activity that will be injected with the modules.
     */
    public Object[] getActivityModules(Activity activity);

    /**
     * Set of modules to be injected into everything application-wide.
     *
     * This gets to be a fully drawn object graph, since it will only ever be
     * drawn once at application bootstrap. every subsequent module is added
     * onto this one.
     */
    public ObjectGraph getApplicationGraph();

    /**
     * Additional sub-modules that will only be attached for classes annotated
     * with the same scope.
     *
     * In this map, the `Class` key is to be the Module class reference and the
     * Object is to be an instantiated version of that class.
     * For example, to make the following scope available:
     *
     *     {@literal @Scope(EventModule.class)}
     *
     * you might have:
     *
     * ~~~~
     * public Map<Class, Object> getScopeModules(Activity activity) {
     *   LinkedHashMap<Class, Object> definitions = new LinkedHashMap<>();
     *   definitions.put(EventModule.class, new EventModule());
     *
     *   return definitions;
     * }
     * ~~~~
     *
     * These sub-modules are only available to activities.
     *
     * @see prism.framework.ModuleScope
     * @param activity The activity that will be injected with the modules.
     */
    public Map<Class, Object> getScopeModules(Activity activity);
}

