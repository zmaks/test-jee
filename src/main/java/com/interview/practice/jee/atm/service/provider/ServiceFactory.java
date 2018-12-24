package com.interview.practice.jee.atm.service.provider;

import com.interview.practice.jee.atm.service.Service;

/**
 * Factory that provides services which implements Service interface
 * @see Service
 */
public interface ServiceFactory {

    /**
     * Provides service
     * @param serviceClass service class
     * @param <T>
     * @return service with T class
     */
    <T extends Service> T get(Class<T> serviceClass);
}
