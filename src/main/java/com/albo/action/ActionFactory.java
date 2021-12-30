package com.albo.action;

import com.albo.exception.ActionFactoryException;
import com.albo.exception.PropertyManagerException;
import com.albo.util.PropertyManager;

import java.util.Map;

public class ActionFactory {
    private static final String ACTION_PROPERTIES_FILE_NAME = "action.properties";

    private Map<String, String> actions;

    public ActionFactory() throws ActionFactoryException {
        try {
            PropertyManager propertyManager = new PropertyManager(ACTION_PROPERTIES_FILE_NAME);
            actions = propertyManager.getHashMap();
        } catch (PropertyManagerException e) {
            throw new ActionFactoryException(e);
        }
    }

    public Action getAction(String actionName) throws ActionFactoryException {
        String actionClassPath = null;

        for (Map.Entry<String, String> entry : actions.entrySet()) {
            if (entry.getKey().equals(actionName)) {
                actionClassPath = entry.getValue();
            }
        }
        if (actionClassPath == null) throw new ActionFactoryException("Can't find action.");
        try {
            Class<?> actionClass = Class.forName(actionClassPath);
            return (Action) actionClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ActionFactoryException(e);
        }
    }
}
